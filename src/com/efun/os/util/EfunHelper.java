package com.efun.os.util;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.efun.core.tools.EfunJSONUtil;
import com.efun.platform.login.comm.bean.LoginParameters;

public class EfunHelper {

	private static final String TAG = EfunHelper.class.getCanonicalName();

	public static LoginParameters StrToLoginParameters(String request,
			Object... objects) {
		LoginParameters loginParameters = null;
		String currentPage = null;

		if (null == request || "".equals(request))
			return loginParameters;
		if (objects.length > 0) {
			currentPage = (String) objects[0];
		}
		if (null == currentPage) {
			try {
				JSONObject jsonObject = new JSONObject(request);
				if (EfunJSONUtil.efunVerificationRequest(jsonObject)) {
					loginParameters = new LoginParameters();
					String code = jsonObject.optString("code", "");
					String message = jsonObject.optString("message", "");
					String sign = jsonObject.optString("sign", "");
					String timestamp = jsonObject.optString("timestamp", "");
					String userid = jsonObject.optString("userid", "");

					loginParameters.setCode("".equals(code) ? null : code);
					loginParameters.setMessage("".equals(message) ? null
							: message);
					loginParameters.setSign("".equals(sign) ? null : sign);
					loginParameters.setTimestamp("".equals(timestamp) ? null
							: Long.parseLong(timestamp));
					loginParameters.setUserId("".equals(userid) ? null : Long
							.parseLong(userid));

					Log.i(TAG, "code: " + code + "    message:" + message);
				}
			} catch (JSONException e) {
				Log.i(TAG, "jsonObject exception");
				e.printStackTrace();
			}
		}
		return loginParameters;
	}

	public static String getUserName(Context context, String username) {
//		String prefixName = AppUiConfiguration.getPrefixName(context);
//		if (prefixName == null || StringUtils.isEmpty(prefixName)) {
//			return username;
//		}
//		return prefixName + username;
		return username;
	}

}
