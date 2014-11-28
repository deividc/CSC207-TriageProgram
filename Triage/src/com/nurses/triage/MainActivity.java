package com.nurses.triage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.example.triage.R;

import core.nurse.triage.Nurse;
import core.nurse.triage.Patient;
import core.nurse.triage.Condition;
import core.nurse.triage.Prescription;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
import java.util.ArrayList;
import java.lang.Integer;
import java.lang.Boolean;
import java.lang.Long;



public class MainActivity extends Activity implements OnClickListener{

	private static final int TYPE_CLASS_NUMBER = 2;

	public static Nurse nurse = new Nurse();
	
	private AlertDialog.Builder alertDialogSearchPatient;
	
	private Button buttonNewPatient, buttonSearchPatient, buttonListOfPatients, buttonLoadData;
	private EditText editTextSearchHealthCardNumber;
	
	private String userType;
	private String username;

    private DataHandler patientData;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        patientData = new DataHandler(this);
        patientData.getDB();
        
        buttonSearchPatient = (Button) findViewById(R.id.buttonMainSearchPatient);
        buttonListOfPatients = (Button) findViewById(R.id.buttonMainListOfPatients);
        buttonNewPatient = (Button) findViewById(R.id.buttonMainNewPatient);
        buttonLoadData = (Button) findViewById(R.id.buttonMainLoadData);
        
        buttonSearchPatient.setOnClickListener(this);
        buttonListOfPatients.setOnClickListener(this);
        buttonNewPatient.setOnClickListener(this);
        buttonLoadData.setOnClickListener(this);
        
        /*
         * set up the main screen according to the user data
         */
        Intent intent = this.getIntent();
        username = intent.getStringExtra("Username");
        userType = intent.getStringExtra("UserType");
        ImageView userImage = (ImageView) findViewById(R.id.imageViewMainUserImage);
        TextView userName = (TextView) findViewById(R.id.textViewMainUsername);
        userName.setText(username);
        if (userType.equals("physician")) {
        	userImage.setImageResource(R.drawable.doctor_logo);
        	buttonNewPatient.setVisibility(View.GONE);
        	
        }
        else {
        	userImage.setImageResource(R.drawable.nurse_logo);
        }
    }

    @Override
	public void onBackPressed() {
		logoutAccount();
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
        
        if (id == R.id.itemMenuAbout) {
        	startActivity(new Intent(MainActivity.this, Screen_about.class));
        	return true;
        }
        
        return super.onOptionsItemSelected(item);
    }
    
    @Override
	public void onClick(View v) {
    	/*
    	 * Button search patient pressed
    	 */
    	if (v == buttonSearchPatient) {
    		alertDialogSearchPatient = new AlertDialog.Builder(this);
    		alertDialogSearchPatient.setIcon(R.drawable.search_icon);
    		alertDialogSearchPatient.setTitle(R.string.search_patient);
    		alertDialogSearchPatient.setMessage(R.string.type_health_card_number);
    		
    		editTextSearchHealthCardNumber = new EditText(this);
    		editTextSearchHealthCardNumber.setRawInputType(TYPE_CLASS_NUMBER);
    		alertDialogSearchPatient.setView(editTextSearchHealthCardNumber);
    		
    		alertDialogSearchPatient.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
    			public void onClick(DialogInterface dialog, int whichButton) {
    			  String numberHCN = editTextSearchHealthCardNumber.getText().toString();
    			  
    			  if (!numberHCN.equals("")) {
        			  Intent intent = new Intent(MainActivity.this, Screen_patient.class);
        			  
        			  Patient patientFound = new Patient();
        			  patientFound = nurse.viewPatientInfo(numberHCN);
        			  
        			  if (patientFound.getHealthCardNumber().equals(numberHCN)) {
        				  intent.putExtra("PatientFound", patientFound);
        				  intent.putExtra("UserType", userType);
            			  
            			  startActivity(intent); //Result changes in patient conditions
            			  
        			  } else { //Patient not Found
        				  Toast.makeText(getApplicationContext(), R.string.error_patient_not_found, Toast.LENGTH_LONG).show();
        			  }

    			  	} else { //Blank number for search not acceptable
    			  		//Implement later
    			  	}
    			  }
    			});
    		
    		alertDialogSearchPatient.show();
    	}
    	
    	/*
    	 * Button list of patients pressed
    	 */
		if (v == buttonListOfPatients) {
			Intent intent = new Intent(MainActivity.this, Screen_list_patients.class);
			intent.putExtra("List of Patients", nurse.getListOfPatients());
			startActivity(intent);
		}
		
		/*
		 * Button new patient pressed
		 */
		if (v == buttonNewPatient) {
			
			startActivityForResult(new Intent(MainActivity.this, Screen_new_patient.class), 1); //Information of the new patient received
			
		}
		
		/*
		 * Button load data pressed
		 */
		if (v == buttonLoadData) {
			
			Builder alertDialog = new AlertDialog.Builder(this);
			alertDialog.setTitle(R.string.warning);
			alertDialog.setIcon(R.drawable.alert_icon);
			alertDialog.setMessage(R.string.message_data_loss);
			alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					loadData();
					
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
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		switch(requestCode) {
			case 1:
				if (resultCode == RESULT_OK) {
					Patient pat1 = (Patient) data.getSerializableExtra("New Patient");
					nurse.newPatient(pat1);
					break;
				}	
		}
	}
	
	/*
	 * Load data from files
	 */
	public void loadData() {
        Cursor patientDb = patientData.getAllRecordsPatients();

        if(patientDb.getCount() == 0) {
            StringBuilder storedData = new StringBuilder();
            InputStream inputStream = getResources().openRawResource(R.raw.patient_records);
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        storedData.append(line);
                        storedData.append('\n');
                    }
                    reader.close();
                    System.out.println(storedData.toString());

                }
                catch(IOException e) {
                    Log.e("Error", "file not found");
                }
            nurse.readPatientsFromFile(storedData.toString());
                
                //Log.i("Deivid testando", storedData.toString());
        }
        else{
            patientDb.moveToFirst();
            ArrayList<Patient> patientList = new ArrayList<Patient>();
            for(int i = 0; i < patientDb.getCount(); i++){
                Patient p = new Patient(patientDb.getString(patientDb.getColumnIndex("healthNumber")),
                                patientDb.getString(patientDb.getColumnIndex("name")),
                                patientDb.getString(patientDb.getColumnIndex("birthdate")));
                patientList.add(p);
                patientDb.moveToNext();
            }
            nurse.setListOfPatients(patientList);
        }

        Cursor conditionDb = patientData.getAllRecordsConditions();
        conditionDb.moveToFirst();
        for(int k = 0; k < nurse.getListOfPatients().size(); k++){
            ArrayList<Condition> conditionsList = new ArrayList<Condition>();
            Patient q = nurse.getListOfPatients().get(k);
            // System.out.println(q.getHealthCardNumber() + " is the patient, currently looking for conditions...");
            for(int j = 0; j < conditionDb.getCount(); j++) {
                if(conditionDb.getString(conditionDb.getColumnIndex("healthNumber")).equals(q.getHealthCardNumber())) {
                // System.out.println("Found a condition attached to health number " + conditionDb.getString(conditionDb.getColumnIndex("healthNumber")));
                    Condition c = new Condition(
                               conditionDb.getString(conditionDb.getColumnIndex("symptoms")),
                               Float.parseFloat(conditionDb.getString(conditionDb.getColumnIndex("temperature"))),
                               Integer.parseInt(conditionDb.getString(conditionDb.getColumnIndex("diastolic"))),
                               Integer.parseInt(conditionDb.getString(conditionDb.getColumnIndex("systolic"))),
                               Integer.parseInt(conditionDb.getString(conditionDb.getColumnIndex("heartRate"))),
                               conditionDb.getString(conditionDb.getColumnIndex("arrival_date")),
                               Boolean.parseBoolean(conditionDb.getString(conditionDb.getColumnIndex("seen_by_doctor"))),
                               Long.parseLong(conditionDb.getString(conditionDb.getColumnIndex("time"))));
                    conditionsList.add(c);


                }
                conditionDb.moveToNext();
            }
            // System.out.println("for the health number " + q.getHealthCardNumber() + " we have " + conditionsList.size() + "conditions");
            // ArrayList<Condition> temp = new ArrayList<Condition>();
            // for(int indexCondition = 0; indexCondition < conditionsList.size(); indexCondition++){
            //     temp.add
            // }
            nurse.setArrayConditionPatient(q.getHealthCardNumber(), conditionsList);
            // System.out.println(q.getListOfCondition().size());
            // System.out.println(q.getListOfCondition().size());
            conditionDb.moveToFirst();
    	}

        Cursor prescriptionDb = patientData.getAllRecordsPrescriptions();
        System.out.println(prescriptionDb.getCount());
        prescriptionDb.moveToFirst();
        for(int k = 0; k < nurse.getListOfPatients().size(); k++){
            ArrayList<Prescription> prescriptionList = new ArrayList<Prescription>();
            Patient q = nurse.getListOfPatients().get(k);
            // System.out.println(q.getHealthCardNumber() + " is the patient, currently looking for conditions...");
            for(int j = 0; j < prescriptionDb.getCount(); j++) {

                if(prescriptionDb.getString(prescriptionDb.getColumnIndex("healthNumber")).equals(q.getHealthCardNumber())) {
                // System.out.println("Found a condition attached to health number " + prescriptionDb.getString(prescriptionDb.getColumnIndex("healthNumber")));
                    Prescription myPre = new Prescription(prescriptionDb.getString(prescriptionDb.getColumnIndex("medication")),
                                                         prescriptionDb.getString(prescriptionDb.getColumnIndex("instruction")));
                    myPre.setDatePrescription(prescriptionDb.getString(prescriptionDb.getColumnIndex("time")));

                    prescriptionList.add(myPre);

                }
                prescriptionDb.moveToNext();
            }
            // System.out.println("for the health number " + q.getHealthCardNumber() + " we have " + conditionsList.size() + "conditions");
            // ArrayList<Condition> temp = new ArrayList<Condition>();
            // for(int indexCondition = 0; indexCondition < conditionsList.size(); indexCondition++){
            //     temp.add
            // }
            System.out.println("Found " + prescriptionList.size() + " prescriptions");
            nurse.setArrayPrescriptionPatient(q.getHealthCardNumber(), prescriptionList);
            System.out.println(q.getListOfPrescription().size());
            // System.out.println(q.getListOfCondition().size());
            // System.out.println(q.getListOfCondition().size());
            prescriptionDb.moveToFirst();
        }
    }
	
	public void loadDataExternalStorage() {
	}
	
	/* Checks if external storage is available for read and write */
	public boolean isExternalStorageWritable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	        return true;
	    }
	    return false;
	}

	/* Checks if external storage is available to at least read */
	public boolean isExternalStorageReadable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state) ||
	        Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
	        return true;
	    }
	    return false;
	}
	
	public void logoutAccount() {
        // Save new info into the database
        ArrayList<Patient> listPatients = nurse.listOfPatients();
        for(int i=0; i < listPatients.size(); i++) {
            if(!(patientData.patientExists(listPatients.get(i).getHealthCardNumber()))) {
                patientData.insertPatient(listPatients.get(i).getHealthCardNumber(),
                                          listPatients.get(i).getName(),
                                          listPatients.get(i).getBirthdate());
            }
        }

        for(int j=0; j < listPatients.size(); j++) {
            for(int k=0; k < listPatients.get(j).getListOfCondition().size(); k++) {
                if(!(patientData.conditionExists(listPatients.get(j).getHealthCardNumber(),
                    listPatients.get(j).getListOfCondition().get(k).getTime()))) {
                    Condition c = listPatients.get(j).getListOfCondition().get(k);
                    patientData.insertCondition(listPatients.get(j).getHealthCardNumber(),
                                                c.getSymptoms(), c.getSystolic(), c.getDiastolic(),
                                                c.getTemperature(), c.getHeartRate(), c.getTime(),
                                                c.getArrivalDate(), c.getSeenByDoctor());
                }
            }
        }

        for(int l=0; l < listPatients.size(); l++) {
            Patient prescripPatient = listPatients.get(l);
            for(int prescripIndex=0; prescripIndex < prescripPatient.getListOfPrescription().size(); prescripIndex++) {
                System.out.println("one is here");
                if(!(patientData.prescriptionExists(prescripPatient.getHealthCardNumber(),
                     prescripPatient.getListOfPrescription().get(prescripIndex).getMedication(),
                     prescripPatient.getListOfPrescription().get(prescripIndex).getDatePrescription()))) {

                Prescription pre = prescripPatient.getListOfPrescription().get(prescripIndex);
                patientData.insertPrescription(prescripPatient.getHealthCardNumber(),
                                               pre.getMedication(),
                                               pre.getInstruction(),
                                               pre.getDatePrescription());
                }
            }
        }

		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setMessage(R.string.message_logout);
		alertDialog.setIcon(R.drawable.logoff_button);
		alertDialog.setTitle(R.string.logout);
		alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				MainActivity.this.finish();
				
			}
		});
		alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				
			}
		});
		alertDialog.show();
	}
}
