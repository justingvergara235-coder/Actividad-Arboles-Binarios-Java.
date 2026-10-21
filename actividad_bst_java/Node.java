public class Node<E> {
    private E value;
    private int key;
    private Node<E> left;
    private Node<E> right;

    public Node() { }
    public Node(E value) { this.value = value; }
    public Node(int key) { this.key = key; }
    public Node(int key, E value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() { return key; }
    public void setKey(int newKey) { this.key = newKey; }
    public E getValue() { return value; }
    public void setValue(E value) { this.value = value; }
    public Node<E> getLeft() { return left; }
    public Node<E> getRight() { return right; }
    public void setLeft(Node<E> newLeft) { this.left = newLeft; }
    public void setRight(Node<E> newRight) { this.right = newRight; }
}
