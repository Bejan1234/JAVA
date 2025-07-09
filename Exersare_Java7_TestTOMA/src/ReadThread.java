import java.io.*;
import java.util.List;

public class ReadThread implements Runnable {

    private List<Airplane> sharedList;
    private String numeFisier;

    public ReadThread(List<Airplane> sharedList, String numeFisier) {
        this.sharedList = sharedList;
        this.numeFisier = numeFisier;
    }

    @Override
    public void run() {
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(numeFisier)))) {
            List<Airplane> list = (List<Airplane>) ois.readObject();
            sharedList.addAll(list);
            System.out.println(list);
            System.out.println("Citire completă din fișier: " + numeFisier);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
