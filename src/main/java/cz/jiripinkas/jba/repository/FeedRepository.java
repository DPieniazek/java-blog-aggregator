package cz.jiripinkas.jba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.jiripinkas.jba.entity.Feed;

public interface FeedRepository extends JpaRepository<Feed, Integer> {

	Feed findByTitle(String title);
}
