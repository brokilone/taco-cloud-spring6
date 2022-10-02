package brokilone.tacocloud.tacos.web.api;

import brokilone.tacocloud.tacos.Taco;
import brokilone.tacocloud.tacos.data.TacoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Kseniia Ushakova
 */
@RestController
@RequestMapping(path = "api/tacos", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://tacocloud:8080")
@RequiredArgsConstructor
public class TacoController {
  private final TacoRepository tacoRepository;

  @GetMapping(params = "recent")
  public Iterable<Taco> recentTacos(){
    PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
    return tacoRepository.findAll(page);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
    return tacoRepository.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public Taco postTaco(@RequestBody Taco taco) {
    return tacoRepository.save(taco);
  }
}
