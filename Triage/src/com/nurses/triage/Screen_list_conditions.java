package com.nurses.triage;

import java.util.ArrayList;

import com.example.triage.R;
import core.nurse.triage.Condition;
import core.nurse.triage.Prescription;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A class with methods to deal with most displaying the list of
 * conditions screen which also includes the prescriptions display
 * and all its required methods including the values and the buttons.
 */
public class Screen_list_conditions extends Activity {
	
	/**
     * Instance variables of Screen_list_conditions.
     */
	private TextView healthCardNumber, patientName;
	private ListView listViewConditions;
	
	private ArrayList<Condition> listOfConditions;
	private ArrayList<Prescription> listOfPrescriptions;
	
	ArrayList<String> dateConditions, datePrescriptions;
	
	int idAdapterCondition, idAdapterPrescription;
	
	/**
     * On create, get patient info.
     */    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_list_conditions);
		
		Intent intent = getIntent();
		
		healthCardNumber = (TextView) findViewById(R.id.textViewListConditionsHealthCardNumber);
		patientName = (TextView) findViewById(R.id.textViewListConditionsPatientName);
		
		healthCardNumber.setText(intent.getCharSequenceExtra("Health Card Number"));
		patientName.setText(intent.getCharSequenceExtra("Name"));
		
		listOfConditions = (ArrayList<Condition>) intent.getSerializableExtra("List Of Conditions");
		listOfPrescriptions = (ArrayList<Prescription>) intent.getSerializableExtra("List Of Prescriptions");
		
		showConditionsList();
		showPrescriptionsList();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.screen_list_conditions, menu);
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
	
	public void showConditionsList() {
		
		dateConditions = new ArrayList<String>();
		
		for (int i = listOfConditions.size() - 1; i >= 0 ; i--) {
			dateConditions.add(listOfConditions.get(i).getArrivalDate());
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dateConditions);
		listViewConditions = (ListView) findViewById(R.id.listViewListConditions);
		listViewConditions.setAdapter(adapter);
		listViewConditions.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				AlertDialog.Builder showCondition = new AlertDialog.Builder(Screen_list_conditions.this);
				showCondition.setIcon(R.drawable.history_magnifying);
				showCondition.setTitle(dateConditions.get(arg2).toString());
				showCondition.setMessage(listOfConditions.get(listOfConditions.size() - arg2 - 1).toString2());
				showCondition.setNegativeButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				showCondition.show();
				
			}
		});
	}

/**
 * Show the prescriptions list.
 *
 * @param	null
 *
 * @return	prescriptions list
 */    
public void showPrescriptionsList() {
		
		datePrescriptions = new ArrayList<String>();
		
		for (int i = listOfPrescriptions.size() - 1; i >= 0 ; i--) {
			datePrescriptions.add(listOfPrescriptions.get(i).getDatePrescription());
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datePrescriptions);
		
		listViewConditions = (ListView) findViewById(R.id.listViewListPrescriptions);
		listViewConditions.setAdapter(adapter);
		listViewConditions.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				AlertDialog.Builder showCondition = new AlertDialog.Builder(Screen_list_conditions.this);
				showCondition.setIcon(R.drawable.history_magnifying);
				showCondition.setTitle(datePrescriptions.get(arg2).toString());
				showCondition.setMessage(listOfPrescriptions.get(listOfPrescriptions.size() - arg2 - 1).toString());
				showCondition.setNegativeButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				showCondition.show();
				
			}
		});
	}
}
