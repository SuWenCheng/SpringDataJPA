package com.alwin.jpa.example.repository;

import com.alwin.jpa.example.bean.User;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

@NoRepositoryBean
interface MyBaseRepository<T, ID extends Serializable> extends Repository<T, ID> {

    T findOne(ID id);

    T save(T entity);

}

interface MyUserRepository extends MyBaseRepository<User, Long> {
    User findByEmailAddress(String emailAddress);

}

