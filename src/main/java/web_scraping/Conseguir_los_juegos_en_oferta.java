package web_scraping;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class Conseguir_los_juegos_en_oferta {
    Map<String, Object> juegos_gratis = new HashMap<>();
    Map<String, String> juego_individual = new HashMap<>();
    Map<String, Object> juegos_ofertados = new HashMap<>();
    public Map<String, Object> getJuegos_gratis() {
        return juegos_gratis;
    }

    public Map<String, Object> getJuegos_ofertados() {
        return juegos_ofertados;
    }

    public void obtenerOfertasSteam(int cantidadJuegos){
        int numeroDeJuego=1;
        Document pagina = intentarObtenerDocumento("https://store.steampowered.com/search/?ignore_preferences=1&specials=1&page=1&ndl=1");
        if (pagina != null) {
            int numeroMaximoDePaginas = 0;
            List<Element> numPaginas = pagina.select("div[id=search_result_container]").select("div.search_pagination_right").select("a[href]").stream()
                    .filter(p -> p.text().matches("\\d+"))
                    .collect(Collectors.toList());
            numeroMaximoDePaginas = Integer.parseInt(numPaginas.get(numPaginas.size() - 1).text());

            for (int i = 1; i <= numeroMaximoDePaginas; i++) {
                pagina = intentarObtenerDocumento("https://store.steampowered.com/search/?ignore_preferences=1&specials=1&page=" + i + "&ndl=1");
                for (Element x : pagina.select("div[id=search_resultsRows]").select("a[data-ds-itemkey]")) {
                    String descuento = "0";
                    String precio= "0";
                    String nombre = x.select("div.col.search_name.ellipsis").text();
                    String enlaceDelJuego = x.attr("href");
                    String urlImagen = x.select("div.col.search_capsule").select("img").attr("src");
                    if(!x.select("div.col.search_price_discount_combined.responsive_secondrow").text().isBlank()) {
                        if(x.select("div.col.search_price.discounted.responsive_secondrow").text().length() > 0) {
                            precio = x.select("div.col.search_price.discounted.responsive_secondrow").text()
                                    .substring(x.select("div.col.search_price.discounted.responsive_secondrow").text().lastIndexOf("C"));
                            descuento = x.select("div.col.search_discount.responsive_secondrow").text();
                        }else{
                            precio = x.select("div.col.search_price_discount_combined.responsive_secondrow")
                                    .select("div.col.search_price.responsive_secondrow").text();
                            String temporal = precio.substring(precio.lastIndexOf(" ") + 1, precio.length());

                            if(!temporal.contains(".") || temporal.length() < 2){
                                descuento = "-100%";
                            }else{
                                descuento = "-10%";
                            }
                        }
                    }else if(!x.hasClass("div.search_result_row.ds_collapse_flag.ds_flagged.ds_excluded_by_preferences.app_impression_tracked")){
                        Document paginaEspecial = intentarObtenerDocumento(x.attr("href"));
                        if(paginaEspecial != null){
                            if(paginaEspecial.select("div.game_area_purchase").select("div.game_area_purchase_game_wrapper.dynamic_bundle_description.ds_no_flags").first() != null){
                                descuento = paginaEspecial.select("div.game_area_purchase").select("div.game_area_purchase_game_wrapper.dynamic_bundle_description.ds_no_flags")
                                        .first().select("div.discount_block.game_purchase_discount").select("div.discount_pct").text();
                                precio =paginaEspecial.select("div.game_area_purchase").select("div.game_area_purchase_game_wrapper.dynamic_bundle_description.ds_no_flags")
                                        .first().select("div.discount_block.game_purchase_discount").select("div.discount_prices").select("div.discount_final_price").text();
                            }else{
                                descuento = paginaEspecial.select("div.game_area_purchase").select("div.game_area_purchase_game_wrapper").first().select("div.discount_pct").text();
                                precio = paginaEspecial.select("div.game_area_purchase").select("div.game_area_purchase_game_wrapper").first().select("div.discount_final_price").text();
                            }
                        }else{
                            continue;
                        }
                    }

                    if(descuento.equals("-100%")){
                        juego_individual.put("Nombre_Juego", nombre);
                        juego_individual.put("Imagen",urlImagen);
                        juego_individual.put("Enlace_Al_Juego",enlaceDelJuego);
                        juegos_gratis.put(nombre, Map.copyOf(juego_individual));
                    }else{
                        juego_individual.put("Nombre_Juego", nombre);
                        juego_individual.put("Precio_Juego", precio);
                        juego_individual.put("Imagen",urlImagen);
                        juego_individual.put("Enlace_Al_Juego",enlaceDelJuego);
                        juegos_ofertados.put(nombre, Map.copyOf(juego_individual));
                    }
                    juego_individual.clear();
                    System.out.println("Juego " + numeroDeJuego + " de steam terminado, pasando al siguiente...");
                    numeroDeJuego++;
                    if(cantidadJuegos < numeroDeJuego && cantidadJuegos != 0){
                        break;
                    }
                }
                if(cantidadJuegos < numeroDeJuego && cantidadJuegos != 0){
                    break;
                }
                esperar(100,50);
            }
        }
    }
    public void obtenerOfertasdeGog(int cantidadJuegos){
        int numeroPaginas = 1;
        int numeroDeJuego=1;
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
                            juego_individual.put("Nombre_Juego", nombre);
                            juego_individual.put("Precio_Juego", precio);
                            juego_individual.put("Imagen",urlImagen);
                            juego_individual.put("Enlace_Al_Juego",enlaceDelJuego);
                            if (descuento.equals("-100%")) {
                                juegos_gratis.put(nombre, Map.copyOf(juego_individual));
                            } else {
                                juegos_ofertados.put(nombre, Map.copyOf(juego_individual));
                            }
                        }else{
                            juego_individual.put("Nombre_Juego", nombre);
                            juego_individual.put("Precio_Juego", precio);
                            juego_individual.put("Enlace_Al_Juego",enlaceDelJuego);
                            if (descuento.equals("-100%")) {
                                juegos_gratis.put(nombre, Map.copyOf(juego_individual));
                            } else {
                                juegos_ofertados.put(nombre, Map.copyOf(juego_individual));
                            }
                        }
                        juego_individual.clear();
                        System.out.println("Juego " + numeroDeJuego + " de gog terminado, pasando al siguiente...");
                        numeroDeJuego++;
                        if(cantidadJuegos < numeroDeJuego && cantidadJuegos != 0){
                            break;
                        }
                    }
                }else{
                    System.out.println("La pagina no esta disponible");
                }
                if(cantidadJuegos < numeroDeJuego && cantidadJuegos != 0){
                    break;
                }
                esperar(100, 50);
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
    public void limpiarOfertas(){
        this.juegos_ofertados.clear();
        this.juegos_gratis.clear();
        this.juego_individual.clear();
    }
}
