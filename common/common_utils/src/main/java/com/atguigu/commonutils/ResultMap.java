package com.atguigu.commonutils;

import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chairuntao
 * @version 1.0
 * @date 2020/5/7 12:28
 */
@Data
public class ResultMap {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    private ResultMap() {
    }

    public static ResultMap ok() {
        ResultMap r = new ResultMap();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    public static ResultMap error() {
        ResultMap r = new ResultMap();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    public ResultMap success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public ResultMap message(String message) {
        this.setMessage(message);
        return this;
    }

    public ResultMap code(Integer code) {
        this.setCode(code);
        return this;
    }

    public ResultMap data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public ResultMap data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}
