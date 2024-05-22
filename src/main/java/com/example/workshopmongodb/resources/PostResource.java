package com.example.workshopmongodb.resources;

import com.example.workshopmongodb.domain.Post;
import com.example.workshopmongodb.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/posts")
public class PostResource {

  @Autowired
  private PostService postService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<Post> findById(@PathVariable String id) {
    Post post = postService.findById(id);

    return ResponseEntity.ok().body(post);
  }
}