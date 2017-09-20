package lesson8.labs.prob3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class Main {

    public static void main(String[] args) {

        BiFunction<Double,Double,List> biFunction = (x,y) -> {
            List<Double> list = new ArrayList<>();
            list.add(Math.pow(x,y));
            list.add(x * y);
            return list;
        };

        System.out.println(biFunction.apply(2.0D,4.0D));
    }
}
