import java.util.ArrayList;



class Main { 
public static void main(String[] args) {
   final String teamToken = "AYHsGhb17k";
   //final int seed = 0;
        
        System.out.println("START " + teamToken
            // + " " + seed
        + "\n.");
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

   boolean isRequestDone = true;
   int neededTick=0;
   ArrayList<String> lineSplitted = new ArrayList<>();
   String[] firstLine = System.console().readLine().split(" ");
   System.console().readLine();
   if(Integer.parseInt(firstLine[2])==0){
      for (ArrayList<Area> x : zone.getField()){
         for (Area actual : x) 
         {
            System.out.print(actual.getinfectionRate()+ " ");
         }
         System.out.println();
      }
      System.out.println(".");      
      }
   else
   {
      isRequestDone=!isRequestDone;
      neededTick=Integer.parseInt(firstLine[2]);
      lineSplitted = new ArrayList<>();
      for (String tmp : firstLine)
      {
         lineSplitted.add(tmp);
      }
   }


  
   for(int tick=1; tick<max_tick_sim+1; tick++)
   {
      if(isRequestDone)
      {
      String[] lines = System.console().readLine().split(" ");
      lineSplitted = new ArrayList<>();
      for (String tmp : lines)
      {
         lineSplitted.add(tmp);
      }
      neededTick=Integer.parseInt(lineSplitted.get(2));
      System.console().readLine();
      isRequestDone = !isRequestDone;
      }
      
      if(neededTick==tick){System.out.println(String.format("RES %d %d %d", Integer.parseInt(lineSplitted.get(1)), 
      Integer.parseInt(lineSplitted.get(2)), Integer.parseInt(lineSplitted.get(3)))); isRequestDone=!isRequestDone;}

      for (ArrayList<Area> x : zone.getField())
      {
         for (Area actual : x) 
         {
            if(tick==0) break;
            zone.infection(tick, actual, factor2, factor3, factor4);
            zone.heal(tick, actual, factor1);
            if (neededTick==tick) {System.out.print(actual.getinfectionRate()+ " "); }
         }
         if (neededTick==tick) System.out.println();
      }
      if(neededTick==tick)System.out.println(".");

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