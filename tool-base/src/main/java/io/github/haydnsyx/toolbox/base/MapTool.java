package io.github.haydnsyx.toolbox.base;

import java.util.Map;

public class MapTool {

    private MapTool() {
        throw new UnsupportedOperationException();
    }

    /**
     * 判断Map是否为空
     *
     * @param map 目标Map
     * @return boolean 是否为空。true：空；false：非空
     */
    public static boolean isEmpty(final Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 判断Map是否非空
     *
     * @param map 目标Map
     * @return boolean 是否为空。true：非空；false：空
     */
    public static boolean isNotEmpty(final Map<?, ?> map) {
        return !isEmpty(map);
    }
}
