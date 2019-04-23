package beans;

import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("codigo")
    private Integer codigo;

    @SerializedName("mensaje")
    private String  mensaje;

    @SerializedName("json")
    private String  json;

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

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }


}
