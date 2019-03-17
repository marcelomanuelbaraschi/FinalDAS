package beans;

import clients.Tecnologia;
import com.google.gson.annotations.SerializedName;
import db.Bean;

public class CadenaServiceConfig implements Bean {


    @SerializedName("idCadena")
    private Long idCadena;
    @SerializedName("nombreCadena")
    private String nombreCadena;
    @SerializedName("tecnologia")
    private Tecnologia tecnologia;
    @SerializedName("url")
    private String url ;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public Tecnologia getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(Tecnologia tecnologia) {
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
