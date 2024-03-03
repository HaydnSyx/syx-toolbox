package cn.syx.toolbox.gray.option.loader;

import cn.syx.toolbox.gray.loader.TaskLoader;
import cn.syx.toolbox.gray.loader.impl.MysqlTaskLoader;
import cn.syx.toolbox.gray.option.TaskLoaderOption;

public class MysqlTaskLoaderOption extends CommonTaskLoaderOption {

    private String url;

    private String username;

    private String password;

    private String db;

    private String table;

    private MysqlTaskLoaderOption(String url, String username, String password) {
        super(MysqlTaskLoader.class);
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static MysqlTaskLoaderOption of(String url, String username, String password) {
        return new MysqlTaskLoaderOption(url, username, password);
    }
}
