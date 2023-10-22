package com.yupi.springbootinit.model.dto.chartfavour;

import java.io.Serializable;
import lombok.Data;

/**
 * 帖子收藏 / 取消收藏请求
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Data
public class ChartFavourAddRequest implements Serializable {

    /**
     * 帖子 id
     */
    private Long chartId;

    private static final long serialVersionUID = 1L;
}