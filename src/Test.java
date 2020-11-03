import java.util.*;

public class Test {
    public static void main(String args[]){
        //String no = "E::=    T     |\t\tE\t\tOP     T    ";
        //String yes = "<s>::=<np> <vp>";
        //System.out.println(yes);

        //String no = "T::=   x    |   y   |   42  | 0 | 1 | 92 | ( E ) | F1 ( E ) | - T | F2 ( E , E )";
        String no = "";

        Scanner scan1 = new Scanner(no);
        scan1.useDelimiter("::=");
        List<String> tester = new ArrayList<>();
        if(no.length()==0){
            throw new IllegalArgumentException();
        }
        while(scan1.hasNext()){
            String front = scan1.next().trim();
            System.out.println(front);
            String back = scan1.next().trim();

            Scanner scan2 = new Scanner(back);
            scan2.useDelimiter("\t\t |\\|");

            while(scan2.hasNext()){
                tester.add(scan2.next().trim());
            }

        }
        System.out.print(tester);

    }

}
