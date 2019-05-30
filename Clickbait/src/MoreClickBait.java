import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MoreClickBait {
	private static int count = 0;

	public static void main(String[] args) {
		JFrame frame = new JFrame("More Click Bait");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,300);
		
		JLabel label = new JLabel("Count: " + count);
		
		JButton inc = new JButton("increment");
//		//individual action listener:
//		class IncListener implements ActionListener{
//			public void actionPerformed(ActionEvent e) {
//				count++;
//				label.setText("Count: " + count);
//			}
//		}
//		inc.addActionListener(new IncListener());
		
		JButton dec = new JButton("decrement");
//		//individual action listener:
//		class DecListener implements ActionListener{
//			public void actionPerformed(ActionEvent e) {
//				count--;
//				label.setText("Count: " + count);
//			}
//		}
//		dec.addActionListener(new DecListener());
		
		JButton reset = new JButton("reset");
//		//individual action listener:
//		class ResetListener implements ActionListener{
//			public void actionPerformed(ActionEvent e) {
//				count=0;
//				label.setText("Count: " + count);
//			}
//		}
//		reset.addActionListener(new ResetListener());
		
		//combined action listener
		class ButtonListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				String button = e.getActionCommand();
				if (button.equals("increment"))
					count++;
				else if (button.equals("decrement"))
					count--;
				else if (button.equals("reset"))
					count=0;
				label.setText("Count: " + count);
			}
		}
		inc.addActionListener(new ButtonListener());
		dec.addActionListener(new ButtonListener());
		reset.addActionListener(new ButtonListener());
		
		JPanel panel = new JPanel();
		panel.add(dec);
		panel.add(label);
		panel.add(inc);
		panel.add(reset);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("file");
		JMenu edit = new JMenu("edit");
		JMenuItem open = new JMenuItem("open");
		JMenuItem save = new JMenuItem("save");
		JMenuItem copy = new JMenuItem("copy");
		
		file.add(open);
		file.add(save);
		edit.add(copy);
		menuBar.add(file);
		menuBar.add(edit);
		frame.setJMenuBar(menuBar);
		
		class MenuListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("open")) {
					System.out.println("Open something");
				} else if (e.getActionCommand().equals("copy")) {
					System.out.println("KEYBOARD SHORTCUT!!!!");
				}
			}
		}
		
		MenuListener listener = new MenuListener();
		open.addActionListener(listener);
		save.addActionListener(listener);
		copy.addActionListener(listener);
		
		//parameter is the width of the box
		JTextField input = new JTextField(10);

		class InputListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				//replace label text with entered text:
				label.setText(input.getText());
				//empty the textField
				input.setText("");
			}
		}
		input.addActionListener(new InputListener());
		
		panel.add(input);

		frame.add(panel);
//		frame.add(inc);
//		frame.add(dec);
		frame.setVisible(true);
	}

}