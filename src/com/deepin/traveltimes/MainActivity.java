package com.deepin.traveltimes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		handler.postDelayed(runnable, 500);// ÿ0.5��ִ��һ��runnable.
	}

	Handler handler = new Handler();
	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			// Ҫ��������
			handler.postDelayed(this, 500);
			Intent intent = new Intent(MainActivity.this, TimesLine.class);
			startActivity(intent);
			handler.removeCallbacks(runnable);
			MainActivity.this.finish();

		}
	};

}
