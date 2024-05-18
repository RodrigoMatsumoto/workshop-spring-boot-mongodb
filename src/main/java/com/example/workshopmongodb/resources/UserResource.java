package com.example.workshopmongodb.resources;

import com.example.workshopmongodb.domain.User;
import com.example.workshopmongodb.dto.UserDTO;
import com.example.workshopmongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/users")
public class UserResource {

  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<List<UserDTO>> findAll() {
    List<User> userList = userService.findAll();
    List<UserDTO> userDTOList = userList.stream().map(UserDTO::new).collect(Collectors.toList());

    return ResponseEntity.ok().body(userDTOList);
  }
}