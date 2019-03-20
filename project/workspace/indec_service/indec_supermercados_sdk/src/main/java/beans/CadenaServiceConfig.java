package beans;

import com.google.gson.annotations.SerializedName;
import db.Bean;

public class CadenaServiceConfig implements Bean {

    @SerializedName("idCadena")
    private Long idCadena;
    @SerializedName("nombreCadena")
    private String nombreCadena;
    @SerializedName("tecnologia")
    private String tecnologia;
    @SerializedName("url")
    private String url ;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public Long getIdCadena() {
        return idCadena;
    }

    public void setIdCadena(Long idCadena) {
        this.idCadena = idCadena;
    }

    public String getNombreCadena() {
        return nombreCadena;
    }

    public void setNombreCadena(String nombreCadena) {
        this.nombreCadena = nombreCadena;
    }




}
