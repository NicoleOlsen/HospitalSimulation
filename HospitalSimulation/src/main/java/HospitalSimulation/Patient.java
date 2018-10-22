package HospitalSimulation;

import java.io.Serializable;
import java.util.ArrayList;

import HospitalSimulation.Hospital.Medication;


public class Patient implements Serializable{
	
	static final long serialVersionUID = 1L;
	
	public enum HealthState {

		F("Fever"), H("Healthy"), D("Diabetes"), T("Tuberculosis"), X("Dead");

		private String state;

		HealthState(String state) {
			this.state = state;
		}
		
		public String getHealthStateDesc() {
			return state;
		}

		public static ArrayList<HealthState> getListOfHealthStates() { 
			ArrayList<HealthState> list = new ArrayList<>();
			list.add(HealthState.F);
			list.add(HealthState.H);
			list.add(HealthState.D);
			list.add(HealthState.T);
			list.add(HealthState.D);
			return list;
		}
		
		public static String displayValidHealthStates() { 
			return "Valid helath states are: F,H,D,T and X.";
		}
	}
	
	private HealthState currentState;
	private ArrayList<Medication> prescribedMedication;

	Patient(HealthState currentState, ArrayList<Medication> medication) {
		this.currentState = currentState;
		this.prescribedMedication = medication;
	}

	public HealthState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(HealthState currentState) {
		this.currentState = currentState;
	}

	public ArrayList<Medication> getPrescribedMedication() {
		return prescribedMedication;
	}

	public void setPrescribedMedication(ArrayList<Medication> prescribedMedication) {
		this.prescribedMedication = prescribedMedication;
	}
}
