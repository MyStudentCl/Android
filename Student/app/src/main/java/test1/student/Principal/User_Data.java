package test1.student.Principal;

/**
 * Created by Jespi on 03-07-2017.
 */

public class User_Data  {
    public static String  IdUser,NombreUser, NombreAlumno, NombreAsignatura;

    public User_Data() {

    }

    public static String getIdUser() {
        return IdUser;
    }

    public static void setIdUser(String idUser) {
        IdUser = idUser;
    }

    public static String getNombreUser() {
        return NombreUser;
    }

    public static void setNombreUser(String nombreUser) {
        NombreUser = nombreUser;
    }


    public static String getNombreAlumno() {
        return NombreAlumno;
    }

    public static void setNombreAlumno(String nombreAlumno) {
        NombreAlumno = nombreAlumno;
    }

    public static String getNombreAsignatura() {
        return NombreAsignatura;
    }


    public static void setNombreAsignatura(String nombreAsignatura) {
        NombreAsignatura = nombreAsignatura;
    }

}