package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import database.Transaksi;
import database.koneksi;

@SuppressWarnings("serial")
public class Kasir extends JFrame{
	private JLabel lblProduk = new JLabel("Nama Produk");
	private JLabel lblIDTrans = new JLabel("ID Transaksi");
	private JLabel lblJumlah = new JLabel("Jumlah");
	private JLabel lblHarga = new JLabel("Harga");
	private JLabel lblTotal = new JLabel("Total");
	private JLabel lblFullTotal = new JLabel("Grand Total");
	private JComboBox<String> CboNamaP = new JComboBox<String>();
	private JComboBox<String> CboIDp = new JComboBox<String>();
	private JComboBox<Integer> CboJumlah = new JComboBox<Integer>();
	private JTextField txtIDTrans = new JTextField();
	private JTextField txtHarga = new JTextField();
	private JTextField txtTotal = new JTextField();
	private JTextField txtFullTotal = new JTextField();
	private JButton BtnInput = new JButton("Masukkan");
	private JButton BtnClear = new JButton("Clear");
	private JButton BtnCetak = new JButton("Cetak");
	private JButton BtnKeluar = new JButton("Keluar");
	private JPanel gridBagLay = new JPanel(new GridBagLayout());
	private JPanel borderLay = new JPanel(new BorderLayout());
	private JPanel gridLay = new JPanel(new GridLayout(2,2));
	private JPanel ATAS = new JPanel();
	private JPanel BAWAH = new JPanel();
	private GridBagConstraints gridBagCons = new GridBagConstraints();
	private MainUtama lg = new MainUtama();
	private Dimension Layar = Toolkit.getDefaultToolkit().getScreenSize();
	private DefaultTableModel dataModel = new DefaultTableModel(){
		@Override
		public boolean isCellEditable(int row, int column) {
        return false;}
    };
    private JTable table = new JTable(dataModel);
	private Vector<String> v = new Vector<String>();
	private Vector<Integer> vi = new Vector<Integer>();
    private koneksi con = new koneksi();
    private Transaksi t = new Transaksi();
	private int GTotal=0,Harga=0;
	
	public Kasir(String user){
		super("Transaksi Penjualan ("+user+")");
		JLabel lblJumlahudul = new JLabel("<HTML><H3>PENJUALAN PRODUK</H3></HTML>");
		lblJumlahudul.setForeground(Color.CYAN);
		lblProduk.setForeground(Color.GREEN);
		lblIDTrans.setForeground(Color.GREEN);
		lblJumlah.setForeground(Color.GREEN);
		lblHarga.setForeground(Color.GREEN);
		lblTotal.setForeground(Color.GREEN);
		lblFullTotal.setForeground(Color.GREEN);
		
		txtIDTrans.setEditable(false); txtIDTrans.setBackground(Color.LIGHT_GRAY);
		txtHarga.setEditable(false); txtHarga.setBackground(Color.LIGHT_GRAY);
		txtTotal.setEditable(false); txtTotal.setBackground(Color.LIGHT_GRAY);
		txtFullTotal.setEditable(false); txtFullTotal.setBackground(Color.LIGHT_GRAY);
		txtIDTrans.setPreferredSize(new Dimension(100,24));
		CboJumlah.setPreferredSize(new Dimension(100,24));
		
		ATAS.setBackground(Color.BLACK);
		ATAS.add(lblJumlahudul);
		
		BAWAH.setBackground(Color.BLACK);
		BAWAH.add(BtnKeluar);
		
		ATAS.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		
		
		dataModel.addColumn("IDP");
		dataModel.addColumn("Nama Produk");
		dataModel.addColumn("Jumlah");
		dataModel.addColumn("Harga");
		dataModel.addColumn("Total");
	    table.getColumnModel().getColumn(1).setPreferredWidth(250);
	    JScrollPane scrollpane = new JScrollPane(table);
	    scrollpane.setPreferredSize(new Dimension(400, 330));
	    scrollpane.setBorder(BorderFactory.createLineBorder(Color.GREEN));
	    
	    rProduk();
		
	    gridBagCons.insets = new Insets(5, 5, 5, 5);
	    
	    gridBagCons.gridx = 0; gridBagCons.gridy = 0;
	    gridBagLay.add(lblIDTrans, gridBagCons);
	    gridBagCons.gridx = 1;
	    gridBagLay.add(txtIDTrans,gridBagCons);
	    
	    gridBagCons.gridx = 0; gridBagCons.gridy = 1;
	    gridBagLay.add(lblProduk, gridBagCons);
	    gridBagCons.gridx = 1;
	    gridBagCons.gridwidth = 3;
	    gridBagCons.fill = GridBagConstraints.HORIZONTAL;
	    gridBagLay.add(CboNamaP,gridBagCons);
	    
	    gridBagCons.gridx = 0; gridBagCons.gridy = 2;
	    gridBagCons.gridwidth = 1;
	    gridBagLay.add(lblHarga,gridBagCons);
	    gridBagCons.gridx = 1;
	    gridBagLay.add(txtHarga,gridBagCons);
	    gridBagCons.gridx = 2;
	    gridBagLay.add(lblJumlah,gridBagCons);
	    gridBagCons.gridx = 3;
	    gridBagLay.add(CboJumlah,gridBagCons);
	    
	    gridBagCons.gridx = 0; gridBagCons.gridy = 3;
	    gridBagLay.add(lblTotal,gridBagCons);
	    gridBagCons.gridx = 1;
	    gridBagLay.add(txtTotal,gridBagCons);
	    gridBagCons.gridx = 2;
	    gridBagLay.add(BtnInput,gridBagCons);
	    gridBagCons.gridx = 3;
	    gridBagLay.add(BtnClear,gridBagCons);
	    
	    gridBagCons.gridx = 0; gridBagCons.gridy = 4;
	    gridBagLay.add(lblFullTotal,gridBagCons);
	    gridBagCons.gridx = 1; gridBagCons.gridwidth = 3;
	    gridBagCons.fill = GridBagConstraints.HORIZONTAL;
	    gridBagLay.add(txtFullTotal,gridBagCons);
	    
	    gridBagCons.gridx = 1; gridBagCons.gridy = 5;
	    gridBagCons.gridwidth = 2;
	    BtnCetak.setPreferredSize(new Dimension(100, 50));
	    gridBagLay.add(BtnCetak,gridBagCons);
	    
	    gridBagLay.setBackground(Color.DARK_GRAY);
	    gridLay.setBackground(Color.DARK_GRAY);
	    gridLay.add(gridBagLay);
	    
	    
	    borderLay.add(scrollpane,BorderLayout.CENTER);
	    gridLay.add(borderLay);
	    
		Container k = getContentPane();
		k.setLayout(new BorderLayout());
		k.add(ATAS, BorderLayout.NORTH);
		k.add(gridLay, BorderLayout.CENTER);
		k.add(BAWAH,BorderLayout.SOUTH);
		
		setSize(500,600);
		setLocation(((Layar.width - getWidth()) / 2),(Layar.height - getHeight()) / 2);
		//setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		BtnKeluar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lg.setVisible(true);
				dispose();
			}
		});
		
		BtnInput.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				v.addElement(CboIDp.getSelectedItem().toString());
				v.addElement(CboNamaP.getSelectedItem().toString());
				vi.addElement(CboJumlah.getSelectedIndex());
				vi.addElement(Harga);
				v.addElement(txtTotal.getText());
				dataModel.addRow(new Object[]{v.elementAt(0),v.elementAt(1),vi.elementAt(0),vi.elementAt(1),v.elementAt(2)});
				v.removeAllElements();
				vi.removeAllElements();
				GTotal+=Harga*(CboJumlah.getSelectedIndex());
				txtFullTotal.setText(GTotal+"");
			}
		});
		
		BtnClear.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dataModel.setRowCount(0);
			}
		});
		
		BtnCetak.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(dataModel.getRowCount()>0){
					int baris;
					baris = dataModel.getRowCount();
					baris-=1;
					t.trx(txtIDTrans.getText(), user);
					while(baris>=0){
						String IDProduk = dataModel.getValueAt(baris, 0).toString();
						int Jumlah=(int)dataModel.getValueAt(baris, 2);
						int HargaBrg=(int)dataModel.getValueAt(baris, 3);
						t.Dtrx(txtIDTrans.getText(), IDProduk, Jumlah, HargaBrg);
						baris--;
					}
					Harga = 0; txtHarga.setText(Harga+"");
					GTotal = 0; txtFullTotal.setText(GTotal+"");
					rProduk();
					dataModel.setRowCount(0);
				}else{
					JOptionPane.showMessageDialog(null,"Masukkan transaksi ke tabel","Insert Rejected",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		CboNamaP.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Statement stmt;
				try {
					CboIDp.setSelectedIndex(CboNamaP.getSelectedIndex());
					stmt = con.getConnection().createStatement();
					ResultSet r = stmt.executeQuery("select Harga from Produk where IDP='"+CboIDp.getSelectedItem()+"'");
					while(r.next()){
						Harga = r.getInt(1);
						txtHarga.setText(Integer.toString(Harga));
					}
					stmt.close();
					stmt = con.getConnection().createStatement();
					r = stmt.executeQuery("select Stok from StokProduk where IDP='"+CboIDp.getSelectedItem()+"'");
					while(r.next()){
						int x=r.getInt(1),y=0;
						CboJumlah.removeAllItems();
						while(x>=0){
							CboJumlah.addItem(y);
							y++;
							x--;
						}
					}
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		CboJumlah.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtHarga.getText().equalsIgnoreCase(null) || txtHarga.getText().equalsIgnoreCase("")){
					txtTotal.setText("0");
				}else{
					txtTotal.setText(Harga*CboJumlah.getSelectedIndex()+"");
				}
			}
		});
	}

	private void rProduk() {
		try {
			CboNamaP.removeAllItems();
			Statement stmt = con.getConnection().createStatement();
			ResultSet r = stmt.executeQuery("select NamaP, IDP from Produk order by NamaP Asc");
			while(r.next()){
				v.addElement(r.getString(1));
				v.addElement(r.getString(2));
				CboNamaP.addItem(v.elementAt(0));
				CboIDp.addItem(v.elementAt(1));
				v.removeAllElements();
			}
			r=stmt.executeQuery("Select count(IDT) from Transaksi");
			while(r.next()){
				String nomor;
				if(r.getInt(1)<8){
					nomor="00"+(r.getInt(1)+1);
				}else if(r.getInt(1)<98){
					nomor="0"+(r.getInt(1)+1);
				}else{
					nomor=""+(r.getInt(1)+1);
				}
				txtIDTrans.setText("TRX"+nomor);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
