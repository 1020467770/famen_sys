package cn.sqh.dao.sqlBuilder;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import tk.mybatis.mapper.util.StringUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RecordSqlBuilder {

    public static String buildByConditions(@Param("conditions") RecordConditions conditions) {
        final Integer valveId = conditions.getValveId();
        final Float temperatureLowLimit = conditions.getTemperatureLowLimit();
        final Float temperatureHighLimit = conditions.getTemperatureHighLimit();
        final Float liquidLevelLowLimit = conditions.getLiquidLevelLowLimit();
        final Float liquidLevelHighLimit = conditions.getLiquidLevelHighLimit();
        final String recordTimeLowLimitStr = conditions.getRecordTimeLowLimit();
        final String recordTimeHighLimitStr = conditions.getRecordTimeHighLimit();
        final Integer dBmLowLimit = conditions.getDBmLowLimit();
        final Integer dBmHighLimit = conditions.getDBmHighLimit();
        final String warningTypesStr = conditions.getWarningTypes();
        String[] warningTypesStrings = null;
        if (warningTypesStr != null) {
            warningTypesStrings = warningTypesStr.split(",");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd,hh:mm:ss");
        Date recordTimeLowLimit = null;
        Date recordTimeHighLimit = null;
        try {
            if (!StringUtils.isBlank(recordTimeLowLimitStr)) {
                recordTimeLowLimit = sdf.parse(recordTimeLowLimitStr);
            }
            if (!StringUtils.isBlank(recordTimeHighLimitStr)) {
                recordTimeHighLimit = sdf.parse(recordTimeHighLimitStr);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date finalRecordTimeLowLimit = recordTimeLowLimit;
        Date finalRecordTimeHighLimit = recordTimeHighLimit;
        String[] finalWarningTypesStrings = warningTypesStrings;
        return new SQL() {{
            SELECT("*");
            FROM("valve_records");
            if (valveId != null) {
                WHERE(" valveId=#{conditions.valveId} ");
            }
            if (temperatureLowLimit != null) {
                WHERE(" temperature>=#{conditions.temperatureLowLimit} ");
            }
            if (temperatureHighLimit != null) {
                WHERE(" #{conditions.temperatureHighLimit}>=temperature ");
            }
            if (liquidLevelLowLimit != null) {
                WHERE(" liquidLevel>=#{conditions.liquidLevelLowLimit} ");
            }
            if (liquidLevelHighLimit != null) {
                WHERE(" #{conditions.liquidLevelHighLimit}>=liquidLevel ");
            }
            if (dBmLowLimit != null) {
                WHERE(" dbm>=#{conditions.dBmLowLimit} ");
            }
            if (dBmHighLimit != null) {
                WHERE(" #{conditions.dBmHighLimit}>=dbm ");
            }
            if (finalRecordTimeLowLimit != null) {
                WHERE(" recordTime>=#{conditions.recordTimeLowLimit} ");
            }
            if (finalRecordTimeHighLimit != null) {
                WHERE(" #{conditions.recordTimeHighLimit}>=recordTime ");
            }
            if (finalWarningTypesStrings != null) {
                for (int i = 0; i < finalWarningTypesStrings.length; i++) {
                    final String warningTypesString = finalWarningTypesStrings[i];
                    if (StringUtils.isNumeric(warningTypesString)) {
                        WHERE(" warningType like '%" + Integer.parseInt(warningTypesString) + "%' ");
                        if (i != finalWarningTypesStrings.length - 1) {
                            OR();
                        }
                    }
                }
            }
        }}.toString();
    }
}
