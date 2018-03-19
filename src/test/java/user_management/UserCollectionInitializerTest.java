package user_management;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import parsing_json.Element;
import parsing_json.ElementCollection;
import parsing_json.ElementCollectionInitializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import static org.junit.Assert.*;

public class UserCollectionInitializerTest {
    private static Gson gson = new Gson();

    @Test
    public void generate() throws IOException {
        assertEquals(1000, UserCollectionInitializer.generate().size());
    }
}