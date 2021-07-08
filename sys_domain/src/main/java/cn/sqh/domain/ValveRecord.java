package cn.sqh.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "valve_records")
public class ValveRecord {

    public final static int WARNING_TYPE_SAFE = 0;//正常范围
    public final static int WARNING_TYPE_BIG_FLUCTUATION_TEMPERATURE = 1;//相比上次温度波动过大
    public final static int WARNING_TYPE_BIG_FLUCTUATION_LIQUID_LEVEL = 2;//相比上次液位波动过大
    public static final int WARNING_TYPE_OVERLOW_TEMPERATURE = 3;//温度过低
    public static final int WARNING_TYPE_OVERHIGH_TEMPERATURE = 4;//温度过高
    public static final int WARNING_TYPE_OVERLOW_LIQUID_LEVEL = 5;//液位过低
    public static final int WARNING_TYPE_OVERHIGH_LIQUID_LEVEL = 6;//液位过高
    public static final int WARNING_TYPE_LOW_POWER = 7;//电量过低
    public static final int WARNING_TYPE_LOW_DBM = 8;//信号强度低

    public static final float FLUCTUATION_LIMIT_TEMPERATURE = 2.3f;//温差波动为限制在2.3度以内
    public static final float FLUCTUATION_LIMIT_LIQUID_LEVEL = 0.0025f;//液位波动限制在0.0025以内
    public static final int LOWER_LIMIT_TEMPERATURE = -30;//最低温度界限为-30度
    public static final int UPPER_LIMIT_TEMPERATURE = 70;//最高温度界限为70度
    public static final float LOWER_LIMIT_LIQUID_LEVEL = 0.002f;//最低液位界限为0.002
    public static final float UPPER_LIMIT_LIQUID_LEVEL = 0.030f;//最高液位界限为0.030
    public static final int POWERLOW_WARNING_LIMIT = 20;//低于20%的电量的电量过低警告
    public static final int DBMLOW_WARNING_LIMIT = 30;//信号强度低于30的警告


    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Float temperature;
    @Column(name = "liquidLevel")
    private Float liquidLevel;
    @Column(name = "remainPower")
    private Integer remainPower;
    private Integer dbm;
    @Column(name = "recordTime")
    private Date recordTime;
    @Column(name = "valveId")
    private Integer valveId;
    @Column(name = "warningType")
    private String warningType;

    public ValveRecord(Float temperature, Float liquidLevel, Integer remainPower, Integer dbm, Integer valveId) {
        this.temperature = temperature;
        this.liquidLevel = liquidLevel;
        this.remainPower = remainPower;
        this.dbm = dbm;
        this.valveId = valveId;
    }

    public ValveRecord() {
    }

    public void setSelfWarningType(ValveInfo valveInfoBefore) {
        StringBuffer sb = new StringBuffer();
        if (Math.abs(valveInfoBefore.getTemperature() - temperature) >= FLUCTUATION_LIMIT_TEMPERATURE) {
            sb.append(WARNING_TYPE_BIG_FLUCTUATION_TEMPERATURE + " ");
        }
        if (Math.abs(valveInfoBefore.getLiquidLevel() - liquidLevel) >= FLUCTUATION_LIMIT_LIQUID_LEVEL) {
            sb.append(WARNING_TYPE_BIG_FLUCTUATION_LIQUID_LEVEL + " ");
        }
        if (temperature > UPPER_LIMIT_TEMPERATURE) {
            sb.append(WARNING_TYPE_OVERHIGH_TEMPERATURE + " ");
        }
        if (temperature < LOWER_LIMIT_TEMPERATURE) {
            sb.append(WARNING_TYPE_OVERLOW_TEMPERATURE + " ");
        }
        if (liquidLevel > UPPER_LIMIT_LIQUID_LEVEL) {
            sb.append(WARNING_TYPE_OVERHIGH_LIQUID_LEVEL + " ");
        }
        if (liquidLevel < LOWER_LIMIT_LIQUID_LEVEL) {
            sb.append(WARNING_TYPE_OVERLOW_LIQUID_LEVEL + " ");
        }
        if (remainPower < POWERLOW_WARNING_LIMIT) {
            sb.append(WARNING_TYPE_LOW_POWER + " ");
        }
        if (dbm < DBMLOW_WARNING_LIMIT) {
            sb.append(WARNING_TYPE_LOW_DBM + " ");
        }
        if (sb.length() == 0) {
            sb.append(WARNING_TYPE_SAFE);
        }
        warningType = sb.toString();
    }
}
