package cn.syx.toolbox.base.condition;

public class SimpleCondition implements Condition {

    private String key;
    private CompareEnum compareEnum;
    private Object value;

    public SimpleCondition(String key, CompareEnum compareEnum, Object value) {
        this.key = key;
        this.compareEnum = compareEnum;
        this.value = value;
    }

    // 根据具体情况实现比较逻辑
    @Override
    public boolean evaluate(Object object) {
        // 这里应该是获取object中的key值并与value进行比较的逻辑
        // 示例中省略具体实现，只是返回true
        return true;
    }
}
