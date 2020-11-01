package com.SpringJwt.controller;

import com.SpringJwt.exception.ResourceNotFoundException;
import com.SpringJwt.models.Course;
import com.SpringJwt.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    @GetMapping("/{id}")
    public List<Course> getCoursesByInstructor(@PathVariable("id") Long instructorId){
        return courseRepository.findByInstructorId(instructorId);
    }
    @PostMapping("/{id}")
    public Course createCourse(@PathVariable("id") Long instructorId,@Valid @RequestBody Course course){
        return courseRepository.findById(instructorId).map(instructor->{
           course.setInstructor(instructor.getInstructor());
           return courseRepository.save(course);
        }).orElseThrow(()->new ResourceNotFoundException("instructor not found"));
    }
    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable("id") Long instructorId,@Valid @RequestBody Course course){
        if(!courseRepository.existsById(instructorId)){
            throw new ResourceNotFoundException("instructorid not found");
        }
        return courseRepository.findById(instructorId).map(course1->{
            course1.setTitle(course.getTitle());
            return courseRepository.save(course1);
        }).orElseThrow(()->new ResourceNotFoundException("course id not found"));
    }

    @DeleteMapping("/{instructorId}/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable(value = "instructorId") Long instructorId,@PathVariable(value = "courseId") Long courseId){

        return courseRepository.findByIdAndInstructorId(instructorId,courseId).map(course -> {
            courseRepository.delete(course);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("course not found with"+courseId+" and instructorId"+instructorId));

    }
}
