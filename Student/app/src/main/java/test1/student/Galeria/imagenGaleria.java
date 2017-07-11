package test1.student.Galeria;

/**
 * Created by Jespi on 27-06-2017.
 */

public class imagenGaleria {

    String Titulo,/*Detalle,*/Imagen;

    public imagenGaleria(String titulo/*, String detalle*/, String imagen) {
        Titulo = titulo;
        //Detalle = detalle;
        Imagen = imagen;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    /*public String getDetalle() {
        return Detalle;
    }*/

   /* public void setDetalle(String detalle) {
        Detalle = detalle;
    }*/

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }
}
