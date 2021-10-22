package com.rounindiary.RouninDiary.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.rounindiary.RouninDiary.service.IndexService;

@RunWith(SpringRunner.class)
class IndexControllerTest extends IndexController {

	@MockBean
	IndexService indexServiceMock;

	private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new IndexController())
        		.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
        		.build();
    }

    @Test
    void GETでアクセスする() throws Exception {
    	Pageable pageable = PageRequest.of(0, 5);
//    	when(indexServiceMock.findAll(pageable)).thenReturn();
//


        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("index"));
    }

    @Test
    void POSTでアクセスする() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/"))
                .andExpect(status().isMethodNotAllowed());
    }

}
