package com.dyzhome.common.exception;

import com.dyzhome.common.result.R;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 自定义异常
 * @author Dyz
 */
@Data
@AllArgsConstructor
public class SysException extends RuntimeException {
    private R result;
}
