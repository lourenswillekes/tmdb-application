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
	 * @param mov the movie to add to the list
	 */
	void addFavoritesMovie(MovieDb mov);

	
	/**
	 * This method adds the selected movie to the watchlist.
	 * @param mov the movie to add to the list
	 */
	void addWatchListMovie(MovieDb mov);
	
	
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
	 * @param mov to check for in the list
	 * @return true if found, else false
	 */
	boolean isSelectedFavorites(MovieDb mov);
	
	
	/**
	 * This method returns true if the selected movie is in the watchlist list.
	 * @param mov to check for in the list
	 * @return true if found, else false
	 */
	boolean isSelectedWatchList(MovieDb mov);
	
}
