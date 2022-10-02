package brokilone.tacocloud.tacos.data;

import brokilone.tacocloud.tacos.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Kseniia Ushakova
 */
public interface UserRepository extends CrudRepository<User, Long> {
  User findByUsername(String username);
}
