package com.sudip.users.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);
	
}
