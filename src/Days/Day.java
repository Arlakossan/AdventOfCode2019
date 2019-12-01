package Days;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface Day{
	default List<String> getInputAsStrings(String fileName) {
		try {
			return (List<String>) Files.readAllLines(new File("src/Input/" + fileName).toPath());
		} catch (IOException e) {
			System.err.println("Failed to read from file");
			return Collections.emptyList();
		}
	}
	
	default List<Integer> getInputAsIntegers(String fileName) {
		List<Integer> list = new ArrayList<>();
		for (String s : getInputAsStrings(fileName)) {
			list.add(Integer.parseInt(s));
		}
		return list;
	}
	
	abstract void partOne();
	abstract void partTwo();
}
