package collections.mainTask.models.sweets;

import collections.mainTask.models.Byn;

public class ChocolateSweet extends Sweet {
    private int percentageOfCocoa;

    public ChocolateSweet(String name, Byn price, int weight, int sugarContent, int percentageOfCocoa) {
        super(name, price, weight, sugarContent);
        this.percentageOfCocoa = percentageOfCocoa;
    }

    @Override
    protected String fieldsToString() {
        return String.format("%s; %d", super.fieldsToString(), percentageOfCocoa);
    }
}