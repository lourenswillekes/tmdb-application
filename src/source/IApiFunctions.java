package source;

import java.util.List;
import info.movito.themoviedbapi.model.MovieDb;

/**
 * Interface for the API methods we are using for TMDb Application.
 * @author Lourens W
 */
public interface IApiFunctions {
	
	
	/**
	 * This method returns a page from the popular movies list.
	 * @param page of popular movies list
	 * @return the list of popular movies from the requested page
	 */
	List<MovieDb> getPopularMovies(final int page);


	/**
	 * This method returns a page from the upcoming movies list.
	 * @param page of upcoming movies list
	 * @return the list of upcoming movies from the requested page
	 */
	List<MovieDb> getUpcomingMovies(final int page);


	/**
	 * This method returns a page from the top rated movies list.
	 * @param page of top rated movies list
	 * @return the list of top rated movies from the requested page
	 */
	List<MovieDb> getTopRatedMovies(final int page);
	
	
	/**
	 * This method returns a page from the now playing movies list.
	 * @param page of now playing movies list
	 * @return the list of now playing movies from the requested page
	 */
	List<MovieDb> getNowPlayingMovies(final int page);
	
	
	/**
	 * This method returns a page from the search results given String srch.
	 * @param srch string to search for movies
	 * @param page of the search results movies list
	 * @return the list of movies from the search on the requested page
	 */
	List<MovieDb> getSearchRes(final String srch, final int page);
	
	
	/**
	 * This function returns movie information for the selected Id.
	 * @param movieId number of the movie requested
	 * @return String with the information
	 */
	String getMovieInfo(int movieId);
	
}
