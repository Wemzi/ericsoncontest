import java.util.HashMap;
import java.util.ArrayList;

public class Area { 
    private int district;
    private int infectionRate;
    private int cured;
    private int populationLevel;
    private HashMap<Integer,Integer> countryVaccines;
    private int columnIndex;
    private int rowIndex;
    private ArrayList<Integer> prevInfectionRates = new ArrayList<>();

    public void updateInfectionRates(int newRate){
        if(this.prevInfectionRates.size()==10)
        {
            this.prevInfectionRates.add(0,newRate);
            this.prevInfectionRates.remove(this.prevInfectionRates.size()-1);
        }
    }
    
    public ArrayList<Integer> getInfectionRates(){return this.prevInfectionRates;}
    public int getCured(){return this.cured;}
    public Coordinates getCoord(){return new Coordinates(columnIndex,rowIndex);}
    public int getRow(){return this.rowIndex;}
    public int getCol(){return this.columnIndex;}

    public Area (int cIndex, int rIndex, int district, int infectionRate, int populationLevel)
    {
        this.cured = 0;
        this.countryVaccines = new HashMap<>();
        this.columnIndex=cIndex;
        this.rowIndex=rIndex;
        this.district=district;
        this.infectionRate=infectionRate;
        this.populationLevel=populationLevel;
        this.prevInfectionRates.add(infectionRate);
        //Tudom hogy nem kell a this DAVID, de akkor is odairom mert: 1 jólesik, 2 jobban néz ki
    }

    public void setCured(int cured){
        this.cured+=cured;
        if(this.cured+this.infectionRate > 100)
        {
            this.infectionRate = 100-this.cured;
        }
    }

    public void setInfectionRate(int newInf){
       this.infectionRate = newInf;
       if(this.cured+this.infectionRate > 100)
        {
        this.cured = 100-this.infectionRate;
        }
    }
    public int getPop()
    {
        return this.populationLevel;
    }

    public int getDistrict()
    {
        return district;
    }

    public int getinfectionRate()
    {
        return infectionRate;
    }

   


    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Area)
        {
            return super.equals(obj) && this.columnIndex == ((Area)obj).columnIndex && this.rowIndex == ((Area)obj).rowIndex;
        }
        else return false;
        
    }
    //elvileg ez az országokhoz tartozó vakcina mennyiség az első integer az ország ID a második a mennyiség
}


 
/*infection(curr_tick, coord) => ceil(=(avg(i = [1 .. mini(factor2() % 10 + 10, curr_tick)], infection(curr_tick - i, coord)) + 
    sum(c = [coord, neighbours(coord)]; t = factor3() % 7 + 3, tick_info[curr_tick-1, c].infection_rate > 
        (start_info[coord].district != start_info[c].district ? 2 : 
            coord != c ? 1 : 0) * t ? 
       clamp(start_info[coord].population - start_info[c].population, 0, 2) + 1 : 0)) * (factor4() % 25 + 50) / 100.0)

       */