package cn.syx.toolbox.base.condition.domain;

import cn.syx.toolbox.base.condition.enums.TokenEnum;

import java.util.ArrayList;
import java.util.List;

public class ConditionNode {

    private final ConditionNode parent;
    private final TokenEnum operator;
    private final String value;
    private final List<ConditionNode> children;

    public ConditionNode(ConditionNode parent, TokenEnum operator, String value) {
        this.parent = parent;
        this.operator = operator;
        this.value = value;
        this.children = new ArrayList<>();
    }

    public ConditionNode getParent() {
        return parent;
    }

    public TokenEnum getOperator() {
        return operator;
    }

    public String getValue() {
        return value;
    }

    public List<ConditionNode> getChildren() {
        return children;
    }
}
