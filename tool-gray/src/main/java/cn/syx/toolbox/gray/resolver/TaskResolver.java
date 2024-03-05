package cn.syx.toolbox.gray.resolver;

import cn.syx.toolbox.gray.domain.GrayTaskConfig;

public interface TaskResolver {


    GrayTaskConfig parseTaskContent(String content);
}
