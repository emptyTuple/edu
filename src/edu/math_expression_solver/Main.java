package edu.math_expression_solver;

public class Main {

    public static void main(String[] args) {
        String exp1 = "-(1.2 + 3.456) *5 * 7.012/8  -9.0*7/8^3.5 ";

        MathExpressionParser p1 = new MathExpressionParser(exp1);
        System.out.println(p1.parseLexemes(exp1).toString());

    }
}
