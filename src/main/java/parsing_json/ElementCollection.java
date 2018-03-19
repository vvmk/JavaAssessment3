package parsing_json;

import java.util.ArrayList;

public class ElementCollection extends ArrayList<Element> {

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
