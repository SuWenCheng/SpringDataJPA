package com.alwin.jpa.example;

import com.alwin.jpa.example.bean.User;
import com.alwin.jpa.example.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Test
    public void testSaveUser() {
        User user = userRepository.save(User.builder().name("jackxx").email("123456@126.com").build());
        Assert.assertNotNull(user);
        List<User> users= userRepository.findAll();
        System.out.println(users);
        Assert.assertNotNull(users);
    }

    @Test
    public void testFindByEmail() {
        User byEmail = userRepository.findByEmail("a745884449@qq.com");
        Assert.assertNotNull(byEmail);
        log.info(byEmail.getName());
    }

    @Test
    public void testPage() {
        //查询user里面的lastname=jk的第一页，每页大小是20条；并会返回一共有多少页的信息
        Page<User> users = userRepository.findByLastname("jk", PageRequest.of(1, 20));

        //查询user里面的lastname=jk的第一页的20条数据，不知道一共多少条
        Slice<User> users1 = userRepository.findByLastname("jk",PageRequest.of(1, 20));

        //查询出来所有的user里面的lastname=jk的User数据，并按照name正序返回List
        List<User> users2 = userRepository.findByLastname("jk",Sort.by(Sort.Direction.ASC, "name"));

        //按照createdAt倒序，查询前一百条User数据
        //List<User> users3 = userRepository.findByLastname("jk",PageRequest.of(0, 100, Sort.Direction.DESC, "createdAt"));

    }
}
