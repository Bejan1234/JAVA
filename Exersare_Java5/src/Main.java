import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Aventura aventura = new Aventura();
        List<Aventura> lista = new ArrayList<Aventura>();
        lista = aventura.citireJson("date/aventuri.json");
        System.out.println(lista);

        System.out.println("+++++++++++++++++++");

        List<Aventura> listaAventuriPeste20Loc= lista.stream().filter(x -> x.getNumarLocuri()>=20).toList();
        System.out.println(listaAventuriPeste20Loc);

        Rezervare rezervare = new Rezervare();
        List<Rezervare> listaRezervari = rezervare.citireTxt("date/rezervari.txt");
        System.out.println(listaRezervari);

        //2
        lista.forEach(x->{
            int locuriSolicitate = listaRezervari.stream().filter(y->y.getCodAventura()==x.getCodAventura()).mapToInt(Rezervare::getNumarLocuriSolicitate).sum();
            int locuriRamase = x.getNumarLocuri()-locuriSolicitate;
            if(locuriRamase>=5){
                System.out.printf("%d %s %d \n",x.getCodAventura(),x.getDenumire(),locuriRamase);

            }
        });

    }



}
