package moduletwo3.main;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParkingAction {

    private static final Logger LOGGER = Logger.getLogger(ParkingAction.class.getName());
    private static final String PARKED = " parked.";
    private static final String LEFT = " left the parking lot";
    private static final String CHANGED_PARKING = " left for another parking lot";
    private static final int SEVERAL_CARS = 12;
    private static final int NUMBER_OF_PARKING_PLACES = 3;
    private static final int DRIVE_UP_PERIOD = 100;
    private static final int PARKING_TIME = 1;
    private static final int WAITING_TIME = 2;

    public static void main(String[] args) {
        BlockingQueue<String> parking = new LinkedBlockingQueue<>(NUMBER_OF_PARKING_PLACES);
        for (int i = 1; i <= SEVERAL_CARS; i++) {
            final int number = i;
            try {
                TimeUnit.MILLISECONDS.sleep(DRIVE_UP_PERIOD);
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
            }
            new Thread(() -> {
                String car = "car-" + number;
                try {
                    if (parking.offer(car, WAITING_TIME, TimeUnit.SECONDS)) {
                        System.out.println(car + PARKED);
                        TimeUnit.SECONDS.sleep(PARKING_TIME);
                        parking.take();
                        System.out.println(car + LEFT);
                    } else {
                        System.out.println(car + CHANGED_PARKING);
                    }
                } catch (InterruptedException e) {
                    LOGGER.log(Level.SEVERE, e.toString(), e);
                }
            }).start();
        }
    }
}
