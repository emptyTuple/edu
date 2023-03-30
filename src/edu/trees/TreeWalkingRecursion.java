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
        BinaryTree root = new BinaryTree(22,
                new BinaryTree(13,
                        new BinaryTree(8,
                                new BinaryTree(1),
                                new BinaryTree(11,
                                        null,
                                        new BinaryTree(15))),
                        new BinaryTree(19)),
                new BinaryTree(33,
                        new BinaryTree(25,
                                new BinaryTree(23),
                                null),
                        new BinaryTree(50,
                                new BinaryTree(49),
                                new BinaryTree(61))));


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
