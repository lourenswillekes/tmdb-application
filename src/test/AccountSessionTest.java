package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;
import source.AccountSession;

/**
 * UnitTest Class to test AccountSession.
 * @author Lourens W
 */
public class AccountSessionTest {
	
	/** Declares AccountSession. */
	private AccountSession as;
	
	/** Declares TmdbApi object to access database. */
	private TmdbApi api = new TmdbApi("34b0b2ee2ac7865db7bd356da1221847");
	
	/** Declares TmdbMovies object to access movies. */
	private TmdbMovies movies = api.getMovies();
	
	/** Declares MovieDb latest: the lates movie from movies. */
	private MovieDb latest = movies.getLatestMovie();
	
	/**
	 * 1. Tests that the tkn is null.
	 * 2. Tests that as is not null.
	 */
	@Test
	public final void testAccountSession() {
		as = new AccountSession("testaccout73", "wrongpassword");
		assertNull(as.getTkn());
		
		as = new AccountSession("testaccount73", "passNEWword");
		assertNotNull(as);
	}
	
	/**
	 * 1. Tests getSelectedMovie to make sure it returns the movie set.
	 */
	@Test
	public final void testSelected() {
		as = new AccountSession("testaccount73", "passNEWword");
		as.setSelectedMovie(latest);
		assertEquals(latest, as.getSelectedMovie());
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
		as = new AccountSession("testaccount73", "passNEWword");
		assertEquals(0, as.getFavorites().size());
		
		as.setSelectedMovie(latest);
		assertFalse(as.isSelectedFavorites());
		
		as.addFavoritesMovie();
		assertTrue(as.isSelectedFavorites());
		assertEquals(1, as.getFavorites().size());
		assertEquals(latest, as.getFavorites().get(0));
		
		as.rmFavoritesMovie();
		assertFalse(as.isSelectedFavorites());
		assertEquals(0, as.getFavorites().size());
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
		as = new AccountSession("testaccount73", "passNEWword");
		assertEquals(0, as.getWatchList().size());
		
		as.setSelectedMovie(latest);
		assertFalse(as.isSelectedWatchList());
		
		as.addWatchListMovie();
		assertTrue(as.isSelectedWatchList());
		assertEquals(1, as.getWatchList().size());
		assertEquals(latest, as.getWatchList().get(0));
		
		as.rmWatchListMovie();
		assertFalse(as.isSelectedWatchList());
		assertEquals(0, as.getWatchList().size());
	}

}
