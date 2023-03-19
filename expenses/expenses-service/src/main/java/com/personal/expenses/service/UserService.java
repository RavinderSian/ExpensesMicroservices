package com.personal.expenses.service;

import java.util.Optional;

import com.personal.expenses.model.User;

public interface UserService {
	
	User save(User user);
	void deleteById(Long id);
	Optional<User> findByUsername(String username);
	Optional<User> findById(Long id);

}
