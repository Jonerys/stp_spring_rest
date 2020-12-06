package jonerys.test.springcrud.repository;

import jonerys.test.springcrud.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("FROM User WHERE login = :login")
    User findByLogin(@Param("login") String login);

    @Query("SELECT id FROM User WHERE login = :login")
    Integer findIDByLogin(@Param("login") String login);

    @Transactional
    @Modifying
    @Query("DELETE FROM User WHERE login = :login")
    void deleteByLogin(@Param("login") String login);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User SET login = :login WHERE id = :id")
    void update(@Param("id") Integer id, @Param("login") String login);
}
