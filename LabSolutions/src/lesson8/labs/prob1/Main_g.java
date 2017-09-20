package lesson8.labs.prob1;

import java.util.function.Predicate;

public class Main_g {
    private String name;
    public Main_g(String name) {
        this.name = name;
    }

     public static void main(String[] args) {
        Main_g test = new Main_g("test");
        System.out.println(test.g(new Main_g("test")));
    }

    public boolean g(Object obj) {
        // Question g
        Predicate<Object> p = this::equals;
        return p.test(obj);
    }

    @Override
    public boolean equals(Object obj)  {
        if (obj != null || Main_g.class.isInstance(obj))
            return this.name.equals(((Main_g)obj).name);
        return false;
    }
}
