package cn.util.interfac;

/**
 * Activity 访问网络接口 MVC模式
 *
 */
public interface INetResult {

    /**
     * 访问网络成功后更新UI
     * 请求成功
     * @param requestCode    区分请求 网络请求顺序号,第一个请求,NetRequestOrderNum = 0,
     *                       处理第一条请求的结果.如果等于1,表示处理此界面的第二条请求
     */
    void onRequestSuccess(int requestCode);

    /**
     * 请求失败
     * @param num
     * @param errorMessage 错误提示信息
     */
    void onRequestFailed(int num, String errorMessage);

    /**
     * 无网络连接
     */
    void onNoConnect();
}
