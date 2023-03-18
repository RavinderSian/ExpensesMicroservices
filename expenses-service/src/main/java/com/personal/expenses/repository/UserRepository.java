package com.personal.expenses.repository;

import java.util.Optional;

import com.personal.expenses.model.User;

public interface UserRepository {

	User save(User user);
	void deleteById(Long id);
	Optional<User> findByUsername(String username);
	Optional<User> findById(Long id);
}
 