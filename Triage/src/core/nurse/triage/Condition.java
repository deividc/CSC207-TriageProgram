package core.nurse.triage;

import java.io.Serializable;

/*
 * @author  Deivid Cavalcante da Silva
 * @version 1.0.5
 * @date    2014-11-08
 */

public class Condition implements Serializable
{
    private String symptoms;
    private float temperature;
    private int bloodPressureDiastolic;
    private int bloodPressureSystolic;
    private int heartRate;
    private String arrivalDate;
    private boolean seenByDoctor;
    private long time;
    
    /**
     * Short constructor of Condition
     * 
     * @param arrivalDate
     * @param seenByDoctor
     * @param time
     */
    public Condition(String arrivalDate, boolean seenByDoctor, long time){
        this.arrivalDate = arrivalDate;
    	this.seenByDoctor = seenByDoctor;
        this.time = time;
    }
    
    /**
     * Extended constructor of Condition
     * 
     * @param symptoms
     * @param temperature
     * @param bloodPressureDiastolic
     * @param bloodPressureSystolic
     * @param heartRate
     * @param arrivalDate
     * @param seenByDoctor
     * @param time
     */
    public Condition(String symptoms, float temperature, int bloodPressureDiastolic,
    int bloodPressureSystolic, int heartRate, String arrivalDate, boolean seenByDoctor, long time){
        this.symptoms = symptoms;
        this.temperature = temperature;
        this.bloodPressureDiastolic = bloodPressureDiastolic;
        this.bloodPressureSystolic = bloodPressureSystolic;
        this.heartRate = heartRate;
        this.arrivalDate = arrivalDate;
        this.seenByDoctor = seenByDoctor;
        this.time = time;
    }
    
    /**
     * Setter Symptoms
     * 
     * @param symptoms
     */
    public void setSymptons(String symptoms) {
        this.symptoms = symptoms;
    }
    
    /**
     * Getter Symptoms
     * 
     * @return
     */
    public String getSymptons() {
        return this.symptoms;
    }
    
    /**
     * Setter Arrival Date
     * 
     * @param arrivalDate
     */
    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
    
    /**
     * 	Getter Arrival Date
     * 
     * @return	 arrival date
     */
    public String getArrivalDate() {
    	return this.arrivalDate;
    }
    
    /**
     * Setter SeenByDoctor
     * 
     * @param seenByDoctor
     */
    public void seenByDoctor(boolean seenByDoctor) {
        this.seenByDoctor = seenByDoctor;
    }
    
    /**
     * Getter SeenByDoctor
     * 
     * @return	boolean seen by doctor
     */
    public boolean getSeenByDoctor() {
        return this.seenByDoctor;
    }
    
    /**
     * Setter Time
     * 
     * @param time
     */
    public void setTime(long time) {
        this.time = time;
    }
    /**
     * Getter Time
     * 
     * @return	time
     */
    public long getTime() {
    	return this.time;
    }
    
    /**
     * Method specific for recording data in text files
     */
    public String toString(){
        String tmp = symptoms + ";" + temperature + ";" + bloodPressureDiastolic + ";" + bloodPressureSystolic + ";" + heartRate + ";" + arrivalDate + ";" + seenByDoctor + ";" + time;
        return tmp;
    }
    
    /**
     * Method that recover and setup the data for showing in the screen
     * 
     * @return	information related to the condition
     */
    public String toString2(){
    	String tmp = new String();
    	if (seenByDoctor == true) {
    		tmp = "Arrival Date: "+ arrivalDate + "\n" +
          		  "Seen by Doctor: " + seenByDoctor + "\n";
    	}
    	else {
    		tmp = "Symptoms: " + symptoms + "\n" +
       			 "Temperature: " + temperature + "\n" +
       			 "Blood Pressure " + "\n" +
       			 "		Diastolic: " + bloodPressureDiastolic + "\n" +
       			 "		Systolic: " + bloodPressureSystolic + "\n" +
       			 "Heart Rate: " + heartRate + "\n" +
       			 "Arrival Date: "+ arrivalDate + "\n" +
       			 "Seen by Doctor: " + seenByDoctor + "\n";
    	}
        
        return tmp;
    }
    
    public float getTemperature() {
    	return this.temperature;
    }
    
    public int getSystolic(){
    	return this.bloodPressureSystolic;
    }
    
    public int getDiastolic(){
    	return this.bloodPressureDiastolic;
    }
    
    public int getHeartRate(){
    	return this.heartRate;
    }

}
