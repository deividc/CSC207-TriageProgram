package core.nurse.triage;

/**
 * This class is responsible for forming a list of patients.
 * 
 * @version 1.0.3
 * @date    2014-11-08
 */

import java.io.Serializable;
import java.util.ArrayList;

public class ListOfPatients implements Serializable
{
	// instance variables
    private ArrayList<Patient> listOfPatients;
    
    public ListOfPatients() {
        listOfPatients = new ArrayList<Patient>();
    }
    
    public void newPatient(Patient p){
        this.listOfPatients.add(p);
    }
    
    public ArrayList<Patient> entireList(){
        return listOfPatients;
    }
    
    public int size(){
        return listOfPatients.size();
    }
    
    public Patient get(int i){
        return listOfPatients.get(i);
    }
    
    public void clear(){
        listOfPatients.clear();
    }
    
    /**
     * Create a new patient object.
     * 
     * @param  p   the patient object
     * @return     null
     */
    public Patient viewPatientInfo(String healthCardNumber){
        Patient tmp = new Patient();
        for (int i = 0; i < listOfPatients.size(); i++){
            tmp = listOfPatients.get(i);
            if (healthCardNumber.equals(tmp.getHealthCardNumber())){
                return tmp;
            } else {
            	tmp = new Patient();
            }
        }
        return tmp;
    }
    
    /**
     * Return the entire list of patients.
     * 
     * @param	null
     * @return	listOfPatients
     */
    public ArrayList<Patient> getListOfPatients() {
		return listOfPatients;
	}

	/**
     * Setter List of Patients.
     * 
     * @param listOfPatients
     * @return void
     */
    public void setListOfPatients(ArrayList<Patient> listOfPatients) {
    	this.listOfPatients = listOfPatients;
    }
}
