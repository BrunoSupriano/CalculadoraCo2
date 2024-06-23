package nature.calculadoraco22.Repositories;

import nature.calculadoraco22.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}