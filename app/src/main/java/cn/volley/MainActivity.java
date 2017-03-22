package cn.volley;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.lib.base.BaseActivity;
import cn.lib.core.Config;
import cn.volley.presenter.ActivationDao;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.editText)
    EditText editText;
    @InjectView(R.id.editText2)
    EditText editText2;
    @InjectView(R.id.textView)
    TextView textView;

    private ActivationDao mActivationDao;
    private String str = "结果： ";

    @Override
    public void onRequestSuccess(int requestCode) {
        super.onRequestSuccess(requestCode);
        str = str +"\n"+ mActivationDao.getmResult();
        textView.setText(str);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());

        mActivationDao = new ActivationDao(this);
    }

    @OnClick(R.id.button)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                mActivationDao.get();
                mActivationDao.post(editText.getText().toString().trim(), editText2.getText().toString().trim());
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        RequestQueue requestQueue = App.getHttpQueues();
        if (requestQueue != null) {
            requestQueue.cancelAll(Config.GET_TAG);
            requestQueue.cancelAll(Config.POST_TAG);
            requestQueue.cancelAll(Config.POST_JSON_TAG);

        }
    }
}
