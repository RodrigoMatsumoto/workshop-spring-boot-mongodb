package com.example.workshopmongodb.config;

import com.example.workshopmongodb.domain.Post;
import com.example.workshopmongodb.domain.User;
import com.example.workshopmongodb.dto.AuthorDTO;
import com.example.workshopmongodb.dto.CommentDTO;
import com.example.workshopmongodb.repository.PostRepository;
import com.example.workshopmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PostRepository postRepository;

  @Override
  public void run(String... args) throws Exception {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

    userRepository.deleteAll();
    postRepository.deleteAll();

    User maria = new User(null, "Maria Brown", "maria@gmail.com");
    User alex = new User(null, "Alex Green", "alex@gmail.com");
    User bob = new User(null, "Bob Grey", "bob@gmail.com");

    userRepository.saveAll(Arrays.asList(maria, alex, bob));

    Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
    Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));

    CommentDTO comment1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
    CommentDTO comment2 = new CommentDTO("Aproveite", sdf.parse("22/03/2018"), new AuthorDTO(bob));
    CommentDTO comment3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(alex));

    post1.getComments().addAll(Arrays.asList(comment1, comment2));
    post2.getComments().add(comment3);

    postRepository.saveAll(Arrays.asList(post1, post2));

    maria.getPosts().addAll(Arrays.asList(post1, post2));

    userRepository.save(maria);
  }
}