package com.wipro.Login.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.Login.Enitiy.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
