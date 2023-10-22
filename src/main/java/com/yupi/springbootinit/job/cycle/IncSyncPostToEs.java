package com.yupi.springbootinit.job.cycle;

import com.yupi.springbootinit.esdao.ChartEsDao;
import com.yupi.springbootinit.mapper.ChartMapper;
import com.yupi.springbootinit.model.dto.chart.ChartEsDTO;
import com.yupi.springbootinit.model.entity.Chart;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 增量同步帖子到 es
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
// todo 取消注释开启任务
//@Component
@Slf4j
public class IncSyncChartToEs {

    @Resource
    private ChartMapper chartMapper;

    @Resource
    private ChartEsDao chartEsDao;

    /**
     * 每分钟执行一次
     */
    @Scheduled(fixedRate = 60 * 1000)
    public void run() {
        // 查询近 5 分钟内的数据
        Date fiveMinutesAgoDate = new Date(new Date().getTime() - 5 * 60 * 1000L);
        List<Chart> chartList = chartMapper.listChartWithDelete(fiveMinutesAgoDate);
        if (CollectionUtils.isEmpty(chartList)) {
            log.info("no inc chart");
            return;
        }
        List<ChartEsDTO> chartEsDTOList = chartList.stream()
                .map(ChartEsDTO::objToDto)
                .collect(Collectors.toList());
        final int pageSize = 500;
        int total = chartEsDTOList.size();
        log.info("IncSyncChartToEs start, total {}", total);
        for (int i = 0; i < total; i += pageSize) {
            int end = Math.min(i + pageSize, total);
            log.info("sync from {} to {}", i, end);
            chartEsDao.saveAll(chartEsDTOList.subList(i, end));
        }
        log.info("IncSyncChartToEs end, total {}", total);
    }
}
