package web_scraping;

import java.util.concurrent.RecursiveTask;

public class Juegos_gratis {
    private String nombre;
    private String sitio_web;
    private String imagen;

    public Juegos_gratis(String nombre, String sitio_web, String imagen) {
        this.nombre = nombre;
        this.sitio_web = sitio_web;
        this.imagen = imagen;
    }

    public Juegos_gratis() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSitio_web() {
        return sitio_web;
    }

    public void setSitio_web(String sitio_web) {
        this.sitio_web = sitio_web;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
