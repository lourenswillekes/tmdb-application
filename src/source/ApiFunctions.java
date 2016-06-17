package source;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import info.movito.themoviedbapi.TmdbAccount;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbAuthentication;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.TmdbMovies.MovieMethod;
import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.config.Account;
import info.movito.themoviedbapi.model.config.TokenAuthorisation;
import info.movito.themoviedbapi.model.config.TokenSession;
import info.movito.themoviedbapi.model.core.AccountID;
import info.movito.themoviedbapi.model.core.SessionToken;
import info.movito.themoviedbapi.model.people.PersonCast;

/**
 * Class of all the functionality for the application.
 * 
 * @author Caleb H, Justin N, Lourens W
 *
 */
public class ApiFunctions implements IApiFunctions {
	
	String Password;
	String UserName;
	List<MovieDb> movieList;
	SessionToken sessionToken;
	
	/** tmdbApi object to access the database. */
	private TmdbApi tmdbApi = new TmdbApi("34b0b2ee2ac7865db7bd356da1221847");
	
	/** Movie database object. */
	private TmdbMovies movies = tmdbApi.getMovies();
	
	/** Search database object. */
	private TmdbSearch search = tmdbApi.getSearch();
	
	/**
	 * This method returns a page from the popular movies list.
	 * @param page of popular movies list
	 * @return the list of popular movies from the requested page
	 */
	public final List<MovieDb> getPopularMovies(final int page) {
		List<MovieDb> pop = movies.getPopularMovies("en", page).getResults();
		return pop;
	}


	/**
	 * This method returns a page from the upcoming movies list.
	 * @param page of upcoming movies list
	 * @return the list of upcoming movies from the requested page
	 */
	public final List<MovieDb> getUpcomingMovies(final int page) {
		List<MovieDb> upc = movies.getUpcoming("en", page).getResults();
		return upc;
	}


	/**
	 * This method returns a page from the top rated movies list.
	 * @param page of top rated movies list
	 * @return the list of top rated movies from the requested page
	 */
	public final List<MovieDb> getTopRatedMovies(final int page) {
		List<MovieDb> top = movies.getTopRatedMovies("en", page).getResults();
		return top;
	}
	
	
	/**
	 * This method returns a page from the now playing movies list.
	 * @param page of now playing movies list
	 * @return the list of now playing movies from the requested page
	 */
	public final List<MovieDb> getNowPlayingMovies(final int page) {
		List<MovieDb> now = movies.getNowPlayingMovies("en", page).getResults();
		return now;
	}
	
	
	/**
	 * This method returns a page from the search results given String srch.
	 * @param srch string to search for movies
	 * @param page of the search results movies list
	 * @return the list of movies from the search on the requested page
	 */
	public final List<MovieDb> getSearchRes(final String srch, final int page) {
		List<MovieDb> searchRes = search.searchMovie(srch, 
				0, "en", true, page).getResults();
		return searchRes;
	}
	
	
	/**
	 * This method returns movie information for the selected Id.
	 * @param movieId number of the movie requested
	 * @return String with the information
	 */
	public final String getMovieInfo(final int movieId) {
		String movieInfo = new String("");
		String castStr = new String("");
		String genreStr = new String("");
		
		MovieDb movie = movies.getMovie(
				movieId, "en", MovieMethod.credits, MovieMethod.reviews);
		
		// retrieve information on movie cast
		List<PersonCast> cast = movie.getCast();
		if (0 == cast.size()) {
			castStr = "Cast info not available for this movie";
		} else {
			Iterator<PersonCast> iterator = cast.iterator();
			while (iterator.hasNext()) {
				PersonCast p = iterator.next();
				castStr = (castStr + p.getName() + "\nas "
								   + p.getCharacter() + "\n");
			}
		}
		
		// retrieve information on movie cast
		List<Genre> genre = movie.getGenres();
		if (0 == genre.size()) {
			genreStr = "Genre info not available for this movie";
		} else {
			Iterator<Genre> iterator = genre.iterator();
			while (iterator.hasNext()) {
				Genre g = iterator.next();
				genreStr = (genreStr + g.getName() + "\n");
			}
		}
		
		movieInfo = "Title: \n" + movie.getTitle()
				+ "\n\nRelease Date:\n" + movie.getReleaseDate()
				+ "\n\nRating:\n" + movie.getVoteAverage()
				+ "\n\nGenre:\n" + genreStr
				+ "\nRun Time:\n" + movie.getRuntime()
				+ "\n\nCast:\n" + castStr;
		
		return movieInfo;
	}

	/**
	 * This method returns the user password string used to Login
	 * @return a string representing the password
	 */
	public String getPassword() {
		return Password;
	}

	/**	
	 * This method is used to set the password
	 * @param password A string representation of the password to set 
	 */
	public void setPassword(String password) {
		Password = password;
	}

	/**
	 * This method returns the user UserName used to Login
	 * @return a string representing the username
	 */
	public String getUserName() {
		return UserName;
	}

	/**
	 * This method is used to set the username 
	 * @param userName A string representation of the username to set
	 */
	public void setUserName(String userName) {
		UserName = userName;
	}

	/**
	 * This method Returns the active movie List
	 * @return the list of movies that has been set 
	 */
	public List<MovieDb> getMovieList() {
		return movieList;
	}

	/**
	 * This method is used to set the movieList
	 * @param movieList the List of movies to set
	 */
	public void setMovieList(List<MovieDb> movieList) {
		this.movieList = movieList;
	}

	/**
	 * This method gets the session token from the sessionToken Field
	 * Uses the userName and password to login to the movie DB and get a 
	 * session ID
	 * @return The session token that is used to work session specific methods  
	 */
	public SessionToken getSessionToken() {
		
		// There are two ways to generate session id
		// Generating session id using only API calls (requires username and password)
		if(this.UserName.equalsIgnoreCase("guest") && this.Password.equalsIgnoreCase("guest"))
		{
			return null;
		}
		TmdbAuthentication tmdbAuth = tmdbApi.getAuthentication();
		TokenAuthorisation tokenAuth = tmdbAuth.getLoginToken(tmdbAuth.getAuthorisationToken(), this.UserName, this.Password);
		TokenSession tokenSession = tmdbAuth.getSessionToken(tokenAuth);
		String sessionId = tokenSession.getSessionId();
		SessionToken sessionToken = new SessionToken(sessionId);
				
		return sessionToken;
	}
	
	/**
	 * THis method is used to set the sessionToken to the Field
	 * @param sessionToken: A session Token that has been made from 
	 * logging in
	 */
	public void setSessionToken(SessionToken sessionToken) {
		this.sessionToken = sessionToken;
	}

	/**
	 * This method Gets the favorites page of movies from a users SessionToekn
	 * @param page of the favorites results movies list
	 * @return the list of movies from the favorites on the requested page
	 */
	public final List<MovieDb> getFavorites(final int page) {
		List<MovieDb> upc;
		if(this.sessionToken != null)
		{
			TmdbAccount tmdbAccount = tmdbApi.getAccount();
			Account act = tmdbAccount.getAccount(this.sessionToken);
			AccountID actId = new AccountID(act.getId());
			
			upc = tmdbAccount.getFavoriteMovies(this.sessionToken, actId).getResults();
		}
		else
		{
			upc =  new ArrayList<MovieDb>();
		}
		return upc;
	}
	
	/**
	 * This method Gets the watchlist page of movies from a users SessionToekn
	 * @param page of the  watchlist results movies list
	 * @return the list of movies from the  watchlist on the requested page
	 */
	public final List<MovieDb> getWatchList(final int page) {
		
		List<MovieDb> upc;
		if(this.sessionToken != null)
		{
			TmdbAccount tmdbAccount = tmdbApi.getAccount();
			Account act = tmdbAccount.getAccount(this.sessionToken);
			AccountID actId = new AccountID(act.getId());
			
			upc = tmdbAccount.getWatchListMovies(this.sessionToken, actId, page).getResults();
		}
		else
		{
			upc =  new ArrayList<MovieDb>();
		}
		return upc;
	}
	
	/**
	 * Gets the account name from the session Token
	 * @return a String representing the accountName
	 * If sessionToken is NULL, then returns blank string
	 */
	public final String getaccountName() {

		if(this.sessionToken != null)
		{
			TmdbAccount tmdbAccount = tmdbApi.getAccount();
			Account act = tmdbAccount.getAccount(this.sessionToken);
			String ret = new String("Hello " +act.getUserName());
			return ret;
		}
		else if(this.UserName.equalsIgnoreCase("guest") && this.Password.equalsIgnoreCase("guest"))
		{
			return "Hello Guest";
		}
		else
			return "";
	}
}
