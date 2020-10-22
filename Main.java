import java.util.*;
import java.util.ArrayList;
import java.io.BufferedReader; 
import java.io.InputStreamReader; 


class Main { 
public static void main(String[] args) {
   String[] line = System.console().readLine().split(" ");
      int game_id = Integer.parseInt(line[1]);
      int max_tick_sim = Integer.parseInt(line[2]);
      int countries_count = Integer.parseInt(line[3]);
   line=System.console().readLine().split(" ");
      FactorGenerator factor1 = new FactorGenerator(Integer.parseInt(line[1]));
      FactorGenerator factor2 = new FactorGenerator(Integer.parseInt(line[2]));
      FactorGenerator factor3 = new FactorGenerator(Integer.parseInt(line[3]));
      FactorGenerator factor4 = new FactorGenerator(Integer.parseInt(line[4]));
   line=System.console().readLine().split(" ");
      int rows = Integer.parseInt(line[1]);

      int columns = Integer.parseInt(line[2]);

      Zone zone = new Zone(rows,columns);

   for(int tick=0; tick<max_tick_sim; tick++){
      for (ArrayList<Area> x : zone.getField()){
         for (Area actual : x) 
         {
            zone.infection(tick, actual, factor2, factor3, factor4);
            zone.heal(tick, actual, factor1);
            System.out.print(actual.getinfectionRate() + " ");
         }
      }
   }
}
}


class FactorGenerator {
    
   private long factor;

   public FactorGenerator(long factor)
   {
       this.factor = factor;
   }

   public long randFact()
   {
       factor = (factor * 48271 % 0x7fffffff);
       return factor;
   }
}