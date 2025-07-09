import java.io.*;
import java.util.List;

public class WriteThread implements Runnable{

    private List<Airplane> airplanes;
    private String numeFisier;

    public WriteThread(List<Airplane> airplanes, String numeFisier) {
        this.airplanes = airplanes;
        this.numeFisier = numeFisier;
    }

    @Override
    public void run() {

        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(numeFisier)))) {

            objectOutputStream.writeObject(airplanes);
            System.out.println("Scirere in fisierul: " + numeFisier);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}