package com.example.workshopmongodb.services;

import com.example.workshopmongodb.domain.User;
import com.example.workshopmongodb.dto.UserDTO;
import com.example.workshopmongodb.repository.UserRepository;
import com.example.workshopmongodb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public Optional<User> findById(String id) {
    Optional<User> user = userRepository.findById(id);

    if (!userRepository.existsById(id)) {
      throw new ObjectNotFoundException("Object not found");
    }

    return user;
  }

  public User insert(User user) {
    return userRepository.insert(user);
  }

  public void delete(String id) {
    findById(id);
    userRepository.deleteById(id);
  }

  public User fromDTO(UserDTO userDTO) {
    return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
  }
}