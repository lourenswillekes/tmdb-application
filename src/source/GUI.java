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

import javax.swing.ListSelectionModel;

public class GUI {

	private JFrame frame;
	private JTable tblMovieList;
	private DefaultTableCellRenderer inboxTableCellRenderer = new DefaultTableCellRenderer();
	private SearchGUI x;

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
		
		///*
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("E:\\CIS 350\\MovieAPI\\junglebookbackdrop.jpg"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		RescaleOp rescaleOp = new RescaleOp(1.2f, 150, null);
		rescaleOp.filter(img, img);
		
		Image dimg = img.getScaledInstance(943, 580, Image.SCALE_SMOOTH);
		
		ImageIcon imageIcon = new ImageIcon(dimg);
		
		
		JLabel label = new JLabel(imageIcon);
		frame.setContentPane(label);
		
		//*/
		
		JLabel lblTab = new JLabel("");
		lblTab.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		lblTab.setBounds(10, 60, 356, 29);
		frame.getContentPane().add(lblTab);
		
		JButton btnSelectRandom = new JButton("Select Random");
		btnSelectRandom.setBounds(353, 453, 164, 32);
		btnSelectRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSelectRandom.setFont(new Font("Modern No. 20", Font.BOLD, 14));
		frame.getContentPane().add(btnSelectRandom);
		
		JButton btnAddFav = new JButton("Add To Favorites");
		btnAddFav.setBounds(527, 453, 170, 32);
		btnAddFav.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddFav.setFont(new Font("Modern No. 20", Font.BOLD, 14));
		frame.getContentPane().add(btnAddFav);
		
		JButton btnAddWatch = new JButton("Add To Watchlist");
		btnAddWatch.setBounds(733, 453, 170, 32);
		btnAddWatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnAddWatch.setFont(new Font("Modern No. 20", Font.BOLD, 14));
		frame.getContentPane().add(btnAddWatch);
		
		JLabel lblPoster_Image = new JLabel("");
		lblPoster_Image.setIcon(new ImageIcon("E:\\CIS 350\\MovieAPI\\junglebook.jpg"));
		lblPoster_Image.setBounds(528, 39, 185, 278);
		frame.getContentPane().add(lblPoster_Image);
		
		JLabel lblMoviePoster = new JLabel("Movie Poster");
		lblMoviePoster.setForeground(Color.BLACK);
		lblMoviePoster.setBounds(529, 11, 168, 25);
		lblMoviePoster.setFont(new Font("Cooper Black", Font.BOLD, 20));
		frame.getContentPane().add(lblMoviePoster);
		
		JScrollPane spPlotDiscription = new JScrollPane();
		spPlotDiscription.setBounds(527, 344, 374, 98);
		frame.getContentPane().add(spPlotDiscription);
		
		JTextPane txtPlotDiscription = new JTextPane();
		txtPlotDiscription.setBackground(new Color(65, 105, 225));
		txtPlotDiscription.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtPlotDiscription.setOpaque(false);
		txtPlotDiscription.setForeground(Color.BLACK);
		spPlotDiscription.setViewportView(txtPlotDiscription);
		spPlotDiscription.setOpaque(false);
		spPlotDiscription.getViewport().setOpaque(false);
		
		JScrollPane spMovieInfo = new JScrollPane();
		spMovieInfo.setBounds(723, 39, 180, 276);
		frame.getContentPane().add(spMovieInfo);
		
		JTextPane txtMovieInfo = new JTextPane();
		txtMovieInfo.setBackground(new Color(65, 105, 225));
		txtMovieInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtMovieInfo.setOpaque(false);
		txtMovieInfo.setForeground(Color.BLACK);
		spMovieInfo.setViewportView(txtMovieInfo);
		spMovieInfo.setOpaque(false);
		spMovieInfo.getViewport().setOpaque(false);
		
		JToggleButton tglbtnUpcoming = new JToggleButton("Upcoming");
		tglbtnUpcoming.setBounds(196, 0, 100, 32);
		tglbtnUpcoming.setFont(new Font("Tahoma", Font.PLAIN, 10));
		tglbtnUpcoming.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTab.setText("Top 20 Upcoming Films");
			}
		});
		frame.getContentPane().add(tglbtnUpcoming);
		
		JToggleButton tglbtnPopular = new JToggleButton("Popular");
		tglbtnPopular.setBounds(0, 0, 100, 32);
		tglbtnPopular.setFont(new Font("Tahoma", Font.PLAIN, 10));
		tglbtnPopular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lblTab.setText("Top 20 Popular Films");
			}
		});
		frame.getContentPane().add(tglbtnPopular);
		
		JToggleButton tglbtnTopRated = new JToggleButton("Top Rated");
		tglbtnTopRated.setBounds(99, 0, 100, 32);
		tglbtnTopRated.setFont(new Font("Tahoma", Font.PLAIN, 10));
		tglbtnTopRated.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTab.setText("Top 20 Top Rated Films");
			}
		});
		frame.getContentPane().add(tglbtnTopRated);
		
		JToggleButton tglbtnSearch = new JToggleButton("Search");
		tglbtnSearch.setBounds(393, 0, 100, 32);
		tglbtnSearch.setFont(new Font("Tahoma", Font.PLAIN, 10));
		tglbtnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				x = new SearchGUI();
				if (x.getCloseStatus() == false) {
					
				}
				lblTab.setText("Closed");
				
				//lblTab.setText("Top 20 Searched Results");
			}
		});
		frame.getContentPane().add(tglbtnSearch);
		
		JToggleButton tglbtnNowPlaying = new JToggleButton("Now Playing");
		tglbtnNowPlaying.setBounds(295, 0, 100, 32);
		tglbtnNowPlaying.setFont(new Font("Tahoma", Font.PLAIN, 10));
		tglbtnNowPlaying.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTab.setText("Top 20 Now Playing Films");
			}
		});
		frame.getContentPane().add(tglbtnNowPlaying);
		
		JScrollPane spMovieList = new JScrollPane();
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
		
		JLabel lblMovieInfo = new JLabel("Movie Info");
		lblMovieInfo.setForeground(Color.BLACK);
		lblMovieInfo.setFont(new Font("Cooper Black", Font.BOLD, 20));
		lblMovieInfo.setBounds(723, 11, 149, 25);
		frame.getContentPane().add(lblMovieInfo);
		
		JLabel lblPlotDiscription = new JLabel("Plot Discription");
		lblPlotDiscription.setForeground(Color.BLACK);
		lblPlotDiscription.setFont(new Font("Cooper Black", Font.BOLD, 20));
		lblPlotDiscription.setBounds(527, 316, 186, 25);
		frame.getContentPane().add(lblPlotDiscription);
	}
}
