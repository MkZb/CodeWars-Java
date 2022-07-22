package kata33;

import java.util.*;

public class MathEvaluator {

    private enum Token {
        INTEGER,
        REAL,
        PARENTHESES,
        OPERATOR
    }

    private static class Lexer {

        private static final String DELIMITERS = " ()+-/*";

        public static List<Map<Token, String>> parseTokens(String str) {
            String[] symbolsArr = str.split("");
            List<Map<Token, String>> tokensList = new ArrayList<>();

            int left = 0;
            int right = 0;
            List<Integer> insertRightParIndexes = new ArrayList<>();

            while (right < symbolsArr.length && left <= right) {
                while (insertRightParIndexes.contains(right)) {
                    insertRightParIndexes.remove(Integer.valueOf(right));
                    Map<Token, String> map = new HashMap<>();
                    map.put(Token.PARENTHESES, ")");
                    tokensList.add(map);
                }

                if (!isDelimiter(symbolsArr[right])) {
                    right++;
                }

                if (right == symbolsArr.length || (isDelimiter(symbolsArr[right])) && left != right) {
                    String[] subStringArr = new String[right - left];
                    System.arraycopy(symbolsArr, left, subStringArr, 0, right - left);
                    if (isInteger(subStringArr)) parseToken(tokensList, subStringArr, Token.INTEGER);
                    else if (isReal(subStringArr)) parseToken(tokensList, subStringArr, Token.REAL);
                    left = right;
                } else if (isDelimiter(symbolsArr[right]) && right == left) {
                    if (symbolsArr[right].equals("-") &&
                            (tokensList.size() == 0
                                    || tokensList.get(tokensList.size() - 1).containsKey(Token.OPERATOR) ||
                                    (tokensList.get(tokensList.size() - 1).containsKey(Token.PARENTHESES) &&
                                            tokensList.get(tokensList.size() - 1).get(Token.PARENTHESES).equals("(")))) {
                        if (symbolsArr[right + 1].matches("\\d")) {
                            right++;
                        } else {
                            int temp = right;
                            while (!symbolsArr[temp].equals(")")) {
                                temp++;
                            }
                            insertRightParIndexes.add(temp);
                            parseExtraMultiply(tokensList);
                            right++;
                            left = right;
                        }
                    } else {
                        if (isOperator(symbolsArr[right])) parseToken(tokensList, symbolsArr[right], Token.OPERATOR);
                        else if (isParentheses(symbolsArr[right]))
                            parseToken(tokensList, symbolsArr[right], Token.PARENTHESES);
                        right++;
                        left = right;
                    }
                }
            }

            return tokensList;
        }

        private static boolean isDelimiter(String symbol) {
            return DELIMITERS.contains(symbol);
        }

        private static boolean isParentheses(String symbol) {
            return "()".contains(symbol);
        }

        private static boolean isOperator(String symbol) {
            return "+-/*".contains(symbol);
        }

        private static boolean isInteger(String[] symbols) {
            return String.join("", symbols).matches("-?\\d+");
        }

        private static boolean isReal(String[] symbols) {
            return String.join("", symbols).matches("-?\\d+\\.?\\d*");
        }

        private static void parseToken(List<Map<Token, String>> tokensList, String symbol, Token tokenType) {
            Map<Token, String> token = new HashMap<>();
            token.put(tokenType, symbol);
            tokensList.add(token);
        }

        private static void parseToken(List<Map<Token, String>> tokensList, String[] symbols, Token tokenType) {
            Map<Token, String> token = new HashMap<>();
            token.put(tokenType, String.join("", symbols));
            tokensList.add(token);
        }

        private static void parseExtraMultiply(List<Map<Token, String>> tokensList) {
            Map<Token, String> token = new HashMap<>();
            token.put(Token.PARENTHESES, "(");
            tokensList.add(token);
            token = new HashMap<>();
            token.put(Token.INTEGER, "-1");
            tokensList.add(token);
            token = new HashMap<>();
            token.put(Token.OPERATOR, "*");
            tokensList.add(token);
        }

    }

    private class AST {

        interface Nodable {
        }

        private class ConstantNode<T extends Number> implements Nodable {
            private final T num;

            ConstantNode(T num) {
                this.num = num;
            }

            public T getNum() {
                return num;
            }
        }

        private class OperationNode<T extends Nodable, S extends Nodable> implements Nodable {
            private final T el1;
            private final S el2;
            private final Operation op;

            OperationNode(T el1, S el2, Operation op) {
                this.el1 = el1;
                this.el2 = el2;
                this.op = op;
            }

            public T getEl1() {
                return el1;
            }

            public S getEl2() {
                return el2;
            }

            public Operation getOp() {
                return op;
            }
        }

        private final Nodable root;

        private enum Operation {
            ADDITION,
            SUBTRACTION,
            DIVISION,
            MULTIPLICATION
        }

        AST(List<Map<Token, String>> tokensList) {
            root = parseExprSum(tokensList);
        }

        public Nodable getRoot() {
            return root;
        }

        private Nodable parseExprSum(List<Map<Token, String>> tokensToParse) {
            boolean isComposite = false;
            int idx = 0;
            int parenthesesCount = 0;
            for (Map<Token, String> token : tokensToParse) {
                if (parenthesesCount == 0 && token.containsKey(Token.OPERATOR) && token.get(Token.OPERATOR).equals("+")) {
                    isComposite = true;
                    break;
                }
                if (token.containsKey(Token.PARENTHESES)) {
                    parenthesesCount = token.get(Token.PARENTHESES).equals("(") ? parenthesesCount + 1 : parenthesesCount - 1;
                }
                idx++;
            }
            if (!isComposite) return parseExprSub(tokensToParse);
            else {
                return new OperationNode<>(parseExprSub(tokensToParse.subList(0, idx)),
                        parseExprSum(tokensToParse.subList(idx + 1, tokensToParse.size())),
                        Operation.ADDITION);
            }
        }

        private Nodable parseExprSub(List<Map<Token, String>> tokensToParse) {
            boolean isComposite = false;
            int idx = 0;
            int parenthesesCount = 0;
            for (Map<Token, String> token : tokensToParse) {
                if (parenthesesCount == 0 && token.containsKey(Token.OPERATOR) && token.get(Token.OPERATOR).equals("-")) {
                    isComposite = true;
                    break;
                }
                if (token.containsKey(Token.PARENTHESES)) {
                    parenthesesCount = token.get(Token.PARENTHESES).equals("(") ? parenthesesCount + 1 : parenthesesCount - 1;
                }
                idx++;
            }
            if (!isComposite) return parseTermMultiply(tokensToParse);
            else {
                return new OperationNode<>(parseTermMultiply(tokensToParse.subList(0, idx)),
                        parseExprSub(tokensToParse.subList(idx + 1, tokensToParse.size())),
                        Operation.SUBTRACTION);
            }
        }

        private Nodable parseTermMultiply(List<Map<Token, String>> tokensToParse) {
            boolean isComposite = false;
            int idx = 0;
            int parenthesesCount = 0;
            for (Map<Token, String> token : tokensToParse) {
                if (parenthesesCount == 0 && token.containsKey(Token.OPERATOR) && token.get(Token.OPERATOR).equals("*")) {
                    isComposite = true;
                    break;
                }
                if (token.containsKey(Token.PARENTHESES)) {
                    parenthesesCount = token.get(Token.PARENTHESES).equals("(") ? parenthesesCount + 1 : parenthesesCount - 1;
                }
                idx++;
            }
            if (!isComposite) return parseTermDivide(tokensToParse);
            else {
                return new OperationNode<>(parseTermDivide(tokensToParse.subList(0, idx)),
                        parseTermMultiply(tokensToParse.subList(idx + 1, tokensToParse.size())),
                        Operation.MULTIPLICATION);
            }
        }

        private Nodable parseTermDivide(List<Map<Token, String>> tokensToParse) {
            boolean isComposite = false;
            int idx = 0;
            int parenthesesCount = 0;
            for (Map<Token, String> token : tokensToParse) {
                if (parenthesesCount == 0 && token.containsKey(Token.OPERATOR) && token.get(Token.OPERATOR).equals("/")) {
                    isComposite = true;
                    break;
                }
                if (token.containsKey(Token.PARENTHESES)) {
                    parenthesesCount = token.get(Token.PARENTHESES).equals("(") ? parenthesesCount + 1 : parenthesesCount - 1;
                }
                idx++;
            }
            if (!isComposite) return parseFactor(tokensToParse);
            else {
                return new OperationNode<>(parseFactor(tokensToParse.subList(0, idx)),
                        parseTermDivide(tokensToParse.subList(idx + 1, tokensToParse.size())),
                        Operation.DIVISION);
            }
        }

        private Nodable parseFactor(List<Map<Token, String>> tokensToParse) {
            if (tokensToParse.get(0).containsKey(Token.PARENTHESES) && tokensToParse.get(0).get(Token.PARENTHESES).equals("(")) {
                return parseExprSum(tokensToParse.subList(1, tokensToParse.size() - 1));
            } else {
                return parseConst(tokensToParse.get(0));
            }
        }

        private ConstantNode parseConst(Map<Token, String> tokenToParse) {
            if (tokenToParse.containsKey(Token.INTEGER)) {
                return new ConstantNode<>(Integer.valueOf(tokenToParse.get(Token.INTEGER)));
            } else if (tokenToParse.containsKey(Token.REAL)) {
                return new ConstantNode<>(Double.valueOf(tokenToParse.get(Token.REAL)));
            }
            return null;
        }

    }

    class Evaluator {

        private final AST.Nodable root;

        Evaluator(AST ast) {
            root = ast.getRoot();
        }

        public double evaluate() {
            return parseNode(root);
        }

        public double parseNode(AST.Nodable node) {
            if (node instanceof AST.ConstantNode<?> constantNode) {
                return Double.parseDouble(constantNode.getNum().toString());
            } else if (node instanceof AST.OperationNode<?, ?> operationNode) {
                double el1 = parseNode(operationNode.getEl1());
                double el2 = parseNode(operationNode.getEl2());
                switch (operationNode.getOp()) {
                    case ADDITION -> {
                        return el1 + el2;
                    }
                    case SUBTRACTION -> {
                        return el1 - el2;
                    }
                    case MULTIPLICATION -> {
                        return el1 * el2;
                    }
                    case DIVISION -> {
                        return el1 / el2;
                    }
                }
            }
            return 0;
        }

    }

    public double calculate(String expression) {
        List<Map<Token, String>> tokens = Lexer.parseTokens(expression);
        AST ast = new AST(tokens);
        Evaluator eval = new Evaluator(ast);
        return eval.evaluate();
    }

}
