package com.atguigu.servciebase.config.exceptionHandle;

import lombok.Data;

/**
 * @author chairuntao
 * @version 1.0
 * @date 2020/5/7 20:00
 */
@Data
public class GuliException extends RuntimeException {

    private Integer code;
    private String msg;
}
