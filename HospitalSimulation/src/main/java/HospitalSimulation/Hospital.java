package HospitalSimulation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import HospitalSimulation.Patient.HealthState;

/**
 * Class represents a hospital entity. Class includes three methods that define
 * healing process of patients.
 * 
 * @author sabinaorazem
 * @since 23.10.2018
 * @version 1.1
 */
public class Hospital implements Serializable{
	
	static final long serialVersionUID = 1L;
	
	private static Random random = new Random();
	
	/**
	 * Medication enum type has 4 possible values AS, AN, I and P.
	 */
	public enum Medication {

		AS("Aspirin"), AN("Antibiotic"), I("Insulin"), P("Paracetamol"), N("No medication");

		private String med;

		Medication(String med) {
			this.med = med;
		}
		
		public String getMedicationDesc() {
			return med;
		}
		
		public static ArrayList<Medication> getListOfMedications() { 
			ArrayList<Medication> list = new ArrayList<>();
			list.add(Medication.AS);
			list.add(Medication.AN);
			list.add(Medication.I);
			list.add(Medication.P);
			return list;
		}
		
		public static String displayValidMedications() { 
			return "Valid medications are As,An,I and P.";
		}
	}
	
	/**
	 * Method healPatienet defines an algorithm that changes patients health state
	 * according to prescribed medications.
	 * 
	 * @param patient
	 * @return Patient
	 */
	public Patient healPatient(Patient patient) {
		HealthState currentState = patient.getCurrentState();
		ArrayList<Medication> medications = patient.getPrescribedMedication();
		
		currentState = checkIligalCombinations(patient, currentState);
		
		switch (currentState) {
		case F:
			if (medications.contains(Medication.AS) || medications.contains(Medication.P)) 
				currentState = HealthState.H;
			break;
		case H:
			break;
		case D:
			if (!medications.contains(Medication.I))
				currentState = HealthState.X;
			break;
		case T:
			if (medications.contains(Medication.AN))
				currentState = HealthState.H;
			break;
		case X:
			currentState = flyingFlyingSpaghettiMonster(patient);
			break;
		}
		
		patient.setCurrentState(currentState);
		return patient;
	}

	
	/**
	 * Method checks for some illegal combinations of medications. One combination
	 * causes death and one fever.
	 * 
	 * @param pat
	 * @param currentState
	 * @return HealthState
	 */
	private HealthState checkIligalCombinations(Patient pat, HealthState currentState) { 
		ArrayList<Medication> prescribedMed = pat.getPrescribedMedication();

		if ((prescribedMed.contains(Medication.AS) && prescribedMed.contains(Medication.P)))
			return HealthState.X;
		if ((prescribedMed.contains(Medication.I) && prescribedMed.contains(Medication.AN) && !(HealthState.X == currentState)))
			return HealthState.F;
		
		return currentState;
	}
	
	/**
	 * Method generates a random number which defines if patient is magically
	 * brought back to life.
	 * 
	 * @param patient
	 * @return HealthState
	 */
	private HealthState flyingFlyingSpaghettiMonster(Patient patient) {
		if(random.nextInt(1000000) == 1) {
			return HealthState.H;
		}
		return HealthState.X;
	}
}
