package jonerys.test.springcrud.repository;

import jonerys.test.springcrud.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Warehouse SET name = :name WHERE id = :id")
    void update(@Param("id") Integer id, @Param("name") String name);

    @Query("SELECT id FROM Warehouse WHERE name = :name")
    Integer findIdByName(@Param("name") String name);

    @Query("FROM Warehouse WHERE name = :name")
    Warehouse findByName(@Param("name") String name);

}
