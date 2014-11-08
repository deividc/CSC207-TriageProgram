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
    
    public Patient viewPatientInfor(String healthCardNumber){
        Patient tmp = new Patient();
        for (int i = 0; i < listOfPatients.size(); i++){
            tmp = listOfPatients.get(i);
            if (healthCardNumber.equals(tmp.getHealthCardNumber())){
                return tmp;
            }
        }
        return tmp;
    }
    
    public void savePatientsOnFile(){
        InputOutputController ioc = new InputOutputController(listOfPatients);
        ioc.savePatients("patient_records.txt");
    }
    
    public void saveConditionsOnFile(){
        InputOutputController ioc = new InputOutputController(listOfPatients);
        ioc.saveConditions("condition_records.txt");
    }
    
    public void readPatientsFromFile(){
        InputOutputController ioc = new InputOutputController(listOfPatients);
        ioc.patientsFromFile("patient_records.txt");
    }
    
    public void readConditionsFromFile(){
        InputOutputController ioc = new InputOutputController(listOfPatients);
        ioc.conditionsFromFile("condition_records.txt");
    }
    
    public ArrayList listOfPatients(){
        return listOfPatients.entireList();
    }
}
