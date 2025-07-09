import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AirplaneUDPServer {

    private static final int PORT = 8080;
    private static final Set<Airplane> listaAvioane = new HashSet<>();
    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        //database -> creare tabela
        DatabaseHandler.createTable();

        try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {
            System.out.println("Serverul asculta pe portul " + PORT);

            while (true) {
                byte[] receivedBuffer = new byte[1024];
                DatagramPacket datagramPacket = new DatagramPacket(receivedBuffer, receivedBuffer.length);
                serverSocket.receive(datagramPacket);
                executorService.submit(new ThreadHandler(serverSocket, datagramPacket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static class ThreadHandler implements Runnable {

        private DatagramSocket datagramSocket;
        private DatagramPacket datagramPacket;

        public ThreadHandler(DatagramSocket datagramSocket, DatagramPacket datagramPacket) {
            this.datagramSocket = datagramSocket;
            this.datagramPacket = datagramPacket;
        }

        @Override
        public void run() {
            try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(datagramPacket.getData())) {

                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

                Set<Airplane> airplanes = (Set<Airplane>) objectInputStream.readObject();

                synchronized (airplanes){
                    listaAvioane.addAll(airplanes);
                }

                System.out.println("Avioane primite: " + airplanes);

                //inseare in BD
                airplanes.forEach(DatabaseHandler::insertAirplane);
                System.out.println("Inserted airplanes into database");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}