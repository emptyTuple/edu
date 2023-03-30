package edu.sequences_and_trees;


/*
Класс для представления бинарного дерева.
Алгоритм рекурсивного обхода дерева в глубину.
Задача - вычислить сумму всех узлов дерева.

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
public class BinaryTreeWalkingRecursion {
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

        System.out.println("All nodes values sum: " + root.sumTreeNodes());
    }

    public static class BinaryTree {
       int value;
       BinaryTree leftOffspring;
       BinaryTree rightOffspring;

        public BinaryTree(int value) { this.value = value; }

        public BinaryTree(int value, BinaryTree leftOffspring, BinaryTree rightOffspring) {
            this.value = value;
            this.leftOffspring = leftOffspring;
            this.rightOffspring = rightOffspring;
        }

        public int sumTreeNodes() {
            int sum = value;
            if (leftOffspring != null) {
                sum += leftOffspring.sumTreeNodes();
            }
            if (rightOffspring != null) {
                sum += rightOffspring.sumTreeNodes();
            }
            return sum;
        }
    }
}
