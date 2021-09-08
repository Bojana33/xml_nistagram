package post.postservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import post.postservice.DTO.User;
import post.postservice.Model.Location;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserInfoRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);

    Optional<User> findById(Long id);


}
