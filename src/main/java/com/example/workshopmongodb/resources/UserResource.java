package com.example.workshopmongodb.resources;

import com.example.workshopmongodb.domain.User;
import com.example.workshopmongodb.dto.UserDTO;
import com.example.workshopmongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
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

  @GetMapping(value = "/{id}")
  public ResponseEntity<UserDTO> findById(@PathVariable String id) {
    Optional<User> user = userService.findById(id);

    return ResponseEntity.ok().body(new UserDTO(user));
  }

  @PostMapping
  public ResponseEntity<Void> insert(@RequestBody UserDTO userDTO) {
    User user = userService.insert(userService.fromDTO(userDTO));
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

    return ResponseEntity.created(uri).build();
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    userService.delete(id);

    return ResponseEntity.noContent().build();
  }
}