package core.nurse.triage;

import java.io.Serializable;

/**
 * This class is responsible for setting and storing patient vitals and time info in memory.
 * 
 * @version 1.0.2
 * @date    2014-11-08
 */
public class Condition implements Serializable
{
	/**
	 * Instance variables.
	 */
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
     * Setter for the Symptoms.
     * 
     * @param symptoms
     * @return null
     */
    public void setSymptons(String symptoms) {
        this.symptoms = symptoms;
    }
    
    /**
     * Getter for Symptoms.
     * 
     * @param null
     * @return symptoms
     */
    public String getSymptoms() {
        return this.symptoms;
    }
    
    /**
     * Setter for Arrival Date.
     * 
     * @param arrivalDate
     * @return null
     */
    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
    
    /**
     * 	Getter for Arrival Date.
     * 
     * @param	null
     * @return	 arrival date
     */
    public String getArrivalDate() {
    	return this.arrivalDate;
    }
    
    /**
     * Setter for if seen by doctor.
     * 
     * @param seenByDoctor
     * @return null
     */
    public void seenByDoctor(boolean seenByDoctor) {
        this.seenByDoctor = seenByDoctor;
    }
    
    /**
     * Getter for if seen by doctor.
     * 
     * @return	boolean seen by doctor
     * @param	null
     */
    public boolean getSeenByDoctor() {
        return this.seenByDoctor;
    }
    
    /**
     * Setter for Time.
     * 
     * @param time
     * @return null
     */
    public void setTime(long time) {
        this.time = time;
    }
    /**
     * Getter for Time.
     * 
     * @param	null
     * @return	time
     */
    public long getTime() {
    	return this.time;
    }
    
    /**
     * Method specific for recording data in text files.
     * 
     * @param	null
     * @return	conditions for display to string.
     */
    public String toString(){
        String tmp = symptoms + ";" + temperature + ";" + bloodPressureDiastolic + ";" + bloodPressureSystolic + ";" + heartRate + ";" + arrivalDate + ";" + seenByDoctor + ";" + time;
        return tmp;
    }
    
    /**
     * Method that recover and setup the data for showing in the screen
     * 
     * @param	null
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
    
    /**
     * Return the temperature.
     *
     * @param	null
     *
     * @return	temperature
     */
    public float getTemperature() {
    	return this.temperature;
    }
    
    /**
     * Return the systolic blood pressure.
     *
     * @param	null
     *
     * @return	systolic blood pressure
     */
    public int getSystolic(){
    	return this.bloodPressureSystolic;
    }
    
    /**
     * Return the diastolic blood pressure.
     *
     * @param	null
     *
     * @return	diastolic blood pressure.
     */
    public int getDiastolic(){
    	return this.bloodPressureDiastolic;
    }
    
    /**
     * Return the heart rate.
     *
     * @param	null
     *
     * @return	heart rate
     */
    public int getHeartRate(){
    	return this.heartRate;
    }

}
