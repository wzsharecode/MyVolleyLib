package cn.lib.base;

import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Base64;

import cn.lib.core.Config;
import cn.util.HttpTool;
import cn.util.LogUtil;
import cn.util.interfac.INetResult;

import com.google.gson.Gson;

/**
 * 
 * 上传工具类
 * 
 * @author spring sky
 */
public abstract class IBaseUploadRequest {

	/**
	 * 解析 Json 数据的工具类
	 */
	protected Gson mGson;
	/**
	 * 响应请求的接口类
	 */
	protected INetResult mResult;
	/**
	 * 构造方法
	 * 
	 * @param iNetResult  
	 *            接口
	 */
	public IBaseUploadRequest(INetResult iNetResult) {
		this.mResult = iNetResult;
		this.mGson = new Gson();
	}

	/**
	 * 请求成功
	 * 
	 * @param result
	 *            返回的结果数据
	 * @param requestCode
	 *            区分请求
	 * @throws IOException
	 *            抛异常
	 */
	public abstract void onRequestSuccess(String result, int requestCode) throws IOException;

	private static final int TIME_OUT = 40 * 1000; // 超时时间
	private static final String CHARSET = "utf-8"; // 设置编码


	/**
	 * android上传文件到服务器
	 * 
	 * @param is
	 *            需要上传的文件
	 * @param RequestURL
	 *            请求的rul
	 * @return 返回响应的内容
	 */
	protected void uploadImg(Context context, InputStream is, String RequestURL, int requestCode) {
		// 判断网络状态
	    if (!HttpTool.isNetworkAvailable(context)) {
			  mResult.onNoConnect();
			  return;
	    }
		MyTask task = new MyTask(is, requestCode);
		task.execute(RequestURL);
	}

	/**
	 * 将从Message中获取的，表示图片的字符串解析为Bitmap对象
	 * 
	 * @param picStrInMsg
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Bitmap decodeImg(String picStrInMsg) {
		Bitmap bitmap = null;

		byte[] imgByte = null;
		InputStream input = null;
		try {
			imgByte = Base64.decode(picStrInMsg, Base64.DEFAULT);
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 8;
			input = new ByteArrayInputStream(imgByte);
			@SuppressWarnings("unchecked")
			SoftReference softRef = new SoftReference(BitmapFactory.decodeStream(input, null, options));
			bitmap = (Bitmap) softRef.get();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (imgByte != null) {
				imgByte = null;
			}
		}

		return bitmap;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Bitmap byteToBitmap(byte[] imgByte) {
		InputStream input = null;
		Bitmap bitmap = null;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 8;
		input = new ByteArrayInputStream(imgByte);
		SoftReference softRef = new SoftReference(BitmapFactory.decodeStream(
				input, null, options));
		bitmap = (Bitmap) softRef.get();
		if (imgByte != null) {
			imgByte = null;
		}

		try {
			if (input != null) {
				input.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	public interface UploadCallBack {
		public void execute(String response);
	}

	/**
	 * 处理返回的数据
	 * 
	 * @param result
	 *            返回的结果
	 * @param requestCode
	 *            区分请求
	 */
	protected void analysisData(String result, int requestCode) {
		LogUtil.w(requestCode + "-----基层请求数据成功-----\n" + result);
		JSONObject json = null;
		try {
			json = new JSONObject(result);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		try {
			/**
			 * code 等于0 请求成功 返回model层处理 数据 否则返回提示 请求成功
			 */
			if (json.getInt("result") == 0) {
				LogUtil.v(requestCode + "\nresult == 0");
				if (json.getString("data") != null && !"".equals(json.getString("data").toString())) {
					LogUtil.v(requestCode + "\nresult == 0   data 有数据");
					// data 有数据
					try {
						onRequestSuccess(json.getString("data").toString(), requestCode);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				mResult.onRequestSuccess(requestCode);
			} else {
				try {
					String message = json.getString("message");
					mResult.onRequestFailed(requestCode, message);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 异步提交图片
	 * 
	 */
	private class MyTask extends AsyncTask<String, Integer, String> {
		private InputStream imgData = null;
		private final int requestCode;

		public MyTask(InputStream imgData, int requestCode) {
			this.imgData = imgData;
			this.requestCode = requestCode;
		}

		// onPreExecute方法用于在执行后台任务前做一些UI操作
		@Override
		protected void onPreExecute() {
			LogUtil.v("onPreExecute() called");
		}

		// doInBackground方法内部执行后台任务,不可在此方法内修改UI
		@Override
		protected String doInBackground(String... params) {
			InputStream is = imgData;
			String result = null;
			String BOUNDARY = UUID.randomUUID().toString(); // 边界标识 随机生成
			String PREFIX = "--", LINE_END = "\r\n";
			String CONTENT_TYPE = "multipart/form-data"; // 内容类型

			try {
				URL url = new URL(Config.IP + params[0]);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setReadTimeout(TIME_OUT);
				conn.setConnectTimeout(TIME_OUT);
				conn.setDoInput(true); // 允许输入流
				conn.setDoOutput(true); // 允许输出流
				conn.setUseCaches(false); // 不允许使用缓存
				conn.setRequestMethod("POST"); // 请求方式
				conn.setRequestProperty("Charset", CHARSET); // 设置编码
				conn.setRequestProperty("connection", "keep-alive");
				conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);
				if (is != null) {
					LogUtil.v(Config.IP +  params[0] + "\n生成成功－－－－－－－" + is.toString());
					/**
					 * 当文件不为空，把文件包装并且上传
					 */
					DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
					byte[] bytes = new byte[1024];
					int len = 0;
					while ((len = is.read(bytes)) != -1) {
						dos.write(bytes, 0, len);
					}
					is.close();
					dos.write(LINE_END.getBytes());
					byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
					dos.write(end_data);
					dos.flush();
				}
				/**
				 * 获取响应码 200=成功 当响应成功，获取响应的流
				 */
				int res = conn.getResponseCode();
				LogUtil.v("response code:" + res);
				if (res == 200) {
					
					LogUtil.v("request success");
					InputStream input = conn.getInputStream();
					StringBuffer sb1 = new StringBuffer();
					int ss;
					while ((ss = input.read()) != -1) {
						sb1.append((char) ss);
					}
					result = sb1.toString();
					LogUtil.v("result : " + result);

				}else {
					mResult.onNoConnect();
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return result;
		}

		// onPostExecute方法用于在执行完后台任务后更新UI,显示结果
		@Override
		protected void onPostExecute(String result) {
			LogUtil.i(result + "上传完....");
			if (result == null || TextUtils.isEmpty(result)) {
				return;
			}
			// 判断成功返回的数据
			analysisData(result, requestCode);
		}
	}
}