package bfe;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class FrameB extends JFrame {

	// General Fields

	private static final long serialVersionUID = 1L;
	private int xpos;
	private int ypos;

	// Datas

	private String[][] teams;
	private List<String> players;
	private List<Integer> points;
	private List<Integer> games = new ArrayList<>();
	private List<Integer> tls;
	private List<Integer> twos;
	private List<Integer> threes;
	private int[] indexes = new int[10];

	// Dimensions

	private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private final Dimension frameSize = new Dimension(1130, 670);

	// Borders

	private Border raisedBevel = BorderFactory.createRaisedBevelBorder();

	// JPanels

	private JPanel contentPane = new JPanel();
	private JPanel teamsPane = new JPanel();
	private JPanel overallPane = new JPanel();
	private JPanel tlPane = new JPanel();
	private JPanel twoPane = new JPanel();
	private JPanel threePane = new JPanel();
	private JPanel individualPane = new JPanel();

	// JTabbedPanes

	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

	// JButtons

	private JButton closeBut = new JButton();
	private JButton maximizeBut = new JButton();
	private JButton minimizeBut = new JButton();
	private JButton loadBut = new JButton();
	private JButton saveBut = new JButton();
	private JButton screenBut = new JButton();
	private JButton aboutBut = new JButton();
	private JButton githubBut = new JButton();
	private JButton mailBut = new JButton();
	private JButton lombCanBut = new JButton();
	private JButton searchBut = new JButton();
	private JButton homeBut = new JButton();

	// JLabels

	private JLabel backLab = new JLabel();
	private JLabel link = new JLabel();

	// JTextFields

	private JTextField getPlayerTf = new JTextField();

	// JTables and Models

	private JTable teamsTb = new JTable();
	private JTable overallTb = new JTable();
	private JTable tlTb = new JTable();
	private JTable twoTb = new JTable();
	private JTable threeTb = new JTable();
	private JTable individualTb = new JTable();

	private DefaultTableModel teamsModel = new DefaultTableModel(new Object[][] {},
			new String[] { "#", "Squadra", "Punti", "Giocate", "Vinte", "Perse", "Punti fatti", "Punti subiti" });

	private DefaultTableModel playersModel1 = new DefaultTableModel(new Object[][] {},
			new String[] { "#", "Giocatore", "PTS", "PPG", "PG" });

	private DefaultTableModel playersModel2 = new DefaultTableModel(new Object[][] {},
			new String[] { "#", "Giocatore", "TL", "TPG", "PG" });

	private DefaultTableModel playersModel3 = new DefaultTableModel(new Object[][] {},
			new String[] { "#", "Giocatore", "2PTS", "2PG", "PG" });

	private DefaultTableModel playersModel4 = new DefaultTableModel(new Object[][] {},
			new String[] { "#", "Giocatore", "3PTS", "3PG", "PG" });

	private DefaultTableModel individualModel = new DefaultTableModel(new Object[][] {},
			new String[] { "Giocatore", "G", "PTS", "PPG", "FT", "FTG", "2PTS", "2PG", "3PTS", "3PG" });

	// ImageIcons

	private ImageIcon x128 = new ImageIcon(getClass().getClassLoader().getResource("128x.png"));
	private ImageIcon x64 = new ImageIcon(getClass().getClassLoader().getResource("64x.png"));
	private ImageIcon x32 = new ImageIcon(getClass().getClassLoader().getResource("32x.png"));
	private ImageIcon close = new ImageIcon(getClass().getClassLoader().getResource("close.png"));
	private ImageIcon background = new ImageIcon(getClass().getClassLoader().getResource("backgroundB.png"));
	private ImageIcon hoverclose = new ImageIcon(getClass().getClassLoader().getResource("hoverclose.png"));
	private ImageIcon maximize = new ImageIcon(getClass().getClassLoader().getResource("maximize.png"));
	private ImageIcon minimize = new ImageIcon(getClass().getClassLoader().getResource("minimize.png"));
	private ImageIcon hoverminimize = new ImageIcon(getClass().getClassLoader().getResource("hoverminimize.png"));
	private ImageIcon save = new ImageIcon(getClass().getClassLoader().getResource("save.png"));
	private ImageIcon hoversave = new ImageIcon(getClass().getClassLoader().getResource("hoversave.png"));
	private ImageIcon load = new ImageIcon(getClass().getClassLoader().getResource("load.png"));
	private ImageIcon hoverload = new ImageIcon(getClass().getClassLoader().getResource("hoverload.png"));
	private ImageIcon screenshot = new ImageIcon(getClass().getClassLoader().getResource("screenshot.png"));
	private ImageIcon hoverscreenshot = new ImageIcon(getClass().getClassLoader().getResource("hoverscreenshot.png"));
	private ImageIcon about = new ImageIcon(getClass().getClassLoader().getResource("about.png"));
	private ImageIcon hoverabout = new ImageIcon(getClass().getClassLoader().getResource("hoverabout.png"));
	private ImageIcon github = new ImageIcon(getClass().getClassLoader().getResource("github.png"));
	private ImageIcon hovergithub = new ImageIcon(getClass().getClassLoader().getResource("hovergithub.png"));
	private ImageIcon mail = new ImageIcon(getClass().getClassLoader().getResource("mail.png"));
	private ImageIcon hovermail = new ImageIcon(getClass().getClassLoader().getResource("hovermail.png"));
	private ImageIcon logolc = new ImageIcon(getClass().getClassLoader().getResource("logolcB.png"));
	private ImageIcon hoverlogolc = new ImageIcon(getClass().getClassLoader().getResource("hoverlogolcB.png"));

	// Images

	private Image image1 = x128.getImage();
	private Image image2 = x64.getImage();
	private Image image3 = x32.getImage();
	private Image[] images = { image1, image2, image3 };

	public FrameB(String[][] teams, List<String> players, List<Integer> points, List<Integer> tls, List<Integer> twos,
			List<Integer> threes, List<Integer> games) {

		// Initializating global variables

		this.teams = teams;
		this.players = players;
		this.points = points;
		this.games = games;
		this.tls = tls;
		this.twos = twos;
		this.threes = threes;

		// General Settings

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(frameSize);
		setLocation((screenSize.width / 2) - (super.getWidth() / 2), (screenSize.height / 2) - (super.getHeight() / 2));
		setResizable(false);
		getContentPane().setLayout(null);
		setIconImages(Arrays.asList(images));
		setUndecorated(true);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setIcons();
		initComponents();
		buildUI();
		eventsHandling();
		fillTables();

	}

	private void setIcons() {

		backLab.setIcon(background);
		closeBut.setIcon(close);
		closeBut.setRolloverIcon(hoverclose);
		maximizeBut.setIcon(maximize);
		minimizeBut.setIcon(minimize);
		minimizeBut.setRolloverIcon(hoverminimize);
		saveBut.setIcon(save);
		saveBut.setRolloverIcon(hoversave);
		loadBut.setIcon(load);
		loadBut.setRolloverIcon(hoverload);
		screenBut.setIcon(screenshot);
		screenBut.setRolloverIcon(hoverscreenshot);
		aboutBut.setIcon(about);
		aboutBut.setRolloverIcon(hoverabout);
		githubBut.setIcon(github);
		githubBut.setRolloverIcon(hovergithub);
		mailBut.setIcon(mail);
		mailBut.setRolloverIcon(hovermail);
		lombCanBut.setIcon(logolc);
		lombCanBut.setRolloverIcon(hoverlogolc);

	}

	private void initComponents() {

		backLab.setBounds(0, 0, 1130, 670);
		link.setBounds(109, 648, 216, 20);

		closeBut.setBorder(null);
		closeBut.setContentAreaFilled(false);
		closeBut.setFocusPainted(false);
		closeBut.setFocusable(false);
		closeBut.setBounds(1100, 0, 30, 30);

		maximizeBut.setBorder(null);
		maximizeBut.setContentAreaFilled(false);
		maximizeBut.setFocusPainted(false);
		maximizeBut.setFocusable(false);
		maximizeBut.setBounds(1067, 0, 30, 30);

		minimizeBut.setBorder(null);
		minimizeBut.setContentAreaFilled(false);
		minimizeBut.setFocusPainted(false);
		minimizeBut.setFocusable(false);
		minimizeBut.setBounds(1034, 0, 30, 30);

		saveBut.setBorder(null);
		saveBut.setContentAreaFilled(false);
		saveBut.setFocusPainted(false);
		saveBut.setFocusable(false);
		saveBut.setBounds(825, 52, 52, 57);

		loadBut.setBorder(null);
		loadBut.setContentAreaFilled(false);
		loadBut.setFocusPainted(false);
		loadBut.setFocusable(false);
		loadBut.setBounds(897, 48, 65, 65);

		screenBut.setBorder(null);
		screenBut.setContentAreaFilled(false);
		screenBut.setFocusPainted(false);
		screenBut.setFocusable(false);
		screenBut.setBounds(975, 52, 80, 56);

		aboutBut.setBorder(null);
		aboutBut.setContentAreaFilled(false);
		aboutBut.setFocusPainted(false);
		aboutBut.setFocusable(false);
		aboutBut.setBounds(1048, 650, 16, 16);

		githubBut.setBorder(null);
		githubBut.setContentAreaFilled(false);
		githubBut.setFocusPainted(false);
		githubBut.setFocusable(false);
		githubBut.setBounds(1072, 650, 16, 16);

		mailBut.setBorder(null);
		mailBut.setContentAreaFilled(false);
		mailBut.setFocusPainted(false);
		mailBut.setFocusable(false);
		mailBut.setBounds(1096, 650, 16, 16);

		lombCanBut.setBorder(null);
		lombCanBut.setContentAreaFilled(false);
		lombCanBut.setFocusPainted(false);
		lombCanBut.setFocusable(false);
		lombCanBut.setBounds(90, 648, 20, 20);

		teamsPane.setBounds(52, 169, 548, 329);
		teamsPane.setLayout(new BorderLayout());
		teamsPane.setBorder(raisedBevel);

		teamsPane.setLayout(new BorderLayout());
		overallPane.setLayout(new BorderLayout());
		tlPane.setLayout(new BorderLayout());
		twoPane.setLayout(new BorderLayout());
		threePane.setLayout(new BorderLayout());
		individualPane.setLayout(new BorderLayout());

		teamsTb.setModel(teamsModel);
		teamsTb.setRowHeight(60);
		teamsTb.setColumnSelectionAllowed(false);
		teamsTb.setRowSelectionAllowed(false);
		teamsTb.getTableHeader().setReorderingAllowed(false);
		teamsTb.setDefaultEditor(Object.class, null);

		overallTb.setModel(playersModel1);
		overallTb.setRowHeight(31);
		overallTb.setColumnSelectionAllowed(false);
		overallTb.setRowSelectionAllowed(false);
		overallTb.getTableHeader().setReorderingAllowed(false);
		overallTb.setDefaultEditor(Object.class, null);

		tlTb.setModel(playersModel2);
		tlTb.setRowHeight(31);
		tlTb.setColumnSelectionAllowed(false);
		tlTb.setRowSelectionAllowed(false);
		tlTb.getTableHeader().setReorderingAllowed(false);
		tlTb.setDefaultEditor(Object.class, null);

		twoTb.setModel(playersModel3);
		twoTb.setRowHeight(31);
		twoTb.setColumnSelectionAllowed(false);
		twoTb.setRowSelectionAllowed(false);
		twoTb.getTableHeader().setReorderingAllowed(false);
		twoTb.setDefaultEditor(Object.class, null);

		threeTb.setModel(playersModel4);
		threeTb.setRowHeight(31);
		threeTb.setColumnSelectionAllowed(false);
		threeTb.setRowSelectionAllowed(false);
		threeTb.getTableHeader().setReorderingAllowed(false);
		threeTb.setDefaultEditor(Object.class, null);

		individualTb.setModel(individualModel);
		individualTb.setRowHeight(72);
		individualTb.setColumnSelectionAllowed(false);
		individualTb.setRowSelectionAllowed(false);
		individualTb.getTableHeader().setReorderingAllowed(false);
		individualTb.setDefaultEditor(Object.class, null);

		tabbedPane.setBounds(670, 146, 416, 360);
		getPlayerTf.setBounds(52, 589, 113, 30);
		individualPane.setBounds(260, 534, 829, 93);

		searchBut.setBounds(167, 591, 89, 23);
		homeBut.setBounds(10, 607, 35, 30);

	}

	private void buildUI() {

		contentPane.add(homeBut);
		contentPane.add(link);
		contentPane.add(lombCanBut);
		contentPane.add(searchBut);
		contentPane.add(getPlayerTf);
		contentPane.add(githubBut);
		contentPane.add(aboutBut);
		contentPane.add(mailBut);
		contentPane.add(saveBut);
		contentPane.add(loadBut);
		contentPane.add(screenBut);
		contentPane.add(closeBut);
		contentPane.add(maximizeBut);
		contentPane.add(minimizeBut);
		teamsPane.add(teamsTb.getTableHeader(), BorderLayout.NORTH);
		teamsPane.add(teamsTb, BorderLayout.CENTER);
		overallPane.add(overallTb.getTableHeader(), BorderLayout.NORTH);
		overallPane.add(overallTb, BorderLayout.CENTER);
		tlPane.add(tlTb.getTableHeader(), BorderLayout.NORTH);
		tlPane.add(tlTb, BorderLayout.CENTER);
		twoPane.add(twoTb.getTableHeader(), BorderLayout.NORTH);
		twoPane.add(twoTb, BorderLayout.CENTER);
		threePane.add(threeTb.getTableHeader(), BorderLayout.NORTH);
		threePane.add(threeTb, BorderLayout.CENTER);
		individualPane.add(individualTb.getTableHeader(), BorderLayout.NORTH);
		individualPane.add(individualTb, BorderLayout.CENTER);
		tabbedPane.addTab("Punti", null, overallPane, "Top 10 realizzatori del girone");
		tabbedPane.addTab("Tiri liberi", null, tlPane, "Top 10 realizzatori di tiri liberi del girone");
		tabbedPane.addTab("Tiri da 2", null, twoPane, "Top 10 realizzatori di tiri da 2 segnati del girone");
		tabbedPane.addTab("Triple", null, threePane, "Top 10 realizzatori di triple del girone");
		contentPane.add(teamsPane);
		contentPane.add(tabbedPane);
		contentPane.add(individualPane);
		contentPane.add(backLab);

	}

	private void eventsHandling() {

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xpos = e.getX();
				ypos = e.getY();
			}
		});

		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				int x = arg0.getXOnScreen();
				int y = arg0.getYOnScreen();
				FrameB.this.setLocation(x - xpos, y - ypos);
			}
		});

		closeBut.addActionListener(e -> {
			System.exit(0);
		});

		minimizeBut.addActionListener(e -> {

			setState(JFrame.ICONIFIED);
		});

		aboutBut.addActionListener(e -> {

			try {
				Desktop.getDesktop().browse(new URI("https://github.com/RichardBoy05/Basket4Ever#readme"));
			} catch (IOException | URISyntaxException ee) {

				JOptionPane.showMessageDialog(null, ee);
			}
		});

		githubBut.addActionListener(e -> {

			try {
				Desktop.getDesktop().browse(new URI("https://github.com/RichardBoy05/Basket4Ever"));
			} catch (IOException | URISyntaxException ee) {

				JOptionPane.showMessageDialog(null, ee);
			}
		});

		mailBut.addActionListener(e -> {

			try {
				Desktop.getDesktop().browse(new URI("mailto:basket4ever.fixandbugs@gmail.com"));
			} catch (IOException | URISyntaxException ee) {

				JOptionPane.showMessageDialog(null, ee);
			}
		});

		lombCanBut.addActionListener(e -> {

			try {
				Desktop.getDesktop().browse(new URI("https://lombardiacanestro.it"));
			} catch (IOException | URISyntaxException ee) {

				JOptionPane.showMessageDialog(null, ee);
			}
		});

		link.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://lombardiacanestro.it"));
				} catch (IOException | URISyntaxException ee) {

					JOptionPane.showMessageDialog(null, ee);
				}
			}

		});

		homeBut.addActionListener(e -> {

			dispose();
			showGui();
			
		});

		searchBut.addActionListener(new IndividualStats(this));
		screenBut.addActionListener(new Screenshot(this));
		saveBut.addActionListener(new SaveDatas(this));
		loadBut.addActionListener(new LoadDatas(this));

	}

	private void fillTables() {

		new FillTables(this);

	}

	private void showGui() {

		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {

					FrameA frame = new FrameA();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Errore nell'apertura dell'interfaccia\n" + e);
				}

			}
		});
	}

	// Getters and Setters

	public Dimension getFrameSize() {

		return frameSize;
	}

	public DefaultTableModel getTeamsModel() {
		return teamsModel;
	}

	public DefaultTableModel getPlayersModel1() {
		return playersModel1;
	}

	public DefaultTableModel getPlayersModel2() {
		return playersModel2;
	}

	public DefaultTableModel getPlayersModel3() {
		return playersModel3;
	}

	public DefaultTableModel getPlayersModel4() {
		return playersModel4;
	}

	public DefaultTableModel getIndividualModel() {
		return individualModel;
	}

	public String[][] getTeams() {
		return teams;
	}

	public List<String> getPlayers() {
		return players;
	}

	public List<Integer> getPoints() {
		return points;
	}

	public List<Integer> getGames() {
		return games;
	}

	public List<Integer> getTls() {
		return tls;
	}

	public List<Integer> getTwos() {
		return twos;
	}

	public List<Integer> getThrees() {
		return threes;
	}

	public void setIndexes(int[] indexes) {
		this.indexes = indexes;
	}

	public int[] getIndexes() {
		return indexes;
	}

	public JTextField getPlayerName() {
		return getPlayerTf;
	}
}