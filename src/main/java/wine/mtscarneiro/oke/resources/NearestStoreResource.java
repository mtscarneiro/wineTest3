package wine.mtscarneiro.oke.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import wine.mtscarneiro.oke.entities.NearestStore;
import wine.mtscarneiro.oke.services.NearestStoreService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/require/store/near-me")
public class NearestStoreResource {

    @Autowired
    private NearestStoreService service;

    @GetMapping
    public ResponseEntity<List<NearestStore>> findIt() {
        List<NearestStore> ns = service.findIt();
        return ResponseEntity.ok().body(ns);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<NearestStore> findById(@PathVariable Long id) {
        NearestStore obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<NearestStore> insert(@RequestBody NearestStore obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<NearestStore> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<NearestStore> update(@PathVariable Long id, @RequestBody NearestStore obj ) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
