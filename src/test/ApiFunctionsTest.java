package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import source.ApiFunctions;


/**
 * UnitTest Class to test ApiFunctions.
 * @author Lourens W
 */
public class ApiFunctionsTest {
	
	/**
	 * class declaration of ApiFunctions.
	 */
	private ApiFunctions apiF = new ApiFunctions();

	/**
	 * Tests getPopularMovies by checking for 20 movies returned.
	 */
	@Test
	public final void testGetPopularMovies() {
		assertEquals(20, apiF.getPopularMovies(1).size());
	}

	/**
	 * Tests getUpcomingMovies by checking for 20 movies returned.
	 */
	@Test
	public final void testGetUpcomingMovies() {
		assertEquals(20, apiF.getUpcomingMovies(1).size());
	}

	/**
	 * Tests getTopRatedMovies by checking for 20 movies returned.
	 */
	@Test
	public final void testGetTopRatedMovies() {
		assertEquals(20, apiF.getTopRatedMovies(1).size());
	}

	/**
	 * Tests getNowPlayingMovies by checking for 20 movies returned.
	 */
	@Test
	public final void testGetNowPlayingMovies() {
		assertEquals(20, apiF.getNowPlayingMovies(1).size());
	}

	/**
	 * 1. Tests getSearchRes by checking for 20 movies returned.
	 * 2. Tests getSearchRes by checking for  4 movies returned.
	 */
	@Test
	public final void testGetSearchRes() {
		assertEquals(20, apiF.getSearchRes("top", 1).size());
		assertEquals(4, apiF.getSearchRes("prestige", 1).size());
	}

	/**
	 * 1. Tests getMovieInfo by checking the title of a movie returned.
	 * 2. Tests getMovieInfo by checking a movie with null cast returned.
	 */
	@Test
	public final void testGetMovieInfo() {
		assertEquals("Ariel", apiF.getMovieInfo(2).substring(8, 13));
		assertEquals("First Life", apiF.getMovieInfo(82960).substring(8, 18)); 
	}
	
	/**
	 * 1. Tests that the set password equals what is gotten.
	 */
	@Test
	public final void testPassword() {
		String pass = new String("password");
		apiF.setPassword(pass);
		assertEquals(pass, apiF.getPassword());
	}
	
	/**
	 * 1. Tests that the set password equals what is gotten.
	 */
	@Test
	public final void testUsername() {
		String user = new String("username");
		apiF.setUserName(user);
		assertEquals(user, apiF.getUserName());
	}

}
