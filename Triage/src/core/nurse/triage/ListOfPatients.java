package core.nurse.triage;

/*
 * @author  Deivid Cavalcante da Silva
 * @version 1.0.4
 * @date    2014-11-26
 */

import java.io.Serializable;
import java.util.ArrayList;

public class ListOfPatients implements Serializable
{
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
     * Return a patient that matches the health card number
     * 
     * @param		health card number
     * @return		patient
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
     * Getter List of Patients
     * 
     * @return		listOfPatients
     */
    public ArrayList<Patient> getListOfPatients() {
		return listOfPatients;
	}

	/**
     * Setter List of Patients
     * 
     * @param listOfPatients
     */
    public void setListOfPatients(ArrayList<Patient> listOfPatients) {
    	this.listOfPatients = listOfPatients;
    }
}
