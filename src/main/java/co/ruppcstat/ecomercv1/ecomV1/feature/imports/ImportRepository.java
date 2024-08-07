package co.ruppcstat.ecomercv1.ecomV1.feature.imports;

import co.ruppcstat.ecomercv1.ecomV1.deman.Import;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImportRepository extends JpaRepository<Import, Integer> {
    Optional<Import> findByImportId(Integer importId);
    Boolean existsByImportId(Integer importId);
    Optional<Import> findByCodeNumber(String codeNumber);
    Boolean existsByCodeNumber(String codeNumber);
}
