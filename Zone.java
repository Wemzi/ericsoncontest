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
 

public class Zone {
    private ArrayList<ArrayList<Area>> field = new ArrayList<ArrayList<Area>>();
    

    /*Ez most létrehozza a pályát, NxM-es és feltölti, azt kell kitalálni hogy mi legyen az alap beállítás az egyes Areak-ra meg a population random hogy legyen, stb stb stb, 
    ez az area konstruktorába kell, */
    public Zone(int n, int m) throws IOException{
        ArrayList<Area> res = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                String line = reader.readLine();
                String[] splitted = line.split(" ");
               res.add(new Area(Integer.parseInt(splitted[1]),
                                Integer.parseInt(splitted[2]),
                                Integer.parseInt(splitted[3]),
                                Integer.parseInt(splitted[5]),
                                Integer.parseInt(splitted[6])));
            }

        field.add(new ArrayList<Area>(res));
        res.clear();
        }
    }
    public ArrayList<ArrayList<Area>> getField(){
        return field;
    }

    public void infection(int curr_tick, Coordinates coord, FactorGenerator factor2, FactorGenerator factor3, FactorGenerator factor4)
    {
        
        int i = getAverage(createListFromOneToN(Math.min((int) factor2.randFact() % 10 + 10,curr_tick)));
        int t = factor3.hashCode() % 7 + 3;

        Pair c = new Pair(coord, getNeighbours(field.get(coord.column).get(coord.row)));
        
        Math.ceil(i, infection(curr_tick - i , coord, factor2, factor3, factor4)   );
        
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
        else if(that.getRow() == 0){
            ret.add(field.get(that.getRow()).get(that.getCol()-1));
            ret.add(field.get(that.getRow()).get(that.getCol()+1));
            ret.add(field.get(that.getRow()+1).get(that.getCol()));
        }
        else if(that.getRow() == max_row){
            ret.add(field.get(that.getRow()).get(that.getCol()-1));
            ret.add(field.get(that.getRow()).get(that.getCol()+1));
            ret.add(field.get(that.getRow()-1).get(that.getCol()));
        }
        else{
            ret.add(field.get(that.getRow()).get(that.getCol()-1));
            ret.add(field.get(that.getRow()).get(that.getCol()+1));
            ret.add(field.get(that.getRow()-1).get(that.getCol()));
            ret.add(field.get(that.getRow()+1).get(that.getCol()));
        }
        return ret;

    }
}

/* infection(curr_tick, coord) => ceil((avg(i = [1 .. mini(factor2() % 10 + 10, curr_tick)], infection(curr_tick - i, coord)) + 
    sum(c = [coord, neighbours(coord)]; t = factor3() % 7 + 3, tick_info[curr_tick-1, c].infection_rate > 
        (start_info[coord].district != start_info[c].district ? 2 : 
            coord != c ? 1 : 0) * t ? 
       clamp(start_info[coord].population - start_info[c].population, 0, 2) + 1 : 0)) * (factor4() % 25 + 50) / 100.0) */ 