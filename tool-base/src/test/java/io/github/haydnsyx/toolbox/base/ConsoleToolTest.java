package io.github.haydnsyx.toolbox.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

public class ConsoleToolTest {

    @Test
    public void testConstructor() {
        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> Whitebox.invokeConstructor(ConsoleTool.class));
    }

    @Test
    public void testPrintln() {
        ConsoleTool.println("test console tool");
    }
}