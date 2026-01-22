import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        LocalDate ldt = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");

        System.out.println(ldt.format(formatter));
    }
}
