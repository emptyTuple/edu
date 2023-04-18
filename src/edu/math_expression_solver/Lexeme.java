package edu.math_expression_solver;

public class Lexeme {
    LexemeType type;
    String value;

    public Lexeme(LexemeType type, Character value) {
        this.type = type;
        this.value = value.toString();
    }

    public Lexeme(LexemeType type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Lexeme{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
