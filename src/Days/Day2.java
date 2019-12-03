package Days;

import java.util.Arrays;
import java.util.List;

public class Day2 implements Day {
	private final List<String> input = getInputAsStrings("Input2.txt");
	private final int[] numbers;
	private final int goal = 19690720;

	public Day2() {
		String[] input = this.input.get(0).split(",");
		numbers = Arrays.asList(input).stream().mapToInt(Integer::parseInt).toArray();
	}
	
	public void partOne() {
		System.out.println("Answer part one: " + compute(getCleanInstructions(12, 2)));
	}
	
	public void partTwo() {
		int answer = 0;
		int noun = 0;
		while (answer == 0) {
			for (int verb = 0; verb < 100; verb++) {
				answer = (compute(getCleanInstructions(noun, verb)) == goal) ? goal : 0;
				if (answer > 0) {
					answer = (100 * noun) + verb;
					break;
				}
			}
			noun++;
		}
		System.out.println("Answer part two: " + answer);
	}
	
	private int[] getCleanInstructions(int noun, int verb) {
		int[] cleanNumbers = numbers.clone();
		cleanNumbers[1] = noun;
		cleanNumbers[2] = verb;
		return cleanNumbers;
	}
	
	private int compute(int[] instructions) {
		for (int i = 0; i < instructions.length; i += 4) {
			int param1, param2, param3, output;
			int opcode = instructions[i];
			
			if (opcode > 2) {
				break;
			}
			
			param1 = instructions[i+1];
			param2 = instructions[i+2];
			param3 = instructions[i+3];

			if (opcode == 1) {
				output = instructions[param1]+instructions[param2];
			} else {
				output = instructions[param1]*instructions[param2];
			}
			instructions[param3] = output;
		}
		return instructions[0];
	}
	
}