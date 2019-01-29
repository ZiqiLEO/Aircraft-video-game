
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;




public class GameView extends JPanel{
	private Model model;
	
	//public static GameView gameview;
	private BufferedImage image;
	private JLabel plane;
	
	
	public final int width = 800, height = 800;
	private boolean up,down,right,left;
	
	private JButton start, pause,exit;
	private JLabel Gameover;
	private JLabel Win;
	
	
	
	public GameView(Model model1){
		setLayout(new FlowLayout());
		
		
		start = new JButton("START");
		start.setFont(new Font("Arial", Font.PLAIN, 20));
		
		this.add(start);
		pause = new JButton("PAUSE");
		pause.setFont(new Font("Arial", Font.PLAIN, 20));
		
		this.add(pause);
		exit = new JButton("EXIT");
		exit.setFont(new Font("Arial", Font.PLAIN, 20));
		
		this.add(exit);
		Gameover = new JLabel("GAME OVER!",SwingConstants.CENTER);
		Gameover.setFont(new Font("Arial", Font.PLAIN, 100));
		this.add(Gameover);
		
		Win = new JLabel("YOU WIN !",SwingConstants.CENTER);
		Win.setFont(new Font("Arial", Font.PLAIN, 100));
		this.add(Win);
		
		
		
		try {
			image = ImageIO.read(new File("plane2.png"));
		} catch (IOException ex) {
			System.out.println(111);
			System.exit(1);
			
		}
		
		plane = new JLabel(new ImageIcon(image));
		
		this.add(plane);
		
		
		
	
		
		start.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(model.win != true) {
					model.RestartGame();
				}else {
					model.winGame();
				}
				
			}
		});
		start.setFocusable(false);
		pause.setFocusable(false);
		pause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				model.PauseGame();
				
			}
		});
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				model.running = false;
				model.PauseGame();
				model.ExitGame();
			}
		});
		
		model = model1;
		//image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_ARGB);
		setPreferredSize(new Dimension(width,height));
		setVisible(true);
		
	}
	

@Override
protected void paintComponent(Graphics g) {
	
	
	super.paintComponent(g);
	
	
		g.setColor(Color.cyan);
		g.fillRect(0,0,width,height);
		
		
		
		
		start.setLocation(50,30);
		pause.setLocation(150,30);
		exit.setLocation(250,30);
		Gameover.setLocation(-400, -400);
		Win.setLocation(-400,-400);		
		
		plane.setLocation(model.head.getX(),model.head.getY());
		
		g.setColor(Color.orange);
		g.fillRect(0,height - 120, width, 120);
		g.setColor(Color.green);
		g.fillRect(0, height-120, width, 20);
		for (Rectangle obstacle : model.obstacles)
		{
			g.setColor(Color.green.darker());
			g.fillRect(obstacle.x, obstacle.y, obstacle.width, obstacle.height);
		}
		g.setColor(Color.BLACK);
		g.drawString("Level :"+ model.level, 50, 100);
		g.drawString("Score :"+ model.score, 50, 140);
		
			
		if(model.gameover) {
			Gameover.setLocation(80, 270);
			model.PauseGame();
		}
		
		for(Rectangle column : model.obstacles) {
			Rectangle temp;
			temp = new Rectangle(model.head.getX(),model.head.getY(),55,55);
			if (column.intersects(temp))
			{	
				Gameover.setLocation(80,270);
				model.PauseGame();
			}
			
		}
		int size = model.obstacles.size();
		if(model.obstacles.get(size-1).getX() < 0) {
			Win.setLocation(120,270);
			model.win = true;
			model.PauseGame();
			
			
		}
	
	}




	

}
