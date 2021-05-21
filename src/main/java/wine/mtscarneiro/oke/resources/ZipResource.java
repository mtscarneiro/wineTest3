package wine.mtscarneiro.oke.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import wine.mtscarneiro.oke.entities.Store;
import wine.mtscarneiro.oke.entities.Zip;
import wine.mtscarneiro.oke.services.ZipService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/zip")
public class ZipResource {

    @Autowired
    private ZipService service;

    @GetMapping
    public ResponseEntity<List<Zip>> findAll() {
        List<Zip> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Zip> findById(@PathVariable Long id) {
        Zip obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Zip> insert(@RequestBody Zip obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Zip> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Zip> update(@PathVariable Long id, @RequestBody Zip obj ) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
