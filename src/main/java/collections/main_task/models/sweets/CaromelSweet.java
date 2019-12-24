package collections.main_task.models.sweets;

import collections.main_task.models.Byn;
import collections.main_task.models.CandyFilling;

public class CaromelSweet extends Sweet {
    private CandyFilling candyFilling;
    private Byn discount;

    public CaromelSweet(String name, Byn price, int weight, int sugarContent, CandyFilling candyFilling, Byn discount) {
        super(name, price, weight, sugarContent);
        this.candyFilling = candyFilling;
        this.discount = discount;
    }

    @Override
    public Byn getCost() {
        return new Byn(getPrice()).sub(discount).div(100).mul(getWeight());
    }

    @Override
    protected String fieldsToString() {
        return String.format("%s; %s; %s", super.fieldsToString(), candyFilling, discount);
    }
}