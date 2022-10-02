package com.brokilone.authserver.tacos.user;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Kseniia Ushakova
 */
public interface UserRepository  extends CrudRepository<User, Long> {
  User findByUsername(String username);
}
