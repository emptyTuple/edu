package edu.math_expression_solver;

import java.util.List;

class LexemeBuffer {
    int pos;
    List<Lexeme> lexemes;

    LexemeBuffer(List<Lexeme> lexemes) {
        this.lexemes = lexemes;
    }

    Lexeme next() {
        return lexemes.get(pos);
    }

    void prev() {
        pos--;
    }

    int getPos() {
        return pos;
    }

}
