package bean;

import com.google.gson.annotations.SerializedName;
import db.Bean;

public class CriterioInfoSucursalBean implements Bean {

    @SerializedName("idSucursal")
    private Long idSucursal;

    public Long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Long idSucursal) {
        this.idSucursal = idSucursal;
    }
}
