package cn.sqh.service;

import cn.sqh.dao.sqlBuilder.RecordConditions;
import cn.sqh.domain.ValveRecord;
import cn.sqh.exception.NotInDataBaseException;

import java.util.List;

public interface IValveRecordService {

    List<ValveRecord> findAll() throws NotInDataBaseException;

    List<ValveRecord> findByPage(Integer pageNum, Integer pageSize) throws NotInDataBaseException;

    List<ValveRecord> findByConditions(Integer pageNum, Integer pageSize, RecordConditions conditions);

}
