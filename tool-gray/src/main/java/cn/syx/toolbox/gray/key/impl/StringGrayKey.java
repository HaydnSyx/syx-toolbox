package cn.syx.toolbox.gray.key.impl;

public class StringGrayKey extends AbstractGrayKey<String> {

    public StringGrayKey(String key) {
        super(key);
    }

    public static StringGrayKey of(String data) {
        return new StringGrayKey(data);
    }
}
