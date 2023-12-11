package project.model;

/**
 * Lead Author(s):
 * @author Jordan Spencer
 * @author Nicholas Moffat
 * 
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 * Retrieved from https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * 
 *  
 * Version/date: 12/10/2023
 * 
 * Responsibilities of class: contains methods to toggle god-like statistics for an organism
 * 
 */

public interface GodMode 
{
	// lots of health
	void infiniteHealth();
	// lots of strength
	void onePunchMan();
	// regular health
	void normalHealth();
	// regular strength
	void normalDamage();
}
