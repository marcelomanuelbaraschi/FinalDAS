package db.beans;

import com.google.gson.annotations.SerializedName;
import db.Bean;

public class CriterioBusquedaProducto implements Bean {

    @SerializedName("idCategoria")
    private Short idCategoria;

    @SerializedName("codigos")
    private String  codigos;

    @SerializedName("palabraclave")
    private String palabraclave;

    public String getCodigos() {
        return codigos;
    }

    public void setCodigos(String codigos) {
        this.codigos = codigos;
    }

    public Short getIdCategoria() { return idCategoria; }

    public void setIdCategoria(Short idCategoria) { this.idCategoria = idCategoria; }

    public String getPalabraclave() {return palabraclave; }

    public void setPalabraclave(String palabraclave) { this.palabraclave = palabraclave; }
}
