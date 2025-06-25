package person;

import java.util.ArrayList;
import java.util.List;

//manager va mosteni si clasa angajat dar si clasa person (lant de mostenire)

//Lista vs vector
//Vectorul are dimensiune fixa, trebuie declarata in momentul in care creem vectorul, pe cand lista are dimensiune dinamica
//adica se redimensioneaza automat pe masura ce adaugam un nou element (folosim metoda add() )
//vectorii pot stoca variabile de tip primitiva (int, char, boolean, float, double, short, byte, long) dar si obiecte
//pe cand listele pot stoca doar obiecte!!! nu si primitive! (se folosesc clase wrapper -> Integer, Character, String, Double, Float, Boolean...)

//o clasa container ce stocheaza liste de obiecte!!!
public class Manager extends Angajat{
    private int nrAngajatiSubordine;
    private List<Angajat> listaAngajati ;//lista de angajati
    private List<String> listaProiecteInCoordonare;

    public Manager() {
    }

    public Manager(String nume, int varsta, char gen, float inaltime, double salariu, String numeCompanie, int aniVechime, Departamente departament) {
        super(nume, varsta, gen, inaltime, salariu, numeCompanie, aniVechime, departament);
        nrAngajatiSubordine = 0;
        listaAngajati = new ArrayList<>();//initializam listele!!!
        listaProiecteInCoordonare = new ArrayList<>();
    }

    public void adaugaAngajat(Angajat angajat){

        listaAngajati.add(angajat);
    }

    public void adaugaProiect(String proiectNou){

        listaProiecteInCoordonare.add(proiectNou);
    }

    @Override
    public String toString() {
        return super.toString() +
                "nrAngajatiSubordine=" + nrAngajatiSubordine +
                ", listaAngajati=" + listaAngajati +
                ", listaProiecteInCoordonare=" + listaProiecteInCoordonare +
                '}' + "\n";
    }
}
