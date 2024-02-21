package cn.syx.toolbox.base.proxy;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface IProxy {

    Object generateProxy(Object target, Consumer<Object> before, BiConsumer<Object, Object> after);
}
