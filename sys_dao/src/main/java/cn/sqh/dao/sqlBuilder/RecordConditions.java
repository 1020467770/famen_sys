package cn.sqh.dao.sqlBuilder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class RecordConditions {
    @ApiModelProperty(value = "要查询的温度的下界")
    private Float temperatureLowLimit;
    @ApiModelProperty(value = "要查询的温度的上界")
    private Float temperatureHighLimit;
    @ApiModelProperty(value = "要查询的液位的下界")
    private Float liquidLevelLowLimit;
    @ApiModelProperty(value = "要查询的液位的上界")
    private Float liquidLevelHighLimit;
    @ApiModelProperty(value = "要查询的信号强度的下界")
    private Integer dBmLowLimit;
    @ApiModelProperty(value = "要查询的信号强度的上界")
    private Integer dBmHighLimit;
    @ApiModelProperty(value = "要查询的记录时间的下界,格式如：2021-06-23,00:00:00",example = "2021-06-23,00:00:00")
    private String recordTimeLowLimit;//格式为：yyyy-MM-dd,hh:mm:ss
    @ApiModelProperty(value = "要查询的记录时间的上界，格式如：2021-06-23,23:59:59",example = "2021-06-23,23:59:59")
    private String recordTimeHighLimit;
    @ApiModelProperty(value = "要查询的阀门硬件Id")
    private Integer valveId;
    @ApiModelProperty(value = "要查询包含的警告类型的集合，格式如：1,3,8", example = "1,3,8")
    private String warningTypes;//格式：1,3,8 用逗号隔开
}
