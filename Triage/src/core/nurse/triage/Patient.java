package core.nurse.triage;
/*
 /*
 * @author  Deivid Cavalcante da Silva
 * @version 1.0.5
 * @date    2014-11-08
 */

import java.io.Serializable;
import java.util.ArrayList;

public class Patient implements Serializable
{
    private String healthCardNumber;
    private String name;
    private String birthdate;
    private ArrayList listOfCondition;
    
    static class Skill implements Serializable {
    	private String healthCardNumber;
        private String name;
        private String birthdate;
        private ArrayList listOfCondition;
    }
    
    public Patient(){
        this.healthCardNumber = "";
        this.name = "";
        this.birthdate = "";
        listOfCondition = new ArrayList();
    }
    
    /*
     * Create a new Patient
     * 
     * @param       patient's health card number
     * @param       patient's name
     * @param       patient's birthdate
     */
    public Patient(String healthCardNumber, String name, String birthdate){
        this.healthCardNumber = healthCardNumber;
        this.name = name;
        this.birthdate = birthdate;
        listOfCondition = new ArrayList();
    }
    
    public void newCondition(Condition cond){
        listOfCondition.add(cond);
    }
    
    public String getHealthCardNumber(){
        return this.healthCardNumber;
    }
    
    public void setHealthCardNumber(String healthCardNumber){
        this.healthCardNumber = healthCardNumber;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setBirthdate(String birthdate){
        this.birthdate = birthdate;
    }
    
    public String getBirthdate(){
        return this.birthdate;
    }
    
    public int urgency(){
        int urgency = 0;
        return urgency;
    }
    
    public String toString(){
        String str = healthCardNumber + "," + name + "," + birthdate + "\n";
        return str;
    }
    
    public String conditionsToString(){
        StringBuilder tmp = new StringBuilder();
        for(int i = 0; i < listOfCondition.size(); i++){
            tmp.append(healthCardNumber + ";" + listOfCondition.get(i).toString() + '\n');
        }
        return tmp.toString();
    }
    
    /*
     * Get the list of Condition of the specific patient
     * 
     * @return      list of conditions
     */
    public ArrayList<Condition> getListOfCondition() {
        return listOfCondition;
    }
    
    /*
     * 
     */
    public void setListOfConditions(ArrayList listOfCondition) {
    	this.listOfCondition = listOfCondition;
    }
}