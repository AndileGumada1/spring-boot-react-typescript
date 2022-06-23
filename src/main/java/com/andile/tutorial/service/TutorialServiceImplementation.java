package com.andile.tutorial.service;

import com.andile.tutorial.model.Tutorial;
import com.andile.tutorial.repository.TutorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class TutorialServiceImplementation implements TutorialService {

    private final TutorialRepository tutorialRepository;

    @Override
    public Tutorial create(Tutorial tutorial) {
        Tutorial _tutorial = tutorialRepository
                .save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
        return _tutorial;
    }

    @Override
    public List<Tutorial> list(String title) {
        List<Tutorial> tutorials = new ArrayList<Tutorial>();
        if (title == null)
            tutorialRepository.findAll().forEach(tutorials::add);
        else
            tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);
        if (tutorials.isEmpty()) {
            throw new TutorialNotFoundException("Tutorials are not found");
        }
        return tutorials;
    }

    @Override
    public Tutorial findById(long id) {
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
        if (tutorialData.isPresent()) {
            return tutorialData.get();
        }else {
            throw new TutorialNotFoundException("Tutorial not found with id :"+id);
        }
    }

    @Override
    public void deleteById(long id) {
       tutorialRepository.deleteById(id);
    }

    @Override
    public List<Tutorial> findByPublished() {
        try {
            List<Tutorial> tutorials = tutorialRepository.findByPublished(true);
            if (tutorials.isEmpty()) {
                return tutorials;
            }
            throw new TutorialNotFoundException("");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Tutorial update(long id,Tutorial tutorial) {
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
        if (tutorialData.isPresent()) {
            Tutorial _tutorial = tutorialData.get();
            _tutorial.setTitle(tutorial.getTitle());
            _tutorial.setDescription(tutorial.getDescription());
            _tutorial.setPublished(tutorial.isPublished());
            return tutorialRepository.save(_tutorial);
        } else {
            throw  new TutorialNotFoundException("Tutorial not found with "+ id);
        }
    }

    @Override
    public void deleteAll() {
        tutorialRepository.deleteAll();
    }


    /**
     *@see java.lang.Exception
     * @see java.lang.RuntimeException
     */
    private class TutorialNotFoundException extends RuntimeException {
        public TutorialNotFoundException(String s) {
            super(s);
        }
    }
}
