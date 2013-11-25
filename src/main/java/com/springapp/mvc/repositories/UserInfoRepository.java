package com.springapp.mvc.repositories;

import com.springapp.mvc.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: WinJavaEnv
 * Date: 25.11.13
 * Time: 15:29
 * To change this template use File | Settings | File Templates.
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
