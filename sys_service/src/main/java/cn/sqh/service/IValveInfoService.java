package cn.sqh.service;

import cn.sqh.domain.ValveInfo;
import cn.sqh.exception.NotInDataBaseException;

import java.util.List;

public interface IValveInfoService {

    List<ValveInfo> getAllNewValveInfo();

    List<ValveInfo> findAll() throws NotInDataBaseException;

    List<ValveInfo> findByPage(Integer pageNum, Integer pageSize) throws NotInDataBaseException;
}
