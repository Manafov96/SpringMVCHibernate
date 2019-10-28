package com.springBootMvcHibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springBootMvcHibernate.models.Roles;

@Repository("roleRepository")
public interface RoleRespository extends JpaRepository<Roles, Integer> {

	Roles findByRole(String role);
}