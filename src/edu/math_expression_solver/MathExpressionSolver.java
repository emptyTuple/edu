package edu.math_expression_solver;

import java.util.ArrayList;
import java.util.HashMap;
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
FACTOR : '-' FACTOR | NUMBER | '(' EXPRESSION ')' | FUNC '(' ( EXPRESSION ( ',' EXPRESSION )* )? ')'
 */

public class MathExpressionSolver {

    private final static HashMap<String, Function> embeddedFunc;
    private String expression;
    private LexemeBuffer lexemes;

    static {
        embeddedFunc = addFunction();
    }

    public MathExpressionSolver(String expression) {
        this.lexemes = new LexemeBuffer(parseLexemes(expression));
    }

    public interface Function {
        double apply(List<Double> args);
    }

    public static HashMap<String, Function> addFunction() {
        HashMap<String, Function> functions = new HashMap<>();

        functions.put("sin", args -> {
            if (args.size() != 1) {
                throw new IllegalArgumentException("Wrong arguments amount for function 'SIN'");
            }
            return (double) Math.sin(args.get(0));
        });

        functions.put("cos", args -> {
            if (args.size() != 1) {
                throw new IllegalArgumentException("Wrong arguments amount for function 'COS'");
            }
            return (double) Math.cos(args.get(0));
        });

        functions.put("tan", args -> {
            if (args.size() != 1) {
                throw new IllegalArgumentException("Wrong arguments amount for function 'TAN'");
            }
            return (double) Math.tan(args.get(0));
        });

        return functions;
    }

    public List<Lexeme> parseLexemes(String exp) {
        exp = exp.replaceAll(" ", "").toLowerCase();
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        int pos = 0;
        while (pos < exp.length()) {
            char c = exp.charAt(pos);
            switch (c) {
                case '+' -> {
                    lexemes.add(new Lexeme(LexemeType.PLUS, c));
                    pos++;
                    continue;
                }
                case '-' -> {
                    lexemes.add(new Lexeme(LexemeType.MINUS, c));
                    pos++;
                    continue;
                }
                case '*' -> {
                    lexemes.add(new Lexeme(LexemeType.MUL, c));
                    pos++;
                    continue;
                }
                case '/' -> {
                    lexemes.add(new Lexeme(LexemeType.DIV, c));
                    pos++;
                    continue;
                }
                case '(' -> {
                    lexemes.add(new Lexeme(LexemeType.LEFT_BRACKET, c));
                    pos++;
                    continue;
                }
                case ')' -> {
                    lexemes.add(new Lexeme(LexemeType.RIGHT_BRACKET, c));
                    pos++;
                    continue;
                }
                case '^' -> {
                    lexemes.add(new Lexeme(LexemeType.POWER, c));
                    pos++;
                    continue;
                }
                default -> {
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
                    if (c == 'p') {
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
                    if (c == 'e') {
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
                    if (c >= 'a' && c <= 'z') {
                        StringBuilder sb = new StringBuilder();
                        do {
                            sb.append(c);
                            pos++;
                            if (pos >= exp.length()) {
                                break;
                            }
                            c = exp.charAt(pos);
                        } while (c >= 'a' && c <= 'z');
                        if (embeddedFunc.containsKey(sb.toString())) {
                            lexemes.add(new Lexeme(LexemeType.FUNC, sb.toString()));
                        } else {
                            throw new RuntimeException("Unexpected function name: " + sb);
                        }
                    }
                }
            }
        }
        lexemes.add(new Lexeme(LexemeType.EOF, ""));
        return lexemes;
    }

    public static double factor(LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.next();
        double value;
        switch (lexeme.type) {
            case NUMBER:
                return Double.parseDouble(lexeme.value);
            case MINUS:
                value = factor(lexemes);
                return - value;
            case FUNC:
                lexemes.prev();
                return func(lexemes);
            case LEFT_BRACKET:
                value = expr(lexemes);
                lexemes.next();
                if (lexeme.type != LexemeType.RIGHT_BRACKET) {
                    throw new IllegalArgumentException("Unexpected token => " +
                            lexeme.value + "at position: " + lexemes.getPos());
                }
                return value;
            default:
                throw new IllegalArgumentException("Unexpected token => " +
                        lexeme.value + "at position: " + lexemes.getPos());
        }
    }

    private static double pow(LexemeBuffer lexemes) {
        double value = factor(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case POWER:
                    value = Math.pow(value, factor(lexemes));
                    break;
                default:
                    lexemes.prev();
                    return value;
            }
        }
    }

    private static double muldiv(LexemeBuffer lexemes) {
        double value = pow(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case MUL:
                    value *= pow(lexemes);
                    break;
                case DIV:
                    value /= pow(lexemes);
                    break;
                default:
                    lexemes.prev();
                    return value;
            }
        }
    }

    private static double plusminis(LexemeBuffer lexemes) {
        return 0f;
    }

    private static double expr(LexemeBuffer lexemes) {
        return 0f;
    }

    private static double func(LexemeBuffer lexemes) {
        return 0f;
    }

}
