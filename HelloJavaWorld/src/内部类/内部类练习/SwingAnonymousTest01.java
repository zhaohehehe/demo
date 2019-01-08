package 内部类.内部类练习;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SwingAnonymousTest01 {

	public static void main(String[] args) {
		
		JFrame frame=new JFrame("JFrame");
		JButton button=new JButton("click me!");
		
		//button.addActionListener(new MyListener());
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("welcome");
			}
		});
		
		frame.setSize(200, 200);
		frame.getContentPane().add(button);
		frame.setVisible(true);
	}

}
class MyListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {

		System.out.println("hello world");
	}
	
}
