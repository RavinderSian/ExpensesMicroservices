package com.personal.expenses.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.personal.expenses.model.Expense;
import com.personal.expenses.service.ExpenseService;
import com.personal.expenses.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@PreAuthorize("hasAuthority('USER')")
@RestController("/expenses")
public class ExpenseJsonController {
	
	private final ExpenseService service;
	private final UserService userService;
	
	public ExpenseJsonController(ExpenseService service, UserService userService) {
		this.service = service;
		this.userService = userService;
	}
	
	@GetMapping("/delete/{id}")
	public ResponseEntity<?> deleteExpense(@PathVariable Long id, HttpServletRequest request,
			 RedirectAttributes redirectAttributes) {
		
		service.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestBody String searchString ,HttpServletRequest request) {
		
		Long userId = userService.findByUsername(request.getUserPrincipal().getName()).get().getId();

		List<Expense> results = service.getSearchResults(userId, searchString);
		
		return new ResponseEntity<>(results, HttpStatus.OK);
	}
	
	@PostMapping("/addexpensejson")
	public ResponseEntity<?> addExpenseJSON(@RequestBody @Valid Expense newExpense, BindingResult bindingResult,
			HttpServletRequest request) {
		
		if (bindingResult.hasFieldErrors()) {
			
			Map<String, String> errorMap = new HashMap<>();
			bindingResult.getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
			
			return new ResponseEntity<>(errorMap.toString(), HttpStatus.BAD_REQUEST);
		}
		
		String loggedInUsername = request.getUserPrincipal().getName();
		
		newExpense.setUserId(userService.findByUsername(loggedInUsername).get().getId());
		
		try {
			service.save(newExpense);
		}
		catch(DataAccessException exception) {
			return new ResponseEntity<>("Currently down due to maintenance", HttpStatus.SERVICE_UNAVAILABLE);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<?> getExpensesForUser(@PathVariable String username, HttpServletRequest request,
			 RedirectAttributes redirectAttributes) {
		
		if (userService.findByUsername(username).isEmpty()) {
			log.error(String.format("User %s not found", username));
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
