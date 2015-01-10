package GUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import database.*;

@SuppressWarnings("serial")
public class FormProduk extends JFrame{
	
	private JPanel ATAS = new JPanel();
	private JPanel BAWAH = new JPanel();
	private JPanel TENGAH = new JPanel();
	private JButton BtnUtama = new JButton("Menu Utama");
	private Dimension Layar = Toolkit.getDefaultToolkit().getScreenSize();
	
	private JButton BtnTambah = new JButton();
	private JButton BtnUbah = new JButton();
	private JButton BtnHapus = new JButton();
	private JButton Tbh = new JButton();
	private JButton Clr = new JButton();
	private JLabel lblTbh = new JLabel();
	private JLabel Tambah = new JLabel("<HTML><H3>TAMBAH</H3></HTML>");
	private JLabel Ubah = new JLabel("<HTML><H3>UBAH</H3></HTML>");
	private JLabel Hapus = new JLabel("<HTML><H3>HAPUS</H3></HTML>");
	private JTextField txtNamaP = new JTextField();
	private JComboBox<String> CboS = new JComboBox<String>();
	private JComboBox<String> CboID = new JComboBox<String>();
	private JComboBox<String> CboIDS = new JComboBox<String>();
	private JTextField txtH = new JTextField(8);
	private JTextField txtID = new JTextField(6);
	private JLabel IDP = new JLabel("ID Produk");
	private JLabel NamaP = new JLabel("Nama Produk");
	private JLabel NamaS = new JLabel("Nama Supplier");
	private JLabel Harga = new JLabel("Harga");
	private JPanel bl = new JPanel(new BorderLayout());
	private JPanel PanelID = new JPanel();
	private JPanel gbl = new JPanel(new GridBagLayout());
	private JPanel gl = new JPanel(new GridLayout(1,3,15,10));
	private GridBagConstraints gbc = new GridBagConstraints();
	private int cek;
	private DefaultTableModel dataModel = new DefaultTableModel(){
		@Override
		public boolean isCellEditable(int row, int column) {
        return false;}
    };
    private JTable table = new JTable(dataModel);
    private Vector<String> v = new Vector<>();
    private koneksi con = new koneksi();
    private Produk p = new Produk();
    
	public FormProduk(){
		super("Maintenance Produk");
		
		txtID.setEditable(false);
		
		cek=1;
		lblTbh.setText("Tambah");
		gbl.setBackground(Color.DARK_GRAY);
		gl.setBackground(Color.WHITE);
		
		BtnTambah.setBackground(Color.DARK_GRAY);;
		BtnUbah.setBackground(Color.BLACK);
		BtnHapus.setBackground(Color.BLACK);
		
		Tambah.setForeground(Color.GREEN);
		Ubah.setForeground(Color.GREEN);
		Hapus.setForeground(Color.GREEN);
		IDP.setForeground(Color.GREEN);
		NamaP.setForeground(Color.GREEN);
		NamaS.setForeground(Color.GREEN);
		Harga.setForeground(Color.GREEN);
		
		BtnTambah.add(Tambah);
		BtnUbah.add(Ubah);
		BtnHapus.add(Hapus);
		Tbh.add(lblTbh);
		Clr.add(new JLabel("Clear"));
		CboID.setPreferredSize(new Dimension(70,24));
		dataModel.addColumn("ID Produk");
		dataModel.addColumn("ID Supplier");
		dataModel.addColumn("Nama Produk");
		dataModel.addColumn("Harga");
	    table.getColumnModel().getColumn(2).setPreferredWidth(400);
	    JScrollPane scrollpane = new JScrollPane(table);
	    scrollpane.setPreferredSize(new Dimension(480, 330));
	    
	    PanelID.add(txtID);
	    
		gbc.insets = new Insets(5, 5, 5, 5);
		
		gl.add(BtnTambah);
		gl.add(BtnUbah);
		gl.add(BtnHapus);
		
		gbc.gridx = 0; gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbl.add(IDP, gbc);
		gbc.gridx = 1;
		gbl.add(PanelID, gbc);
		gbc.gridx = 2;
		gbl.add(NamaS, gbc);
		gbc.gridx = 3;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbl.add(CboS, gbc);
		
		gbc.gridx=0; gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbl.add(NamaP, gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbl.add(txtNamaP, gbc);
		
		gbc.gridx=0; gbc.gridy = 2;
		gbc.gridwidth=1;
		gbl.add(Harga, gbc);
		gbc.gridx=1;
		gbc.gridwidth=2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbl.add(txtH, gbc);
		gbc.gridwidth=1;
		gbc.gridx=3;
		gbl.add(Tbh, gbc);
		gbc.gridx=4;
		gbl.add(Clr, gbc);
		
		gbc.gridx=0; gbc.gridy = 3;
		gbc.gridwidth = 5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbl.add(scrollpane,gbc);
		
		bl.add(gl,BorderLayout.NORTH);
		bl.add(new JLabel(),BorderLayout.CENTER);
		bl.add(gbl,BorderLayout.SOUTH);
		
		//-----------------------------------------------------------------------------
		JLabel lblJudul = new JLabel("<HTML><H1>MAINTENANCE PRODUK</H1></HTML>");
		
		
		TENGAH.setBackground(Color.BLACK);
		BAWAH.setBackground(Color.BLACK);
		
		
		ATAS.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		TENGAH.setBorder(BorderFactory.createLineBorder(Color.GREEN));
				
		
		ATAS.add(lblJudul);
		BAWAH.add(BtnUtama);
		TENGAH.add(getBL());
		
		Container k = getContentPane();
		k.setLayout(new BorderLayout());
		k.add(ATAS, BorderLayout.NORTH);
		k.add(BAWAH, BorderLayout.SOUTH);
		k.add(TENGAH, BorderLayout.CENTER);
		
		setSize(550, 625);
		setLocation((Layar.width - getWidth()) / 2, (Layar.height - getHeight()) / 2);
		
		//----------------------------------------------------------------------------
		BtnTambah.addActionListener(new ActionListener()
		{
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BtnTambah.setBackground(Color.DARK_GRAY);
				BtnUbah.setBackground(Color.BLACK);
				BtnHapus.setBackground(Color.BLACK);
				txtNamaP.enable(true);
				lblTbh.setText("Tambah");
				txtH.setEnabled(true);
				cek = 1;
				PanelID.removeAll();
				PanelID.add(txtID);
				PanelID.validate();
				PanelID.repaint();
				txtNamaP.setText("");
			}
		});
		
		BtnUbah.addActionListener(new ActionListener()
		{
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BtnTambah.setBackground(Color.BLACK);
				BtnUbah.setBackground(Color.DARK_GRAY);
				BtnHapus.setBackground(Color.BLACK);
				lblTbh.setText("Ubah");
				txtNamaP.enable(true);
				txtH.setEnabled(true);
				cek = 2;
				PanelID.removeAll();
				PanelID.add(CboID);
				PanelID.validate();
				PanelID.repaint();
				bl.repaint();
			}
		});
		
		BtnHapus.addActionListener(new ActionListener()
		{
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BtnTambah.setBackground(Color.BLACK);
				BtnUbah.setBackground(Color.BLACK);
				BtnHapus.setBackground(Color.DARK_GRAY);
				lblTbh.setText("Hapus");
				txtNamaP.enable(false);
				txtH.setEnabled(false);
				cek = 3;
				PanelID.removeAll();
				PanelID.add(CboID);
				PanelID.validate();
				PanelID.repaint();
			}
		});
		//--------------------------------------------------------------------------
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
		//---------------------------------------------------------------------------
		Clr.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtNamaP.setText("");
				CboID.setSelectedItem("");
				txtH.setText("");
				CboS.setSelectedItem("");
			}
		});
		
		Tbh.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(cek==1){
					if(txtNamaP.getText().isEmpty() || txtH.getText().isEmpty()){
						JOptionPane.showMessageDialog(null,"isi semua field","Error",JOptionPane.ERROR_MESSAGE);
					}else if(txtID.getDocument().getLength()>6 || txtID.getDocument().getLength()<6){
						JOptionPane.showMessageDialog(null,"ID harus berisi 6 karakter","Error",JOptionPane.ERROR_MESSAGE);
					}else{
						try {
							int a=0;
							Statement stmt = con.getConnection().createStatement();
							ResultSet r = stmt.executeQuery("select count(IDP) from Produk where NamaP='"+txtNamaP.getText()+"' or IDP='"+txtID.getText()+"'");
							while(r.next()){
								a=r.getInt(1);
							}
							if(a==0){
								p.TambahProduk(txtID.getText(), txtNamaP.getText(), Integer.parseInt(txtH.getText()), CboIDS.getSelectedItem().toString());
								JOptionPane.showMessageDialog(null,"Berhasil");
								rTabel();
							}else{
								JOptionPane.showMessageDialog(null,"ID/Nama Produk sudah ada","Error",JOptionPane.ERROR_MESSAGE);
							}
							stmt.close();
							txtID.setText("");
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}else if(cek==2){
					if(txtH.getText().isEmpty()){
						JOptionPane.showMessageDialog(null,"isi semua field","Error",JOptionPane.ERROR_MESSAGE);
					}else{
						String x;
						if(txtNamaP.getText().isEmpty()){
							x=null;
						}else{
							x=txtNamaP.getText();
						}
						p.UbahProduk(CboID.getSelectedItem().toString(), x, Integer.parseInt(txtH.getText()));
						rTabel();
						JOptionPane.showMessageDialog(null,"Berhasil");
					}
				}else{
					p.HapusProduk(CboID.getSelectedItem().toString());
					rTabel();
					JOptionPane.showMessageDialog(null,"Berhasil");
				}
			}
		});
		//--------------------------------------------------------------------------
		CboS.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					CboID.removeAllItems();
					CboIDS.setSelectedIndex(CboS.getSelectedIndex());
					Statement stmt = con.getConnection().createStatement();
					ResultSet r = stmt.executeQuery("select IDP from Produk where IDS='"+CboIDS.getSelectedItem()+"' order by IDP ASC");
					while(r.next()){
						CboID.addItem(r.getString(1));
					}
					CboID.setSelectedIndex(0);
					
					int a=1,b=1;
					String x="";
					while(a==1){
						String y = "00"+b;
						y.substring(y.length()-2, y.length());
						x=CboIDS.getSelectedItem().toString()+y;
						r = stmt.executeQuery("select count(IDP) from Produk where IDP='"+x+"'");
						while(r.next()){
							a=r.getInt(1);
						}
						b++;
					}
					txtID.setText(x);
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		CboID.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(CboID.getItemCount()>0){
					Statement stmt;
					if(cek==1){
						txtNamaP.setText("");
					}else{
						try {
							stmt = con.getConnection().createStatement();
							ResultSet r = stmt.executeQuery("select NamaP from Produk where IDP='"+CboID.getSelectedItem().toString()+"'");
							while(r.next()){
								txtNamaP.setText(r.getString(1));
							}
							stmt.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
	}
	
	private void rTabel() {
		dataModel.setRowCount(0);
		try {
			Statement stmt = con.getConnection().createStatement();
			ResultSet r = stmt.executeQuery("select * from Produk order by IDS Asc");
			while(r.next()){
				v.addElement(r.getString(1));
				v.addElement(r.getString(2));
				v.addElement(r.getString(3));
				v.addElement(Integer.toString(r.getInt(4)));
				dataModel.addRow(new Object[]{v.elementAt(0),v.elementAt(1),v.elementAt(2),v.elementAt(3)});
				v.removeAllElements();
			}
			
			r = stmt.executeQuery("select * from Supplier order by NamaS Asc");
			while(r.next()){
				v.addElement(r.getString(1));
				v.addElement(r.getString(2));
				CboIDS.addItem(v.elementAt(0));
				CboS.addItem(v.elementAt(1));
				v.removeAllElements();
			}
			CboS.setSelectedIndex(0);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Component getBL(){
		rTabel();
		txtNamaP.setText("");
		bl.repaint();
		return bl;
	}
}
