
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class ComputerProject {

   public static void main(String[] args) {
       int n = 20; 
       int r = 5; 
       double p = 0.1; 
       int day = 0;
       
       int[] computers = new int[n];
       computers[0] = 1;
       int totalInfectedComputer = 1;
       for (int i = 1; i < n; i++) {
           computers[i] = 0;
       }
       System.out.println("Start State: " + Arrays.toString(computers));
       System.out.println();

       while (infectionExists(computers)) {
           day++; 
           
           for (int i = 0; i < n; i++) {
               for (int j = 0; j < n; j++) {
                   if ((computers[i] == 1) && (computers[j] == 0)) {
                       int x = MyBernoulli(p);
                       if (x == 1) {
                           computers[j] = 2;
                           totalInfectedComputer++;
                       }
                   }
               }
           }
           int infectionsToday = 0;
           List <Integer> systemsInfectedToday = new ArrayList<Integer>();
           for (int i = 0; i < n; i++) {
               if (computers[i] == 2) {
                   computers[i] = 1;
                   infectionsToday++;
                   systemsInfectedToday.add(i);
               }
           }
           System.out.println("Day " + day + ":" + Arrays.toString(computers));
           System.out.println("New Infections on Day" + day + " :" + infectionsToday);
          
           fixInfections(computers, r);
           System.out.println("*****************");
       }
      
       System.out.println("Summary:");
       System.out.println("Total Systems Infected: "+ totalInfectedComputer);
       System.out.println("Number of days taken to kill infection: "+ day);
       System.out.println("Average number of computers infected per day: "+ (float)totalInfectedComputer/day);
   }

   private static void fixInfections(int[] computers, int r) {
       int fixedComputerCount = 0;
       for (int i = 0; i < computers.length && fixedComputerCount < r; i++) {
           if (computers[i] == 1) {
               System.out.println("Technician fixed computer number : " + i);
               fixedComputerCount ++;
               computers[i] = 0;
           }
       }
      
   }

   private static boolean infectionExists(int[] computers) {
       for (int i = 0; i < computers.length; i++) {
           if (computers[i] == 1)
               return true;
       }
       return false;
   }

   public static int MyBernoulli(double p) {
       double U = Math.random();
       int X;
       if (U < p) {
           X = 1;
       } else {
           X = 0;
       }
       return X;
   }

}