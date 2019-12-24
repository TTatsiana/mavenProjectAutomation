package classes.main_task;

public class Byn implements Comparable<Byn> {
    private final int value;

    public Byn(int value) {
        this.value = value;
    }

    public Byn() {
        this(0);
    }

    public Byn(Byn byn) {
        this(byn.value);
    }

    public int getRubs() {
        return value / 100;
    }

    public int getCoins() {
        return value % 100;
    }

    public String toString() {
        return getRubs() + "." + getCoins();
    }

    public int compareTo(Byn byn) {
        return value - byn.value;
    }
}