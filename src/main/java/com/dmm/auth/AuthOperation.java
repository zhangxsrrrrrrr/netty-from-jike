package com.dmm.auth;

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
public class AuthOperation extends Operation {
    private String userName;

    @Override
    public OperationResult execute() {
        if ("admin".equals(userName)) {
            return new AuthOperationResult(true);
        }
        return new AuthOperationResult(false);
    }
}
