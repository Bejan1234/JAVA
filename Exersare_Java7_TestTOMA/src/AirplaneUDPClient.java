import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.*;

public class AirplaneUDPClient {
    private static final String adresaServer = "localhost";
    private static final int PORT = 8080;

    public static void main(String[] args) throws Exception {
        Airplane airplane1 = new Airplane(600.0f, "M1", "Insurance1", 7000.0f, AirplaneType.TURKISH_AIRLINES);
        Airplane airplane2 = new Airplane(700.0f, "M2", "Insurance2", 5000.0f, AirplaneType.LUFTANSA);
        Airplane airplane3 = new Airplane(800.0f, "M3", "Insurance3", 4000.0f, AirplaneType.LUFTANSA);
        Airplane airplane4 = new Airplane(200.0f, "M3", "Insurance3", 4000.0f, AirplaneType.LUFTANSA);
        Airplane airplane5 = new Airplane(900.0f, "M4", "Insurance4", 2000.0f, AirplaneType.TAROM);

        List<Airplane> listaAvioane = new ArrayList<>();
        listaAvioane.add(airplane1);
        listaAvioane.add(airplane2);
        listaAvioane.add(airplane3);
        listaAvioane.add(airplane4);
        listaAvioane.add(airplane5);
        //filtrare fara stream

        List<Airplane> listaFiltrata = new ArrayList<>();
        for (Airplane airplane : listaAvioane) {
            if (airplane.getFlightCrewCost() > 2000.0) {
                listaFiltrata.add(airplane);
            }
        }

        Map<Integer, Airplane> map = new HashMap<>();
        map.put(1, airplane1);
        map.put(2, airplane2);
        map.put(3, airplane2);
        map.put(4, airplane2);
        map.put(5, airplane2);


        System.out.println("===========AFISARE MAP=====================");
        for (var entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        //filtrare fara stream dupa un String
        List<Airplane> filtrareString = new ArrayList<>();
        for (int i = 0; i < listaAvioane.size(); i++) {
            if (listaAvioane.get(i).getAirplaneType().equals(AirplaneType.LUFTANSA)) {
                filtrareString.add(listaFiltrata.get(i));
            }
        }

        //conversie din list in set

        Set<Airplane> setAvioane = new HashSet<>();
        for (Airplane a : filtrareString) {
            setAvioane.add(a);
        }


        System.out.println("===============AFISARE SET =========================");
        System.out.println(setAvioane);

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
            for (Airplane a : filtrareString) {
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


        //scriere in JSON

        try (FileWriter fileWriter = new FileWriter("listaJSON.json")) {
            JSONArray jsonArray = new JSONArray();
            for (Airplane a : listaCititaDinTXT) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("kerosen", a.getKerosene());
                jsonObject.put("maintenance", a.getMaintenance());
                jsonObject.put("insurance", a.getInsurance());
                jsonObject.put("flightCrewCost", a.getFlightCrewCost());
                jsonObject.put("airplaneType", a.getAirplaneType().toString());
                jsonArray.put(jsonObject);

            }

            fileWriter.write(jsonArray.toString());
        }

        //citire din json

        List<Airplane> listaCititaDinJSON = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream("listaJSON.json")) {
            JSONTokener jsonTokener = new JSONTokener(fileInputStream);
            JSONArray jsonArray = new JSONArray(jsonTokener);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Airplane airplane = new Airplane(jsonObject.getFloat("kerosen"),
                        jsonObject.getString("maintenance"), jsonObject.getString("insurance"),
                        jsonObject.getFloat("flightCrewCost"), AirplaneType.valueOf(jsonObject.getString("airplaneType")));
                listaCititaDinJSON.add(airplane);
            }
        }

        System.out.println("===============AFISARE LISTA CITITA DIN JSON====================");
        System.out.println(listaCititaDinJSON);

        sendAirplanesToServer(setAvioane);
        Thread.sleep(2000);
        DatabaseHandler.selectAll();
//        list_set.DatabaseHandler.delete();
    }

    private static void sendAirplanesToServer(Set<Airplane> listaAvioane) {
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(listaAvioane);

            byte[] sendBuffer = byteArrayOutputStream.toByteArray();

            InetAddress adresaServerUDP = InetAddress.getByName(adresaServer);

            DatagramPacket datagramPacket = new DatagramPacket(sendBuffer, sendBuffer.length, adresaServerUDP, PORT);
            datagramSocket.send(datagramPacket);
            System.out.println("Aviaoane trimise: " + listaAvioane);

        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}