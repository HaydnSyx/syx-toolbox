package cn.syx.toolbox.gray.option;

import cn.syx.toolbox.gray.matcher.GrayMatcher;
import cn.syx.toolbox.gray.matcher.impl.DefaultGrayMatcher;

import java.util.HashMap;
import java.util.Map;

public class GrayOption {

    private boolean delayLoad;

    private TaskLoaderOption loadTaskOption;

    private Class<? extends GrayMatcher> commonMatcherCls;

    private Map<String, Class<? extends GrayMatcher>> matcherClsMap;

    public TaskLoaderOption getLoadTaskOption() {
        return loadTaskOption;
    }

    public boolean isDelayLoad() {
        return delayLoad;
    }

    public Class<? extends GrayMatcher> getCommonMatcherCls() {
        return commonMatcherCls;
    }

    public Map<String, Class<? extends GrayMatcher>> getMatcherClsMap() {
        return matcherClsMap;
    }

    public static Builder builder() {
        return new Builder();
    }

    private GrayOption() {
    }

    public static class Builder {
        private boolean delayLoad;
        private TaskLoaderOption loadTaskOption;
        private Class<? extends GrayMatcher> commonMatcherCls = DefaultGrayMatcher.class;
        private final Map<String, Class<? extends GrayMatcher>> matcherClsMap = new HashMap<>();

        public Builder delayLoad(boolean delayLoad) {
            this.delayLoad = delayLoad;
            return this;
        }

        public Builder loadTaskOption(TaskLoaderOption loadTaskOption) {
            this.loadTaskOption = loadTaskOption;
            return this;
        }

        public Builder commonMatcher(Class<? extends GrayMatcher> matcherClass) {
            this.commonMatcherCls = matcherClass;
            return this;
        }

        public Builder customMatcher(String id, Class<? extends GrayMatcher> matcherClass) {
            this.matcherClsMap.put(id, matcherClass);
            return this;
        }

        public GrayOption build() {
            GrayOption grayOption = new GrayOption();
            grayOption.delayLoad = this.delayLoad;
            grayOption.loadTaskOption = this.loadTaskOption;
            grayOption.commonMatcherCls = this.commonMatcherCls;
            grayOption.matcherClsMap = this.matcherClsMap;
            return grayOption;
        }
    }
}
