package io.dm848.microservices.userservice.users;

import java.util.List;
import java.util.logging.Logger;

import io.dm848.microservices.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A RESTFul controller for accessing controller information.
 */
@RestController
public class UsersController {

	protected Logger logger = Logger.getLogger(UsersController.class
			.getName());
	protected UserRepository userRepository;

	/**
	 * Create an instance plugging in the respository of Accounts.
	 * 
	 * @param userRepository
	 *            An controller repository implementation.
	 */
	@Autowired
	public UsersController(UserRepository userRepository) {
		this.userRepository = userRepository;

		logger.info("UserRepository says system has "
				+ userRepository.countUsers() + " users");
	}

	/**
	 * Fetch an controller with the specified controller number.
	 * 
	 * @param userNumber
	 *            A numeric, 9 digit controller number.
	 * @return The controller if found.
	 * @throws UserNotFoundException
	 *             If the number is not recognised.
	 */
	@RequestMapping("/users/{userNumber}")
	public User byNumber(@PathVariable("userNumber") String userNumber) {

		logger.info("user-service byNumber() invoked: " + userNumber);
		User user = userRepository.findByNumber(userNumber);
		logger.info("user-service byNumber() found: " + user);

		if (user == null)
			throw new UserNotFoundException(userNumber);
		else {
			return user;
		}
	}

	/**
	 * Fetch userservice with the specified name. A partial case-insensitive match
	 * is supported. So <code>http://.../accounts/owner/a</code> will find any
	 * userservice with upper or lower case 'a' in their name.
	 * 
	 * @param partialName
	 * @return A non-null, non-empty set of userservice.
	 * @throws UserNotFoundException
	 *             If there are no matches at all.
	 */
	@RequestMapping("/users/owner/{name}")
	public List<User> byOwner(@PathVariable("name") String partialName) {

		logger.info("user-service byOwner() invoked: "
				+ userRepository.getClass().getName() + " for "
				+ partialName);

		List<User> users = userRepository
				.findByOwnerContainingIgnoreCase(partialName);

		logger.info("user-service byOwner() found: " + users);

		if (users == null || users.size() == 0)
			throw new UserNotFoundException(partialName);
		else {
			return users;
		}
	}
}