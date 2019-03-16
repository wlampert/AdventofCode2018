package day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class day4 {
    private static final String input = "/Users/WillyLampert/Desktop/AdventOfCode/inputs/day4input.txt";

    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader in = new BufferedReader(new FileReader(input));
        PriorityQueue<Entrie> queue = new PriorityQueue<>();
        Vector<String> sortedInput = new Vector<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        String input;

        //Create an entry object for each line of the input and add to a minheap
        while((input = in.readLine()) != null) {
            String date = input.substring(1,17);
            Date d = sdf.parse(date);
            Entrie e = new Entrie(input, d);
            queue.add(e);
        }

        //Remove every item from the minheap and add to a vector list of the input in sorted order
        while(queue.size() > 0) {
            Entrie x = queue.remove();
            sortedInput.add(x.entrie);
        }

        //At this point the entries are sorted in chronological order
        //Now go through the entries and create the gaurd object and add their minutes
        boolean a = true;
        int startTime = 0;
        int endTime;
        int id = 0;
        Gaurd currentGaurd = new Gaurd(0);
        Hashtable<Integer, Gaurd> hashtable = new Hashtable<>();
        for(String i: sortedInput) {
            System.out.println(i);
            if(i.charAt(19) == 'G') {
                //System.out.println(currentGaurd.totalMinutes);
                hashtable.put(id, currentGaurd);
                if(i.charAt(29) == ' ') {
                    id = Integer.parseInt(i.substring(26, 29));
                }
                else {
                    id = Integer.parseInt(i.substring(26,30));
                }

                if(!hashtable.containsKey(id)) {
                    Gaurd g = new Gaurd(id);
                    hashtable.put(id, g);
                }
                currentGaurd = hashtable.get(id);
            }
            else {
                if (a) {
                    startTime = Integer.parseInt(i.substring(15, 17));
                    a = false;
                } else {
                    endTime = Integer.parseInt(i.substring(15, 17)) - 1;
                    currentGaurd.addMinutes(startTime, endTime);
                    a = true;
                }
            }
        }


        //Now go through each gaurd and find the one with the most minutes, print out id * chosen minute - part 1
        Set<Integer> ids = hashtable.keySet();
        Gaurd gaurd;
        int curMax = 0;
        int bestId = 0;
        for(int x: ids) {
            gaurd = hashtable.get(x);
            if(gaurd.totalMinutes > curMax) {
                curMax = gaurd.totalMinutes;
                bestId = x;
            }
        }

        Gaurd bestGaurd = hashtable.get(bestId);
        System.out.println(bestId * bestGaurd.findMostAsleepMinute());
        System.out.println(bestId + " : " + bestGaurd.totalMinutes);

        //Now go through each gaurd and find the one with the most slept minute, print out id * chosen minute - part 2
        curMax = 0;
        for(int x: ids) {
            gaurd = hashtable.get(x);
            if(gaurd.minutes[gaurd.findMostAsleepMinute()] > curMax) {
                curMax = gaurd.minutes[gaurd.findMostAsleepMinute()];
                bestId = x;
            }
        }

        bestGaurd = hashtable.get(bestId);
        System.out.println(bestId * bestGaurd.findMostAsleepMinute());
        System.out.println(bestId + " : " + bestGaurd.totalMinutes);



    }
}



/**
 [1518-11-08 00:02] Guard #2851 begins shift
 [1518-02-28 00:43] falls asleep
 [1518-06-24 00:52] wakes up
 [1518-06-08 00:29] falls asleep
 [1518-09-18 00:30] falls asleep
 **/
