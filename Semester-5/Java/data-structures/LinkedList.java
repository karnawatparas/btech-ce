public class LinkedList {

    private Node head;

    public static class Node {
        int data;
        Node next;

        public Node() {
            this.next = null;
        }

        public Node(int data) {
            this.data = data;
            this.next = null;
        }

        public void setNext(Node n) {
            this.next = n;
        }

        public Node getNext() {
            return this.next;
        }

        public void setData(int d) {
            this.data = d;
        }

        public int getData() {
            return this.data;
        }

    }

    public LinkedList() {
        head = null;
    }

    public void sortedInsert(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {

            if (head.getData() > data) {
                newNode.next = head;
                head = newNode;
            } else {
                Node current = head;
                while (current.next != null && current.next.data < data) {
                    current = current.getNext();
                }
                newNode.next = current.next;
                current.next = newNode;
            }
        }
    }

    public void deleteNode(int data) {
        if (head.getData() == data) {
            head = head.next;
        } else {
            Node current = head.next;
            Node previous = head;
            while (current != null && current.getData() != data) {
                previous = current;
                current = current.next;
            }
            if (current != null) {
                previous.next = current.next;
            } else {
                System.out.format("%d not found in the list.\n", data);
            }
        }
    }

    @Override
    public String toString() {
        String output = "List : [ ";
        Node current = head;
        while (current != null) {
            output += String.valueOf(current.getData()) + " ";
            current = current.next;
        }
        output += "]\n";
        return output;
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        System.out.println();

        list.sortedInsert(10);
        System.out.println(list);

        list.sortedInsert(30);
        System.out.println(list);

        list.sortedInsert(20);
        System.out.println(list);

        list.sortedInsert(0);
        System.out.println(list);

        list.sortedInsert(40);
        System.out.println(list);

        list.deleteNode(20);
        System.out.println(list);

        list.deleteNode(20);
        System.out.println(list);

    }

}