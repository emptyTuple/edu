package edu.sequences_and_trees;

import java.util.ArrayList;

/*
 Queue - коллекция данных, реализующая FIFO дисциплину доступа к элементам.
 Для реализации описан интерфейс QueueBase, ограничивающий функциональность ArrayList
 для соблюдения дисциплины FIFO.
 */

public class QueueInterfaceRealization implements QueueBase<T> {
    private ArrayList<T> array = new ArrayList<T>();

    @Override
    public void add(T element) {
        array.add(element);
    }

    @Override
    public T remove() {
        return array.remove(0);
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }
}
