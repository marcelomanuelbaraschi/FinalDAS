package beans;

import com.google.gson.annotations.SerializedName;
import repository.db.Bean;

public class ProvinciaBean implements Bean {

    @SerializedName("codigoEntidadFederal")
    private String codigoEntidadFederal;
    @SerializedName("nombreProvincia")
    private String nombreProvincia;

    public String getCodigoEntidadFederal() {
        return codigoEntidadFederal;
    }

    public void setCodigoEntidadFederal(String codigoEntidadFederal) {
        this.codigoEntidadFederal = codigoEntidadFederal;
    }

    public String getNombreProvincia() {
        return nombreProvincia;
    }

    public void setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }
}
