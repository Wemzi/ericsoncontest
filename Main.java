import java.util.*;
import java.util.ArrayList;
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 


class Main { 

  

public static void main(String[] args) throws IOException {
   BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
   try{
   String[] line = reader.readLine().split(" ");
      int game_id = Integer.parseInt(line[1]);
      int max_tick_sim = Integer.parseInt(line[2]);
      int countries_count = Integer.parseInt(line[3]);
   line=reader.readLine().split(" ");
      FactorGenerator factor1 = new FactorGenerator(Integer.parseInt(line[1]));
      FactorGenerator factor2 = new FactorGenerator(Integer.parseInt(line[2]));
      FactorGenerator factor3 = new FactorGenerator(Integer.parseInt(line[3]));
      FactorGenerator factor4 = new FactorGenerator(Integer.parseInt(line[4]));
   line=reader.readLine().split(" ");
      int rows = Integer.parseInt(line[1]);
      int columns = Integer.parseInt(line[2]);
   Zone zone = new Zone(rows,columns);
   } catch (IOException e){System.out.println(e.getMessage());}
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