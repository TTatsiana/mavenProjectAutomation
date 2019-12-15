package collections.mainTask.models;

import collections.mainTask.models.sweets.Sweet;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Present {
    private List<Sweet> sweets;

    public Present(List<Sweet> sweets) {
        this.sweets = sweets;
    }

    public List<Sweet> getSweets() {
        return sweets;
    }

    public int calculateWeight() {
        int weight = 0;
        for (Sweet sweet : sweets) {
            weight += sweet.getWeight();
        }
        return weight;
    }

    public Present sortByPrice() {
        Collections.sort(sweets, new Comparator<Sweet>() {
            @Override
            public int compare(Sweet o1, Sweet o2) {
                return o1.getPrice().compareTo(o2.getPrice());
            }
        });
        return this;
    }

    public Byn calculatingTotalCost() {
        Byn sum = new Byn(0);
        for (Sweet sweet : sweets) {
            sum = sum.add(sweet.getCost());
        }
        return sum;
    }
}