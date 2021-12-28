public class TreeTest {

    public static void main(String[] args) {
        TreeAVL avl = new TreeAVL();
        avl.add(new Key('Б', 4));
        avl.add(new Key('Б',2));
        avl.add(new Key('Б',1));
        avl.add(new Key('Б',3));
        avl.add(new Key('Б',5));
        avl.add(new Key('Б', 6));
        avl.add(new Key('A',7));
        avl.add(new Key('М',7));
        avl.add(new Key('А',8));
        avl.add(new Key('Б',10));
        avl.showInWindow("До удаления");
        avl.printLRN();
        System.out.println("Высота: " + avl.height());
        System.out.println(avl.hasElem(new Key('Б',5)));
        System.out.println(avl.hasElem(new Key('Б',1)));
        System.out.println(avl.hasElem(new Key('A',5)));
        avl.remove(new Key('Б',4));
        avl.showInWindow("После удаления");
        avl.printLNR();
    }
}
