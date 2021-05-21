package wine.mtscarneiro.oke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import wine.mtscarneiro.oke.entities.Zip;
import wine.mtscarneiro.oke.repositories.ZipRepository;
import wine.mtscarneiro.oke.services.exceptions.DatabaseException;
import wine.mtscarneiro.oke.services.exceptions.ResourceNotFoundException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ZipService {

    @Autowired
    private ZipRepository zipRepository;

    public List<Zip> findAll() {
        return zipRepository.findAll();
    }

    public Zip findById(Long id) {
        Optional<Zip> obj = zipRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Zip insert(Zip obj) {
        return zipRepository.save(obj);
    }

    public void delete(Long id) {
        try {
            zipRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private void updateData(Zip entity, Zip obj) {
       entity.setStartZip(obj.getStartZip());
       entity.setEndZip(obj.getEndZip());
    }

    public Zip update(Long id, Zip obj) {
        try {
            Zip entity = zipRepository.getOne(id);
            updateData(entity, obj);
            return zipRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}
