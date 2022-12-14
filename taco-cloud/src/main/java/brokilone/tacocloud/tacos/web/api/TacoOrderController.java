package brokilone.tacocloud.tacos.web.api;

import brokilone.tacocloud.tacos.TacoOrder;
import brokilone.tacocloud.tacos.data.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kseniia Ushakova
 */
@Slf4j
@RestController
@RequestMapping(path = "api/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@CrossOrigin(origins = "http://tacocloud:8080")
public class TacoOrderController {
  private final OrderRepository orderRepository;

  @PutMapping(path="/{orderId}", consumes="application/json")
  public TacoOrder putOrder(
      @PathVariable("orderId") Long orderId,
      @RequestBody TacoOrder order) {
    order.setId(orderId);
    return orderRepository.save(order);
  }

  @PatchMapping(path="/{orderId}", consumes="application/json")
  public ResponseEntity<TacoOrder> patchOrder(@PathVariable("orderId") Long orderId,
                              @RequestBody TacoOrder patch) {
    return orderRepository.findById(orderId)
        .map(found -> patchOrderFromDb(found, patch))
        .map(ResponseEntity::ok)
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

  }
  @DeleteMapping("/{orderId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteOrder(@PathVariable("orderId") Long orderId) {
    try {
      orderRepository.deleteById(orderId);
    } catch (EmptyResultDataAccessException e) {
      log.info("Deleting resource is not found by id {}", orderId);
    }
  }

  private TacoOrder patchOrderFromDb(TacoOrder order, TacoOrder patch) {
    if (patch.getDeliveryName() != null) {
      order.setDeliveryName(patch.getDeliveryName());
    }
    if (patch.getDeliveryStreet() != null) {
      order.setDeliveryStreet(patch.getDeliveryStreet());
    }
    if (patch.getDeliveryCity() != null) {
      order.setDeliveryCity(patch.getDeliveryCity());
    }
    if (patch.getDeliveryState() != null) {
      order.setDeliveryState(patch.getDeliveryState());
    }
    if (patch.getDeliveryZip() != null) {
      order.setDeliveryZip(patch.getDeliveryZip());
    }
    if (patch.getCcNumber() != null) {
      order.setCcNumber(patch.getCcNumber());
    }
    if (patch.getCcExpiration() != null) {
      order.setCcExpiration(patch.getCcExpiration());
    }
    if (patch.getCcCVV() != null) {
      order.setCcCVV(patch.getCcCVV());
    }
    return orderRepository.save(order);
  }
}
