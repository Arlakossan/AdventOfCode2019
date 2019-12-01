package Days;
import java.util.List;

public class Day1 implements Day {
	private final List<Integer> input = getInputAsIntegers("Input1.txt"); 
	
	public void partOne() {
		int sum = 0;
		for (Integer mass : input) {
			sum += (mass / 3) - 2;
		}
		System.out.println("Answer part one: " + sum);
	}

	public void partTwo() {
		int sum = 0;
		for (Integer mass : input) {
			sum += calculateFuelRequired(mass);
		}
		System.out.println("Answer part two: " + sum);
	}

	private int calculateFuelRequired(Integer mass) {
		Integer newMass = (mass / 3) - 2;
		return (mass > 8) ? calculateFuelRequired(newMass) + newMass : 0;
	}
}