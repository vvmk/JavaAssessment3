package parsing_json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;

import java.lang.reflect.Type;
import java.util.List;

public class ElementCollectionInitializer {
    private static Gson gson = new Gson();

    public static ElementCollection generate() {
        String raw;
        ElementCollection table = new ElementCollection();
        try {
            raw = new ElementCollectionInitializer().readRawDataToString();
            List<Element> elements = getElementsListFromRawData(raw);
            table = new ElementCollection(elements);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    private static List<Element> getElementsListFromRawData(String rawData) {
        Type collectionType = new TypeToken<List<Element>>(){}.getType();
        return gson.fromJson(rawData, collectionType);
    }

    private String readRawDataToString() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        return IOUtils.toString(classLoader.getResourceAsStream("periodic_table.json"));
    }
}
