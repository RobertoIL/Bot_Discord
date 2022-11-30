package web_scraping;

public class Juegos_oferta {
    private String nombre;
    private String precio;
    private String sitio_web;
    private String imagen;

    public Juegos_oferta(String nombre, String precio, String sitio_web, String imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.sitio_web = sitio_web;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
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
