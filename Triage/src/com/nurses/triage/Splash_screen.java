package com.nurses.triage;

import com.example.triage.R;
import com.example.triage.R.id;
import com.example.triage.R.layout;
import com.example.triage.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

/**
 * A class with methods to deal with most of the features needed to
 * display the splash screen.
 */
public class Splash_screen extends Activity {

	/**
	 * Instance variables for Splash_screen.
	 */
	private static final int SPLASH_PAUSE = 2000; 
	
	/**
	 * Check if splash screen was successful.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		Thread splashScreen = new Thread() {
			public void run() {
				try {
					sleep(SPLASH_PAUSE);
					Intent intent = new Intent(Splash_screen.this, Screen_login.class);
					startActivity(intent);
					finish();
					
				} catch (InterruptedException e) {
					e.printStackTrace();
					Log.e("error", e.toString());
				}
			}
		};
		splashScreen.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
