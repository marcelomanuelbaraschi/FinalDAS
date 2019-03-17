package beans;

import clients.ClientType;
import com.google.gson.annotations.SerializedName;
import db.Bean;

public class CadenaServiceConfig implements Bean {

    @SerializedName("paramValue")
    private String paramValue ;
    @SerializedName("clientType")
    private ClientType clientType;
    @SerializedName("idCadena")
    private Long idCadena;
    @SerializedName("nombreCadena")
    private String nombreCadena;


    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
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
