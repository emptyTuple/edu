package edu.sequences_and_trees;

/*
Примитивный интрефейс для реализации очереди ограничением функциональности коллекции
 */

public interface QueueBase<T> {
    void add(T element); // добавляет элемент в конец очереди
    T remove();          // возвращает и удаляет элемент из конца очереди
    boolean isEmpty();
}
