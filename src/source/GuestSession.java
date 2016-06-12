package source;

import info.movito.themoviedbapi.TmdbMovies;
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
	
	private TmdbMovies movies;
	
	
	/**
	 * Public constructor for GuestSession.
	 */
	public GuestSession() {
		guestFavorites = new ArrayList<MovieDb>();
		guestWatchList = new ArrayList<MovieDb>();
	}
	
	
	/**
	 * This method returns the users favorites.
	 * @return movies on the favorites list
	 */
	public final List<MovieDb> getFavorites() {
		return guestFavorites;
	}

	/**
	 * This method returns the users watchlist.
	 * @return moves on the watchlist list
	 */
	public final List<MovieDb> getWatchList() {
		return guestWatchList;
	}
	
	/**
	 * This method returns the requested movie from the favorites.
	 * @param idx the index of the selected movie
	 * @return the movie that was requested
	 */
	public final void addFavoritesMovie(final int id) {
		guestFavorites.add(movies.getMovie(id, "en"));
	}
	
	/**
	 * This method returns the requested movie from the watchlist.
	 * @param idx the index of the selected movie
	 * @return the movie that was requested
	 */
	public final void addWatchListMovie(final int id) {
		guestWatchList.add(movies.getMovie(id, "en"));
	}
	
	/**
	 * This method removes the requested movie from the favorites.
	 * @param mov the movie to remove from the list
	 */
	public final void rmFavoritesMovie(final MovieDb mov) {
		guestFavorites.remove(mov);
	}
	
	/**
	 * This method removes the requested movie from the watchlist.
	 * @param mov the movie to remove from the list
	 */
	public final void rmWatchListMovie(final MovieDb mov) {
		guestWatchList.remove(mov);
	}
	
}
