package beans;

import activerecord.annotations.Column;
import activerecord.annotations.Entity;

@Entity(name = "cadenaServiceConfig")
public class CadenaServiceConfig {
    @Column(name = "id")
    private Long id;
    @Column(name = "nombreCadena")
    private String nombreCadena;
    @Column(name = "tecnologia")
    private String tecnologia;
    @Column(name = "url")
    private String url ;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCadena() {
        return nombreCadena;
    }

    public void setNombreCadena(String nombreCadena) {
        this.nombreCadena = nombreCadena;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}