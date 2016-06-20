package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;
import source.GuestSession;

/**
 * UnitTest Class to test GuestSession.
 * @author Lourens W
 */
public class GuestSessionTest {
	
	/** Declares GuestSession. */
	private GuestSession gs;
	
	/** Declares TmdbApi object to access database. */
	private TmdbApi api = new TmdbApi("34b0b2ee2ac7865db7bd356da1221847");
	
	/** Declares TmdbMovies object to access movies. */
	private TmdbMovies movies = api.getMovies();
	
	/** Declares MovieDb latest: the lates movie from movies. */
	private MovieDb latest = movies.getLatestMovie();
	
	/**
	 * 1. Tests that gs is not null.
	 */
	@Test
	public final void testGuestSession() {
		gs = new GuestSession();
		assertNotNull(gs);
	}
	
	/**
	 * 1. Tests getSelectedMovie to make sure it returns the movie set.
	 */
	@Test
	public final void testSelected() {
		gs = new GuestSession();
		gs.setSelectedMovie(latest);
		assertEquals(latest, gs.getSelectedMovie());
	}

	/**
	 * 1. Tests that size of newly created Favorites is 0.
	 * 2. Tests that latest movie is not in Favorites.
	 * 3. Tests that latest movie is in Favorites.
	 * 4. Tests that size of Favorites is 1.
	 * 5. Tests that the movie in Favorites is latest.
	 * 6. Tests that latest movie is not in Favorites.
	 * 7. Tests that size Favorites is 0. 
	 */
	@Test
	public final void testFavorites() {
		gs = new GuestSession();
		assertEquals(0, gs.getFavorites().size());
		
		gs.setSelectedMovie(latest);
		assertFalse(gs.isSelectedFavorites());
		
		gs.addFavoritesMovie();
		assertTrue(gs.isSelectedFavorites());
		assertEquals(1, gs.getFavorites().size());
		assertEquals(latest, gs.getFavorites().get(0));
		
		gs.rmFavoritesMovie();
		assertFalse(gs.isSelectedFavorites());
		assertEquals(0, gs.getFavorites().size());
	}

	/**
	 * 1. Tests that size of newly created WatchList is 0.
	 * 2. Tests that latest movie is not in WatchList.
	 * 3. Tests that latest movie is in WatchList.
	 * 4. Tests that size of WatchList is 1.
	 * 5. Tests that the movie in WatchList is latest.
	 * 6. Tests that latest movie is not in WatchList.
	 * 7. Tests that size WatchList is 0. 
	 */
	@Test
	public final void testWatchList() {
		gs = new GuestSession();
		assertEquals(0, gs.getWatchList().size());
		
		gs.setSelectedMovie(latest);
		assertFalse(gs.isSelectedWatchList());
		
		gs.addWatchListMovie();
		assertTrue(gs.isSelectedWatchList());
		assertEquals(1, gs.getWatchList().size());
		assertEquals(latest, gs.getWatchList().get(0));
		
		gs.rmWatchListMovie();
		assertFalse(gs.isSelectedWatchList());
		assertEquals(0, gs.getWatchList().size());
	}

}
