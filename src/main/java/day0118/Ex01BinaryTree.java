package day0118;

import java.util.ArrayList;
import java.util.HashSet;

public class Ex01BinaryTree {
    static int[] array = {3, 7, 1, 2, 4, 0};
    static int currentIndex = 0;

    public static void main(String[] args) {
        Node zero = new Node();
        zero.value = array[currentIndex++];
        for (int i = 1; i < array.length; i++) {
            insert(zero, array[i]);
        }
/*
        System.out.println(zero.value);
        System.out.println(zero.left.value);
        System.out.println(zero.right.value);
        System.out.println(zero.left.left.value);
        System.out.println(zero.left.right.value);
        System.out.println(zero.right.left.value);*/

        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");

        System.out.println(list.size());

        String str = "f";

        list.remove(str);
        System.out.println(list.size());

        HashSet<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        set.add("ac");
        set.add("ad");



    }

    public static void insert(Node parent, int value) {
        if (parent.value < value) {
            if (parent.right == null) {
                parent.right = createNode(value);
            } else {
                insert(parent.right, value);
            }
        } else {
            if (parent.left == null) {
                parent.left = createNode(value);
            } else {
                insert(parent.left, value);
            }
        }
    }

    public static Node createNode(int value) {
        Node n = new Node();
        n.value = value;

        return n;
    }
}














