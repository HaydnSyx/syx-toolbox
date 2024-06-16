package io.github.haydnsyx.toolbox.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import java.util.Map;

public class BeanToolTest {

    @Test
    public void testConstructor() {
        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> Whitebox.invokeConstructor(BeanTool.class));
    }

    @Test
    public void testConvertToMap() {
        Assertions.assertTrue(BeanTool.convertToMap(null).isEmpty());

        User user = new User("张三", 18);
        Map<String, ?> map = BeanTool.convertToMap(user);
        Assertions.assertEquals(2, map.size());
        Assertions.assertEquals("张三", map.get("name"));
        Assertions.assertEquals(18, map.get("age"));
    }

    static class User {

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}