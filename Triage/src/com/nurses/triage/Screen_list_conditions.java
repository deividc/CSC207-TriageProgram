package com.nurses.triage;

import java.util.ArrayList;

import com.example.triage.R;
import com.example.triage.R.id;
import com.example.triage.R.layout;
import com.example.triage.R.menu;

import core.nurse.triage.Condition;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Screen_list_conditions extends Activity {

	private TextView healthCardNumber, patientName;
	private ListView listViewConditions;
	
	private ArrayList<Condition> listOfConditions;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_list_conditions);
		
		Intent intent = getIntent();
		
		healthCardNumber = (TextView) findViewById(R.id.textViewListConditionsHealthCardNumber);
		patientName = (TextView) findViewById(R.id.textViewListConditionsPatientName);
		
		healthCardNumber.setText("Health Card Number: " + intent.getCharSequenceExtra("Health Card Number"));
		patientName.setText("Name: " + intent.getCharSequenceExtra("Name"));
		
		listOfConditions = (ArrayList<Condition>) intent.getSerializableExtra("List Of Conditions");
		
		showConditionsList();
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
		
		ArrayList<String> dateConditions = new ArrayList<String>();
		
		for (int i = 0; i < listOfConditions.size(); i++) {
			dateConditions.add(listOfConditions.get(i).getArrivalDate());
		}
		
		Log.i("Deivid testando 2", dateConditions.toString());
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dateConditions);
		
		listViewConditions = (ListView) findViewById(R.id.listViewListConditions);
		listViewConditions.setAdapter(adapter);
	}
}
