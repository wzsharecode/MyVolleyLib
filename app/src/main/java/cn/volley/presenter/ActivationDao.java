package cn.volley.presenter;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import cn.lib.base.IBaseRequest;
import cn.util.LogUtil;
import cn.util.interfac.INetResult;
import cn.volley.UrlConfig;

/**
 * Created by Mr Wang on 2017/1/14 00:53
 */
public class ActivationDao extends IBaseRequest{

    public String getmResult() {
        return mResult;
    }

    private String mResult;

    public ActivationDao(INetResult iNetResult){
        super(iNetResult);
    }

    @Override
    public void onRequestSuccess(String result, int requestCode) throws IOException {
        LogUtil.v("解析数据： " + result);
        this.mResult = result;
    }

    public void get() {
        getRequest(UrlConfig.API_GET, UrlConfig.CODE_GET);
    }

    public void post(String name, String pwd) {
        JSONObject jObject = new JSONObject();
        try {
            jObject.put("loginName",name);
            jObject.put("loginPsw",pwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        postStringRequest(UrlConfig.API_POST,jObject.toString(), UrlConfig.CODE_POST);
        postJsonRequest(UrlConfig.API_POST,jObject, UrlConfig.CODE_POST);
    }

}
