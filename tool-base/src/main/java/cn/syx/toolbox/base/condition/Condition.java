package cn.syx.toolbox.base.condition;

import java.util.Map;

public interface Condition {

    /**
     * 判断数据在条件结构中是否匹配
     *
     * @param data map数据
     * @return boolean 是否匹配。true：匹配；false：不匹配
     */
    boolean evaluate(Map<String, ?> data);
}
