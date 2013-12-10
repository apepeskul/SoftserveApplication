package com.springapp.mvc.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
public class User_ {
    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, String> login;
}

