package com.berkaycumbur.pribastask.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.berkaycumbur.pribastask.model.Timeline;

public interface TimelineRepo extends MongoRepository<Timeline, Integer> {
    List<Timeline> findByTagsContaining(String tag);//For searhing for tag
}
