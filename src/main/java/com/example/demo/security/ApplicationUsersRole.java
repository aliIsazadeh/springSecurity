package com.example.demo.security;

import com.google.common.collect.Sets;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.security.ApplicationUsersPermission.*;

public enum ApplicationUsersRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(STUDENT_READ,STUDENT_WRITE,COURSE_READ,COURSE_WRITE)),
    ADMIN_READ(Sets.newHashSet(STUDENT_READ,COURSE_READ));


    private final Set<ApplicationUsersPermission> permissions;

    ApplicationUsersRole(Set<ApplicationUsersPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUsersPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthority(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
