package cn.lib.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import cn.util.interfac.INetResult;

/**
 * Created by Mr Wang on 2017/1/13 18:20
 */
public class BaseActivity extends Activity implements INetResult {
    @Override
    public void onRequestSuccess(int requestCode) {

    }

    @Override
    public void onRequestFailed(int num, String errorMessage) {

    }

    @Override
    public void onNoConnect() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);

    }
}
