package com.luxoft.blogApp.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luxoft.blogApp.entity.Post;
import com.luxoft.blogApp.service.DefaultService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DefaultService defaultService;
    @Autowired
    private ObjectMapper objectMapper;



    @Test
    void postSaved() throws Exception {
        Post post = Post.builder()
                .title("fashion")
                .content("chanel spring couture collection")
                .build();

        when(defaultService.save(any())).thenReturn(post);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"fashion\", \"content\": \"chanel spring couture collection\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("fashion"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("chanel spring couture collection"))
                .andDo(MockMvcResultHandlers.print());
        verify(defaultService).save(any(Post.class));
    }

    @Test
    void sortedPostsByTitle() throws Exception {
        Post post = Post.builder()
                .id(1L)
                .title("fashion")
                .content("chanel spring couture collection")
                .build();
        Post nextPost = Post.builder()
                .id(2L)
                .title("music")
                .content("cha-cha-cha")
                .build();


        when(defaultService.findAllWithSort("title")).thenReturn(List.of(post, nextPost));
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/posts?sort=title", "fashion"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("fashion"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("music"))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    void postDeleted() throws Exception {
        Post post = Post.builder()
                .id(1L)
                .title("fashion")
                .content("chanel spring couture collection")
                .build();
        when(defaultService.getById(post.getId())).thenReturn(post);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/posts/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void postUpdated() throws Exception {
        Post post = Post.builder()
                .id(1L)
                .title("fashion")
                .content("chanel spring couture collection")
                .build();
        when(defaultService.update(post)).thenReturn(post);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/posts/1")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(post)))
                .andExpect(status().isOk());
        verify(defaultService, times(1)).update( post);

    }

    @Test
    public void methodMarkedByStarIsCorrect() throws Exception {
        Post post = Post.builder()
                .id(1L)
                .title("fashion")
                .content("chanel spring couture collection")
                .star(true)
                .build();
        Post nextPost = Post.builder()
                .id(2L)
                .title("music")
                .content("cha-cha-cha")
                .star(true)
                .build();
        when(defaultService.returnMarkedByStar()).thenReturn(List.of(post, nextPost));

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/posts/star"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].star").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].star").isBoolean())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void checkMethodMarkPostByStarCorrect() throws Exception {
        Post post = Post.builder()
                .id(1L)
                .title("fashion")
                .content("chanel spring couture collection")
                .star(true)
                .build();
        when(defaultService.markedByStar(1L)).thenReturn(post);
        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/posts/{id}/star", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.star").value(true));
    }

    @Test
    public void checkMethodDeleteStarFromPostById() throws Exception {
        Post post = Post.builder()
                .id(1L)
                .title("fashion")
                .content("chanel spring couture collection")
                .star(false)
                .build();
        when(defaultService.unmarkedByStar(1L)).thenReturn(post);
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/posts/{id}/star", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.star").value(false));
    }
}