package it.proactivity.proactivityairway;

import it.proactivity.proactivityairway.model.Employee;
import it.proactivity.proactivityairway.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProactivityairwayApplicationTests {

	@Autowired
	EmployeeRepository employeeRepository;
	@Test
	void contextLoads() {
	}

	@Test
	void findCdaEmployeeTest() {
		List<Employee> employeeList = employeeRepository.findCdaEmployee("C.D.A");
		employeeList.stream().forEach(System.out::println);
	}
}
