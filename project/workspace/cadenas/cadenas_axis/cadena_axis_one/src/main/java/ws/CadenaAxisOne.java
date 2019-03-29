package ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CadenaAxisOne {

    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
            .create();

    public String health(final String identificador) {
        System.out.println("Axis health identificador -> " + identificador);
        return "OK";
    }
}
