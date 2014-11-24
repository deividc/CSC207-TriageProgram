package com.nurses.triage;

import java.util.ArrayList;

import com.example.triage.R;
import core.nurse.triage.Condition;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Screen_list_conditions extends Activity implements OnItemClickListener  {

	private TextView healthCardNumber, patientName;
	private ListView listViewConditions;
	
	private ArrayList<Condition> listOfConditions;
	
	ArrayList<String> dateConditions;
	
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
		
		dateConditions = new ArrayList<String>();
		
		for (int i = 0; i < listOfConditions.size(); i++) {
			dateConditions.add(listOfConditions.get(i).getArrivalDate());
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dateConditions);
		
		listViewConditions = (ListView) findViewById(R.id.listViewListConditions);
		listViewConditions.setAdapter(adapter);
		listViewConditions.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		AlertDialog.Builder showCondition = new AlertDialog.Builder(this);
		showCondition.setTitle(dateConditions.get(arg2).toString());
		showCondition.setMessage(listOfConditions.get(arg2).toString2());
		showCondition.show();
		
	}
}
