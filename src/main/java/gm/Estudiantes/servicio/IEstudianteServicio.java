package gm.Estudiantes.servicio;

import gm.Estudiantes.modelo.Estudiante;
import java.util.List;

public interface IEstudianteServicio {
    public List <Estudiante> listarEstudiantes();

    public Estudiante buscarEstudiantePorId(Integer idEstudiante);

    public void guardarEstudiante(Estudiante estudiante);

    // --- AGREGAR ESTOS DOS MÉTODOS ---
    public void modificarEstudiante(Estudiante estudiante); // Para la modificación
    public void eliminarEstudiantePorId(Integer idEstudiante); // Para eliminar por ID
    // ---------------------------------

    public void eliminarEstudiante(Estudiante estudiante);
}