import java.util.ArrayList;

public class BinarySearchTree {
    private Node<Cliente> root;

    public BinarySearchTree() { root = null; }
    public BinarySearchTree(Node<Cliente> root) { this.root = root; }
    public Node<Cliente> getRoot() { return root; }

    public void insertNode(Node<Cliente> newNode) {
        root = insertRecursive(newNode, root);
    }

    // Política para duplicados: las claves iguales se insertan en la rama derecha.
    public Node<Cliente> insertRecursive(Node<Cliente> nodeToInsert, Node<Cliente> rootNode) {
        if (rootNode == null) return nodeToInsert;

        if (nodeToInsert.getKey() < rootNode.getKey()) {
            rootNode.setLeft(insertRecursive(nodeToInsert, rootNode.getLeft()));
        } else {
            // Incluye claves mayores e iguales; así no se descartan clientes.
            rootNode.setRight(insertRecursive(nodeToInsert, rootNode.getRight()));
        }
        return rootNode;
    }

    public boolean searchNodeRecursive(int key, Node<Cliente> rootNode) {
        return buscarNodo(key, rootNode) != null;
    }

    public Node<Cliente> buscarNodo(int key, Node<Cliente> rootNode) {
        if (rootNode == null) return null;
        if (key == rootNode.getKey()) return rootNode;
        if (key < rootNode.getKey()) return buscarNodo(key, rootNode.getLeft());
        return buscarNodo(key, rootNode.getRight());
    }

    public void InOrder(Node<Cliente> node) {
        if (node == null) return;
        InOrder(node.getLeft());
        System.out.println("->" + node.getKey());
        InOrder(node.getRight());
    }

    public void preOrder(Node<Cliente> node) {
        if (node == null) return;
        System.out.println("->" + node.getKey());
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }

    public void posOrder(Node<Cliente> node) {
        if (node == null) return;
        posOrder(node.getLeft());
        posOrder(node.getRight());
        System.out.println("->" + node.getKey());
    }

    public ArrayList<Integer> getKeyList() {
        ArrayList<Integer> keyList = new ArrayList<>();
        agregarClavesInOrder(root, keyList);
        return keyList;
    }

    private void agregarClavesInOrder(Node<Cliente> node, ArrayList<Integer> lista) {
        if (node == null) return;
        agregarClavesInOrder(node.getLeft(), lista);
        lista.add(node.getKey());
        agregarClavesInOrder(node.getRight(), lista);
    }

    public ArrayList<Cliente> clientesAscendente() {
        ArrayList<Cliente> lista = new ArrayList<>();
        clientesInOrder(root, lista);
        return lista;
    }

    private void clientesInOrder(Node<Cliente> node, ArrayList<Cliente> lista) {
        if (node == null) return;
        clientesInOrder(node.getLeft(), lista);
        lista.add(node.getValue());
        clientesInOrder(node.getRight(), lista);
    }

    public ArrayList<Cliente> mayoresN(int n) {
        ArrayList<Cliente> lista = new ArrayList<>();
        if (n <= 0) return lista;
        mayoresNRecursivo(root, n, lista);
        return lista;
    }

    // In-order inverso: derecha, nodo, izquierda. Se detiene al reunir n clientes.
    private void mayoresNRecursivo(Node<Cliente> node, int n, ArrayList<Cliente> lista) {
        if (node == null || lista.size() >= n) return;
        mayoresNRecursivo(node.getRight(), n, lista);
        if (lista.size() < n) lista.add(node.getValue());
        if (lista.size() < n) mayoresNRecursivo(node.getLeft(), n, lista);
    }

    public ArrayList<Cliente> clientesEnRango(int min, int max) {
        ArrayList<Cliente> lista = new ArrayList<>();
        if (min > max) return lista;
        clientesEnRangoRecursivo(root, min, max, lista);
        return lista;
    }

    private void clientesEnRangoRecursivo(Node<Cliente> node, int min, int max,
                                           ArrayList<Cliente> lista) {
        if (node == null) return;
        if (node.getKey() > min) clientesEnRangoRecursivo(node.getLeft(), min, max, lista);
        if (node.getKey() >= min && node.getKey() <= max) lista.add(node.getValue());
        if (node.getKey() < max) clientesEnRangoRecursivo(node.getRight(), min, max, lista);
    }

    public Cliente minimo() {
        Node<Cliente> node = root;
        if (node == null) return null;
        while (node.getLeft() != null) node = node.getLeft();
        return node.getValue();
    }

    public Cliente maximo() {
        Node<Cliente> node = root;
        if (node == null) return null;
        while (node.getRight() != null) node = node.getRight();
        return node.getValue();
    }

    // Altura: árbol vacío = -1; hoja = 0.
    public int altura(Node<Cliente> rootNode) {
        if (rootNode == null) return -1;
        int alturaIzquierda = altura(rootNode.getLeft());
        int alturaDerecha = altura(rootNode.getRight());
        return 1 + Math.max(alturaIzquierda, alturaDerecha);
    }
}
