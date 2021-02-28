package com.example.demo.auth;


import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface ApplicationUserDao {

    public Optional<ApplicationUser> selectApplicationUserByUsername(String userName);
}
