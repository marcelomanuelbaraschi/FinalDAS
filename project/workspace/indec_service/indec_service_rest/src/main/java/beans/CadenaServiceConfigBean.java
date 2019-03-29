package beans;

import activerecord.annotations.Column;
import com.google.gson.annotations.SerializedName;
import db.Bean;

public class CadenaServiceConfigBean implements Bean {

    @SerializedName("id")
    private Long id;
    @SerializedName("nombreCadena")
    private String nombreCadena;
    @SerializedName("tecnologia")
    private String tecnologia;
    @SerializedName("url")
    private String url ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCadena() {
        return nombreCadena;
    }

    public void setNombreCadena(String nombreCadena) {
        this.nombreCadena = nombreCadena;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
