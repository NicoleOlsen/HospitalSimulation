package HospitalSimulation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import HospitalSimulation.Patient.HealthState;

public class Hospital implements Serializable{
	
	static final long serialVersionUID = 1L;
	
	private static Random random = new Random();
	
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

	private HealthState checkIligalCombinations(Patient pat, HealthState currentState) { 
		ArrayList<Medication> prescribedMed = pat.getPrescribedMedication();

		if ((prescribedMed.contains(Medication.AS) && prescribedMed.contains(Medication.P)))
			return HealthState.X;
		if ((prescribedMed.contains(Medication.I) && prescribedMed.contains(Medication.AN) && !(HealthState.X == currentState)))
			return HealthState.F;
		
		return currentState;
	}
	
	private HealthState flyingFlyingSpaghettiMonster(Patient patient) {
		if(random.nextInt(1000000) == 1) {
			return HealthState.H;
		}
		return HealthState.X;
	}
}
