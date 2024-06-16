package io.github.haydnsyx.toolbox.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import java.lang.reflect.Method;

public class MethodToolTest {

    @Test
    public void testConstructor() {
        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> Whitebox.invokeConstructor(MethodTool.class));
    }

    @Test
    public void testIsLocalMethod() throws NoSuchMethodException {
        MethodUnitTest unitTest = new MethodUnitTest();

        Method method = unitTest.getClass().getMethod("hello");
        Assertions.assertFalse(MethodTool.isLocalMethod(method));
        Assertions.assertTrue(MethodTool.isLocalMethod(unitTest.getClass().getMethod("toString")));
        Assertions.assertTrue(MethodTool.isLocalMethod(unitTest.getClass().getMethod("hashCode")));
        Assertions.assertTrue(MethodTool.isLocalMethod(unitTest.getClass().getMethod("equals", Object.class)));
        Assertions.assertTrue(MethodTool.isLocalMethod(unitTest.getClass().getMethod("getClass")));
        Assertions.assertTrue(MethodTool.isLocalMethod(unitTest.getClass().getMethod("notify")));
        Assertions.assertTrue(MethodTool.isLocalMethod(unitTest.getClass().getMethod("notifyAll")));
        Assertions.assertTrue(MethodTool.isLocalMethod(unitTest.getClass().getMethod("wait")));
//        Assertions.assertTrue(MethodTool.isLocalMethod(unitTest.getClass().getMethod("finalize")));
//        Assertions.assertTrue(MethodTool.isLocalMethod(unitTest.getClass().getMethod("clone")));
    }

    public class MethodUnitTest {
        public void hello() {
            System.out.println("hello");
        }
    }
}
