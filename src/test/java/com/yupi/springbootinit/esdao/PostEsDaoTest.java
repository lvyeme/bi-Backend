package com.yupi.springbootinit.esdao;

import com.yupi.springbootinit.model.dto.chart.ChartEsDTO;
import com.yupi.springbootinit.model.dto.chart.ChartQueryRequest;
import com.yupi.springbootinit.model.entity.Chart;
import com.yupi.springbootinit.service.ChartService;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * 帖子 ES 操作测试
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@SpringBootTest
public class ChartEsDaoTest {

    @Resource
    private ChartEsDao chartEsDao;

    @Resource
    private ChartService chartService;

    @Test
    void test() {
        ChartQueryRequest chartQueryRequest = new ChartQueryRequest();
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Chart> page =
                chartService.searchFromEs(chartQueryRequest);
        System.out.println(page);
    }

    @Test
    void testSelect() {
        System.out.println(chartEsDao.count());
        Page<ChartEsDTO> ChartPage = chartEsDao.findAll(
                PageRequest.of(0, 5, Sort.by("createTime")));
        List<ChartEsDTO> chartList = ChartPage.getContent();
        System.out.println(chartList);
    }

    @Test
    void testAdd() {
        ChartEsDTO chartEsDTO = new ChartEsDTO();
        chartEsDTO.setId(1L);
        chartEsDTO.setTitle("test");
        chartEsDTO.setContent("test");
        chartEsDTO.setTags(Arrays.asList("java", "python"));
        chartEsDTO.setThumbNum(1);
        chartEsDTO.setFavourNum(1);
        chartEsDTO.setUserId(1L);
        chartEsDTO.setCreateTime(new Date());
        chartEsDTO.setUpdateTime(new Date());
        chartEsDTO.setIsDelete(0);
        chartEsDao.save(chartEsDTO);
        System.out.println(chartEsDTO.getId());
    }

    @Test
    void testFindById() {
        Optional<ChartEsDTO> chartEsDTO = chartEsDao.findById(1L);
        System.out.println(chartEsDTO);
    }

    @Test
    void testCount() {
        System.out.println(chartEsDao.count());
    }

    @Test
    void testFindByCategory() {
        List<ChartEsDTO> chartEsDaoTestList = chartEsDao.findByUserId(1L);
        System.out.println(chartEsDaoTestList);
    }
}
