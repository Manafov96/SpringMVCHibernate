package com.springBootMvcHibernate.services;

import com.springBootMvcHibernate.models.Users;

public interface UserService {

	public Users findUserByEmail(String email);

	public void saveUser(Users user);
}