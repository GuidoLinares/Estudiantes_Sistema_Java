package gm.Estudiantes.repositorio;

import gm.Estudiantes.modelo.Estudiante;
import gm.Estudiantes.EstudiantesApplication;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface EstudianteRepositorio extends JpaRepository <Estudiante, Integer> {
}
