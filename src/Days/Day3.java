package Days;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day3 implements Day {
	private final List<String> input = getInputAsStrings("Input3.txt");

	public void partOne() {
		String[] wire1 = input.get(0).split(",");
		String[] wire2 = input.get(1).split(",");

		Trail wireOneTrail = new Trail(wire1);
		Trail wireTwoTrail = new Trail(wire2);
		
		List<Point> intersections = getIntersections(wireOneTrail, wireTwoTrail);
		int closest = getClosestDistance(intersections);
		
		System.out.println("Answer part one: " + closest);
	}

	public void partTwo() {
		String[] wire1 = input.get(0).split(",");
		String[] wire2 = input.get(1).split(",");

		Trail wireOneTrail = new Trail(wire1);
		Trail wireTwoTrail = new Trail(wire2);
		
		List<Point> intersections1 = getIntersections(wireOneTrail, wireTwoTrail);
		List<Point> intersections2 = getIntersections(wireTwoTrail, wireOneTrail);
		int closest = getClosestDistanceBySteps(intersections1, intersections2);
		
		System.out.println("Answer part two: " + closest);
	}

	private List<Point> getIntersections(Trail first, Trail second) {
		List<Point> intersections= new ArrayList<>();
		first.trail.forEach(point -> {if (second.trail.contains(point)) intersections.add(point);});
		return intersections;
	}
	
	private int getClosestDistance(List<Point> intersections) {
		int minDistance = Integer.MAX_VALUE;
		for (Point point : intersections) {
			int distance = getManhattanDistanceToCenter(point);
			minDistance = (distance < minDistance) ? distance : minDistance;
		}
		return minDistance;
	}
	
	private int getClosestDistanceBySteps(List<Point> firstTrail, List<Point> secondTrail) {
		int minDistance = Integer.MAX_VALUE;
		for (Point point1 : firstTrail) {
			for (Point point2 : secondTrail) {
				if (point1.equals(point2)) {
					int distance = point1.steps+point2.steps;
					minDistance = (distance < minDistance) ? distance : minDistance;
					break;
				}
			}
		}
		return minDistance;
	}
	
	private int getManhattanDistanceToCenter(Point intersection) {
		return Math.abs(intersection.x-0) + Math.abs(intersection.y-0);
	}

	private class Trail {
		Set<Point> trail = new HashSet<>();
		Point current = new Point(0,0,0);
		
		public Trail(String[] path) {
			run(path);
		}
		
		private void run(String[] path) {
			for (String s : path) {
				String dir = s.substring(0, 1);
				int steps = Integer.parseInt(s.substring(1));
				switch (dir) {
				case "U":
					moveUp(steps);
					break;
				case "D":
					moveDown(steps);
					break;
				case "R":
					moveLeft(steps);
					break;
				case "L":
					moveRight(steps);
					break;
				default:
					break;
				}
			}
		}
		
		private void moveUp(int steps) {
			int x = current.x;
			int y = current.y;
			int step = current.steps;
			while (steps > 0) {
				trail.add(new Point(x,++y,++step));
				steps--;
			}
			current.y = y;
			current.steps = step;
		}
		private void moveDown(int steps) {
			int x = current.x;
			int y = current.y;
			int step = current.steps;
			while (steps > 0) {
				trail.add(new Point(x,--y,++step));
				steps--;
			}
			current.y = y;
			current.steps = step;
		}
		private void moveRight(int steps) {
			int x = current.x;
			int y = current.y;
			int step = current.steps;
			while (steps > 0) {
				trail.add(new Point(++x,y,++step));
				steps--;
			}
			current.x = x;
			current.steps = step;
		}
		private void moveLeft(int steps) {
			int x = current.x;
			int y = current.y;
			int step = current.steps;
			while (steps > 0) {
				trail.add(new Point(--x,y,++step));
				steps--;
			}
			current.x = x;
			current.steps = step;
		}
	}

	private class Point {
		int x, y, steps;
		
		public Point(int x, int y, int steps) {
			this.x = x;
			this.y = y;
			this.steps = steps;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			
			if (!Point.class.isAssignableFrom(obj.getClass())) {
				return false;
			}
			
			final Point other = (Point) obj;
			if (this.x != other.x) {
				return false;
			}
			
			if (this.y != other.y) {
				return false;
			}
			
			return true;
		}
		
		@Override
		public int hashCode() {
			int hash = 3;
			hash = 53 * hash + this.x;
			hash = 53 * hash + this.y;
			return hash;
		}
	}
}