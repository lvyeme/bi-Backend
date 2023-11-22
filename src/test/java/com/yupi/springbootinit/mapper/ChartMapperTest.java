package com.yupi.springbootinit.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author LVye
 * @version 1.0
 */
@SpringBootTest
class ChartMapperTest {
    @Resource
    private ChartMapper chartMapper;

    @Test
    void queryChartData() {
        String chartId = "1717467292467945474";
        String querySql = String.format("select * from chart_%s", chartId);
        List<Map<String, Object>> resultData  = chartMapper.queryChartData(querySql);
        System.out.println(resultData);
    }
}