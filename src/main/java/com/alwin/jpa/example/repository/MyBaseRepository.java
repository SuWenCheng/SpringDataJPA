/*
package com.alwin.jpa.example.repository;

import com.alwin.jpa.example.bean.User;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

*/
/**
 * 选择性地暴露 CRUD 方法
 *//*

//@NoRepositoryBean
interface MyBaseRepository<T, ID extends Serializable> extends Repository<T, ID> {

    T findOne(ID id);

    T save(T entity);

}

interface MyUserRepository extends MyBaseRepository<User, Long> {
    User findByEmailAddress(String emailAddress);

}

*/
