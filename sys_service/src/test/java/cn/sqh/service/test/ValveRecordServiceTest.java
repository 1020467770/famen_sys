package cn.sqh.service.test;

import cn.sqh.dao.sqlBuilder.RecordConditions;
import cn.sqh.domain.ValveRecord;
import cn.sqh.service.IValveRecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValveRecordServiceTest {

    @Autowired
    private IValveRecordService valveRecordService;

    @Test
    public void findByConditionsTest() {
        RecordConditions conditions = new RecordConditions();
//        conditions.setValveId(1);
//        conditions.setTemperatureLowLimit(-1f);
//        conditions.setTemperatureHighLimit(20f);
        conditions.setRecordTimeLowLimit("2021-06-11 15:00:00");
        conditions.setRecordTimeHighLimit("2021-07-11 15:00:00");
        final List<ValveRecord> valveRecordList = valveRecordService.findByConditions(0, 5, conditions);
        System.out.println(valveRecordList);
    }
}
