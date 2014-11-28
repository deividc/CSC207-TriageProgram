package com.nurses.triage;

import com.example.triage.R;
import com.example.triage.R.id;
import com.example.triage.R.layout;
import com.example.triage.R.menu;

import core.nurse.triage.DateTime;
import core.nurse.triage.Prescription;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import android.widget.TextView;

/**
 * A class with methods to deal with most of the features needed to
 * display screen of prescription.
 */
public class Screen_prescription extends Activity implements OnClickListener {

	/**
     * Instance variables of Screen_prescription.
     */
	private Button save;
	private EditText medication, instruction;
	private TextView datePrescription;
	
	private String year, month, day, hour, minute;
	private int y, m, d;
	
	public Button changeDate;
	
	/**
	 * On create, fix date and set up EditText for perscription
	 * information.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_prescription);
		
		getCurrentDate(); //Adjust the time according to the device
		setCurrentDate(); //Fix date on the screen
		
		medication = (EditText) findViewById(R.id.editTextPrescriptionMedication);
		instruction = (EditText) findViewById(R.id.EditTextPrescriptionInstruction);
		save = (Button) findViewById(R.id.buttonPrescriptionSave);
		changeDate = (Button) findViewById(R.id.buttonPrescriptionChangeDate);
		
		save.setOnClickListener(this);
		changeDate.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.screen_prescription, menu);
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

	/**
	 * On click, handle if date is changed, then set up alerts.
	 * 
	 * @param	view
	 * 
	 * @return	result
	 */
	@Override
	public void onClick(View v) {
		
		//Case button changeDate pressed
				if (v == changeDate) {
					DatePickerDialog dpd = new DatePickerDialog(Screen_prescription.this, 0, new DatePickerDialog.OnDateSetListener() {
						
						@Override
						public void onDateSet(DatePicker view, int year, int monthOfYear,
								int dayOfMonth) {
							Screen_prescription.this.y = year;
							Screen_prescription.this.m = monthOfYear + 1;
							Screen_prescription.this.d = dayOfMonth;
							
							Screen_prescription.this.year = Integer.toString(y);
							Screen_prescription.this.month = Integer.toString(m);
							Screen_prescription.this.day = Integer.toString(d);
							
					        Screen_prescription.this.setCurrentDate();
							
						}
					}, Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day));
			        dpd.show();
			        
			        
				}
		
		if (v == save) {
			if(medication.getText().toString().equals("") || instruction.getText().toString().equals("")) {
				/*
				 * Alert Dialog saying that user must fill out all field
				 */
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
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
			}
			else {
				Intent intent = new Intent(Screen_prescription.this, Screen_patient.class);
				Prescription p = new Prescription(medication.getText().toString(), instruction.getText().toString());
				p.setDatePrescription(datePrescription.getText().toString());
				intent.putExtra("New Prescription", p);
				
				setResult(Activity.RESULT_OK, intent);
				finish();
			}
			
		}
		
	}
	
	
	/**
	 * Get the date of the system through the class DateTime
	 * 
	 * @param		null
	 * @return		null
	 */
	public void getCurrentDate() {
		
		DateTime dt = new DateTime();
		
		hour = dt.getHour24();
		minute = dt.getMinute();
		year = dt.getYear();
		month = dt.getMonth();
		day = dt.getDay();
		
	}
	
	/**
	 * Show the date on the screen
	 * 
	 * @param		null
	 * @return		null
	 */
	public void setCurrentDate() {
		datePrescription = (TextView) findViewById(R.id.textViewPrescriptionDate);
		datePrescription.setText(month + "-" + day + "-" + year + "		" + hour + ":" + minute);
	}
}
