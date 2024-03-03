package cn.syx.toolbox.gray.key.impl;

import cn.syx.toolbox.gray.key.GrayKey;

public abstract class AbstractGrayKey<T> implements GrayKey<T> {

    private final T key;

    public AbstractGrayKey(T key) {
        this.key = key;
    }

    @Override
    public T getData() {
        return this.key;
    }
}
