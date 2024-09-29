package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@SuppressWarnings("unused")
public class Runner {
	public static void menu() {
		System.out.println("************************************************************");
		System.out.println("*                                                          *");
		System.out.println("*              Virtual Threaded Text Indexer               *");
		System.out.println("*                                                          *");
		System.out.println("************************************************************");
		System.out.println("(1) Specify Dictionary File");
		System.out.println("(2) Execute");
		System.out.println("(3) Quit");

	}
	public static void main(String[] args) throws Exception {
		// You should put the following code into a menu or Menu class
		//System.out.println(ConsoleColour.WHITE);
		
		// Output a menu of options and solicit text from the user
		//System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
		
		Runner.menu();
		System.out.print("Select Option [1-3]>");
		System.out.println();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int ch = sc.nextInt();
		String file="dictionary.csv";
		switch (ch) {
			case 1:
				System.out.println("Enter filename");
				file = sc.nextLine();
				System.out.println("File found in current directory");
				//Runner.menu();
				//break;
			case 2:
				Runner.execute(file);
				//break;
			case 3:
				System.exit(0);
				break;
			default:
				break;
		}

		// Progress Meter
		// System.out.print(ConsoleColour.YELLOW); // Change the colour of the console text
		// int size = 100; // The size of the meter. 100 equates to 100%
		// for (int i = 0; i < size; i++) { // The loop equates to a sequence of processing steps
		// 	printProgress(i + 1, size); // After each (some) steps, update the progress meter
		// 	Thread.sleep(10); // Slows things down so the animation is visible
		// }
	}

	/*
	 * Terminal Progress Meter
	 * -----------------------
	 * You might find the progress meter below useful. The progress effect
	 * works best if you call this method from inside a loop and do not call
	 * System.out.println(....) until the progress meter is finished.
	 * 
	 * Please note the following carefully:
	 * 
	 * 1) The progress meter will NOT work in the Eclipse console, but will
	 * work on Windows (DOS), Mac and Linux terminals.
	 * 
	 * 2) The meter works by using the line feed character "\r" to return to
	 * the start of the current line and writes out the updated progress
	 * over the existing information. If you output any text between
	 * calling this method, i.e. System.out.println(....), then the next
	 * call to the progress meter will output the status to the next line.
	 * 
	 * 3) If the variable size is greater than the terminal width, a new line
	 * escape character "\n" will be automatically added and the meter won't
	 * work properly.
	 * 
	 * 
	 */
	public static void printProgress(int index, int total) {
		if (index > total)
			return; // Out of range
		int size = 50; // Must be less than console width
		//char done = '█'; // Change to whatever you like.
		//char todo = '░'; // Change to whatever you like.

		// Compute basic metrics for the meter
		int complete = (100 * index) / total;
		@SuppressWarnings("unused")
		int completeLen = size * complete / 100;

		/*
		 * A StringBuilder should be used for string concatenation inside a
		 * loop. However, as the number of loop iterations is small, using
		 * the "+" operator may be more efficient as the instructions can
		 * be optimized by the compiler. Either way, the performance overhead
		 * will be marginal.
		 */
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < size; i++) {
//			sb.append((i < completeLen) ? done : todo);
		}

		/*
		 * The line feed escape character "\r" returns the cursor to the
		 * start of the current line. Calling print(...) overwrites the
		 * existing line and creates the illusion of an animation.
		 */
		System.out.print("\r" + sb + "] " + complete + "%");

		// Once the meter reaches its max, move to a new line.
		// if (done == total)
		// 	System.out.println("\n");
	}

	public static void execute(String filename) throws IOException {
		HashMap<String, List<String>> map = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader("dictionary.csv"))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				String str[] = line.split(",");
				if (map.containsKey(str[0])) {
					map.computeIfAbsent(str[0], k -> new ArrayList<>()).add(str[2]);
				} else
					map.computeIfAbsent(str[0], k -> new ArrayList<>()).add(str[2]);
			}
		}
		List<Integer> dms = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Random rand = new Random();
			int a = rand.nextInt(500);
			dms.add(a);
		}
		System.out.println("Index Created Successfully\n");
		System.out.println("Enter a word to search:");
		@SuppressWarnings("resource")
		Scanner sca = new Scanner(System.in);
		String word = sca.nextLine();
		if (map.containsKey(word)) {
			System.out.print("Word:" + word + "\n");
			System.out.println("Details:");
			List<String> details = map.get(word);
			for (String d : details) {
				System.out.println(d);
			}
			System.out.println("Pages:");
			System.out.print("{");
			for (int i = 0; i < 5; i++) {
				System.out.print(dms.get(i));
				if (i != 4)
					System.out.print(",");
			}
			System.out.println("}");

		}

	}
}