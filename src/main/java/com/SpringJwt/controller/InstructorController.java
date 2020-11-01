package com.SpringJwt.controller;

import com.SpringJwt.exception.ResourceNotFoundException;
import com.SpringJwt.models.Instructor;
import com.SpringJwt.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

    @Autowired
    private InstructorRepository instructorRepository;

    @GetMapping
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable("id") Long instructorId) {
        Instructor response = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor no found" + instructorId));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public Instructor createInstructor(@Valid @RequestBody Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable("id") Long instructorId, @Valid @RequestBody Instructor instructor) throws ResourceNotFoundException {
        Instructor instruct = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found" + instructorId));
        instruct.setFirst_name(instructor.getFirst_name());
        instruct.setLast_name(instructor.getLast_name());
        instruct.setEmail(instructor.getEmail());
        Instructor updated = instructorRepository.save(instruct);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteInstructor(@PathVariable("id") Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found" + instructorId));
        instructorRepository.deleteById(instructorId);
        return HttpStatus.FORBIDDEN;
    }
}
