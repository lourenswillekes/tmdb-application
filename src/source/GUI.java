package source;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

public class GUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 943, 545);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Label lblPlotDiscription = new Label("Plot Discription");
		lblPlotDiscription.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPlotDiscription.setBounds(527, 320, 121, 22);
		frame.getContentPane().add(lblPlotDiscription);
		
		JButton btnSelectRandom = new JButton("Select Random");
		btnSelectRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSelectRandom.setFont(new Font("Modern No. 20", Font.BOLD, 14));
		btnSelectRandom.setBounds(353, 453, 164, 32);
		frame.getContentPane().add(btnSelectRandom);
		
		JButton btnAddFav = new JButton("Add To Favorites");
		btnAddFav.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddFav.setFont(new Font("Modern No. 20", Font.BOLD, 14));
		btnAddFav.setBounds(527, 453, 170, 32);
		frame.getContentPane().add(btnAddFav);
		
		JButton btnAddWatch = new JButton("Add To Watchlist");
		btnAddWatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddWatch.setFont(new Font("Modern No. 20", Font.BOLD, 14));
		btnAddWatch.setBounds(733, 453, 170, 32);
		frame.getContentPane().add(btnAddWatch);
		
		JLabel lblPoster_Image = new JLabel("");
		lblPoster_Image.setBounds(528, 39, 185, 278);
		frame.getContentPane().add(lblPoster_Image);
		
		JLabel lblMoviePoster = new JLabel("Movie Poster");
		lblMoviePoster.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMoviePoster.setBounds(529, 11, 119, 25);
		frame.getContentPane().add(lblMoviePoster);
		
		JLabel lblMovieInfo = new JLabel("Movie Info");
		lblMovieInfo.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMovieInfo.setBounds(723, 11, 90, 25);
		frame.getContentPane().add(lblMovieInfo);
		
		JScrollPane spPlotDiscription = new JScrollPane();
		spPlotDiscription.setBounds(527, 344, 374, 98);
		frame.getContentPane().add(spPlotDiscription);
		
		JTextPane txtPlotDiscription = new JTextPane();
		spPlotDiscription.setViewportView(txtPlotDiscription);
		
		JScrollPane spMovieInfo = new JScrollPane();
		spMovieInfo.setBounds(723, 39, 180, 276);
		frame.getContentPane().add(spMovieInfo);
		
		JTextPane txtMovieInfo = new JTextPane();
		spMovieInfo.setViewportView(txtMovieInfo);
		
		JToggleButton tglbtnUpcoming = new JToggleButton("Upcoming");
		tglbtnUpcoming.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		tglbtnUpcoming.setBounds(189, 26, 90, 23);
		frame.getContentPane().add(tglbtnUpcoming);
		
		JToggleButton tglbtnPopular = new JToggleButton("Popular");
		tglbtnPopular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		tglbtnPopular.setBounds(10, 26, 90, 23);
		frame.getContentPane().add(tglbtnPopular);
		
		JToggleButton tglbtnTopRated = new JToggleButton("Top Rated");
		tglbtnTopRated.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		tglbtnTopRated.setBounds(99, 26, 90, 23);
		frame.getContentPane().add(tglbtnTopRated);
		
		JToggleButton tglbtnSearch = new JToggleButton("Search");
		tglbtnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		tglbtnSearch.setBounds(369, 26, 90, 23);
		frame.getContentPane().add(tglbtnSearch);
		
		JToggleButton tglbtnNowPlaying = new JToggleButton("Now Playing");
		tglbtnNowPlaying.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		tglbtnNowPlaying.setBounds(279, 26, 90, 23);
		frame.getContentPane().add(tglbtnNowPlaying);
	}
}
