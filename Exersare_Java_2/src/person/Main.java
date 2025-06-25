package person;

public class Main {
    public static void main(String[] args) {
        //referinta in memorie se numeste hashCode
//        Person person = new Person("Ionut", 20, 'M',1.80f);
//        Person person1 = new Person("Petru", 20, 'M', 1.83f);
//
//        System.out.println(person);
//        System.out.println(person1);

        //valorile din enum se numeste constante
        Angajat angajat1 = new Angajat("Ionut", 20, 'M',1.80f,7000,"Cegeka",3,Departamente.IT);
        Angajat angajat2 = new Angajat("Mihai", 22, 'M',1.81f,8000,"Cegeka",4,Departamente.IT);
        Angajat angajat3 = new Angajat("Ioana", 21, 'F',1.82f,7000,"Cegeka",3,Departamente.IT);
        Angajat angajat4 = new Angajat("Radu", 20, 'M',1.84f,5000,"Cegeka",1,Departamente.IT);

        System.out.println(angajat1);

        angajat1.maresteSalariu();

        System.out.println(angajat1);

        System.out.println(angajat1.getPremiu());

        Manager manager1 = new Manager("Petru", 25, 'M',1.85f,9000,"Cegeka",9,Departamente.IT);
        manager1.adaugaAngajat(angajat1);
        manager1.adaugaAngajat(angajat2);
        manager1.adaugaAngajat(angajat3);
        manager1.adaugaAngajat(angajat4);

        System.out.println(manager1);


    }
}
