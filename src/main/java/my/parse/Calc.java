package my.parse;

import my.math.Const;
import my.math.Multiply;
import my.math.Subtract;

import java.text.ParseException;
import java.util.*;

/**
 * Created by Taras S on 29.10.2014.
 */
public class Calc {

    private final String OPERATORS = "+-*/";

    private String expression;

    private Stack<String> stackOperations = new Stack<String>();

    private Stack<String> stackRPN = new Stack<String>();

    private Stack<String> stackResult = new Stack<String>();

    public Calc(String expression) {
        this.expression = expression;
    }

    public String evaluate() throws ParseException {
        return this.evaluate(new HashMap<String, Integer>());
    }

    public String evaluate(Map<String, Integer> context) throws ParseException {
        this.parse(context);

        if (stackRPN.empty()) {
            return "";
        }
        stackResult.clear();
        Stack<String> stackRPN = (Stack<String>) this.stackRPN.clone();

        while (!stackRPN.empty()) {
            String token = stackRPN.pop();
            if (isNumber(token)) {
                stackResult.push(token);
            } else if (isOperator(token)) {
                int a = Integer.parseInt(stackResult.pop());
                int b = Integer.parseInt(stackResult.pop());
                long result = 0;
                switch (token) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = new Subtract(new Const(b), new Const(a)).evaluate();
                        break;
                    case "*":
                        result = a * b;
                        break;
                    case "/":
                        if (a == 0) {
                            throw new IllegalArgumentException("division by zero");
                        }
                        result = b / a;
                        break;
                }
                if (result > Integer.MAX_VALUE) {
                    throw new RuntimeException("Overflow occured");
                }
                stackResult.push(Long.toString(result));
            }
        }
        if (stackResult.size() > 1) {
            throw new ParseException("Some operator is missing", 0);
        }
        return stackResult.pop();
    }

    String parse(Map<String, Integer> context) throws ParseException {
        stackOperations.clear();
        stackRPN.clear();
        expression = expression.replace(" ","")
                .replace("(-", "(0-")
                .replace("(+", "(0+");
        if (expression.charAt(0) == '-' || expression.charAt(0) == '+') {
            expression = "0" + expression;
        }

        StringTokenizer stringTokenizer = new StringTokenizer(expression, OPERATORS + "()", true);
        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            if (isNumber(token)) {
                stackRPN.push(token);
            } else if (isOperator(token)) {
                while (!stackOperations.empty()
                        && isOperator(stackOperations.lastElement())
                        && getPrecedence(token) <= getPrecedence(stackOperations.lastElement())) {
                    stackRPN.push(stackOperations.pop());
                }
                stackOperations.push(token);
            } else if (isOpenBracket(token)) {
                stackOperations.push(token);
            } else if (isCloseBracket(token)) {
                while (!stackOperations.empty() && !isOpenBracket(stackOperations.lastElement())) {
                    stackRPN.push(stackOperations.pop());
                }
                stackOperations.pop();
            } else if (context.get(token) != null) {
                stackRPN.push(context.get(token).toString());
            } else {
                throw new ParseException("Unrecognized token: " + token, 0);
            }
        }
        while (!stackOperations.empty()) {
            stackRPN.push(stackOperations.pop());
        }
        Collections.reverse(stackRPN);
        return stackRPN.toString();
    }

    private boolean isOperator(String token) {
        return OPERATORS.contains(token);
    }

    private boolean isOpenBracket(String token) {
        return token.equals("(");
    }

    private boolean isCloseBracket(String token) {
        return token.equals(")");
    }

    private boolean isNumber(String token) {
        try {
            Integer.parseInt(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private byte getPrecedence(String token) {
        if (token.equals("+") || token.equals("-")) {
            return 1;
        }
        return 2;
    }
}
