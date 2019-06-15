package com.example.springbootruanjian.repository;

import com.example.springbootruanjian.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends CustomizedRepository<User,Integer> {
    @Query("select u from User u where u.userName=:username")
    User find(@Param("username") String username);

    @Query("select u from User u where u.authority=:aid")
    List<User> listByauthority(@Param("aid") int aid);

    @Transactional
    @Modifying
    @Query("update User u set u.authority=:aid where u.id=:id")
    void addadminer(@Param("id") int id,@Param("aid") int aid);
}
