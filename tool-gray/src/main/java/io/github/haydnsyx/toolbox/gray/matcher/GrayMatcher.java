package io.github.haydnsyx.toolbox.gray.matcher;

import io.github.haydnsyx.toolbox.gray.domain.GrayRequest;
import io.github.haydnsyx.toolbox.gray.domain.GrayTaskConfig;

public interface GrayMatcher {

    boolean match(GrayRequest req, GrayTaskConfig task);
}
