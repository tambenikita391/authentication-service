package org.dnyanyog.service;

import java.util.List;
import org.dnyanyog.dto.AddUserRequest;
import org.dnyanyog.dto.AddUserResponse;
import org.dnyanyog.entity.UsersDetails;

public interface UserManagementService {
  public AddUserResponse addUser(AddUserRequest request) throws Exception;

  public AddUserResponse searchUser(int userId);

  public List<UsersDetails> getUser();
}
