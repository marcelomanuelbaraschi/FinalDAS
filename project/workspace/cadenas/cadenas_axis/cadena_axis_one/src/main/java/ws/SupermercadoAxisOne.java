package ws;

import bean.MarcaBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import db.Bean;
import db.Dao;
import db.DaoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SupermercadoAxisOne {
    protected static final Logger log = LoggerFactory.getLogger(SupermercadoAxisOne.class);

    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
            .create();

    public String health(final String identificador) {
        log.debug("Axis health identificador -> " + identificador);
        return "OK";
    }

    /*public String marcas(final String identificador, final String marca) throws SQLException {
        log.debug("Axis marcas identificador -> {}, marca {}", identificador, marca);

        Dao dao = DaoFactory.getDaoSimple("Marcas");
        MarcaBean bean = new MarcaBean();
        bean.setMarca(marca);
        List<Bean> marcas = dao.select(bean);

        return gson.toJson(marcas);
    }*/
}
