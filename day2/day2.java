package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

public class day2 {

    private static final String input = "/Users/WillyLampert/Desktop/AdventOfCode/inputs/day2input.txt";

    public static void main(String[] args) throws IOException {
        //Part 1
        BufferedReader in = new BufferedReader ( new FileReader(input));
        int twiceoverall = 0;
        int threetimesoverall = 0;


        String ID;
        while((ID = in.readLine()) != null) {
            Hashtable<Character, Integer> table = new Hashtable<>();
            Vector<Character> twice = new Vector<>();
            Vector<Character> threetimes = new Vector<>();
            for (int i = 0; i < ID.length(); i++) {
                Character c = ID.charAt(i);
                if (!table.containsKey(c)) {
                    table.put(c, 1);
                } else {
                    table.put(c, table.get(c) + 1);
                    if (table.get(c) == 2) {
                        twice.add(c);
                    }
                    else if(table.get(c) == 3){
                        twice.removeElement(c);
                        threetimes.add(c);
                    }
                    else {
                        threetimes.removeElement(c);
                    }
                }
            }
            if (twice.size() > 0) twiceoverall++;
            if (threetimes.size() > 0) threetimesoverall++;
        }

        System.out.println(twiceoverall * threetimesoverall);


        //Part 2
        in = new BufferedReader ( new FileReader(input));
        Vector<String> boxes = new Vector<>();
        while((ID = in.readLine()) != null) {
            boxes.add(ID);
        }

        for(int j = 0; j<boxes.size() -1; j++) {
            String s = boxes.elementAt(j);
            for(int k = j + 1; k<boxes.size(); k++) {
                int count = 0;
                boolean match = false;
                int different = 0;
                char y = 'a';
                String compare = boxes.elementAt(k);
                for(int x = 0; x<s.length(); x++) {
                    char char1 = s.charAt(x);
                    char char2 = compare.charAt(x);
                    if(char1 != char2) {
                        count++;
                        if(count == 1) {
                            match = true;
                            different = x;
                            y = char1;
                        }
                        else {
                            match = false;
                        }
                    }
                }
                if(match) {
                    System.out.println(s.substring(0,different) + s.substring(different+1));
                    System.out.println(s);
                    System.out.println(y);
                }



            }
        }



    }
}