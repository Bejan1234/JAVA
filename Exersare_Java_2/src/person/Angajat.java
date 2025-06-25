package person;

//extends -> folosit pentru a marca faptul ca Angajat mosteneste clasa Person(relatie is-a): angajatul este o persoana
//clasa Angajat este o clasa copil a clasei Person (se mai numeste subclasa, clasa copil, clasa mostenita)
//clasa Person este o clasa parinte a clasei Angajat(se mai numeste super clasa, clasa de baza, clasa din care mostenim)
//mai intai mostenim cu extends apoi implementam interfetele cu "implements"
// o clasa poate mosteni doar o singura alta clasa! dar poate implementa 1 sau mai multe interfete in acelasi timp

//intrebare interviu: de ce nu putem mosteni mai multe clase in acelasi timp?
//rasp: apare problema diamantului (romb): daca o clasa va mosteni 2 clase care ambele o metoda cu aceeasi semnatura atunci clasa copil nu va sti pe care metoda sa o supradefineasca(din ce clasa sa supradefineascca metoda pentru ca sunt identice!)
//nu exista mostenire multipla in java!
//daca in clasa de baza nu avem niciun constructor default implementat chiar daca avem cu param, atunci clasa copil este obligata sa mosteneasca constructorul cu toti param din clasa de baza
public class Angajat extends Person implements ISkills {

    private double salariu;
    private String numeCompanie;
    private int aniVechime;
    private Departamente departament; //relatie has-a

    //in java existe 3 mari tipuri referentiale (care genereaza obiecte): CLASA, INTERFATA, ENUM !!!

    //mostenirea constructorilor -> se foloseste keyword-ul super()...
    public Angajat() {
        super(); //marchez mostenirea explicita a constructorului default din clasa de baza(Person)
    }

    public Angajat(String nume, int varsta, char gen, float inaltime, double salariu, String numeCompanie, int aniVechime, Departamente departament) {
        super(nume, varsta, gen, inaltime);
        this.salariu = salariu;
        this.numeCompanie = numeCompanie;
        this.aniVechime = aniVechime;
        this.departament = departament;
    }

    @Override
    public int calculCoeficient() {
        if (salariu > 5000 && salariu < 10000) {
            return 2;
        } else if (salariu >= 10000) {
            return 1;
        } else return 0;
    }

    @Override
    public void maresteSalariu() {
        if (aniVechime <= 2) {
            this.salariu += this.salariu * 0.2; //crestem salariul cu 20% daca vechimea este de maxim 2 ani
        } else if (aniVechime > 3) {
            this.salariu += this.salariu * 0.25;//crestem salariul cu 25% daca vechimea este peste 3 ani
        } else this.salariu += this.salariu * 0.1; //altfel crestem cu 10%
    }

    //metoda equals() este folosita pentru a compara 2 obiecte: clase, interfete, enum
    @Override
    public double getPremiu() {
        if (this.aniVechime >= 2 && this.departament.equals(Departamente.IT)) {
            return 2585.90;
        } else if (this.aniVechime > 5 && this.salariu < 10000 && this.departament.equals(Departamente.MK)) {
            return 568.82;
        } else return 0;
    }

    @Override
    public String toString() {
        return super.toString() +
                "salariu=" + salariu +
                ", numeCompanie='" + numeCompanie + '\'' +
                ", aniVechime=" + aniVechime +
                ", departament=" + departament +
                '}' + "\n";
    }

}
