import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AirplaneTCPClient {

    private static final String adresaServer = "localhost";
    private static final int PORT = 8080;

    public static void main(String[] args) throws Exception {
        Airplane airplane1 = new Airplane(700.0f, "M1", "Insurance1", 7000.0f, AirplaneType.TURKISH_AIRLINES);
        Airplane airplane2 = new Airplane(900.0f, "M2", "Insurance2", 5000.0f, AirplaneType.LUFTANSA);
        Airplane airplane3 = new Airplane(800.0f, "M3", "Insurance3", 4000.0f, AirplaneType.LUFTANSA);
        Airplane airplane4 = new Airplane(200.0f, "M3", "Insurance3", 4000.0f, AirplaneType.LUFTANSA);
        Airplane airplane5 = new Airplane(900.0f, "M4", "Insurance4", 2000.0f, AirplaneType.TAROM);

        System.out.println(airplane1.equals(airplane2));


        List<Airplane> listaAvioane = new ArrayList<>();
        listaAvioane.add(airplane1);
        listaAvioane.add(airplane2);
        listaAvioane.add(airplane3);
        listaAvioane.add(airplane4);
        listaAvioane.add(airplane5);


        List<Airplane> listaAvioaneFiltrateStream = listaAvioane.stream().filter(s->s.getAirplaneType().equals(AirplaneType.LUFTANSA)).toList();
        System.out.println(listaAvioaneFiltrateStream);


        List<String> listaStringuri = List.of("abc","def","ghi","jkl","mno");
        List<String> listaStringuriSoratata = listaStringuri.stream().filter(s->s.startsWith("a")).toList();
        System.out.println(listaStringuriSoratata);

        List<Airplane> listaSoratataDupaKerosen = listaAvioane.stream().sorted(Comparator.comparingDouble(Airplane::getKerosene).reversed()).toList();
        System.out.println(listaSoratataDupaKerosen);



        IAction airplane7 = new Airplane();//upcasting

        IAction actiune = new IAction() {// instantiere interfata
            @Override
            public void takeOff() {
                System.out.println("Decolare anonima!");
            }

            @Override
            public boolean isLuxury() {
                return true;
            }
        };


        //apel interfata functionala

        for(Airplane airplane: listaAvioane){
            airplane.takeOff();
            System.out.println();
        }

        //filtrare fara stream

        List<Airplane> listaFiltrata = new ArrayList<>();
        for (Airplane airplane : listaAvioane) {
            if (airplane.getFlightCrewCost() > 2000.0) {
                listaFiltrata.add(airplane);
            }
        }

        //filtrare fara stream dupa un String
        List<Airplane> filtrareString = new ArrayList<>();
        for (int i = 0; i < listaAvioane.size(); i++) {
            if (listaAvioane.get(i).getAirplaneType().equals(AirplaneType.LUFTANSA)) {
                filtrareString.add(listaAvioane.get(i));
            }
        }

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("fisierBinar.dat")))) {
            objectOutputStream.writeObject(filtrareString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        List<Airplane> listCititaDinBinar = new ArrayList<>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream("fisierBinar.dat")))) {
            listCititaDinBinar = (List<Airplane>) objectInputStream.readObject();
        }
        //SCRIERE IN FISIER TXT


        try (FileWriter fileWriter = new FileWriter("listaAvioane.txt")) {
            for (Airplane a : listCititaDinBinar) {
                fileWriter.write(String.valueOf(a.getKerosene()));
                fileWriter.write(",");
                fileWriter.write(a.getMaintenance());
                fileWriter.write(",");
                fileWriter.write(a.getInsurance());
                fileWriter.write(",");
                fileWriter.write(String.valueOf(a.getFlightCrewCost()));
                fileWriter.write(",");
                fileWriter.write(String.valueOf(a.getAirplaneType()));
                fileWriter.write("\n");
            }
        }

        //CITIRE DIN FISIER TEXT
        File file = new File("listaAvioane.txt");
        Scanner scanner = new Scanner(file);
        List<Airplane> listaCititaDinTXT = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String linie = scanner.nextLine();
            String[] bucati = linie.split(",");
            float kerosene = Float.parseFloat(bucati[0]);
            String maintenance = bucati[1];
            String insurance = bucati[2];
            float flightCrewCost = Float.parseFloat(bucati[3]);
            AirplaneType airplaneType = AirplaneType.valueOf(bucati[4]);

            Airplane airplane = new Airplane(kerosene, maintenance, insurance, flightCrewCost, airplaneType);
            listaCititaDinTXT.add(airplane);
        }

        System.out.println("================AFISARE LISTA DIN TXT=========================");
        System.out.println(listaCititaDinTXT);


        List<Airplane> sharedList = new ArrayList<>();

//        Thread thread1 = new Thread(new WriteThread(filtrareString, "fisierPrinThread.dat"));
//        Thread thread2 = new Thread(new ReadThread(sharedList, "fisierPrinThread.dat"));
//
//        thread1 .start();
//        thread1.join();
//        thread2.start();
//
//        Thread thread2 = new Thread(new WriteThread(listaAvioane, "fisierPrinThread2.dat"));


        //amandoua ruleaza in acelasi timp
//        thread1.start();
//        thread2.start();


        //mai intai rulam thread, scrie in binar, iar citirea din binar o face dupa ce se executa thread2
        //thread1 asteapta dupa thread2
//        thread1.start();
//        thread1.join();


//        //thread2 va astepta dupa urmatorul thread existent in aplicatie
//        thread2.start();
//        thread2.join();


        //oprim thread-ul
//        thread1.stop();
//
//        thread2.stop();


        writeAirplanesToXML(listaFiltrata, "airplanes.xml");

        // Read from XML

        System.out.println("====================AFISARE LISTA CITITA DIN XML===================");

        List<Airplane> airplanesFromXML = readAirplanesFromXML("airplanes.xml");
        System.out.println(airplanesFromXML);

        sendAirplanesToServer(listaCititaDinTXT);
        Thread.sleep(2000);

        DatabaseHandler.selectAll();

        DatabaseHandler.deleteAll();
        DatabaseHandler.selectAll();


        DatabaseHandler.insertAirplane(airplane1);
        DatabaseHandler.insertAirplane(airplane2);
        DatabaseHandler.insertAirplane(airplane3);

        DatabaseHandler.selectAll();

        //actualizare dupa un camp
        Airplane updatedAirplane = new Airplane(1000.0f, "UpdatedM1", "UpdatedInsurance1", 8000.0f, AirplaneType.LUFTANSA);


        DatabaseHandler.updateAirplaneByType(AirplaneType.TURKISH_AIRLINES, updatedAirplane);
        DatabaseHandler.selectAll();

        //stergere dupa un camp
        DatabaseHandler.deleteAirplaneByType(AirplaneType.LUFTANSA);

        System.out.println("===============TABELA DUPA STERGEREA AVIOANELOR LUFTANSA=================");
        DatabaseHandler.selectAll();



    }

    private static void sendAirplanesToServer(List<Airplane> listaAvioane) {
        try (Socket socket = new Socket(adresaServer, PORT)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(listaAvioane);
            System.out.println("Avioane trimise catre server: " + listaAvioane);

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeAirplanesToXML(List<Airplane> airplanes, String filename) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();


            Element rootElement = document.createElement("airplanes");
            document.appendChild(rootElement);


            for (Airplane airplane : airplanes) {
                Element airplaneElement = document.createElement("airplane");

                Element kerosene = document.createElement("kerosene");
                kerosene.appendChild(document.createTextNode(String.valueOf(airplane.getKerosene())));
                airplaneElement.appendChild(kerosene);

                Element maintenance = document.createElement("maintenance");
                maintenance.appendChild(document.createTextNode(airplane.getMaintenance()));
                airplaneElement.appendChild(maintenance);

                Element insurance = document.createElement("insurance");
                insurance.appendChild(document.createTextNode(airplane.getInsurance()));
                airplaneElement.appendChild(insurance);

                Element flightCrewCost = document.createElement("flightCrewCost");
                flightCrewCost.appendChild(document.createTextNode(String.valueOf(airplane.getFlightCrewCost())));
                airplaneElement.appendChild(flightCrewCost);

                Element airplaneType = document.createElement("airplaneType");
                airplaneType.appendChild(document.createTextNode(airplane.getAirplaneType().name()));
                airplaneElement.appendChild(airplaneType);

                rootElement.appendChild(airplaneElement);
            }


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(filename));

            transformer.transform(domSource, streamResult);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static List<Airplane> readAirplanesFromXML(String filename) {
        List<Airplane> airplanes = new ArrayList<>();
        try {
            File file = new File(filename);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("airplane");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    float kerosene = Float.parseFloat(element.getElementsByTagName("kerosene").item(0).getTextContent());
                    String maintenance = element.getElementsByTagName("maintenance").item(0).getTextContent();
                    String insurance = element.getElementsByTagName("insurance").item(0).getTextContent();
                    float flightCrewCost = Float.parseFloat(element.getElementsByTagName("flightCrewCost").item(0).getTextContent());
                    AirplaneType airplaneType = AirplaneType.valueOf(element.getElementsByTagName("airplaneType").item(0).getTextContent());

                    Airplane airplane = new Airplane(kerosene, maintenance, insurance, flightCrewCost, airplaneType);
                    airplanes.add(airplane);
                }
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return airplanes;
    }


}