package com.onpassive.onet.controller;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.onpassive.onet.repository.PostRepository;
import com.onpassive.onet.service.PostStorageService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PostController.class)
public class PostControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	PostStorageService postStorageService;
	
	@MockBean
	PostRepository repository;

	@Test
	public void getAllPosts() throws Exception {
		String type = "A";
		MockHttpServletRequestBuilder getReq = MockMvcRequestBuilders.get("/file/getAllPosts/"+type+"'");
		MvcResult result = mockMvc.perform(getReq).andReturn();
		MockHttpServletResponse response = result.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);
	}
	
	
//	
//	  @Test public void testUploadFile() throws Exception { MockMultipartFile
//	  mockMultipartFile = new MockMultipartFile("file", "jhonwick.jpg",
//	  "multipart/form-data", is); MvcResult result =
//	  mockMvc.perform(MockMvcRequestBuilders.multipart("/file/uploadFile").file(
//	  mockMultipartFile).contentType(MediaType.MULTIPART_FORM_DATA))
//	  .andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
//	  Assert.assertEquals(200, result.getResponse().getStatus());
//	  Assert.assertNotNull(result.getResponse().getContentAsString());
//	  Assert.assertEquals("jhonwick.jpg",
//	  result.getResponse().getContentAsString()); }
//	  
//	  @Test
//	  public void uploadFileTest() throws Exception{
//	      //given
//	      InputStream uploadStream = PostControllerTest.class.getClassLoader().getResourceAsStream("jhonwick.jpg");
//	      MockMultipartFile file = new MockMultipartFile("file", uploadStream);
//	      assert uploadStream != null;
//
//	      //when
//	      this.mockMvc.perform(fileUpload("/DefectImport")
//	              .file(file))
//	      //then
//	              .andExpect(status().isOk());
//	  }
	 
	 
	
	
}
