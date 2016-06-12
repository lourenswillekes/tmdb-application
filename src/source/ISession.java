package source;

import info.movito.themoviedbapi.model.MovieDb;
import java.util.List;

/**
 * Interface for two different session types.
 * @author Lourens W
 */
public interface ISession {

	
	/**
	 * This method returns the user's favorites.
	 * @return movies on the favorites list
	 */
	List<MovieDb> getFavorites();
	
	
	/**
	 * This method returns the user's watchlist.
	 * @return moves on the watchlist list
	 */
	List<MovieDb> getWatchList();
	
	
	/**
	 * This method returns the requested movie from the favorites.
	 * @param id the index of the selected movie
	 */
	void addFavoritesMovie(int id);

	
	/**
	 * This method returns the requested movie from the watchlist.
	 * @param id the index of the selected movie
	 */
	void addWatchListMovie(int id);
	
	
	/**
	 * This method removes the requested movie from the favorites.
	 * @param mov the movie to remove from the list
	 */
	void rmFavoritesMovie(MovieDb mov);
	
	
	/**
	 * This method removes the requested movie from the watchlist.
	 * @param mov the movie to remove from the list
	 */
	void rmWatchListMovie(MovieDb mov);
	
}
