package com.blgroup.cloud.sync.consumer;

import com.blgroup.cloud.sync.annotation.Table;
import com.blgroup.cloud.sync.pojo.FlatMessage;
import com.blgroup.cloud.sync.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: cloud-sync
 * @description:
 * @author: weijiankai
 * @create: 2021-01-06 10:59
 **/

@Service
public class BaseConsumer implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    private Map<String, BaseService> baseServiceMap;


    /**
     * 启动时获取所有baseService实现类
     * 获取类上的注解，得到此类处理的表
     * 存储到baseServiceMap
     *
     * @param args 命令行参数
     * @throws Exception 父类异常
     */
    @Override
    public void run(String... args) throws Exception {
        Map<String, BaseService> beansOfType = applicationContext.getBeansOfType(BaseService.class);
        baseServiceMap = new HashMap<>();
        for (BaseService classObject : beansOfType.values()) {
            Table annotation = classObject.getClass().getAnnotation(Table.class);
            if (annotation != null) {
                baseServiceMap.put(annotation.value().toLowerCase(), classObject);
            }
        }
    }


    /**
     * 接收到消息后,获取对应的表并执行
     * @param message 接收到的消息
     */
    public void doConsumption(FlatMessage message) {
        String table = message.getTable().toLowerCase();
        if (baseServiceMap.containsKey(table)) {
            BaseService baseService = baseServiceMap.get(table);
            String type = message.getType().toLowerCase();
            switch (type) {
                case "update":
                    baseService.update(message);
                    break;
                case "delete":
                    baseService.delete(message);
                    break;
                case "insert":
                    baseService.insert(message);
                    break;
            }
        }
    }
}
