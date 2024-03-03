package cn.syx.toolbox.gray.key.impl;

public class IntegerGrayKey extends AbstractGrayKey<Integer> {

    public IntegerGrayKey(Integer key) {
        super(key);
    }

    public static IntegerGrayKey of(Integer data) {
        return new IntegerGrayKey(data);
    }
}
