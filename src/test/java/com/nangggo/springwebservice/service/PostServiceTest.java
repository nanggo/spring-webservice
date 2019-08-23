package com.nangggo.springwebservice.service;

import com.nangggo.springwebservice.domain.posts.Posts;
import com.nangggo.springwebservice.domain.posts.PostsRepository;
import com.nangggo.springwebservice.dto.PostsMainResponseDto;
import com.nangggo.springwebservice.dto.posts.PostsSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void Dto데이터가_posts테이블에_저장된다() {
        //given
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .author("yamsiri@gmail.com")
                .content("테스트")
                .title("테스트 타이틀")
                .build();

        //when
        postsService.save(dto);

        //then
        Posts posts = postsRepository.findAll().get(0);
        assertThat(posts.getAuthor()).isEqualTo(dto.getAuthor());
        assertThat(posts.getContent()).isEqualTo(dto.getContent());
        assertThat(posts.getTitle()).isEqualTo(dto.getTitle());
    }

    @Test
    public void 초기_저장된_데이터_확인() {
        //given
        //when
        PostsMainResponseDto response = postsService.findAllDesc().get(0);

        //then
//        assertThat(response.getTitle()).is("테스트2");
        assertThat(response.getAuthor()).isEqualTo("test2@gmail.com");
        assertThat(response.getTitle()).isEqualTo("테스트2");
        assertThat(response.getId().toString()).isEqualTo("2");
    }
}
