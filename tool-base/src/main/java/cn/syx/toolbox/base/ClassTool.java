package cn.syx.toolbox.base;

/**
 * Class相关工具
 *
 * @author syx
 */
public class ClassTool {

    private ClassTool() {
        throw new UnsupportedOperationException();
    }

    /**
     * 判断是否存在目标类
     *
     * @param className 类名
     * @return boolean 是否存在目标类。true：存在；false：不存在
     */
    public static boolean existClass(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
