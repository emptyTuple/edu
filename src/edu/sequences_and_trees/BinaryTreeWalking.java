package edu.sequences_and_trees;

import java.util.Stack;

/*
Класс для представления бинарного дерева.
Рекурсивный обход дерева в глубину с вычислением суммы всех узлов.
Итеративный обход дерева с использованием стэка с вычислением суммы узлов.

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
public class BinaryTreeWalking {
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

        System.out.println("All nodes values sum: " + root.sumTreeNodesRecursion());
        System.out.println("All nodes values sum: " + root.sumTreeNodesIteration(root));

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

        public int sumTreeNodesRecursion() {
            int sum = value;
            if (leftOffspring != null) {
                sum += leftOffspring.sumTreeNodesRecursion();
            }
            if (rightOffspring != null) {
                sum += rightOffspring.sumTreeNodesRecursion();
            }
            return sum;
        }

        public int sumTreeNodesIteration(BinaryTree root) {
            Stack<BinaryTree> stack = new Stack<>();
            stack.push(root);
            int sum = 0;
            while (!stack.isEmpty()) {
                BinaryTree node = stack.pop();
                System.out.println(node.value); // увидеть порядок обхода
                sum += node.value;
                if (node.rightOffspring != null) {
                    stack.push(node.rightOffspring);
                }
                if (node.leftOffspring != null) {
                    stack.push(node.leftOffspring);
                }
            }
            return sum;
        }
    }
}
