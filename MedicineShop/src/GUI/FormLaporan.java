package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import database.koneksi;

@SuppressWarnings("serial")
public class FormLaporan extends JFrame {
	
	private JPanel ATAS = new JPanel();
	private JPanel BAWAH = new JPanel();
	private JPanel TENGAH = new JPanel();
	private JButton BtnUtama = new JButton("Menu Utama");
	private Dimension Layar1 = Toolkit.getDefaultToolkit().getScreenSize();
	
	private JPanel gl = new JPanel(new GridLayout(2,1));
	private JPanel gbl = new JPanel(new GridBagLayout());
	private JPanel gbl2 = new JPanel(new GridBagLayout());
	private GridBagConstraints gbc = new GridBagConstraints();
	private JComboBox<String> CboID = new JComboBox<String>();
	private JComboBox<String> CboJumlah = new JComboBox<String>();
	private JTextField txtACC = new JTextField(6);
	private JLabel lblID = new JLabel("ID USER : ");
	private JLabel lblT = new JLabel("<HTML><H3>SEMUA PENJUALAN</H3></HTML>");
	private DefaultTableModel dataModel1 = new DefaultTableModel(){
		@Override
		public boolean isCellEditable(int row, int column) {
        return false;}
    };
    private JTable table1 = new JTable(dataModel1);
    private DefaultTableModel dataModel2 = new DefaultTableModel(){
		@Override
		public boolean isCellEditable(int row, int column) {
        return false;}
    };
    private JTable table2 = new JTable(dataModel2);
    private koneksi con = new koneksi();
	private Vector<String> v = new Vector<String>(6);
	
	public FormLaporan(){
		lblID.setForeground(Color.GREEN);
		lblT.setForeground(Color.GREEN);
		
		CboID.setPreferredSize(new Dimension(100,24));
		
		txtACC.setEditable(false);
		rCbo();
		
		dataModel1.addColumn("IDT");
		dataModel1.addColumn("Tanggal");
		dataModel1.addColumn("Nama Barang");
		dataModel1.addColumn("Harga");
		dataModel1.addColumn("Jumlah");
	    table1.getColumnModel().getColumn(2).setPreferredWidth(200);
	    JScrollPane scrollpane1 = new JScrollPane(table1);
	    scrollpane1.setPreferredSize(new Dimension(500,200));
	    
	    dataModel2.addColumn("IDT");
	    dataModel2.addColumn("Tanggal");
		dataModel2.addColumn("Kasir");
	    dataModel2.addColumn("Nama Barang");
		dataModel2.addColumn("Harga");
		dataModel2.addColumn("Jumlah");
	    table2.getColumnModel().getColumn(3).setPreferredWidth(200);
	    JScrollPane scrollpane2 = new JScrollPane(table2);
	    scrollpane2.setPreferredSize(new Dimension(500,200));
	    
	    gbc.insets = new Insets(0, 20, 0, 20);
	    
	    gbc.gridx = 0; gbc.gridy = 0;
	    gbl.add(lblID, gbc);
	    gbc.gridx = 1;
	    gbl.add(CboID, gbc);
	    gbc.gridx = 2;
	    gbl.add(txtACC, gbc);
	    
	    gbc.gridx = 0; gbc.gridy = 1;
	    gbc.gridwidth = 4;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbl.add(scrollpane1, gbc);
	    
	    gbc.gridx = 0; gbc.gridy = 0;
	    gbc.gridwidth = 1;
	    gbl2.add(lblT, gbc);
	    
	    gbc.gridx = 0; gbc.gridy = 1;
	    gbc.gridwidth = 4;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbl2.add(scrollpane2, gbc);
	    
	    gbl.setBackground(Color.darkGray);
	    gbl2.setBackground(Color.darkGray);
	    gl.setBackground(Color.darkGray);
	    gl.add(gbl);
	    gl.add(gbl2);
	    
	  //-----------------------------------------------------------------------------
		JLabel lblJudul = new JLabel("<HTML><H1>MAINTENANCE PRODUK</H1></HTML>");
		
		
		TENGAH.setBackground(Color.BLACK);
		BAWAH.setBackground(Color.BLACK);
		
		
		ATAS.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		TENGAH.setBorder(BorderFactory.createLineBorder(Color.GREEN));
				
		
		ATAS.add(lblJudul);
		BAWAH.add(BtnUtama);
		TENGAH.add(getGBL());
		
		Container k = getContentPane();
		k.setLayout(new BorderLayout());
		k.add(ATAS, BorderLayout.NORTH);
		k.add(BAWAH, BorderLayout.SOUTH);
		k.add(TENGAH, BorderLayout.CENTER);
		
		setSize(550, 625);
		setLocation((Layar1.width - getWidth()) / 2, (Layar1.height - getHeight()) / 2);
		
		//----------------------------------------------------------------------------
		
		//================================================================================
		BtnUtama.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Menu Utama = new Menu ("WELCOME ADMIN");
				Utama.setVisible(true);
				dispose();
				
			}
		});
		//=================================================================================
	    CboID.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				if(CboID.getItemCount()>1){
					CboJumlah.setSelectedIndex(CboID.getSelectedIndex());
					if(Integer.parseInt(CboJumlah.getSelectedItem().toString())==1){
						txtACC.setText("Admin");
					}else{
						txtACC.setText("Kasir");
					}
					dataModel1.setRowCount(0);
					Statement stmt,stmt2;
					try {
						stmt = con.getConnection().createStatement();
						stmt2 = con.getConnection().createStatement();
						ResultSet r = stmt.executeQuery("select * from Transaksi where IDU='"+CboID.getSelectedItem().toString()+"'");
						while(r.next()){
							ResultSet r2 = stmt2.executeQuery("select NamaP, d.Harga, Jumlah from dTransaksi d join Produk p on d.IDP=p.IDP where IDT='"+r.getString(1)+"'");
							while(r2.next()){
								v.addElement(r.getString(1));
								v.addElement(r.getString(2));
								v.addElement(r2.getString(1));
								v.addElement(Integer.toString(r2.getInt(2)));
								v.addElement(Integer.toString(r2.getInt(3)));
								dataModel1.addRow(new Object[]{v.elementAt(0),v.elementAt(1),v.elementAt(2),v.elementAt(3),v.elementAt(4)});
								v.removeAllElements();
							}
						}
						stmt.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}

	private void rCbo() {
		try {
			CboID.removeAllItems();
			CboJumlah.removeAllItems();
			Statement stmt = con.getConnection().createStatement();
			ResultSet r = stmt.executeQuery("select IDU,Jenis from Users order by IDU Asc");
			while(r.next()){
				CboID.addItem(r.getString(1));
				CboJumlah.addItem(Integer.toString(r.getInt(2)));
			}
			CboID.setSelectedIndex(0);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void rTbl(){
		try {
			Statement stmt = con.getConnection().createStatement();
			@SuppressWarnings("unused")
			Statement stmt2 = con.getConnection().createStatement();
			ResultSet r = stmt.executeQuery("select t.IDT, Tgl, IDU, NamaP, d.Harga, Jumlah from Transaksi t join dTransaksi d on t.IDT=d.IDT join Produk p on d.IDP=p.IDP");
			while(r.next()){
				v.addElement(r.getString(1));
				v.addElement(r.getString(2));
				v.addElement(r.getString(3));
				v.addElement(r.getString(4));
				v.addElement(Integer.toString(r.getInt(5)));
				v.addElement(Integer.toString(r.getInt(6)));
				dataModel2.addRow(new Object[]{v.elementAt(0),v.elementAt(1),v.elementAt(2),v.elementAt(3),v.elementAt(4),v.elementAt(5)});
				v.removeAllElements();
			}
			stmt.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public Component getGBL(){
		rCbo();
		rTbl();
		return gl;
	}
}
