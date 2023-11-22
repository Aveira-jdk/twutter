package com.company.userservice.service;

import com.company.userservice.model.dto.request.SignUpRequestDto;
import com.company.userservice.model.dto.request.UserRequestDto;
import com.company.userservice.model.entity.Account;
import com.company.userservice.model.entity.User;
import com.company.userservice.model.mapper.UserMapper;
import com.company.userservice.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AccountService accountService;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, AccountService accountService, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.accountService = accountService;
        this.userMapper = userMapper;
    }

    public List<User> getAllUsers(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        List<User> users = userRepository.findAll(paging).getContent();
        return users;
    }


    @Transactional(rollbackFor = RuntimeException.class)
    public void add(SignUpRequestDto signUpRequestDto) {
        Account account = accountService.add(signUpRequestDto);
        User user = userMapper.signUpRequestDtoToUser(signUpRequestDto);
        user.setAccount(account);
        userRepository.save(user);
    }

    public User update(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id).get();
        if (userRequestDto.getFirstName() != null)
            user.setFirstName(userRequestDto.getFirstName());
        if (userRequestDto.getLastName() != null)
            user.setLastName(userRequestDto.getLastName());
        if (userRequestDto.getEmail() != null)
            user.setEmail(userRequestDto.getEmail());
        if (userRequestDto.getContactNumber() != null)
            user.setContactNumber(userRequestDto.getContactNumber());
        return userRepository.save(user);
    }

    public void delete(Long userId) {
        User user = getUserById(userId);
        Account account = user.getAccount();
        user.setAccount(null);
        userRepository.delete(user);
        accountService.delete(account);
    }

    public User getById(Long id) {
        return userRepository.findById(id).get();
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByAccount_Username(username);
    }

    public Set<User> getUsersById(Set<Long> userIds) {
        Set<User> users = new HashSet<>();
        userIds.forEach(id -> users.add(userRepository.getUserById(id)));
        return users;
    }

    public User getUserById(Long userId) {
        return userRepository.getUserById(userId);
    }
}
