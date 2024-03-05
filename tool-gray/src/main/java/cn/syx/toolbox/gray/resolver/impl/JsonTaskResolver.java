package cn.syx.toolbox.gray.resolver.impl;

import cn.syx.toolbox.gray.domain.GrayTaskConfig;
import cn.syx.toolbox.gray.resolver.TaskResolver;
import com.alibaba.fastjson2.JSON;

public class JsonTaskResolver implements TaskResolver {

    @Override
    public GrayTaskConfig parseTaskContent(String content) {
        return JSON.parseObject(content, GrayTaskConfig.class);
    }
}
