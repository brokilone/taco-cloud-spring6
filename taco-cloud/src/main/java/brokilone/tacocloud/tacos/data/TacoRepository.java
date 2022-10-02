package brokilone.tacocloud.tacos.data;

import brokilone.tacocloud.tacos.Taco;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Kseniia Ushakova
 */
public interface TacoRepository extends CrudRepository<Taco, Long> {
  Iterable<Taco> findAll(Pageable page);
}
