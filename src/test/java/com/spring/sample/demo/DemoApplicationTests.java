package com.spring.sample.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.sample.demo.model.Employee;
import com.spring.sample.demo.repository.EmployeeRepository;
import com.spring.sample.demo.service.EmployeeService;

@RunWith(SpringRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private EmployeeService employeeService;



	@Autowired
    private WebApplicationContext context;

	@Autowired
	private ObjectMapper mapper;

	private MockMvc mockMvc;

	@BeforeEach
    public void setup() {
		mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }


	@WithMockUser("/sampleuser")
	@Test
	public void saveEmployee()  {

		try {
		Employee employee = new Employee(12, "TEST", 600000);
		String jsonRequest = mapper.writeValueAsString(employee);

		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.post("/emplyeemanagement/save").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		assertEquals(200, mvcResult.getResponse().getStatus());
		
		}catch (Exception e) {

			System.out.println(e.getMessage());
		}

	}

//	@Test
//	public void getEmployee() {
//
////		List<Employee> employees = employeeService.getAllEmployee();
//
//		Stream.of(new Employee(12, "Mayoora", 60000), new Employee(12, "Mayoora", 60000));
//
//		when(employeeRepository.findAll())
//				.thenReturn(Stream.of(new Employee(12, "Mayoora", 60000), new Employee(12, "Mayoora", 60000))
//						.collect(Collectors.toList()));
//
//		assertEquals(2, employeeService.getAllEmployee().size());
//
//	}

}
