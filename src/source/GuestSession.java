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
	 * @return movies on the watchlist list
	 */
	@Override
	public final List<MovieDb> getWatchList() {
		return guestWatchList;
	}
	
	/**
	 * This method adds the selected movie to the favorites.
	 */
	@Override
	public final void addFavoritesMovie() {
		guestFavorites.add(selected);
	}
	
	/**
	 * This method adds the selected movie to the watchlist.
	 */
	@Override
	public final void addWatchListMovie() {
		guestWatchList.add(selected);
	}
	
	/**
	 * This method removes the requested movie from the favorites.
	 */
	@Override
	public final void rmFavoritesMovie() {
		guestFavorites.remove(selected);
	}
	
	/**
	 * This method removes the requested movie from the watchlist.
	 */
	@Override
	public final void rmWatchListMovie() {
		guestWatchList.remove(selected);
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
	 * @return true if found, else false
	 */
	@Override
	public final boolean isSelectedFavorites() {
		return guestFavorites.contains(selected);
	}


	/**
	 * This method returns true if the selected movie is in the watchlist list.
	 * @return true if found, else false
	 */
	@Override
	public final boolean isSelectedWatchList() {
		return guestWatchList.contains(selected);
	}

	
}
