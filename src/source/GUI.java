package source;

import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.Video;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

/**
 * GUI Class.
 * @author Caleb H, Justin N, Lourens W
 * May 2016
 */
public class GUI {
	/** Instance of ApiFunctions interface. */
	private static ApiFunctions api;
	
	/** Declares the session. */
	private static ISession session;
	
	/** GUI frame. */
	private static JFrame frame;
	
	/** Watch trailer button */
	public static JButton btnWatchTrailer;
	
	/** Add to favorites button (Release #2). */
	private static JButton btnAddRmFav;
	
	/** Add to watch list button (Release #2). */
	private static JButton btnAddRmWatch;
	
	/** Select random movie from list button. */
	public static JButton btnSelectRandom;
	
	/** Button used to Login. */
	public static JButton btnLogin;
	
	/** Movie info label. */
	private static JLabel lblMovieInfo;
	
	/** Plot overview label. */
	private static JLabel lblPlotOverview;
	
	/** Label for indicating which tab is displayed in the table. */
	private static JLabel lblTab;
	
	/** Label used to represent the UserName of who is logged in*/
	private static JLabel lblUserName;
	
	/** Movie info scroll pane. */
	private static JScrollPane spMovieInfo;
	
	/** Movie list scroll pane. */
	private static JScrollPane spMovieList;
	
	/** Plot overview scroll pane. */
	private static JScrollPane spPlotOverview;
	
	/** Movie list table. */
	private static JTable tblMovieList;
	
	/** Movie info text pane. */
	private static JTextPane txtMovieInfo;
	
	/** Plot overview text pane. */
	private static JTextPane txtPlotOverview;
	
	/** Now playing movies in theaters toggle button. */
	public static JToggleButton tglbtnNowPlaying;
	
	/** Popular movies toggle button. */
	public static JToggleButton tglbtnPopular;
	
	/** Search for movies toggle button. */
	public static JToggleButton tglbtnSearch;
	
	/** Top rated movies toggle button. */
	public static JToggleButton tglbtnTopRated;
	
	/** Upcoming movies toggle button. */
	public static JToggleButton tglbtnUpcoming;
	
	/** Label for displaying string. */
	private static String lblString;
	
	/** TMDb API object used for accessing the database. */
	private static TmdbApi tmdbApi
		= new TmdbApi("34b0b2ee2ac7865db7bd356da1221847");
	
	/** An Login object to access the Login J Dialog. */
	static LogIn logIn;
	
	/** Favorites movies toggle button. */
	public static JToggleButton tglbtnFavorites;
	
	/** Watchlist movies toggle button. */
	public static JToggleButton tglbtnWatchList;
	
	/** TMDb Movies object for accessing all movies */
	public static TmdbMovies tmdbMovies;
	
	/**
	 * Launch the application.
	 * @param args vars
	 */
	public static void main(final String[] args) {
		NativeInterface.open();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new GUI();
					GUI.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		NativeInterface.runEventPump();
		// don't forget to properly close native components
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		    @Override
		    public void run() {
		    	NativeInterface.close();
		    }
		}));
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public GUI() throws IOException {
		
		LoadLoginFrame();
		initialize();
	}
	
	private void LoadLoginFrame()
	{
		api = new ApiFunctions();
		api.setPassword(null);
		api.setUserName(null);
		session = new GuestSession();
		
		logIn = new LogIn(api);
		
		if(api.getUserName() != null && api.getPassword() != null)
		{
			session = new AccountSession(api.getUserName(), api.getPassword());
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		
		//api = new ApiFunctions();
		
		//session = new GuestSession();
		
		tmdbMovies = tmdbApi.getMovies();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 950, 575);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		createGUI();
		
		api.setMovieList(api.getNowPlayingMovies(1));
		fillMovieTable(api.getMovieList());
		lblString = new String("Top 20 Now Playing Films");
		lblTab.setText(lblString);
		
		String a = tblMovieList.getValueAt(0, 4).toString();
    	int b = Integer.parseInt(a);
    	
    	session.setSelectedMovie(tmdbApi.getMovies().getMovie(b, "en"));
    	
   
    	frame.getContentPane().removeAll();
		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();
		
		try {
			displayMovieInfo(b);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		reSelectButton();
		lblTab.setText(lblString);
		fillMovieTable(api.getMovieList());
	}
	
	/**
	 * Sets the movie poster based on the selected movie.
	 * @param posterPath URL of the movie poster requested
	 * @throws IOException e
	 */
	public static void setMoviePoster(
			final String posterPath) throws IOException {
		URL posterURL = new URL(posterPath);
		BufferedImage poster = ImageIO.read(posterURL);
		Image scaled = poster.getScaledInstance(185, 307, Image.SCALE_SMOOTH);
    	JLabel lblMoviePoster = new JLabel(new ImageIcon(scaled));
	    
	   	lblMoviePoster.setBounds(528, 50, 185, 307);
	   	frame.getContentPane().add(lblMoviePoster); 	
	}
	
	
	/**
	 * Displays the information for the selected movie.
	 * @param movieID of the selected movie to be displayed
	 * @throws IOException e
	 */
	private static void displayMovieInfo(final int movieID) throws IOException {
		
		// Retrieve information on a movie given movie id
		MovieDb movie = tmdbMovies.getMovie(movieID, "en");
		
		// Display movie backdrop
		try {
			String backdropPath = "https://image.tmdb.org/t/p/original"
					+ movie.getBackdropPath();
			
			if (backdropPath.equals("https://image.tmdb.org/t/p/"
					+ "originalnull")) {
				backdropPath = "http://www.ipages.am/files/companies/1476/"
						+ "template/bg/Blue-movie-film-strip-backgrounds.jpg";
			}
			setMovieBackdrop(backdropPath);
		} catch (Exception e) {
			
			System.out.println("No backDrop");
			e.printStackTrace();
		}											
		
		// Display movie poster
		try {
			String posterPath = "https://image.tmdb.org/t/p/original"
					+ movie.getPosterPath();
			
			if (posterPath.equals("https://image.tmdb.org/t/p/originalnull")) {
				posterPath = "https://cdn.amctheatres.com/Media/Default/"
						+ "Images/noposter.jpg";
			}
			setMoviePoster(posterPath);
		} catch (Exception e) {
			System.out.println("No Poster");
			e.printStackTrace();
		}
		
		// Re-create GUI
		createGUI();
		
		setAddRmBtnTxt();
		txtMovieInfo.setText(api.getMovieInfo(movie.getId()));
		txtPlotOverview.setText(movie.getOverview());
		txtMovieInfo.setCaretPosition(0);
		txtPlotOverview.setCaretPosition(0);
	}
	
	/**
	 * Used to re-select the toggle button selected when the GUI is reset
	 *   for a background change.
	 */
	public static void reSelectButton() {
		if (lblString.equals("Top 20 Now Playing Films")) {
			tglbtnNowPlaying.setSelected(true);
		}
		if (lblString.equals("Top 20 Popular Films")) {
			tglbtnPopular.setSelected(true);
		}
		if (lblString.equals("Top 20 Top Rated Films")) {
			tglbtnTopRated.setSelected(true);
		}
		if (lblString.equals("Top 20 Upcoming Films")) {
			tglbtnUpcoming.setSelected(true);
		}
		if (lblString.equals("Top 20 Results From Search")) {
			tglbtnSearch.setSelected(true);
		}
		if (lblString.equals("Top 20 Favorite Movies")) {
			tglbtnFavorites.setSelected(true);
		}
		if (lblString.equals("Top 20 WatchList Movies")) {
			tglbtnWatchList.setSelected(true);
		}
	}
	
	/**
	 * Sets the background as the movie backdrop.
	 * @param backdropPath URL to image
	 * @throws IOException e
	 */
	public static void setMovieBackdrop(final String backdropPath)
			throws IOException {
		URL backdropURL = new URL(backdropPath);
		
		BufferedImage backdrop = ImageIO.read(backdropURL);
	    
		RescaleOp rescaleOp = new RescaleOp(1.2f, 150, null);
		rescaleOp.filter(backdrop, backdrop);
		
		Image scaledBackdrop = backdrop.getScaledInstance(
				950, 575, Image.SCALE_SMOOTH);
		
	    JLabel lblBackdrop = new JLabel(new ImageIcon(scaledBackdrop));
	    
	    frame.setContentPane(lblBackdrop);  
	}
	
	
	/**
	 * Fills the table with the requested movies.
	 * @param mov is the list returned from the API
	 */
	public static void fillMovieTable(final List<MovieDb> mov) {
		int j;
		
		for (j = 0; j < mov.size(); j++) {
			
			tblMovieList.getModel().setValueAt(mov.get(j).
												getTitle(), j, 1);
			tblMovieList.getModel().setValueAt(mov.get(j).
												getReleaseDate(), j, 2);
			tblMovieList.getModel().setValueAt(mov.get(j).
												getVoteAverage(), j, 3);
			tblMovieList.getModel().setValueAt(mov.get(j).
												getId(), j, 4);
		}
		if (mov.size() != 20) {
			for (; j < 20; j++) {
				
				tblMovieList.getModel().setValueAt("", j, 1);
				tblMovieList.getModel().setValueAt("", j, 2);
				tblMovieList.getModel().setValueAt("", j, 3);
				tblMovieList.getModel().setValueAt("", j, 4);
			}
		}
	}
	
	/**
	 * Sets the state of all buttons to unselected.
	 */
	public static void setButtonsFalse() {
		tglbtnNowPlaying.setSelected(false);
		tglbtnPopular.setSelected(false);
		tglbtnTopRated.setSelected(false);
		tglbtnUpcoming.setSelected(false);
		tglbtnTopRated.setSelected(false);
		tglbtnSearch.setSelected(false);
		tglbtnFavorites.setSelected(false);
		tglbtnWatchList.setSelected(false);
	}
	
	/**
	 * Sets the text on the add/rm buttons based on selected movie.
	 */
	public static void setAddRmBtnTxt(){
		if (session.isSelectedFavorites()) {
			btnAddRmFav.setText("Rm From Favorites");
		} else {
			btnAddRmFav.setText("Add To Favorites");
		}
		if (session.isSelectedWatchList()) {
			btnAddRmWatch.setText("Rm From WatchList");
		} else {
			btnAddRmWatch.setText("Add To WatchList");
		}
	}
	
	/**
	 * Opens a new JFrame and plays the trailer in a separate thread
	 * @param path youtube path to trailer
	 */
	public static void displayTrailer(String path) {
		
		new Thread(new Runnable() {
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					JFrame trailer = new JFrame();
					trailer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					trailer.getContentPane().add(getBrowserPanel(path), BorderLayout.CENTER);
					trailer.setSize(800, 600);
					trailer.setLocationByPlatform(true);
					trailer.setVisible(true);
					}
				});
			}
		}).start(); 
	}
	
	/**
	 * Gets web browser JPanel for displaying trailer
	 * @param path Movie trailer path
	 * @return Web Browser JPanel
	 */
	public static JPanel getBrowserPanel(String path) {
	    JPanel webBrowserPanel = new JPanel(new BorderLayout());
	    JWebBrowser webBrowser = new JWebBrowser();
	    webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
	    webBrowser.setBarsVisible(false);
	    webBrowser.navigate(path);
	    return webBrowserPanel;
	}
	
	/**
	 * Used to create the GUI.
	 * @throws IOException e
	 */
	public static void createGUI() throws IOException {
		
		lblTab = new JLabel("");
		lblTab.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		lblTab.setBounds(10, 60, 356, 29);
		frame.getContentPane().add(lblTab);
		
		lblUserName = new JLabel(api.getaccountName());
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUserName.setBounds(820, 25, 356, 29);
		frame.getContentPane().add(lblUserName);
		
		// Now Playing Button
		tglbtnNowPlaying = new JToggleButton("Now Playing");
		tglbtnNowPlaying.setBounds(0, 0, 117, 32);
		tglbtnNowPlaying.setFont(new Font("Tahoma", Font.PLAIN, 10));
		frame.getContentPane().add(tglbtnNowPlaying);
		
		// Upcoming Button
		tglbtnUpcoming = new JToggleButton("Upcoming");
		tglbtnUpcoming.setBounds(117, 0, 117, 32);
		tglbtnUpcoming.setFont(new Font("Tahoma", Font.PLAIN, 10));
		frame.getContentPane().add(tglbtnUpcoming);
		
		// Top Rated Button
		tglbtnTopRated = new JToggleButton("Top Rated");
		tglbtnTopRated.setBounds(234, 0, 117, 32);
		tglbtnTopRated.setFont(new Font("Tahoma", Font.PLAIN, 10));
		frame.getContentPane().add(tglbtnTopRated);
		
		// Popular Button
		tglbtnPopular = new JToggleButton("Popular");
		tglbtnPopular.setBounds(351, 0, 117, 32);
		tglbtnPopular.setFont(new Font("Tahoma", Font.PLAIN, 10));
		frame.getContentPane().add(tglbtnPopular);
		
		// Search Button
		tglbtnSearch = new JToggleButton("Search");
		tglbtnSearch.setBounds(468, 0, 117, 32);
		tglbtnSearch.setFont(new Font("Tahoma", Font.PLAIN, 10));
		frame.getContentPane().add(tglbtnSearch);
		
		
		// Select Random Button
		btnSelectRandom = new JButton("Select Random");
		btnSelectRandom.setBounds(160, 493, 164, 32);
		btnSelectRandom.setFont(new Font("Modern No. 20", Font.BOLD, 14));
		frame.getContentPane().add(btnSelectRandom);
		
		// Watch Trailer Button (Release #2)
		btnWatchTrailer = new JButton("Watch Trailer");
		btnWatchTrailer.setBounds(728, 325, 170, 32);
		btnWatchTrailer.setFont(new Font("Modern No. 20", Font.BOLD, 14));
		frame.getContentPane().add(btnWatchTrailer);
		
		// Add/Rm Favorites Button (Release #2)
		btnAddRmFav = new JButton("Add To Favorites");
		btnAddRmFav.setBounds(527, 493, 170, 32);
		btnAddRmFav.setFont(new Font("Modern No. 20", Font.BOLD, 14));
		frame.getContentPane().add(btnAddRmFav);
		
		// Add/Rm Watchlist Button (Release #2)
		btnAddRmWatch = new JButton("Add To Watchlist");
		btnAddRmWatch.setBounds(733, 493, 170, 32);
		btnAddRmWatch.setFont(new Font("Modern No. 20", Font.BOLD, 14));
		frame.getContentPane().add(btnAddRmWatch); 
		
		tglbtnFavorites = new JToggleButton("Favorites");
		tglbtnFavorites.setBounds(585, 0, 117, 32);
		tglbtnFavorites.setFont(new Font("Tahoma", Font.PLAIN, 10));
		frame.getContentPane().add(tglbtnFavorites);
		
		tglbtnWatchList = new JToggleButton("Watchlist");
		tglbtnWatchList.setBounds(702, 0, 117, 32);
		tglbtnWatchList.setFont(new Font("Tahoma", Font.PLAIN, 10));
		frame.getContentPane().add(tglbtnWatchList);
		
		
		// Login Button
		btnLogin = new JButton("");
		btnLogin.setBounds(820, 0, 110, 32);
		String a = new String("http://www.bcwood.com/wp-content/uploads/2012/08/login_button_01.jpg");
		URL posterURL = new URL(a);
		BufferedImage poster = ImageIO.read(posterURL);
		Image scaled = poster.getScaledInstance(110, 32, Image.SCALE_SMOOTH);
		btnLogin.setIcon(new ImageIcon(scaled));
		btnLogin.setBorderPainted(false);
		btnLogin.setOpaque(false);
		btnLogin.setContentAreaFilled(false);
		frame.getContentPane().add(btnLogin);
		
		// Plot Overview Scroll/Text Pane
		spPlotOverview = new JScrollPane();
		spPlotOverview.setBounds(527, 384, 374, 98);
		frame.getContentPane().add(spPlotOverview);
		txtPlotOverview = new JTextPane();
		txtPlotOverview.setBackground(new Color(65, 105, 225));
		txtPlotOverview.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtPlotOverview.setOpaque(false);
		txtPlotOverview.setForeground(Color.BLACK);
		txtPlotOverview.setEditable(false);
		spPlotOverview.setViewportView(txtPlotOverview);
		spPlotOverview.setOpaque(false);
		spPlotOverview.getViewport().setOpaque(false);
		
		// Movie Info Scroll/Text Pane
		spMovieInfo = new JScrollPane();
		spMovieInfo.setBounds(723, 79, 180, 235);
		frame.getContentPane().add(spMovieInfo);
		txtMovieInfo = new JTextPane();
		txtMovieInfo.setBackground(new Color(65, 105, 225));
		txtMovieInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtMovieInfo.setOpaque(false);
		txtMovieInfo.setForeground(Color.BLACK);
		txtMovieInfo.setEditable(false);
		spMovieInfo.setViewportView(txtMovieInfo);
		spMovieInfo.setOpaque(false);
		spMovieInfo.getViewport().setOpaque(false);
		
		// Movie List Scroll Pane
		spMovieList = new JScrollPane();
		spMovieList.setBounds(10, 100, 501, 378);
		frame.getContentPane().add(spMovieList);
		
		// Movie List Table
		tblMovieList = new JTable();
		tblMovieList.setBackground(Color.WHITE);
		tblMovieList.setFont(new Font("Tahoma", Font.BOLD, 14));
		tblMovieList.setModel(new DefaultTableModel(
				new Object[][] {
					{new Integer(1), null, null, null, null},
					{new Integer(2), null, null, null, null},
					{new Integer(3), null, null, null, null},
					{new Integer(4), null, null, null, null},
					{new Integer(5), null, null, null, null},
					{new Integer(6), null, null, null, null},
					{new Integer(7), null, null, null, null},
					{new Integer(8), null, null, null, null},
					{new Integer(9), null, null, null, null},
					{new Integer(10), null, null, null, null},
					{new Integer(11), null, null, null, null},
					{new Integer(12), null, null, null, null},
					{new Integer(13), null, null, null, null},
					{new Integer(14), null, null, null, null},
					{new Integer(15), null, null, null, null},
					{new Integer(16), null, null, null, null},
					{new Integer(17), null, null, null, null},
					{new Integer(18), null, null, null, null},
					{new Integer(19), null, null, null, null},
					{new Integer(20), null, null, null, null},
				},
				new String[] {
					"", "Title", "Release Date", "Rating", ""
				}
			) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				private Class[] columnTypes = new Class[] {
					Integer.class, String.class, String.class, 
					Object.class, Integer.class
				};
				public Class getColumnClass(final int columnIndex) {
					return columnTypes[columnIndex];
				}
				private boolean[] columnEditables = new boolean[] {
						false, false, false, false
				};
				public boolean isCellEditable(final int row, final int column) {
					return columnEditables[column];
				}
			}
		);
		
		// Movie List Table Properties
		DefaultTableCellRenderer renderCenter = new DefaultTableCellRenderer();
		renderCenter.setHorizontalAlignment(SwingConstants.CENTER);
		renderCenter.setOpaque(false);
		
		DefaultTableCellRenderer renderLeft = new DefaultTableCellRenderer();
		renderLeft.setHorizontalAlignment(SwingConstants.LEFT);
		renderLeft.setOpaque(false);
		
		tblMovieList.getColumnModel().getColumn(0).setResizable(false);
		tblMovieList.getColumnModel().getColumn(0).setPreferredWidth(23);
		tblMovieList.getColumnModel().getColumn(0).setMaxWidth(23);
		tblMovieList.getColumnModel().getColumn(0).setMinWidth(23);
		tblMovieList.getColumnModel().getColumn(0).setCellRenderer(
															renderCenter);
		tblMovieList.getColumnModel().getColumn(1).setResizable(false);
		tblMovieList.getColumnModel().getColumn(1).setPreferredWidth(250);
		tblMovieList.getColumnModel().getColumn(1).setMinWidth(250);
		tblMovieList.getColumnModel().getColumn(1).setMaxWidth(600);
		tblMovieList.getColumnModel().getColumn(1).setCellRenderer(renderLeft);
		tblMovieList.getColumnModel().getColumn(2).setResizable(false);
		tblMovieList.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblMovieList.getColumnModel().getColumn(2).setMinWidth(100);
		tblMovieList.getColumnModel().getColumn(2).setMaxWidth(100);
		tblMovieList.getColumnModel().getColumn(2).setCellRenderer(
															renderCenter);
		tblMovieList.getColumnModel().getColumn(3).setResizable(false);
		tblMovieList.getColumnModel().getColumn(3).setPreferredWidth(100);
		tblMovieList.getColumnModel().getColumn(3).setMinWidth(75);
		tblMovieList.getColumnModel().getColumn(3).setMaxWidth(75);
		tblMovieList.getColumnModel().getColumn(3).setCellRenderer(
															renderCenter);
		tblMovieList.getColumnModel().getColumn(4).setResizable(false);
		tblMovieList.getColumnModel().getColumn(4).setPreferredWidth(0);
		tblMovieList.getColumnModel().getColumn(4).setMinWidth(0);
		tblMovieList.getColumnModel().getColumn(4).setMaxWidth(0);
		tblMovieList.setRowHeight(30);
		tblMovieList.getTableHeader().setReorderingAllowed(false);
		tblMovieList.setOpaque(false);
		tblMovieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		((DefaultTableCellRenderer) tblMovieList.getDefaultRenderer(
				Object.class)).setOpaque(false);
		
		// Movie List Scroll Pane
		spMovieList.setViewportView(tblMovieList);
		spMovieList.setOpaque(false);
		spMovieList.getViewport().setOpaque(false);
		
		// Movie Info Label
		lblMovieInfo = new JLabel("Movie Info");
		lblMovieInfo.setForeground(Color.BLACK);
		lblMovieInfo.setFont(new Font("Cooper Black", Font.BOLD, 20));
		lblMovieInfo.setBounds(723, 51, 149, 25);
		frame.getContentPane().add(lblMovieInfo);
		
		// Plot Overview Label
		lblPlotOverview = new JLabel("Plot Overview");
		lblPlotOverview.setForeground(Color.BLACK);
		lblPlotOverview.setFont(new Font("Cooper Black", Font.BOLD, 20));
		lblPlotOverview.setBounds(527, 356, 186, 25);
		frame.getContentPane().add(lblPlotOverview);
		
		// Now Playing Button Listener
		tglbtnNowPlaying.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				lblString = "Top 20 Now Playing Films";
				api.setMovieList(api.getNowPlayingMovies(1));
				
				setButtonsFalse();
				tglbtnNowPlaying.setSelected(true);
				lblTab.setText(lblString);	
				fillMovieTable(api.getMovieList());
			}
		});
		
		// Upcoming Button Listener
		tglbtnUpcoming.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				lblString = "Top 20 Upcoming Films";
				api.setMovieList(api.getUpcomingMovies(1));
				
				setButtonsFalse();
				tglbtnUpcoming.setSelected(true);
				lblTab.setText(lblString);
				fillMovieTable(api.getMovieList());
			}
		});
				
		// Top Rated Button Listener
		tglbtnTopRated.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				lblString = "Top 20 Top Rated Films";
				api.setMovieList(api.getTopRatedMovies(1));
				setButtonsFalse();
				tglbtnTopRated.setSelected(true);
				lblTab.setText(lblString);	
				fillMovieTable(api.getMovieList());
			}
		});
		
		// Popular Button Listener
		tglbtnPopular.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				lblString = "Top 20 Popular Films";
				api.setMovieList(api.getPopularMovies(1));
				
				setButtonsFalse();
				tglbtnPopular.setSelected(true);
				lblTab.setText(lblString);	
				fillMovieTable(api.getMovieList());
			}
		});
		
		
		// Login Button Listener
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				//logIn = new LogIn(api);
				
				final MovieDb temp = session.getSelectedMovie();
				
				session = new AccountSession("parkourlour", "passNEWword");
				session.setSelectedMovie(temp);
				setAddRmBtnTxt();
				
				lblUserName.setText("sup dude");
				
				if (lblString.equals("Top 20 Favorite Movies")) {
					api.setMovieList(session.getFavorites());
					fillMovieTable(api.getMovieList());
				}
				else if (lblString.equals("Top 20 WatchList Movies")) {
					api.setMovieList(session.getWatchList());
					fillMovieTable(api.getMovieList());
				} 
			}
		});
		
		// Search Button Listener
		tglbtnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				String inputValue = JOptionPane.showInputDialog("Search Title");
				
				if (inputValue != null && !inputValue.equals("")) {
					lblString = "Top 20 Results From Search";
					
					api.setMovieList(api.getSearchRes(inputValue, 1));
				
					setButtonsFalse();
					tglbtnSearch.setSelected(true);
					lblTab.setText(lblString);
					fillMovieTable(api.getMovieList());
				} else {
					setButtonsFalse();
					reSelectButton();
				}
			}
		});
		
		// Favorites Button Listener
		tglbtnFavorites.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				lblString = "Top 20 Favorite Movies";
				api.setMovieList(session.getFavorites());
				
				setButtonsFalse();
				tglbtnFavorites.setSelected(true);
				lblTab.setText(lblString);	
				fillMovieTable(api.getMovieList());
			}
		});
				
		// WatchList Button Listener
		tglbtnWatchList.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				lblString = "Top 20 WatchList Movies";
				api.setMovieList(session.getWatchList());
				
				setButtonsFalse();
				tglbtnWatchList.setSelected(true);
				lblTab.setText(lblString);	
				fillMovieTable(api.getMovieList());
			}
		});
		
		// Movie Table Selection Listener
		tblMovieList.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
	        public void valueChanged(final ListSelectionEvent event) {
	            // do some actions here, for example
	            // print first column value from selected row
	        	String movieID = tblMovieList.getValueAt(
	        			tblMovieList.getSelectedRow(), 4).toString();
	        	
	        	if (!movieID.equals("")) {
	        		
	        		session.setSelectedMovie(tmdbApi.getMovies().getMovie(Integer.parseInt(movieID), "en"));
	        		
		        	frame.getContentPane().removeAll();
					frame.getContentPane().revalidate();
					frame.getContentPane().repaint();
					
					try {
						displayMovieInfo(Integer.parseInt(movieID));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					lblTab.setText(lblString);
					reSelectButton();
					fillMovieTable(api.getMovieList());
	        	}
	        }
	    });
		
		// Select Random Button Listener
		btnSelectRandom.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				
				Random rand = new Random();
				
			    int randNum = rand.nextInt((api.getMovieList().size() - 0) + 1);
			    
			    String movieID = tblMovieList.getValueAt(randNum, 4).toString();
			    
			    if (!movieID.equals("")) {

	        		session.setSelectedMovie(tmdbApi.getMovies().getMovie(Integer.parseInt(movieID), "en"));
	        		
	        		
		        	frame.getContentPane().removeAll();
					frame.getContentPane().revalidate();
					frame.getContentPane().repaint();
					
					try {
						displayMovieInfo(Integer.parseInt(movieID));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					lblTab.setText(lblString);
					reSelectButton();
					fillMovieTable(api.getMovieList());
	        	}
			}
		});
		
		// Watch Trailer Button Listener (Release #2)
		btnWatchTrailer.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				List<Video> videos = tmdbMovies.getVideos(session.getSelectedMovie().getId(), "en");
				if(videos != null) {
					if (0 == videos.size()) {
						System.out.println("Videos not available for this movie\n");
					} else {
						Video v = new Video();
						
						Iterator<Video> iterator = videos.iterator();
						//while (iterator.hasNext()) {
						v = iterator.next(); // Just gets first video available
						//}
						
						String trailerPath = "https://www.youtube.com/embed/" + v.getKey();
						
						displayTrailer(trailerPath);
					}
				}
			}
		});
		
		// Add to Favorites Button Listener (Release #2)
		btnAddRmFav.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (session.isSelectedFavorites()) {
					session.rmFavoritesMovie();
					btnAddRmFav.setText("Add To Favorites");
				} else {
					session.addFavoritesMovie();
					btnAddRmFav.setText("Rm From Favorites");
				}
				
				if (lblString.equals("Top 20 Favorite Movies")) {
					api.setMovieList(session.getFavorites());
					fillMovieTable(api.getMovieList());
				}
			}
		});
		
		
		// Add to Watchlist Button Listener (Release #2)
		btnAddRmWatch.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (session.isSelectedWatchList()) {
					session.rmWatchListMovie();
					btnAddRmWatch.setText("Add To WatchList");
				} else {
					session.addWatchListMovie();
					btnAddRmWatch.setText("Rm From WatchList");
				}
				
				if (lblString.equals("Top 20 WatchList Movies")) {
					api.setMovieList(session.getWatchList());
					fillMovieTable(api.getMovieList());
				}
			}
		});
		
	}	
}
