package com.example.workshopmongodb.dto;

import java.io.Serializable;
import java.util.Date;

public class CommentDTO implements Serializable {

  private static final Long serialVersionUID = 1L;

  private String text;
  private Date date;
  private AuthorDTO auhtor;

  public CommentDTO() {
  }

  public CommentDTO(String text, Date date, AuthorDTO auhtor) {
    this.text = text;
    this.date = date;
    this.auhtor = auhtor;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public AuthorDTO getAuhtor() {
    return auhtor;
  }

  public void setAuhtor(AuthorDTO auhtor) {
    this.auhtor = auhtor;
  }
}