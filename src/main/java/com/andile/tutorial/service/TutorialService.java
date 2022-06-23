package com.andile.tutorial.service;

import com.andile.tutorial.model.Tutorial;

import java.util.List;

public interface TutorialService {
  Tutorial create(Tutorial tutorial);
  List<Tutorial> list(String title);
  Tutorial findById(long id);
  void deleteById(long id);
  List<Tutorial> findByPublished();
  Tutorial update(long id,Tutorial tutorial);
  void deleteAll();
}
