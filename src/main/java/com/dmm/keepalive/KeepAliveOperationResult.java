package com.dmm.keepalive;

import com.dmm.common.OperationResult;

/**
 * @author zhangxun_a
 * @date 2023/2/22 22:48
 * @description: TODO
 */
public class KeepAliveOperationResult extends OperationResult {
    private long time;

    public KeepAliveOperationResult(long time) {
        this.time = time;
    }
}
