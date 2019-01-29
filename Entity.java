

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Entity {
	private int x,y,size;
	public Entity(int size) {
		this.size = size;
		
	}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public int getSize() {return size;}
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		
	}
	
	public void move(int dx, int dy) {
		x += dx;
		y += dy;
		
	}
	
	public Rectangle getBound() {
		return new Rectangle(x,y,size,size);
	}

	public void render(Graphics2D g2d) {
		g2d.fillRect(x, y, size, size);
	}
}
