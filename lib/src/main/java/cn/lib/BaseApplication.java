package cn.lib;

import android.app.Application;
import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import cn.lib.core.Config;
import cn.util.Preferences;

public class BaseApplication extends Application {

    /**
     * 保存数据
     */
    public static Preferences mPreferences = null;

    /**
     * 上下文
     *
     * @return
     */
    private static Context instance;

    /**
     * 请求队列
     */
    public static RequestQueue queues;

    /**
     * 给队列添加请求
     *
     * @return
     */
    public static RequestQueue getHttpQueues()
    {
        return queues;
    }

    public static Context getInstance() {
        return instance;
    }

    public static Preferences getmPreferences() {
        return mPreferences;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
        // 封装提交的公共数据
        this.mPreferences = new Preferences(getSharedPreferences(Config.PREFERENCES_NAME, Context.MODE_PRIVATE));
        this.queues = Volley.newRequestQueue(getApplicationContext());
    }
}
