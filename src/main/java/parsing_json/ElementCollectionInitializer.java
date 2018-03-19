package parsing_json;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import java.util.Arrays;
import java.util.List;

public class ElementCollectionInitializer {
    private static Gson gson = new Gson();

    public static ElementCollection generate() {
        String raw;
        ElementCollection table = new ElementCollection();
        try {
            raw = new ElementCollectionInitializer().readRawDataToString();
            List<String> jsonElements = getElementsListFromRawData(raw);
            for (String jsonElement : jsonElements) {
                Element e = gson.fromJson(jsonElement, Element.class);
                table.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    public static List<String> getElementsListFromRawData(String rawData) {
        //TODO: Do this with GSON if possible or add correct delimiter
        return Arrays.asList(rawData.split(""));
    }

    public String readRawDataToString() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        return IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
    }
}
