package com.nurses.triage;

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

public class Screen_login extends Activity  implements OnClickListener{

	private static ArrayList<ArrayList> usersList;
	
	private static String username, password, userType;
	private boolean userFound;
	
	private EditText typedUsername, typedPassword;
	private Button buttonLogin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_login);		
		
		typedUsername = (EditText) findViewById(R.id.editTextLoginUsername);
		typedPassword = (EditText) findViewById(R.id.editTextLoginPassword);
		buttonLogin = (Button) findViewById(R.id.buttonLogin);
		
		buttonLogin.setOnClickListener(this);
		
	}

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
	
	public void verifyLoginInfo (String un, String pw) {
		if(usersList != null) {
			for (int i = 0; i < usersList.size(); i++) {
				if (usersList.get(i).get(0).equals(un) && usersList.get(i).get(1).equals(pw)) {
					this.username = (String) usersList.get(i).get(0);
					this.password = (String) usersList.get(i).get(1);
					this.userType = (String) usersList.get(i).get(2);
					userFound = true;
				}
			}
		}
		else {
			Log.e("Error", "Null");
		}
	}

}
