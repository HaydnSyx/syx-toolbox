package cn.syx.toolbox.base;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConsoleTool {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    private ConsoleTool() {
        throw new UnsupportedOperationException();
    }

    public static void println(String msg) {
        LocalDateTime now = LocalDateTime.now();
        System.out.printf("[%s] - %s%n", now.format(formatter), msg);
    }
}