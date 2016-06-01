package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
	 * Creates static GUI and calls Main.
	 */
	@Test
	public final void testMain() {
		GUI.main(new String[] {});
	}

	/**
	 * Tests the constructor for GUI.
	 */
	@Test
	public final void testGUI() {
		try {
			new GUI();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	 * creates the GUI.
	 */
	@Test
	public final void testCreateGUI() {
		try {
			GUI.createGUI();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		
	}
	

}
