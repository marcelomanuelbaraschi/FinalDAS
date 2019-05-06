package beans;


import com.google.gson.annotations.SerializedName;
import db.Bean;

public class CriterioBusquedaLocalidad implements Bean {
    @SerializedName("codigoEntidadFederal")
    private String codigoEntidadFederal;

    public String getCodigoEntidadFederal() {
        return codigoEntidadFederal;
    }

    public void setCodigoEntidadFederal(String codigoEntidadFederal) {
        this.codigoEntidadFederal = codigoEntidadFederal;
    }
}
