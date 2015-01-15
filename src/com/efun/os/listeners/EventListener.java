package com.efun.os.listeners;

import android.content.Context;
import android.util.Log;

import com.efun.os.control.EfunLoginEventListener;

public class EventListener implements EfunLoginEventListener{
	
	public EventListener() {
		Log.d("alex", EventListener.class + ": instantiate!");
	}

	@Override
	public void loginEvent(Context paramContext, int paramInt) {
		
	}

	@Override
	public void registerEvent(Context paramContext, int paramInt) {
		
	}

}
