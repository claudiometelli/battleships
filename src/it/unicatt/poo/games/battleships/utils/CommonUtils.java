package it.unicatt.poo.games.battleships.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * A class providing a number of useful functionalities to be used across the whole application.
 * @author Daniele
 *
 */
public class CommonUtils {

	/**
	 * Returns the corresponding 0-based position in the International alphabet
	 * of a single-character string representing a letter from A to Z.
	 * @param s - a single-character string
	 * @return a number between 0 and 25 according to the position of the given letter in the International alphabet
	 */
	public static Integer getAlphabeticalIndexFromString(String s) {
		Integer result = null;
		s = s.toUpperCase();
		if(s!=null && s.length()==1) {
			result = (char)s.charAt(0)-65;
		}
		return result;
	}

	/**
	 * Returns the corresponding letter of the International alphabet
	 * according to the given 0-based index (from 0 to 25);
	 * @param i - an index ranging from 0 to 25
	 * @return the corresponding letter
	 */
	public static String getAlphabeticalStringFromIndex(int index) {
		String result = null;
		if(index >= 0 && index < 26) {
			int c = 65 + index;
			result = (char)c+"";
			result = result.toUpperCase();
		}
		return result;
	}

	/**
	 * Returns a random integer included within the given range.
	 * @param min - the lower bound (included) of the range
	 * @param max - the upper bound (included) of the range
	 * @return a random integer
	 */
	public static int getRandomIntegerBetweenRange(int min, int max) {
		Double d = (Math.random()*((max-min)+1))+min;
		return d.intValue(); 
	}

	/**
	 * Writes an Object to disk by storing it within the given file path.
	 *
	 * @param o the input Object
	 * @param path the file path
	 */
	public static void serializeObject(Object o, String path) {
		// Write to disk with FileOutputStream
		try (
			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream objOut = new ObjectOutputStream(fileOut)) {
			// Write object out to disk
			objOut.writeObject(o);
			objOut.flush();
		} catch (Exception e) {
			System.err.println("An error occurred while serializing object to file");
		}
	}

	/**
	 * Reads a previously stored Object from disk, given its file path.
	 *
	 * @param path the stored Object's file path
	 * @return an Object if found, null otherwise
	 */
	public static Object deserializeObject(String path) {
		Object o = null;
		// Read from disk using FileInputStream
		try (
			FileInputStream fileIn = new FileInputStream(path);
			ObjectInputStream objIn = new ObjectInputStream(fileIn)) {
			// Read an object
			o = objIn.readObject();

		} catch (Exception e) {
			System.err.println("An error occurred while deserializing object from file");
		}

		return o;
	}

	public static void main(String[] args) {
		String s = "D";
		int i = 25;
		System.out.println(getAlphabeticalIndexFromString(s));
		System.out.println(getAlphabeticalStringFromIndex(i));

		System.out.println(getRandomIntegerBetweenRange(0, 1));	
	}
}