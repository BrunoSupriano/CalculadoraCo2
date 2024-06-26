package nature.calculadoraco22.Repositories;

import nature.calculadoraco22.Model.TreePlanting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreePlantingRepository extends JpaRepository<TreePlanting, Long> {

    List<TreePlanting> findByUserId(Long userId);

    TreePlanting findByUserIdAndId(Long userId, Long id);

}
