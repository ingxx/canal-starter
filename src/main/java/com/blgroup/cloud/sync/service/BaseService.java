package com.blgroup.cloud.sync.service;

import com.blgroup.cloud.sync.pojo.FlatMessage;

public interface BaseService {
    void update(FlatMessage message);

    void delete(FlatMessage message);

    void insert(FlatMessage message);
}
