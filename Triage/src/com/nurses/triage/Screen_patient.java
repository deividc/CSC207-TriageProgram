package com.nurses.triage;

import java.util.ArrayList;

import com.example.triage.R;

import core.nurse.triage.Condition;
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

public class Screen_patient extends Activity implements OnClickListener{

	
	private Button newCondition, patientHistory, buttonSave;
	private DatePicker birthdayDate;
	private EditText healthCardNumber, patientName;
	
	private static Patient patientFound;
	
	private AlertDialog.Builder alertDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_patient);
		
		Intent intent = getIntent();
		patientFound = (Patient) intent.getSerializableExtra("PatientFound");
		
		healthCardNumber = (EditText) findViewById(R.id.editTextPatientHealthCardNumber);
		patientName = (EditText) findViewById(R.id.editTextPatientName);
		birthdayDate = (DatePicker) findViewById(R.id.datePatientPickerBirthdate);
		healthCardNumber.setTextColor(getResources().getColor(R.color.text_black));
		patientName.setTextColor(getResources().getColor(R.color.text_black));
		
		healthCardNumber.setText(patientFound.getHealthCardNumber());
		patientName.setText(patientFound.getName());
		
		birthdayDate = (DatePicker) findViewById(R.id.datePatientPickerBirthdate);
		String[] birthday = patientFound.getBirthdate().split("-");
		birthdayDate.updateDate(Integer.parseInt(birthday[0]), Integer.parseInt(birthday[1]) - 1, Integer.parseInt(birthday[2]));
		
		newCondition = (Button) findViewById(R.id.buttonPatientNewCondition);
		newCondition.setOnClickListener(this);
		
		patientHistory  = (Button) findViewById(R.id.buttonPatientHistory);
		patientHistory.setOnClickListener(this);
		
		buttonSave = (Button) findViewById(R.id.buttonPatientSave);
		buttonSave.setOnClickListener(this);
		
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
				Screen_patient.this.finish();
				
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
		getMenuInflater().inflate(R.menu.screen_patient, menu);
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
		 * Button new Condition pressed
		 */
		if (v == newCondition) {
			startActivityForResult(new Intent(Screen_patient.this, Screen_condition.class), 1); //
		}
		
		/*
		 * Button patient history pressed
		 */
		if (v == patientHistory) {
			Intent intent = new Intent(Screen_patient.this, Screen_list_conditions.class);
			intent.putExtra("Health Card Number", patientFound.getHealthCardNumber());
			intent.putExtra("Name", patientFound.getName());
			intent.putExtra("List Of Conditions", patientFound.getListOfCondition());
			startActivity(intent);
		}
		
		/*
		 * Button save pressed
		 */
		if (v == buttonSave) {
			Intent intent = new Intent(Screen_patient.this, MainActivity.class);
			com.nurses.triage.MainActivity.nurse.setArrayConditionPatient(patientFound.getHealthCardNumber(), patientFound.getListOfCondition());
			finish();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		switch (requestCode) {
			case 1:
				if (resultCode == RESULT_OK) {
					Condition c = (Condition) data.getSerializableExtra("New Condition");
					patientFound.newCondition(c);
					break;
				}
		}
		
	}
	
}
