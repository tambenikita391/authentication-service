package org.dnyanyog.service;

import java.util.List;

import org.dnyanyog.dto.LoginRequest;
import org.dnyanyog.dto.LoginResponse;
import org.dnyanyog.encryption.EncryptionService;
import org.dnyanyog.entity.Users;
import org.dnyanyog.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class LoginServiceImpl implements LoginService {

	@Autowired
	UsersRepository repo;

	@Autowired
	EncryptionService encryptionService;

	public LoginResponse validateUser(LoginRequest request) throws Exception {
		LoginResponse response = new LoginResponse();

		List<Users> user = repo.findByUserNameAndPassword(request.getUserName(),
				encryptionService.encrypt(request.getPassword()));

		if (user.size() == 1) {
			response.setMessage("Login successful ");

		} else {
			response.setMessage("Login failed ");
		}
		return response;
	}

	public LoginResponse addUser(LoginRequest request) throws Exception {
		LoginResponse response = new LoginResponse();
		Users users = new Users();

		users.setUserName(request.getUserName());
		users.setPassword(encryptionService.encrypt(request.getPassword()));

		users = repo.save(users);
		response.setMessage("Sign up successfully !! ");
		return response;
	}
}
