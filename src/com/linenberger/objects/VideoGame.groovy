package com.linenberger.objects;

public class VideoGame {
	private String title
	private String platform
	private int yearReleased
	
	VideoGame(title, platform, yearReleased) {
		this.title = title
		this.platform = platform
		this.yearReleased = yearReleased
	}
	
	def getTitle() {
		return title;
	}	
	
	def getYearReleased() {
		return yearReleased
	}
	
	def getPlatform() {
		return platform
	}
}
