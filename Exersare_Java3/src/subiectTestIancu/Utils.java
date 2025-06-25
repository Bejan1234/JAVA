package subiectTestIancu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class Utils {
    public static Runnable lambdaOperation = () -> {
        int[] array = {1, 2, 3};

        int product = 1;
        for (int x : array) {
          product *=x;
        }
        System.out.println(product);
    };

    public static Stream<TrainTicket> getLongDistanceTrain(List<TrainTicket> trains, String departure){
        return trains.stream().filter(x->x.getDistance()>100).filter(y->y.getDeparture().equals(departure)).distinct();
    }

    public static void main(String[] args) {
        //lansam un fir secundar , prin acest executor service
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(lambdaOperation);


        List<TrainTicket> trainTickets = new ArrayList<>();
        trainTickets.add(new TrainTicket("Bucharest", "Cluj-Napoca", 500, TrainType.INTERCITY));
        trainTickets.add(new TrainTicket("Cluj-Napoca", "Timisoara", 300, TrainType.REGIO));
        trainTickets.add(new TrainTicket("Bucharest", "Constanta", 200, TrainType.INTERREGIO));

        executorService.execute(()->{
            List<TrainTicket>longDistanceTrains = Utils.getLongDistanceTrain(trainTickets,"Bucharest").toList();

            longDistanceTrains.forEach(System.out::println);
        });

    }

}
