package io.github.haydnsyx.toolbox.gray.key.impl;

public class LongGrayKey extends AbstractGrayKey<Long> {

    public LongGrayKey(Long key) {
        super(key);
    }

    public static LongGrayKey of(Long data) {
        return new LongGrayKey(data);
    }
}
