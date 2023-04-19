package edu.math_expression_solver;

import javax.xml.crypto.dsig.spec.XPathType;
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
POWER : FACTOR ( '^' FACTOR )*
FACTOR : '-' FACTOR | NUMBER | '(' EXPRESSION ')' | FUNC '(' ( EXPRESSION ( ',' EXPRESSION )* )? ')' | PI | E
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

                            pos += matcher.group().length();
                            if (pos >= exp.length()) {
                                break;
                            }
                            c = exp.charAt(pos);
                        }
                    }
                    else if (c == 'p') {
                        Pattern p1 = Pattern.compile("pi(?=[^a-z])");
                        Matcher m1 = p1.matcher(exp).region(pos, exp.length());
                        if (m1.find()) {
                            lexemes.add(new Lexeme(LexemeType.PI, String.valueOf(Math.PI)));
                            pos = pos + 2;
                            if (pos >= exp.length()) {
                                break;
                            }
                            c = exp.charAt(pos);
                        }
                    }
                    else if(c == 'e') {
                        Pattern p2 = Pattern.compile("e(?=[^a-z])");
                        Matcher m2 = p2.matcher(exp).region(pos, exp.length());
                        if (m2.find()) {
                            lexemes.add(new Lexeme(LexemeType.E, String.valueOf(Math.E)));
                            pos++;
                            if (pos >= exp.length()) {
                                break;
                            }
                            c = exp.charAt(pos);
                        }
                    }
            }
        }
        lexemes.add(new Lexeme(LexemeType.EOF, ""));
        return lexemes;
    }
}
