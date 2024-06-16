package io.github.haydnsyx.toolbox.base;

import java.util.Collection;

public class CollectionTool {

    private CollectionTool() {
        throw new UnsupportedOperationException();
    }

    /**
     * 判断集合是否为空
     *
     * @param co 目标集合
     * @return boolean 是否为空。true：空；false：非空
     */
    public static boolean isEmpty(final Collection<?> co) {
        return co == null || co.isEmpty();
    }

    /**
     * 判断集合是否非空
     *
     * @param co 目标集合
     * @return boolean 是否为空。true：非空；false：空
     */
    public static boolean isNotEmpty(final Collection<?> co) {
        return !isEmpty(co);
    }
}
