package com.nurses.triage;

import com.example.triage.R;

import core.nurse.triage.Condition;
import core.nurse.triage.DateTime;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class Screen_condition extends Activity implements OnClickListener {
	
	private TextView timeNow;
	private AlertDialog.Builder alertDialog;
	private Button changeDate, save;
	private EditText symptoms, temperature, bloodPressureSystolic, bloodPressureDiastolic, heartRate;
	private CheckBox seenByDoctor;
	
	private int year, month, day, hour, minute;
	private long time;
	private boolean patientSeenByDoctor;
	private String patientSymptoms, patientTemperature, patientBloodPressureSystolic, patientBloodPressureDiastolic, patientHeartRate, patientArrivalDate, minuteAsString, hourAsString;
	Condition c;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_condition);
		
		getCurrentDate(); //Adjust the time according to the device
		setCurrentDate();
		
		symptoms = (EditText) findViewById(R.id.editTextConditionSymptoms);
		temperature = (EditText) findViewById(R.id.editTextConditionTemperature);
		bloodPressureSystolic = (EditText) findViewById(R.id.editTextConditionSystolic);
		bloodPressureDiastolic = (EditText) findViewById(R.id.editTextConditionDyastolic);
		heartRate = (EditText) findViewById(R.id.editTextConditionHeartRate);
		timeNow = (TextView) findViewById(R.id.textViewConditionTimeNow);
		seenByDoctor = (CheckBox) findViewById(R.id.checkBoxConditionSeenByDoctor);
		
		changeDate = (Button) findViewById(R.id.buttonConditionChangeTime);
		changeDate.setOnClickListener(this);
		
		save = (Button) findViewById(R.id.buttonConditionSave);
		save.setOnClickListener(this);
		
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
				Screen_condition.this.finish();
				
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
		getMenuInflater().inflate(R.menu.screen_condition, menu);
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
	
	/*
	 * Get the date of the system through the class DateTime
	 * 
	 * @param		null
	 * @return		null
	 */
	public void getCurrentDate() {
		
		DateTime dt = new DateTime();
		
		hour = Integer.parseInt(dt.getHour24());
		hourAsString = dt.getHour24();
		minute = Integer.parseInt(dt.getMinute());
		minuteAsString = dt.getMinute();
		year = Integer.parseInt(dt.getYear());
		month = Integer.parseInt(dt.getMonth());
		day = Integer.parseInt(dt.getDay());
		
	}
	
	/*
	 * Show the date on the screen
	 * 
	 * @param		null
	 * @return		null
	 */
	public void setCurrentDate() {
		TextView timeNow = (TextView) findViewById(R.id.textViewConditionTimeNow);
		timeNow.setText(month + "-" + day + "-" + year + "		" + hourAsString + ":" + minuteAsString);
	}
	
	@Override
	public void onClick(View v) {
		
		//Case button changeDate pressed
		if (v == changeDate) {
			DatePickerDialog dpd = new DatePickerDialog(Screen_condition.this, new DatePickerDialog.OnDateSetListener() {
				
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear,
						int dayOfMonth) {
					Screen_condition.this.year = year;
					Screen_condition.this.month = monthOfYear + 1;
					Screen_condition.this.day = dayOfMonth;
					Screen_condition.this.setCurrentDate();
				}
			}, year, month, day);
	        dpd.show();
		}
		
		/*
		 * Case button save pressed
		 */
		if (v == save) {
			patientSymptoms = symptoms.getText().toString();
			patientTemperature = temperature.getText().toString();
			patientBloodPressureSystolic = bloodPressureSystolic.getText().toString();
			patientBloodPressureDiastolic = bloodPressureDiastolic.getText().toString();
			patientHeartRate = heartRate.getText().toString();
			patientArrivalDate = timeNow.getText().toString();
			patientSeenByDoctor = seenByDoctor.isChecked();

			if (!patientSeenByDoctor) { //option Seen By Doctor not checked
				if (patientSymptoms.equalsIgnoreCase("")
						|| patientTemperature.equalsIgnoreCase("")
						|| patientBloodPressureSystolic.equalsIgnoreCase("")
						|| patientBloodPressureDiastolic.equalsIgnoreCase("")
						|| patientHeartRate.equalsIgnoreCase("")) {
					
					/*
					 * Alert Dialog saying that user must fill out all field
					 */
					alertDialog = new AlertDialog.Builder(this);
					alertDialog.setTitle("Alert");
					alertDialog.setMessage(R.string.message_error_blank_field);
					alertDialog.setIcon(R.drawable.main_icon_app_xxhdpi);
					alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//No action needed because the user cannot jump the steps
							
						}
					});
					alertDialog.show();
					
				} else {
					// Create a new condition with the information provided by use and return the condition back to the patient
					c = new Condition(
							patientSymptoms,
							Float.parseFloat(patientTemperature),
							Integer.parseInt(patientBloodPressureDiastolic),
							Integer.parseInt(patientBloodPressureSystolic),
							Integer.parseInt(patientHeartRate),
							patientArrivalDate,
							patientSeenByDoctor,
							time
							);
					
					//Adding new condition to intent to be returned back to the previous activity
					Intent intent = new Intent(Screen_condition.this, Screen_patient.class);
					intent.putExtra("New Condition", c);
					setResult(Activity.RESULT_OK, intent);
					
					finish(); //Finish this activity and return result for Patient
				}
			} else { // Option Seen By Doctor checker -> nurse does not collect information of the patient
				c = new Condition(patientArrivalDate, true, time);
				//Adding new condition to intent to be returned back to the previous activity
				Intent intent = new Intent(Screen_condition.this, Screen_patient.class);
				intent.putExtra("New Condition", c);
				setResult(Activity.RESULT_OK, intent);
				finish();
			}
		}
		
	}
}
