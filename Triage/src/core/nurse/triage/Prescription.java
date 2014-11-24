package core.nurse.triage;

/*
* @author  Deivid Cavalcante da Silva
* @version 1.0.0
* @date    2014-11-08
*/

public class Prescription {
	String medication;
	String instruction;
	
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
	
	
}
