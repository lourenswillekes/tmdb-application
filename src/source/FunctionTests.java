package source;

import info.movito.themoviedbapi.*;
import info.movito.themoviedbapi.model.*;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.people.*;
import info.movito.themoviedbapi.model.tv.*;
import info.movito.themoviedbapi.TmdbMovies.MovieMethod;

import java.util.List;
import java.util.Random;

public class FunctionTests {

	static int MAX_MOVIE_ID = 400000;
	
	public static void main(String[] args) {
		TmdbApi tmdbApi = new TmdbApi("34b0b2ee2ac7865db7bd356da1221847");
		
		TmdbMovies movies = tmdbApi.getMovies();
//		TmdbSearch search = tmdbApi.getSearch();
//		
//		// get information on a movie
//		MovieDb movie = movies.getMovie(03, "en", MovieMethod.now_playing);
//		System.out.println(movie.getTitle());
//		
//		
//		
//		int randInt;
//		MovieDb randMovie = null;
//		Random rand = new Random();
//		do {	
//			try	{
//				randInt = rand.nextInt(MAX_MOVIE_ID);
//				randMovie = movies.getMovie(randInt, "en");
//			}
//			catch (Exception e) {
//
//			}
//		} while (null == randMovie);
//		System.out.println(randMovie.getTitle() + " (" + randMovie.getReleaseDate() + ")");
		
		
//		List<MovieDb> playing = movies.getNowPlayingMovies("en", 0).getResults();
//		System.out.println(playing);
		
		List<MovieDb> popular = movies.getPopularMovies("en", 1).getResults();
		System.out.println(popular);
//		
//		List<MovieDb> top = movies.getTopRatedMovies("en", 0).getResults();
//		System.out.println(top);
		
		List<MovieDb> upcoming = movies.getUpcoming("en", 1).getResults();
		System.out.println(upcoming);
		
		
		
//		MovieDb newest = movies.getLatestMovie();
//		System.out.println(newest);
//		
//		MovieResultsPage searchRes = search.searchMovie(newest.getTitle(), 0, "en", true, 0);
//		List<MovieDb> searchList = searchRes.getResults();
//		System.out.println(searchList.get(0).getId());

		
		
		
		
	}
	
}
