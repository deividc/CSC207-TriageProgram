package core.nurse.triage;

import java.io.Serializable;

/**
 * This class is responsible for handling the information required
 * and methods required to provide prescription functionality.
 * 
 */
public class Prescription implements Serializable {
	// instance variables
	String medication;
	String instruction;
	String datePrescription;
	
	/**
	 * Constructor of the Class Prescription.
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
	 * Getter for medication.
	 * 
	 * @param		null
	 * @return		medication
	 */
	public String getMedication() {
		return medication;
	}
	
	/**
	 * Setter for Medication.
	 * 
	 * @param		medication
	 * @return		null
	 */
	public void setMedication(String medication) {
		this.medication = medication;
	}
	
	/**
	 * Getter the Instruction.
	 * 
	 * @param		null
	 * @return		instruction
	 */
	public String getInstruction() {
		return instruction;
	}
	
	/**
	 * Setter the Instruction.
	 * 
	 * @param		instruction
	 * @return		null
	 */
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	
	/**
	 * Getter Date of Prescription.
	 * 
	 * @param null
	 * @return date of prescription
	 */
	public String getDatePrescription() {
		return datePrescription;
	}

	/**
	 * Setter Date of Prescription.
	 * 
	 * @param datePrescription
	 * @return null
	 */
	public void setDatePrescription(String datePrescription) {
		this.datePrescription = datePrescription;
	}

	/**
	 * Output all information to string.
	 * 
	 * @param null
	 * @return string of info for prescriptions
	 */
	public String toString() {
		String tmp = "Medication: " + medication + '\n' +
					 "Instructions: " + instruction + '\n' +
					 "Date of Prescription: " + datePrescription + '\n';
		return tmp;
		
	}
	
}
