package repositories;

import model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Page<User> findBySalaryBetween(Double minSalary, Double maxSalary, Pageable pageable);
    Page<User> findByNameContainingIgnoreCase(String name, Pageable pageable);
}