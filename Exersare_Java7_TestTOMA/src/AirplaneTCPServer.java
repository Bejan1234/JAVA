import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class AirplaneTCPServer {

    private static final int PORT = 8080;
    private static final List<Airplane> listaAvioane = new ArrayList<>();
    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        //database -> creare tabela
        DatabaseHandler.createTable();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Serverul asculta pe portul " + PORT);

            //poate primi oricati clienti, server-ul cand e up poate procesa mai multe cereri
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client conectat: " + socket.getInetAddress());

                // Utilizare Future pentru a procesa fiecare client în mod asincron
                Future<List<Airplane>> future = executorService.submit(new ThreadHandler(socket));

                try {
                    List<Airplane> result = future.get();
                    System.out.println("Avioane primite de la client: " + result);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ThreadHandler implements Callable<List<Airplane>> {
//future callable insemna ca se executa inainte de alte date
        private final Socket socket;

        public ThreadHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public List<Airplane> call() {
            List<Airplane> airplanes = new ArrayList<>();
            try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())) {
                airplanes = (List<Airplane>) objectInputStream.readObject();

                synchronized (listaAvioane) {
                    listaAvioane.addAll(airplanes);
                }

                System.out.println("Avioane primite: " + airplanes);

                // Inseare in BD
                airplanes.forEach(DatabaseHandler::insertAirplane);
                System.out.println("Inserted airplanes into database");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return airplanes;
        }
    }
}