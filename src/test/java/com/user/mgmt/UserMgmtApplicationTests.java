package com.user.mgmt;


import java.util.List;
import java.util.Arrays;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.test.mock.mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.;


import org.springframework.web.context.WebApplicationContext;

import com.user.mgmt.model.User;
import com.user.mgmt.service.UserService;
import com.user.mgmt.repository.UserRepository;
import com.user.mgmt.reader.UserCSVReader;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class UserMgmtApplicationTests {

	@LocalServerPort
    int randomServerPort;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private InputStream is;
    private MockMvc mockMvc;
	

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;
    
	@Test
	public void whenFileUploaded_thenVerifyStatus() 
	  throws Exception {
	    MockMultipartFile file 
	      = new MockMultipartFile(
	        "file", 
	        "user.csv", 
	        MediaType.TEXT_PLAIN_VALUE, 
	        "".getBytes()
	      );

	    MockMvc mockMvc 
	      = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	    mockMvc.perform(multipart("/upload").file(file))
	      .andExpect(status().isOk());
	}
	
	@Test
    public void testUploadFile() throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "excel.xlsx", "multipart/form-data", is);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.multipart("/upload").file(mockMultipartFile).contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().is(200)).andReturn();
        Assert.assertEquals(200, result.getResponse().getStatus());
        Assert.assertNotNull(result.getResponse().getContentAsString());
        Assert.assertEquals("excel.xlsx", result.getResponse().getContentAsString());
    }
//	@Test
//	void contextLoads() {
//	}
	
	  @Test
	    public void findAllUsers() {
		  User user1 = new User("emp1","emp1","Marie",8000.0,UserCSVReader.getValidStartDate("2020-01-01"));
			User user2 = new User("emp2","emp2","Satya",6000.0,UserCSVReader.getValidStartDate("2020-06-01"));
			List<User> list = Arrays.asList(user1,user2);
		    userRepository.saveAll(list);
	        assertNotNull(userRepository.findAll());
	    }
	
	@Test
	public void SaveAllUserTest() {
		
	 
	    List<User> list = userRepository.findById("emp1");
	   
        assertEquals("emp1", list.get(0).getLogin());
	 }
}
