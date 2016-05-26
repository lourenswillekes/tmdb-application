package source;

import java.util.*;
import info.movito.themoviedbapi.*;
import info.movito.themoviedbapi.TmdbMovies.MovieMethod;
import info.movito.themoviedbapi.model.*;
//import info.movito.themoviedbapi.model.changes.*;
import info.movito.themoviedbapi.model.config.*;
import info.movito.themoviedbapi.model.core.*;
//import info.movito.themoviedbapi.model.keywords.*;
import info.movito.themoviedbapi.model.people.*;
//import info.movito.themoviedbapi.model.tv.*;
//import info.movito.themoviedbapi.tools.*;

public class Test {

	private static TmdbApi tmdbApi;
	private static SessionToken sessionToken;
	
	public static void main(String[] args) {
		tmdbApi = new TmdbApi("34b0b2ee2ac7865db7bd356da1221847");
		
		// certain methods in TMDb API require a session id as a parameter, so
		// let's generate it and have it ready
		sessionToken = getSessionToken();
		
		// demonstration of general movie/tv features of TMDb API
		demoMovieFeatures();
		
		// demonstration of searching features of TMDb API
		demoSearchFeatures();
		
		// demonstration of account related features of TMDb API
		demoAccountFeatures();
	}

	private static SessionToken getSessionToken() {
		// There are two ways to generate session id
		
		// Generating session id using only API calls (requires username and password)
		/*
		TmdbAuthentication tmdbAuth = tmdbApi.getAuthentication();
		TokenAuthorisation tokenAuth = tmdbAuth.getLoginToken(tmdbAuth.getAuthorisationToken(), "username", "password");
		TokenSession tokenSession = tmdbAuth.getSessionToken(tokenAuth);
		String sessionId = tokenSession.getSessionId();
		SessionToken sessionToken = new SessionToken(sessionId);
		*/
		
		// Generating session id via the website (user interaction involved)
		// Step 1: create a new request token
		//		http://api.themoviedb.org/3/authentication/token/new?api_key=your-api-key
		//		(note down the request_token from the response)
		// Step 2: ask the user for permission
		//		https://www.themoviedb.org/authenticate/request_token
		// Step 3: create a session id
		//		http://api.themoviedb.org/3/authentication/session/new?api_key=api-key&request_token=request-token
		//		(use session-id value in the response to set the value for sessionId variable in the code below
		String sessionId = "05a83e277910fba5d940a40565e473c25493f38d";
		SessionToken sessionToken = new SessionToken(sessionId);
		
		return sessionToken;
	}
	
	private static void demoMovieFeatures() {
		TmdbMovies tmdbMovies = tmdbApi.getMovies();
		
		// retrieve information on a movie given movie id
		MovieDb movie = tmdbMovies.getMovie(271110, "en", MovieMethod.credits, MovieMethod.reviews);
		System.out.println(movie.getTitle());
		System.out.println(movie.getReleaseDate());
		System.out.println(movie.getRuntime());
		System.out.println(movie.getOverview());
		System.out.println();
		
		// retrieve information on movie cast
		List<PersonCast> cast = movie.getCast();
		if (cast == null) {
			System.out.println("Cast info not available for this movie");
		} else {
			Iterator<PersonCast> iterator = cast.iterator();
			while (iterator.hasNext()) {
				PersonCast person = iterator.next();
				System.out.println(person.getName());
				System.out.println(person.getCharacter());
				System.out.println();
			}
		}
		
		// retrieve information on movie crew
		List<PersonCrew> crew = movie.getCrew();
		if (crew == null) {
			System.out.println("Crew info not available for this movie");
		} else {
			Iterator<PersonCrew> iterator = crew.iterator();
			while (iterator.hasNext()) {
				PersonCrew person = iterator.next();
				System.out.println(person.getName());
				System.out.println(person.getJob());
				System.out.println();
			}
		}
		
		// get review for the movie
		List<Reviews> reviews = movie.getReviews();
		if (reviews == null) {
			System.out.println("Reviews not available for this movie");
		} else {
			Iterator<Reviews> iterator = reviews.iterator();
			while (iterator.hasNext()) {
				Reviews review = iterator.next();
				System.out.println(review.getAuthor());
				System.out.println(review.getContent());
				System.out.println();
			}
		}
	}
	
	private static void demoSearchFeatures() {
		TmdbSearch tmdbSearch = tmdbApi.getSearch();
		
		// search for movies containing "civil war" in title
		MovieResultsPage results = tmdbSearch.searchMovie("civil war", 0, "en", false, 0);
		Iterator<MovieDb> iterator = results.iterator();
		while (iterator.hasNext()) {
			MovieDb movie = iterator.next();
			System.out.println(movie.getTitle());
			System.out.println(movie.getOriginalTitle());
			System.out.println(movie.getReleaseDate());
			System.out.println();
		}	
	}
	
	private static void demoAccountFeatures() {
		TmdbAccount tmdbAccount = tmdbApi.getAccount();
		
		// get basic account information
		Account act = tmdbAccount.getAccount(sessionToken);
		System.out.println(act.getId());
		System.out.println(act.getUserName());
		System.out.println(act.getName());
		System.out.println();
		
		// get favorite movies of the account
		AccountID actId = new AccountID(act.getId());
		MovieResultsPage results = tmdbAccount.getFavoriteMovies(sessionToken, actId);
		System.out.println("Movies in the favorite list: " + results.getTotalResults());
		Iterator<MovieDb> iterator = results.iterator();
		while (iterator.hasNext()) {
			MovieDb movie = iterator.next();
			System.out.println(movie.getTitle());
			System.out.println(movie.getReleaseDate());
			System.out.println();
		}
		
		// get movies in the watch list of the account
		results = tmdbAccount.getWatchListMovies(sessionToken, actId, 0);
		System.out.println("Movies in the watch list: " + results.getTotalResults());
		iterator = results.iterator();
		while (iterator.hasNext()) {
			MovieDb movie = iterator.next();
			System.out.println(movie.getTitle());
			System.out.println(movie.getId());
			System.out.println(movie.getReleaseDate());
			System.out.println();
		}	
	}
	
	
	
	
	

	static int MAX_MOVIE_ID = 400000;
	private TmdbMovies movies = tmdbApi.getMovies();
	
	/**
	 * This function randomly selects a movie and returns the object.
	 * @return randMovie the randomly selected movie
	 */
	public final MovieDb getRandomMovie() {
		
		int randInt;
		MovieDb randMovie = null;
		Random rand = new Random();
		do {	
			try	{
				randInt = rand.nextInt(MAX_MOVIE_ID);
				randMovie = movies.getMovie(randInt, "en");
			} catch (Exception e) {
				
			}
		} while (null == randMovie);
		
		return randMovie;
	}
}
