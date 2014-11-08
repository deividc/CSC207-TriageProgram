package com.nurses.triage;
/*
 * @author  Deivid Cavalcante da Silva
 * @version 1.0.4
 * @date    2014-11-08
 */

public class InputOutputController
{
    private ListOfPatients listOfPatients;
    private DataRecorder dataRecorder;
    
    public InputOutputController(ListOfPatients list)
    {
        this.listOfPatients = list;
        dataRecorder = new DataRecorder();
    }
    
    /*
     * Save the List of Patients on a file
     */
    public void savePatients(String filename)
    {
        StringBuilder tmp = new StringBuilder();
        Patient patient = new Patient();
        for (int i = 0; i < listOfPatients.size(); i++){
            patient = listOfPatients.get(i);
            tmp.append(patient.toString());
        }
        dataRecorder.fileRecorder(filename, tmp.toString());
    }
    
    /*
     * Save the list of conditions of each patient on a file
     */
    public void saveConditions(String filename)
    {
        StringBuilder tmp = new StringBuilder();
        Patient patient = new Patient();
        for (int i = 0; i < listOfPatients.size(); i++){
            patient = listOfPatients.get(i);
            tmp.append(patient.conditionsToString());
        }
        dataRecorder.fileRecorder(filename, tmp.toString());
    }
    
    /*
     *  Recover the patients' information from records
     */
    public void patientsFromFile(String finename)
    {
        listOfPatients.clear(); // WARNING: clear all the previous patients list to prevent double records. Save previous changes before reloading data
        
        String data = new String();
        data = dataRecorder.fileReader(finename);
        
        StringBuilder healthCardNumber = new StringBuilder();
        StringBuilder name = new StringBuilder();
        StringBuilder birthdate = new StringBuilder();
        int controller = 0;
        for(int i = 0; i < data.length(); i++)
        {
            if (data.charAt(i) != '\n')
            {
                if (data.charAt(i) == ',')
                {
                    controller++;
                }
                else
                {
                    switch (controller)
                    {
                        case 0:
                            healthCardNumber.append(data.charAt(i));
                            break;
                        case 1:
                            name.append(data.charAt(i));
                            break;
                        case 2:
                            birthdate.append(data.charAt(i));
                            break;
                    }
                }
            }
            else
            {
                Patient p = new Patient(healthCardNumber.toString(), name.toString(), birthdate.toString());
                listOfPatients.newPatient(p);
                
                healthCardNumber.delete(0, healthCardNumber.length());
                name.delete(0, name.length());
                birthdate.delete(0, birthdate.length());
                controller = 0;
            }
            
        }
    }
    
    /*
     *  Recover history of conditions of each patient from records
     */
    
    public void conditionsFromFile(String filename)
    {
        String data = dataRecorder.fileReader(filename);
        
        StringBuilder healthCardNumber = new StringBuilder();
        StringBuilder symptoms = new StringBuilder();
        StringBuilder temperature = new StringBuilder();
        StringBuilder bloodPressureDiastolic = new StringBuilder();
        StringBuilder bloodPressureSystolic = new StringBuilder();
        StringBuilder heartRate = new StringBuilder();
        StringBuilder arrivalDate = new StringBuilder();
        StringBuilder seenByDoctor = new StringBuilder();
        StringBuilder time = new StringBuilder();
        int controller = 0;
        
        StringBuilder test = new StringBuilder();
        
        for(int i = 0; i < data.length(); i++)
        {
            if (data.charAt(i) != '\n')
            {
                if (data.charAt(i) == ';')
                {
                    controller++;
                }
                else
                {
                    switch (controller)
                    {
                        case 0:
                            healthCardNumber.append(data.charAt(i));
                            break;
                        case 1:
                            symptoms.append(data.charAt(i));
                            break;
                        case 2:
                            temperature.append(data.charAt(i));
                            break;
                        case 3:
                            bloodPressureDiastolic.append(data.charAt(i));
                            break;
                        case 4:
                            bloodPressureSystolic.append(data.charAt(i));
                            break;
                        case 5:
                            heartRate.append(data.charAt(i));
                            break;
                        case 6:
                            arrivalDate.append(data.charAt(i));
                            break;
                        case 7:
                            seenByDoctor.append(data.charAt(i));
                            break;
                        case 8:
                            time.append(data.charAt(i));
                            break;
                    }
                }
            }
            else {
                Condition c = new Condition(symptoms.toString(), Float.parseFloat(temperature.toString()),Integer.parseInt(bloodPressureDiastolic.toString()),
                Integer.parseInt(bloodPressureSystolic.toString()), Integer.parseInt(heartRate.toString()), arrivalDate.toString(), Boolean.parseBoolean(seenByDoctor.toString()),
                Long.parseLong(time.toString())); //Create a new condition with the information on records.

                Patient tmp = new Patient();
                for(int j = 0; j < listOfPatients.size(); j++) {
                    tmp = listOfPatients.get(j);
                    
                    
                    test.append(healthCardNumber.toString() + "         " + tmp.getHealthCardNumber() + " -> " + healthCardNumber.toString().equals(tmp.getHealthCardNumber()) + '\n'); //testing
                    
                    if(healthCardNumber.toString().equals(tmp.getHealthCardNumber())) {
                        tmp.newCondition(c);
                    }
                }
                
                healthCardNumber.delete(0, healthCardNumber.length());
                symptoms.delete(0, symptoms.length());
                temperature.delete(0, temperature.length());
                bloodPressureDiastolic.delete(0, bloodPressureDiastolic.length());
                bloodPressureSystolic.delete(0, bloodPressureSystolic.length());
                heartRate.delete(0, heartRate.length());
                arrivalDate.delete(0, arrivalDate.length());
                seenByDoctor.delete(0, seenByDoctor.length());
                time.delete(0, time.length());
                controller = 0;
            }
            
        }
        
        dataRecorder.fileRecorder("test.txt", test.toString());
    }
}