//4. [2p] Implementează o clasă GenericPackage, folosită de un sistem de curierat, conform următoarelor detalii de implementare:
//
//        [0.5p] Starea internă este reprezentată astfel:
//uniqueID: cod unic de identificare a pachetului, format numai din cifre, de tipul String.
//        weight: greutatea coletului, de tipul double.
//packageName: numele pachetului, de tipul String.
//courierName: numele firmei de curierat, tipul String.
//[0.5p] Clasa GenericPackage trebuie să respecte următoarele criterii:
//        [0.1p] deoarece clasa va fi folosită de un singur curier, toate obiectele clasei GenericPackage vor avea același courierName.
//        [0.1p] câmpul uniqueID, unic pentru fiecare obiect GenericPackage, trebuie să nu poată fi modificat (i.e. după inițializarea acestuia).
//        [0.1p] transformă câmpul courierName, același pentru toate obiectele din clasă, într-o proprietate a obiectelor, care poate fi atât citită cât și actualizată.
//[0.1p] transformă câmpul packageName într-o proprietate read-only (i.e. poate fi citită, dar nu poate fi actualizată).
//        [0.1p] definește 2 constructori diferiți ai clasei, la alegere.
//        Hint: ține cont de criteriile anterioare în definirea corectă a constructorilor.
//        [1p] Definește următoarele metode:
//        [0.25p] addItem(double itemWeight): actualizează greutatea pachetului prin adăugarea greutății noului obiect (i.e. itemWeight) la greutatea actuală a coletului.
//        [0.5p] checkID(): metoda verifică dacă numărul pachetului este valid. Codul unic de identificare este valid dacă sunt îndeplinite toate condițiile următoare:
//codul este alcătuit doar din cifre (i.e. toate caracterele sunt cifre).
//numărul de cifre impare este multiplu de 3.
//suma tuturor cifrelor formează un număr divizibil cu 4.
//diferența dintre oricare 2 cifre consecutive din cod este mai mică decât 5.
//lungimea totală a codului este cuprinsă între 10 și 12 caractere (inclusiv).
//        [0.25p] computeDetails() metoda returnează un obiect String, care conține detaliile obiectului, sub următorul format:
//Pachetul <packageName> avand codul <uniqueID> si greutatea <weight> apartine curierului <courierName>.
//        Exemplu: Pachetul LaptopDustPRO avand codul 213142341425 si greutatea 4.2 apartine curierului DevCarry.
public class GenericPackage {

    private String uniqueID;
    private double weight;
    private String packageName;
    // un camp static este un camp care are acceasi valoare pentru taote obiectele din cadrul calsei
    private static String courierName;
    //proprietate read-only inseamna ca un camp are doar getteri nu si setteri

    public GenericPackage() {

    }

    public GenericPackage(String uniqueID, double weight, String packageName) {
        this.uniqueID = uniqueID;
        this.weight = weight;
        this.packageName = packageName;
    }

    //getteri
    public String getPackageName() {
        return packageName;
    }

    //setteri
    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public static void setCourierName(String courierName) {
        GenericPackage.courierName = courierName;
    }


    public void addidem(double weight) {
        this.weight += weight;
    }

    public boolean areDoarCifre() {
        for (int i = 0; i < uniqueID.length(); i++) {
            if (!Character.isDigit(uniqueID.charAt(i))) {
                return false;
            }

        }
        return true;
    }
    //Character este o clasa Wrapper pe tipul de data char. Accesam metoda isDigit pentru a afla
    // daca fiecare caracter din string este cifra
    //Ca sa accesam caracterul dintr-un string folosim metoda CharAt-Care acceseaza fiecare litera din string(uniqueID)

    public boolean areNumarMultipleTrei() {
        int contor = 0;


        for (int i = 0; i < uniqueID.length(); i++) {
            int cifraCurenta = Character.getNumericValue(uniqueID.charAt(i));//getNumericValue este o metoda care converteste un char intr-un numar
            //Parsarea reprezinta conversia dintr-un tip de data in alt tip.
            if (cifraCurenta % 2 != 0) {
                contor++;
            }
            //verificam daca restul impartirii la 2 este diferit de 0 , atunci cifra este impara

        }
        return contor % 3 == 0;
        //Verificam daca numarul de cifre este multipu de 3.Daca da intoarce true dca nu false.
    }

    public boolean sumaCifrelorDivPatru() {
        int sumCifre = 0;
        for (int i = 0; i < uniqueID.length(); i++) {
            int cifraCurenta = Character.getNumericValue(uniqueID.charAt(i));
            sumCifre += cifraCurenta;
        }
        return sumCifre % 4 == 0;

    }

    public boolean diferentaCifraCon5(){
        for(int i=0;i<uniqueID.length()-1;i++){
            if(Character.getNumericValue(uniqueID.charAt(i))-Character.getNumericValue(uniqueID.charAt(i+1))>=5){
                return false;
            }
        }
        return true;
    }
    
    public boolean lungimeCuprinsa10si12(){
        if(uniqueID.length()>=10 && uniqueID.length()<=12)//length acceaseaza dimensiunea stringului ,numara
           // cate caractere are un string
        {
            return true;
        }
        return false;
    }

    public boolean checkID(){
        return areDoarCifre() && areNumarMultipleTrei() && sumaCifrelorDivPatru() && diferentaCifraCon5() & lungimeCuprinsa10si12();

    }

    @Override
    public String toString() {
        return "GenericPackage{" +
                "uniqueID='" + uniqueID + '\'' +
                ", weight=" + weight +
                ", packageName='" + packageName + '\'' +
                '}';
    }//to string in clasa object care afiseaza la consola campurile obicetelor


}

