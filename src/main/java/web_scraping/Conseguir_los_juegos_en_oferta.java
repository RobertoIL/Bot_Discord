package web_scraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Collectors;
public class Conseguir_los_juegos_en_oferta {

    List<Juegos_gratis> juegos_gratuitos = new ArrayList<>();

    List<Juegos_oferta> juegos_en_oferta = new ArrayList<>();


    public void obtenerOfertasdeGog(){
        int numeroPaginas = 1;
        Document paginaDeGog = intentarObtenerDocumento("https://www.gog.com/en/games?discounted=true");
        if(paginaDeGog != null) {
            try {
                Document pagina = Jsoup.connect("https://www.gog.com/en/games?discounted=true").get();
                Elements cantidad = pagina.select("div.pagination");
                numeroPaginas = Integer.parseInt(cantidad.select("div.ng-star-inserted").last().text());
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int i = 1; i < numeroPaginas; i++) {
                try {
                    Document pagina;
                    pagina = Jsoup.connect("https://www.gog.com/en/games?discounted=true&page=" + i).cookie("gog_lc", "CL_USD_en-US").get();
                    Elements listaJuegos = pagina.select("div.paginated-products-grid.grid");
                    for (Element juego : listaJuegos.select("product-tile.ng-star-inserted")) {
                        String urlImagen = juego.select("div.product-tile__image-wrapper").select("source[type=image/webp]").attr("srcset").substring(0,
                                juego.select("div.product-tile__image-wrapper").select("source[type=image/webp]").attr("srcset").lastIndexOf(","));
                        String nombre = juego.select("div.product-tile__title").text();
                        String precioDescontado = juego.select("span.final-value").text();
                        String cantidadDescuento = juego.select("price-discount.ng-star-inserted").text();
                        String precioBase = juego.select("span.base-value.ng-star-inserted").text();
                        if (precioDescontado.equals("-100%")) {
                            this.juegos_gratuitos.add(new Juegos_gratis(nombre, "https://www.gog.com/", urlImagen));
                        } else {
                            this.juegos_en_oferta.add(new Juegos_oferta(nombre, precioDescontado, "https://www.gog.com/", urlImagen));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (i == 2) {
                    break;
                }
            }
        }
    }
    public void obtenerOfertasSteam() {
        Document pagina = intentarObtenerDocumento("https://store.steampowered.com/search/?ignore_preferences=1&specials=1&page=1&ndl=1");
        if (pagina != null) {
            int numeroMaximoDePaginas = 0;
            List<Element> numPaginas = pagina.select("div[id=search_result_container]").select("div.search_pagination_right").select("a[href]").stream()
                    .filter(p -> p.text().matches("\\d+"))
                    .collect(Collectors.toList());
            numeroMaximoDePaginas = Integer.parseInt(numPaginas.get(numPaginas.size() - 1).text());

            for (int i = 1; i <= numeroMaximoDePaginas; i++) {
                try {
                    pagina = intentarObtenerDocumento("https://store.steampowered.com/search/?ignore_preferences=1&specials=1&page=" + i + "&ndl=1");
                    for (Element x : pagina.select("div[id=search_resultsRows]").select("a[data-ds-itemkey]")) {
                        String descuento = "0";
                        String precioOriginal= "0";
                        String precioConLaOferta= "0";
                        if(!x.select("div.col.search_price_discount_combined.responsive_secondrow").text().isBlank()) {
                            precioOriginal = x.select("div.col.search_price.discounted.responsive_secondrow").text()
                                    .substring(0, (x.select("div.col.search_price.discounted.responsive_secondrow").text().lastIndexOf("C") - 1));
                            precioConLaOferta = x.select("div.col.search_price.discounted.responsive_secondrow").text()
                                    .substring(x.select("div.col.search_price.discounted.responsive_secondrow").text().lastIndexOf("C"));
                            descuento = x.select("div.col.search_discount.responsive_secondrow").text();
                        }else if(!x.hasClass("div.search_result_row.ds_collapse_flag.ds_flagged.ds_excluded_by_preferences.app_impression_tracked")){
                            Document paginaEspecial = intentarObtenerDocumento(x.attr("href"));
                            if(paginaEspecial != null){
                                descuento = paginaEspecial.select("div.game_area_purchase").select("div.game_area_purchase_game_wrapper.dynamic_bundle_description.ds_no_flags")
                                        .first().select("div.discount_block.game_purchase_discount").select("div.discount_pct").text();
                                precioOriginal =paginaEspecial.select("div.game_area_purchase").select("div.game_area_purchase_game_wrapper.dynamic_bundle_description.ds_no_flags")
                                        .first().select("div.discount_block.game_purchase_discount").select("div.discount_prices").select("div.discount_original_price").text();
                                precioConLaOferta =paginaEspecial.select("div.game_area_purchase").select("div.game_area_purchase_game_wrapper.dynamic_bundle_description.ds_no_flags")
                                        .first().select("div.discount_block.game_purchase_discount").select("div.discount_prices").select("div.discount_final_price").text();
                            }else{
                                continue;
                            }
                        }
                        String nombre = x.select("div.col.search_name.ellipsis").text();
                        String enlaceDelJuego = x.attr("href");
                        String urlImagen = x.select("div.col.search_capsule").attr("src");
                        if (descuento.equals("-100%")) {
                            this.juegos_gratuitos.add(new Juegos_gratis(nombre, "https://store.steampowered.com", urlImagen));
                        } else {
                            this.juegos_en_oferta.add(new Juegos_oferta(nombre, precioConLaOferta, "https://store.steampowered.com", urlImagen));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
    public Document intentarObtenerDocumento(String url){
        Document paginaVacia = new Document("");
        try{
            return Jsoup.connect(url).get();
        }catch (Exception e){
            System.out.println("La pagina no esta disponible");
            return  null;
        }
    }
}
