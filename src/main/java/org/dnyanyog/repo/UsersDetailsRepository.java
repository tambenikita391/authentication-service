package org.dnyanyog.repo;

import java.util.List;
import org.dnyanyog.entity.UsersDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface UsersDetailsRepository extends JpaRepository<UsersDetails, Integer> {

  List<UsersDetails> findByUserNameAndPassword(String userName, String password);
}
