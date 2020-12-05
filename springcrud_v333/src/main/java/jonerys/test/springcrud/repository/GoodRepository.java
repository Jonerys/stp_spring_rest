package jonerys.test.springcrud.repository;

import jonerys.test.springcrud.model.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodRepository extends JpaRepository<Good, Integer> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Good SET name = :name WHERE id = :id")
    void update(@Param("id") Integer id, @Param("name") String name);

    @Query("FROM Good WHERE name = :name")
    Good findByName(@Param("name") String name);
}
