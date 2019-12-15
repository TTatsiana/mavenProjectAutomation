package collections.mainTask;

import collections.mainTask.models.Byn;
import collections.mainTask.models.CandyFilling;
import collections.mainTask.models.FlavoringAdditives;
import collections.mainTask.models.Present;
import collections.mainTask.models.sweets.CaromelSweet;
import collections.mainTask.models.sweets.Sweet;
import collections.mainTask.models.sweets.ToffeeSweet;
import collections.mainTask.models.sweets.ChocolateSweet;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Runner {
    static List<Sweet> sweetList = Arrays.asList(
            new ToffeeSweet("Cream Toffee", new Byn(900), 50, 60, FlavoringAdditives.CREAM),
            new ToffeeSweet("Toffo", new Byn(850), 50, 46, FlavoringAdditives.LEMON),
            new ToffeeSweet("Toffee", new Byn(700), 120, 78, FlavoringAdditives.PEPPER),
            new ToffeeSweet("Dairy Toffee", new Byn(590), 70, 35, FlavoringAdditives.VANILLIN),
            new ChocolateSweet("Rocher", new Byn(430), 150, 60, 75),
            new ChocolateSweet("Pandy Candy", new Byn(900), 50, 60, 40),
            new ChocolateSweet("Choc", new Byn(800), 50, 80, 90),
            new CaromelSweet("Like", new Byn(450), 80, 90, CandyFilling.LIQUOR, new Byn(10)),
            new CaromelSweet("Lolli Pops", new Byn(440), 50, 90, CandyFilling.WALNUT, new Byn(10)),
            new CaromelSweet("Yogurtini", new Byn(410), 50, 90, CandyFilling.FRUIT, new Byn(15))
    );

    public static void main(String[] args) {
        Present childrenPresent = new Present(sweetList);
        System.out.println("Total cost = " + childrenPresent.calculatingTotalCost());
        childrenPresent.getSweets().stream().forEach(System.out::println);
        System.out.println("Weight = " + childrenPresent.calculateWeight());
        System.out.println("Sort by price:");
        childrenPresent.sortByPrice();
        childrenPresent.getSweets().stream().forEach(System.out::println);
        System.out.println("60 < Sugar content < 90");
        (childrenPresent.getSweets().stream().filter((s) -> (s.getSugarСontent() > 60) && (s.getSugarСontent() < 90))
                .collect(Collectors.toList()))
                .stream().forEach(System.out::println);
    }
}