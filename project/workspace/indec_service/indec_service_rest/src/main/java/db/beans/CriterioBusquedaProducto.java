package db.beans;
public class CriterioBusquedaProducto {

    private Short idCategoria;
    private String marca;
    private String palabraclave;

    public String getPalabraclave() { return palabraclave; }

    public void setPalabraclave(String palabraclave) { this.palabraclave = palabraclave; }

    public String getMarca() { return marca; }

    public void setMarca(String marca) { this.marca = marca; }

    public Short getIdCategoria() { return idCategoria; }

    public void setIdCategoria(Short idCategoria) { this.idCategoria = idCategoria; }

    public boolean filtraPorCategoria (Producto p) {
        if (this.getIdCategoria() == null) {
            return true;
        } else {
            return p.getIdCategoria().equals(this.getIdCategoria());
        }
    }

    public boolean filtraPorMarca (Producto p) {
        if (this.getMarca() == null) {
            return true;
        } else {
            return (p.getNombreMarca().trim().toLowerCase().equals(this.getMarca().trim().toLowerCase()));
        }
    }

    public boolean filtraPorPalabraClave (Producto p) {

        if (this.getPalabraclave() == null) {
            return true;
        } else {
            return
                    p.getNombreProducto().trim().toLowerCase().contains(this.getPalabraclave().toLowerCase().trim())
                            || p.getNombreMarca().trim().toLowerCase().contains(this.getPalabraclave().toLowerCase().trim())
                            || p.getNombreCategoria().trim().toLowerCase().contains(this.getPalabraclave().toLowerCase().trim());
        }

    }
}
