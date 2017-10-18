package com.mgcoin.ar_department.lbs_redpacket.util;

import android.util.Log;

import com.mgcoin.ar_department.lbs_redpacket.bean.BuyerBean;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import static android.R.attr.password;
import static android.content.ContentValues.TAG;

public class PostToServer {
	/**
	 * 
	 * @param username
	 * @param password
	 * @return null---->error text--->success
	 */
	public static String loginByGet(String username, String password) {
		// 提交数据到服务器
		// 拼装路径

		try {
            String path="http://192.168.23.1:8080/lbsbonustext/logintest.action";
			/*String path = "http://10.10.5.31:8080/web/LoginServlet?username="
					+ URLEncoder.encode(username, "UTF-8") + "&password="
					+ password;*/
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			int code = conn.getResponseCode();
			if (code == 200) {
				// 请求成功
				InputStream is = conn.getInputStream();
				String text = StreamTools.readInputStream(is);
				return text;

			} else {
				return null;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param username
	 * @return null---->error text--->success
	 */
	public static String loginByPost(String buyer) {
		// 提交数据到服务器
		// 拼装路径
		//Log.e(TAG, "上传的信息="+username+"与:"+password);

		try {
			String path = "http://192.168.23.1:8080/lbsbonustext/logintest.action";
			//String path = "http://10.10.5.31:8080/web/LoginServlet";
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("POST");
			// 准备数据
			String data = "username=" + URLEncoder.encode(buyer, "UTF-8");
					//+ "&password=" + password;
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", data.length() + "");

			// post实际上是浏览器把数据写给了服务器
			conn.setDoOutput(true);//UrlConnection允许向外传数据
			OutputStream os = conn.getOutputStream();
			os.write(data.getBytes());
			int code = conn.getResponseCode();
			if (code == 200) {
				// 请求成功
				InputStream is = conn.getInputStream();
				String text = StreamTools.readInputStream(is);
				return text;

			} else {
				return null;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
