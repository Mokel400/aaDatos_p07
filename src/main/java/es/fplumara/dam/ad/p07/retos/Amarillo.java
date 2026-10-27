package es.fplumara.dam.ad.p07.retos;


import es.fplumara.dam.ad.p07.modelo.Serie;
import es.fplumara.dam.ad.p07.servicio.CatalogoDom;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Amarillo {




    public static CatalogoDom dom;
    private static final String RUTA_SERIES = "datos/series.xml";

    private String textoDe(Element padre, String etiqueta){
        return padre.getElementsByTagName(etiqueta).item(0).getTextContent().trim();
    }

    public List<Serie> leerSeriesYDescartarInvalidas(Document documento){
        List<Serie> series = new ArrayList<>();
        NodeList nodos = documento.getElementsByTagName("serie");
        for (int i = 0; i < nodos.getLength(); i++) {

            if (){
                Element elemento = (Element) nodos.item(i);
                String id = elemento.getAttribute("id");
                String titulo = textoDe(elemento, "titulo");
                String genero = textoDe(elemento, "genero");
                int anio = Integer.parseInt(textoDe(elemento, "anio"));
                int temporadas = Integer.parseInt(textoDe(elemento, "temporadas"));
                double valoracion = Double.parseDouble(textoDe(elemento, "valoracion"));
                series.add(new Serie(id, titulo, genero, anio, temporadas, valoracion));

            }
        }
        return series;
    }

    public static void main(String[] args) throws Exception {

        Document documento = dom.cargar(RUTA_SERIES);
        List<Serie> series = dom.leerSeries(documento);
        int seriesValidas = 0;





            List<Serie> seriesInvalidas = new ArrayList<>();
            for (Serie serie : series) {
                if (serie.getAnio() <= 2030 && serie.getAnio() >= 1950 && serie.getValoracion() <= 10 && serie.getValoracion() >= 0 && serie.getTemporadas() >= 1) {
                    seriesValidas++;
                }else{
                    seriesInvalidas.add(serie);
                }
            }


    }
}
