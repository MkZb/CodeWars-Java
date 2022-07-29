package kata41;

import java.util.ArrayList;
import java.util.List;

public class ScreenLock {
    public int calculateCombinations(char startPosition, int patternLength) {
        if (patternLength > 9 || patternLength < 1) return 0;
        Tree tree = new Tree(startPosition, patternLength - 1);
        return tree.getCombinations();
    }

    class Tree {
        private final int combinations;

        Tree(char startingNode, int depth) {
            Node root = new Node(startingNode);
            List<Node> nodesList = new ArrayList<>(List.of(root));
            while (depth-- != 0) {
                List<Node> temp = new ArrayList<>();
                for (Node node : nodesList) {
                    temp.addAll(genChildren(node));
                }
                nodesList = temp;
            }
            combinations = nodesList.size();
        }

        public int getCombinations() {
            return combinations;
        }

        private List<Node> genChildren(Node node) {
            char[][] layout = new char[][]{
                    {'A', 'B', 'C'},
                    {'D', 'E', 'F'},
                    {'G', 'H', 'I'}
            };

            int x = 0, y = 0;
            for (Character ch : node.getUsedNodeValues()) {
                for (int i = 0; i < layout.length; i++) {
                    for (int j = 0; j < layout[i].length; j++) {
                        if (layout[i][j] == node.getVal()) {
                            x = i;
                            y = j;
                        }
                        if (layout[i][j] == ch) layout[i][j] = 0;
                    }
                }
            }

            for (int i = 0; i < layout.length; i++) {
                for (int j = 0; j < layout[i].length; j++) {
                    if (layout[i][j] != 0) {
                        if (Math.abs(x - i) > 1 && Math.abs(y - j) > 1) {
                            if (layout[1][1] == 0) node.addChild(new Node(layout[i][j]));
                        } else if (Math.abs(x - i) > 1) {
                            if (y == j) {
                                if (layout[1][j] == 0) node.addChild(new Node(layout[i][j]));
                            } else {
                                node.addChild(new Node(layout[i][j]));
                            }
                        } else if (Math.abs(y - j) > 1) {
                            if (x == i) {
                                if (layout[i][1] == 0) node.addChild(new Node(layout[i][j]));
                            } else {
                                node.addChild(new Node(layout[i][j]));
                            }
                        } else {
                            node.addChild(new Node(layout[i][j]));
                        }
                    }
                }
            }

            return node.getChildren();
        }
    }

    class Node {
        private final char val;
        private Node parent;
        private final List<Node> children;

        Node(char val) {
            this.val = val;
            children = new ArrayList<>();
        }

        public void addChild(Node child) {
            child.setParent(this);
            children.add(child);
        }

        public Node getParent() {
            return parent;
        }

        private void setParent(Node parent) {
            this.parent = parent;
        }

        public List<Node> getChildren() {
            return children;
        }

        public char getVal() {
            return val;
        }

        public List<Character> getUsedNodeValues() {
            List<Character> chars = new ArrayList<>();
            Node currentNode = this;
            while (currentNode != null) {
                chars.add(currentNode.getVal());
                currentNode = currentNode.getParent();
            }
            return chars;
        }
    }
}
