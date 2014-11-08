package com.nurses.triage;

/*
 * @author  Deivid Cavalcante da Silva
 * @version 1.0.3
 * @date    2014-11-08
 */

import java.util.ArrayList;

public class ListOfPatients
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
}
