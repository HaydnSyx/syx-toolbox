package cn.syx.toolbox.gray.matcher;

import cn.syx.toolbox.gray.domain.GrayRequest;
import cn.syx.toolbox.gray.domain.GrayTaskConfig;

public interface GrayMatcher {

    boolean match(GrayRequest req, GrayTaskConfig task);
}
