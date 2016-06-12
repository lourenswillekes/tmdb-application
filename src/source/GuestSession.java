package source;

import info.movito.themoviedbapi.TmdbApi;
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

	/** Private object to access the db movies. */
	private TmdbMovies movies;
	
	
	/**
	 * Public constructor for GuestSession.
	 */
	public GuestSession() {
		
		final TmdbApi api = new TmdbApi("34b0b2ee2ac7865db7bd356da1221847");
		
		guestFavorites = new ArrayList<MovieDb>();
		guestWatchList = new ArrayList<MovieDb>();
		movies = api.getMovies();
		
	}
	
	
	/**
	 * This method returns the user's favorites.
	 * @return movies on the favorites list
	 */
	public final List<MovieDb> getFavorites() {
		return guestFavorites;
	}

	/**
	 * This method returns the user's watchlist.
	 * @return moves on the watchlist list
	 */
	public final List<MovieDb> getWatchList() {
		return guestWatchList;
	}
	
	/**
	 * This method returns the requested movie from the favorites.
	 * @param id the index of the selected movie
	 */
	public final void addFavoritesMovie(final int id) {
		guestFavorites.add(movies.getMovie(id, "en"));
	}
	
	/**
	 * This method returns the requested movie from the watchlist.
	 * @param id the index of the selected movie
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
