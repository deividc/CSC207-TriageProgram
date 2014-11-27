package com.nurses.triage;

import java.util.ArrayList;

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
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Screen_list_patients extends Activity {

	private ArrayList<Patient> listPatients;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_list_patients);
		
		Intent intent = getIntent();
		ListOfPatients lop = (ListOfPatients) intent.getSerializableExtra("List of Patients");
		listPatients = lop.getListOfPatients();
		
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
		ListView lv = (ListView) findViewById(R.id.listViewListPatientsList);
		
		ArrayList<String> patients = new ArrayList<String>();
		for (int i = 0; i < listPatients.size(); i++) {
			String tmp = listPatients.get(i).toString2();
			patients.add(tmp);
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, patients);
		lv.setAdapter(adapter);
	}
}
