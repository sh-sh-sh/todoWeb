package userTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.study.dao.UserDaoImpl;
import org.study.dao.UserService;

public class PasswordTest {
	
	UserService us=new UserDaoImpl();

	@Test
	public void testPasswordCheck() {
//		fail("아직 구현되지 않음");
//		assertTrue(us.passwordCheck("user1", "error"));
		assertTrue(us.passwordCheck("user1", "asdf"));
	}

}
