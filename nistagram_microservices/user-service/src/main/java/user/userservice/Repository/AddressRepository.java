package user.userservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user.userservice.Model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
}
