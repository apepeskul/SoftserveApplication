package com.springapp.entity;

import com.springapp.entity.User;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 11.11.13
 * Time: 14:52
 * To change this template use File | Settings | File Templates.
 */
    @StaticMetamodel(User.class)
    public class User_ {
        public static volatile SingularAttribute<User, String> lastName;
        public static volatile SingularAttribute<User, String> login;
    }

