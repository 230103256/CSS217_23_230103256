import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

public class EnumerationIteratorAdapter<T> implements Iterator<T> {

    private Enumeration<T> enumeration;
    public EnumerationIteratorAdapter(Enumeration<T> enumeration) {
        this.enumeration = enumeration;
    }
    @Override
    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    @Override
    public T next() {
        return enumeration.nextElement();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported");
    }

    public static void main(String[] args) {
        String studentId = "230103256";

        System.out.println("Student ID: " + studentId);

        Vector<String> items = new Vector<>();
        items.add(studentId);
        items.add("book");
        items.add("Banana");
        items.add("grysha");

        EnumerationIteratorAdapter<String> iter =
                new EnumerationIteratorAdapter<>(items.elements());

        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}