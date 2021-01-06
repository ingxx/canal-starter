package com.blgroup.cloud.sync.service.impl.consumer;

import com.blgroup.cloud.sync.annotation.Table;
import com.blgroup.cloud.sync.pojo.FlatMessage;
import com.blgroup.cloud.sync.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * @program: cloud-sync
 * @description:
 * @author: weijiankai
 * @create: 2021-01-05 17:53
 **/
@Service
@Table("goods_info")
public class goodsInfoServiceImpl implements BaseService {
    @Override
    public void update(FlatMessage message) {

    }

    @Override
    public void delete(FlatMessage message) {

    }

    @Override
    public void insert(FlatMessage message) {

    }
}
