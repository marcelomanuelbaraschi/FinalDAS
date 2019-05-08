package beans.in_models;

import com.google.gson.annotations.SerializedName;
import db.Bean;

public class CriterioBusquedaProducto implements Bean {
    @SerializedName("idCategoria")
    private Long idCategoria;

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }
}
