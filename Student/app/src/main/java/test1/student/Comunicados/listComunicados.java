package test1.student.Comunicados;

/**
 * Created by Jespi on 03-07-2017.
 */

public class listComunicados {
    String texto,fecha;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public listComunicados(String texto, String fecha) {
        this.texto = texto;
        this.fecha=fecha;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
