package bfe;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

public class FrameA extends JFrame {//

	// General fields

	private static final long serialVersionUID = 1L;
	//private final int MIN_SCREEN_WHIDTH = 1150; //usali per controllare che l'utente abbia una risoluzione adeguata
	//private final int MIN_SCREEN_HEIGHT = 700;
	private int xpos;
	private int ypos;

	// JPanels

	private JPanel contentPane = new JPanel();

	// JLabels

	private JLabel progress_label = new JLabel();
	private JLabel backLabel = new JLabel();
	private JLabel link = new JLabel();

	// JButtons

	private JButton go_button = new JButton();
	private JButton minimize_button = new JButton();
	private JButton maximize_button = new JButton();
	private JButton about_button = new JButton();
	private JButton github_button = new JButton();
	private JButton mail_button = new JButton();
	private JButton lombcanestro_button = new JButton();
	private JButton close_button = new JButton();

	// Arrays

	private String[] genderls = { "Maschile", "Femminile" };
	private String[] yearlsm = { "C Gold", "C Silver", "Serie D", "Promozione", "Prima Divisione", "Seconda Divisione",
			"U20", "U19", "U17", "U16", "U15", "U14", "U13" };
	private String[] yearlsf = { "Serie B", "Serie C", "Promozione", "U19", "U17", "U15", "U14", "U13" };
	private String[] levellsinit = { "Regionale" };
	private String[] levells = { "Eccellenza", "Elité", "Gold", "Silver", "Regionale" };
	private String[] fasels = { "Qualificazione", "Classificazione" };
	private String[] groupls = { "Blu", "Giallo", "Verde" };

	// JComboBoxes

	private JComboBox<String> gendercb = new JComboBox<String>(genderls);
	private JComboBox<String> yearcb = new JComboBox<String>(yearlsm);
	private JComboBox<String> levelcb = new JComboBox<String>(levellsinit);
	private JComboBox<String> fasecb = new JComboBox<String>(fasels);
	private JComboBox<String> groupcb = new JComboBox<String>(groupls);

	// JProgressBar

	private JProgressBar bar;

	// ImageIcons

	private ImageIcon x128 = new ImageIcon(getClass().getClassLoader().getResource("128x.png"));
	private ImageIcon x64 = new ImageIcon(getClass().getClassLoader().getResource("64x.png"));
	private ImageIcon x32 = new ImageIcon(getClass().getClassLoader().getResource("32x.png"));
	private ImageIcon background = new ImageIcon(getClass().getClassLoader().getResource("selection_frame.png"));
	private ImageIcon close = new ImageIcon(getClass().getClassLoader().getResource("close.png"));
	private ImageIcon hoverclose = new ImageIcon(getClass().getClassLoader().getResource("hoverclose.png"));
	private ImageIcon maximize = new ImageIcon(getClass().getClassLoader().getResource("maximize.png"));
	private ImageIcon minimize = new ImageIcon(getClass().getClassLoader().getResource("minimize.png"));
	private ImageIcon hoverminimize = new ImageIcon(getClass().getClassLoader().getResource("hoverminimize.png"));
	private ImageIcon go = new ImageIcon(getClass().getClassLoader().getResource("go.png"));
	private ImageIcon hovergo = new ImageIcon(getClass().getClassLoader().getResource("hovergo.png"));
	private ImageIcon about = new ImageIcon(getClass().getClassLoader().getResource("about.png"));
	private ImageIcon hoverabout = new ImageIcon(getClass().getClassLoader().getResource("hoverabout.png"));
	private ImageIcon github = new ImageIcon(getClass().getClassLoader().getResource("github.png"));
	private ImageIcon hovergithub = new ImageIcon(getClass().getClassLoader().getResource("hovergithub.png"));
	private ImageIcon mail = new ImageIcon(getClass().getClassLoader().getResource("mail.png"));
	private ImageIcon hovermail = new ImageIcon(getClass().getClassLoader().getResource("hovermail.png"));
	private ImageIcon logolc = new ImageIcon(getClass().getClassLoader().getResource("logolc.png"));
	private ImageIcon hoverlogolc = new ImageIcon(getClass().getClassLoader().getResource("hoverlogolc.png"));

	// Images

	private Image image1 = x128.getImage();
	private Image image2 = x64.getImage();
	private Image image3 = x32.getImage();
	private Image[] images = { image1, image2, image3 };

	public FrameA() {

		// General Settings

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(849, 500);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width / 2) - (super.getWidth() / 2), (screenSize.height / 2) - (super.getHeight() / 2));
		setResizable(false);
		getContentPane().setLayout(null);
		setIconImages(Arrays.asList(images));
		setUndecorated(true);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Building the UI

		link.setBounds(429, 483, 157, 14);
		groupcb.setBounds(567, 340, 200, 30);
		gendercb.setBounds(567, 140, 200, 30);
		yearcb.setBounds(567, 187, 200, 30);
		levelcb.setBounds(567, 239, 200, 30);
		fasecb.setBounds(566, 288, 200, 30);

		close_button.setBorder(null);
		close_button.setContentAreaFilled(false);
		close_button.setIcon(close);
		close_button.setRolloverIcon(hoverclose);
		close_button.setFocusPainted(false);
		close_button.setFocusable(false);
		close_button.setBounds(819, 0, 30, 30);

		minimize_button.setBorder(null);
		minimize_button.setContentAreaFilled(false);
		minimize_button.setIcon(minimize);
		minimize_button.setRolloverIcon(hoverminimize);
		minimize_button.setFocusPainted(false);
		minimize_button.setFocusable(false);
		minimize_button.setBounds(753, 0, 30, 30);

		maximize_button.setBorder(null);
		maximize_button.setContentAreaFilled(false);
		maximize_button.setIcon(maximize);
		maximize_button.setFocusPainted(false);
		maximize_button.setFocusable(false);
		maximize_button.setBounds(786, 0, 30, 30);

		go_button.setBorder(null);
		go_button.setContentAreaFilled(false);
		go_button.setIcon(go);
		go_button.setRolloverIcon(hovergo);
		go_button.setFocusPainted(false);
		go_button.setFocusable(false);
		go_button.setBounds(412, 402, 110, 58);

		about_button.setBorder(null);
		about_button.setContentAreaFilled(false);
		about_button.setIcon(about);
		about_button.setRolloverIcon(hoverabout);
		about_button.setFocusPainted(false);
		about_button.setFocusable(false);
		about_button.setBounds(782, 482, 15, 15);

		github_button.setBorder(null);
		github_button.setContentAreaFilled(false);
		github_button.setIcon(github);
		github_button.setRolloverIcon(hovergithub);
		github_button.setFocusPainted(false);
		github_button.setFocusable(false);
		github_button.setBounds(804, 482, 15, 15);

		mail_button.setBorder(null);
		mail_button.setContentAreaFilled(false);
		mail_button.setIcon(mail);
		mail_button.setRolloverIcon(hovermail);
		mail_button.setFocusPainted(false);
		mail_button.setFocusable(false);
		mail_button.setBounds(826, 482, 15, 15);

		lombcanestro_button.setBorder(null);
		lombcanestro_button.setContentAreaFilled(false);
		lombcanestro_button.setIcon(logolc);
		lombcanestro_button.setRolloverIcon(hoverlogolc);
		lombcanestro_button.setFocusPainted(false);
		lombcanestro_button.setFocusable(false);
		lombcanestro_button.setBounds(408, 482, 15, 15);

		progress_label.setBounds(610, 452, 150, 14);

		backLabel.setBounds(0, 0, 849, 500);
		backLabel.setIcon(background);

		bar = new JProgressBar(0, 100);
		bar.setBounds(567, 394, 216, 52);
		bar.setStringPainted(true);
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		bar.setBorder(loweredbevel);
		bar.setForeground(new Color(0, 255, 127));

		// Fonts and Borders

		Font comboFont = new Font("Arial", Font.PLAIN, 15);
		Font barFont = new Font("", Font.BOLD, 16);
		
		gendercb.setFont(comboFont);
		yearcb.setFont(comboFont);
		levelcb.setFont(comboFont);
		fasecb.setFont(comboFont);
		groupcb.setFont(comboFont);
		bar.setFont(barFont);

		// Adding elements

		contentPane.add(link);
		contentPane.add(bar);
		contentPane.add(groupcb);
		contentPane.add(gendercb);
		contentPane.add(yearcb);
		contentPane.add(levelcb);
		contentPane.add(fasecb);
		contentPane.add(close_button);
		contentPane.add(minimize_button);
		contentPane.add(maximize_button);
		contentPane.add(go_button);
		contentPane.add(about_button);
		contentPane.add(github_button);
		contentPane.add(mail_button);
		contentPane.add(lombcanestro_button);
		contentPane.add(progress_label);
		contentPane.add(backLabel);

		// Events handling

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
				FrameA.this.setLocation(x - xpos, y - ypos);
			}
		});

		close_button.addActionListener(e -> {
			System.exit(0);
		});

		minimize_button.addActionListener(e -> {

			setState(JFrame.ICONIFIED);
		});

		lombcanestro_button.addActionListener(e -> {

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

		about_button.addActionListener(e -> {

			try {
				Desktop.getDesktop().browse(new URI("https://github.com/RichardBoy05/Basket4Ever#readme"));
			} catch (IOException | URISyntaxException ee) {

				JOptionPane.showMessageDialog(null, ee);
			}
		});

		github_button.addActionListener(e -> {

			try {
				Desktop.getDesktop().browse(new URI("https://github.com/RichardBoy05/Basket4Ever"));
			} catch (IOException | URISyntaxException ee) {

				JOptionPane.showMessageDialog(null, ee);
			}
		});

		mail_button.addActionListener(e -> {

			try {
				Desktop.getDesktop().browse(new URI("mailto:basket4ever.fixandbugs@gmail.com"));
			} catch (IOException | URISyntaxException ee) {

				JOptionPane.showMessageDialog(null, ee);
			}
		});

		go_button.addActionListener(new WebScraping(this));

		gendercb.addItemListener(new ListHandler(this));
		yearcb.addItemListener(new ListHandler(this));
		levelcb.addItemListener(new ListHandler(this));
		fasecb.addItemListener(new ListHandler(this));
		groupcb.addItemListener(new ListHandler(this));

	}

	// Getters

	public JLabel getProgressLabel() {
		return progress_label;
	}

	public JButton getGoButton() {
		return go_button;
	}

	public String[] getGenderls() {
		return genderls;
	}

	public String[] getYearlsm() {
		return yearlsm;
	}

	public String[] getYearlsf() {
		return yearlsf;
	}

	public String[] getLevellsinit() {
		return levellsinit;
	}

	public String[] getLevells() {
		return levells;
	}

	public String[] getFasels() {
		return fasels;
	}

	public String[] getGroupls() {
		return groupls;
	}

	public JComboBox<String> getGendercb() {
		return gendercb;
	}

	public JComboBox<String> getYearcb() {
		return yearcb;
	}

	public JComboBox<String> getLevelcb() {
		return levelcb;
	}

	public JComboBox<String> getFasecb() {
		return fasecb;
	}

	public JComboBox<String> getGroupcb() {
		return groupcb;
	}
	
	public JProgressBar getBar() {
		return bar;
	}

}
