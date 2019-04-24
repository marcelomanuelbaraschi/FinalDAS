package beans;

import com.google.gson.annotations.SerializedName;
import indecObjects.Cadena;

import java.util.List;

public class ComparadorResponse {

    @SerializedName("codigo")
    private Integer codigo;

    @SerializedName("mensaje")
    private String  mensaje;

    @SerializedName("cadenas")
    private List<Cadena> cadenas;

    public Integer getCodigo() {
        return codigo;
    }
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<Cadena> getCadenas() {
        return cadenas;
    }

    public void setCadenas(List<Cadena> cadenas) {
        this.cadenas = cadenas;
    }




}
