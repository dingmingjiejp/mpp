package lesson8.labs.prob1;

import java.util.function.Supplier;

public class Main_j {
    public static void main(String[] args) {

        // i. Rewrite this method reference as a lambda expression
        Supplier<Double> s1 = () -> Math.random();

        //ii. Put this method expression in a main method in a Java class
        // and use it to print a random number to the console
        System.out.println(s1.get());

        //iii. Create an equivalent Java class in which the functional behavior of Math::random is
        //expressed using an inner class (implementing Supplier); call this inner class from a
        //main method and use it to output a random number to the console. The behavior
        //should be the same as in part b.
        Supplier<Double> r = new Supplier<Double>() {
            @Override
            public Double get() {
                return Math.random();
            }
        };
        System.out.println(r.get());


    }

}
