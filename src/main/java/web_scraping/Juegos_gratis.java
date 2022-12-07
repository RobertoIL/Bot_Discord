package web_scraping;

import java.util.concurrent.RecursiveTask;

public class Juegos_gratis{
    private String nombre;
    private String sitio_web;
    private String imagen;

    public Juegos_gratis(String nombre, String sitio_web, String imagen) {
        this.nombre = nombre;
        this.sitio_web = sitio_web;
        this.imagen = imagen;
    }

    public Juegos_gratis(String nombre, String sitio_web) {
        this.nombre = nombre;
        this.sitio_web = sitio_web;
    }

    public Juegos_gratis() {
    }

    @Override
    public String toString() {
        return "Juegos_gratis{" +
                "nombre='" + nombre + '\'' +
                ", sitio_web='" + sitio_web + '\'' +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}
