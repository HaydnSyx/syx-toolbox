package io.github.haydnsyx.toolbox.gray.resolver.impl;

import io.github.haydnsyx.toolbox.base.StringTool;
import io.github.haydnsyx.toolbox.base.condition.Condition;
import io.github.haydnsyx.toolbox.base.ConditionTool;
import io.github.haydnsyx.toolbox.gray.domain.GrayTaskConfig;
import io.github.haydnsyx.toolbox.gray.resolver.TaskResolver;
import com.alibaba.fastjson2.JSON;

public class JsonTaskResolver implements TaskResolver {

    @Override
    public GrayTaskConfig parseTaskContent(String content) {
        GrayTaskConfig config = JSON.parseObject(content, GrayTaskConfig.class);
        if (StringTool.isNotBlank(config.getConditionExpress())) {
            Condition condition = ConditionTool.parse(config.getConditionExpress());
            config.wrapperCondition(condition);
        }
        return config;
    }
}
