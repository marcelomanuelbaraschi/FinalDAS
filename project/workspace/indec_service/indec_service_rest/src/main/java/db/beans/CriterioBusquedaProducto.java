package db.beans;

import com.google.gson.annotations.SerializedName;
import db.Bean;

public class CriterioBusquedaProducto implements Bean {

    @SerializedName("idCategoria")
    private Short idCategoria;

    @SerializedName("codigos")
    private String  codigos;

    @SerializedName("keyword")
    private String keyword;

    public String getCodigos() {
        return codigos;
    }

    public void setCodigos(String codigos) {
        this.codigos = codigos;
    }

    public Short getIdCategoria() { return idCategoria; }

    public void setIdCategoria(Short idCategoria) { this.idCategoria = idCategoria; }

    public String getKeyword() { return keyword; }

    public void setKeyword(String keyword) { this.keyword = keyword; }

}
