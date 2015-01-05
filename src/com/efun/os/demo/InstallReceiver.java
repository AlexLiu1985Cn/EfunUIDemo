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
		// ���չ㲥���豸���°�װ��һ��Ӧ�ó�������Զ������°�װӦ�ó���
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
		// ���չ㲥���豸��ɾ����һ��Ӧ�ó������
		if (paramIntent.getAction().equals(
				"android.intent.action.PACKAGE_REMOVED")) {
			Log.d("alex", "********************************");
		}
	}
}
