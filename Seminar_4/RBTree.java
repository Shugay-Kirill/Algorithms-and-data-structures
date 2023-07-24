package Seminar_4;

import java.util.ArrayList;
import java.util.List;

public class RBTree <T extends Comparable<T>>{
    
    private class Node{
        private T value;
        private Color color;
        private Node left;
        private Node rigth;
    
        @Override
        public String toString(){
            return "Node{" + 
                    "value = " + value + 
                    ", color = " + color + 
                    "}"; 
        }
    }

    private enum Color {
        RED, BLACK
    }

    private Node root;

    private boolean addNode(Node node, T value){
        if(node.value.compareTo(value) == 0){
            return false;
        } else {
            if(node.value.compareTo(value) > 0){
                if(node.left != null){
                    boolean result = addNode(node.left, value);
                    node.left = rebalance(node.left);
                    return result;
                } else {
                    node.left = new Node();
                    node.left.color = Color.RED;
                    node.left.value = value;
                    return true;
                }
            } else {
                if(node.rigth != null){
                    boolean result = addNode(node.rigth, value);
                    node.rigth = rebalance(node.rigth);
                    return result;
                } else {
                    node.rigth = new Node();
                    node.rigth.color = Color.RED;
                    node.rigth.value = value;
                    return true;
                }
            }
        }
    }    

    public boolean add(T value){
        if (root != null){
            boolean result = addNode(root, value);
            root = rebalance(root);
            root.color = Color.BLACK;
            return result;
        } else {
            root = new Node();
            root.color = Color.BLACK;
            root.value = value;
            return true;
        }
    }

    private Node rebalance(Node node){
        Node result = node;
        boolean needRebalance;
        do {
            needRebalance = false;
            if (result.rigth != null && result.rigth.color == Color.RED && 
                    (result.left == null || result.left.color == Color.BLACK)){
                needRebalance = true;
                result = rightSwap(result);
            }
            if (result.left != null && result.left.color == Color.RED && 
                    result.left.left != null && result.left.left.color == Color.RED){
                needRebalance = true;
                result = leftSwap(result);
            }
            if (result.left != null && result.left.color == Color.RED && 
                    result.rigth != null && result.rigth.color == Color.RED){
                needRebalance = true;
                colorSwap(result);
            }
        }
        while (needRebalance);
        return result;
    }

    private Node leftSwap(Node node){
        Node left = node.left;
        Node between = left.rigth;
        left.rigth = node;
        node.left = between;
        left.color = node.color;
        node.color = Color.RED;
        return left;
    }

    private Node rightSwap(Node node){
        Node rigth = node.rigth;
        Node between = rigth.left;
        rigth.left = node;
        node.rigth = between;
        rigth.color = node.color;
        node.color = Color.RED;
        return rigth;
    }

    private void colorSwap(Node node){
        node.rigth.color = Color.BLACK;
        node.left.color = Color.BLACK;
        node.color = Color.RED;
    }


        private class PrintNode {
        Node node;
        String str;
        int depth;

        public PrintNode() {
            node = null;
            str = " ";
            depth = 0;
        }

        public PrintNode(Node node) {
            depth = 0;
            this.node = node;
            this.str = node.value.toString();
        }
    }

    public void print() {

        int maxDepth = maxDepth() + 3;
        int nodeCount = nodeCount(root, 0);
        int width = 50;//maxDepth * 4 + 2;
        int height = nodeCount * 5;
        List<List<PrintNode>> list = new ArrayList<List<PrintNode>>();
        for (int i = 0; i < height; i++) /*РЎРѕР·РґР°РЅРёРµ СЏС‡РµРµРє РјР°СЃСЃРёРІР°*/ {
            ArrayList<PrintNode> row = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                row.add(new PrintNode());
            }
            list.add(row);
        }

        list.get(height / 2).set(0, new PrintNode(root));
        list.get(height / 2).get(0).depth = 0;

        for (int j = 0; j < width; j++)  /*РџСЂРёРЅС†РёРї Р·Р°РїРѕР»РЅРµРЅРёСЏ*/ {
            for (int i = 0; i < height; i++) {
                PrintNode currentNode = list.get(i).get(j);
                if (currentNode.node != null) {
                    currentNode.str = currentNode.node.value.toString();
                    if (currentNode.node.left != null) {
                        int in = i + (maxDepth / (int) Math.pow(2, currentNode.depth));
                        int jn = j + 3;
                        printLines(list, i, j, in, jn);
                        list.get(in).get(jn).node = currentNode.node.left;
                        list.get(in).get(jn).depth = list.get(i).get(j).depth + 1;

                    }
                    if (currentNode.node.rigth != null) {
                        int in = i - (maxDepth / (int) Math.pow(2, currentNode.depth));
                        int jn = j + 3;
                        printLines(list, i, j, in, jn);
                        list.get(in).get(jn).node = currentNode.node.rigth;
                        list.get(in).get(jn).depth = list.get(i).get(j).depth + 1;
                    }

                }
            }
        }
        for (int i = 0; i < height; i++) /*Р§РёСЃС‚РєР° РїСѓСЃС‚С‹С… СЃС‚СЂРѕРє*/ {
            boolean flag = true;
            for (int j = 0; j < width; j++) {
                if (list.get(i).get(j).str != " ") {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                list.remove(i);
                i--;
                height--;
            }
        }

        for (var row : list) {
            for (var item : row) {
                System.out.print(item.str + " ");
            }
            System.out.println();
        }
    }

    private void printLines(List<List<PrintNode>> list, int i, int j, int i2, int j2) {
        if (i2 > i) // РРґС‘Рј РІРЅРёР·
        {
            while (i < i2) {
                i++;
                list.get(i).get(j).str = "|";
            }
            list.get(i).get(j).str = "\\";
            while (j < j2) {
                j++;
                list.get(i).get(j).str = "-";
            }
        } else {
            while (i > i2) {
                i--;
                list.get(i).get(j).str = "|";
            }
            list.get(i).get(j).str = "/";
            while (j < j2) {
                j++;
                list.get(i).get(j).str = "-";
            }
        }
    }

    public int maxDepth() {
        return maxDepth2(0, root);
    }

    private int maxDepth2(int depth, Node node) {
        depth++;
        int left = depth;
        int right = depth;
        if (node.left != null)
            left = maxDepth2(depth, node.left);
        if (node.rigth != null)
            right = maxDepth2(depth, node.rigth);
        return left > right ? left : right;
    }

    private int nodeCount(Node node, int count) {
        if (node != null) {
            count++;
            return count + nodeCount(node.left, 0) + nodeCount(node.rigth, 0);
        }
        return count;
    }
}
