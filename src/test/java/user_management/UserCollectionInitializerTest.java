package user_management;

import com.google.gson.Gson;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class UserCollectionInitializerTest {
    private static Gson gson = new Gson();

    @Test
    public void generate() throws IOException {
        assertEquals(1000, UserCollectionInitializer.generate().size());
    }
}