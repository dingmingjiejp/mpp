package lesson8.labs.prob4;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> t = new ArrayList<>();

        t.add("Test1");
        t.add("Test2");
        t.add("Test3");

        Main m = new Main();
        System.out.println(m.countWords(t, 'T','1', 3));


    }

    public int countWords(List<String> words, char c, char d, int len) {

        return Long.valueOf(words.stream().filter( v -> v.indexOf(c)>-1 && v.indexOf(d)==-1).count()).intValue();

    }
}
