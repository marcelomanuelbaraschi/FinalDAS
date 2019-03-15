package beans;

import clients.factory.ClientType;
import com.google.gson.annotations.SerializedName;
import db.Bean;

import java.util.HashMap;

public class ServiceConfigBean  implements Bean {

    @SerializedName("params")
    private HashMap<String, String> params = new HashMap<>();
    @SerializedName("clientType")
    private ClientType clientType;
    @SerializedName("idCadena")
    private Long idCadena;
    @SerializedName("nombreCadena")
    private String nombreCadena;

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
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
