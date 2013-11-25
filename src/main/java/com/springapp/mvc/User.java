
package com.springapp.mvc;

import javax.persistence.*;
import java.util.Set;

     @Entity (name = "account")
     public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Basic
        @Column (length = 50)
        private String firstName;

        @Basic
        @Column (length = 50)
        private String lastName;

        @Basic
        private String email;

        @Basic
        @Column (length = 10)
        private String password;

        @Basic
        @Column (length = 10)
        private String login;

        @Basic
        private Region region;

        @ManyToOne
        @JoinColumn (name = "role_id",  referencedColumnName="id"  )
        private Role role;

        @OneToMany(cascade = CascadeType.ALL)
        @JoinTable (name = "user_orders")
        private Set<Order> ordersSet;

         public enum Region {North, South, West, East};

        // геттеры и сеттеры
        public Set<Order> getOrdersSet() {
            return ordersSet;
        }

        public void setOrdersSet(Set<Order> ordersSet) {
            this.ordersSet = ordersSet;
        }

         public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;

        }

        public Region getRegion() {
            return region;
        }

        public void setRegion(Region region) {
            this.region = region;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String name) {
            this.firstName = name;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
