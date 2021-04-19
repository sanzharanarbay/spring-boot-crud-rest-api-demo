package com.testapp.mydemo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.testapp.mydemo.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
