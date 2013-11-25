package com.springapp.mvc.model;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserSpecs {
    public static Specification<User> loginIsLike( final String searchTerm) {
        return new Specification<User>() {
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                String likePattern = getLikePattern(searchTerm);
                return builder.like(builder.lower(root.get(User_.login)), likePattern);
            }
          private String getLikePattern (final String searchTerm){
              StringBuilder pattern = new StringBuilder();
              pattern.append(searchTerm.toLowerCase());
              pattern.append("%");
              return pattern.toString();
          }
        };
    }



}
