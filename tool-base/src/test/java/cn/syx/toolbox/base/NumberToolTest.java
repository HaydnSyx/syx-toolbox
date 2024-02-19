package cn.syx.toolbox.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

public class NumberToolTest {

    @Test
    public void testConstructor() {
        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> Whitebox.invokeConstructor(NumberTool.class));
    }

    @Test
    public void testIsZeroWithShort() {
        Assertions.assertTrue(NumberTool.isZero((short) 0));
        Assertions.assertFalse(NumberTool.isZero((short) 1));
        Assertions.assertFalse(NumberTool.isZero((short) -1));

        Assertions.assertFalse(NumberTool.isZero((Short) null));
        Assertions.assertTrue(NumberTool.isZero(Short.valueOf((short) 0)));
        Assertions.assertFalse(NumberTool.isZero(Short.valueOf((short) 1)));
        Assertions.assertFalse(NumberTool.isZero(Short.valueOf((short) -1)));
    }

    @Test
    public void testIsPositiveWithShort() {
        Assertions.assertFalse(NumberTool.isPositive((short) 0));
        Assertions.assertTrue(NumberTool.isPositive((short) 1));
        Assertions.assertFalse(NumberTool.isPositive((short) -1));

        Assertions.assertFalse(NumberTool.isPositive((Short) null));
        Assertions.assertFalse(NumberTool.isPositive(Short.valueOf((short) 0)));
        Assertions.assertTrue(NumberTool.isPositive(Short.valueOf((short) 1)));
        Assertions.assertFalse(NumberTool.isPositive(Short.valueOf((short) -1)));
    }

    @Test
    public void testIsNegativeWithShort() {
        Assertions.assertFalse(NumberTool.isNegative((short) 0));
        Assertions.assertFalse(NumberTool.isNegative((short) 1));
        Assertions.assertTrue(NumberTool.isNegative((short) -1));

        Assertions.assertFalse(NumberTool.isNegative((Short) null));
        Assertions.assertFalse(NumberTool.isNegative(Short.valueOf((short) 0)));
        Assertions.assertFalse(NumberTool.isNegative(Short.valueOf((short) 1)));
        Assertions.assertTrue(NumberTool.isNegative(Short.valueOf((short) -1)));
    }

    @Test
    public void testIsNonPositiveWithShort() {
        Assertions.assertTrue(NumberTool.isNonPositive((short) 0));
        Assertions.assertFalse(NumberTool.isNonPositive((short) 1));
        Assertions.assertTrue(NumberTool.isNonPositive((short) -1));

        Assertions.assertTrue(NumberTool.isNonPositive((Short) null));
        Assertions.assertTrue(NumberTool.isNonPositive(Short.valueOf((short) 0)));
        Assertions.assertFalse(NumberTool.isNonPositive(Short.valueOf((short) 1)));
        Assertions.assertTrue(NumberTool.isNonPositive(Short.valueOf((short) -1)));
    }

    @Test
    public void testIsNonNegativeWithShort() {
        Assertions.assertTrue(NumberTool.isNonNegative((short) 0));
        Assertions.assertTrue(NumberTool.isNonNegative((short) 1));
        Assertions.assertFalse(NumberTool.isNonNegative((short) -1));

        Assertions.assertTrue(NumberTool.isNonNegative((Short) null));
        Assertions.assertTrue(NumberTool.isNonNegative(Short.valueOf((short) 0)));
        Assertions.assertTrue(NumberTool.isNonNegative(Short.valueOf((short) 1)));
        Assertions.assertFalse(NumberTool.isNonNegative(Short.valueOf((short) -1)));
    }

    @Test
    public void testIsZeroWithInt() {
        Assertions.assertTrue(NumberTool.isZero(0));
        Assertions.assertFalse(NumberTool.isZero(1));
        Assertions.assertFalse(NumberTool.isZero(-1));

        Assertions.assertFalse(NumberTool.isZero((Integer) null));
        Assertions.assertTrue(NumberTool.isZero(Integer.valueOf(0)));
        Assertions.assertFalse(NumberTool.isZero(Integer.valueOf(1)));
        Assertions.assertFalse(NumberTool.isZero(Integer.valueOf(-1)));
    }

    @Test
    public void testIsPositiveWithInt() {
        Assertions.assertFalse(NumberTool.isPositive(0));
        Assertions.assertTrue(NumberTool.isPositive(1));
        Assertions.assertFalse(NumberTool.isPositive(-1));

        Assertions.assertFalse(NumberTool.isPositive((Integer) null));
        Assertions.assertFalse(NumberTool.isPositive(Integer.valueOf(0)));
        Assertions.assertTrue(NumberTool.isPositive(Integer.valueOf(1)));
        Assertions.assertFalse(NumberTool.isPositive(Integer.valueOf(-1)));
    }

    @Test
    public void testIsNegativeWithInt() {
        Assertions.assertFalse(NumberTool.isNegative(0));
        Assertions.assertFalse(NumberTool.isNegative(1));
        Assertions.assertTrue(NumberTool.isNegative(-1));

        Assertions.assertFalse(NumberTool.isNegative((Integer) null));
        Assertions.assertFalse(NumberTool.isNegative(Integer.valueOf(0)));
        Assertions.assertFalse(NumberTool.isNegative(Integer.valueOf(1)));
        Assertions.assertTrue(NumberTool.isNegative(Integer.valueOf(-1)));
    }

    @Test
    public void testIsNonPositiveWithInt() {
        Assertions.assertTrue(NumberTool.isNonPositive(0));
        Assertions.assertFalse(NumberTool.isNonPositive(1));
        Assertions.assertTrue(NumberTool.isNonPositive(-1));

        Assertions.assertTrue(NumberTool.isNonPositive((Integer) null));
        Assertions.assertTrue(NumberTool.isNonPositive(Integer.valueOf(0)));
        Assertions.assertFalse(NumberTool.isNonPositive(Integer.valueOf(1)));
        Assertions.assertTrue(NumberTool.isNonPositive(Integer.valueOf(-1)));
    }

    @Test
    public void testIsNonNegativeWithInt() {
        Assertions.assertTrue(NumberTool.isNonNegative(0));
        Assertions.assertTrue(NumberTool.isNonNegative(1));
        Assertions.assertFalse(NumberTool.isNonNegative(-1));

        Assertions.assertTrue(NumberTool.isNonNegative((Integer) null));
        Assertions.assertTrue(NumberTool.isNonNegative(Integer.valueOf(0)));
        Assertions.assertTrue(NumberTool.isNonNegative(Integer.valueOf(1)));
        Assertions.assertFalse(NumberTool.isNonNegative(Integer.valueOf(-1)));
    }

    @Test
    public void testIsZeroWithLong() {
        Assertions.assertTrue(NumberTool.isZero(0L));
        Assertions.assertFalse(NumberTool.isZero(1L));
        Assertions.assertFalse(NumberTool.isZero(-1L));

        Assertions.assertFalse(NumberTool.isZero((Long) null));
        Assertions.assertTrue(NumberTool.isZero(Long.valueOf(0L)));
        Assertions.assertFalse(NumberTool.isZero(Long.valueOf(1L)));
        Assertions.assertFalse(NumberTool.isZero(Long.valueOf(-1L)));
    }

    @Test
    public void testIsPositiveWithLong() {
        Assertions.assertFalse(NumberTool.isPositive(0L));
        Assertions.assertTrue(NumberTool.isPositive(1L));
        Assertions.assertFalse(NumberTool.isPositive(-1L));

        Assertions.assertFalse(NumberTool.isPositive((Long) null));
        Assertions.assertFalse(NumberTool.isPositive(Long.valueOf(0L)));
        Assertions.assertTrue(NumberTool.isPositive(Long.valueOf(1L)));
        Assertions.assertFalse(NumberTool.isPositive(Long.valueOf(-1L)));
    }

    @Test
    public void testIsNegativeWithLong() {
        Assertions.assertFalse(NumberTool.isNegative(0L));
        Assertions.assertFalse(NumberTool.isNegative(1L));
        Assertions.assertTrue(NumberTool.isNegative(-1L));

        Assertions.assertFalse(NumberTool.isNegative((Long) null));
        Assertions.assertFalse(NumberTool.isNegative(Long.valueOf(0L)));
        Assertions.assertFalse(NumberTool.isNegative(Long.valueOf(1L)));
        Assertions.assertTrue(NumberTool.isNegative(Long.valueOf(-1L)));
    }

    @Test
    public void testIsNonPositiveWithLong() {
        Assertions.assertTrue(NumberTool.isNonPositive(0L));
        Assertions.assertFalse(NumberTool.isNonPositive(1L));
        Assertions.assertTrue(NumberTool.isNonPositive(-1L));

        Assertions.assertTrue(NumberTool.isNonPositive((Long) null));
        Assertions.assertTrue(NumberTool.isNonPositive(Long.valueOf(0L)));
        Assertions.assertFalse(NumberTool.isNonPositive(Long.valueOf(1L)));
        Assertions.assertTrue(NumberTool.isNonPositive(Long.valueOf(-1L)));
    }

    @Test
    public void testIsNonNegativeWithLong() {
        Assertions.assertTrue(NumberTool.isNonNegative(0L));
        Assertions.assertTrue(NumberTool.isNonNegative(1L));
        Assertions.assertFalse(NumberTool.isNonNegative(-1L));

        Assertions.assertTrue(NumberTool.isNonNegative((Long) null));
        Assertions.assertTrue(NumberTool.isNonNegative(Long.valueOf(0L)));
        Assertions.assertTrue(NumberTool.isNonNegative(Long.valueOf(1L)));
        Assertions.assertFalse(NumberTool.isNonNegative(Long.valueOf(-1L)));
    }


    @Test
    public void testAbs() {
        Assertions.assertEquals(0, NumberTool.abs(0));
        Assertions.assertEquals(10, NumberTool.abs(10));
        Assertions.assertEquals(1, NumberTool.abs(-1));
        Assertions.assertEquals(10, NumberTool.abs(-10));
        Assertions.assertEquals(100, NumberTool.abs(-100));
        Assertions.assertEquals(1000, NumberTool.abs(-1000));
        Assertions.assertEquals(Integer.MAX_VALUE, NumberTool.abs(Integer.MIN_VALUE));
        Assertions.assertEquals(Integer.MAX_VALUE, NumberTool.abs(Integer.MAX_VALUE));
    }
}
