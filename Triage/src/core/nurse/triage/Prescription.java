package core.nurse.triage;

import java.io.Serializable;

/*
* @author  Deivid Cavalcante da Silva
* @version 1.0.0
* @date    2014-11-08
*/

public class Prescription implements Serializable {
	String medication;
	String instruction;
	String datePrescription;
	
	/**
	 * Constructor of the Class Prescription
	 * 
	 * @param		medication
	 * @param		instruction
	 * @return		null
	 */
	public Prescription(String medication, String instruction) {
		this.medication = medication;
		this.instruction = instruction;
	}
	
	/**
	 * Getter medication
	 * 
	 * @param		null
	 * @return		medication
	 */
	public String getMedication() {
		return medication;
	}
	
	/**
	 * Setter Medication
	 * 
	 * @param		medication
	 * @return		null
	 */
	public void setMedication(String medication) {
		this.medication = medication;
	}
	
	/**
	 * Getter Instruction
	 * 
	 * @param		null
	 * @return		instruction
	 */
	public String getInstruction() {
		return instruction;
	}
	
	/**
	 * Setter Instruction
	 * 
	 * @param		instruction
	 * @return		null
	 */
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	
	/**
	 * Getter Date of Prescription
	 * 
	 * @return date of prescription
	 */
	public String getDatePrescription() {
		return datePrescription;
	}

	/**
	 * Setter Date of Prescription
	 * 
	 * @param datePrescription
	 */
	public void setDatePrescription(String datePrescription) {
		this.datePrescription = datePrescription;
	}

	public String toString() {
		String tmp = "Medication: " + medication + '\n' +
					 "Instructions: " + instruction + '\n' +
					 "Date of Prescription: " + datePrescription + '\n';
		return tmp;
		
	}
	
}
