package edu.math_expression_solver;

class Lexeme {
    LexemeType type;
    String value;

    Lexeme(LexemeType type, Character value) {
        this.type = type;
        this.value = value.toString();
    }

    Lexeme(LexemeType type, String value) {
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
