package user.userservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user.userservice.Model.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request,Long> {
}
