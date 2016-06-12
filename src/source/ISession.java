package source;

import info.movito.themoviedbapi.model.MovieDb;
import java.util.List;

/**
 * Interface for two different session types.
 * @author Lourens W
 */
public interface ISession {

	
	/**
	 * This method returns the users favorites.
	 * @return movies on the favorites list
	 */
	List<MovieDb> getFavorites();
	
	
	/**
	 * This method returns the users watchlist.
	 * @return moves on the watchlist list
	 */
	List<MovieDb> getWatchList();
	
	
	/**
	 * This method returns the requested movie from the favorites.
	 * @param idx the index of the selected movie
	 * @return the movie that was requested
	 */
	MovieDb getFavoritesMovie(int idx);

	
	/**
	 * This method returns the requested movie from the watchlist.
	 * @param idx the index of the selected movie
	 * @return the movie that was requested
	 */
	MovieDb getWatchListMovie(int idx);
	
	
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
