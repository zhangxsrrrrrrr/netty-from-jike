package com.dmm.keepalive;

import com.dmm.auth.AuthOperationResult;
import com.dmm.common.Operation;
import com.dmm.common.OperationResult;
import lombok.Data;
import lombok.extern.java.Log;

/**
 * @author zhangxun_a
 * @date 2023/2/22 22:48
 * @description: TODO
 */
@Data
@Log
public class KeepAliveOperation extends Operation {
    private long time = 123123L;

    @Override
    public OperationResult execute() {

        return new KeepAliveOperationResult(time);
    }
}
