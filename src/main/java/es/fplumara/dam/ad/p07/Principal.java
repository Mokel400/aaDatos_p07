package es.fplumara.dam.ad.p07;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.fplumara.dam.ad.p07.servicio.CatalogoDom;
import es.fplumara.dam.ad.p07.modelo.Serie;
import es.fplumara.dam.ad.p07.servicio.CatalogoJaxb;
import es.fplumara.dam.ad.p07.modelo.Catalogo;
import es.fplumara.dam.ad.p07.retos.*;


public class Principal {

    private static final String RUTA_SERIES = "datos/series.xml";
    public static void main(String[] args) throws Exception{

        //Paso 1
        System.out.println("");
        System.out.println("Paso 1");
        CatalogoDom dom = new CatalogoDom();
        Document documento = dom.cargar(RUTA_SERIES);
        Element raiz = documento.getDocumentElement();
        System.out.println("Elemento raíz; " + raiz.getTagName());
        System.out.println("Plataforma: " + raiz.getAttribute("plataforma"));

        //Paso 2
        System.out.println("");
        System.out.println("Paso 2");
        List<Serie> series = dom.leerSeries(documento);
        System.out.println("Series leidas con DOM: " + series.size());
        for (Serie serie: series) {
            System.out.println(serie);
        }

        //Paso 3
        System.out.println("");
        System.out.println("Paso 3");
        List<Serie> mejores = new  ArrayList<>();
        for (Serie serie: series){
            if (serie.getValoracion() >= 8.0) {
                mejores.add(serie);
            }
        }
        dom.guardarSeries(mejores, raiz.getAttribute("plataforma"), "datos/mejor-valoradas");
        System.out.println("Guardadas " + mejores.size() + " series en datos/mejor-valoradas.xml");

        //Paso 4
        System.out.println("");
        System.out.println("Paso 4");
        CatalogoJaxb jaxb = new CatalogoJaxb();
        Catalogo catalogo = jaxb.leer(RUTA_SERIES);
        System.out.println(catalogo);
        System.out.println("Primera serie: " + catalogo.getSeries().get(0));
        System.out.println("Ultima serie: " + catalogo.getSeries().get(catalogo.getSeries().size()-1));

        //Paso 5
        System.out.println("");
        System.out.println("Paso 5");
        Serie nueva = new Serie("S25", "Patio de Luces", "Drama", 2026, 1, 8.5);
        catalogo.getSeries().add(nueva);
        jaxb.guardar(catalogo, "datos/catalogo-actualizado.xml");
        System.out.println("Guardado " + catalogo + " en datos/catalogo-actualizado.xml");

        //Reto Azul
        System.out.println("");
        System.out.println("Reto Azul");
        Azul azul = new Azul();
        List<Serie> seriesClasicas = azul.leerSeriesClasicas(documento);
        System.out.println("Series clasicas leidas con DOM: " + seriesClasicas.size());
        for (Serie serie : seriesClasicas){
            System.out.println(serie);
        }
    }
}
