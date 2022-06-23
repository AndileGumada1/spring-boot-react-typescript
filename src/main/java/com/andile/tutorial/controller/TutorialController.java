package com.andile.tutorial.controller;

import com.andile.tutorial.model.Tutorial;
import com.andile.tutorial.service.TutorialServiceImplementation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tutorials")
@RequiredArgsConstructor

public class TutorialController {
       private final TutorialServiceImplementation serviceImplementation;
        private final ObjectMapper mapper;
    @PostMapping("/save")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) throws JsonProcessingException {
        System.out.println(mapper.writeValueAsString(tutorial));
        Tutorial response = serviceImplementation.create(tutorial);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    @GetMapping("/list")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) throws JsonProcessingException {
        System.out.println(mapper.writeValueAsString(title));
        List<Tutorial> tutorials =serviceImplementation.list(title);
        return new ResponseEntity<>(tutorials,HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) throws JsonProcessingException {
        System.out.println(mapper.writeValueAsString("Getting Tutorial with :"+id));
        Tutorial tutorial = serviceImplementation.findById(id);
        return new ResponseEntity<>(tutorial,HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) throws JsonProcessingException {
        System.out.println(mapper.writeValueAsString("Updating tutorial with id"+tutorial.getId()));
        Tutorial update = serviceImplementation.update(id, tutorial);
        return new ResponseEntity<>(update,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteTutorial(@PathVariable("id") long id) throws JsonProcessingException {
        System.out.println(mapper.writeValueAsString("deleteTutorial with :"+id));
        serviceImplementation.deleteById(id);
    }
//    @DeleteMapping("/tutorials")
//    public ResponseEntity<Void> deleteAllTutorials() {
//        return new ResponseEntity<>serviceImplementation.deleteAll();
//    }
    @GetMapping("/published")
    public ResponseEntity<List<Tutorial>> findByPublished() throws JsonProcessingException {
        List<Tutorial> tutorialList = serviceImplementation.findByPublished();
        System.out.println(mapper.writeValueAsString("Getting tutorials : "+tutorialList));
        return new ResponseEntity<>(tutorialList,HttpStatus.OK);
    }
}
