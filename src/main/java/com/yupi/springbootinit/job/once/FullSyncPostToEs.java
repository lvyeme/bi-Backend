package com.yupi.springbootinit.job.once;

import com.yupi.springbootinit.esdao.ChartEsDao;
import com.yupi.springbootinit.model.dto.chart.ChartEsDTO;
import com.yupi.springbootinit.model.entity.Chart;
import com.yupi.springbootinit.service.ChartService;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.CommandLineRunner;

/**
 * 全量同步帖子到 es
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
// todo 取消注释开启任务
//@Component
@Slf4j
public class FullSyncChartToEs implements CommandLineRunner {

    @Resource
    private ChartService chartService;

    @Resource
    private ChartEsDao chartEsDao;

    @Override
    public void run(String... args) {
        List<Chart> chartList = chartService.list();
        if (CollectionUtils.isEmpty(chartList)) {
            return;
        }
        List<ChartEsDTO> chartEsDTOList = chartList.stream().map(ChartEsDTO::objToDto).collect(Collectors.toList());
        final int pageSize = 500;
        int total = chartEsDTOList.size();
        log.info("FullSyncChartToEs start, total {}", total);
        for (int i = 0; i < total; i += pageSize) {
            int end = Math.min(i + pageSize, total);
            log.info("sync from {} to {}", i, end);
            chartEsDao.saveAll(chartEsDTOList.subList(i, end));
        }
        log.info("FullSyncChartToEs end, total {}", total);
    }
}
