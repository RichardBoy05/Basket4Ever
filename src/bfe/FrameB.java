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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class FrameB extends JFrame {

	// General Fields

	private static final long serialVersionUID = 1L;
	private int xpos;
	private int ypos;

	// Datas

	private final static SimpleDateFormat INTERNAL_DATE_FORMATTER = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss");
	private final static SimpleDateFormat VISIBLE_DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy 'alle' HH:mm:ss");
	private Date date = new Date(System.currentTimeMillis());
	private String code;
	private String[][] teams;
	private List<String> players;
	private List<String> playerHomes;
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
	private JButton viewOnLcBut = new JButton();

	// JLabels

	private JLabel overviewLab = new JLabel();
	private JLabel backLab = new JLabel();
	private JLabel link = new JLabel();

	// JTextFields

	private JTextField getPlayerTf = new JTextField();

	// JTables, Models, Rendered

	private JTable teamsTb = new JTable();
	private JTable overallTb = new JTable();
	private JTable tlTb = new JTable();
	private JTable twoTb = new JTable();
	private JTable threeTb = new JTable();
	private JTable individualTb = new JTable();

	private DefaultTableModel teamsModel = new DefaultTableModel(new Object[][] {},
			new String[] { "#", "Squadra", "P", "G", "V", "S", "PF", "PS" });

	private DefaultTableModel playersModel1 = new DefaultTableModel(new Object[][] {},
			new String[] { "#", "Giocatore", "Squadra", "PTS", "PPG", "PG" });

	private DefaultTableModel playersModel2 = new DefaultTableModel(new Object[][] {},
			new String[] { "#", "Giocatore", "Squadra", "TL", "TPG", "PG" });

	private DefaultTableModel playersModel3 = new DefaultTableModel(new Object[][] {},
			new String[] { "#", "Giocatore", "Squadra", "2PTS", "2PG", "PG" });

	private DefaultTableModel playersModel4 = new DefaultTableModel(new Object[][] {},
			new String[] { "#", "Giocatore", "Squadra", "3PTS", "3PG", "PG" });

	private DefaultTableModel individualModel = new DefaultTableModel(new Object[][] {},
			new String[] { "Giocatore", "Squadra", "G", "PTS", "PPG", "FT", "FTG", "2PTS", "2PG", "3PTS", "3PG" });

	private DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();

	private final String[] EMPTY_INDIVIDUAL_ROW = { "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-" };

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
	private ImageIcon saved = new ImageIcon(getClass().getClassLoader().getResource("saved.png"));
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
	private ImageIcon home = new ImageIcon(getClass().getClassLoader().getResource("home.png"));
	private ImageIcon hoverhome = new ImageIcon(getClass().getClassLoader().getResource("hoverhome.png"));
	private ImageIcon search = new ImageIcon(getClass().getClassLoader().getResource("search.png"));
	private ImageIcon hoversearch = new ImageIcon(getClass().getClassLoader().getResource("hoversearch.png"));
	private ImageIcon biglogolc = new ImageIcon(getClass().getClassLoader().getResource("logolcC.png"));
	private ImageIcon hoverbiglogolc = new ImageIcon(getClass().getClassLoader().getResource("hoverlogolcC.png"));

	// Images

	private Image image1 = x128.getImage();
	private Image image2 = x64.getImage();
	private Image image3 = x32.getImage();
	private Image[] images = { image1, image2, image3 };

	public FrameB(String code, String[][] teams, List<String> players, List<String> playerHomes, List<Integer> points,
			List<Integer> tls, List<Integer> twos, List<Integer> threes, List<Integer> games) {

		// Initializating global variables

		this.code = code;
		this.teams = teams;
		this.players = players;
		this.playerHomes = playerHomes;
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
		adjustTables();
		buildUI();
		eventsHandling();

		if (code != null)
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
		homeBut.setIcon(home);
		homeBut.setRolloverIcon(hoverhome);
		searchBut.setIcon(search);
		searchBut.setRolloverIcon(hoversearch);
		viewOnLcBut.setIcon(biglogolc);
		viewOnLcBut.setRolloverIcon(hoverbiglogolc);

	}

	private void initComponents() {

		overviewLab.setBounds(74, 55, 435, 42);
		
		if (code != null) {
			overviewLab.setText(CodeTranslator.translateCode(getCode()));
			overviewLab.setToolTipText("Salvataggio effettuato in data " + VISIBLE_DATE_FORMATTER.format(date));
			Utils.fixLabelFontSize(overviewLab);
		}

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

		homeBut.setBorder(null);
		homeBut.setContentAreaFilled(false);
		homeBut.setFocusPainted(false);
		homeBut.setFocusable(false);
		homeBut.setBounds(3, 604, 35, 35);

		searchBut.setBorder(null);
		searchBut.setContentAreaFilled(false);
		searchBut.setFocusPainted(false);
		searchBut.setFocusable(false);
		searchBut.setBounds(218, 564, 52, 61);

		viewOnLcBut.setBorder(null);
		viewOnLcBut.setContentAreaFilled(false);
		viewOnLcBut.setFocusPainted(false);
		viewOnLcBut.setFocusable(false);
		viewOnLcBut.setBounds(745, 52, 57, 57);

		teamsPane.setBorder(raisedBevel);
		teamsPane.setLayout(new BorderLayout());
		teamsPane.setBounds(52, 158, 493, 345);

		individualPane.setBorder(raisedBevel);
		individualPane.setLayout(new BorderLayout());
		individualPane.setBounds(286, 540, 796, 85);

		teamsPane.setLayout(new BorderLayout());
		overallPane.setLayout(new BorderLayout());
		tlPane.setLayout(new BorderLayout());
		twoPane.setLayout(new BorderLayout());
		threePane.setLayout(new BorderLayout());

		tabbedPane.setBounds(586, 135, 496, 368);
		getPlayerTf.setBounds(52, 577, 156, 30);

	}

	private void adjustTables() {

		cellRenderer.setHorizontalAlignment(JLabel.CENTER);

		teamsTb.setModel(teamsModel);

		for (int i = 0; i < teamsTb.getColumnCount(); i++) {
			teamsTb.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
		}

		teamsTb.setColumnSelectionAllowed(false);
		teamsTb.setRowSelectionAllowed(false);
		teamsTb.getTableHeader().setReorderingAllowed(false);
		teamsTb.setDefaultEditor(Object.class, null);

		teamsTb.getColumnModel().getColumn(0).setMaxWidth(35);
		teamsTb.getColumnModel().getColumn(0).setMinWidth(35);

		teamsTb.getColumnModel().getColumn(2).setMaxWidth(45);
		teamsTb.getColumnModel().getColumn(2).setMinWidth(35);
		teamsTb.getColumnModel().getColumn(3).setMaxWidth(45);
		teamsTb.getColumnModel().getColumn(3).setMinWidth(35);
		teamsTb.getColumnModel().getColumn(4).setMaxWidth(45);
		teamsTb.getColumnModel().getColumn(4).setMinWidth(35);
		teamsTb.getColumnModel().getColumn(5).setMaxWidth(45);
		teamsTb.getColumnModel().getColumn(5).setMinWidth(35);

		teamsTb.getColumnModel().getColumn(6).setMaxWidth(55);
		teamsTb.getColumnModel().getColumn(6).setMinWidth(45);
		teamsTb.getColumnModel().getColumn(7).setMaxWidth(55);
		teamsTb.getColumnModel().getColumn(7).setMinWidth(45);

		teamsTb.getColumnModel().getColumn(1).setPreferredWidth(164);

		overallTb.setModel(playersModel1);

		for (int i = 0; i < overallTb.getColumnCount(); i++) {
			overallTb.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
		}

		overallTb.setRowHeight(32);
		overallTb.setColumnSelectionAllowed(false);
		overallTb.setRowSelectionAllowed(false);
		overallTb.getTableHeader().setReorderingAllowed(false);
		overallTb.setDefaultEditor(Object.class, null);

		overallTb.getColumnModel().getColumn(0).setMaxWidth(36);
		overallTb.getColumnModel().getColumn(0).setMinWidth(36);

		overallTb.getColumnModel().getColumn(3).setMaxWidth(55);
		overallTb.getColumnModel().getColumn(3).setMinWidth(55);
		overallTb.getColumnModel().getColumn(4).setMaxWidth(55);
		overallTb.getColumnModel().getColumn(4).setMinWidth(55);
		overallTb.getColumnModel().getColumn(5).setMaxWidth(55);
		overallTb.getColumnModel().getColumn(5).setMinWidth(55);

		overallTb.getColumnModel().getColumn(1).setPreferredWidth(145);
		overallTb.getColumnModel().getColumn(1).setMinWidth(100);
		overallTb.getColumnModel().getColumn(2).setPreferredWidth(145);
		overallTb.getColumnModel().getColumn(2).setMinWidth(100);

		tlTb.setModel(playersModel2);

		for (int i = 0; i < tlTb.getColumnCount(); i++) {
			tlTb.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
		}

		tlTb.setRowHeight(32);
		tlTb.setColumnSelectionAllowed(false);
		tlTb.setRowSelectionAllowed(false);
		tlTb.getTableHeader().setReorderingAllowed(false);
		tlTb.setDefaultEditor(Object.class, null);

		tlTb.getColumnModel().getColumn(0).setMaxWidth(36);
		tlTb.getColumnModel().getColumn(0).setMinWidth(36);

		tlTb.getColumnModel().getColumn(3).setMaxWidth(55);
		tlTb.getColumnModel().getColumn(3).setMinWidth(55);
		tlTb.getColumnModel().getColumn(4).setMaxWidth(55);
		tlTb.getColumnModel().getColumn(4).setMinWidth(55);
		tlTb.getColumnModel().getColumn(5).setMaxWidth(55);
		tlTb.getColumnModel().getColumn(5).setMinWidth(55);

		tlTb.getColumnModel().getColumn(1).setPreferredWidth(145);
		tlTb.getColumnModel().getColumn(1).setMinWidth(100);
		tlTb.getColumnModel().getColumn(2).setPreferredWidth(145);
		tlTb.getColumnModel().getColumn(2).setMinWidth(100);

		twoTb.setModel(playersModel3);

		for (int i = 0; i < twoTb.getColumnCount(); i++) {
			twoTb.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
		}

		twoTb.setRowHeight(32);
		twoTb.setColumnSelectionAllowed(false);
		twoTb.setRowSelectionAllowed(false);
		twoTb.getTableHeader().setReorderingAllowed(false);
		twoTb.setDefaultEditor(Object.class, null);

		twoTb.getColumnModel().getColumn(0).setMaxWidth(36);
		twoTb.getColumnModel().getColumn(0).setMinWidth(36);

		twoTb.getColumnModel().getColumn(3).setMaxWidth(55);
		twoTb.getColumnModel().getColumn(3).setMinWidth(55);
		twoTb.getColumnModel().getColumn(4).setMaxWidth(55);
		twoTb.getColumnModel().getColumn(4).setMinWidth(55);
		twoTb.getColumnModel().getColumn(5).setMaxWidth(55);
		twoTb.getColumnModel().getColumn(5).setMinWidth(55);

		twoTb.getColumnModel().getColumn(1).setPreferredWidth(145);
		twoTb.getColumnModel().getColumn(1).setMinWidth(100);
		twoTb.getColumnModel().getColumn(2).setPreferredWidth(145);
		twoTb.getColumnModel().getColumn(2).setMinWidth(100);

		threeTb.setModel(playersModel4);

		for (int i = 0; i < threeTb.getColumnCount(); i++) {
			threeTb.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
		}

		threeTb.setRowHeight(32);
		threeTb.setColumnSelectionAllowed(false);
		threeTb.setRowSelectionAllowed(false);
		threeTb.getTableHeader().setReorderingAllowed(false);
		threeTb.setDefaultEditor(Object.class, null);

		threeTb.getColumnModel().getColumn(0).setMaxWidth(36);
		threeTb.getColumnModel().getColumn(0).setMinWidth(36);

		threeTb.getColumnModel().getColumn(3).setMaxWidth(55);
		threeTb.getColumnModel().getColumn(3).setMinWidth(55);
		threeTb.getColumnModel().getColumn(4).setMaxWidth(55);
		threeTb.getColumnModel().getColumn(4).setMinWidth(55);
		threeTb.getColumnModel().getColumn(5).setMaxWidth(55);
		threeTb.getColumnModel().getColumn(5).setMinWidth(55);

		threeTb.getColumnModel().getColumn(1).setPreferredWidth(145);
		threeTb.getColumnModel().getColumn(1).setMinWidth(100);
		threeTb.getColumnModel().getColumn(2).setPreferredWidth(145);
		threeTb.getColumnModel().getColumn(2).setMinWidth(100);

		individualTb.setModel(individualModel);

		for (int i = 0; i < individualTb.getColumnCount(); i++) {
			individualTb.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
		}

		individualTb.setRowHeight(60);
		individualTb.setColumnSelectionAllowed(false);
		individualTb.setRowSelectionAllowed(false);
		individualTb.getTableHeader().setReorderingAllowed(false);
		individualTb.setDefaultEditor(Object.class, null);

		individualTb.getColumnModel().getColumn(0).setPreferredWidth(145);
		individualTb.getColumnModel().getColumn(0).setMinWidth(100);
		individualTb.getColumnModel().getColumn(1).setPreferredWidth(145);
		individualTb.getColumnModel().getColumn(1).setMinWidth(100);

		individualTb.getColumnModel().getColumn(2).setMaxWidth(55);
		individualTb.getColumnModel().getColumn(2).setMinWidth(55);
		individualTb.getColumnModel().getColumn(3).setMaxWidth(55);
		individualTb.getColumnModel().getColumn(3).setMinWidth(55);
		individualTb.getColumnModel().getColumn(4).setMaxWidth(55);
		individualTb.getColumnModel().getColumn(4).setMinWidth(55);
		individualTb.getColumnModel().getColumn(5).setMaxWidth(55);
		individualTb.getColumnModel().getColumn(5).setMinWidth(55);
		individualTb.getColumnModel().getColumn(6).setMaxWidth(55);
		individualTb.getColumnModel().getColumn(6).setMinWidth(55);
		individualTb.getColumnModel().getColumn(7).setMaxWidth(55);
		individualTb.getColumnModel().getColumn(7).setMinWidth(55);
		individualTb.getColumnModel().getColumn(8).setMaxWidth(55);
		individualTb.getColumnModel().getColumn(8).setMinWidth(55);
		individualTb.getColumnModel().getColumn(9).setMaxWidth(55);
		individualTb.getColumnModel().getColumn(9).setMinWidth(55);
		individualTb.getColumnModel().getColumn(10).setMaxWidth(55);
		individualTb.getColumnModel().getColumn(10).setMinWidth(55);

		individualModel.addRow(EMPTY_INDIVIDUAL_ROW);

	}

	private void buildUI() {

		getRootPane().setDefaultButton(searchBut);

		contentPane.add(viewOnLcBut);
		contentPane.add(overviewLab);
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
		viewOnLcBut.addActionListener(new ViewOnLc(this));
		overallTb.addMouseListener(new SelectedPlayerListener(this));
		tlTb.addMouseListener(new SelectedPlayerListener(this));
		twoTb.addMouseListener(new SelectedPlayerListener(this));
		threeTb.addMouseListener(new SelectedPlayerListener(this));

	}

	public void fillTables() {

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

	public JTable getTeamsTb() {
		return teamsTb;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public SimpleDateFormat getInternalDateFormatter() {
		return INTERNAL_DATE_FORMATTER;
	}

	public SimpleDateFormat getVisibleDateFormatter() {
		return VISIBLE_DATE_FORMATTER;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public void setTeams(String[][] teams) {
		this.teams = teams;
	}

	public void setPlayers(List<String> players) {
		this.players = players;
	}

	public List<String> getPlayerHomes() {
		return playerHomes;
	}

	public void setPlayerHomes(List<String> playerHomes) {
		this.playerHomes = playerHomes;
	}

	public void setPoints(List<Integer> points) {
		this.points = points;
	}

	public void setGames(List<Integer> games) {
		this.games = games;
	}

	public void setTls(List<Integer> tls) {
		this.tls = tls;
	}

	public void setTwos(List<Integer> twos) {
		this.twos = twos;
	}

	public void setThrees(List<Integer> threes) {
		this.threes = threes;
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

	public JButton getSaveBut() {
		return saveBut;
	}

	public ImageIcon getSaveButIcon() {
		return save;
	}

	public ImageIcon getSavedButIcon() {
		return saved;
	}

	public JLabel getOverViewLab() {
		return overviewLab;
	}

	public String[] getEmptyIndividualRow() {
		return EMPTY_INDIVIDUAL_ROW;
	}

	public JButton getSearchBut() {
		return searchBut;
	}

}