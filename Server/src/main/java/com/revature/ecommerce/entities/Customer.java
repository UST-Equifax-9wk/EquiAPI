package com.revature.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "customers")
public class Customer implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    Integer customerId;
    @Column
    String email;
    @Column(name="first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column
    String password;

    @Column(columnDefinition = "varchar(255) default 'CUSTOMER'")
    String role;


    @JsonIgnore
    @JsonManagedReference(value = "cartReference")
    @OneToMany(mappedBy = "customer")
    private Set<Cart> carts = new HashSet<Cart>();

    @Getter
    @JsonManagedReference(value = "orderReference")
    @OneToMany(mappedBy = "customer")
    private Set<Order> orders = new HashSet<Order>();

    @JsonManagedReference(value = "apiReference")
    @OneToMany(mappedBy = "customer")
    private Set<Api> apis = new HashSet<Api>();


    public Customer() {
    }

    public Customer(String email, String firstName, String lastName, String password,
                    Set<Cart> carts,  Set<Order> orders, Set<Api> apis) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.carts = carts;
        this.orders = orders;
        this.apis = apis;
    }

    public Customer(Integer customerId, String email, String firstName, String lastName, String password,
                    Set<Cart> carts,  Set<Order> orders, Set<Api> apis) {
        this.customerId = customerId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.carts = carts;
        this.orders = orders;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> role);
    }

    public Set<Api> getApis() {
        return apis;
    }

    public void setApis(Set<Api> apis) {
        this.apis = apis;
    }

    public Integer getCustomerId() {
        return customerId;
    }


    public String getEmail() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Cart> getCart() {
        return carts;
    }

    public void setCart(Set<Cart> carts) {
        this.carts = carts;
    }




    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId) && Objects.equals(email, customer.email)
                && Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName)
                && Objects.equals(password, customer.password);// && Objects.equals(cart, customer.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, email, firstName, lastName, password); //, cart);
    }

    @Override
    public String toString() {


        return "Customer{" +
                "customerId=" + customerId +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '}'; //\'' +
//                ", cart=" + cart +
//                '}';
    }

}