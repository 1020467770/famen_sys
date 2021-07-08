package cn.sqh.web.controller;

import cn.sqh.dao.sqlBuilder.RecordConditions;
import cn.sqh.domain.result.Result;
import cn.sqh.service.IValveInfoService;
import cn.sqh.service.IValveRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/valve")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "阀门监控接口")
public class ValveController {

    @Autowired
    private IValveInfoService valveInfoService;

    @Autowired
    private IValveRecordService valveRecordService;

    @GetMapping("/getAllValveInfos.do")
    @ApiOperation(value = "获取已有的所有阀门的最新信息")
    public Result findValveInfos(@RequestParam(required = false, defaultValue = "false") Boolean isGetCurrent) {
        return Result.build(Result.RESULTTYPE_SUCCESS,
                isGetCurrent ? valveInfoService.getAllNewValveInfo() : valveInfoService.findAll());
    }

    /*@GetMapping("/getSomeValveInfo.do/{pageNum}/{pageSize}")
    @ApiOperation(value = "根据分页参数获取已有的阀门硬件信息")
    public Result findValveInfos(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        return Result.build(Result.RESULTTYPE_SUCCESS, valveInfoService.findByPage(pageNum, pageSize));
    }*/

    @GetMapping("/getSomeValveRecords.do/{pageNum}/{pageSize}")
    @ApiOperation(value = "根据分页参数获取已有的阀门上传的数据记录")
    public Result findValveRecords(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        return Result.build(Result.RESULTTYPE_SUCCESS, valveRecordService.findByPage(pageNum, pageSize));
    }

    @GetMapping("/getSomeValveRecordsByConditions.do/{pageNum}/{pageSize}")
    @ApiOperation(value = "根据分页参数以及条件信息获取已有的阀门上传的数据记录")
    public Result findValveRecords(@PathVariable("pageNum") Integer pageNum,
                                   @PathVariable("pageSize") Integer pageSize,
            /*@RequestBody*/ RecordConditions conditions
    ) {
        System.out.println(conditions);
        return Result.build(Result.RESULTTYPE_SUCCESS, valveRecordService.findByConditions(pageNum, pageSize, conditions));
    }

}
