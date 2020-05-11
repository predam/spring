package com.example.demo;

import com.example.demo.entity.Teacher;
import com.example.demo.entity.Training;
import com.example.demo.repo.TeacherRepo;
import com.example.demo.repo.TrainingRepo;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

@Service
public class DummyData {

	@Autowired
	private TrainingRepo trainingRepo;

	@Autowired
	private TeacherRepo teacherRepo;


	@EventListener
	public void initMockData(ContextRefreshedEvent event) {
		System.out.println("Inserting dummy data");
		Teacher t1 = new Teacher("Victor");
		Teacher t2 = new Teacher("Ionut");
		teacherRepo.save(t1);
		teacherRepo.save(t2);


		Training c1 = new Training("Spring Framework", "All about Spring", LocalDate.now(), t1);
		Training c2 = new Training("JPA", "The coolest standard in Java EE", LocalDate.now(), t1);
		Training c3 = new Training("Java Basic", "The new way of doing Single Page Applications", LocalDate.now(), t2);
		Training c4 = new Training("DesignPatterns", "Design Thinking", LocalDate.now(), t1);
		trainingRepo.save(c1);
		trainingRepo.save(c2);
		trainingRepo.save(c3);
		trainingRepo.save(c4);
	}
	
	
}