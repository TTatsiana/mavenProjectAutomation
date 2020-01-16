package moduletwo3.optional;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StartAirport {

    private static final Logger LOGGER = Logger.getLogger(StartAirport.class.getName());
    private static final String BEGIN = " began to enter the strip ";
    private static final String TAKEOFF = " took off";
    private static final String LANE_BUSY = " lane took the ";
    private static final String LANE_FREE = "is free";
    private static final String LANE_1 = "Lane1 ";
    private static final String LANE_2 = "Lane2 ";
    private static final String LANE_3 = "Lane3 ";
    private static final String LANE_4 = "Lane4 ";
    private static final String LANE_5 = "Lane5 ";
    private static final int NUMBER_OF_PLANES = 10;

    public static void main(String[] args) {
        BlockingQueue<String> planes = new ArrayBlockingQueue<>(NUMBER_OF_PLANES);
        planes.addAll(Arrays.asList("plane1", "plane2", "plane3", "plane4", "plane5", "plane6", "plane7", "plane8",
                "plane9", "plane10"));
        new Thread(() -> {
            try {
                while (!planes.isEmpty()) {
                    String plane = planes.take();
                    System.out.println(plane + BEGIN + LANE_1);
                    TimeUnit.MILLISECONDS.sleep(250);
                    System.out.println(LANE_1 + LANE_BUSY + plane);
                    TimeUnit.MILLISECONDS.sleep(2500);
                    System.out.println(plane + TAKEOFF);
                    TimeUnit.MILLISECONDS.sleep(250);
                    System.out.println(LANE_1 + LANE_FREE);
                }
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
            }
        }).start();
        new Thread(() -> {
            try {
                while (!planes.isEmpty()) {
                    String plane = planes.take();
                    System.out.println(plane + BEGIN + LANE_2);
                    TimeUnit.MILLISECONDS.sleep(1000);
                    System.out.println(LANE_2 + LANE_BUSY + plane);
                    TimeUnit.MILLISECONDS.sleep(1500);
                    System.out.println(plane + TAKEOFF);
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.println(LANE_2 + LANE_FREE);
                }
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
            }
        }).start();
        new Thread(() -> {
            try {
                while (!planes.isEmpty()) {
                    String plane = planes.take();
                    System.out.println(plane + BEGIN + LANE_3);
                    TimeUnit.MILLISECONDS.sleep(300);
                    System.out.println(LANE_3 + LANE_BUSY + plane);
                    TimeUnit.MILLISECONDS.sleep(1700);
                    System.out.println(plane + TAKEOFF);
                    TimeUnit.MILLISECONDS.sleep(1000);
                    System.out.println(LANE_3 + LANE_FREE);
                }
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
            }
        }).start();
        new Thread(() -> {
            try {
                while (!planes.isEmpty()) {
                    String plane = planes.take();
                    System.out.println(plane + BEGIN + LANE_4);
                    TimeUnit.MILLISECONDS.sleep(1000);
                    System.out.println(LANE_4 + LANE_BUSY + plane);
                    TimeUnit.MILLISECONDS.sleep(1000);
                    System.out.println(plane + TAKEOFF);
                    TimeUnit.MILLISECONDS.sleep(1000);
                    System.out.println(LANE_4 + LANE_FREE);
                }
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
            }
        }).start();
        new Thread(() -> {
            try {
                while (!planes.isEmpty()) {
                    String plane = planes.take();
                    System.out.println(plane + BEGIN + LANE_5);
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.println(LANE_5 + LANE_BUSY + plane);
                    TimeUnit.MILLISECONDS.sleep(2000);
                    System.out.println(plane + TAKEOFF);
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.println(LANE_5 + LANE_FREE);
                }
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
            }
        }).start();
    }
}