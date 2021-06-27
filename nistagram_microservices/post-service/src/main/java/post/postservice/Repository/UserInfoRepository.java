package post.postservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import post.postservice.Model.Location;

@Repository
public interface UserInfoRepository extends JpaRepository<Location, Long> {
}
