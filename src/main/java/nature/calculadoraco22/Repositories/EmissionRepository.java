package nature.calculadoraco22.Repositories;

import nature.calculadoraco22.Model.Emission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmissionRepository extends JpaRepository<Emission, Long> {

    List<Emission> findByUserIdAndMonthAndYear(Long userId, int month, int year);

    List<Emission> findByUserIdAndYear(Long userId, int year);
}
