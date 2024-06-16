package io.github.haydnsyx.toolbox.gray.resolver;

import io.github.haydnsyx.toolbox.gray.domain.GrayTaskConfig;

public interface TaskResolver {


    GrayTaskConfig parseTaskContent(String content);
}
