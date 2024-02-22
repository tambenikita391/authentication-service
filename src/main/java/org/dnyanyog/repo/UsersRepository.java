package org.dnyanyog.repo;

import java.util.List;

import org.dnyanyog.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface UsersRepository extends JpaRepository<Users, Integer> {

	List<Users> findByUserNameAndPassword(String userName, String password);

}
