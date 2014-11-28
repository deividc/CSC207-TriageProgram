package core.nurse.triage;

/**
 * This class is responsible for handling instances of Patient calling the
 * recording of patient data and updating the personal data.
 * 
 * @version 1.0.2
 * @date    2014-11-08
 */
import java.io.Serializable;
import java.util.ArrayList;

public class Nurse implements Serializable
{
    /*
	 * serialVersionUID automatic
	 */
	private static final long serialVersionUID = 1L;
	
	ListOfPatients listOfPatients;
    
	/**
     * Constructor for objects of class Nurse
     */
    public Nurse() {
        listOfPatients = new ListOfPatients();
    }
    
    /**
     * Add a new patient.
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
    
    /**
     * Add new patient p.
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
    
    /**
     * Return patient's information of a patient who matches a health card number.
     * 
     * @param healthCardNumber
     * @return
     */
    public Patient viewPatientInfo(String healthCardNumber){
        return listOfPatients.viewPatientInfo(healthCardNumber);
    }
    
    /*
     * To depreciate when database is working fine
     * 
     */
    
    /**
     * Record the list of patient.
     * 
     * @param		null
     * @return		data of patients to write on a file
     */
    public String savePatientsOnFile(){
        InputOutputController ioc = new InputOutputController(listOfPatients);
         return ioc.savePatients();
    }
    
    /**
     * Record the list of condition.
     * 
     * @param		null
     * @return		data of condition to write on a file
     */
    public String saveConditionsOnFile(){
        InputOutputController ioc = new InputOutputController(listOfPatients);
        return ioc.saveConditions();
    }
    
    /**
     * Read the list of patient of records and update the information in the memory.
     * 
     * @param		data of patients
     * @return		null
     */
    public void readPatientsFromFile(String data){
        InputOutputController ioc = new InputOutputController(listOfPatients);
        ioc.patientsFromFile(data);
    }
    
    /**
     * Organize the condition of all patients after reading data from a file.
     * 
     * @param data
     * @return null
     */
    public void readConditionsFromFile(String data){
        InputOutputController ioc = new InputOutputController(listOfPatients);
        ioc.conditionsFromFile(data);
    }
    
    //********************************************************
    
    /**
     * Getter List of Patients.
     * 
     * @param	null
     * @return	ListOfPatients
     */
    public ListOfPatients getListOfPatients() {
    	return this.listOfPatients;
    }
    
    /**
     * Setter ArrayList of patients.
     * 
     * @param listOfPatients
     * @return	null
     */
    public void setListOfPatients(ArrayList<Patient> listOfPatients) {
    	this.listOfPatients.setListOfPatients(listOfPatients);
    }
    
    /**
     * return a list of patients.
     * 
     * @param		null
     * @return      list of patients
     */
    public ArrayList listOfPatients(){
        return listOfPatients.entireList();
    }
    
    /**
     * Search for the list of conditions through the health card number.
     * 
     * @param		health card number
     * @return		arraylist of conditions
     */
    public ArrayList<Condition> conditionsOfPatient(String healthCardNumber) {
        ArrayList<Condition> tmp = new ArrayList();
        
        for(int i = 0; i < listOfPatients.size(); i++) {
            if(healthCardNumber.equals(listOfPatients.get(i).getHealthCardNumber())) {
                tmp = listOfPatients.get(i).getListOfCondition();
            }
        }
        
        return tmp;
    }
    
    /**
     * Set array of conditions for one specific patient that matches the health card number.
     * 
     * @param		health card number
     * @param		array conditions
     * @return		null
     */
    public void setArrayConditionPatient(String healthCardNumber, ArrayList conditions) {
    	for(int i = 0; i < listOfPatients.size(); i++) {
            if(healthCardNumber.equals(listOfPatients.get(i).getHealthCardNumber())) {
                listOfPatients.get(i).setListOfCondition(conditions);
            }
        }
    }
    
    
    /**
     * Search for the list of prescription through the health card number.
     * 
     * @param		health card number
     * @return		arraylist of conditions
     */
    public ArrayList prescriptionOfPatient(String healthCardNumber) {
        ArrayList tmp = new ArrayList();
        
        for(int i = 0; i < listOfPatients.size(); i++) {
            if(healthCardNumber.equals(listOfPatients.get(i).getHealthCardNumber())) {
                tmp = listOfPatients.get(i).getListOfPrescription();
            }
        }
        
        return tmp;
    }
    
    /**
     * Set array of prescriptions for one specific patient that matches the health card number.
     * 
     * @param		health card number
     * @param		array conditions
     * @return		null
     */
    public void setArrayPrescriptionPatient(String healthCardNumber, ArrayList prescriptions) {
    	for(int i = 0; i < listOfPatients.size(); i++) {
            if(healthCardNumber.equals(listOfPatients.get(i).getHealthCardNumber())) {
                listOfPatients.get(i).setListOfPrescription(prescriptions);
            }
        }
    }
}
