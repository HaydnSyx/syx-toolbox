package io.github.haydnsyx.toolbox.test;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateRandomUidTool {

    private GenerateRandomUidTool() {
        throw new UnsupportedOperationException();
    }

    private static final ConcurrentHashMap<Integer, Boolean> UID_MAP = new ConcurrentHashMap<>();
    private static final int DEFAULT_MAX_RETRY_NUM = 3;
    private static final int DEFAULT_UID_LENGTH = 6;

    public static int generateUid() {
        return generateUid(DEFAULT_MAX_RETRY_NUM, DEFAULT_UID_LENGTH);
    }

    public static int generateUid(int maxRetryNum, int length) {
        if (maxRetryNum == 0) {
            maxRetryNum = 1;
        }
        // int 最大范围是-2147483648 ～ 2147483648, 所以最大随机数是 999999999，共9位
        if (length > 9 || length < 1) {
            throw new RuntimeException("uid的长度不符合标准");
        }
        // 最大尝试3次
        for (int i = 0; i < maxRetryNum; i++) {
            int uid = doGenerateUid(length);
            // 不存在则说明的uid有效
            if (Objects.isNull(UID_MAP.putIfAbsent(uid, Boolean.TRUE))) {
                return uid;
            }
        }
        throw new RuntimeException("生成UID失败");
    }

    private static int doGenerateUid(int length) {
        StringBuilder sb = new StringBuilder(length);
        // 首位不为0
        sb.append(ThreadLocalRandom.current().nextInt(1, 10));
        for (int i = 1; i < length; i++) {
            sb.append(ThreadLocalRandom.current().nextInt(10));
        }
        return Integer.parseInt(sb.toString());
    }
}
