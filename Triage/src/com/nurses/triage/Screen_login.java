package com.nurses.triage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.example.triage.R;
import com.example.triage.R.id;
import com.example.triage.R.layout;
import com.example.triage.R.menu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * A class with methods to deal with most of the features needed to
 * display the screen of the login.
 */
public class Screen_login extends Activity  implements OnClickListener{

	/**
     * Instance variables of Screen_login.
     */
	private static ArrayList<String[]> usersList = new ArrayList<String[]>();
	
	private static String username, password, userType;
	private boolean userFound;
	
	private EditText typedUsername, typedPassword;
	private Button buttonLogin;
	
	/**
	 * On create, load login data.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		loadData(); //load users login data
		
		setContentView(R.layout.activity_screen_login);
		
		typedUsername = (EditText) findViewById(R.id.editTextLoginUsername);
		typedPassword = (EditText) findViewById(R.id.editTextLoginPassword);
		buttonLogin = (Button) findViewById(R.id.buttonLogin);
		
		buttonLogin.setOnClickListener(this);
		
	}
	
	/**
	 * On click, check if user exists, then login user.
	 */
	@Override
	public void onClick(View v) {
    	/*
    	 * Button search patient pressed
    	 */
		verifyLoginInfo(typedUsername.getText().toString(), typedPassword.getText().toString());
		
    	if (v == buttonLogin) {
    		
    		if (userFound) {
    			typedUsername.setText(""); //delete the typedUsername to prevent stilling data
    			typedPassword.setText(""); //delete the typedPassword to prevent stilling data
    			userFound = false;
    			
    			Intent intent = new Intent(Screen_login.this, MainActivity.class);
    			intent.putExtra("Username", username);
    			intent.putExtra("UserType", userType);
    			startActivity(intent);
    			
    		} else {
    			Builder alertDialog = new AlertDialog.Builder(this);
    			alertDialog.setTitle(R.string.warning);
    			alertDialog.setIcon(R.drawable.alert_icon);
    			alertDialog.setMessage(R.string.message_login_error);
    			alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
    				
    				@Override
    				public void onClick(DialogInterface dialog, int which) {
    					dialog.cancel();
    					
    				}
    			});
    			alertDialog.show();
    		}
    	}
	}
	
	/**
     * Verify the login info against the txt file.
     *
     * @param	username, password
     *
     * @return	null
     */
	public void verifyLoginInfo (String un, String pw) {
		if(usersList != null) {
			for (int i = 0; i < usersList.size(); i++) {
				String [] test = usersList.get(i);
				if (test[0].equals(un) && test[1].equals(pw)) {
					this.username = test[0];
					this.password = test[1];
					this.userType = test[2];
					userFound = true;
				}
				else {
					Log.e("Error", "User not found"); // to delete
				}
			}
		}
		else {
			Log.e("Error", "Null"); // to delete
		}
	}

	/**
	 * Load data from files
	 */
	public void loadData() {
		InputStream inputStream = getResources().openRawResource(R.raw.passwords);
	        try {
	            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
	            String line = new String();
	            String[] patientInfo;
	            while ((line = reader.readLine()) != null) {
	            	patientInfo = line.split(",");
	            	usersList.add(patientInfo);
	            }
	            reader.close();
	        }
	        catch(IOException e) {
	        	Log.e("Error", "file not found");
	        }
	}
}
