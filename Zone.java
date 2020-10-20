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
    public Zone(int n, int m){
        ArrayList<Area> res = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                /* EZ AKKOR KELL HA MÁR A SZERVER KÜLDI AZ INFÓKAT
                String line = reader.readLine();
                String[] splitted = line.split(" ");
                
                res.add(new Area(splitted[1],splitted[2],splitted[3],splitted[4],splitted[5]))
                                    col         row         distr       inf_rate    pop
                */ 

                res.add(new Area(i,j)); //Ezt tesztre használjuk
            }

        field.add(new ArrayList<Area>(res));
        res.clear();
        }
    }
    public ArrayList<ArrayList<Area>> getField(){
        return field;
    }
}
