package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import model.Grade;
import model.Student;

public class aaaa extends Classs {

	public static void main(String[] args) {
		new aaaa().task();
	}

	@Override
	protected void task() {
		
		Student stu1= new Student();
		Student stu2= new Student();
		stu1.setAge(10);
		stu1.setStuName("小米");
		stu2.setAge(11);
		stu2.setStuName("小明");
		
		List<Student> students = new ArrayList<Student>();
		students.add(stu1);
		students.add(stu2);
		
		Grade grade = new Grade();
		grade.setName("一班");
		grade.setStudents(students);
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			String string = mapper.writeValueAsString(grade);
			System.out.println(string);
			System.out.println(string);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
