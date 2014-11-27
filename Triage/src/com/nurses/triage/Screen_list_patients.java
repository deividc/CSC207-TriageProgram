package com.nurses.triage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.example.triage.R;
import com.example.triage.R.id;
import com.example.triage.R.layout;
import com.example.triage.R.menu;

import core.nurse.triage.ListOfPatients;
import core.nurse.triage.Patient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;

public class Screen_list_patients extends Activity {

	private ArrayList<Patient> listPatients;
	private RadioButton orderUrgency, orderTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_list_patients);
		
		Intent intent = getIntent();
		ListOfPatients lop = (ListOfPatients) intent.getSerializableExtra("List of Patients");
		listPatients = lop.getListOfPatients();
		
		orderUrgency = (RadioButton) findViewById(R.id.radioButtonListPatientsOrderByUrgency);
		orderUrgency.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				urgencySetUpListView();
				
			}
		});
		
		generalSetUpListView();
	}

	@Override
	public void onBackPressed() {
		Screen_list_patients.this.finish();
		super.onBackPressed();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.screen_list_patients, menu);
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
	
	public void generalSetUpListView () {
		ListView lv = (ListView) findViewById(R.id.listViewListPatientsList);
		
		ArrayList<String> patients = new ArrayList<String>();
		for (int i = 0; i < listPatients.size(); i++) {
			String tmp = listPatients.get(i).toString2();
			patients.add(tmp);
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, patients);
		lv.setAdapter(adapter);
	}
	
	public void urgencySetUpListView () { //To Implement
		
		Collections.sort(listPatients, new Comparator<Patient>() {
			@Override
			public int compare(Patient p1, Patient p2)
			{
				Integer p1Urgency = p1.urgency();
				Integer p2Urgency = p2.urgency();
				return  p1Urgency.compareTo(p2Urgency);
			}
		});
		
		ListView lv = (ListView) findViewById(R.id.listViewListPatientsList);
		
		ArrayList<String> patients = new ArrayList<String>();
		for (int i = listPatients.size() - 1; i >= 0; i--) {
			if (listPatients.get(i).getListOfCondition().size() > 0) {
				if (listPatients.get(i).getListOfCondition().get(listPatients.get(i).getListOfCondition().size() - 1).getSeenByDoctor() != true) {
					String tmp = listPatients.get(i).toString2();
					patients.add(tmp);
				}
			}
			
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, patients);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
