import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Aventura {
    private int codAventura;
    private String denumire;
    private double tarif;
    private int numarLocuri;

    public Aventura(int codAventura, String denumire, double tarif, int numarLocuri) {
        this.codAventura = codAventura;
        this.denumire = denumire;
        this.tarif = tarif;
        this.numarLocuri = numarLocuri;
    }

    public Aventura() {
    }

    public int getCodAventura() {
        return codAventura;
    }

    public void setCodAventura(int codAventura) {
        this.codAventura = codAventura;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }

    public int getNumarLocuri() {
        return numarLocuri;
    }

    public void setNumarLocuri(int numarLocuri) {
        this.numarLocuri = numarLocuri;
    }

    @Override
    public String toString() {
        return "Aventura{\n" +
                "codAventura=" + codAventura +
                ", denumire='" + denumire + '\'' +
                ", tarif=" + tarif +
                ", numarLocuri=" + numarLocuri +
                '}';
    }
    //Citire din Json
    public List<Aventura> citireJson(String numeFisier) throws FileNotFoundException {
        List<Aventura> listaAventuri = new ArrayList<>();
        try(FileInputStream fileInputStream =new FileInputStream(numeFisier)){
            JSONTokener jsonTokener = new JSONTokener(fileInputStream);
            JSONArray JSONArray = new JSONArray(jsonTokener);
            for (int i = 0; i < JSONArray.length(); i++) {
                JSONObject jsonObject = JSONArray.getJSONObject(i);
                Aventura ventura = new Aventura(jsonObject.getInt("cod_aventura"), jsonObject.getString("denumire"), jsonObject.getDouble("tarif"),jsonObject.getInt("locuri_disponibile"));
                listaAventuri.add(ventura);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listaAventuri;
    }


}