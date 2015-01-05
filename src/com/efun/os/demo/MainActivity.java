package com.efun.os.demo;


import com.efun.os.demo.R;
import com.efun.os.ui.PageContainer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends FragmentActivity{

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.main);
		Button bt = (Button) findViewById(R.id.start);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent it = new Intent(MainActivity.this, PageContainer.class);
				startActivity(it);
			}
		});
	}

}
