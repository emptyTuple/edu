package edu.trees;


/*
Класс для представления бинарного дерева.
Алгоритм рекурсивного обхода дерева в глубину.

Тестовое дерево:
                      22
                    /    \
                  13      33
                 /  \     /  \
                8   19   25   50
              /  \       /    / \
             1    11   23   49   61
                    \
                     15
 */
public class TreeWalkingRecursion {
    public static void main(String[] args) {

    }

    public static class BinaryTree {
       int value;
       BinaryTree leftOffspring;
       BinaryTree rightOffspring;

        public BinaryTree(int value) {
            this.value = value;
        }

        public BinaryTree(int value, BinaryTree leftOffspring, BinaryTree rightOffspring) {
            this.value = value;
            this.leftOffspring = leftOffspring;
            this.rightOffspring = rightOffspring;
        }
    }
}
