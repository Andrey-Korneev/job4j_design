package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        int counter = 0;
        while (source.hasNext()) {
            nodes.get(counter).add(source.next());
            counter++;
            if (counter == nodes.size()) {
                counter = 0;
            }
        }
    }
}