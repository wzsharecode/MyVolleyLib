package cn.lib.base;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import cn.lib.BaseApplication;
import cn.lib.core.Config;
import cn.util.HttpTool;
import cn.util.LogUtil;
import cn.util.interfac.INetResult;

public abstract class IBaseRequest {
    protected INetResult mResult;

    public IBaseRequest(INetResult iNetResult) {
        this.mResult = iNetResult;
    }

    /**
     * 得到结果后，对结果处理逻辑
     *
     * @param result
     * @param requestCode
     * @throws IOException
     */

    public abstract void onRequestSuccess(String result, int requestCode) throws IOException;

    /**
     * get请求网络
     *
     * @param url         请求地址
     * @param requestCode 请求编号
     */
    protected void getRequest(String url, final int requestCode) {
        LogUtil.v("URL: get "+ Config.IP+url);
        //判断是否有网络
        if (!HttpTool.isNetworkAvailable(BaseApplication.getInstance())) {
            mResult.onNoConnect();
            return;
        }

        StringRequest request = new StringRequest(Request.Method.GET, Config.IP+url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) {
                        LogUtil.v("---成功获取数据-get---" + result);
                        /**
                        JSONObject json = null;
                        try {
                            json = new JSONObject(result);
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                        try {
                            //code 等于0 请求成功 返回model层处理 数据  否则返回提示 请求成功
                            if (json.getInt("code") == 0) {
                                if (!"".equals(json.getString("data"))) {
                         */
                                    // data 有数据
                                    try {
                                        onRequestSuccess(result, requestCode);
                                        mResult.onRequestSuccess(requestCode);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                        /**
                                } else {
                                    // data 为空
                                }

                            } else {
                                try {
                                    mResult.onRequestFailed(requestCode, json.getString("message"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                         */
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mResult.onRequestFailed(requestCode, error.getMessage());
            }
        });
        request.setTag(Config.GET_TAG);
        /**
         * 将请求加入队列
         */
        BaseApplication.getHttpQueues().add(request);
    }

    /**
     * POST String
     *
     * @param url         请求地址URL
     * @param jsonParams  请求体
     * @param requestCode 区分请求
     */
    public void postStringRequest(String url, String jsonParams, final int requestCode) {

        LogUtil.v("URL: postString "+ Config.IP+url);
        StringRequest request = new StringRequest(Request.Method.POST, Config.IP+url, new Response.Listener<String>() {
            @Override
            public void onResponse(String result) {
                LogUtil.v("---成功获取数据-postString---" + result);
                /**
                JSONObject json = null;
                try {
                    json = new JSONObject(result);
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
                try {

                    //result 等于0 请求成功 返回model层处理 数据 否则返回提示 请求成功
                    if (json.getInt("result") == 0) {
                        if (json.getString("data") != null && !TextUtils.isEmpty(json.getString("data"))) {
                 */
                            // data 有数据
                            try {
                                onRequestSuccess(result, requestCode);
                                mResult.onRequestSuccess(requestCode);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                /**
                        }
                    } else {
                        try {
                            String message = json.getString("message");
                            mResult.onRequestFailed(requestCode, message);
                            LogUtil.v("成功返回错误数据--->" + json.getString("message"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                 */
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                /**
                 * 请求失败 提示错误信息
                 */
                mResult.onRequestFailed(requestCode, error.getMessage());
            }
        });

        request.setTag(Config.POST_TAG);
        /**
         * 将请求加入队列
         */
        BaseApplication.getHttpQueues().add(request);
    }

    /**
     * POST JSON
     *
     * @param requestCode
     */
    public void postJsonRequest(String url, JSONObject jsonParams, final int requestCode) {
        LogUtil.v("URL: postJson "+ Config.IP+url);
        //判断是否有网络
        if (!HttpTool.isNetworkAvailable(BaseApplication.getInstance())) {
            mResult.onNoConnect();
            return;
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Config.IP+url, jsonParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject result) {
                LogUtil.v("---成功获取数据-postJson---" + result);
                /**
                try {

                    //result 等于0 请求成功 返回model层处理 数据 否则返回提示 请求成功
                    if (result.getInt("result") == 0) {
                        if (result.getString("data") != null && !TextUtils.isEmpty(result.getString("data"))) {
                 */
                            // data 有数据
                            try {
                                onRequestSuccess(result.toString(), requestCode);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                /**
                        }
                        mResult.onRequestSuccess(requestCode);
                    } else {
                        String message = result.getString("message");
                        mResult.onRequestFailed(requestCode, message);
                        LogUtil.v("成功返回错误数据--->" + result.getString("message"));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                 */
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mResult.onRequestFailed(requestCode, error.getMessage());
            }
        }) {
            /**
             * 请求协议头
             * @return
             */
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
                return headers;
            }
        };
        request.setTag(Config.POST_JSON_TAG);
        /**
         * 将请求加入队列
         */
        BaseApplication.getHttpQueues().add(request);
    }
}
