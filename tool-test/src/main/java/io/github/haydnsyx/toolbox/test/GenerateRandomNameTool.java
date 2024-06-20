package io.github.haydnsyx.toolbox.test;

import java.util.concurrent.ThreadLocalRandom;

public class GenerateRandomNameTool {

    private GenerateRandomNameTool() {
        throw new UnsupportedOperationException();
    }

    private static final String[] FIRST_NAMES = {"王", "李", "张", "刘", "陈", "杨", "黄", "吴", "赵", "周", "徐", "孙", "马", "朱", "胡", "郭", "何", "高", "林", "罗", "郑", "梁", "谢", "宋", "唐", "许", "韩", "冯", "邱", "曹", "彭", "曾", "肖", "田", "董", "袁", "潘", "于", "蒋", "蔡", "余", "杜", "叶", "程", "苏", "魏", "吕", "丁", "任", "沈", "姚", "卢", "傅", "钟", "姜", "崔", "谭", "廖", "范", "汪", "陆", "金", "石", "戴", "贾", "韦", "夏", "邓", "方", "卓", "蔺", "唐", "翟"};
    private static final String[] LAST_NAMES = {"丽", "敏", "静", "强", "磊", "军", "勇", "杰", "娟", "婷", "霞", "倩", "明", "健", "超", "玲", "芳", "娜", "洋", "艳", "秀", "欣", "成", "涛", "华", "国", "文", "建", "辉", "庆", "新", "利", "子", "峰", "亮", "刚", "立", "平", "中", "伟", "宇", "宁", "栋", "慧", "婉", "琳", "瑞", "娥", "媛", "露", "娴", "妍", "妮", "婕", "菲", "蓉", "萍", "琼", "彬", "宏", "云", "飞", "峥", "瑶", "燕", "婧", "洁", "媚", "芬", "波", "瑾", "晶", "欢", "东", "宝", "宁"};

    public static String generateName() {
        StringBuilder sb = new StringBuilder();
        String firstName = FIRST_NAMES[ThreadLocalRandom.current().nextInt(FIRST_NAMES.length)];
        sb.append(firstName);
        // 名字长度1～2位
        int num = ThreadLocalRandom.current().nextInt(1, 3);
        for (int i = 0; i < num; i++) {
            String lastName = LAST_NAMES[ThreadLocalRandom.current().nextInt(LAST_NAMES.length)];
            sb.append(lastName);
        }
        return sb.toString();
    }
}
