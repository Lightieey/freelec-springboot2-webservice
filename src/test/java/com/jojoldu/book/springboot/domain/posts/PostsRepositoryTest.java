package com.jojoldu.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest                     // 별다른 설정이 없으면 H2 테이터베이스를 자동으로 실행해줌
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After                          // Junit에서 단위테스트가 끝날 때마다 수행되는 메소드를 지정
    public void cleanup() {         // 보통 배포 전 전체테스트를 수행할 때 테스트간 데이터 침범을 막기 위해 사용
        postsRepository.deleteAll();
    }

    @Test
    public void pull_savePost() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        // posts table에 insert/update 쿼리를 실행
        // id 값이 있다면 update가, 없다면 insert 쿼리가 실행됨
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("minjikim@gmail.com")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();  // posts table에 있는 모든 데이터를 조회해옴

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void register_BaseTimeEntity() {

        // given
        LocalDateTime now = LocalDateTime.of(2022, 4, 9, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>> createDate=" + posts.getCreatedDate() + ", modifiedDate=" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
