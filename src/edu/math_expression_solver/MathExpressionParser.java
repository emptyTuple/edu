package edu.math_expression_solver;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
_________________
EXPRESSION RULES:
_________________
EXPRESSION : PLUS_MINUS EOF
PLUS_MINUS : MUL_DIV ( ( '+' | '-' ) MUL_DIV )*
MUL_DIV : POWER ( ( '*' | '/' ) POWER )*
POWER : FACTOR ( '^' FACTOR )+
FACTOR : '-' FACTOR | NUMBER | '(' EXPRESSION ')' | FUNC '(' ( EXPRESSION ( ',' EXPRESSION )* )? ')'
 */

public class MathExpressionParser {
    private String expression;
    private LexemeBuffer lexemes;

    public MathExpressionParser(String expression) {
        this.lexemes = new LexemeBuffer(parseLexemes(expression));
    }

    public List<Lexeme> parseLexemes(String exp) {
        exp = exp.replaceAll(" ", "").toLowerCase();
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        int pos = 0;
        while (pos < exp.length()) {
            char c = exp.charAt(pos);
            switch (c) {
                case '+':
                    lexemes.add(new Lexeme(LexemeType.PLUS, c));
                    pos++;
                    continue;
                case '-':
                    lexemes.add(new Lexeme(LexemeType.MINIS, c));
                    pos++;
                    continue;
                case '*':
                    lexemes.add(new Lexeme(LexemeType.MUL, c));
                    pos++;
                    continue;
                case '/':
                    lexemes.add(new Lexeme(LexemeType.DIV, c));
                    pos++;
                    continue;
                case '(':
                    lexemes.add(new Lexeme(LexemeType.LEFT_BRACKET, c));
                    pos++;
                    continue;
                case ')':
                    lexemes.add(new Lexeme(LexemeType.RIGHT_BRACKET, c));
                    pos++;
                    continue;
                case '^':
                    lexemes.add(new Lexeme(LexemeType.POWER, c));
                    pos++;
                    continue;
                default:
                    if (c <= '9' && c >= '0') {
                        Pattern pattern = Pattern.compile("\\d+(?:\\.\\d+)?");
                        Matcher matcher = pattern.matcher(exp).region(pos, exp.length());
                        if (matcher.find()) {
                            lexemes.add(new Lexeme(LexemeType.NUMBER, matcher.group()));
                        }
                        pos += matcher.group().length();
                        if (pos >= exp.length()) {
                            break;
                        }
                        c = exp.charAt(pos);
                    }
                    else if (c >= 'a' && c <= 'z') {

                    }
            }
        }
        lexemes.add(new Lexeme(LexemeType.EOF, ""));
        return lexemes;
    }
}