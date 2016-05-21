package source;

import info.movito.themoviedbapi.*;
import info.movito.themoviedbapi.model.*;
import info.movito.themoviedbapi.model.people.*;
import info.movito.themoviedbapi.model.tv.*;

import java.util.Random;

/**
 * Class of all the functionality for the application.
 * 
 * @author Caleb H, Justin N, Lourens W
 *
 */
public class Functions {
	

	public static int MAX_MOVIE_ID = 300000;
	
	/**
	 * tmdbAPi object to access the database.
	 */
	private TmdbApi tmdbApi = new TmdbApi("34b0b2ee2ac7865db7bd356da1221847");
	
	/**
	 * Movie database object.
	 */
	private TmdbMovies movies = tmdbApi.getMovies();
	/**
	 * People database object.
	 */
	private TmdbPeople people = tmdbApi.getPeople();
	/**
	 * All TV series database object.
	 */
	private TmdbTV allSeries = tmdbApi.getTvSeries();
	
	
	
	
	/**
	 * This function randomly selects a movie and returns the object.
	 * 
	 * @return randMovie the randomly selected movie
	 */
	public final MovieDb getRandomMovie() {
		
		int randInt;
		MovieDb randMovie = null;
		Random rand = new Random();
		do {	
			try	{
				randInt = rand.nextInt(MAX_MOVIE_ID);
				randMovie = movies.getMovie(randInt, "en");
			} catch (Exception e) {
				
			}
		} while (null == randMovie);
		
		return randMovie;
	}
	
	

	/**
	 * Main function.
	 * 
	 * @param args .
	 */
	public static void main(final String[] args) {
		
	}
	
	
}
