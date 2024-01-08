package com.revature.ecommerce.dtos;

import com.revature.ecommerce.entities.User;
import com.revature.ecommerce.entities.Auth;

import java.util.Objects;


public class NewUserDto {
    private User user;
    private Auth auth;

    public NewUserDto(User user, Auth auth){
        this.user = user;
        this.auth = auth;
    }

    public NewUserDto() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewUserDto that = (NewUserDto) o;
        return Objects.equals(user, that.user) && Objects.equals(auth, that.auth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, auth);
    }

    @Override
    public String toString() {
        return "NewUserDto{" +
                "user=" + user +
                ", auth=" + auth +
                '}';
    }
}
