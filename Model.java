

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.swing.*;


interface Iview {
    public void updateView();
}



public class Model {
    /** The observers that are watching this model for changes. */
    private ArrayList<Iview> views = new ArrayList<Iview>();
    
   //Game Loop
	public final int WIDTH = 800;
	public final int HEIGHT = 800;
	public boolean running = false;
	public boolean gameover;
	public boolean start = false;
	public boolean setting = false;
	public boolean levelEdit = false;
	public boolean help = false;
	public boolean exit = false;
	public boolean settingExit = false;
	public boolean win = false;
	
	public boolean pause = false;
	public int speed = 0;
	
	public int level = 1;
	public int score = 0;
	private int FPS = 30;
	
	private javax.swing.Timer timer;
	//Game stuff
	public Entity head;
	public ArrayList<Rectangle> obstacles;
	
	
	//movement 
	private int dx,dy;
	
	
	//key input
	public boolean up,down,right,left;

    /**
     * Create a new model.
     */
	public void addView(Iview view) {
		views.add(view);
	}
	
	public boolean IsStart() {
		return start;
	}
	
	public boolean IsRunning() {
		return running;
	}
	public boolean IsSetting() {
		return setting;
	}
	public boolean IsLevelEdit() {
		return levelEdit;
	}
	
	public void clickone() {
		if(start) {
			init();
			notifyObservers();
			start = false;
		}
		if(setting) {
			
			notifyObservers();
			setting = false;
			
		}
		if(levelEdit) {
			notifyObservers();
			levelEdit = false;
			
		}
		if(help) {
			notifyObservers();
			help = false;
		}
	}
	
	
	 public void notifyObservers() {
        for (Iview view: this.views) {
             view.updateView();
        }
    }
	 
	 private void Animation() {
		 
		 this.score++;
		 for(int i = 0; i < obstacles.size(); ++i)
			{
				Rectangle obstacle = obstacles.get(i);
				obstacle.x -= (speed+5);
			
			}
		
	 }
	 
	 public void PlayGame() {
		 this.timer = new javax.swing.Timer(1000/this.FPS, event -> {
	            this.Animation();
	            notifyObservers();
	            });
		 this.timer.start();
	 }
	 
	 public void RestartGame() {
		 this.timer.start();
	 }
	 
	 public void PauseGame() {
		 this.timer.stop();
	 }
	 public void ExitGame() {
		 this.exit = true;
		 notifyObservers();
		 
		 
	 }
	 public void ExitSetting() {
		 this.settingExit = true;
		 notifyObservers();
	 }
	 public void winGame() {
		 this.win = true;
		 ++this.level;
		 notifyObservers();
	 }
	 
	 private void setLevel() {
		 
		 head = new Entity(60);
		 head.setPosition(WIDTH/2, HEIGHT/2);
		 
		 if(level == 1) {
			 
		 
			
			obstacles = new ArrayList<Rectangle>();
			obstacles.add(new Rectangle(WIDTH+100+obstacles.size() * 300, HEIGHT-300-120,100,300));
			obstacles.add(new Rectangle(WIDTH+100+(obstacles.size()-1)*300,0,100,HEIGHT-250-300));
			
			obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x+600, HEIGHT-350-120,100,350));
			obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x,0,100,HEIGHT-350-300));
			
			obstacles.add(new Rectangle(WIDTH+100+obstacles.size() * 300, HEIGHT-300-120,100,300));
			obstacles.add(new Rectangle(WIDTH+100+(obstacles.size()-1)*300,0,100,HEIGHT-250-300));
			
			obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x+600, HEIGHT-350-120,100,350));
			obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x,0,100,HEIGHT-350-300));
			
			obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x+600, HEIGHT-600,100,350));
			obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x,0,100,HEIGHT-200));
			
			obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x+600, HEIGHT-350-120,100,350));
			obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x,0,100,HEIGHT-350-300));
			
			obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x+600, HEIGHT-350-120,100,200));
			obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x,0,100,HEIGHT-350-300));
			
			obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x+600, HEIGHT-350-120,100,400));
			obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x,0,100,HEIGHT-350-300));
			
		 
			
		 }
		 if(level == 2) {
			 obstacles = new ArrayList<Rectangle>();
				obstacles.add(new Rectangle(WIDTH+100+obstacles.size() * 300, HEIGHT-400-120,100,300));
				obstacles.add(new Rectangle(WIDTH+100+(obstacles.size()-1)*300,0,100,HEIGHT-250-300));
				
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x+600, HEIGHT-350-120,100,350));
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x,0,100,HEIGHT-350-300));
				
				obstacles.add(new Rectangle(WIDTH+100+obstacles.size() * 300, HEIGHT-300-120,100,300));
				obstacles.add(new Rectangle(WIDTH+100+(obstacles.size()-1)*300,0,100,HEIGHT-250-300));
				
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x+600, HEIGHT-350-120,100,350));
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x,0,100,HEIGHT-350-300));
				
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x+600, HEIGHT-600,100,350));
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x,0,100,HEIGHT-200));
				
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x+600, HEIGHT-350-120,100,350));
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x,0,100,HEIGHT-350-300));
				
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x+600, HEIGHT-350-120,100,200));
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x,0,100,HEIGHT-350-300));
				
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x+600, HEIGHT-350-120,100,400));
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x,0,100,HEIGHT-350-300));
				
			 
		 }
		 if(level == 3) {
			 obstacles = new ArrayList<Rectangle>();
			 obstacles.add(new Rectangle(WIDTH+100+obstacles.size() * 300, HEIGHT-400-120,100,300));
				obstacles.add(new Rectangle(WIDTH+100+(obstacles.size()-1)*300,0,100,HEIGHT-250-300));
				
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x+600, HEIGHT-350-120,100,350));
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x,0,100,HEIGHT-250-300));
				
				obstacles.add(new Rectangle(WIDTH+100+obstacles.size() * 300, HEIGHT-300-120,100,300));
				obstacles.add(new Rectangle(WIDTH+100+(obstacles.size()-1)*300,0,100,HEIGHT-300-300));
				
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x+600, HEIGHT-200-120,300,200));
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x,0,100,HEIGHT-350-300));
				
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x+600, HEIGHT-600,100,350));
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x,0,100,HEIGHT-200));
				
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x+600, HEIGHT-350-120,200,350));
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x,0,100,HEIGHT-350-200));
				
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x+600, HEIGHT-300-120,150,200));
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x,0,100,HEIGHT-350-300));
				
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x+600, HEIGHT-300-120,100,500));
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x,0,100,HEIGHT-300-300));
				
			 
		 }
		 if(level == 4) {
			 obstacles = new ArrayList<Rectangle>();
			 obstacles.add(new Rectangle(WIDTH+100+obstacles.size() * 300, HEIGHT-400-50,100,300));
				obstacles.add(new Rectangle(WIDTH+100+(obstacles.size()-1)*300,0,100,HEIGHT-350-300));
				
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x+600, HEIGHT-350-120,100,350));
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x,0,100,HEIGHT-250-300));
				
				obstacles.add(new Rectangle(WIDTH+100+obstacles.size() * 300, HEIGHT-300-120,100,300));
				obstacles.add(new Rectangle(WIDTH+100+(obstacles.size()-1)*300,0,100,HEIGHT-300-300));
				
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x+600, HEIGHT-200-120,300,200));
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x,0,100,HEIGHT-350-300));
				
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x+600, HEIGHT-600,100,350));
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x,0,100,HEIGHT-200));
				
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x+600, HEIGHT-350-120,200,350));
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x,0,100,HEIGHT-350-200));
				
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x+600, HEIGHT-300-120,150,200));
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x,0,100,HEIGHT-350-300));
				
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x+600, HEIGHT-300-120,100,500));
				obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x,0,100,HEIGHT-300-300));
				
			 
		 }
		
			
			
			
			
		}
	 public void incrementSpeed() {
		 if(speed < 5) {
			 speed++;
		 }else {
			 speed = 0;
		 }
		 
	 }
	 
	 public void init() {
			running = true;
			gameover = false;
			win = false;
			setLevel();
	}
	 public void update() {
			if(gameover) {
				this.PauseGame();
				return;
			}
			if(up && dy == 0) {
				dy = -30;
				dx = 0;
			}
			if(down && dy == 0) {
				dy = 30;
				dx = 0;
			}
			if(left && dx == 0) {
				dy = 0;
				dx = -30;
			}
			if(right && dx == 0) {
				dy = 0;
				dx = 30;
			}
			head.move(dx, dy);
			dx = 0;
			dy = 0;
			
			if(head.getX() < 0) gameover = true;
			if(head.getY() < 0) gameover = true;
			if(head.getX() > WIDTH) gameover = true;
			if(head.getY() > HEIGHT-160) gameover = true;
			
			for(Rectangle column : obstacles) {
				Rectangle temp;
				temp = new Rectangle(head.getX(),head.getY(),55,55);
				if (column.intersects(temp))
				{
					gameover = true;
				}
			}
			notifyObservers();
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
