package gm.Estudiantes.servicio;

import gm.Estudiantes.modelo.Estudiante;
import gm.Estudiantes.repositorio.EstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional; // --- AGREGAR ESTA IMPORTACIÓN ---

@Service
public class EstudianteServicio implements IEstudianteServicio{
    @Autowired
    private EstudianteRepositorio estudianteRepositorio;

    @Override
    public List<Estudiante> listarEstudiantes() {
        List<Estudiante> estudiantes = estudianteRepositorio.findAll();
        return estudiantes;
    }

    @Override
    public Estudiante buscarEstudiantePorId(Integer idEstudiante) {
        // --- CAMBIAR ESTA LÍNEA ---
        Optional<Estudiante> estudianteOptional = estudianteRepositorio.findById(idEstudiante);
        return estudianteOptional.orElse(null); // Retorna el estudiante o null si no se encuentra
        // ------------------------
    }

    @Override
    public void guardarEstudiante(Estudiante estudiante) {
        estudianteRepositorio.save(estudiante);
    }

    // --- AGREGAR ESTE MÉTODO ---
    @Override
    public void modificarEstudiante(Estudiante estudiante) {
        // El método save() de JpaRepository maneja tanto la creación como la actualización.
        // Si la entidad tiene un ID que existe en la DB, actualiza; de lo contrario, inserta.
        estudianteRepositorio.save(estudiante);
    }
    // ---------------------------

    @Override
    public void eliminarEstudiante(Estudiante estudiante) {
        estudianteRepositorio.delete(estudiante);
    }

    // --- AGREGAR ESTE MÉTODO ---
    @Override
    public void eliminarEstudiantePorId(Integer idEstudiante) {
        estudianteRepositorio.deleteById(idEstudiante); // Elimina directamente por ID
    }
    // ---------------------------
}