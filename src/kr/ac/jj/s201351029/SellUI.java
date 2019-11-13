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
		setTitle("판매");
		this.setSize(1000, 650);
		this.setLocation(x, y);
		this.setLayout(new BorderLayout());
		
		north = new JPanel();
		center = new JPanel();
		south = new JPanel();
		
		//북쪽 입력 레이아웃
		north.setLayout(new FlowLayout());
		
		jfSbarcord = new JTextField(15);
		jfShidden = new JTextField(0);
		
		btn_select = new JButton("바코드 입력");
		btn_select.addActionListener(this);
		btn_init= new JButton("초기화");
		btn_init.addActionListener(this);
		btn_delete= new JButton("삭제");
		btn_delete.addActionListener(this);
		north.add(jfSbarcord);
		north.add(btn_select);
		north.add(btn_init);
		north.add(btn_delete);
		north.setBackground(Color.LIGHT_GRAY);
		add(north, BorderLayout.NORTH);
				
				//중앙 상품테이블
		center.setLayout(new GridLayout());
		String header[] = {"NO","바코드","상품명","가격","수량","재고","매입단가","비고"};
		smodel = new DefaultTableModel(header,0) {
			public boolean isCellEditable(int row,int column) {
				return false;
			}
		};
		table = new JTable(smodel);
		JScrollPane scrollpane = new JScrollPane(table);
		table.getColumn("재고").setWidth(0);
		table.getColumn("재고").setMinWidth(0);
		table.getColumn("재고").setMaxWidth(0);
		table.getColumn("매입단가").setWidth(0);
		table.getColumn("매입단가").setMinWidth(0);
		table.getColumn("매입단가").setMaxWidth(0);
		table.getColumn("바코드").setWidth(0);
		table.getColumn("바코드").setMinWidth(0);
		table.getColumn("바코드").setMaxWidth(0);
		center.add(scrollpane);
		add(center, BorderLayout.CENTER);
				
		//남쪽 
		south.setLayout(new GridLayout(2,4));
				
		jlTotalpri = new JLabel(""+totalPrice);
		jlTotalCount = new JLabel(""+totalCount);
		btn_sell = new JButton("판매");
		btn_sell.addActionListener(this);
		btn_sList = new JButton("판매내역");
		btn_sList.addActionListener(this);
		btn_pInsert = new JButton("상품관리");
		btn_pInsert.addActionListener(this);
//				btn_update = new JButton("재고관리");
//				btn_update.addActionListener(this);
				
		btn_count = new JButton("수량");
		btn_count.addActionListener(this);
		south.add(new Label("합계 :"));
		south.add(jlTotalpri);
		south.add(new Label("총 수량 :"));
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
		case "바코드 입력":
			dao = new SellDAO();
			if(jfSbarcord.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "바코드를 입력해주세요.", "경고", JOptionPane.INFORMATION_MESSAGE);
				return;
			}else {
				index = smodel.getRowCount()+1;
				String row[] = new String[6];
				String barcord = jfSbarcord.getText();
				row = dao.searchProduct(row, barcord);
				if(row[2] == null) {
					JOptionPane.showMessageDialog(null, "등록되지 않은 상품입니다.", "경고", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				row[0] = index+"";
				row[4] = JOptionPane.showInputDialog("수량 입력");
				totalPrice += Integer.parseInt(row[3]) * Integer.parseInt(row[4]);
				totalCount += Integer.parseInt(row[4]);
				jlTotalpri.setText(""+totalPrice);
				jlTotalCount.setText(""+totalCount);
				smodel.addRow(row);
				jfSbarcord.setText(null);
			}
			break;
		case "초기화":
			smodel.setNumRows(0);
			totalPrice = 0;
			totalCount = 0;
			int i = 0;
			jlTotalpri.setText(""+totalPrice);
			jlTotalCount.setText(""+totalCount);
			jfSbarcord.setText(null);
			break;
		case "판매":
			dao = new SellDAO();
			row = smodel.getRowCount();
			if(row == 0) {
				JOptionPane.showMessageDialog(null, "결제 상품이 없습니다.", "경고", JOptionPane.INFORMATION_MESSAGE);
				return;
			}else {
				for(i = 0; i < row; i++) {
				//	"NO","group","바코드","상품명","가격","수량","재고","매입단가","비고"
					String barcord = ""+ smodel.getValueAt(i, 1);
					String name = ""+smodel.getValueAt(i, 2);
					int price = Integer.parseInt(""+smodel.getValueAt(i, 3));
					int count = Integer.parseInt(""+smodel.getValueAt(i, 4));
					int stock = Integer.parseInt(""+smodel.getValueAt(i, 5));
					int bprice = Integer.parseInt(""+smodel.getValueAt(i, 6));
					int rCount = stock - count;  
					String group = "판매";
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
			
		case "삭제" :
			//총 가격 수량 피드백
//			"NO","바코드","상품명","가격","수량","재고","매입단가"
			//System.out.println(""+jfShidden.getText());
			// smodel.setValueAt(변경할값, 몇번째 줄, 몇번째칼럼);
			int rowIndex = table.getSelectedRow();
			String mPrice = "" + smodel.getValueAt(rowIndex, 3);
			String mStock = "" + smodel.getValueAt(rowIndex, 4);		
//			System.out.println(mPrice);
//			System.out.println(mStock);
			smodel.setValueAt(0, rowIndex, 3); // 가격 
			smodel.setValueAt(0, rowIndex, 4); // 재고
			smodel.setValueAt("삭제", Integer.parseInt(jfShidden.getText())-1, 7);
			//smodel.removeRow(Integer.parseInt(jfShidden.getText())-1);
			totalPrice -= Integer.parseInt(mPrice) * Integer.parseInt(mStock);
			totalCount -= Integer.parseInt(mStock);
			jlTotalpri.setText(""+totalPrice);
			jlTotalCount.setText(""+totalCount);
			jfSbarcord.setText(null);
			break;
			
		case "수량" :
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
			
		case "판매내역" :
			new SellListUI();
			break;
			
		case "상품관리" :
			new ProductUI();
			break;
		}
	}


}
