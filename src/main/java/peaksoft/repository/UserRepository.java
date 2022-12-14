package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.UserResponse;
import peaksoft.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

//    @Query("""
//           select new peaksoft.dto.UserResponse(
//           u.id,
//           u.name,
//           u.email,
//           u.password)
//           from User u
//           """)
//    List<UserResponse> findAllByUsers();
}
