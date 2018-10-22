package HospitalSimulation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import HospitalSimulation.Hospital.Medication;
import HospitalSimulation.Patient.HealthState;

public class HospitalSimulation implements Serializable{
	
	private static final long serialVersionUID = 1L;
		
	private static Hospital hospital = new Hospital();
	
	private static HashMap<HealthState, Integer> mapPatMed = new HashMap<>();
	
	private static ArrayList<Medication> medsGivenToPatients = new ArrayList<>();
	private static ArrayList<Patient> healedPatients = new ArrayList<>();
	
	public static String generateOutput(String patients, String medications) {
		initialize();
		
		String[] listOfPatients = patients.toUpperCase().split(","); 
		String[] listOfMedications = medications.toUpperCase().split(",");
		
		if (listOfPatients.length == 0 || "".equals(listOfPatients[0])) {
			return mapToStr(mapPatMed);
		}

		for (int j = 0; j < listOfMedications.length; j++) {
			try {
				if("".equals(listOfMedications[j])) 
					medsGivenToPatients.add(Medication.N);
				else
					medsGivenToPatients.add(Medication.valueOf(listOfMedications[j])); 
			} catch (IllegalArgumentException e) {
				return listOfMedications[j] + " is not valid medication." + Medication.displayValidMedications();
			}
		}

		for (int i = 0; i < listOfPatients.length; i++) {
			HealthState healthState = null;
			try {
				healthState = HealthState.valueOf(listOfPatients[i]);
			} catch (IllegalArgumentException e) {
				return listOfPatients[i] + " is not a valid health state. " + HealthState.displayValidHealthStates();
			}

			Patient patient = new Patient(healthState, medsGivenToPatients);
			Patient healedPatient = hospital.healPatient(patient);
			healedPatients.add(healedPatient);
			mapPatMed.computeIfPresent(healedPatient.getCurrentState(), (k,v) -> v+1);
		}
		return mapToStr(mapPatMed);
	}

	private static void initialize() {
		mapPatMed.put(HealthState.F, 0);
		mapPatMed.put(HealthState.H, 0);
		mapPatMed.put(HealthState.D, 0);
		mapPatMed.put(HealthState.T, 0);
		mapPatMed.put(HealthState.X, 0);
	}
	private static String mapToStr(HashMap<HealthState, Integer> map) {
		String out = "";
		for (Map.Entry<HealthState, Integer> entry : map.entrySet()) {
			if (out != "") 
				out += ",";
			out += entry.getKey().toString() + ":";
			out += entry.getValue();
		}
		return out;
	}
	
	public static void main(String[] args) {	
		if (args.length == 0) {
			System.out.println(generateOutput("", "")); 
		} else if (args.length == 1) {
			System.out.println(generateOutput(args[0], ""));
		} else if (args.length == 2) {
			System.out.println(generateOutput(args[0], args[1]));
		} else {
			System.out.println("Too many arguments. Please enter two arguments; list of patients and list of medications.");
		}
		return;
	}
}