package com.alwin.jpa.example.repository;

import com.alwin.jpa.example.dto.UserOnlyName;
import com.alwin.jpa.example.dto.UserOnlyNameEmailDto;
import com.alwin.jpa.example.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.stream.Stream;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    /**
     * 根据分页参数查询User，返回一个带分页结果的Page
     */
    Page<User> findByLastname(String lastName, Pageable pageable);

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

    /**
     * 自定义一个查询方法，返回Stream对象，并且有分页属性
     */
    @Query("select u from User u")
    Stream<User> findAllByCustomQueryAndStream(Pageable pageable);

    /**
     * 测试Slice的返回结果
     */
    @Query("select u from User u")
    Slice<User> findAllByCustomQueryAndSlice(Pageable pageable);

    /**
     * 测试只返回name和email的DTO
     */
    //@Query(value = "select u.name, u.email from User u where u.email = ?1")
    UserOnlyNameEmailDto findDtoByEmail(String email);

    /**
     * 接口的方式返回DTO
     */
    UserOnlyName findByAddress(String address);

}
