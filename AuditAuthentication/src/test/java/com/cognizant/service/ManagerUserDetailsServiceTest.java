package com.cognizant.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.model.ProjectManager;
import com.cognizant.repository.ManagerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerUserDetailsServiceTest {

	UserDetails userdetails;

	@InjectMocks
	ManagerDetailsService managerdetailservice;

	@Mock
	ManagerRepository userservice;

	@Test
	public void loadUserByUsernameTest() {

		ProjectManager user1 = new ProjectManager("admin", "admin", null);
		Optional<ProjectManager> data = Optional.of(user1);
		when(userservice.findById("admin")).thenReturn(data);
		UserDetails loadUserByUsername2 = managerdetailservice.loadUserByUsername("admin");
		assertEquals(user1.getUserId(), loadUserByUsername2.getUsername());
	}

}
