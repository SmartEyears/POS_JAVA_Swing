package kr.ac.jj.s201351029;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class SellListUI extends JFrame implements ActionListener{
	
	public SellListUI() {
		this(250,100);
	}
	int customer;
	int fMoney;
	
	JPanel north;
	JPanel center;
	JPanel south;
	JLabel jlLdate;
	JLabel jlLscount;
	JLabel jlLscount2;
	JLabel jlLmoney;
	JLabel jlLmoney2;
	JLabel jlLrmoney;
	JLabel jlLrmoney2;
	JButton btn_search;
	JButton btn_re;
	JDateChooser jdate;
	JComboBox<String> jcLdate;
	DefaultTableModel slModel;
	DefaultTableModel cModel;
	DefaultTableModel reModel;
	
	JTable mTable;
	JTable sTable;
	JTable rTable;
	
	Date today = new Date();
	
	public SellListUI(int x, int y) {
		setTitle("���� �Ǹų���");
		this.setSize(800,500);
		this.setLocation(x, y);
		this.setLayout(new BorderLayout());
		
		north = new JPanel();
		center = new JPanel();
		south = new JPanel();
		
		north.setLayout(new GridLayout(2,8));
		//��¥ �޺��ڽ� �ǸŰǼ� ���� ����
		SellDAO dao = new SellDAO();
		String [] sList = new String[1];
		jlLdate = new JLabel("��¥ :");
		jdate = new JDateChooser();
		jdate.setDate(today);
		jdate.getDateEditor().addPropertyChangeListener(jdate);
		jlLscount = new JLabel("�ǸŰǼ�");
		jlLscount2 = new JLabel(""+customer);
		jlLmoney = new JLabel("����");
		jlLmoney2 = new JLabel(""+fMoney);
		jlLrmoney = new JLabel("");
		jlLrmoney2 = new JLabel("");
		
		north.add(jlLdate);
		north.add(jdate);
		north.add(jlLscount);
		north.add(jlLscount2);
		north.add(jlLmoney);
		north.add(jlLmoney2);
		north.add(jlLrmoney);
		north.add(jlLrmoney2);
		north.add(new JLabel("�ǸŸ��"));
		north.add(new JLabel(""));
		north.add(new JLabel(""));
		north.add(new JLabel(""));
		north.add(new JLabel("��ǰ���"));
		north.add(new JLabel(""));
		north.add(new JLabel(""));
		north.add(new JLabel(""));
		
		
		north.setBackground(Color.LIGHT_GRAY);
		add(north, BorderLayout.NORTH);
		
		center.setLayout(new GridLayout(1,2));
		// �ð�   �Ѽ��� �Ѿ�
		String slHeader[] = {"�ð�","����","���ڵ�","��ǰ��","����","���԰�","����","�Ѿ�"};
		
		slModel = new DefaultTableModel(slHeader,0) {
			
		};
		mTable = new JTable(slModel);
		JScrollPane sl = new JScrollPane(mTable);
		mTable.getColumn("�ð�").setWidth(120);
		mTable.getColumn("�ð�").setMinWidth(120);
		mTable.getColumn("�ð�").setMaxWidth(120);
		
		mTable.getColumn("����").setWidth(30);
		mTable.getColumn("����").setMinWidth(30);
		mTable.getColumn("����").setMaxWidth(30);
		
		mTable.getColumn("����").setWidth(30);
		mTable.getColumn("����").setMinWidth(30);
		mTable.getColumn("����").setMaxWidth(30);
	
//		mTable.getColumn("���ڵ�").setWidth(0);
//		mTable.getColumn("���ڵ�").setMinWidth(0);
//		mTable.getColumn("���ڵ�").setMaxWidth(0);
//		
//		mTable.getColumn("����").setWidth(0);
//		mTable.getColumn("����").setMinWidth(0);
//		mTable.getColumn("����").setMaxWidth(0);
//		
//		mTable.getColumn("���԰�").setWidth(0);
//		mTable.getColumn("���԰�").setMinWidth(0);
//		mTable.getColumn("���԰�").setMaxWidth(0);
		
		
		
		String reHeader[] = {"�ð�","����","���ڵ�","��ǰ��","����","���԰�","����","�Ѿ�"};
		reModel = new DefaultTableModel(reHeader, 0) {
		};
		rTable = new JTable(reModel);
		JScrollPane re = new JScrollPane(rTable);
		
		rTable.getColumn("�ð�").setWidth(120);
		rTable.getColumn("�ð�").setMinWidth(120);
		rTable.getColumn("�ð�").setMaxWidth(120);
		
		rTable.getColumn("����").setWidth(30);
		rTable.getColumn("����").setMinWidth(30);
		rTable.getColumn("����").setMaxWidth(30);
		
		rTable.getColumn("����").setWidth(30);
		rTable.getColumn("����").setMinWidth(30);
		rTable.getColumn("����").setMaxWidth(30);
	
//		rTable.getColumn("���ڵ�").setWidth(0);
//		rTable.getColumn("���ڵ�").setMinWidth(0);
//		rTable.getColumn("���ڵ�").setMaxWidth(0);
//		
//		rTable.getColumn("����").setWidth(0);
//		rTable.getColumn("����").setMinWidth(0);
//		rTable.getColumn("����").setMaxWidth(0);
//		
//		rTable.getColumn("���԰�").setWidth(0);
//		rTable.getColumn("���԰�").setMinWidth(0);
//		rTable.getColumn("���԰�").setMaxWidth(0);
		
		String cHeader[] = {"�ð�"};
		cModel = new DefaultTableModel(cHeader,0) {
			};
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		for(int i = 0; i < slModel.getColumnCount(); i++) {
			mTable.getColumnModel().getColumn(i).setCellRenderer(dtcr);
		}	
		
		DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();
		dtcr2.setHorizontalAlignment(SwingConstants.CENTER);
		
		for(int i = 0; i < reModel.getColumnCount(); i++) {
			rTable.getColumnModel().getColumn(i).setCellRenderer(dtcr2);
		}	
		
		center.add(sl);
		center.add(re);
		add(center, BorderLayout.CENTER);
		
		south.setLayout(new GridLayout());
		//��ư ��ǰ '
		btn_search = new JButton("��ȸ");
		btn_search.addActionListener(this);
		btn_re = new JButton("��ǰ");
		btn_re.addActionListener(this);
		
		south.add(btn_search);
		south.add(btn_re);
		add(south, BorderLayout.SOUTH);
		
		this.setVisible(true);
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = format1.format(jdate.getDate());
		dao.customer(sDate, cModel);
		dao.sellList(sDate, slModel);
		dao.reList(sDate, reModel);	
		customer = cModel.getRowCount();
		jlLscount2.setText(""+customer);
		sales();
	}
	
//	DefaultTableCellRenderer renderer;
//	public void Renderer(JTable slJTable) {
//		renderer = (DefaultTableCellRenderer)
//				slJTable.getTableHeader().getDefaultRenderer();
//		renderer.setHorizontalAlignment(slJTable.CENTER);
//		
//	}
	public void sales() {
		SellDAO dao = new SellDAO();
		int fm_row = slModel.getRowCount();
//		System.out.println(fm_row);
//		System.out.println(""+slModel.getValueAt(0, 7)); 
		for(int i = 0; i < fm_row; i++) {
			fMoney += Integer.parseInt(""+ slModel.getValueAt(i, 7));
			}
		jlLmoney2.setText(""+fMoney);
		fMoney = 0;
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SellDAO dao;
		switch(e.getActionCommand()) {
		case "��ȸ" :
			dao = new SellDAO();
			if(jdate.getDate() == null) {
				JOptionPane.showMessageDialog(null, "��¥�� �Է����ּ���.", "�˸�", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
//			String time = ""+jdate.getDate();
//			System.out.println(time);
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			String sDate = format1.format(jdate.getDate());
			dao.customer(sDate, cModel);
			dao.sellList(sDate, slModel);
			dao.reList(sDate, reModel);	
			customer = cModel.getRowCount();
			jlLscount2.setText(""+customer);
			sales();
//			System.out.println(""+jdate.getDate());
			//�ߺ��� ���� �Ѱ���
			
			//SELECT date(s_date) date FROM Sell WHERE date(s_date) = '2019-05-13';
			break;
		case "��ǰ" :
			dao = new SellDAO();
			int rowIndex = mTable.getSelectedRow();
			String reTime =  "" + slModel.getValueAt(rowIndex,0);
			dao.Return(reTime);
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
			String sDate1 = format2.format(jdate.getDate());
			dao.sellList(sDate1, slModel);
			dao.reList(sDate1, reModel);
			dao.customer(sDate1, cModel);
			customer = cModel.getRowCount();
			jlLscount2.setText(""+customer);
			sales();
//			System.out.println(reTime);
			break;
		}
	}

}
