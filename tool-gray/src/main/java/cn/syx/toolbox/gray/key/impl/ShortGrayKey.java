package cn.syx.toolbox.gray.key.impl;

public class ShortGrayKey extends AbstractGrayKey<Short> {

    public ShortGrayKey(Short key) {
        super(key);
    }

    public static ShortGrayKey of(Short data) {
        return new ShortGrayKey(data);
    }
}
