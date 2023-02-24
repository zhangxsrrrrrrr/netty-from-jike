package com.dmm.client.dispatch;

import com.dmm.common.OperationResult;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: zhangxun
 * @create: 2023-02-23 22:21
 * @description:
 **/
public class RequestPendingCenter {
    private final Map<Long, OperationResultFuture> map = new ConcurrentHashMap<>();

    public void add(Long streamId, OperationResultFuture future) {
        this.map.put(streamId, future);
    }

    public void set(Long streamId, OperationResult result) {
        OperationResultFuture resultFuture = map.get(streamId);
        if (resultFuture != null) {
            resultFuture.setSuccess(result);
            this.map.remove(streamId);
        }
    }
}
