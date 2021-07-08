package cn.sqh.dao;

import cn.sqh.dao.sqlBuilder.RecordConditions;
import cn.sqh.dao.sqlBuilder.RecordSqlBuilder;
import cn.sqh.domain.ValveRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface IValveRecordDao extends Mapper<ValveRecord> {


    @SelectProvider(type = RecordSqlBuilder.class, method = "buildByConditions")
    List<ValveRecord> findRecordsByConditions(@Param("conditions") RecordConditions conditions);

}
