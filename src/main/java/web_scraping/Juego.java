package web_scraping;

public class Juego {
    private String nombre;
    private String sitio_web;
    private String imagen;

    public Juego(String nombre, String sitio_web, String imagen) {
        this.nombre = nombre;
        this.sitio_web = sitio_web;
        this.imagen = imagen;
    }

    public Juego(String nombre, String sitio_web) {
        this.nombre = nombre;
        this.sitio_web = sitio_web;
    }
}
