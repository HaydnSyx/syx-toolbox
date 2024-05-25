package cn.syx.toolbox.experiment.defer;

import cn.syx.toolbox.base.ConsoleTool;

import java.util.concurrent.TimeUnit;

public class CostTimeDefer implements IDefer {

    private long startTime = System.nanoTime();

    private TimeUnit unit;

    private CostTimeDefer(TimeUnit unit) {
        this.unit = unit;
    }

    public static CostTimeDefer create() {
        return create(TimeUnit.MILLISECONDS);
    }

    public static CostTimeDefer create(TimeUnit unit) {
        return new CostTimeDefer(unit);
    }

    @Override
    public void defer() {
        long endTime = System.nanoTime();
        long costTime = endTime - startTime;

        String time = "%d%s";
        if (TimeUnit.NANOSECONDS == this.unit) {
            ConsoleTool.println("Cost time: " + String.format(time, costTime, "ns"));
        } else if (TimeUnit.MICROSECONDS == this.unit) {
            ConsoleTool.println("Cost time: " + String.format(time, TimeUnit.NANOSECONDS.toMicros(costTime), "us"));
        } else if (TimeUnit.SECONDS == this.unit) {
            ConsoleTool.println("Cost time: " + String.format(time, TimeUnit.NANOSECONDS.toSeconds(costTime), "s"));
        } else {
            ConsoleTool.println("Cost time: " + String.format(time, TimeUnit.NANOSECONDS.toMillis(costTime), "ms"));
        }
    }
}
