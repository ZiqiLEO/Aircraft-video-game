

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class SettingView extends JPanel{

		// the view's main user interface
		private JButton button;
		private JButton Exit;

		// the model that this view is showing
		private Model model;

		SettingView(Model model_) {
			// create the view UI
			button = new JButton("?");
			
			Exit = new JButton("EXIT");
			
			// a GridBagLayout with default constraints centres
			// the widget in the window
			this.setLayout(new GridBagLayout());
			this.add(button, new GridBagConstraints());
			this.add(Exit, new GridBagConstraints());

			// set the model
			model = model_;
			setBackground(Color.cyan);

			// setup the event to go to the "controller"
			// (this anonymous class is essentially the controller)
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					model.incrementSpeed();
					
				}
			});
			Exit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					model.ExitSetting();
					
					
				}
			});
		}
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			button.setMaximumSize(new Dimension(200, 100));
			button.setPreferredSize(new Dimension(200, 100));
			button.setLocation(300,300);
			Exit.setMaximumSize(new Dimension(100, 50));
			Exit.setPreferredSize(new Dimension(100, 50));
			Exit.setLocation(300,500);
			button.setText("Speed: "+ Integer.toString(model.speed));
		}
		
	


}
