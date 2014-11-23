package core.nurse.triage;

import java.io.Serializable;

/*
 * @author  Deivid Cavalcante da Silva
 * @version 1.0.4
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
    
    public Condition(String arrivalDate, boolean seenByDoctor, long time){
        this.arrivalDate = arrivalDate;
    	this.seenByDoctor = seenByDoctor;
        this.time = time;
    }
    
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
    
    public void setSymptons(String symptons) {
        this.symptoms = symptons;
    }
    
    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
    
    public String getArrivalDate() {
    	return this.arrivalDate;
    }
    
    public void seenByDoctor(boolean seenByDoctor) {
        this.seenByDoctor = seenByDoctor;
    }
    
    public void setTime(long time) {
        this.time = time;
    }
    
    public long getTime() {
    	return this.time;
    }
    
    public String toString(){
        String tmp = symptoms + ";" + temperature + ";" + bloodPressureDiastolic + ";" + bloodPressureSystolic + ";" + heartRate + ";" + arrivalDate + ";" + seenByDoctor + ";" + time;
        return tmp;
    }
}
