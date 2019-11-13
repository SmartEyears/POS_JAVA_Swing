package kr.ac.jj.s201351029;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ProductUI extends JFrame implements ActionListener{

	JTextField jfPsearch;
	JTextField jfPbarcord;
	JTextField jfPname;
	JTextField jfPprice;
	JTextField jfPbuy;
	JTextField jfPstock;
	JTextField jfPp_no;
	JPanel north;
	JPanel center;
	JPanel south;
	JPanel east;
	JButton btn_select;
	JButton btn_insert;
	JButton btn_update;
	JButton btn_delete;
	JButton btn_cancel;
	JButton btn_search;
	JButton btn_searchmode;
	JButton btn_searchUp;
	JTable table;
	DefaultTableModel model;
	
	public ProductUI() {
		this(150,0);
	}
	
	public ProductUI(int x, int y) {
		setTitle("��ǰ����");
		this.setSize(1000, 650);
		this.setLocation(x, y);
		this.setLayout(new BorderLayout());
		
		north = new JPanel();
		center = new JPanel();
		south = new JPanel();
		east = new JPanel();
		
		
		jfPp_no = new JTextField(3);
		jfPbarcord = new JTextField(15);
		jfPname = new JTextField(6);
		jfPprice = new JTextField(8);
		jfPbuy = new JTextField(8);
		jfPstock = new JTextField(8);
		
		jfPsearch = new JTextField(15);
		btn_searchmode = new JButton("�˻����");
		btn_searchmode.addActionListener(this);
		btn_search = new JButton("�˻�");
		btn_search.addActionListener(this);
		btn_cancel = new JButton("�Է¸��");
		btn_cancel.addActionListener(this);
		btn_searchUp = new JButton("�����ϱ�");
		btn_searchUp.addActionListener(this);
		
		String header[] = {"","���ڵ�","��ǰ��","����","���Դܰ�","���"};
		model = new DefaultTableModel(header,0) {
			public boolean isCellEditable(int row,int column) {
				return false;
			}
		};
		table = new JTable(model);
		JScrollPane scrollpane = new JScrollPane(table);
		table.getColumn("").setWidth(0);
		table.getColumn("").setMinWidth(0);
		table.getColumn("").setMaxWidth(0);
		
		btn_select = new JButton("�ʱ�ȭ");
		btn_select.addActionListener(this);
		btn_insert = new JButton("�Է�");
		btn_insert.addActionListener(this);
		btn_update = new JButton("����");
		btn_update.addActionListener(this);
		btn_delete= new JButton("����");
		btn_delete.addActionListener(this);
		
		north.setLayout(new FlowLayout());
		north.add(new Label("���ڵ� :"));
		north.add(jfPbarcord);
		north.add(new Label("��ǰ�� :"));
		north.add(jfPname);
		north.add(new Label("���� :"));
		north.add(jfPprice);
		north.add(new Label("���Դܰ� :"));
		north.add(jfPbuy);
		north.add(new Label("��� :"));
		north.add(jfPstock);
		north.add(btn_searchmode);
		north.setBackground(Color.LIGHT_GRAY);
		add(north, BorderLayout.NORTH);
		
		center.setLayout(new GridLayout());
		center.add(scrollpane);
		add(center, BorderLayout.CENTER);
		
		south.setLayout(new GridLayout(1,4));
		south.add(btn_select);
		south.add(btn_insert);
		south.add(btn_update);
		south.add(btn_delete);
		south.setBackground(Color.LIGHT_GRAY);
		add(south, BorderLayout.SOUTH);
		
		this.setVisible(true);
		
		east.setLayout(new FlowLayout());
		east.add(new Label("���ڵ� :"));
		east.add(jfPsearch);
		east.add(btn_search);
		east.add(btn_cancel);
		add(east, BorderLayout.EAST);
		east.setVisible(false);
		
		listenerSetting();
		ProductDAO dao;
		dao = new ProductDAO();
		dao.selectProduct(model);
	}
	
	void init() {
		jfPbarcord.setText(null);
		jfPbarcord.setText(null);
		jfPname.setText(null);
		jfPprice.setText(null);
		jfPbuy.setText(null);
		jfPstock.setText(null);
		jfPp_no.setText(null);
	}
	
	void listenerSetting() {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				table = (JTable) e.getComponent();
				model = (DefaultTableModel) table.getModel();
				
				String p_no = (String)model.getValueAt(table.getSelectedRow(), 0);
				jfPp_no.setText(p_no);
				jfPp_no.setEnabled(false);
				
				String barcord = (String) model.getValueAt(table.getSelectedRow(), 1);
				jfPbarcord.setText(barcord);
				
				String name = (String) model.getValueAt(table.getSelectedRow(), 2);
				jfPname.setText(name);
				
				String price = (String) model.getValueAt(table.getSelectedRow(), 3);
				jfPprice.setText(price);
				
				String bprice = (String) model.getValueAt(table.getSelectedRow(), 4);
				jfPbuy.setText(bprice);
				
				String stock = (String) model.getValueAt(table.getSelectedRow(), 5);
				jfPstock.setText(stock);
				btn_insert.setEnabled(false);
				super.mouseClicked(e);
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ProductDAO dao;
		ProductDTO dto;
		String barcord1;
		switch(e.getActionCommand()) {
		case "�˻�":
			dao = new ProductDAO();
			barcord1 = jfPsearch.getText()+"";
			dao.searchProduct(model, barcord1);
			break;
		case "�Է¸��" :
			dao = new ProductDAO();
			east.setVisible(false);
			dao.selectProduct(model);
			break;
		case "�˻����" :
			east.setVisible(true);
			break;
		case "�ʱ�ȭ" :
			init();
			btn_insert.setEnabled(true);
			break;
			
		case "�Է�" :
			dao = new ProductDAO();
			String barcord = jfPbarcord.getText();
			String name = jfPname.getText();
			int pri = 0,bpri = 0,qua = 0;
			
			try {
				pri = Integer.parseInt(jfPprice.getText());
			}catch(Exception e1){
				JOptionPane.showMessageDialog(null, "������ �Է����ּ���.", "���", JOptionPane.INFORMATION_MESSAGE);
			}
			
			try {
				bpri = Integer.parseInt(jfPbuy.getText());
			}catch(Exception e2) {
				JOptionPane.showMessageDialog(null, "���Դܰ��� �Է����ּ���.", "���", JOptionPane.INFORMATION_MESSAGE);
			}
			
			try {
				qua = Integer.parseInt(jfPstock.getText());
			}catch(Exception e2) {
				JOptionPane.showMessageDialog(null, "��� �Է����ּ���.", "���", JOptionPane.INFORMATION_MESSAGE);
			}
			
			ProductDTO pd = new ProductDTO(barcord, name, pri, bpri, qua);
			if(name.compareTo("") == 0) {
				JOptionPane.showMessageDialog(null, "�̸��� �Է����ּ���.", "���", JOptionPane.INFORMATION_MESSAGE);
				return;
			}else if(jfPbarcord.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "���ڵ带 �Է����ּ���.", "���", JOptionPane.INFORMATION_MESSAGE);
				return;
			}else {
				dao.insert(pd);
				dao.selectProduct(model);
				init();
			}
			
			break;
			
		case "����" :
			dao = new ProductDAO();
			int x = model.getRowCount();
			//System.out.println(x);
			int u_p_no = Integer.parseInt(jfPp_no.getText());
			String u_barcord = jfPbarcord.getText();
			String u_p_name = jfPname.getText();
			int u_p_pri = Integer.parseInt(jfPprice.getText());
			int u_p_bpri = Integer.parseInt(jfPbuy.getText());
			int u_p_qua = Integer.parseInt(jfPstock.getText());
			
			dao.update(u_p_no, u_barcord, u_p_name, u_p_pri, u_p_bpri, u_p_qua);
			if(x == 1) {
				barcord1 = jfPsearch.getText()+"";
				dao.searchProduct(model, barcord1);
			}else {
				dao.selectProduct(model);
			}
				
			
			jfPsearch.setText(null);
			init();
			break;
		
		case "����" :
			dao = new ProductDAO();
			int d_p_no = Integer.parseInt(jfPp_no.getText());
			dao.delete(d_p_no);
			dao.selectProduct(model);
			init();
			break;
		}
	}
}
