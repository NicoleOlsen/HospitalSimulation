package HospitalSimulation;

import java.io.Serializable;
import java.util.ArrayList;

import HospitalSimulation.Hospital.Medication;

/**
 * Class represents a patient entity.
 * 
 * @author sabinaorazem
 * @since 23.10.2018
 * @version 1.1
 */
public class Patient implements Serializable{
	
	static final long serialVersionUID = 1L;
	
	/**
	 * HealthState enum type has 5 possible values F, H, D, T and X.
	 */
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
			return "Valid helath states are F,H,D,T and X.";
		}
	}
	
	private HealthState currentState;
	private ArrayList<Medication> prescribedMedication;

	Patient(HealthState currentState, ArrayList<Medication> medication) {
		this.currentState = currentState;
		this.prescribedMedication = medication;
	}

	/**
	 * @return HealthState
	 */
	public HealthState getCurrentState() {
		return currentState;
	}

	/**
	 * @param currentState
	 */
	public void setCurrentState(HealthState currentState) {
		this.currentState = currentState;
	}

	/**
	 * @return ArrayList<Medication>
	 */
	public ArrayList<Medication> getPrescribedMedication() {
		return prescribedMedication;
	}

	/**
	 * @param prescribedMedication
	 */
	public void setPrescribedMedication(ArrayList<Medication> prescribedMedication) {
		this.prescribedMedication = prescribedMedication;
	}
}
