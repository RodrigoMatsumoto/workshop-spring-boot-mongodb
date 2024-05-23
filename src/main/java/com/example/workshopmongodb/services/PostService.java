package com.example.workshopmongodb.services;

import com.example.workshopmongodb.domain.Post;
import com.example.workshopmongodb.repository.PostRepository;
import com.example.workshopmongodb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

  @Autowired
  private PostRepository postRepository;

  public Post findById(String id) {
    Optional<Post> post = postRepository.findById(id);

    if (!postRepository.existsById(id)) {
      throw new ObjectNotFoundException("Object not found");
    }

    return post.get();
  }

  public List<Post> findByTitle(String text) {
    return postRepository.searchTitle(text);
  }

  public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
    return postRepository.fullSearch(text, minDate, new Date(maxDate.getTime() + 24 * 60 * 60 * 1000));
  }
}