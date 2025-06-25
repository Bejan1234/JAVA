package person;

//clasa abstracta:
//o clasa poate fi marcata abstracta, atunci nu vom putea crea obiecte de tipul clasei! adica nu poate fi instantiata
//o clasa care are cel putin o medota abstracta atunci este obligatoriu abstracta! dar daca n-are e alegerea noastra daca o facem abstracta sau nu
//metodele abstracte nu au corp, au doar semnatura (modificator de acces (private, protected, public..) + tipul de return + nume + () in care putem avea param.
//clasele ce vor mosteni aceasta clasa abstracta vor trebui sa supradefineasca (override) metodele abstracte, daca nu o vor face, vor fi marcate obligatoriu ca abstracte
public abstract class Person {
    private String nume;
    private int varsta;
    private char gen;
    private float inaltime;

    public Person() {
    }

    public Person(String nume, int varsta) {
        this.nume = nume;
        this.varsta = varsta;
    }
// cum putem apela un constructor in alt constructor? cu this(parametri)
    public Person(String nume, int varsta, char gen, float inaltime) {
//        this.nume = nume;
//        this.varsta = varsta;
        this(nume, varsta); //apelul constructorului cu nume si varsta de mai sus
        this.gen = gen;
        this.inaltime = inaltime;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public float getInaltime() {
        return inaltime;
    }

    public void setInaltime(float inaltime) {
        this.inaltime = inaltime;
    }

    public char getGen() {
        return gen;
    }

    public void setGen(char gen) {
        this.gen = gen;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    //afiseaza campurile obiectului la consola, daca nu avem supradefinita aceasta metoda,
    //atunci se va afisa referinta din memorie a obiectului
    @Override
    public String toString() {
        return "Person{" +
                "nume='" + nume + '\'' +
                ", varsta=" + varsta +
                ", gen=" + gen +
                ", inaltime=" + inaltime +
                '}' + "\n";
    }

    public abstract int calculCoeficient();
}
