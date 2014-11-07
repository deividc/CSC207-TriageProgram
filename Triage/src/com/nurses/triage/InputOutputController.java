package com.nurses.triage;

public class InputOutputController
{
    private ListOfPatients listOfPatients;
    private DataRecorder dataRecorder;
    
    public InputOutputController(ListOfPatients list)
    {
        this.listOfPatients = list;
        dataRecorder = new DataRecorder();
    }
    
    public void saveData(String filename)
    {
        StringBuilder tmp = new StringBuilder();
        Patient patient = new Patient();
        for (int i = 0; i < listOfPatients.size(); i++){
            patient = listOfPatients.get(i);
            tmp.append(patient.toString());
        }
        dataRecorder.fileRecorder(filename, tmp.toString());
    }
    
    public void patientsFromFile(String finename) //This method treats the string received from file and fill out the list with each patient's information
    {
        String data = new String();
        data = dataRecorder.fileReader(finename);
        
        StringBuilder healthCardNumber = new StringBuilder();
        StringBuilder name = new StringBuilder();
        StringBuilder birthdate = new StringBuilder();
        int controller = 0;
        for(int i = 0; i < data.length(); i++)
        {
            //System.out.println(i + " -> " + data.charAt(i));
            if (data.charAt(i) != '\n')
            {
                if (data.charAt(i) == ',')
                {
                    controller++;
                }
                else
                {
                    if(controller == 0)
                    {
                        healthCardNumber.append(data.charAt(i));
                    }
                
                    if (controller == 1)
                    {
                        name.append(data.charAt(i));
                    }
                
                    if(controller == 2)
                    {
                        birthdate.append(data.charAt(i));
                    }
                
                }
            }
            else
            {
                //System.out.println(healthCardNumber.toString() + " " + name.toString() + " " + birthdate.toString());
                Patient p = new Patient(healthCardNumber.toString(), name.toString(), birthdate.toString());
                listOfPatients.newPatient(p);
                
                healthCardNumber.delete(0, healthCardNumber.length());
                name.delete(0, name.length());
                birthdate.delete(0, birthdate.length());
                controller = 0;
            }
            
        }
    }
}