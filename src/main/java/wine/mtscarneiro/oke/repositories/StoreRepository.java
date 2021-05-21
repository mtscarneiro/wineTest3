package wine.mtscarneiro.oke.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wine.mtscarneiro.oke.entities.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
