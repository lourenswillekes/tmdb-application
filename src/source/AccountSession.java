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
	
	/** Declares MovieDb for selected movie. */
	private MovieDb selected;
	
	
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
	@Override
	public final List<MovieDb> getFavorites() {
		List<MovieDb> fav = acc.getFavoriteMovies(tkn, aid).getResults();
		return fav;
	}

	/**
	 * This method returns the user's watchlist.
	 * @return movies on the watchlist list
	 */
	@Override
	public final List<MovieDb> getWatchList() {
		List<MovieDb> wat = acc.getWatchListMovies(tkn, aid, 1).getResults();
		return wat;
	}

	/**
	 * This method returns the requested movie from the favorites.
	 */
	@Override
	public final void addFavoritesMovie() {
		acc.addFavorite(tkn, aid, selected.getId(), MediaType.MOVIE);
	}

	/**
	 * This method returns the requested movie from the watchlist.
	 */
	@Override
	public final void addWatchListMovie() {
		acc.addToWatchList(tkn, aid, selected.getId(), MediaType.MOVIE);
	}

	/**
	 * This method removes the requested movie from the favorites.
	 */
	@Override
	public final void rmFavoritesMovie() {
		acc.removeFavorite(tkn, aid, selected.getId(), MediaType.MOVIE);
	}

	/**
	 * This method removes the requested movie from the watchlist.
	 */
	@Override
	public final void rmWatchListMovie() {
		acc.removeFromWatchList(tkn, aid, selected.getId(), MediaType.MOVIE);
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
		List<MovieDb> fav = this.getFavorites();
		return fav.contains(selected);
	}

	/**
	 * This method returns true if the selected movie is in the watchlist list.
	 * @return true if found, else false
	 */
	@Override
	public final boolean isSelectedWatchList() {
		List<MovieDb> wat = this.getWatchList();
		return wat.contains(selected);
	}

}
