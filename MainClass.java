/*import java.util.*;
import java.util.stream.*;


class Area {
    int district;
    int infectionRate;
    int healthRate;
    int population;

    Area(Scanner scanner) {
        this.district = scanner.nextInt();
        this.infectionRate = scanner.nextInt();
        this.population = scanner.nextInt();
    }
}

class Reader {
    int[] data = new int[]{0, 0, 0};
    int[] factors = new int[]{0, 0, 0, 0};
    int[] dimension = new int[]{0, 0};
    ArrayList<ArrayList<Area>> areas = new ArrayList<>();
    String message = "";
    boolean hasEnd = false;
    boolean needAnsw = false;
}

// a MainClass lesz az indulo
public class MainClass {
    static private Scanner inScanner = new Scanner(System.in);

    public static void readData(Reader reader) {
        String line;
        
        while(!(line = inScanner.nextLine()).isEmpty()) {
            Scanner scanner = new Scanner(line);
            String command = scanner.next();
            switch(command) {
                case ".":
                    return;
                case "WRONG":
                case "SUCCESS":
                case "FAILED":
                    reader.hasEnd = true;
                    reader.message = line;
                    break;
                case "REQ":
                    reader.needAnsw = true;
                    IntStream.range(0, 3)
                        .forEach(i -> reader.data[i] = scanner.nextInt());
                    break;
                case "START":
                    reader.needAnsw = false;
                    IntStream.range(0, 3)
                        .forEach(i -> reader.data[i] = scanner.nextInt());
                    break;
                case "FACTORS":
                    IntStream.range(0, 4)
                        .forEach(i -> reader.factors[i] = scanner.nextInt());
                    break;
                case "FIELDS":
                    IntStream.range(0, 2)
                        .forEach(i -> reader.dimension[i] = scanner.nextInt());
                    IntStream.range(0, reader.dimension[0])
                        .forEach(i -> {
                            ArrayList<Area> row = new ArrayList<>();
                            IntStream.range(0, reader.dimension[1]).
                                forEach(j -> row.add(null));
                            reader.areas.add(row);
                        });
                    break;
                case "FD":
                    int y = scanner.nextInt(), x = scanner.nextInt();
                    reader.areas.get(y).set(x, new Area(scanner));
                    break;
                default:
                    System.err.println("READER ERROR HAPPENED: unrecognized command line: " + line);
                    reader.hasEnd = true;
                    return;
            }
        }
        System.err.println("Unexpected input end\n");
        reader.hasEnd = true;
    }

    public static void main(String[] args) {
        final String teamToken = "AYHsGhb17k";
        // final int seed = 0;
        
        System.out.println("START " + teamToken
            // + " " + seed
        + "\n.");
        
        Reader reader = new Reader();

        while(true) {
            readData(reader);
        
            if (reader.hasEnd)
                break;
            if (!reader.needAnsw)
                continue;
            
            // Ha szeretnetek debug uzenetet kuldeni, akkor megtehetitek.
            // Maximalisan csak 1024 * 1024 bajtot!
            System.err.println("Send response");
            
            // standard out-ra meg mehet ki a megoldas! Mas ne irodjon ide ki ;)
            System.out.println(String.format("RES %d %d %d", reader.data[0], 
                reader.data[1], reader.data[2]));
            for (ArrayList<Area> row : reader.areas) {
                for (Area area : row) {
                    System.out.print(String.format("%d ", area.infectionRate));
                }
                System.out.println();
            }
            System.out.println(".");
        }
        System.err.println(String.format("END (message): %s", reader.message));
    }
}
*/