package my.parse;

import my.math.*;

import java.text.ParseException;
import java.util.*;

public class Calc {

    private final String OPERATORS = "+-*/";

    Stack<String> stackRPN;

    public Calc(String expression) throws ParseException {
        stackRPN = this.parse(expression);
    }

    public String evaluate() throws ParseException {
        return this.evaluate(new HashMap<String, Integer>());
    }

    public String evaluate(Map<String, Integer> context) throws ParseException {
        if (stackRPN.empty()) {
            return "";
        }
        Stack<Evaluable> stackResult = new Stack<>();
        Stack<String> stackRPN = (Stack<String>) this.stackRPN.clone();

        while (!stackRPN.empty()) {
            String token = stackRPN.pop();
            if (isNumber(token)) {
                stackResult.push(new Const(Integer.parseInt(token)));
            } else if (isVariable(token)) {
                if (context.get(token) != null) {
                    stackResult.push(new Variable(token));
                } else {
                    throw new RuntimeException("Variable " + token + " not found");
                }
            } else if (isOperator(token)) {
                Evaluable a = stackResult.pop();
                Evaluable b = stackResult.pop();
                Evaluable result = new Const(0);
                switch (token) {
                    case "+":
                        result = new Sum(a, b);
                        break;
                    case "-":
                        result = new Subtract(b, a);
                        break;
                    case "*":
                        result = new Multiply(a, b);
                        break;
                    case "/":
                        result = new Division(b, a);
                        break;
                }
                /*if (result > Integer.MAX_VALUE) {
                    throw new RuntimeException("Overflow occurred");
                } else if (result < Integer.MIN_VALUE) {
                    throw new RuntimeException("Underflow occurred");
                }*/
                stackResult.push(result);
            }
        }
        return Integer.toString(stackResult.pop().evaluate(context));
    }

    Stack<String> parse(String expression) throws ParseException {
        Stack<String> stackOperations = new Stack<>();
        Stack<String> stackRPN = new Stack<>();
        expression = expression.replace(" ","")
                .replace("(-", "(0-")
                .replace("(+", "(0+");
        if (expression.charAt(0) == '-' || expression.charAt(0) == '+') {
            expression = "0" + expression;
        }

        StringTokenizer stringTokenizer = new StringTokenizer(expression, OPERATORS + "()", true);
        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            if (isNumber(token) || isVariable(token)) {
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
            } else {
                throw new ParseException("Unrecognized token: " + token, 0);
            }
        }
        while (!stackOperations.empty()) {
            stackRPN.push(stackOperations.pop());
        }
        Collections.reverse(stackRPN);
        return stackRPN;
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
            int a = Integer.parseInt(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean isVariable(String token) {
        return token.matches("[a-z]");
    }

    private byte getPrecedence(String token) {
        if (token.equals("+") || token.equals("-")) {
            return 1;
        }
        return 2;
    }
}
