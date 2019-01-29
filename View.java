
import java.io.*;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;




class View extends JFrame implements Iview,KeyListener{
	
	
	
	private JButton start;
	private JButton setting;
	private JButton LevelEdit;
	private JButton Help;
	private JLabel label = new JLabel("AIRCRAFT",SwingConstants.CENTER);
	private Model model;
	
	private JPanel p1;
	private JPanel game;
	private JPanel settingview;
	
	View(Model model1){
		 p1 = new JPanel(new GridLayout(5,1));
		
		model1.addView(this);
		start = new JButton("?");
		start.setMaximumSize(new Dimension(150, 70));
		start.setPreferredSize(new Dimension(150, 70));
		start.setText("START GAME");
		start.setFont(new Font("Arial", Font.PLAIN, 40));
	
	
		setting = new JButton("?");
		setting.setMaximumSize(new Dimension(150, 70));
		setting.setPreferredSize(new Dimension(150, 70));
		setting.setFont(new Font("Arial", Font.PLAIN, 40));
		setting.setText("SETTINGS");
		
		
		LevelEdit = new JButton("?");
		LevelEdit.setMaximumSize(new Dimension(150, 70));
		LevelEdit.setPreferredSize(new Dimension(150, 70));
		LevelEdit.setFont(new Font("Arial", Font.PLAIN, 40));
		LevelEdit.setText("LEVEL EDITOR");
		
		Help = new JButton("HELP");
		Help.setMaximumSize(new Dimension(150, 70));
		Help.setPreferredSize(new Dimension(150, 70));
		Help.setFont(new Font("Arial", Font.PLAIN, 40));
		
		
		
		label.setFont(new Font("Arial",Font.PLAIN,100));
        p1.setBackground(Color.cyan);
        
        p1.add(label);
        p1.add(start);
        p1.add(setting);
        p1.add(LevelEdit);
        p1.add(Help);
       
        
     
		
		this.pack();
        this.setSize(new Dimension(800,800));
        this.add(p1);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setFocusable(true);
		
		this.setVisible(true);

	    model = model1;
	    
		
		
		start.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				model.start = true;
				model.clickone();
				
			}
		});
		setting.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				model.setting = true;
				model.clickone();
				
			}
		});
		LevelEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				model.levelEdit = true;
				model.clickone();
				
			}
		});
		Help.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				model.help = true;
				model.clickone();
				
			}
		});
		
		
	}
	
	public void updateView() {
		if(model.start) {
			model.start = false;
			game = new GameView(model);
			this.remove(p1);
			this.add(game);
			this.addKeyListener(this);
			game.addKeyListener(this);
		//	this.setFocusable(true);
		//	frame.getContentPane().invalidate();
		//	frame.getContentPane().validate();
			this.setVisible(true);
			this.repaint();
			model.PlayGame();
			return;
		}
		if(model.IsRunning()) {
			this.repaint();
		}
		if(model.win) {
			if(model.level == 5) {
				return;
			}
			model.win = false;
			this.remove(game);
			this.removeKeyListener(this);
			game.removeKeyListener(this);
			game = new GameView(model);
			this.add(game);
			model.init();
			
			this.addKeyListener(this);
			game.addKeyListener(this);
			this.setVisible(true);
			this.repaint();
			model.PlayGame();
			
		}
		if(model.exit) {
			
			
			model.gameover = false;
			model.win = false;
			
			this.removeKeyListener(this);
			game.removeKeyListener(this);
			this.remove(game);
			model.level = 1;
			model.score = 0;
			this.add(p1);
			this.setVisible(true);
			this.repaint();
			model.exit = false;
			
			
		}
		if(model.setting) {
			model.setting = false;
			this.remove(p1);
			settingview = new SettingView(model);
			this.add(settingview);
			this.setVisible(true);
			this.repaint();
			
		}
		if(model.settingExit) {
			model.settingExit = false;
			this.remove(settingview);
			this.add(p1);
			this.setVisible(true);
			this.repaint();
		}
		if(model.levelEdit) {
			
		}
		if(model.help) {
			JOptionPane.showMessageDialog(this,"Aircraft side-scroller-game.\n"
					+ "All buttons could be clicked but not pressed.\n"
					+ "UP DOWN RIGHT LEFT on keyboard to move player.\n"
					+ "You can set game speed in Setting menu, the max speed is 5.\n"
					+ "There are total 4 levels in the game.\n"
					+ "The main menu is resizable.");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		if(k == KeyEvent.VK_UP) model.up = true;
		if(k == KeyEvent.VK_DOWN) model.down = true;
		if(k == KeyEvent.VK_LEFT) model.left = true;
		if(k == KeyEvent.VK_RIGHT) model.right = true;
		model.update();
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int k = e.getKeyCode();
		if(k == KeyEvent.VK_UP) model.up = false;
		if(k == KeyEvent.VK_DOWN) model.down = false;
		if(k == KeyEvent.VK_LEFT) model.left = false;
		if(k == KeyEvent.VK_RIGHT) model.right = false;
		
	}

	
	
}
