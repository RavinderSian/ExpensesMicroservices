package com.personal.expenses.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.personal.expenses.repository.ExpenseRepository;
import com.personal.expenses.repository.UserRepository;
import com.personal.expenses.rest.controller.ExpenseJsonController;
import com.personal.expenses.service.ExpenseService;
import com.personal.expenses.service.ExpenseServiceImpl;
import com.personal.expenses.service.UserService;
import com.personal.expenses.service.UserServiceImpl;

@SpringBootTest
class ExpensesRestApplicationTests {
	
	
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
