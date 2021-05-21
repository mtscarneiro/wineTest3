package wine.mtscarneiro.oke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import wine.mtscarneiro.oke.entities.NearestStore;
import wine.mtscarneiro.oke.entities.Store;
import wine.mtscarneiro.oke.repositories.NearestStoreRepository;
import wine.mtscarneiro.oke.services.exceptions.DatabaseException;
import wine.mtscarneiro.oke.services.exceptions.ResourceNotFoundException;

import javax.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class NearestStoreService {

    @Autowired
    private NearestStoreRepository repository;

    public List<NearestStore> findIt() {
        return repository.findAll();
    }

    public List<NearestStore> findAll() {
        return repository.findAll();
    }

    public NearestStore findById(Long id) {
        Optional<NearestStore> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public NearestStore insert(NearestStore obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private void updateData(NearestStore entity, NearestStore obj) {
        entity.setOwnZip(obj.getOwnZip());
    }

    public NearestStore update(Long id, NearestStore obj) {
        try {
            NearestStore entity = repository.getOne(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
















}
