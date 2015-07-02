package cz.jiripinkas.jba.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cz.jiripinkas.jba.entity.Comment;
import cz.jiripinkas.jba.entity.Feed;
import cz.jiripinkas.jba.repository.CommentRepository;
import cz.jiripinkas.jba.repository.FeedRepository;
import cz.jiripinkas.jba.service.CommentService;
import cz.jiripinkas.jba.service.FeedService;
import cz.jiripinkas.jba.service.UserService;

@Controller
public class FeedsController {

	@Autowired
	FeedService feedService;
	
	@Autowired
	FeedRepository feedRepository;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/feeds")
	public String showFeeds(Model model){
		model.addAttribute("feeds", feedService.getItems());
		return "feeds";
	}
	
	@RequestMapping(value = "feeds/addFeed", method=RequestMethod.POST)
	public String addFeed(Model model,
			@RequestParam(value="ename") String name,
			@RequestParam(value="edescription") String description){
		Feed feed = new Feed();
		feed.setTitle(name);
		feed.setDescription(description);
		feedRepository.save(feed);
		return "redirect:/feeds.html";
	}
	
	@RequestMapping("feed/{id}")
	public String currentFeed(Model model, @PathVariable Integer id){
		Feed feed = feedService.findOne(id);
		model.addAttribute("feed", feed);
		List<Comment> list = commentService.getListByFeed(feed);
		model.addAttribute("comments", list);
		return "feed";
	}
	
	@RequestMapping(value = "feeds/editFeed", method=RequestMethod.POST)
	public String editFeed(Model model,
			@RequestParam(value="ename") String name,
			@RequestParam(value="edescription") String description,
			@RequestParam(value="id") Integer id){
		Feed feed = feedService.findOne(id);
		feed.setTitle(name);
		feed.setDescription(description);
		feedRepository.save(feed);
		return "redirect:/feeds.html";
	}
	
	@RequestMapping(value = "feeds/deleteFeed", method=RequestMethod.POST)
	public String deleteFeed(Model model,
			@RequestParam(value="id") Integer id){
		Feed feed = feedRepository.findOne(id);
		feed.setCurrent(false);
		feedRepository.save(feed);
		return "redirect:/feeds.html";
	}
	
	@RequestMapping(value = "feeds/comment", method=RequestMethod.POST)
	public String addComment(Model model,
			@RequestParam(value="id") Integer id,
			@RequestParam(value="comment") String comment,
			Principal principal){
		Comment c = new Comment();
		c.setComment(comment);
		c.setFeed(feedRepository.getOne(id));
		c.setUser(userService.findOne(principal.getName()));
		commentRepository.save(c);
		return "redirect:/feed/" + id.toString() + ".html";
	}
	
	@RequestMapping(value="feeds/editComment", method=RequestMethod.GET)
	public String currentComment(Model model,
			@RequestParam(value="id") Integer id){
		Comment comment = commentRepository.findOne(id);
		model.addAttribute("comment", comment);
		return "comment";
	}
	
	@RequestMapping(value="feeds/changeComment", method=RequestMethod.POST)
	public String editComment(Model model,
			@RequestParam(value="id") Integer id,
			@RequestParam(value="newComment") String newComment){
		Comment comment = commentRepository.findOne(id);
		comment.setComment(newComment);
		commentRepository.save(comment);
		return "redirect:/feed/" + comment.getFeed().getId().toString() + ".html";
	}
	
	@RequestMapping(value="feeds/deleteComment", method=RequestMethod.POST)
	public String deleteComment(Model model,
			@RequestParam(value="id") Integer id){
		Comment comment = commentRepository.findOne(id);
		String path = comment.getFeed().getId().toString();
		commentRepository.delete(comment);
		return "redirect:/feed/" + path + ".html";
	}
}
