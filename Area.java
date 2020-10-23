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
    private int neededArraySize;
    private int widthHeight;

    public void updateInfectionRates(int newRate){

        if(this.prevInfectionRates.size()==neededArraySize)
        {
            this.prevInfectionRates.add(0,newRate);
            this.prevInfectionRates.remove(this.prevInfectionRates.size()-1);
        }
        else{
            this.prevInfectionRates.add(0,newRate);
        }
    }

    public ArrayList<Integer> infectionRatesToTen(){
        if(this.prevInfectionRates.size()<10){return this.prevInfectionRates;}
        else{
        ArrayList<Integer> ret = new ArrayList<>();
        for(int i = 0; i<10; i++){
            ret.add(this.prevInfectionRates.get(i));
        }
        return ret;
        }
    }

    public ArrayList<Integer> infectionRatesToWidthAndHeight(){
        ArrayList<Integer> ret = new ArrayList<>();
            for(int i=0; i<widthHeight; i++){
                ret.add(this.prevInfectionRates.get(i));
            }
            return ret;
        }
    
    public ArrayList<Integer> getInfectionRates(){return this.prevInfectionRates;}
    public int getCured(){return this.cured;}
    public Coordinates getCoord(){return new Coordinates(columnIndex,rowIndex);}
    public int getRow(){return this.rowIndex;}
    public int getCol(){return this.columnIndex;}

    public Area (int cIndex, int rIndex, int district, int infectionRate, int populationLevel, int arrsize)
    {
        this.cured = 0;
        this.countryVaccines = new HashMap<>();
        this.columnIndex=cIndex;
        this.rowIndex=rIndex;
        this.district=district;
        this.infectionRate=infectionRate;
        this.populationLevel=populationLevel;
        this.prevInfectionRates.add(infectionRate);
        if(arrsize > 10){neededArraySize = arrsize;}
        else {neededArraySize=10;}
        widthHeight=arrsize;
        //Tudom hogy nem kell a this DAVID, de akkor is odairom mert: 1 jólesik, 2 jobban néz ki
    }

    public void setCured(int cured){
        this.cured+=cured;
        if(this.cured > 100)
        {
            this.cured = 100;
        }
    }

    public void setInfectionRate(int newInf){
        if(this.cured+(this.infectionRate+newInf) > 100){
            this.infectionRate += 100-this.infectionRate-this.cured;
        }
        else{this.infectionRate+=newInf;}
        this.updateInfectionRates(newInf);
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
}