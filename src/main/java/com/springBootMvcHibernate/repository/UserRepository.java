package com.springBootMvcHibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springBootMvcHibernate.models.Users;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<Users, Long> {

	Users findByEmail(String email);
}