package core.nurse.triage;
/*
 * @author  Deivid Cavalcante da Silva
 * @version 1.0.2
 * @date    2014-11-08
 */

import java.io.Serializable;
import java.util.ArrayList;

public class Nurse implements Serializable
{
    /**
	 * serialVersionUID automatic
	 */
	private static final long serialVersionUID = 1L;
	
	ListOfPatients listOfPatients;
    
    public Nurse() {
        listOfPatients = new ListOfPatients();
    }
    
    /*
     * Add a new patient
     * 
     * @param		health card number
     * @param		name
     * @param		birthdate
     * @return		boolean true for added, false for no added
     */
    public boolean newPatient(String healthCardNumber, String name, String birthdate) {
        Patient tmp = new Patient();
        for(int i = 0; i < listOfPatients.size(); i++){
            tmp = listOfPatients.get(i);
            if (healthCardNumber.equals(tmp.getHealthCardNumber())){
                return false; //the patient is already in the list
            }
        }
        
        Patient newPatient = new Patient(healthCardNumber, name, birthdate);
        listOfPatients.newPatient(newPatient);
        
        return true;
    }
    
    /*
     * Add new patient p
     * 
     * @param		patient p
     * @return		boolean true if it was added
     * 						false if it was not added
     */
    public boolean newPatient(Patient p) {
    	Patient tmp = new Patient();
    	for(int i = 0; i < listOfPatients.size(); i++){
            tmp = listOfPatients.get(i);
            if (p.getHealthCardNumber().equals(tmp.getHealthCardNumber())){
                return false; //the patient is already in the list
            }
        }
        
        listOfPatients.newPatient(p);
        
        return true;
    }
    
    /*
     * Create a new condition of a patient that matches one health card number
     * 
     * @param		health card number
     * @param		symptoms
     * @param		temperature
     * @param		blood pressure diastolic
     * @param		blood pressure systolic
     * @param		heart rate
     * @param		arrival date
    public boolean checkNewCondition(String healthCardNumber, String symptoms, float temperature, int bloodPressureDiastolic,
    int bloodPressureSystolic, int heartRate, String arrivalDate){
        Patient tmp = new Patient();
        for(int i = 0; i < listOfPatients.size(); i++)
        {
            tmp = listOfPatients.get(i);
            if (healthCardNumber.equals(tmp.getHealthCardNumber())){
                Condition cond = new Condition(symptoms, temperature, bloodPressureDiastolic, bloodPressureSystolic, heartRate, arrivalDate);
                tmp.newCondition(cond);
                return true; //New condition added
            }
        }
        
        return false;
    }
    */
    public Patient viewPatientInfo(String healthCardNumber){
        return listOfPatients.viewPatientInfo(healthCardNumber);
    }
    
    /*
     * Record the list of patient
     * 
     * @param		null
     * @return		data of patients to write on a file
     */
    public String savePatientsOnFile(){
        InputOutputController ioc = new InputOutputController(listOfPatients);
         return ioc.savePatients();
    }
    
    /*
     * Record the list of condition
     * 
     * @param		null
     * @return		data of condition to write on a file
     */
    public String saveConditionsOnFile(){
        InputOutputController ioc = new InputOutputController(listOfPatients);
        return ioc.saveConditions();
    }
    
    /*
     * Read the list of patient of records and update the information in the memory.
     * 
     * @param		data of patients
     * @return		null
     */
    public void readPatientsFromFile(String data){
        InputOutputController ioc = new InputOutputController(listOfPatients);
        ioc.patientsFromFile(data);
    }
    
    /*
     * 
     */
    public void readConditionsFromFile(String data){
        InputOutputController ioc = new InputOutputController(listOfPatients);
        ioc.conditionsFromFile(data);
    }
    
    /*
     * return a list of patients
     * 
     * @param		null
     * @return      list of patients
     */
    public ArrayList listOfPatients(){
        return listOfPatients.entireList();
    }
    
    /*
     * Search for the list of conditions through the health card number
     * 
     * @param		health card number
     * @return		arraylist of conditions
     */
    public ArrayList conditionsOfPatient(String healthCardNumber) {
        ArrayList tmp = new ArrayList();
        
        for(int i = 0; i < listOfPatients.size(); i++) {
            if(healthCardNumber.equals(listOfPatients.get(i).getHealthCardNumber())) {
                tmp = listOfPatients.get(i).getListOfCondition();
            }
        }
        
        return tmp;
    }
    
    /*
     * Set array of conditions for one specific patient that matches the health card number
     * 
     * @param		health card number
     * @param		array conditions
     * @return		null
     */
    public void setArrayConditionPatient(String healthCardNumber, ArrayList conditions) {
    	for(int i = 0; i < listOfPatients.size(); i++) {
            if(healthCardNumber.equals(listOfPatients.get(i).getHealthCardNumber())) {
                listOfPatients.get(i).setListOfConditions(conditions);
            }
        }
    }
}
