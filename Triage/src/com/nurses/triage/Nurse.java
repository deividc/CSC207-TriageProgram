package com.nurses.triage;
import java.util.ArrayList;

public class Nurse
{
    ListOfPatients listOfPatients;
    
    public Nurse(){
        listOfPatients = new ListOfPatients();
    }
    
    public boolean newPatient(String healthCardNumber, String name, String birthdate)
    {
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
    int bloodPressureSystolic, int heartRate, String arrivalDate, boolean seenByDoctor, long time)
    {
        Patient tmp = new Patient();
        for(int i = 0; i < listOfPatients.size(); i++)
        {
            tmp = listOfPatients.get(i);
            if (healthCardNumber.equals(tmp.getHealthCardNumber())){
                Condition cond = new Condition(symptoms, temperature, bloodPressureDiastolic, bloodPressureSystolic, heartRate, arrivalDate, seenByDoctor, time);
                tmp.newCondition(cond);
                return true; //New condition added
            }
        }
        
        return false;
    }
    
    public Patient viewPatientInfor(String healthCardNumber)
    {
        Patient tmp = new Patient();
        for (int i = 0; i < listOfPatients.size(); i++){
            tmp = listOfPatients.get(i);
            if (healthCardNumber.equals(tmp.getHealthCardNumber())){
                return tmp;
            }
        }
        return tmp;
    }
    
    public void saveDataOnFile()
    {
        InputOutputController ioc = new InputOutputController(listOfPatients);
        ioc.saveData("patient_records.txt");
    }
    
    public void readPatientsFromFile()
    {
        InputOutputController ioc = new InputOutputController(listOfPatients);
        ioc.patientsFromFile("patient_records.txt");
    }
    
    public ArrayList listOfPatients()
    {
        return listOfPatients.entireList();
    }
}