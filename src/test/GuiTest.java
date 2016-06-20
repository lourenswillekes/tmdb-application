package test;

import java.io.IOException;

import javax.swing.AbstractButton;

import org.junit.Test;

import source.GUI;


/**
 * UnitTest Class to test GUI.
 * @author Lourens W
 */
public class GuiTest {

	/**
	 * Tests the constructor for GUI.
	 * @throws IOException e
	 */
	@Test
	public final void testGUI() throws IOException {
		new GUI();
	}

	/**
	 * Tests the reselect button function.
	 */
	@Test
	public final void testReSelectButton() {
		GUI.reSelectButton();
	}

	/**
	 * sets all button selection states to false.
	 */
	@Test
	public final void testSetButtonsFalse() {
		GUI.setButtonsFalse();
	}
	
	/**
	 * Triggers the ButtonListeners to test the movie list functions.
	 * @throws IOException 
	 */
	@Test
	public final void testGetMovieMethods() throws IOException {
		
		GUI g = new GUI();
		
		AbstractButton abRandom     = g.btnSelectRandom;
		AbstractButton abNowPlaying = g.tglbtnNowPlaying;
		AbstractButton abPopular    = g.tglbtnPopular;
		AbstractButton abTopRated   = g.tglbtnTopRated;
		AbstractButton abUpcoming   = g.tglbtnUpcoming;
		AbstractButton abSearch     = g.tglbtnSearch;
		AbstractButton abFavorites  = g.tglbtnFavorites;
		AbstractButton abWatchList  = g.tglbtnWatchList;
		AbstractButton abAddRmFav   = g.btnAddRmFav;
		AbstractButton abAddRmWatch = g.btnAddRmWatch;
		
		abNowPlaying.doClick();
		abRandom.doClick();
		
		abPopular.doClick();
		abRandom.doClick();
		
		abTopRated.doClick();
		abRandom.doClick();
		
		abSearch.doClick();
		//Type "johnny walker"
		abRandom.doClick();
		
		abUpcoming.doClick();
		abRandom.doClick();
		
		abFavorites.doClick();
		abAddRmFav.doClick();
		abRandom.doClick();
		
		abWatchList.doClick();
		abAddRmWatch.doClick();
		abRandom.doClick();
	}
	

}
