package com.company.timelineservice.client;

import com.company.timelineservice.model.dto.response.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

@FeignClient(name = "USER-SERVICE")
public interface UserClient {

    @RequestMapping(method = RequestMethod.POST, value = "/users/get-users-by-id")
    Set<UserResponseDTO> getUsersById(@RequestBody Set<Long> usersId);
}
