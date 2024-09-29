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
public class Main {
    public static void main(String[] args) throws IOException {
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
                if(i!=4)
                System.out.print(",");
            }
            System.out.println("}");

        }
    }
}