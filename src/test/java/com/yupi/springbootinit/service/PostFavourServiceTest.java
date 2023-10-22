package com.yupi.springbootinit.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.springbootinit.model.entity.Chart;

import javax.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 帖子收藏服务测试
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@SpringBootTest
class ChartFavourServiceTest {

    @Resource
    private ChartFavourService chartFavourService;

    private static final User loginUser = new User();

    @BeforeAll
    static void setUp() {
        loginUser.setId(1L);
    }

    @Test
    void doChartFavour() {
        int i = chartFavourService.doChartFavour(1L, loginUser);
        Assertions.assertTrue(i >= 0);
    }

    @Test
    void listFavourChartByPage() {
        QueryWrapper<Chart> chartQueryWrapper = new QueryWrapper<>();
        chartQueryWrapper.eq("id", 1L);
        chartFavourService.listFavourChartByPage(Page.of(0, 1), chartQueryWrapper, loginUser.getId());
    }
}
