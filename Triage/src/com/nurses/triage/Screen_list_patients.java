package com.nurses.triage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import com.example.triage.R;
import com.example.triage.R.id;
import com.example.triage.R.layout;
import com.example.triage.R.menu;

import core.nurse.triage.Condition;
import core.nurse.triage.ListOfPatients;
import core.nurse.triage.Patient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

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
		
		RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupListPatientOrdering); 
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				if(arg1 == R.id.radioButtonListPatientsOrderByUrgency) {
					urgencySetUpListView();
				}
				else {
					arrivalTimeSetUpListView();
				}
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
	
	public void urgencySetUpListView () {
		
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
		
		final ArrayList<String> patients = new ArrayList<String>();
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
				String[] infoPatient = patients.get(arg2).toString().split(",");
				AlertDialog.Builder ald = new AlertDialog.Builder(Screen_list_patients.this);
				ald.setTitle(infoPatient[1] + '\n' + "Urgency: " + com.nurses.triage.MainActivity.nurse.viewPatientInfo(infoPatient[0]).urgency());
				
				ald.setMessage(com.nurses.triage.MainActivity.nurse.conditionsOfPatient(infoPatient[0]).get(com.nurses.triage.MainActivity.nurse.conditionsOfPatient(infoPatient[0]).size() - 1).toString2());
				ald.show();
				
			}
		});
	}
	
public void arrivalTimeSetUpListView() {
		
		Collections.sort(listPatients, new Comparator<Patient>() {
			@Override
			public int compare(Patient p1, Patient p2)
			{
				int comparison = -1;
				if (p1.getListOfCondition().size() > 0 && p2.getListOfCondition().size() > 0) {
					if (p1.getListOfCondition().get(p1.getListOfCondition().size() - 1).getSeenByDoctor() != true && p2.getListOfCondition().get(p2.getListOfCondition().size() - 1).getSeenByDoctor() != true) {
						SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
						try {
							Date infoArrivalTimeP1 = format.parse(p1.getListOfCondition().get(p1.getListOfCondition().size() - 1).getArrivalDate());
							Date infoArrivalTimeP2 = format.parse(p2.getListOfCondition().get(p2.getListOfCondition().size() - 1).getArrivalDate());
							comparison = infoArrivalTimeP1.compareTo(infoArrivalTimeP2);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
				
				return  comparison;
			}
		});
		
		ListView lv = (ListView) findViewById(R.id.listViewListPatientsList);
		
		final ArrayList<String> patients = new ArrayList<String>();
		for (int i = listPatients.size() - 1; i >= 0 ; i--) {
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
				String[] infoPatient = patients.get(arg2).toString().split(",");
				AlertDialog.Builder ald = new AlertDialog.Builder(Screen_list_patients.this);
				ald.setTitle(infoPatient[1] + '\n' + "Urgency: " + com.nurses.triage.MainActivity.nurse.viewPatientInfo(infoPatient[0]).urgency());
				
				ald.setMessage(com.nurses.triage.MainActivity.nurse.conditionsOfPatient(infoPatient[0]).get(com.nurses.triage.MainActivity.nurse.conditionsOfPatient(infoPatient[0]).size() - 1).toString2());
				ald.show();
				
			}
		});
	}
}
