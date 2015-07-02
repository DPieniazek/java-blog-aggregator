package cz.jiripinkas.jba.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.jiripinkas.jba.entity.Feed;
import cz.jiripinkas.jba.repository.FeedRepository;

@Service
public class FeedService {

	@Autowired
	FeedRepository feedRepository;
	
	public List<Feed> getItems() {
		List<Feed> temp = feedRepository.findAll();
		List<Feed> currentFeeds = new ArrayList<Feed>();
		for(Feed f: temp)
		{
			if(f.isCurrent())
			{
				currentFeeds.add(f);
			}
		}
		return currentFeeds;
	}
	
	public Feed findOne(int id)
	{
		return feedRepository.findOne(id);
	}
	
	public void delete(int id)
	{
		feedRepository.delete(id);
	}
}
