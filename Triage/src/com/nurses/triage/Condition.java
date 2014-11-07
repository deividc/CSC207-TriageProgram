package com.nurses.triage;

public class Condition {
	private String symptons;
    private float temperature;
    private int bloodPressureDiastolic;
    private int bloodPressureSystolic;
    private int heartRate;
    private String arrivalDate;
    private boolean seenByDoctor;
    private long time;
    
    public Condition(boolean seenByDoctor){
        this.setSeenByDoctor(seenByDoctor);
    }
    
    public Condition(String symptoms, float temperature, int bloodPressureDiastolic,
    int bloodPressureSystolic, int heartRate, String arrivalDate, boolean seenByDoctor, long time){
        this.setSymptons(symptoms);
        this.setTemperature(temperature);
        this.setBloodPressureDiastolic(bloodPressureDiastolic);
        this.setBloodPressureSystolic(bloodPressureSystolic);
        this.setHeartRate(heartRate);
        this.setArrivalDate(arrivalDate);
        this.setSeenByDoctor(seenByDoctor);
        this.setTime(time);
    }
    
    public void symptons(String symptons){
        this.setSymptons(symptons);
    }
    
    public void arrivalDate(String arrivalDate){
        this.setArrivalDate(arrivalDate);
    }
    
    public void seenByDoctor(boolean seenByDoctor){
        this.setSeenByDoctor(seenByDoctor);
    }
    
    public void time(long time){
        this.setTime(time);
    }

	public String getSymptons() {
		return symptons;
	}

	public void setSymptons(String symptons) {
		this.symptons = symptons;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public int getBloodPressureDiastolic() {
		return bloodPressureDiastolic;
	}

	public void setBloodPressureDiastolic(int bloodPressureDiastolic) {
		this.bloodPressureDiastolic = bloodPressureDiastolic;
	}

	public int getBloodPressureSystolic() {
		return bloodPressureSystolic;
	}

	public void setBloodPressureSystolic(int bloodPressureSystolic) {
		this.bloodPressureSystolic = bloodPressureSystolic;
	}

	public int getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(int heartRate) {
		this.heartRate = heartRate;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public boolean isSeenByDoctor() {
		return seenByDoctor;
	}

	public void setSeenByDoctor(boolean seenByDoctor) {
		this.seenByDoctor = seenByDoctor;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}
