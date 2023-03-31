package edu.sequences_and_trees;

/*
Примитивный интрефейс для реализации стека ограничением функциональности коллекции
 */

public interface StackBase<T> {
    void push(T element);  // добавить элемент на вершину стэка
    T pop();               // взять элемент с вершины стэка и вернуть его
    boolean isEmpty();
}
