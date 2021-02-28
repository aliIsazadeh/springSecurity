package com.example.demo.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


import static com.example.demo.security.ApplicationUsersRole.*;
@Repository("fake")
public class FakeApplicationDaoUserService implements ApplicationUserDao{

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationDaoUserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String userName) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> userName.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers(){
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(
                         "Amin"
                        ,passwordEncoder.encode("password")
                        ,STUDENT.getGrantedAuthority(),
                        true
                        ,true
                        ,true
                        ,true
                )
                , new ApplicationUser(
                "Ali"
                , passwordEncoder.encode("password")
                , ADMIN.getGrantedAuthority(),
                true
                ,true
                ,true
                ,true
                )
                ,new ApplicationUser(
                        "hadi"
                        ,passwordEncoder.encode("password")
                        ,ADMIN_READ.getGrantedAuthority(),
                        true
                        ,true
                        ,true
                        ,true
                )




                );
        return applicationUsers;

    }
}
