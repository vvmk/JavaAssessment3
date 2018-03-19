//package vm;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.ParameterizedType;
//import java.util.ArrayList;
//import java.util.Collection;
//
///**
// * filename:
// * project: assessment3
// * author: https://github.com/vvmk
// * date: 3/19/18
// */
//public class VMCollection<E> extends ArrayList<E> {
//    public VMCollection() {
//        super();
//    }
//
//    public VMCollection(int initialCapacity) {
//        super(initialCapacity);
//    }
//
//    public VMCollection(Collection<? extends E> c) {
//        super(c);
//    }
//
//    private static Field objectContainsField(String fieldName) {
//        try {
//            ParameterizedType type = (ParameterizedType) VMCollection.class.getGenericSuperclass();
//              //TODO: lost to type erasure, no good :(
//            Class<?> c = (Class<?>) type.getActualTypeArguments()[0];
//            return c.getDeclaredField(fieldName);
//        } catch (NoSuchFieldException nsfe) {
//            return null;
//        }
//    }
//
//    private static boolean fieldValuesMatch(Field checkField, Object objectWithValue, Object valueToMatch) {
//        try {
//            return checkField.get(objectWithValue).equals(valueToMatch);
//        } catch (IllegalAccessException iae) {
//            iae.printStackTrace();
//            return false;
//        }
//    }
//
//    public VMCollection<E> where(String fieldName, Object value) {
//        VMCollection<E> result = new VMCollection<>();
//        Field field = objectContainsField(fieldName);
//        if (field != null) {
//            for (Object o : super.toArray()) {
//                if (fieldValuesMatch(field, o, value)) {
//                    result.add((E) o);
//                }
//            }
//        }
//        return result;
//    }
//}
