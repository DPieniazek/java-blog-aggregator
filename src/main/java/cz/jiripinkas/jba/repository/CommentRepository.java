package cz.jiripinkas.jba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.jiripinkas.jba.entity.Comment;
import cz.jiripinkas.jba.entity.Feed;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	List<Comment> findByFeed(Feed feed);

}
