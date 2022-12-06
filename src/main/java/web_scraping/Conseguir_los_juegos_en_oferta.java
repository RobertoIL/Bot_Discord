package web_scraping;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
public class Conseguir_los_juegos_en_oferta {

    HashMap<String, Object> juegos_gratis = new HashMap<>();
    HashMap<String, Object> juegos_ofertados = new HashMap<>();

    public HashMap<String, Object> getJuegos_gratis() {
        return juegos_gratis;
    }

    public HashMap<String, Object> getJuegos_ofertados() {
        return juegos_ofertados;
    }

    public void obtenerOfertasdeGog(){
        int numeroPaginas = 1;
        Document paginaDeGog = intentarObtenerDocumento("https://www.gog.com/en/games?discounted=true");
        if(paginaDeGog != null) {
            Elements cantidad = paginaDeGog.select("div.pagination");
            numeroPaginas = Integer.parseInt(cantidad.select("div.ng-star-inserted").last().text());
            for (int i = 1; i <= numeroPaginas; i++) {
                paginaDeGog = intentarObtenerDocumento("https://www.gog.com/en/games?discounted=true&page=" + i);
                if (paginaDeGog != null){
                    Elements listaJuegos = paginaDeGog.select("div.paginated-products-grid.grid");
                    for(Element juego : listaJuegos.select("product-tile.ng-star-inserted")){
                        String enlaceDelJuego = juego.select("a[href]").attr("href");
                        String nombre = juego.select("div.product-tile__title").text();
                        String precio = juego.select("span.final-value").text();
                        String urlImagen;
                        if(juego.select("store-picture.ng-star-inserted").select("source").first() != null){
                            urlImagen = juego.select("store-picture.ng-star-inserted").select("source").first().attr("srcset").substring(0,
                                    juego.select("store-picture.ng-star-inserted").select("source").first().attr("srcset").indexOf(",")-1);
                        }else{
                            urlImagen = "false";
                        }
                        String descuento = juego.select("product-price[selenium-id=productPrice]").select("price-discount.ng-star-inserted").text();
                        if(!urlImagen.equals("false")) {
                            if (descuento.equals("-100%")) {
                                Juegos_gratis juegoNuevo = new Juegos_gratis(nombre, enlaceDelJuego, urlImagen);
                                if (!juegos_gratis.containsValue(juegoNuevo)) {
                                    juegos_gratis.put(nombre, juegoNuevo);
                                } else {
                                    juegos_gratis.remove(nombre, juegoNuevo);
                                    juegos_gratis.put(nombre, juegoNuevo);
                                }
                            } else {
                                Juegos_oferta juegoNuevo = new Juegos_oferta(nombre, precio, enlaceDelJuego, urlImagen);
                                if (!juegos_ofertados.containsValue(juegoNuevo)) {
                                    juegos_ofertados.put(nombre, juegoNuevo);
                                } else {
                                    juegos_ofertados.remove(nombre, juegoNuevo);
                                    juegos_ofertados.put(nombre, juegoNuevo);
                                }
                            }
                        }else{
                            if (descuento.equals("-100%")) {
                                Juegos_gratis juegoNuevo = new Juegos_gratis(nombre, enlaceDelJuego);
                                if (!juegos_gratis.containsValue(juegoNuevo)) {
                                    juegos_gratis.put(nombre, juegoNuevo);
                                } else {
                                    juegos_gratis.remove(nombre, juegoNuevo);
                                    juegos_gratis.put(nombre, juegoNuevo);
                                }
                            } else {
                                Juegos_oferta juegoNuevo = new Juegos_oferta(nombre, precio, enlaceDelJuego);
                                if (!juegos_ofertados.containsValue(juegoNuevo)) {
                                    juegos_ofertados.put(nombre, juegoNuevo);
                                } else {
                                    juegos_ofertados.remove(nombre, juegoNuevo);
                                    juegos_ofertados.put(nombre, juegoNuevo);
                                }
                            }
                        }
                    }
                }else{
                    System.out.println("La pagina no esta disponible");
                }
                esperar(100, 25);
            }
        }
    }
    public void obtenerOfertasSteam(){
        int numeroPaginas = 1;
        Document paginaDeGog = intentarObtenerDocumento("https://www.gog.com/en/games?discounted=true");
        if(paginaDeGog != null) {
            Elements cantidad = paginaDeGog.select("div.pagination");
            numeroPaginas = Integer.parseInt(cantidad.select("div.ng-star-inserted").last().text());
            for (int i = 1; i <= numeroPaginas; i++) {
                paginaDeGog = intentarObtenerDocumento("https://www.gog.com/en/games?discounted=true&page=" + i);
                if (paginaDeGog != null){
                    Elements listaJuegos = paginaDeGog.select("div.paginated-products-grid.grid");
                    for(Element juego : listaJuegos.select("product-tile.ng-star-inserted")){
                        String enlaceDelJuego = juego.select("a[href]").attr("href");
                        String nombre = juego.select("div.product-tile__title").text();
                        String precio = juego.select("span.final-value").text();
                        String urlImagen;
                        if(juego.select("store-picture.ng-star-inserted").select("source").first() != null){

                            urlImagen = juego.select("store-picture.ng-star-inserted").select("source[type=image/jpeg]").attr("srcset").substring(0,
                                    juego.select("store-picture.ng-star-inserted").select("source[type=image/jpeg]").attr("srcset").lastIndexOf(","));
                        }else{
                            Document verImagen = intentarObtenerDocumento(enlaceDelJuego);
                            if(verImagen != null){
                                urlImagen = verImagen.select("div.mobile-slider__items-container._gog-module-slider__items-container").select("div.mobile-slider__slide._gog-slider__item.js-item")
                                        .first().select("img.mobile-slider__image").attr("src");
                            }else{
                                urlImagen = "false";
                            }
                        }
                        String descuento = juego.select("product-price[selenium-id=productPrice]").select("price-discount.ng-star-inserted").text();
                        if(!urlImagen.equals("false")) {
                            if (descuento.equals("-100%")) {
                                Juegos_gratis juegoNuevo = new Juegos_gratis(nombre, enlaceDelJuego, urlImagen);
                                if (!juegos_gratis.containsValue(juegoNuevo)) {
                                    juegos_gratis.put(nombre, juegoNuevo);
                                } else {
                                    juegos_gratis.remove(nombre, juegoNuevo);
                                    juegos_gratis.put(nombre, juegoNuevo);
                                }
                            } else {
                                Juegos_oferta juegoNuevo = new Juegos_oferta(nombre, precio, enlaceDelJuego, urlImagen);
                                if (!juegos_ofertados.containsValue(juegoNuevo)) {
                                    juegos_ofertados.put(nombre, juegoNuevo);
                                } else {
                                    juegos_ofertados.remove(nombre, juegoNuevo);
                                    juegos_ofertados.put(nombre, juegoNuevo);
                                }
                            }
                        }else{
                            if (descuento.equals("-100%")) {
                                Juegos_gratis juegoNuevo = new Juegos_gratis(nombre, enlaceDelJuego);
                                if (!juegos_gratis.containsValue(juegoNuevo)) {
                                    juegos_gratis.put(nombre, juegoNuevo);
                                } else {
                                    juegos_gratis.remove(nombre, juegoNuevo);
                                    juegos_gratis.put(nombre, juegoNuevo);
                                }
                            } else {
                                Juegos_oferta juegoNuevo = new Juegos_oferta(nombre, precio, enlaceDelJuego);
                                if (!juegos_ofertados.containsValue(juegoNuevo)) {
                                    juegos_ofertados.put(nombre, juegoNuevo);
                                } else {
                                    juegos_ofertados.remove(nombre, juegoNuevo);
                                    juegos_ofertados.put(nombre, juegoNuevo);
                                }
                            }
                        }
                    }
                }else{
                    System.out.println("La pagina no esta disponible");
                }
                esperar(100, 25);
            }
        }
    }
    public Document intentarObtenerDocumento(String url){
        Document paginaVacia = new Document("");
        try{
            if(url.contains("/app/")){
                return Jsoup.connect(url).cookie("birthtime","1070244001").get();
            }else if(url.contains("gog") && url.contains("&page=")){
                return Jsoup.connect(url).cookie("gog_lc", "CL_USD_en-US").get();
            }
            return Jsoup.connect(url).get();
        }catch (Exception e){
            System.out.println("La pagina no esta disponible");
            return  null;
        }
    }
    public void esperar(int max,int min){
        try {
            Thread.sleep((int) (Math.random() * ((max + 1) - min) + min));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
