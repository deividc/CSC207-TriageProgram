package com.nurses.triage;
/*
 * @author  Deivid Cavalcante da Silva
 * @version 1.0.2
 * @date    2014-11-08
 */

public class Condition
{
    private String symptoms;
    private float temperature;
    private int bloodPressureDiastolic;
    private int bloodPressureSystolic;
    private int heartRate;
    private String arrivalDate;
    private boolean seenByDoctor;
    private long time;
    
    public Condition(boolean seenByDoctor){
        this.seenByDoctor = seenByDoctor;
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
    
    public Condition(String symptoms, float temperature, int bloodPressureDiastolic,
    int bloodPressureSystolic, int heartRate, String arrivalDate){
        this.symptoms = symptoms;
        this.temperature = temperature;
        this.bloodPressureDiastolic = bloodPressureDiastolic;
        this.bloodPressureSystolic = bloodPressureSystolic;
        this.heartRate = heartRate;
        this.arrivalDate = arrivalDate;
        this.seenByDoctor = seenByDoctor;
        DateTime dt = new DateTime();
        this.time = dt.getEpoch();
    }
    
    public void symptons(String symptons){
        this.symptoms = symptons;
    }
    
    public void arrivalDate(String arrivalDate){
        this.arrivalDate = arrivalDate;
    }
    
    public void seenByDoctor(boolean seenByDoctor){
        this.seenByDoctor = seenByDoctor;
    }
    
    public void time(long time){
        this.time = time;
    }
    
    public String toString(){
        String tmp = symptoms + ";" + temperature + ";" + bloodPressureDiastolic + ";" + bloodPressureSystolic + ";" + heartRate + ";" + arrivalDate + ";" + seenByDoctor + ";" + time;
        return tmp;
    }
}
