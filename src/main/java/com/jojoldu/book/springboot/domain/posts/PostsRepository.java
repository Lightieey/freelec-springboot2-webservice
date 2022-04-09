package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// <Entity Class, PK Type>
public interface PostsRepository extends JpaRepository<Posts, Long>{

}
