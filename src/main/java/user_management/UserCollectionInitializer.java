package user_management;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;
import parsing_json.ElementCollection;

import java.lang.reflect.Type;
import java.util.List;

public class UserCollectionInitializer {
    private static Gson gson = new Gson();

    public static UserCollection generate() {
        String raw;
        UserCollection table = new UserCollection();
        try {
            raw = new UserCollectionInitializer().readRawDataToString();
            List<User> users = getUsersListFromRawData(raw);
            table = new UserCollection(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    private static List<User> getUsersListFromRawData(String rawData) {
        Type collectionType = new TypeToken<List<User>>() {
        }.getType();
        return gson.fromJson(rawData, collectionType);
    }

    private String readRawDataToString() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        return IOUtils.toString(classLoader.getResourceAsStream("users.json"));
    }
}
