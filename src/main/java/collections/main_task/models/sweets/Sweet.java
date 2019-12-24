package collections.main_task.models.sweets;

import collections.main_task.models.Byn;

public class Sweet {
    private String name;
    private Byn price;
    private int weight;
    private int sugarContent;

    public Sweet(String name, Byn price, int weight, int sugarContent) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.sugarContent = sugarContent;
    }

    public Byn getCost() {
        return new Byn(price).div(100).mul(weight);
    }

    public String getName() {
        return name;
    }

    public Byn getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }

    public int getSugarСontent() {
        return sugarContent;
    }

    protected String fieldsToString() {
        return String.format("%s; price(%s); weight(%d); Sugar content(%d)", name, price, weight, sugarContent);
    }

    @Override
    public String toString() {
        return String.format("%s; cost=%s", fieldsToString(), getCost());
    }
}