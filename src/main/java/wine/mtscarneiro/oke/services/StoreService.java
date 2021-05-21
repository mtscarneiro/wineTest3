package wine.mtscarneiro.oke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import wine.mtscarneiro.oke.entities.Store;
import wine.mtscarneiro.oke.repositories.StoreRepository;
import wine.mtscarneiro.oke.services.exceptions.DatabaseException;
import wine.mtscarneiro.oke.services.exceptions.ResourceNotFoundException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;


    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    public Store findById(Long id) {
        Optional<Store> obj = storeRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Store insert(Store obj) {
        return storeRepository.save(obj);
    }

    public void delete(Long id) {
        try {
            storeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private void updateData(Store entity, Store obj) {
        entity.setStoreCode(obj.getStoreCode());
    }

    public Store update(Long id, Store obj) {
        try {
            Store entity = storeRepository.getOne(id);
            updateData(entity, obj);
            return storeRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}
