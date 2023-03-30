package edu.sequences_and_trees;

import java.util.ArrayList;

/*
 Queue - коллекция данных, реализующая FIFO дисциплину доступа к элементам.
 Для реализации описан интерфейс QueueBase, ограничивающий функциональность ArrayList
 для соблюдения дисциплины FIFO.
 */

public class QueueSimple implements QueueBase<String> {
    private ArrayList<String> array = new ArrayList<>();

    @Override
    public void add(String element) {
        array.add(element);
    }

    @Override
    public String remove() {
        return array.remove(0);
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }
}
