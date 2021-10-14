package todo1.sysiems.store.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import todo1.sysiems.store.security.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
