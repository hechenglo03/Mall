package com.hcl.Mall.utls;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JsonResult {

    private Integer code;

    private String message;

    private Object object;
}
