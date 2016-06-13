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
	 * This method adds the selected movie to the favorites.
	 */
	void addFavoritesMovie();

	
	/**
	 * This method adds the selected movie to the watchlist.
	 */
	void addWatchListMovie();
	
	
	/**
	 * This method removes the selected movie from the favorites.
	 */
	void rmFavoritesMovie();
	
	
	/**
	 * This method removes the selected movie from the watchlist.
	 */
	void rmWatchListMovie();
	
	
	/**
	 * This method gets the selected movie.
	 * @return the selected movie
	 */
	MovieDb getSelectedMovie();
	
	
	/**
	 * This method sets the selected movie.
	 * @param mov the movie to set as selected
	 */
	void setSelectedMovie(MovieDb mov);
	
	
	/**
	 * This method returns true if the selected movie is in the favorites list.
	 * @return true if found, else false
	 */
	boolean isSelectedFavorites();
	
	
	/**
	 * This method returns true if the selected movie is in the watchlist list.
	 * @return true if found, else false
	 */
	boolean isSelectedWatchList();
	
}
