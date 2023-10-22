package com.yupi.springbootinit.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.springbootinit.model.entity.Chart;
import javax.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 帖子收藏数据库操作测试
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@SpringBootTest
class ChartFavourMapperTest {

    @Resource
    private ChartFavourMapper chartFavourMapper;

    @Test
    void listUserFavourChartByPage() {
        IPage<Chart> page = new Page<>(2, 1);
        QueryWrapper<Chart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", 1);
        queryWrapper.like("content", "a");
        IPage<Chart> result = chartFavourMapper.listFavourChartByPage(page, queryWrapper, 1);
        Assertions.assertNotNull(result);
    }
}