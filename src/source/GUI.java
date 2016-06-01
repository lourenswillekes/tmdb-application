package source;

import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * GUI Class.
 * @author Caleb H, Justin N, Lourens W
 * May 2016
 */
public class GUI {
	/** Instance of ApiFunctions interface. */
	private static ApiFunctions api;
	
	/** GUI frame. */
	private static JFrame frame;
	
	/** Add to favorites button (Release #2). */
	//private static JButton btnAddFav;
	
	/** Add to watch list button (Release #2). */
	//private static JButton btnAddWatch;
	
	/** Select random movie from list button. */
	private static JButton btnSelectRandom;
	
	/** Movie info label. */
	private static JLabel lblMovieInfo;
	
	/** Plot overview label. */
	private static JLabel lblPlotOverview;
	
	/** Label for indicating which tab is displayed in the table. */
	private static JLabel lblTab;
	
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
	private static JToggleButton tglbtnNowPlaying;
	
	/** Popular movies toggle button. */
	private static JToggleButton tglbtnPopular;
	
	/** Search for movies toggle button. */
	private static JToggleButton tglbtnSearch;
	
	/** Top rated movies toggle button. */
	private static JToggleButton tglbtnTopRated;
	
	/** Upcoming movies toggle button. */
	private static JToggleButton tglbtnUpcoming;
	
	/** Stores list of movies from movie database. */
	private static List<MovieDb> movieList;
	
	/** Label for displaying string. */
	private static String lblString;
	
	/** TMDb API object used for accessing the database. */
	private static TmdbApi tmdbApi;
	
	/**
	 * Launch the application.
	 * @param args vars
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tmdbApi = new TmdbApi("34b0b2ee2ac7865db7bd356da1221847");
					new GUI();
					GUI.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public GUI() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		
		api = new ApiFunctions();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 943, 545);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		createGUI();
		
		movieList = api.getNowPlayingMovies(1);
		fillMovieTable(movieList);
		lblString = new String("Top 20 Now Playing Films");
		lblTab.setText(lblString);
		
		String a = tblMovieList.getValueAt(0, 4).toString();
    	int b = Integer.parseInt(a);
    	
   
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
		fillMovieTable(movieList);
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
	    
	   	lblMoviePoster.setBounds(528, 10, 185, 307);
	   	frame.getContentPane().add(lblMoviePoster); 	
	}
	
	
	/**
	 * Displays the information for the selected movie.
	 * @param movieID of the selected movie to be displayed
	 * @throws IOException e
	 */
	private static void displayMovieInfo(final int movieID) throws IOException {
		
		TmdbMovies tmdbMovies = tmdbApi.getMovies();
		
		// Retrieve information on a movie given movie id
		MovieDb movie = tmdbMovies.getMovie(movieID, "en");
		
		// Display movie poster
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
		
		// Display movie backdrop
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
				943, 545, Image.SCALE_SMOOTH);
		
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
		
		// Now Playing Button
		tglbtnNowPlaying = new JToggleButton("Now Playing");
		tglbtnNowPlaying.setBounds(0, 0, 100, 32);
		tglbtnNowPlaying.setFont(new Font("Tahoma", Font.PLAIN, 10));
		frame.getContentPane().add(tglbtnNowPlaying);
		
		// Upcoming Button
		tglbtnUpcoming = new JToggleButton("Upcoming");
		tglbtnUpcoming.setBounds(99, 0, 100, 32);
		tglbtnUpcoming.setFont(new Font("Tahoma", Font.PLAIN, 10));
		frame.getContentPane().add(tglbtnUpcoming);
		
		// Top Rated Button
		tglbtnTopRated = new JToggleButton("Top Rated");
		tglbtnTopRated.setBounds(196, 0, 100, 32);
		tglbtnTopRated.setFont(new Font("Tahoma", Font.PLAIN, 10));
		frame.getContentPane().add(tglbtnTopRated);
		
		// Popular Button
		tglbtnPopular = new JToggleButton("Popular");
		tglbtnPopular.setBounds(295, 0, 100, 32);
		tglbtnPopular.setFont(new Font("Tahoma", Font.PLAIN, 10));
		frame.getContentPane().add(tglbtnPopular);
		
		// Search Button
		tglbtnSearch = new JToggleButton("Search");
		tglbtnSearch.setBounds(393, 0, 100, 32);
		tglbtnSearch.setFont(new Font("Tahoma", Font.PLAIN, 10));
		frame.getContentPane().add(tglbtnSearch);
		
		
		// Select Random Button
		btnSelectRandom = new JButton("Select Random");
		btnSelectRandom.setBounds(353, 453, 164, 32);
		btnSelectRandom.setFont(new Font("Modern No. 20", Font.BOLD, 14));
		frame.getContentPane().add(btnSelectRandom);
		
		// Add to Favorites Button (Release #2)
		/*
		btnAddFav = new JButton("Add To Favorites");
		btnAddFav.setBounds(527, 453, 170, 32);
		btnAddFav.setFont(new Font("Modern No. 20", Font.BOLD, 14));
		frame.getContentPane().add(btnAddFav);
		*/
		
		// Add to Watchlist Button (Release #2)
		/*
		btnAddWatch = new JButton("Add To Watchlist");
		btnAddWatch.setBounds(733, 453, 170, 32);
		btnAddWatch.setFont(new Font("Modern No. 20", Font.BOLD, 14));
		frame.getContentPane().add(btnAddWatch);
		*/ 
		
		// Plot Overview Scroll/Text Pane
		spPlotOverview = new JScrollPane();
		spPlotOverview.setBounds(527, 344, 374, 98);
		frame.getContentPane().add(spPlotOverview);
		txtPlotOverview = new JTextPane();
		txtPlotOverview.setBackground(new Color(65, 105, 225));
		txtPlotOverview.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtPlotOverview.setOpaque(false);
		txtPlotOverview.setForeground(Color.BLACK);
		spPlotOverview.setViewportView(txtPlotOverview);
		spPlotOverview.setOpaque(false);
		spPlotOverview.getViewport().setOpaque(false);
		
		// Movie Info Scroll/Text Pane
		spMovieInfo = new JScrollPane();
		spMovieInfo.setBounds(723, 39, 180, 276);
		frame.getContentPane().add(spMovieInfo);
		txtMovieInfo = new JTextPane();
		txtMovieInfo.setBackground(new Color(65, 105, 225));
		txtMovieInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtMovieInfo.setOpaque(false);
		txtMovieInfo.setForeground(Color.BLACK);
		spMovieInfo.setViewportView(txtMovieInfo);
		spMovieInfo.setOpaque(false);
		spMovieInfo.getViewport().setOpaque(false);
		spMovieList = new JScrollPane();
		spMovieList.setBounds(10, 100, 501, 328);
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
		lblMovieInfo.setBounds(723, 11, 149, 25);
		frame.getContentPane().add(lblMovieInfo);
		
		// Plot Overview Label
		lblPlotOverview = new JLabel("Plot Overview");
		lblPlotOverview.setForeground(Color.BLACK);
		lblPlotOverview.setFont(new Font("Cooper Black", Font.BOLD, 20));
		lblPlotOverview.setBounds(527, 316, 186, 25);
		frame.getContentPane().add(lblPlotOverview);
		
		// Now Playing Button Listener
		tglbtnNowPlaying.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				lblString = "Top 20 Now Playing Films";
				movieList = api.getNowPlayingMovies(1);
				
				setButtonsFalse();
				tglbtnNowPlaying.setSelected(true);
				lblTab.setText(lblString);	
				fillMovieTable(movieList);
			}
		});
		
		// Upcoming Button Listener
		tglbtnUpcoming.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				lblString = "Top 20 Upcoming Films";
				movieList = api.getUpcomingMovies(1);
				
				setButtonsFalse();
				tglbtnUpcoming.setSelected(true);
				lblTab.setText(lblString);
				fillMovieTable(movieList);
			}
		});
				
		// Top Rated Button Listener
		tglbtnTopRated.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				lblString = "Top 20 Top Rated Films";
				movieList = api.getTopRatedMovies(1);
				
				setButtonsFalse();
				tglbtnTopRated.setSelected(true);
				lblTab.setText(lblString);	
				fillMovieTable(movieList);
			}
		});
		
		// Popular Button Listener
		tglbtnPopular.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				lblString = "Top 20 Popular Films";
				movieList = api.getPopularMovies(1);
				
				setButtonsFalse();
				tglbtnPopular.setSelected(true);
				lblTab.setText(lblString);	
				fillMovieTable(movieList);
			}
		});
		
		// Search Button Listener
		tglbtnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				String inputValue = JOptionPane.showInputDialog("Search Title");
				
				if (inputValue != null && !inputValue.equals("")) {
					lblString = "Top 20 Results From Search";
					
					movieList = api.getSearchRes(inputValue, 1);
				
					setButtonsFalse();
					tglbtnSearch.setSelected(true);
					lblTab.setText(lblString);
					fillMovieTable(movieList);
				} else {
					setButtonsFalse();
					reSelectButton();
				}
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
					fillMovieTable(movieList);
	        	}
	        }
	    });
		
		// Select Random Button Listener
		btnSelectRandom.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				
				Random rand = new Random();
				
			    int randNum = rand.nextInt((movieList.size() - 0) + 1);
			    
			    String movieID = tblMovieList.getValueAt(randNum, 4).toString();
			    
			    if (!movieID.equals("")) {
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
					fillMovieTable(movieList);
	        	}
			}
		});
		
		// Add to Favorites Button Listener (Release #2)
		/*
		btnAddFav.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				// TODO: Add selected movie to Favorites. RELEASE 2.
			}
		});
		*/
		
		// Add to Watchlist Button Listener (Release #2)
		/*
		btnAddWatch.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				// TODO: Add selected movie to Watchlist. RELEASE 2.
			}
		});
		*/
	}	
}
