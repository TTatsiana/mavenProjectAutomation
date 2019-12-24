package collections.main_task.models;

public class Byn implements Comparable<Byn> {
    private final int value;

    public Byn(int value) {
        this.value = value;
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

    public Byn mul(int multiplier) {
        return new Byn(value * multiplier);
    }

    public Byn div(int multiplier) {
        return new Byn(Math.round(value /
                multiplier));
    }

    public Byn add(Byn byn) {
        return new Byn(value + byn.value);
    }

    public Byn sub(Byn byn) {

        return new Byn(value - byn.value);
    }

    @Override
    public String toString() {
        int coins = getCoins();
        return getRubs() + "." + coins / 10 + coins % 10;
    }

    public int compareTo(Byn byn) {
        return value - byn.value;
    }
}