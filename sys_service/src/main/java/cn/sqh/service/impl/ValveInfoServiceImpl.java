package cn.sqh.service.impl;

import cn.sqh.dao.IValveInfoDao;
import cn.sqh.dao.IValveRecordDao;
import cn.sqh.domain.ValveInfo;
import cn.sqh.domain.ValveRecord;
import cn.sqh.exception.NotInDataBaseException;
import cn.sqh.service.IValveInfoService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Transactional
public class ValveInfoServiceImpl implements IValveInfoService {

    @Autowired
    private IValveInfoDao valveInfoDao;

    @Autowired
    private IValveRecordDao valveRecordDao;


    /**
     * 服务器模拟获取最新的所有阀门信息
     *
     * @return
     */
    @Override
    public List<ValveInfo> getAllNewValveInfo() {
        final List<ValveInfo> valveInfos = findAll();
        valveInfos.forEach(valveInfo -> {
            try {
                ValveInfo valveInfoBefore = (ValveInfo) valveInfo.clone();
                final Float temperatureBefore = valveInfo.getTemperature();
                final Float liquidLevelBefore = valveInfo.getLiquidLevel();
                final Integer remainPowerBefore = valveInfo.getRemainPower();
                final Integer dbmBefore = valveInfo.getDbm();
                valveInfo.setTemperature(temperatureBefore + RandomUtils.nextFloat(0f, 6f) - 3f);
                valveInfo.setLiquidLevel(liquidLevelBefore + RandomUtils.nextFloat(0.001f, 0.006f) - 0.003f);
                valveInfo.setRemainPower(remainPowerBefore + (RandomUtils.nextInt(0, 100) > 80 ? -1 : 0));
                valveInfo.setDbm(dbmBefore + RandomUtils.nextInt(0, 6) - 3);
                valveInfo.setUploadTime(new Date());
                valveInfoDao.updateByPrimaryKey(valveInfo);
                final ValveRecord valveRecord = new ValveRecord(
                        valveInfo.getTemperature(),
                        valveInfo.getLiquidLevel(),
                        valveInfo.getRemainPower(),
                        valveInfo.getDbm(),
                        valveInfo.getId()
                );
                valveRecord.setSelfWarningType(valveInfoBefore);
                valveRecordDao.insert(valveRecord);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        });
        return valveInfos;
    }

    @Override
    public List<ValveInfo> findAll() throws NotInDataBaseException {
        return valveInfoDao.selectAll();
    }

    @Override
    public List<ValveInfo> findByPage(Integer pageNum, Integer pageSize) throws NotInDataBaseException {
        PageHelper.startPage(pageNum, pageSize);
        return valveInfoDao.selectAll();
    }


}
