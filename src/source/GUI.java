package source;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;

import javax.swing.ListSelectionModel;

public class GUI {

	private static JFrame frame;
	private SearchGUI x;
	private static TmdbApi tmdbApi;
	
	static JLabel lblTab;
	static JButton btnSelectRandom;
	static JButton btnAddFav;
	static JTextPane txtPlotDiscription;
	static JButton btnAddWatch;
	static JLabel lblMoviePoster;
	static JScrollPane spPlotDiscription;
	static JScrollPane spMovieInfo;
	static JTextPane txtMovieInfo;
	static JToggleButton tglbtnUpcoming;
	static JToggleButton tglbtnPopular;
	static JToggleButton tglbtnTopRated;
	static JToggleButton tglbtnSearch;
	static JTable tblMovieList;
	static JToggleButton tglbtnNowPlaying;
	static JScrollPane spMovieList;
	static JLabel lblMovieInfo;
	static JLabel lblPlotDiscription;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		
		Functions api = new Functions();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 943, 545);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		CreateGUI();
		displayMovieInfo(100);
		
		btnSelectRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		btnAddFav.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		btnAddWatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		tglbtnUpcoming.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTab.setText("Top 20 Upcoming Films");
				
				List<MovieDb> popList = api.getUpcomingMovies(1);
				for(int j = 0; j < 20; j++){
					tblMovieList.getModel().setValueAt(popList.get(j).getTitle(), j, 1);
					tblMovieList.getModel().setValueAt(popList.get(j).getReleaseDate(), j, 2);
					tblMovieList.getModel().setValueAt(popList.get(j).getVoteAverage(), j, 3);
				}
			}
		});
		
		tglbtnPopular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTab.setText("Top 20 Popular Films");
				
				List<MovieDb> popList = api.getPopularMovies(1);
				for(int j = 0; j < 20; j++){
					tblMovieList.getModel().setValueAt(popList.get(j).getTitle(), j, 1);
					tblMovieList.getModel().setValueAt(popList.get(j).getReleaseDate(), j, 2);
					tblMovieList.getModel().setValueAt(popList.get(j).getVoteAverage(), j, 3);
				}

				
//				try {
//					displayMovieInfo(101);
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				
				
				
			}
		});
		
		tglbtnTopRated.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTab.setText("Top 20 Top Rated Films");
				
				//TODO: Change this to NowPlaying when we get a new .jar.
				List<MovieDb> popList = api.getPopularMovies(1);
				for(int j = 0; j < 20; j++){
					tblMovieList.getModel().setValueAt(popList.get(j).getTitle(), j, 1);
					tblMovieList.getModel().setValueAt(popList.get(j).getReleaseDate(), j, 2);
					tblMovieList.getModel().setValueAt(popList.get(j).getVoteAverage(), j, 3);
				}
				
			}
		});
		
		tglbtnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				x = new SearchGUI();
				if (x.getCloseStatus() == false) {
					
				}
				lblTab.setText("Closed");
				
				//lblTab.setText("Top 20 Searched Results");
			}
		});
		
		tglbtnNowPlaying.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTab.setText("Top 20 Now Playing Films");
				
				//TODO: Change this to NowPlaying when we get a new .jar.
				List<MovieDb> popList = api.getPopularMovies(1);
				for(int j = 0; j < 20; j++){
					tblMovieList.getModel().setValueAt(popList.get(j).getTitle(), j, 1);
					tblMovieList.getModel().setValueAt(popList.get(j).getReleaseDate(), j, 2);
					tblMovieList.getModel().setValueAt(popList.get(j).getVoteAverage(), j, 3);
				}
				
			}
		});
		
		//Add action listener for tabel
	}
	
	public static void setMoviePoster(String posterPath) throws IOException {
		
		URL posterURL = new URL(posterPath);
		
		BufferedImage poster = ImageIO.read(posterURL);
	    
		Image scaledPoster = poster.getScaledInstance(180, 276, Image.SCALE_SMOOTH);
		
    	JLabel lblMoviePoster = new JLabel(new ImageIcon(scaledPoster));
	    
	   	lblMoviePoster.setBounds(528, 39, 185, 278);
	    
	   	frame.getContentPane().add(lblMoviePoster); 	
	}
	
	private static void displayMovieInfo(int movieID) throws IOException {
		
		TmdbMovies tmdbMovies = tmdbApi.getMovies();
		
		// Retrieve information on a movie given movie id
		MovieDb movie = tmdbMovies.getMovie(movieID, "en");
		
		// Display movie poster
		String backdropPath = "https://image.tmdb.org/t/p/original" + movie.getBackdropPath();
		try {
			setMovieBackdrop(backdropPath);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		// Display movie backdrop
		String posterPath = "https://image.tmdb.org/t/p/original" + movie.getPosterPath();
		try {
			setMoviePoster(posterPath);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		CreateGUI();
		txtPlotDiscription.setText(movie.getOverview());
		
	}
	
	public static void setMovieBackdrop(String backdropPath) throws IOException {
		URL backdropURL = new URL(backdropPath);
		
		BufferedImage backdrop = ImageIO.read(backdropURL);
	    
		RescaleOp rescaleOp = new RescaleOp(1.2f, 150, null);
		rescaleOp.filter(backdrop, backdrop);
		
		Image scaledBackdrop = backdrop.getScaledInstance(943, 545, Image.SCALE_SMOOTH);
		
	    JLabel lblBackdrop = new JLabel(new ImageIcon(scaledBackdrop));
	    
	    frame.setContentPane(lblBackdrop);  
	}
	
	public static void CreateGUI() throws IOException {
		
		lblTab = new JLabel("");
		lblTab.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		lblTab.setBounds(10, 60, 356, 29);
		frame.getContentPane().add(lblTab);
	
		btnSelectRandom = new JButton("Select Random");
		btnSelectRandom.setBounds(353, 453, 164, 32);
		btnSelectRandom.setFont(new Font("Modern No. 20", Font.BOLD, 14));
		frame.getContentPane().add(btnSelectRandom);
		
		btnAddFav = new JButton("Add To Favorites");
		btnAddFav.setBounds(527, 453, 170, 32);
		btnAddFav.setFont(new Font("Modern No. 20", Font.BOLD, 14));
		frame.getContentPane().add(btnAddFav);
		
		spPlotDiscription = new JScrollPane();
		spPlotDiscription.setBounds(527, 344, 374, 98);
		frame.getContentPane().add(spPlotDiscription);
		
		txtPlotDiscription = new JTextPane();
		txtPlotDiscription.setBackground(new Color(65, 105, 225));
		txtPlotDiscription.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtPlotDiscription.setOpaque(false);
		txtPlotDiscription.setForeground(Color.BLACK);
		spPlotDiscription.setViewportView(txtPlotDiscription);
		spPlotDiscription.setOpaque(false);
		spPlotDiscription.getViewport().setOpaque(false);
		
		btnAddWatch = new JButton("Add To Watchlist");
		btnAddWatch.setBounds(733, 453, 170, 32);
		btnAddWatch.setFont(new Font("Modern No. 20", Font.BOLD, 14));
		frame.getContentPane().add(btnAddWatch);
		
		lblMoviePoster = new JLabel("Movie Poster");
		lblMoviePoster.setForeground(Color.BLACK);
		lblMoviePoster.setBounds(529, 11, 168, 25);
		lblMoviePoster.setFont(new Font("Cooper Black", Font.BOLD, 20));
		frame.getContentPane().add(lblMoviePoster);
		
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
		
		tglbtnUpcoming = new JToggleButton("Upcoming");
		tglbtnUpcoming.setBounds(196, 0, 100, 32);
		tglbtnUpcoming.setFont(new Font("Tahoma", Font.PLAIN, 10));
		frame.getContentPane().add(tglbtnUpcoming);
		
		tglbtnPopular = new JToggleButton("Popular");
		tglbtnPopular.setBounds(0, 0, 100, 32);
		tglbtnPopular.setFont(new Font("Tahoma", Font.PLAIN, 10));
		frame.getContentPane().add(tglbtnPopular);
		
		tglbtnTopRated = new JToggleButton("Top Rated");
		tglbtnTopRated.setBounds(99, 0, 100, 32);
		tglbtnTopRated.setFont(new Font("Tahoma", Font.PLAIN, 10));
		frame.getContentPane().add(tglbtnTopRated);
		
		tglbtnSearch = new JToggleButton("Search");
		tglbtnSearch.setBounds(393, 0, 100, 32);
		tglbtnSearch.setFont(new Font("Tahoma", Font.PLAIN, 10));
		frame.getContentPane().add(tglbtnSearch);
		
		tglbtnNowPlaying = new JToggleButton("Now Playing");
		tglbtnNowPlaying.setBounds(295, 0, 100, 32);
		tglbtnNowPlaying.setFont(new Font("Tahoma", Font.PLAIN, 10));
		frame.getContentPane().add(tglbtnNowPlaying);
		
		
		spMovieList = new JScrollPane();
		spMovieList.setBounds(10, 100, 501, 328);
		frame.getContentPane().add(spMovieList);
		
		tblMovieList = new JTable();
		tblMovieList.setBackground(Color.WHITE);
		tblMovieList.setFont(new Font("Tahoma", Font.BOLD, 14));
		tblMovieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblMovieList.setModel(new DefaultTableModel(
			new Object[][] {
				{new Integer(1), null, null, null},
				{new Integer(2), null, null, null},
				{new Integer(3), null, null, null},
				{new Integer(4), null, null, null},
				{new Integer(5), null, null, null},
				{new Integer(6), null, null, null},
				{new Integer(7), null, null, null},
				{new Integer(8), null, null, null},
				{new Integer(9), null, null, null},
				{new Integer(10), null, null, null},
				{new Integer(11), null, null, null},
				{new Integer(12), null, null, null},
				{new Integer(13), null, null, null},
				{new Integer(14), null, null, null},
				{new Integer(15), null, null, null},
				{new Integer(16), null, null, null},
				{new Integer(17), null, null, null},
				{new Integer(18), null, null, null},
				{new Integer(19), null, null, null},
				{new Integer(20), null, null, null},
			},
			new String[] {
				"", "Title", "Release Date", "Rating"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tblMovieList.getColumnModel().getColumn(0).setResizable(false);
		tblMovieList.getColumnModel().getColumn(0).setPreferredWidth(23);
		tblMovieList.getColumnModel().getColumn(0).setMaxWidth(22);
		tblMovieList.getColumnModel().getColumn(1).setResizable(false);
		tblMovieList.getColumnModel().getColumn(2).setResizable(false);
		tblMovieList.getColumnModel().getColumn(3).setResizable(false);
		tblMovieList.setRowHeight(30);
		
		tblMovieList.setOpaque(false);
		((DefaultTableCellRenderer)tblMovieList.getDefaultRenderer(Object.class)).setOpaque(false);
		
		spMovieList.setViewportView(tblMovieList);
		spMovieList.setOpaque(false);
		spMovieList.getViewport().setOpaque(false);
		
		lblMovieInfo = new JLabel("Movie Info");
		lblMovieInfo.setForeground(Color.BLACK);
		lblMovieInfo.setFont(new Font("Cooper Black", Font.BOLD, 20));
		lblMovieInfo.setBounds(723, 11, 149, 25);
		frame.getContentPane().add(lblMovieInfo);
		
		lblPlotDiscription = new JLabel("Plot Discription");
		lblPlotDiscription.setForeground(Color.BLACK);
		lblPlotDiscription.setFont(new Font("Cooper Black", Font.BOLD, 20));
		lblPlotDiscription.setBounds(527, 316, 186, 25);
		frame.getContentPane().add(lblPlotDiscription);
	}
	
}
