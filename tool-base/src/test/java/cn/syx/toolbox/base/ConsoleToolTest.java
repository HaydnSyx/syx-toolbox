package cn.syx.toolbox.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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