package HospitalSimulationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

import HospitalSimulation.Hospital.Medication;
import HospitalSimulation.Patient.HealthState;

public class HospitalSimulationTest {

	private static Random random = new Random();

	private static String inputPat0Med0;
	private static String inputPat05Med0;
	private static String inputPat100Med05;
	private static String inputPat0Med2;
	private static String inputPat1Med3;
	private static String inputPat100Med1;
	private static String inputPat100Med2;
	private static String inputPat100Med100;
	private static String inputPat1000Med3;

	@BeforeClass
	public static void setup() {
		String inputPatients1 = generateRandomInputOfPatients(1);
		String inputPatients100 = generateRandomInputOfPatients(100);
		String inputPatients1000 = generateRandomInputOfPatients(1000);

		String inputMedications1 = generateRandomInputOfDrugs(1);
		String inputMedications2 = generateRandomInputOfDrugs(2);
		String inputMedications3 = generateRandomInputOfDrugs(3);
		String inputMedications100 = generateRandomInputOfDrugs(100);

		inputPat0Med0 = HospitalSimulation.HospitalSimulation.generateOutput("", "");
		inputPat05Med0 = HospitalSimulation.HospitalSimulation.generateOutput(",,,,,", "");
		inputPat100Med05 = HospitalSimulation.HospitalSimulation.generateOutput(inputPatients100, ",,,,,");
		inputPat0Med2 = HospitalSimulation.HospitalSimulation.generateOutput("", inputMedications2);
		inputPat1Med3 = HospitalSimulation.HospitalSimulation.generateOutput(inputPatients1, inputMedications3);
		inputPat100Med1 = HospitalSimulation.HospitalSimulation.generateOutput(inputPatients100, inputMedications1);
		inputPat100Med2 = HospitalSimulation.HospitalSimulation.generateOutput(inputPatients100, inputMedications2);
		inputPat100Med100 = HospitalSimulation.HospitalSimulation.generateOutput(inputPatients100, inputMedications100);
		inputPat1000Med3 = HospitalSimulation.HospitalSimulation.generateOutput(inputPatients1000, inputMedications3);
	}

	@Test
	public void testPattern() {
		String msg = "Output doesn't match the pattern ([HDTXF]:\\d+,)+([HDTXF]:\\d+).";
		String pattern = "([HDTXF]:\\d+,)+([HDTXF]:\\d+)";
		assertTrue(msg, inputPat0Med0.matches(pattern));
		assertTrue(msg, inputPat05Med0.matches(pattern));
		assertTrue(msg, inputPat100Med05.matches(pattern));
		assertTrue(msg, inputPat0Med2.matches(pattern));
		assertTrue(msg, inputPat1Med3.matches(pattern));
		assertTrue(msg, inputPat100Med1.matches(pattern));
		assertTrue(msg, inputPat100Med2.matches(pattern));
		assertTrue(msg, inputPat100Med100.matches(pattern));
		assertTrue(msg, inputPat1000Med3.matches(pattern));
	}

	@Test
	public void testNumberOfPatients() { 
		String msg = "Number of patients in output doesn't match number of patients in input.";
		assertEquals(msg, countPatients(inputPat0Med0), 0);
		assertEquals(msg, countPatients(inputPat05Med0), 0);
		assertEquals(msg, countPatients(inputPat100Med05), 100);
		assertEquals(msg, countPatients(inputPat0Med2), 0);
		assertEquals(msg, countPatients(inputPat1Med3), 1);
		assertEquals(msg, countPatients(inputPat100Med1), 100);
		assertEquals(msg, countPatients(inputPat100Med2), 100);
		assertEquals(msg, countPatients(inputPat100Med100), 100);
		assertEquals(msg, countPatients(inputPat1000Med3), 1000);
	}

	private static String generateRandomInputOfPatients(int i) {
		String output = "";
		for (int j = 0; j < i; j++) {
			output += HealthState.getListOfHealthStates().get(random.nextInt(4)).toString() + ",";
		}
		return output.substring(0, output.length() - 1);
	}

	private static String generateRandomInputOfDrugs(int i) {
		String output = "";
		for (int j = 0; j < i; j++) {
			output += Medication.getListOfMedications().get(random.nextInt(4)).toString() + ",";
		}
		return output.substring(0, output.length() - 1);
	}

	private static int countPatients(String str) {
		int numOfPat = 0;
		String[] splitComma = str.split(",");
		for (String subStr : splitComma) {
			numOfPat += Integer.valueOf(subStr.split(":")[1]);
		}
		return numOfPat;
	}
}
