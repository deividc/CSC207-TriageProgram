package core.nurse.triage;
/*
 /*
 * @author  Deivid Cavalcante da Silva
 * @version 1.0.6
 * @date    2014-11-08
 */

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Patient implements Serializable
{
    private String healthCardNumber;
    private String name;
    private String birthdate;
    private ArrayList<Condition> listOfCondition;
    private ArrayList<Prescription> listOfPrescription;
    
    public Patient(){
        this.healthCardNumber = "";
        this.name = "";
        this.birthdate = "";
        listOfCondition = new ArrayList();
    }
    
    /**
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
        listOfPrescription = new ArrayList();
    }
    
    /**
     * 
     */
    public void newCondition(Condition cond){
        listOfCondition.add(cond);
    }
    
    /**
     * 
     */
    public String getHealthCardNumber(){
        return this.healthCardNumber;
    }
    
    /**
     * 
     */
    public void setHealthCardNumber(String healthCardNumber){
        this.healthCardNumber = healthCardNumber;
    }
    
    /**
     * 
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * 
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * 
     */
    public void setBirthdate(String birthdate){
        this.birthdate = birthdate;
    }
    
    /**
     * 
     */
    public String getBirthdate(){
        return this.birthdate;
    }
    
    public int getAge() {
    	String[] bd = birthdate.split("-");

        Calendar birthday = Calendar.getInstance();
        birthday.set(Integer.parseInt(bd[0]), Integer.parseInt(bd[1]), Integer.parseInt(bd[2]));
        
        Calendar currentTime = Calendar.getInstance();
        currentTime.getTimeInMillis();
        
        int age = currentTime.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);

        if (currentTime.get(Calendar.MONTH) < birthday.get(Calendar.MONTH)) {
            if (currentTime.get(Calendar.DAY_OF_MONTH) < birthday.get(Calendar.DAY_OF_MONTH)) {
                age--;
            }
        }
        
        return age;
    }
    
    /**
     * Calculate the urgency of a patient based on his/her information
     * 
     * @return		urgency
     */
    public int urgency() {
    	int urgency = 0;
    	
        Condition lastCondition = new Condition("", true, 0);
        int i = 1;
        do {
            if (listOfCondition.size() > 0 ) {
                lastCondition = listOfCondition.get(listOfCondition.size() - i);
                i++;
            }
        	
        }
        while (lastCondition.getSeenByDoctor() == false && (listOfCondition.size() - i) > 1);
        
		if (lastCondition.getSeenByDoctor() != true) {
        	if(lastCondition.getTemperature() >= 39.0)
            	urgency += 1;
            if(lastCondition.getSystolic() >= 140 || lastCondition.getDiastolic() >= 90)
            	urgency += 1;
            if(lastCondition.getHeartRate() >= 100 || lastCondition.getHeartRate() <= 50)
            	urgency += 1;
            if(this.getAge() < 2)
            	urgency += 1;
        }
        return urgency;
    }
    
    /**
     * 
     */
    public String toString(){
        String str = healthCardNumber + "," + name + "," + birthdate + "\n";
        return str;
    }
    
    public String toString2(){
        String str = healthCardNumber + ", " + name + ", " + birthdate;
        return str;
    }
    
    public String toStringUrgency(){
    	String str = "";
    	if (listOfCondition.get(listOfCondition.size() - 1).getSeenByDoctor() == false) {
    		str = "Urgency: " + urgency() + '\n';
    	}
        
        return str;
    }
    
    /**
     * 
     * 
     * @return
     */
    public String conditionsToString(){
        StringBuilder tmp = new StringBuilder();
        for(int i = 0; i < listOfCondition.size(); i++){
            tmp.append(healthCardNumber + ";" + listOfCondition.get(i).toString() + '\n');
        }
        return tmp.toString();
    }
    
    /**
     * Get the list of Condition of the patient
     * 
     * @param		list of conditions
     * @return      null
     */
    public void setListOfCondition(ArrayList<Condition> listOfCondition) {
        this.listOfCondition = listOfCondition;
    }
    
    /**
     * Get the list of Condition of the patient
     * 
     * @param		null
     * @return      list of conditions
     */
    public ArrayList<Condition> getListOfCondition() {
        return listOfCondition;
    }
    
    /**
     * Set the list of Prescription of the patient
     * 
     * @param		arraylist of prescriptions
     * @return		null
     */
    public void setListOfPrescription(ArrayList listOfPrescription) {
    	this.listOfPrescription = listOfPrescription;
    }
    
    /**
     * Get the list of Prescription of the specific patient
     * 
     * @param		null
     * @return      list of conditions
     */
    public ArrayList<Prescription> getListOfPrescription() {
        return listOfPrescription;
    }
    
    /**
     *  Receive a new Prescription for the patient
     *  
     *  @param		prescription
     */
    public void newPrescription(Prescription p){
    	listOfPrescription.add(p);
    }
}