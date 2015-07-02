package cz.jiripinkas.jba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.jiripinkas.jba.entity.Comment;
import cz.jiripinkas.jba.entity.Feed;
import cz.jiripinkas.jba.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	CommentRepository commentRepository;
	
	public List<Comment> getListByFeed(Feed f)
	{
		return commentRepository.findByFeed(f);
	}
}
