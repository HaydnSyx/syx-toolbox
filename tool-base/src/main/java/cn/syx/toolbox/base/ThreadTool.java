package cn.syx.toolbox.base;

import java.util.concurrent.TimeUnit;

public class ThreadTool {

    private ThreadTool() {
        throw new UnsupportedOperationException();
    }

    public static void sleep(long time, TimeUnit unit) {
        try {
            Thread.sleep(unit.toMillis(time));
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }
}
