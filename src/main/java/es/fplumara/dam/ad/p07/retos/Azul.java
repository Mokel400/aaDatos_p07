package es.fplumara.dam.ad.p07.retos;

import es.fplumara.dam.ad.p07.modelo.Serie;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Azul {

    private String textoDe(Element padre, String etiqueta){
        return padre.getElementsByTagName(etiqueta).item(0).getTextContent().trim();
    }

    public List<Serie> leerSeriesClasicas(Document documento){
        List<Serie> series = new ArrayList<>();
        NodeList nodos = documento.getElementsByTagName("serie");
        for (int i = 0; i < nodos.getLength(); i++) {
            Element elemento = (Element) nodos.item(i);
            String id = elemento.getAttribute("id");
            String titulo = textoDe(elemento, "titulo");
            String genero = textoDe(elemento, "genero");
            int anio = Integer.parseInt(textoDe(elemento, "anio"));
            int temporadas = Integer.parseInt(textoDe(elemento, "temporadas"));
            double valoracion = Double.parseDouble(textoDe(elemento, "valoracion"));
            series.add(new Serie(id, titulo, genero, anio, temporadas, valoracion));
        }
        List<Serie> seriesClasicas = new ArrayList<>();
        for (int i = 0; i < series.size(); i++) {
            if (series.get(i).getAnio() < 2020){
                seriesClasicas.add(series.get(i));
            }
        }
        return seriesClasicas;
    }
}
