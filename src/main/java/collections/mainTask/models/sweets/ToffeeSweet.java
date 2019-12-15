package collections.mainTask.models.sweets;

import collections.mainTask.models.Byn;
import collections.mainTask.models.FlavoringAdditives;

public class ToffeeSweet extends Sweet {
    private FlavoringAdditives flavoringAdditives;

    public ToffeeSweet(String name, Byn price, int weight, int sugarContent, FlavoringAdditives flavoringAdditives) {
        super(name, price, weight, sugarContent);
        this.flavoringAdditives = flavoringAdditives;
    }

    @Override
    protected String fieldsToString() {
        return String.format("%s;%s", super.fieldsToString(), flavoringAdditives);
    }
}