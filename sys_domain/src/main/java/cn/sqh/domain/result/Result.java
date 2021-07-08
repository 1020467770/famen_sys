package cn.sqh.domain.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class Result {

    //访问成功
    public static final String RESULTTYPE_SUCCESS = "ok";
    //访问失败
    public static final String RESULTTYPE_FAILED = "fail";
    //拒绝访问
    public static final String RESULTTYPE_DINIED = "diny";
    //出现异常
    public static final String RESULTTYPE_EXCEPTION = "exception";

    @ApiModelProperty(value = "响应消息的状态")
    private String status;
    @ApiModelProperty(value = "响应消息的具体内容")
    private Object data;

    static public Result build(String status, Object data) {
        return new Result(status, data);
    }


    public Result(String status, Object data) {
        this.status = status;
        this.data = data;
    }

    public Result() {
    }


}