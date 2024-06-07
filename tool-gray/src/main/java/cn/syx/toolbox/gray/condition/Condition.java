package cn.syx.toolbox.gray.condition;

import java.util.Map;

public interface Condition {

    boolean evaluate(Map<String, ?> data);
}
