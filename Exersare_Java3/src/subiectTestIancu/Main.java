package subiectTestIancu;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        TrainTicket trainTicket = new TrainTicket("BUC", "CJ", 200, TrainType.INTERREGIO);
        TrainTicket trainTicketNou = new TrainTicket("BUC", "CJ", 250, TrainType.INTERREGIO);

        System.out.println(trainTicket);
        System.out.println(trainTicketNou);



    }
}
