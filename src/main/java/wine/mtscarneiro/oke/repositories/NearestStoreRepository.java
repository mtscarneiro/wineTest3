package wine.mtscarneiro.oke.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wine.mtscarneiro.oke.entities.NearestStore;

public interface NearestStoreRepository extends JpaRepository<NearestStore, Long> {
}
