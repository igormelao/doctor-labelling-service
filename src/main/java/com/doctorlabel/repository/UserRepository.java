package com.doctorlabel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctorlabel.model.User;

/**
 * 
 * Interface responsible to manage database storage for User.
 * 
 * @author Igor Melão (igormelao@gmail.com)
 * @Date:  14-03-2021
 * @since  0.0.1-SNAPSHOT
 */
public interface UserRepository extends JpaRepository<User, Long>{

	/**
	 * <p>Find some user through your e-mail</p>
	 * <p> This is a derived query Spring Boot that allow us to
	 * only put the attribute that we need to find and don't need
	 * to implement the query. Spring Boot will take care of all</p>
	 * 
	 * @author Igor Melão (igormelao@gmail.com)
	 * @param
	 * @return User @see(com.doctorlabel.model.User)
	 * @since 0.0.1-SNAPSHOT
	 */
	Optional<User> findByEmail(String email);
}
