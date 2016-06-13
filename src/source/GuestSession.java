package source;

import info.movito.themoviedbapi.model.MovieDb;

import java.util.ArrayList;
import java.util.List;


/**
 * Class used when the user does not login.
 * @author Lourens W
 */
public class GuestSession implements ISession {

	/** Private list of movies for guest favorites. */
	private List<MovieDb> guestFavorites;
	
	/** Private list of movies for guest watchlist. */
	private List<MovieDb> guestWatchList;

	/** Private object to access the db movies. */
	private MovieDb selected;
	
	
	/**
	 * Public constructor for GuestSession.
	 */
	public GuestSession() {
		guestFavorites = new ArrayList<MovieDb>();
		guestWatchList = new ArrayList<MovieDb>();
	}
	
	
	/**
	 * This method returns the user's favorites.
	 * @return movies on the favorites list
	 */
	@Override
	public final List<MovieDb> getFavorites() {
		return guestFavorites;
	}

	/**
	 * This method returns the user's watchlist.
	 * @return moves on the watchlist list
	 */
	@Override
	public final List<MovieDb> getWatchList() {
		return guestWatchList;
	}
	
	/**
	 * This method adds the selected movie to the favorites.
	 * @param mov the movie to remove from the list
	 */
	@Override
	public final void addFavoritesMovie(final MovieDb mov) {
		guestFavorites.add(mov);
	}
	
	/**
	 * This method returns the requested movie from the watchlist.
	 * @param mov the movie to remove from the list
	 */
	@Override
	public final void addWatchListMovie(final MovieDb mov) {
		guestWatchList.add(mov);
	}
	
	/**
	 * This method removes the requested movie from the favorites.
	 * @param mov the movie to remove from the list
	 */
	@Override
	public final void rmFavoritesMovie(final MovieDb mov) {
		guestFavorites.remove(mov);
	}
	
	/**
	 * This method removes the requested movie from the watchlist.
	 * @param mov the movie to remove from the list
	 */
	@Override
	public final void rmWatchListMovie(final MovieDb mov) {
		guestWatchList.remove(mov);
	}

	/**
	 * This method gets the selected movie.
	 * @return the selected movie
	 */
	@Override
	public final MovieDb getSelectedMovie() {
		return selected;
	}

	/**
	 * This method sets the selected movie.
	 * @param mov the movie to set as selected
	 */
	@Override
	public final void setSelectedMovie(final MovieDb mov) {
		selected = mov;
	}


	/**
	 * This method returns true if the selected movie is in the favorites list.
	 * @param mov to check for in the list
	 * @return true if found, else false
	 */
	@Override
	public final boolean isSelectedFavorites(final MovieDb mov) {
		return guestFavorites.contains(mov);
	}


	/**
	 * This method returns true if the selected movie is in the watchlist list.
	 * @param mov to check for in the list
	 * @return true if found, else false
	 */
	@Override
	public final boolean isSelectedWatchList(final MovieDb mov) {
		return guestWatchList.contains(mov);
	}
	
}
