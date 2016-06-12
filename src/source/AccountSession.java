package source;

import info.movito.themoviedbapi.TmdbAccount;
import info.movito.themoviedbapi.TmdbAccount.MediaType;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbAuthentication;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.config.TokenSession;
import info.movito.themoviedbapi.model.core.AccountID;
import info.movito.themoviedbapi.model.core.SessionToken;

import java.util.List;


/** 
 * Class used when the user logs into his/her account.
 * @author Lourens W
 */
public class AccountSession implements ISession {
	
	/** Private session token for the account logged in. */
	private SessionToken tkn;
	
	/** Private TMDb account for the account logged in. */
	private TmdbAccount acc;
	
	/** Private account Id for the account logged in. */
	private AccountID aid;
	
	
	/**
	 * Public constructor for AccountSession.
	 * @param username of the account logging in.
	 * @param password of the account logging in.
	 */
	public AccountSession(final String username, final String password) {
		
		final TmdbApi api = new TmdbApi("34b0b2ee2ac7865db7bd356da1221847");
		final TmdbAuthentication tmdbAuth = api.getAuthentication();
		final TokenSession sess = tmdbAuth.getSessionLogin(username, password);

		tkn = new SessionToken(sess.getSessionId());
		acc = api.getAccount();
		aid = new AccountID(acc.getAccount(tkn).getId());

	}
	
	
	/**
	 * This method returns the user's favorites.
	 * @return movies on the favorites list
	 */
	public final List<MovieDb> getFavorites() {
		List<MovieDb> fav = acc.getFavoriteMovies(tkn, aid).getResults();
		return fav;
	}

	/**
	 * This method returns the user's watchlist.
	 * @return movies on the watchlist list
	 */
	public final List<MovieDb> getWatchList() {
		List<MovieDb> wat = acc.getWatchListMovies(tkn, aid, 1).getResults();
		return wat;
	}

	/**
	 * This method returns the requested movie from the favorites.
	 * @param id the index of the selected movie
	 */
	public final void addFavoritesMovie(final int id) {
		acc.addFavorite(tkn, aid, id, MediaType.MOVIE);
	}

	/**
	 * This method returns the requested movie from the watchlist.
	 * @param id the index of the selected movie
	 */
	
	public final void addWatchListMovie(final int id) {
		acc.addToWatchList(tkn, aid, id, MediaType.MOVIE);
	}

	/**
	 * This method removes the requested movie from the favorites.
	 * @param mov the movie to remove from the list
	 */
	
	public final void rmFavoritesMovie(final MovieDb mov) {
		acc.removeFavorite(tkn, aid, mov.getId(), MediaType.MOVIE);
	}

	/**
	 * This method removes the requested movie from the watchlist.
	 * @param mov the movie to remove from the list
	 */
	
	public final void rmWatchListMovie(final MovieDb mov) {
		acc.removeFromWatchList(tkn, aid, mov.getId(), MediaType.MOVIE);
	}

}
