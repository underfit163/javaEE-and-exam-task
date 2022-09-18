import java.io.*;

public class LinkedListVector implements Serializable {
    private class Node implements Serializable {
        double value = Double.NaN;
        Node next = null;
        Node prev = null;

        public Node() {
        }

        public Node(double value) {
            this.value = value;
        }
    }

    private Node head = new Node();

    {
        head.next = head;
        head.prev = head;
    }

    private int size = 0;

    public LinkedListVector(int n) {
        for (int i = 0; i < n; i++) {
            addNodeBeforeHead(Math.random());
        }
    }

    public void addNodeAfterHead(double val) {
        Node node = new Node(val);
        if (head.next == head) {
            head.next = node;
            head.prev = node;
            node.next = head;
            node.prev = head;
        } else {
            node.prev = head;
            node.next = head.next;
            head.next = node;
            head.next.prev = node;
        }
        size++;
    }

    public void addNodeBeforeHead(double val) {
        Node node = new Node(val);
        if (head.next == head) {
            head.next = node;
            head.prev = node;
            node.next = head;
            node.prev = head;
        } else {
            node.next = head;
            node.prev = head.prev;
            head.prev.next = node;
            head.prev = node;
        }
        size++;
    }

    private Node gotoNumber(int index) {
        Node p = head.next;
        if (index >= size) {
            System.out.println("Error!");
        } else {
            int i = 0;
            while (p != head && i != index) {
                p = p.next;
                i++;
            }
        }
        return p;
    }

    public double getValueByIndex(int index) {
        return gotoNumber(index).value;
    }

    public void setValue(int index, double value) {
        gotoNumber(index).value = value;
    }

    public int getSize() {
        return size;
    }

    public void deleteValue(int index) {
        Node p = gotoNumber(index);
        p.prev.next = p.next;
        p.next.prev = p.prev;
        size--;
    }
}
