package com.spring.fakestore.fakestore.models;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

    private String Rolename;

    public Role(String rolename) {
        Rolename = rolename;
    }

    @Override
    public String toString() {
        return "Role{" +
                "Rolename='" + Rolename + '\'' +
                '}';
    }

    @Override
    public String getAuthority() {
        return Rolename;
    }
}
