import javax.swing.*;
import java.awt.*;

/**
 * @author Danil
 * Класс АВЛ-дерева
 */
public class TreeAVL {

    private Node root; // Корневой узел

    /**
     * Обычный конструктор АВЛ-дерева
     */
    public TreeAVL(){
        root = null;
    }

    /**
     * Конструктор - создание дерева из массива ключей
     * @param keys набор ключей для построения дерева
     */
    public TreeAVL(Key[] keys){
        root = null;
        for(Key key : keys){
            add(key);
        }
    }

    /**
     * Функция добавляющая элемент в дерево
     * @param key - добавляемый элемент
     */
    public void add(Key key){
            root = add(key,root);
    }

    /**
     * Рекурсивная функция, добавляющая элемент в дерево
     * @param key добавляемый элемент
     * @param node текущий узел
     * @return сбалансированное дерево с добавленным элементом
     */
    private Node add(Key key,Node node){
        if (!hasElem(key)) {
            if (node == null){
                return new Node(key); // если узел пустой добавляет узел с новым значением
            }
            else {
                if(key.compareTo(node.key) == -1){ // если добавляемый ключ меньше значения ключа текущей ноды
                    node.left = add(key,node.left); // двигаемся влево
                }else{
                    node.right = add(key,node.right); // двигаемся вправо
                }
            }
        }
        return balance(node);
    }

    /**
     * Функция удаляющая заданный элемент из дерева
     * @param key элемент, который нужно удалить
     */
    public void remove(Key key){
        root = remove(key,root);
    }

    /**
     * Рекурсивная функция, удаляющая заданный элемент из дерева
     * @param key элемент который нужно удалить
     * @param node текущий узел
     * @return сбалансированное дерево с удаленным элементом
     */
    private Node remove(Key key,Node node){
        if (hasElem(key)){ // если элемент есть
            if(key.compareTo(node.key) == -1){ //если текущий меньше
                node.left = remove(key,node.left);//двигаемся влево
            }else if ( key.compareTo(node.key) == 0){ //  элемент нашелся
                if (node.left != null && node.right != null){ //два потомка
                    node.key = findMax(node.left).key; //меняем на максимальный слева
                    node.left = remove(node.key,node.left); // удаляем максимальный слева
                }else{
                    node = (node.right != null)?node.right:node.left;// один потомок: если справа то заменяем текущую на правого потомка, иначе на левого
                }
            }else{
                node.right = remove(key,node.right);//двигаемся вправо
            }
        }
        return balance(node);
    }

    /**
     * Функция, для определения присутствия элемента в дереве
     * @param key - элемент для поиска
     * @return true/false - если элемент присутствует/отсутствует в дереве
     */
    public boolean hasElem(Key key){
        return hasElem(key,root);
    }

    /**
     * Рекурсивная функция, для определения присутствия элемента в дереве
     * @param key - элемент для поиска
     * @param node - текущий узел
     * @return true/false - если элемент присутствует/отсутствует в дереве
     */
    private boolean hasElem(Key key,Node node){
        if(node == null) return false; // если не нашли

        return switch (key.compareTo(node.key)) { //ищем
            case -1 -> hasElem(key, node.left); // слева
            case 1 -> hasElem(key, node.right); //справа
            default -> true; // нашли
        };
    }

    /**
     * Возвращает значение максимальный элемент из дерева
     * @return значение максимального
     */
    public Key findMax(){
        return findMax(root).key;
    }

    /**
     * Возвращает узел максимального элемента, дерева с вершиной node
     * @param node вершина дерева, для нахождения max
     * @return узел максимальный элемент
     */
    private Node findMax(Node node){
        if (node != null){
            while (node.right != null){
                node = node.right;
            }
        }
        return node;
    }

    /**
     * Прямой обход дерева
     */
    public void printNLR(){
        printNLR(root);
        System.out.println();
    }

    /**
     * Рекурсивная функция, выводит дерево, делая прямой обход
     * @param node текущий узел
     */
    private void printNLR(Node node){
        if(node != null){
            System.out.print(node.key + " ");
            printNLR(node.left);
            printNLR(node.right);
        }
    }

    /**
     * Симметричный обход дерева слева направо
     */
    public void printLNR(){
        printLNR(root);
        System.out.println();
    }

    /**
     * Рекурсивная функция, выводит дерево, делая симметричный обход дерева слева направо
     * @param node текущий узел
     */
    private void printLNR(Node node){
        if (node != null){
            printLNR(node.left);
            System.out.print(node.key+ " ");
            printLNR(node.right);
        }
    }

    /**
     * Симметричный обход дерева справа налево
     */
    public void printRNL(){
        printRNL(root);
        System.out.println();
    }

    /**
     * Рекурсивная функция, выводит дерево, делая симметричный обход дерева справа налево
     * @param node текущий узел
     */
    private void printRNL(Node node){
        if (node != null){
            printRNL(node.right);
            System.out.print(node.key + " ");
            printRNL(node.left);
        }
    }

    /**
     * Обратный обход дерева
     */
    public void printLRN(){
        printLRN(root);
        System.out.println();
    }

    /**
     * Рекурсивная функция, выводит дерево, делая обратный обход дерева
     * @param node текущий узел
     */
    private void printLRN(Node node){
        if (node != null){
            printLRN(node.left);
            printLRN(node.right);
            System.out.print(node.key + " ");
        }
    }

    private static final short ALLOWED_IMBALANCE = 1; // Максимальная разрешенная разница в высоте деревьев

    private Node balance(Node node){
        if (node == null) return node;

        // Разница между левым и правым поддеревом
        if(height(node.left) - height(node.right) > ALLOWED_IMBALANCE){
            if (height(node.left.left) >= height(node.left.right)){
                node = llRotate(node);
            }else{
                node = lrRotate(node);
            }
        }else if(height(node.right) - height(node.left) > ALLOWED_IMBALANCE){ // разница между правым и левым
            if (height(node.right.right) >= height(node.right.left)){
                node = rrRotate(node);
            }else{
                node = rlRotate(node);
            }
        }
        node.balance = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    /**
     * Одиночный правый поворот(Single right rotation(RR))\
     * @param node вершина дерева, которой необходим поворот
     * @return повернутое вправо дерево
     */
    private Node rrRotate(Node node){
        Node rightChild = node.right;
        node.right = rightChild.left;
        rightChild.left = node;
        node.balance = Math.max(height(node.left),height(node.right))+1;
        rightChild.balance = Math.max(height(rightChild.right),node.balance)+1;
        return rightChild;
    }

    /**
     * Одиночный левый поворот(Single left rotation(LL))/
     * @param node вершина дерева, которой необходим поворот
     * @return повернутое влево дерево с вершиной node
     */
    private Node llRotate(Node node){
        Node leftChild = node.left;
        node.left = leftChild.right;
        leftChild.right = node;
        node.balance = Math.max(height(node.left),height(node.right))+1;
        leftChild.balance = Math.max(height(leftChild.right),node.balance) +1;
        return leftChild;
    }

    /**
     * Двойной лево-правый поворот (Double Left-right rotation(LR))
     * @param node вершина дерева, которой необходим поворот
     * @return повернутое влево-вправо дерево
     */
    private Node lrRotate(Node node){
        node.left = rrRotate(node.left);
        return llRotate(node);
    }

    /**
     * Двойной право-левый поворот (Double Right-left rotation(RL))
     * @param node вершина дерева, которой необходим поворот
     * @return повернутое влево-вправо дерево
     */
    private Node rlRotate(Node node){
        node.right = llRotate(node.right);
        return rrRotate(node);
    }

    /**
     * Возвращает длину дерева
     * @param node вершина дерева
     * @return высоту дерева
     */
    private int height(Node node){
        return node == null? -1:node.balance;
    }

    public int height(){
        return height(root);
    }


    /**
     * Проверка дерева на пустоту
     * @return true/false - пустой/не пустой
     */
    public boolean isEmpty(){
        return this.root == null;
    }

    /**
     * Очистка списка
     */
    public void clear(){
        this.root = null;
    }



    private JFrame frame; // окно визуализатора
    /**
     * Функция выводит простую визуализацию текущего дерева в окно
     * @param windowTitle название окна
     */
    public void showInWindow(String windowTitle){
        final int WINDOW_WIDTH = 1280;
        final int WINDOW_HEIGHT = 400;
        frame = new JFrame(windowTitle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(255, 255, 255));
        drawCircle(root,WINDOW_WIDTH/2-60,40);
        frame.setVisible(true);
    }

    /**
     * Функция рекурсивно пробегает по узлам дерева и выводит его
     * @param node текущая нода
     * @param x координата x для вывода текущей ноды
     * @param y координата y для вывода текущей ноды
     */
    private void drawCircle(Node node,int x, int y){
        if(node != null){
            Circle circle = new Circle();
            circle.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            JLabel label = new JLabel(node.key.toString());
            label.setFont(new Font("Verdana",Font.PLAIN,13));
            circle.add(label,gbc);
            frame.add(circle);

            int nextRightX = x+90*height(node);
            int nextLeftX = x-90*height(node);
            int nextLevelY = y+60;


            circle.setBounds(x,y,60,60);
            if(node.right != null){
                JPanel rightLine = new JPanel(){
                    @Override
                    protected void paintComponent(Graphics g) {
                        g.drawLine(0,0,getWidth(),getHeight());
                    }
                };
                rightLine.setBounds(x+60+10,y+30+10,nextRightX-x-60-10,nextLevelY-y-30);
                frame.add(rightLine);
            }
            drawCircle(node.right,nextRightX,nextLevelY);
            if(node.left != null){
                JPanel leftLine = new JPanel(){
                    @Override
                    protected void paintComponent(Graphics g) {
                        g.drawLine(getWidth(),0,0,getHeight());
                    }
                };
                leftLine.setBounds(nextLeftX+60,y+30+10,x-nextLeftX-60 - 10,nextLevelY - y-30);
                frame.add(leftLine);
            }
            drawCircle(node.left,nextLeftX,nextLevelY);
        }
    }


    /**
     * Класс узла АВЛ-дерева
     * Внутренний класс
     */
    static class Node {
        Node left = null;   // Левый подузел
        Node right = null;  // Правый подузел
        int balance = 0;    // balance = -1; 0 ;1
        Key key;            // Ключ

        Node(Key key){
            this.key = key;
        }
    }

}
