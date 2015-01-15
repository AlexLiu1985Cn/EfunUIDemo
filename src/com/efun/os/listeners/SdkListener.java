package com.efun.os.listeners;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.efun.core.task.EfunCommandCallBack;
import com.efun.core.task.EfunCommandExecute;
import com.efun.core.task.command.abstracts.EfunCommand;
import com.efun.core.tools.EfunLocalUtil;
import com.efun.core.tools.EfunResourceUtil;
import com.efun.os.control.EfunSdkManager;
import com.efun.os.util.Constant;
import com.efun.os.util.EfunHelper;
import com.efun.platform.login.comm.bean.LoginParameters;
import com.efun.platform.login.comm.execute.EfunThirdPlatLoginOrRegCmd2;
import com.efun.platform.login.comm.utils.EfunLoginHelper.ReturnCode;

public class SdkListener extends EfunSdkManager {

	private String advertisersName;
	private String partnerName;
	private String language;
	private String paramLanguage;

	public SdkListener() {
		Log.d("alex", SdkListener.class + ": instantiated!");
	}

	@Override
	public void excuteRequest(Context context, Request request) {

		// paramLanguage = Controls.instance().getAssignLanguage();
		paramLanguage = "zh_CH";
		language = paramLanguage.toLowerCase();

		switch (request.getRequestType()) {
		case EfunSdkManager.Request.REQUEST_TYPE_LOGIN_MAC:
			Log.d("alex", SdkListener.class + ": login mac");
			loginWithMac(context);
			break;
		}
	}

	private void loginWithMac(final Context context) {
		efunThirdPlatformLoginOrReg(context,
				EfunLocalUtil.getLocalMacAddress(context), advertisersName,
				partnerName, Constant.PLAT_FORM, Constant.Platform.MAC);
	}

	private void efunThirdPlatformLoginOrReg(final Context mContext,
			String thirdPlateId, String advertisersName, String partnerName,
			final String platForm, final String thirdPlate) {
		// plat_userName = EfunHelper.getUserName(mContext, thirdPlateId);
		EfunThirdPlatLoginOrRegCmd2 efunCommon = new EfunThirdPlatLoginOrRegCmd2(
				mContext, EfunHelper.getUserName(mContext, thirdPlateId),
				advertisersName, partnerName, platForm, thirdPlate, null);
		String msg = "";
		// if (!TextUtils.isEmpty(language)) {
		// msg = EfunResourceUtil.findStringByName(mContext, language + "_"
		// + "progress_msg");
		// } else {
		msg = EfunResourceUtil.findStringByName(mContext, "efun_sdk_progress_msg");
		// }
		efunCommon.setCommandMsg(msg);
		efunCommon.setLanguage(paramLanguage);
		// efunCommon.setPreferredUrl(preferredUrl);
		// efunCommon.setSparedUrl(sparedUrl);
		efunCommon.setCallback(new EfunCommandCallBack() {
			@Override
			public void cmdCallBack(EfunCommand command) {
				try {
					String request = command.getResponse();
					LoginParameters loginParameters = EfunHelper
							.StrToLoginParameters(request);
					// SdkListener.plat_timeStamp =
					// loginParameters.getTimestamp()
					// + "";
					// SdkListener.plat_sign = loginParameters.getSign();
					if (null != loginParameters
							&& null != loginParameters.getCode()
							&& !"".equals(loginParameters.getCode())) {
						String resultCode = loginParameters.getCode();
						String resultMessage = loginParameters.getMessage();
						if (ReturnCode.RETURN_SUCCESS.equals(resultCode)
								|| ReturnCode.ALREADY_EXIST.equals(resultCode)) {
							// SdkListener.plat_timeStamp = loginParameters
							// .getTimestamp() + "";
							// SdkListener.plat_sign =
							// loginParameters.getSign();
							getEfunLoginListener().onFinishLoginProcess(loginParameters);
							Log.d("alex", SdkListener.class + ": callback");
							sdkNotifyChange(resultCode);
						} else {
							Toast.makeText(mContext, resultMessage,
									Toast.LENGTH_SHORT).show();
						}
					} else {
						showToastSysError(mContext);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		EfunCommandExecute.getInstance().asynExecute((Activity) mContext,
				efunCommon);
	}

	private void showToastSysError(Context mContext) {
		String msg = "";
		if (!TextUtils.isEmpty(language)) {
			msg = EfunResourceUtil.findStringByName(mContext, language + "_"
					+ "efun_sdk_notify_internet_time_out");
		} else {
			msg = EfunResourceUtil.findStringByName(mContext,
					"efun_sdk_notify_internet_time_out");
		}
		Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
	}

}
