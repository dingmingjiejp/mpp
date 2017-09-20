package lesson8.labs.prob1;

import java.util.function.Consumer;

public class Main_h {

    public static void main(String[] args) {
        //System.out::println;
        Consumer<String> c1 = System.out::println;
        Consumer<String> c2 = (v) -> System.out.println(v);
        c1.accept("Test1");
        c2.accept("Test2");
    }
}
