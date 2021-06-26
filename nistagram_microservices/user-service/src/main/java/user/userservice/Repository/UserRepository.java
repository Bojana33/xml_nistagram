package user.userservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user.userservice.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
