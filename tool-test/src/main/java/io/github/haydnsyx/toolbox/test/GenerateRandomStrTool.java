package io.github.haydnsyx.toolbox.test;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateRandomStrTool {

    private GenerateRandomStrTool() {
        throw new UnsupportedOperationException();
    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateStr() {
        StringBuilder sb = new StringBuilder();
        // 生成的随机字符串长度
        int length = ThreadLocalRandom.current().nextInt(50);
        if (length < 10) {
            return null;
        }
        // 名字长度1～2位
        for (int i = 0; i < length; i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
