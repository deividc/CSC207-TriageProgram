package com.example.triage;

public class InputOutputController
{
    private ListOfPatients listOfPatients;
    private DataRecorder dataRecorder;
    
    public InputOutputController(ListOfPatients list){
        this.listOfPatients = list;
        dataRecorder = new DataRecorder();
    }
    
    public void saveData(String filename){
        StringBuilder tmp = new StringBuilder();
        Patient patient = new Patient();
        for (int i = 0; i < listOfPatients.size(); i++){
            patient = listOfPatients.get(i);
            tmp.append(patient.toString());
        }
        dataRecorder.fileRecorder(filename, tmp.toString());
    }
}