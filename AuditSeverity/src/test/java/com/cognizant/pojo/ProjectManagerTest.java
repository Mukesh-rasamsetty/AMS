package com.cognizant.pojo;

import static org.junit.Assert.assertEquals;

/**
 * @author POD5
 * @version 1.8
 * @apiNote To test ProjectManager class
 * 
 *
 */
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectManagerTest {

	ProjectManager projectManager = new ProjectManager();

	@Test
	public void testProjectManagerAllConstructor() {
		ProjectManager manager = new ProjectManager("ab", "ab", null);
		assertEquals("ab", manager.getUserId());
	}

	@Test
	public void testUserid() {
		projectManager.setUserId("abc");
		assertEquals("abc", projectManager.getUserId());
	}

	@Test
	public void testUserPassword() {
		projectManager.setPassword("abc");
		assertEquals("abc", projectManager.getPassword());
	}

	@Test
	public void testAuthToken() {
		projectManager.setAuthToken("abc");
		assertEquals("abc", projectManager.getAuthToken());
	}

	@Test
	public void testoString() {
		String string = projectManager.toString();
		assertEquals(string, projectManager.toString());
	}

}
