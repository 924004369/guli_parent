package com.atguigu.servciebase.config.exceptionHandle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chairuntao
 * @version 1.0
 * @date 2020/5/7 20:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException {

    private Integer code;
    private String msg;
}
