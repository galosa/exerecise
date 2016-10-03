/**
 * 
 */
package io.bigpanda.exercise.rest;

import java.util.Set;

import io.bigpanda.exercise.model.EventStats;
import io.bigpanda.exercise.model.WordStats;
import io.bigpanda.exercise.service.IStatsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gal.a
 *
 */
@RestController
@RequestMapping("/stats")
public class StatsController {

	@Autowired
	private IStatsService statsService; 
	
	@RequestMapping(path="/events/{eventType}")
	public EventStats getEvnetStats(@PathVariable("eventType") String eventType){
		return statsService.getEventStats(eventType);
	}
	
	@RequestMapping(path="/events/{eventType}/{word}")
	public EventStats getEvnetStats(@PathVariable("eventType") String eventType, @PathVariable("word") String word){
		return statsService.getEventStats(eventType, word);
	}
	
	@RequestMapping(path="/words/{word}")
	public WordStats getWordStats(@PathVariable("word") String word){
		return statsService.getWordStats(word);
	}
	
	@RequestMapping(path="/events")
	public Set<EventStats> getAllEvnetsStats(){
		return statsService.getAllEventsStats();
	}
	
	@RequestMapping(path="/words")
	public Set<WordStats> getAllWordsStats(){
		return statsService.getAllWordStats();
	}
	
	
	
	
}
