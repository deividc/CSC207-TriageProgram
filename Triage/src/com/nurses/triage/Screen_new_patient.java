package com.nurses.triage;

import com.example.triage.R;
import com.example.triage.R.id;
import com.example.triage.R.layout;
import com.example.triage.R.menu;

import core.nurse.triage.Patient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class Screen_new_patient extends Activity implements OnClickListener {

	private Button  saveNewPatientInfo;
	private DatePicker birthdayDate;
	private EditText healthCardNumber, patientName;
	
	private int year, month, day;
	private String patName, patHealthCardNumber, patBirthday;
	
	private AlertDialog.Builder alertDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_new_patient);
		
		saveNewPatientInfo = (Button) findViewById(R.id.buttonNewPatientSave);
		saveNewPatientInfo.setOnClickListener(this);
		
		healthCardNumber = (EditText) findViewById(R.id.editTextNewPatientHealthCardNumber);
		patientName = (EditText) findViewById(R.id.editTextNewPatientName);
		birthdayDate = (DatePicker) findViewById(R.id.datePickerNewPatientBirthdate);
		
		healthCardNumber.setTextColor(getResources().getColor(R.color.text_black));
		patientName.setTextColor(getResources().getColor(R.color.text_black));
		
	}

	@Override
	public void onBackPressed() {
		alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle(R.string.warning);
		alertDialog.setIcon(R.drawable.alert_icon);
		alertDialog.setMessage(R.string.message_data_loss);
		alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Screen_new_patient.this.finish();
				
			}
		});
		alertDialog.setCancelable(true);
		alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		alertDialog.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.screen_new_patient, menu);
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
	
	public void onClick(View v) {
		
		/*
		 * Button save new patient info pressed
		 */
		if (v == saveNewPatientInfo) {
			
			year = birthdayDate.getYear();
			month = birthdayDate.getMonth() + 1;
			day = birthdayDate.getDayOfMonth();
			
			patHealthCardNumber = new String();
			patHealthCardNumber = healthCardNumber.getText().toString();
			patName = new String();
			patName = patientName.getText().toString();
			patBirthday = year + "-" + month + "-" + day;
			
			if (!patHealthCardNumber.equals("") && !patName.equals("")) {
				Patient p = new Patient(patHealthCardNumber, patName, patBirthday); //Creating a new patient with the information collected from the user
				
				Intent intent = new Intent(Screen_new_patient.this, MainActivity.class);
				intent.putExtra("New Patient", p);
				setResult(Activity.RESULT_OK, intent);
				
				finish(); //Finish this activity and return the result for the Main Activity
			
			} else { //Blank information not permitted
				
				alertDialog = new AlertDialog.Builder(this);
				alertDialog.setTitle("Alert");
				alertDialog.setMessage(R.string.message_error_blank_field);
				alertDialog.setIcon(R.drawable.main_icon_app_xxhdpi);
				alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
						
					}
				});
				alertDialog.show();
			}
			
			
			
		}
	}
}
