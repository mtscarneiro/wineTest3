package wine.mtscarneiro.oke.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wine.mtscarneiro.oke.entities.Zip;

public interface ZipRepository extends JpaRepository<Zip, Long> {
}
