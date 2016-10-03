package io.bigpanda.exercise.service;

import java.util.Set;

import io.bigpanda.exercise.model.EventStats;
import io.bigpanda.exercise.model.WordStats;

public interface IStatsService {

	EventStats getEventStats(String eventType);
	Set<EventStats> getAllEventsStats();
	EventStats getEventStats(String eventType, String word);
	WordStats getWordStats(String word);
	Set<WordStats> getAllWordStats();
}