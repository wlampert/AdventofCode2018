package day4;

public class Gaurd {
    public int[] minutes = new int[60];
    public int id;
    public int totalMinutes;

    public Gaurd(int x) {id = x;}

    public void addMinutes(int start, int end) {
        totalMinutes += end - start;
        for(int i = start; i<=end; i++) {
            minutes[i] ++;
        }
    }

    public int findMostAsleepMinute() {
        int max = 0;
        int returnValue = 0;
        for(int i = 0; i<60; i++) {
            if(minutes[i] > max) {
                max = minutes[i];
                returnValue = i;
            }
        }
        return returnValue;
    }

}
