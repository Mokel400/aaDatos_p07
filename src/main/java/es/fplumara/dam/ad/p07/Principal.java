package es.fplumara.dam.ad.p07;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.fplumara.dam.ad.p07.servicio.CatalogoDom;
import es.fplumara.dam.ad.p07.modelo.Serie;

public class Principal {

    private static final String RUTA_SERIES = "datos/series.xml";

    public static void main(String[] args) throws Exception{

        //Paso 1
        CatalogoDom dom = new CatalogoDom();
        Document documento = dom.cargar(RUTA_SERIES);
        Element raiz = documento.getDocumentElement();
        System.out.println("Elemento raíz; " + raiz.getTagName());
        System.out.println("Plataforma: " + raiz.getAttribute("plataforma"));

        //Paso 2
        List<Serie> series = dom.leerSeries(documento);
        System.out.println("Series leidas con DOM: " + series.size());
        for (Serie serie: series) {
            System.out.println(serie);
        }
    }
}
