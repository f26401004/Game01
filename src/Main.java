import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class Main{
  public static void main(String[] args){
    try{
      ArrayList<Integer> meteoriteList = new ArrayList<Integer>();
      int n = 0;
      Scanner scan = new Scanner(System.in);
      File file = new File("map.txt");
      scan = new Scanner(file);
      while(scan.hasNextLine() && n < 10){
        String s = scan.nextLine();
        // System.out.println(s);
        for(int i=0; i<s.length(); i += 2){
          String ss;
          ss = s.substring(i,i+1);
          System.out.println(ss);

          if(ss.equals(" ") || ss.equals("0")){
            meteoriteList.add(87878787);
            // System.out.println("IF");
          }
          else{
            Random rand = new Random();
    			  meteoriteList.add(rand.nextInt(4) + 1);
            // System.out.println("ELSE");
          }
        }
        n++;
      }
      System.out.println(meteoriteList);
    }
    catch(Exception e){

    }
  }
}
