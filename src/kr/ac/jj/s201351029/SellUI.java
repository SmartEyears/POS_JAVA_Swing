package kr.ac.jj.s201351029;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SellUI extends JFrame implements ActionListener{
	
	public SellUI(){
		this(150,0);
	}
	
	int index = 0;
	int totalPrice = 0;
	int stockCount = 0;
	int totalCount = 0;
	int row = 0;
	
	JLabel jlTotalpri;
	JLabel jlTotalCount;
	JTextField jfSbarcord;
	JTextField jfShidden;
	JPanel north;
	JPanel center;
	JPanel south;
	JButton btn_select;
	JButton btn_count;
	JButton btn_sell;
	JButton btn_delete;
	JButton btn_init;
	JButton btn_sList;
	JButton btn_pInsert;
	JTable table;
	DefaultTableModel smodel;
	Date today;
	
	public SellUI(int x, int y) {
		setTitle("�Ǹ�");
		this.setSize(1000, 650);
		this.setLocation(x, y);
		this.setLayout(new BorderLayout());
		
		north = new JPanel();
		center = new JPanel();
		south = new JPanel();
		
		//���� �Է� ���̾ƿ�
		north.setLayout(new FlowLayout());
		
		jfSbarcord = new JTextField(15);
		jfShidden = new JTextField(0);
		
		btn_select = new JButton("���ڵ� �Է�");
		btn_select.addActionListener(this);
		btn_init= new JButton("�ʱ�ȭ");
		btn_init.addActionListener(this);
		btn_delete= new JButton("����");
		btn_delete.addActionListener(this);
		north.add(jfSbarcord);
		north.add(btn_select);
		north.add(btn_init);
		north.add(btn_delete);
		north.setBackground(Color.LIGHT_GRAY);
		add(north, BorderLayout.NORTH);
				
				//�߾� ��ǰ���̺�
		center.setLayout(new GridLayout());
		String header[] = {"NO","���ڵ�","��ǰ��","����","����","���","���Դܰ�","���"};
		smodel = new DefaultTableModel(header,0) {
			public boolean isCellEditable(int row,int column) {
				return false;
			}
		};
		table = new JTable(smodel);
		JScrollPane scrollpane = new JScrollPane(table);
		table.getColumn("���").setWidth(0);
		table.getColumn("���").setMinWidth(0);
		table.getColumn("���").setMaxWidth(0);
		table.getColumn("���Դܰ�").setWidth(0);
		table.getColumn("���Դܰ�").setMinWidth(0);
		table.getColumn("���Դܰ�").setMaxWidth(0);
		table.getColumn("���ڵ�").setWidth(0);
		table.getColumn("���ڵ�").setMinWidth(0);
		table.getColumn("���ڵ�").setMaxWidth(0);
		center.add(scrollpane);
		add(center, BorderLayout.CENTER);
				
		//���� 
		south.setLayout(new GridLayout(2,4));
				
		jlTotalpri = new JLabel(""+totalPrice);
		jlTotalCount = new JLabel(""+totalCount);
		btn_sell = new JButton("�Ǹ�");
		btn_sell.addActionListener(this);
		btn_sList = new JButton("�Ǹų���");
		btn_sList.addActionListener(this);
		btn_pInsert = new JButton("��ǰ����");
		btn_pInsert.addActionListener(this);
//				btn_update = new JButton("������");
//				btn_update.addActionListener(this);
				
		btn_count = new JButton("����");
		btn_count.addActionListener(this);
		south.add(new Label("�հ� :"));
		south.add(jlTotalpri);
		south.add(new Label("�� ���� :"));
		south.add(jlTotalCount);
		south.add(btn_sell);
		south.add(btn_sList);
		south.add(btn_pInsert);
//				south.add(btn_insert);
//				south.add(btn_update);
		south.add(btn_count);
		add(south, BorderLayout.SOUTH);
		this.setVisible(true);
		mouseClik();
				
			}
	void mouseClik() {
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				table = (JTable) e.getComponent();
				smodel = (DefaultTableModel) table.getModel();
				
				String dk = (String)smodel.getValueAt(table.getSelectedRow(), 0);
				jfShidden.setText(dk);
			}
			
		});
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		SellDAO dao;
		switch(e.getActionCommand()) {
		case "���ڵ� �Է�":
			dao = new SellDAO();
			if(jfSbarcord.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "���ڵ带 �Է����ּ���.", "���", JOptionPane.INFORMATION_MESSAGE);
				return;
			}else {
				index = smodel.getRowCount()+1;
				String row[] = new String[6];
				String barcord = jfSbarcord.getText();
				row = dao.searchProduct(row, barcord);
				if(row[2] == null) {
					JOptionPane.showMessageDialog(null, "��ϵ��� ���� ��ǰ�Դϴ�.", "���", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				row[0] = index+"";
				row[4] = JOptionPane.showInputDialog("���� �Է�");
				totalPrice += Integer.parseInt(row[3]) * Integer.parseInt(row[4]);
				totalCount += Integer.parseInt(row[4]);
				jlTotalpri.setText(""+totalPrice);
				jlTotalCount.setText(""+totalCount);
				smodel.addRow(row);
				jfSbarcord.setText(null);
			}
			break;
		case "�ʱ�ȭ":
			smodel.setNumRows(0);
			totalPrice = 0;
			totalCount = 0;
			int i = 0;
			jlTotalpri.setText(""+totalPrice);
			jlTotalCount.setText(""+totalCount);
			jfSbarcord.setText(null);
			break;
		case "�Ǹ�":
			dao = new SellDAO();
			row = smodel.getRowCount();
			if(row == 0) {
				JOptionPane.showMessageDialog(null, "���� ��ǰ�� �����ϴ�.", "���", JOptionPane.INFORMATION_MESSAGE);
				return;
			}else {
				for(i = 0; i < row; i++) {
				//	"NO","group","���ڵ�","��ǰ��","����","����","���","���Դܰ�","���"
					String barcord = ""+ smodel.getValueAt(i, 1);
					String name = ""+smodel.getValueAt(i, 2);
					int price = Integer.parseInt(""+smodel.getValueAt(i, 3));
					int count = Integer.parseInt(""+smodel.getValueAt(i, 4));
					int stock = Integer.parseInt(""+smodel.getValueAt(i, 5));
					int bprice = Integer.parseInt(""+smodel.getValueAt(i, 6));
					int rCount = stock - count;  
					String group = "�Ǹ�";
					if(price == 0) {
						
					}else {
						dao.s_insert(group, barcord, name, price, bprice, count);
						dao.Refresh(rCount, barcord);
					}
				}
				smodel.setNumRows(0);
				totalPrice = 0;
				totalCount = 0;
				jlTotalpri.setText(""+totalPrice);
				jlTotalCount.setText(""+totalCount);
			}
			
			
			break;
			
		case "����" :
			//�� ���� ���� �ǵ��
//			"NO","���ڵ�","��ǰ��","����","����","���","���Դܰ�"
			//System.out.println(""+jfShidden.getText());
			// smodel.setValueAt(�����Ұ�, ���° ��, ���°Į��);
			int rowIndex = table.getSelectedRow();
			String mPrice = "" + smodel.getValueAt(rowIndex, 3);
			String mStock = "" + smodel.getValueAt(rowIndex, 4);		
//			System.out.println(mPrice);
//			System.out.println(mStock);
			smodel.setValueAt(0, rowIndex, 3); // ���� 
			smodel.setValueAt(0, rowIndex, 4); // ���
			smodel.setValueAt("����", Integer.parseInt(jfShidden.getText())-1, 7);
			//smodel.removeRow(Integer.parseInt(jfShidden.getText())-1);
			totalPrice -= Integer.parseInt(mPrice) * Integer.parseInt(mStock);
			totalCount -= Integer.parseInt(mStock);
			jlTotalpri.setText(""+totalPrice);
			jlTotalCount.setText(""+totalCount);
			jfSbarcord.setText(null);
			break;
			
		case "����" :
			int x = Integer.parseInt(jfSbarcord.getText());
			int y = table.getSelectedRow();
			String mPrice1 = "" + smodel.getValueAt(y, 3);
			String mStock1 = "" + smodel.getValueAt(y, 4);
			smodel.setValueAt(""+x, y, 4);
			String mPrice2 = "" + smodel.getValueAt(y, 3);
			String mStock2 = "" + smodel.getValueAt(y, 4);
			totalPrice -= Integer.parseInt(mPrice1) * Integer.parseInt(mStock1);
			totalCount -= Integer.parseInt(mStock1);
			totalPrice += Integer.parseInt(mPrice2) * Integer.parseInt(mStock2);
			totalCount += Integer.parseInt(mStock2);
			jlTotalpri.setText(""+totalPrice);
			jlTotalCount.setText(""+totalCount);
			jfSbarcord.setText(null);
			
			break;
			
		case "�Ǹų���" :
			new SellListUI();
			break;
			
		case "��ǰ����" :
			new ProductUI();
			break;
		}
	}


}
