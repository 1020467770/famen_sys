package cn.sqh.service.impl;

import cn.sqh.dao.IValveRecordDao;
import cn.sqh.dao.sqlBuilder.RecordConditions;
import cn.sqh.domain.ValveRecord;
import cn.sqh.exception.NotInDataBaseException;
import cn.sqh.service.IValveRecordService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ValveRecordServiceImpl implements IValveRecordService {

    @Autowired
    private IValveRecordDao valveRecordDao;

    @Override
    public List<ValveRecord> findAll() throws NotInDataBaseException {
        return valveRecordDao.selectAll();
    }

    @Override
    public List<ValveRecord> findByPage(Integer pageNum, Integer pageSize) throws NotInDataBaseException {
        PageHelper.startPage(pageNum, pageSize);
        return valveRecordDao.selectAll();
    }

    @Override
    public List<ValveRecord> findByConditions(Integer pageNum, Integer pageSize, RecordConditions conditions) {
        PageHelper.startPage(pageNum, pageSize);
        return valveRecordDao.findRecordsByConditions(conditions);
    }


}
