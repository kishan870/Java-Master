package dev.lpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainMailer {
    public static void main(String[] args) {

        String[] names = {"Ann Jones", "Ann Jones Ph.D.", "Bob Jones", "Bob Jones M.D.",
                            "Carol Jones", "Ed Green Ph.D.", "Ed Green M.D.", "Ed Black"};

        List<StringBuilder> population = getNames(names);
        Map<StringBuilder, Integer> counts = new TreeMap<>();

        population.forEach(s -> counts.merge(s, 1, Integer::sum));
        System.out.println(counts);

    }

    private static List<StringBuilder> getNames(String[] names) {

        List<StringBuilder> list = new ArrayList<>();
        int index = 3;

        for(String name: names) {
            for(int i=0; i< index; i++) {
                list.add(new StringBuilder(name));
            }

            index++;
        }
        return list;
    }
}
