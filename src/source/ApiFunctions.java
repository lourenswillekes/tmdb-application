package source;

import java.util.List;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.model.MovieDb;

/**
 * Class of all the functionality for the application.
 * 
 * @author Caleb H, Justin N, Lourens W
 *
 */
public class ApiFunctions {
	
	/**
	 * tmdbAPi object to access the database.
	 */
	private TmdbApi tmdbApi = new TmdbApi("34b0b2ee2ac7865db7bd356da1221847");
	/**
	 * Movie database object.
	 */
	private TmdbMovies movies = tmdbApi.getMovies();
	/**
	 * Search database object.
	 */
	private TmdbSearch search = tmdbApi.getSearch();
	
	
	

	
	/**
	 * This function returns a page from the popular movies list.
	 * @param page of popular movies list
	 * @return the list of popular movies from the requested page
	 */
	public final List<MovieDb> getPopularMovies(final int page) {
		List<MovieDb> pop = movies.getPopularMovies("en", page).getResults();
		return pop;
	}


	/**
	 * This function returns a page from the upcoming movies list.
	 * @param page of upcoming movies list
	 * @return the list of upcoming movies from the requested page
	 */
	public final List<MovieDb> getUpcomingMovies(final int page) {
		List<MovieDb> upc = movies.getUpcoming("en", page).getResults();
		return upc;
	}


	/**
	 * This function returns a page from the top rated movies list.
	 * @param page of top rated movies list
	 * @return the list of top rated movies from the requested page
	 */
	public final List<MovieDb> getTopRatedMovies(final int page) {
		List<MovieDb> top = movies.getTopRatedMovies("en", page).getResults();
		return top;
	}
	
	
	/**
	 * This function returns a page from the now playing movies list.
	 * @param page of now playing movies list
	 * @return the list of now playing movies from the requested page
	 */
	public final List<MovieDb> getNowPlayingMovies(final int page) {
		List<MovieDb> now = movies.getNowPlayingMovies("en", page).getResults();
		return now;
	}
	
	
	/**
	 * This function returns a page from the search results given String srch.
	 * @param srch string to search for movies
	 * @param page of the search results movies list
	 * @return the list of movies from the search on the requested page
	 */
	public final List<MovieDb> getSearchRes(final String srch, final int page) {
		List<MovieDb> searchRes = search.searchMovie(srch, 
				0, "en", true, page).getResults();
		return searchRes;
	}
	
}
