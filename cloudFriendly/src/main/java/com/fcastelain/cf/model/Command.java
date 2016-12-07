package main.java.com.fcastelain.cf.model;

import java.util.regex.Pattern;

public interface Command {

	/**
	 * Return the regex of the command line
	 * @return the regex
	 */
	String getRegex();

	/**
	 * Execute the command and return the graphique.
	 * @param tty terminal passe in param
	 * @param str command line
	 * @return the value
	 */
	default String exec(Terminal tty, String str) {
		return "";
	}

	/**
	 * Check if the String respect the pattern of a class.
	 * @param str to check
	 * @return if it a part of the pattern
	 */
	default boolean check(String str) {
		return Pattern.matches(getRegex(), str);
	}

}