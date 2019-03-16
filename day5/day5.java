package day5;

import com.sun.xml.internal.org.jvnet.fastinfoset.RestrictedAlphabet;
import day4.Entrie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;



public class day5 {
    public static int reactPolymer(Vector<Character> vector) {
        boolean b = true;
        while(b) {
            for(int i = 0; i<vector.size(); i++) {
                if(i == vector.size() - 1) {
                    b = false;
                    break;
                }
                char char1 = vector.elementAt(i);
                char char2 = vector.elementAt(i+1);
                if(Character.isLowerCase(char1) && !Character.isLowerCase(char2)) {
                    char2 = Character.toLowerCase(char2);
                    if(char1 == char2) {
                        vector.removeElementAt(i);
                        vector.removeElementAt(i);
                        if(i > 0) {
                            i = i - 2;
                        }
                        else {
                            i = -1;
                        }
                    }
                }
                else if(!Character.isLowerCase(char1) && Character.isLowerCase(char2)) {
                    char1 = Character.toLowerCase(char1);
                    if(char1 == char2) {
                        vector.removeElementAt(i);
                        vector.removeElementAt(i);
                        if(i > 0) {
                            i = i - 2;
                        }
                        else {
                            i = -1;
                        }
                    }
                }
            }
        }
        return vector.size();
    }


    private static final String input = "/Users/WillyLampert/Desktop/AdventOfCode/inputs/day5input.txt";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(input));
        char[] chars = new char[55000];
        Vector<Character> chars2 = new Vector<>();
        in.read(chars);
        for(char x:chars) {
            if((x>64 && x<91) || (x>96 && x<123)) {
                chars2.add(x);
            }
        }
        System.out.println(chars.length);
        System.out.println(chars2.size());



        //Part 1
        System.out.println(reactPolymer(chars2));


        //Part 2
        char[] alphabet = {'a',  'b', 'c',  'd','e', 'f', 'g','h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        int curmin = 100000;
        for(char letter: alphabet) {
            Vector<Character> chars3 = (Vector) chars2.clone();
            char capitalLetter = Character.toUpperCase(letter);
            while (chars3.contains(letter)){
                chars3.removeElement(letter);
            }
            while (chars3.contains(capitalLetter)){
                chars3.removeElement(capitalLetter);
            }
            int length = reactPolymer(chars3);
            if(length < curmin) {
                curmin = length;
            }


        }
        System.out.println(curmin);


    }
}
