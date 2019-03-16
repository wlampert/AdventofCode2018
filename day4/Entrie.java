package day4;
import java.util.Date;

public class Entrie implements Comparable<Entrie>{
    public String entrie;
    public Date date;

    public Entrie(String e, Date d) { entrie = e; date = d;}

    public int compareTo(Entrie other) {
        if(this.date.compareTo(other.date) < 0){
            return -1;
        }
        if(this.date.compareTo(other.date) == 0){
            return 0;
        }
        if(this.date.compareTo(other.date) > 0){
            return 1;
        }
        else return 0;
    }

}
