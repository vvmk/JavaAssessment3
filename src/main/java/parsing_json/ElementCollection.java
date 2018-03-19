package parsing_json;

import java.util.ArrayList;
import java.util.Collection;

public class ElementCollection extends ArrayList<Element> {
    public ElementCollection() {
        super();
    }

    public ElementCollection(int initialCapacity) {
        super(initialCapacity);
    }

    public ElementCollection(Collection<? extends Element> c) {
        super(c);
    }

    public Element findByAtomicNumber(int atomic_number) {
        for (Element e : super.toArray(new Element[0])) {
            if (e.getNumber().equals(atomic_number))
                return e;
        }
        return null;
    }

    public Element findByName(String name) {
        return null;
    }

    public ElementCollection where(String fieldName, Object value) {
        return null;
    }
}
