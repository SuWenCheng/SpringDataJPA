package com.alwin.jpa.example;

import com.alwin.jpa.example.dto.UserOnlyName;
import com.alwin.jpa.example.dto.UserOnlyNameEmailDto;
import com.alwin.jpa.example.entity.User;
import com.alwin.jpa.example.repository.UserRepository;
import com.alwin.jpa.example.util.JsonHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
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
import java.util.stream.Stream;

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

        // 按照createdAt倒序，查询前一百条User数据
        //List<User> users3 = userRepository.findByLastname("jk",PageRequest.of(0, 100, Sort.Direction.DESC, "createdAt"));
    }

    @Test
    public void testSaveUser2() throws JsonProcessingException {
        // 新增7条数据方便测试分页结果
        userRepository.save(User.builder().name("jack1").email("123456@126.com").sex("man").address("shanghai").build());
        userRepository.save(User.builder().name("jack2").email("123456@126.com").sex("man").address("shanghai").build());
        userRepository.save(User.builder().name("jack3").email("123456@126.com").sex("man").address("shanghai").build());
        userRepository.save(User.builder().name("jack4").email("123456@126.com").sex("man").address("shanghai").build());
        userRepository.save(User.builder().name("jack5").email("123456@126.com").sex("man").address("shanghai").build());
        userRepository.save(User.builder().name("jack6").email("123456@126.com").sex("man").address("shanghai").build());
        userRepository.save(User.builder().name("jack7").email("123456@126.com").sex("man").address("shanghai").build());
        // 利用ObjectMapper将我们的返回结果Json to String
        ObjectMapper objectMapper = new ObjectMapper();
        // 返回Stream类型结果（1）
        Stream<User> userStream = userRepository.findAllByCustomQueryAndStream(PageRequest.of(1,3));
        userStream.forEach(System.out::println);
        // 返回分页数据（2）
        Page<User> userPage = userRepository.findAll(PageRequest.of(0,3));
        System.out.println(JsonHelper.toJson(userPage));
        // 返回Slice结果（3）
        Slice<User> userSlice = userRepository.findAllByCustomQueryAndSlice(PageRequest.of(0,3));
        System.out.println(objectMapper.writeValueAsString(userSlice));
        // 返回List结果（4）
        List<User> userList = userRepository.findAllById(Lists.newArrayList(1L,2L));
        System.out.println(objectMapper.writeValueAsString(userList));


    }
    @Test

    public void testProjections() {
        userRepository.save(User.builder()
                .id(1L)
                .name("jack12")
                .email("123456@126.com")
                .sex("man")
                .address("shanghai")
                .build());
        UserOnlyNameEmailDto userOnlyNameEmailDto =  userRepository.findDtoByEmail("123456@126.com");
        log.info(userOnlyNameEmailDto.toString());
    }

    /**
     * 接口方式返回DTO
     */
    @Test
    public void testProjections2() {
        userRepository.save(User.builder()
                .name("jack12")
                .email("123456@126.com")
                .sex("man")
                .address("shanghai")
                .build());
        UserOnlyName userOnlyName = userRepository.findByAddress("shanghai");
        log.info("---------------result--------------------");
        log.info(userOnlyName.toString());
    }

}
