/* 
A szimuláció egy négyzetrácsos pályán játszódik,
 ahol a négyzetrácsok kerületekbe csoportosulnak. 
 Minden négyzetrács egy-egy logikai területet jelöl,
  ahol mérve van a vírus mennyisége. 
  Ezen területeket kell vírusmentessé tenni a csapat vakcináival, 
  hogy a teljesen megtisztított kerületeken újabb vírusgyártási lehetőséget, 
  és biztonságot nyerjetek.
 */

 /* Na itt kell létrehozni valamit ami NxM és areat tartalmaz*/
 /*  megírtam már*/
 /*Jogos, viszont ha létrehozzuk őket úgyis n*m-es ciklusban csináljuk...
  */
 import java.util.ArrayList;
 import java.io.BufferedReader; 
 import java.io.IOException; 
 import java.io.InputStreamReader; 
 import java.util.Collections;
 

public class Zone {
    private ArrayList<ArrayList<Area>> field = new ArrayList<ArrayList<Area>>();
    private int width;
    private int height;

    /*Ez most létrehozza a pályát, NxM-es és feltölti, azt kell kitalálni hogy mi legyen az alap beállítás az egyes Areak-ra meg a population random hogy legyen, stb stb stb, 
    ez az area konstruktorába kell, */
    public Zone(int n, int m){
        this.width=m;  //3
        this.height=n; //3
       
        for (int i = 0; i < height; i++){
            ArrayList<Area> res = new ArrayList<>();
            for (int j= 0; j < width; j++)
            {         
            String line = System.console().readLine();
            String[] splitted = line.split(" ");
                res.add(new Area(Integer.parseInt(splitted[1]),
                                 Integer.parseInt(splitted[2]),
                                 Integer.parseInt(splitted[3]),
                                 Integer.parseInt(splitted[4]),
                                 Integer.parseInt(splitted[5])));             
            }

        field.add(new ArrayList<Area>(res));
        }
    }
    public ArrayList<ArrayList<Area>> getField(){
        return this.field;
    }

    public void infection(int curr_tick, Area actual, FactorGenerator factor2,FactorGenerator factor3, FactorGenerator factor4) //Ez viszaad egy számot, azt csinálja, hogy megkap egy areat és kiszámolja mennyi lesz az új fertőzöttségi ráta, majd beállítja az area új rátáját
    {
        
        int newInfectionRate = getAverage(actual.getInfectionRates());

        newInfectionRate += (factor2.randFact() % 10);
        
        newInfectionRate += getInfectionWillingness(actual, factor3);

        newInfectionRate *= (factor4.randFact() % 25)+50 ;

        newInfectionRate /= 100;

        newInfectionRate = (int)Math.ceil(newInfectionRate);

       actual.setInfectionRate(newInfectionRate);
        
    }

    public int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }

    public int getInfectionWillingness(Area actual, FactorGenerator factor3)
    {
        ArrayList<Area> c = getNeighbours(actual);
        c.add(actual);
        int infWillness = 0;
        for (Area that : c) {
            long t = factor3.randFact() % 7 + 3;
            int prevInfection = actual.getInfectionRates().get(actual.getInfectionRates().size()-1);
            int distance = (actual.equals(that) ? 0 : actual.getDistrict()==that.getDistrict() ? 1 : 2);
            int popDiff = actual.getPop() - that.getPop(); //start_info[coord].population - start_info[c].population, 0, 2 evInfection > (t*distance)){
        if (prevInfection > (t*distance)){
            infWillness += clamp(actual.getPop()-that.getPop(),0,2)+1;
            }
        }
        return infWillness;
    }


    public void heal(int curr_tick, Area that, FactorGenerator factor1){
        if(that.getCured() != 0){
            if(curr_tick > this.width+this.height){
                int cure = (int)(Math.floor((Collections.min(that.getInfectionRates()) * (factor1.randFact() % 10)) / 20.0));
                that.setCured(cure);
            }
        }
    }
    
    public ArrayList<Integer> createListFromOneToN(int n)
    {
        ArrayList <Integer> res = new ArrayList<Integer>();
        for(int idx=1;idx<=n;idx++)
        {
            res.add(idx);
        }
        return res;
    }

    public int getAverage(ArrayList<Integer> numbers)
    {
        int sum=0;
        for(int idx=0; idx<numbers.size(); idx++)
        {
            sum+= numbers.get(idx);
            
        }
        return sum;
    }
    
    public ArrayList<Area> getNeighbours(Area that){
        ArrayList<Area> ret = new ArrayList<>();
        int max_col= field.get(0).size()-1;
        int max_row= field.size()-1;
        
        if(that.getCol() == 0 && that.getRow()== 0){ //balfelso
            ret.add(field.get(0).get(1)); 
            ret.add(field.get(1).get(0));
        }
        else if(that.getCol() == max_col && that.getRow() == max_row){ //jobbalso
            ret.add(field.get(max_row).get(max_col-1));
            ret.add(field.get(max_row-1).get(max_col));
        }
        else if(that.getCol() == max_col && that.getRow() == 0){ //jobbfelso
            ret.add(field.get(0).get(max_col-1));
            ret.add(field.get(1).get(max_col));
        }
        else if(that.getCol() == 0 && that.getRow() == max_row){ //bal also
            ret.add(field.get(max_row-1).get(0));
            ret.add(field.get(max_row).get(1));
        }
        else if(that.getCol() == 0){ //bal szel
            ret.add(field.get(that.getRow()+1).get(that.getCol()));
            ret.add(field.get(that.getRow()-1).get(that.getCol()));
            ret.add(field.get(that.getRow()).get(that.getCol()+1));
        }
        else if(that.getCol() == max_col){ //jobb szel
            ret.add(field.get(that.getRow()+1).get(that.getCol()));
            ret.add(field.get(that.getRow()-1).get(that.getCol()));
            ret.add(field.get(that.getRow()).get(that.getCol()-1));
        }
        else if(that.getRow() == 0){ //felso sor
            ret.add(field.get(that.getRow()).get(that.getCol()-1));
            ret.add(field.get(that.getRow()).get(that.getCol()+1));
            ret.add(field.get(that.getRow()+1).get(that.getCol()));
        }
        else if(that.getRow() == max_row){ //also sor
            ret.add(field.get(that.getRow()).get(that.getCol()-1));
            ret.add(field.get(that.getRow()).get(that.getCol()+1));
            ret.add(field.get(that.getRow()-1).get(that.getCol()));
        }
        else{ //minden mas
            ret.add(field.get(that.getRow()).get(that.getCol()-1));
            ret.add(field.get(that.getRow()).get(that.getCol()+1));
            ret.add(field.get(that.getRow()-1).get(that.getCol()));
            ret.add(field.get(that.getRow()+1).get(that.getCol()));
        }
        return ret;

    }
}