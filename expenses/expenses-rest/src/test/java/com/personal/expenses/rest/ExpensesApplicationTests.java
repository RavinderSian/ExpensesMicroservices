package com.personal.expenses.rest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.personal.expenses.repository.ExpenseRepository;
import com.personal.expenses.repository.UserRepository;
import com.personal.expenses.service.ExpenseService;
import com.personal.expenses.service.UserService;

@SpringBootTest
class ExpensesApplicationTests {
	
	
	@MockBean
	private ExpenseService service;
	
	@MockBean
	private ExpenseRepository expenseRepository;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	@Test
	void contextLoads() {
	}

}
