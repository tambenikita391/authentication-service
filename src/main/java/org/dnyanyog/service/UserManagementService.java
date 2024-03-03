package org.dnyanyog.service;

import java.util.List;
import java.util.Optional;

import org.dnyanyog.dto.AddUserRequest;
import org.dnyanyog.dto.AddUserResponse;
import org.dnyanyog.dto.DiscountRequest;
import org.dnyanyog.dto.DiscountResponse;
import org.dnyanyog.entity.Discount;
import org.dnyanyog.entity.Users;

public interface UserManagementService {
	public AddUserResponse addUser(AddUserRequest request) throws Exception;

	public AddUserResponse searchUser(int userId);

	public List<Users> getUser();

	public Optional<DiscountResponse> addDiscount(DiscountRequest request);

}
