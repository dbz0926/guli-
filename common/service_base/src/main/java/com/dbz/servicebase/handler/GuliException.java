package com.dbz.servicebase.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 10979
 * @description:
 * @date 2022/4/122:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException{
    private Integer code;
    private String msg;
}
