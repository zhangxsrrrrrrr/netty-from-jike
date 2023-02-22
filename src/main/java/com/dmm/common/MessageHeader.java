package com.dmm.common;

import lombok.Data;

/**
 * @author zhangxun_a
 * @date 2023/2/22 22:36
 * @description: TODO
 */
@Data
public class MessageHeader {
    private int version = 1;
    private long streamId;
    private int opCode;

}
