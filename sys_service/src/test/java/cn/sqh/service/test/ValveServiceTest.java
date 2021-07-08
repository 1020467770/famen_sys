package cn.sqh.service.test;

import cn.sqh.domain.ValveInfo;
import cn.sqh.service.IValveInfoService;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValveServiceTest {

    @Autowired
    private IValveInfoService valveService;

    @Test
    public void findByPageTest() {
        final val valveList = valveService.findByPage(0, 8);
        System.out.println(valveList);
    }

    @Test
    public void findAllTest() {
        final val valveList = valveService.findAll();
        System.out.println(valveList);
    }

    @Test
    public void refreshTest() {
        final val valveList = valveService.findAll();
        System.out.println(valveList);
        valveService.getAllNewValveInfo();
        final List<ValveInfo> updatedValveInfos = valveService.findAll();
        System.out.println(updatedValveInfos);
    }
}
