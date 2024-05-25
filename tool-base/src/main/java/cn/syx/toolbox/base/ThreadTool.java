package cn.syx.toolbox.base;

import java.util.concurrent.TimeUnit;

public class ThreadTool {

    private ThreadTool() {
        throw new UnsupportedOperationException();
    }

    /**
     * 休眠一段时间
     *
     * @param time 休眠时间
     * @param unit 休眠时间单位
     */
    public static void sleep(long time, TimeUnit unit) {
        try {
            Thread.sleep(unit.toMillis(time));
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }
}
