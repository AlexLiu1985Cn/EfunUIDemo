package com.efun.os.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class InstallReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context paramContext, Intent paramIntent) {
		if (paramIntent.getAction().equals(
				"android.intent.action.BOOT_COMPLETED")) {
			Intent newIntent = new Intent(paramContext, MainActivity.class);
			newIntent.setAction("android.intent.action.MAIN");
			newIntent.addCategory("android.intent.category.LAUNCHER");
			newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			paramContext.startActivity(newIntent);
		}
		// 接收广播：设备上新安装了一个应用程序包后自动启动新安装应用程序。
		if (paramIntent.getAction().equals(
				"android.intent.action.PACKAGE_ADDED")) {
			String packageName = paramIntent.getDataString().substring(8);
			Log.d("alex", "---------------" + packageName);
//			Intent newIntent = new Intent();
//			newIntent.setClassName(packageName, packageName + ".MainActivity");
//			newIntent.setAction("android.intent.action.MAIN");
//			newIntent.addCategory("android.intent.category.LAUNCHER");
//			newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			paramContext.startActivity(newIntent);
		}
		// 接收广播：设备上删除了一个应用程序包。
		if (paramIntent.getAction().equals(
				"android.intent.action.PACKAGE_REMOVED")) {
			Log.d("alex", "********************************");
		}
	}
}
