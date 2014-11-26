package com.nurses.triage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.example.triage.R;

import core.nurse.triage.Nurse;
import core.nurse.triage.Patient;

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


public class MainActivity extends Activity implements OnClickListener{

	private static final int TYPE_CLASS_NUMBER = 2;

	public static Nurse nurse = new Nurse();
	
	private AlertDialog.Builder alertDialogSearchPatient;
	
	private Button buttonNewPatient, buttonSearchPatient, buttonListOfPatients, buttonLoadData;
	private EditText editTextSearchHealthCardNumber;
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        
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
        String username = intent.getStringExtra("Username");
        String userType = intent.getStringExtra("UserType");
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
    		alertDialogSearchPatient.setIcon(R.drawable.magnifying_glass);
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
			startActivity(new Intent(MainActivity.this, Screen_list_patients.class));
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
		StringBuilder dataOnFile = new StringBuilder();
		InputStream inputStream = getResources().openRawResource(R.raw.patient_records);
	        try {
	            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
	            ;
	            String line;
	            while ((line = reader.readLine()) != null) {
	            	dataOnFile.append(line);
	            	dataOnFile.append('\n');
	            }
	            reader.close();

	        }
	        catch(IOException e) {
	        	Log.e("Error", "file not found");
	        }
	        
	        //Log.i("Deivid testando", dataOnFile.toString());
	        nurse.readPatientsFromFile(dataOnFile.toString());
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
