package kr.ac.jj.s201351029;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MyMain extends JFrame implements ActionListener{
	
	public MyMain() {
		this.setSize(150, 200);
		this.setLayout(null);
		
		JButton btnProduct = new JButton("惑前包府");
		btnProduct.setBounds(10, 10, 110, 50);
		this.add(btnProduct);
		btnProduct.addActionListener(this);

		JButton btnSell = new JButton("魄概");
		btnSell.setBounds(10, 100, 110, 50);
		this.add(btnSell);
		btnSell.addActionListener(this);
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyMain();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "惑前包府":
			new ProductUI();
			break;
		case "魄概":
			new SellUI();
			break;
		}
	}
}
