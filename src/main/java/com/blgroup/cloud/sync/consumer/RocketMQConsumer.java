package com.blgroup.cloud.sync.consumer;

import com.blgroup.cloud.sync.pojo.FlatMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: cloud-sync
 * @description:
 * @author: weijiankai
 * @create: 2021-01-05 16:49
 **/
@Service //由于rocketMQ 没有配置是否开启的开关，如果使用rocketMQ则添加此类到IOC中
@RocketMQMessageListener(topic = "goods_goods_info", consumerGroup = "canal-Group")
@Slf4j
public class RocketMQConsumer implements RocketMQListener<FlatMessage> {

    @Autowired
    private BaseConsumer baseConsumer;

    /**
     * 接收到消息进行处理
     *
     * @param message canal监控到的消息 json格式
     */
    @Override
    public void onMessage(FlatMessage message) {
        log.info("canal监控到消息{}", message);
        baseConsumer.doConsumption(message);
    }
}
