public class MainGenericPackage {
    public static void main(String[] args) {
        GenericPackage genericPackage = new GenericPackage("2314", 34, "Coletul1");
        System.out.println(genericPackage);
        System.out.println(genericPackage.areDoarCifre());
        System.out.println(genericPackage.areNumarMultipleTrei());
        System.out.println(genericPackage.sumaCifrelorDivPatru());
        System.out.println(genericPackage.diferentaCifraCon5());
        System.out.println(genericPackage.lungimeCuprinsa10si12());
    }
}
