

public class FactorGenerator {
    
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
    // Az eleve long amit ott kapsz ha nem     // ha nem válEZt írtéák, h mindig egyenlővé kell tenni és így változik a fact-od, így lesz random generltált
    // itt msot kétszer változtatod sztem ez így jó én szerintem