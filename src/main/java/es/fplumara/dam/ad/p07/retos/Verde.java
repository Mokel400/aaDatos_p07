package es.fplumara.dam.ad.p07.retos;

import es.fplumara.dam.ad.p07.modelo.Serie;
import es.fplumara.dam.ad.p07.servicio.CatalogoJaxb;
import es.fplumara.dam.ad.p07.modelo.Catalogo;

import jakarta.xml.bind.JAXBException;

import java.util.ArrayList;
import java.util.List;

public class Verde {

    public static void main(String[] args) {
        try{
            CatalogoJaxb jaxb = new CatalogoJaxb();

            Catalogo catalogo = jaxb.leer("datos/series.xml");

            List<Serie> seriesComedia = new ArrayList<>();
            for (Serie serie : catalogo.getSeries()) {
                if (serie.getGenero().equalsIgnoreCase("comedia")) {
                    seriesComedia.add(serie);
                }
            }
            Catalogo catalogoComedia = new Catalogo(catalogo.getPlataforma(), seriesComedia);
            jaxb.guardar(catalogoComedia, "datos/genero-comedia.xml");
            System.out.println("Series de comedia guardadas: " + seriesComedia.size());
            System.out.println("Series de comedia guardadas en: datos/genero-comedia.xml");

            Catalogo comprobacion = jaxb.leer("datos/genero-comedia.xml");
            System.out.println(comprobacion);
            for (Serie serie : comprobacion.getSeries()) {
                System.out.println(serie);
            }
        } catch(JAXBException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

}
