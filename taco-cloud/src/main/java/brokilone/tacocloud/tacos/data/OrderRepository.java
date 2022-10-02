package brokilone.tacocloud.tacos.data;

import brokilone.tacocloud.tacos.TacoOrder;
import brokilone.tacocloud.tacos.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * @author Kseniia Ushakova
 */
public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

  List<TacoOrder> findByDeliveryZip(String deliveryZip);

  List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);

  List<TacoOrder> findByDeliveryNameAndDeliveryCityAllIgnoringCase(String deliveryName, String deliveryCity);

  List<TacoOrder> findByDeliveryCityOrderByDeliveryName(String deliveryCity);

  List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);

}
