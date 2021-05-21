package wine.mtscarneiro.oke.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import wine.mtscarneiro.oke.entities.Store;
import wine.mtscarneiro.oke.services.StoreService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/stores")
public class StoreResource {

    @Autowired
    private StoreService service;

    @GetMapping
    public ResponseEntity<List<Store>> findAll() {
        List<Store> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Store> findById(@PathVariable Long id) {
        Store obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Store> insert(@RequestBody Store obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Store> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Store> update(@PathVariable Long id, @RequestBody Store obj ) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
