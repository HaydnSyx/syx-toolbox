package cn.syx.toolbox.base;

import java.io.*;
import java.util.Objects;

/**
 * 文件相关工具
 *
 * @author syx
 */
public class FileTool {

    private FileTool() {
        throw new UnsupportedOperationException();
    }

    /**
     * 从资源指定位置获取文件内容, 不存在则返回null
     *
     * @param cls      类对应的资源目录
     * @param filePath 资源目录下文件位置
     * @return String 文件内容
     */
    public static String readContentWithResource(Class<?> cls, String filePath) {
        if (StringTool.isBlank(filePath) || Objects.isNull(cls)) {
            return null;
        }

        try (InputStream stream = cls.getClassLoader().getResourceAsStream(filePath)) {
            if (stream == null) {
                return null;
            }

            return readStreamContent(stream);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 从文件中获取文件内容, 不存在则返回null
     *
     * @param file      文件
     * @return String 文件内容
     */
    public static String readFileContent(File file) {
        if (Objects.isNull(file) || !file.exists() || !file.isFile()) {
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 从流中获取内容, 不存在则返回null
     *
     * @param stream  文件流
     * @return String 文件内容
     */
    public static String readStreamContent(InputStream stream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            return null;
        }
    }
}
