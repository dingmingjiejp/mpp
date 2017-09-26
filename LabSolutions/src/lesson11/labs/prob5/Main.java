package lesson11.labs.prob5;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Integer> ints = Arrays.asList(1,4,5,5,6,2);
        System.out.println("second smallest number is : " + secondSmallest(ints));

        List<Character> chars = Arrays.asList('b','c','e','d');
        System.out.println("second smallest char is : " + secondSmallest(chars));

    }


    public static <T extends Comparable<? super T>> T secondSmallest(List<T> list) {

        if (list == null || list.size() == 0) return null;

        T min = null;
        T second = null;

        for (T t : list) {
            if (min == null) {
                min = t;
            } else if (second == null) {
                if (min.compareTo(t) <= 0) {
                    second = t;
                } else {
                    second = min;
                    min = t;
                }
            } else {
                if (min.compareTo(t) <= 0) {
                    if (second.compareTo(t)>0) {
                        second = t;
                    }
                } else {
                    second = min;
                    min = t;
                }
            }
        }

        return second;
    }

}
