import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Rezervare {
     private String codRezervare;
     private int CodAventura;
     private int NumarLocuriSolicitate;

    public Rezervare(String codRezervare, int codAventura, int numarLocuriSolicitate) {
        this.codRezervare = codRezervare;
        CodAventura = codAventura;
        NumarLocuriSolicitate = numarLocuriSolicitate;
    }

    public Rezervare() {
    }

    public String getCodRezervare() {
        return codRezervare;
    }

    public void setCodRezervare(String codRezervare) {
        this.codRezervare = codRezervare;
    }

    public int getCodAventura() {
        return CodAventura;
    }

    public void setCodAventura(int codAventura) {
        CodAventura = codAventura;
    }

    public int getNumarLocuriSolicitate() {
        return NumarLocuriSolicitate;
    }

    public void setNumarLocuriSolicitate(int numarLocuriSolicitate) {
        NumarLocuriSolicitate = numarLocuriSolicitate;
    }

    @Override
    public String toString() {
        return "Rezervare{" +
                "codRezervare='" + codRezervare + '\'' +
                ", CodAventura=" + CodAventura +
                ", NumarLocuriSolicitate=" + NumarLocuriSolicitate +
                '}'+"\n";
    }

    public List<Rezervare> citireTxt (String numeFisier) throws FileNotFoundException {
        List <Rezervare> rezervari= new ArrayList<>();
        File file = new File(numeFisier);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] bucati = line.split(",");
            String codRezervare= bucati[0];
            int codAventura= Integer.parseInt(bucati[1]);
            int numarLocuriSolicitate= Integer.parseInt(bucati[2]);
            Rezervare rezervare = new Rezervare(codRezervare, codAventura, numarLocuriSolicitate);
            rezervari.add(rezervare);

        }
        return rezervari;

    }


}
