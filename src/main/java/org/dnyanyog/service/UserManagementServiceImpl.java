package org.dnyanyog.service;

import java.util.List;
import java.util.Optional;

import org.dnyanyog.dto.AddUserRequest;
import org.dnyanyog.dto.AddUserResponse;
import org.dnyanyog.dto.UserData;
import org.dnyanyog.encryption.EncryptionService;
import org.dnyanyog.entity.Users;
import org.dnyanyog.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagementServiceImpl implements UserManagementService {
	@Autowired
	UsersRepository repo;
	
	@Autowired
	EncryptionService encryptionService;

	public AddUserResponse addUser(AddUserRequest request) throws Exception {
		AddUserResponse response = new AddUserResponse();

		Users userTable = Users.getInstance()
				.setUserName(request.getUserName())
				.setPassword(encryptionService.encrypt(request.getPassword()))
				.setEmail_id(request.getEmailId())
				.setAge(request.getAge())
				.setUser_id(request.getUserId());

		try {
			userTable = repo.save(userTable);
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.setStatus("Success");
		response.setMessage("User added successfully!!");
		response.setUserCode(userTable.getUser_code());
		return response;

	}

	public AddUserResponse searchUser(int userId) {
		AddUserResponse response = new AddUserResponse();
		Optional<Users> userTable = repo.findById(userId);

		if (userTable.isEmpty()) {
			response.setStatus("Fail");
			response.setMessage("User not found");
			response.setUserCode(0000);
		} else {
			Users user = userTable.get();
			response.setStatus("Success");
			response.setMessage("User found");
			response.setUserCode(user.getUser_code());
			response.setUserName(user.getUserName());
			response.setPassword(user.getPassword());
			response.setEmailId(user.getEmail_id());
			response.setAge(user.getAge());
			response.setUserId(user.getUser_id());

		}
		return response;

	}

	public List<Users> getUser() {
		return repo.findAll();
	}

}
