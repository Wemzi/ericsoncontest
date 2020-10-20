import java.util.HashMap;
public class Area { 
    private int district;
    private int infected;
    private int cured;
    private int populationLevel;
    private HashMap<Integer,Integer> countryVaccines;
    private int columnIndex;
    private int rowIndex;

    public Area (int cIndex, int rIndex){
        // Na úgy van, hogy ezeket az adatokat majd a szerver küldi el, minden area-hoz küld adatokat tehát nem kell factor
        cured = 0;
        countryVaccines = new HashMap<>();
        columnIndex=cIndex;
        rowIndex=rIndex;
    }
    //elvileg ez az országokhoz tartozó vakcina mennyiség az első integer az ország ID a második a mennyiség
}
