package parsing_json;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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

    private static Field elementContainsField(String fieldName) {
        try {
            return Element.class.getDeclaredField(fieldName);
        } catch (NoSuchFieldException nsfe) {
            return null;
        }
    }

    private static boolean fieldValuesMatch(Field checkField, Object objectWithValue, Object valueToMatch) {
        try {
            return checkField.get(objectWithValue).equals(valueToMatch);
        } catch (IllegalAccessException iae) {
            iae.printStackTrace();
            return false;
        }
    }

    public Element findByAtomicNumber(int atomic_number) {
        Iterator iterator = super.iterator();
        while (iterator.hasNext()) {
            Element e = (Element) iterator.next();
            if (e.getNumber().equals(atomic_number))
                return e;
        }
        return null;
    }

    public Element findByName(String name) {
        Iterator iterator = super.iterator();
        while (iterator.hasNext()) {
            Element e = (Element) iterator.next();
            if (e.getName().equals(name))
                return e;
        }
        return null;
    }

    public ElementCollection where(String fieldName, Object value) {
        ElementCollection result = new ElementCollection();
        Field field = elementContainsField(fieldName);
        if (field != null) {
            field.setAccessible(true);
            Iterator iterator = super.iterator();
            while (iterator.hasNext()) {
                Element e = (Element) iterator.next();
                if (fieldValuesMatch(field, e, value)) {
                    result.add(e);
                }
            }
        }
        return result;
    }
}
