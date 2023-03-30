package edu.sequences_and_trees;

public interface QueueBase<T> {
    void add(T element); // добавляет элемент в конец очереди
    T remove();          // возвращает и удаляет элемент из конца очереди
    boolean isEmpty();
}
