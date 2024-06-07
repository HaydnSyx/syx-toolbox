package cn.syx.toolbox.gray.resolver.impl;

import cn.syx.toolbox.base.StringTool;
import cn.syx.toolbox.gray.GrayTool;
import cn.syx.toolbox.gray.condition.Condition;
import cn.syx.toolbox.gray.condition.ConditionTool;
import cn.syx.toolbox.gray.domain.GrayTaskConfig;
import cn.syx.toolbox.gray.resolver.TaskResolver;
import com.alibaba.fastjson2.JSON;

public class SingleJsonTaskResolver implements TaskResolver {

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
