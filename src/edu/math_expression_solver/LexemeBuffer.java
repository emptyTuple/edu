package edu.math_expression_solver;

import java.util.List;

class LexemeBuffer {
    int pos;
    List<Lexeme> lexemes;

    LexemeBuffer(List<Lexeme> lexemes) {
        this.lexemes = lexemes;
    }

    // возвращаем позицию и передвигаем указатель
    Lexeme next() {
        return lexemes.get(pos++);
    }

    // передвигаем указатель наад
    void prev() {
        pos--;
    }

    int getPos() {
        return pos;
    }

}
