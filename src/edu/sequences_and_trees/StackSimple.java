package edu.sequences_and_trees;

import java.util.ArrayList;

/*
 Stack - коллекция данных, реализующая LIFO дисциплину доступа к элементам.
 Для реализации описан интерфейс StackBase, ограничивающий функциональность ArrayList
 для соблюдения дисциплины LIFO.
 */

public class StackSimple implements StackBase<String> {
    private ArrayList<String> array = new ArrayList<>();

    @Override
    public void push(String element) {
        array.add(0, element);
    }

    @Override
    public String pop() {
        return array.remove(0);
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }
}
