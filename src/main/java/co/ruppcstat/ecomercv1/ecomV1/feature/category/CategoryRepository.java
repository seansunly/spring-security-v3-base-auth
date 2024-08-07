package co.ruppcstat.ecomercv1.ecomV1.feature.category;

import co.ruppcstat.ecomercv1.ecomV1.deman.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByName(String name);
    Boolean existsByName(String name);
    Optional<Category> findByCategoryID(Integer categoryId);
    Boolean existsByCategoryID(Integer categoryId);


}
