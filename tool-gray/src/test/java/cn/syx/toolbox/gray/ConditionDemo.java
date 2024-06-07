package cn.syx.toolbox.gray;

import cn.syx.toolbox.gray.condition.Condition;
import cn.syx.toolbox.gray.condition.ConditionTool;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class ConditionDemo {

    public static void main(String[] args) {
        /*Condition condition = ConditionBuilder.builder()
                .and("name", CompareEnum.EQUALS, "syx")
                .and(ConditionBuilder.builder()
                        .and(ConditionBuilder.builder()
                                .and("age", CompareEnum.GREATER_THAN_OR_EQUAL, 18)
                                .and("version", CompareEnum.GREATER_THAN, "1.0.0")
                        )
                        .or("age", CompareEnum.LESS_THAN_OR_EQUAL, 10)
                )
//                .and("type", CompareEnum.IN, Arrays.asList("1", "2", "3"))
                .build();

        String transfer = ConditionTool.transfer(condition);
        System.out.println(transfer);

        // 在这里使用条件进行判断
        boolean result = condition.evaluate(Map.of(
                "name", "syx",
                "age", 8,
                "version", "2.0.0"
        ));
        System.out.println("Evaluation result: " + result);*/

        String s = "(name=syx and ((age>=18 and version>1.0.0) or (age<=10)))";
        Condition condition = ConditionTool.parse(s);
        System.out.println(1);
    }
}
