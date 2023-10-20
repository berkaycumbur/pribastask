package com.berkaycumbur.pribastask.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.berkaycumbur.pribastask.model.Timeline;

import com.berkaycumbur.pribastask.repository.TimelineRepo;
import com.berkaycumbur.pribastask.service.SequenceGeneratorService;

@RestController // For APIs

public class TimelineController {

    @Autowired // Injecting Service
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired // Injecting Repo Interface
    private TimelineRepo repo;

    @PostMapping("/addtimeline")
    public String saveTimeline(@RequestBody Timeline timeline) {
        timeline.setId(sequenceGeneratorService.generateSequence(Timeline.SEQUENCE_NAME));// Setting ID from sequence
        repo.save(timeline);
        return "Added Successfully";
    }

    @PutMapping("/update/{id}")
    public String updateTimeline(@PathVariable long id, @RequestBody Timeline updatedTimeline) {
        Timeline existingTimeline = repo.findById((int) id).orElse(null);// Change from id because ID should be constant

        if (existingTimeline != null) {
            // Update the fields of the existing timeline with the new data
            existingTimeline.setTitle(updatedTimeline.getTitle());
            existingTimeline.setDescription(updatedTimeline.getDescription());
            existingTimeline.setTags(updatedTimeline.getTags());
            existingTimeline.setMoments(updatedTimeline.getMoments());
            // Update other fields as needed

            repo.save(existingTimeline);
            return "Updated Successfully";
        } else {
            return "Timeline not found";
        }
    }

    @GetMapping("/showall")
    public List<Timeline> getTimelines() {
        return repo.findAll(Sort.by(Sort.Order.asc("id"))); // Showing all timelines ordered by ID
    }

    @GetMapping("/findbytag")
    public List<Timeline> getTimelinesByTag(@RequestParam("tag") String tag) {
        // Use the TimelineRepo to find timelines with the specified tag
        /// findbytag?tag=deneme
        return repo.findByTagsContaining(tag);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTimeline(@PathVariable int id) {
        repo.deleteById(id);
        return "Deleted Successfully";
    }
}
