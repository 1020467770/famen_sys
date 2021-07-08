package cn.sqh.dao;

import cn.sqh.domain.ValveInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface IValveInfoDao extends Mapper<ValveInfo> {

    @Delete("truncate table valve_info")
    void deleteAll();

}
