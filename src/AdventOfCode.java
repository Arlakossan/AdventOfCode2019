import Days.*;

public class AdventOfCode {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();

		solve(new Day1(), 1);
		
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime + "ms");

	}
	
	private static void solve(Day day, Integer part) {
		switch (part) {
		case 1:
			day.partOne();
			break;
		case 2:
			day.partTwo();
			break;
		}
	}
}