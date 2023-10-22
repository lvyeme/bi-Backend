package com.yupi.springbootinit.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.springbootinit.model.dto.chart.ChartQueryRequest;
import com.yupi.springbootinit.model.entity.Chart;
import javax.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 帖子服务测试
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@SpringBootTest
class ChartServiceTest {

    @Resource
    private ChartService chartService;

    @Test
    void searchFromEs() {
        ChartQueryRequest chartQueryRequest = new ChartQueryRequest();
        chartQueryRequest.setUserId(1L);
        Page<Chart> chartPage = chartService.searchFromEs(chartQueryRequest);
        Assertions.assertNotNull(chartPage);
    }

}