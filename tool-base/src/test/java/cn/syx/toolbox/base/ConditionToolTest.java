package cn.syx.toolbox.base;

import cn.syx.toolbox.base.condition.Condition;
import cn.syx.toolbox.base.ConditionTool;
import cn.syx.toolbox.base.condition.domain.ConditionBuilder;
import cn.syx.toolbox.base.condition.enums.CompareEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.powermock.reflect.Whitebox;

import java.util.Arrays;
import java.util.Map;

public class ConditionToolTest {

    @Test
    public void testConstructor() {
        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> Whitebox.invokeConstructor(ConditionTool.class));
    }

    @Test
    public void testParse1() {
        String expression = "name=syx   and a>b ";
        Condition condition = ConditionTool.parse(expression);

        String str = ConditionTool.translate(condition);
        Assert.assertEquals("name=syx and a>b", str);
        Assert.assertTrue(condition.evaluate(Map.of("name", "syx", "a", "c")));
    }

    @Test
    public void testParse2() {
        String expression = "name=syx and (city = beijing or version >1.0.0)  and (country=china and (xxx=zzz or yyy=zzz) and year = 2014)   and a>b and age in [17,18,19]";
        Condition condition = ConditionTool.parse(expression);

        String str = ConditionTool.translate(condition);
        Assert.assertEquals("name=syx and (city=beijing or version>1.0.0) and (country=china and (xxx=zzz or yyy=zzz) and year=2014) and a>b and age in [17, 18, 19]", str);
        Assert.assertTrue(condition.evaluate(Map.of(
                "name", "syx",
                "city", "beijing",
                "country", "china",
                "yyy", "zzz",
                "year", "2014",
                "a", "c",
                "age", 18
        )));
    }

    @Test
    public void testParse3() {
        Condition condition = ConditionBuilder.builder()
                .and("name", CompareEnum.EQUALS, "syx")
                .and(ConditionBuilder.builder()
                        .and("city", CompareEnum.EQUALS, "beijing")
                        .or("version", CompareEnum.GREATER_THAN, "1.0.0")
                )
                .and(ConditionBuilder.builder()
                        .and("country", CompareEnum.EQUALS, "china")
                        .and(ConditionBuilder.builder()
                                .and("xxx", CompareEnum.EQUALS, "zzz")
                                .or("yyy", CompareEnum.EQUALS, "zzz")
                        )
                )
                .or(ConditionBuilder.builder()
                        .and("bbb", CompareEnum.GREATER_THAN, "1.0.0")
                        .and("ccc", CompareEnum.EQUALS, "zzz")
                )
                .and("a", CompareEnum.GREATER_THAN, "b")
                .build();

        String str = ConditionTool.translate(condition);
        System.out.println(str);
    }

    @Test
    public void testParse4() {
        Condition condition = ConditionBuilder.builder()
                .and("name", CompareEnum.EQUALS, "syx")
                .or("a", CompareEnum.EQUALS, "b")
                .or(ConditionBuilder.builder()
                        .and("c", CompareEnum.EQUALS, "d")
                        .and("ccc", CompareEnum.EQUALS, "zzz")
                )
                .or(ConditionBuilder.builder()
                        .and(ConditionBuilder.builder()
                                .and("w", CompareEnum.EQUALS, "2")
                                .or("e", CompareEnum.EQUALS, "3")
                        )
                        .and("q", CompareEnum.EQUALS, "1")
                )
                .build();

        String str = ConditionTool.translate(condition);
        System.out.println(str);

        boolean evaluate = condition.evaluate(Map.of(
                "name", "aaa",
                "a", "b1",
                "c", "d",
                "ccc", "yyy",
                "e", "3",
                "q", "1"));
        Assert.assertTrue(evaluate);
    }

    @Test
    public void testParse5() {
        Condition condition = ConditionBuilder.builder()
                .and("name", CompareEnum.IN, Arrays.asList("syx", "abc"))
                .and("age", CompareEnum.NOT_IN, Arrays.asList(18, 19))
                .build();

        String str = ConditionTool.translate(condition);
        System.out.println(str);

        boolean evaluate = condition.evaluate(Map.of(
                "name", "abc",
                "age", 20
        ));
        Assert.assertTrue(evaluate);
    }
}
