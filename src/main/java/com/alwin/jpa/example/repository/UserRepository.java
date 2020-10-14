package com.alwin.jpa.example.repository;

import com.alwin.jpa.example.bean.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    /**
     * 根据分页参数查询User，返回一个带分页结果的Page
     */
    Page<User> findByLastname(String lastname, Pageable pageable);

    /**
     * 根据分页参数返回一个Slice的user结果
     */
    //Slice<User> findByLastname(String lastname, Pageable pageable);

    /**
     * 根据排序结果返回一个List
     */
    List<User> findByLastname(String lastname, Sort sort);

    /**
     * 根据分页参数返回一个List对象
     */
    //List<User> findByLastname(String lastname, Pageable pageable);

    // first and top
    User findFirstByOrderByLastnameAsc();

    User findTopByOrderByAgeDesc();

    List<User> findDistinctUserTop3ByLastname(String lastname, Pageable pageable);

    List<User> findFirst10ByLastname(String lastname, Sort sort);

    List<User> findTop10ByLastname(String lastname, Pageable pageable);


}
