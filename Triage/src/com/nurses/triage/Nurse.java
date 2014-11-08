package com.nurses.triage;
/*
 * @author  Deivid Cavalcante da Silva
 * @version 1.0.2
 * @date    2014-11-08
 */

import java.util.ArrayList;

public class Nurse
{
    ListOfPatients listOfPatients;
    
    public Nurse(){
        listOfPatients = new ListOfPatients();
    }
    
    public boolean newPatient(String healthCardNumber, String name, String birthdate){
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
    
    public Patient viewPatientInfo(String healthCardNumber){
        Patient tmp = new Patient();
        for (int i = 0; i < listOfPatients.size(); i++){
            tmp = listOfPatients.get(i);
            if (healthCardNumber.equals(tmp.getHealthCardNumber())){
                return tmp;
            }
        }
        return tmp;
    }
    
    /*
     * Record the list of patient
     */
    public void savePatientsOnFile(){
        InputOutputController ioc = new InputOutputController(listOfPatients);
        ioc.savePatients("patient_records.txt");
    }
    
    /*
     * Record the list of condition
     */
    public void saveConditionsOnFile(){
        InputOutputController ioc = new InputOutputController(listOfPatients);
        ioc.saveConditions("condition_records.txt");
    }
    
    /*
     * Read the list of patient of records and update the information in the memory.
     */
    public void readPatientsFromFile(){
        InputOutputController ioc = new InputOutputController(listOfPatients);
        ioc.patientsFromFile("patient_records.txt");
    }
    
    public void readConditionsFromFile(){
        InputOutputController ioc = new InputOutputController(listOfPatients);
        ioc.conditionsFromFile("condition_records.txt");
    }
    
    /*
     * return a list of patients
     * 
     * @return      list of patients
     */
    public ArrayList listOfPatients(){
        return listOfPatients.entireList();
    }
    
    /*
     * Search for the list of conditions through the health card number
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
}
