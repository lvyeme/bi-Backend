package com.yupi.springbootinit.model.dto.chartfavour;

import com.yupi.springbootinit.common.PageRequest;
import com.yupi.springbootinit.model.dto.chart.ChartQueryRequest;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 帖子收藏查询请求
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ChartFavourQueryRequest extends PageRequest implements Serializable {

    /**
     * 帖子查询请求
     */
    private ChartQueryRequest chartQueryRequest;

    /**
     * 用户 id
     */
    private Long userId;

    private static final long serialVersionUID = 1L;
}