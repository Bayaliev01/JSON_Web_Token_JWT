package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.model.Auth;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {

    Auth findByEmail(String email);

}
