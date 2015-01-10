package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import database.*;

@SuppressWarnings("serial")
public class FormStok extends JFrame {
	
	private JPanel ATAS = new JPanel();
	private JPanel BAWAH = new JPanel();
	private JPanel TENGAH = new JPanel();
	private JButton BtnUtama = new JButton("Menu Utama");
	
	private JButton BtnTambah = new JButton();
	private JButton BtnKurang = new JButton();
	private JButton BtnHapus = new JButton();
	private JButton Tbh = new JButton();
	private JButton Clr = new JButton();
	private JLabel lblTbh = new JLabel();
	private JLabel Tambah = new JLabel("<HTML><H3>TAMBAH</H3></HTML>");
	private JLabel Kurang = new JLabel("<HTML><H3>KURANG</H3></HTML>");
	private JLabel Hapus = new JLabel("");
	private JComboBox<String> CboNamaP = new JComboBox<String>();
	private JComboBox<String> CboIDP = new JComboBox<String>();
	private JComboBox<String> CboS = new JComboBox<String>();
	private JComboBox<String> CboIDS = new JComboBox<String>();
	private JTextField txtID = new JTextField(8);
	private JTextField txtS = new JTextField(8);
	private JLabel IDP = new JLabel("ID Produk");
	private JLabel NamaP = new JLabel("Nama Produk");
	private JLabel NamaS = new JLabel("Nama Supplier");
	private JLabel StokP = new JLabel("Stok");
	private Dimension Layar = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel bl = new JPanel(new BorderLayout());
	private JPanel gbl = new JPanel(new GridBagLayout());
	private GridBagConstraints gbc = new GridBagConstraints();
	private JPanel gl = new JPanel(new GridLayout(1,3,15,10));
	private int pos;
	private DefaultTableModel dataModel = new DefaultTableModel(){
		@Override
		public boolean isCellEditable(int row, int column) {
        return false;}
    };
    private JTable table = new JTable(dataModel);
    private Vector<String> v = new Vector<>();
    private koneksi con = new koneksi();
	private StokProduk sp = new StokProduk();
    
	public FormStok(){
		super("Maintenance Produk");
		
		NumberFormatter nf = new NumberFormatter(NumberFormat.getIntegerInstance());
		nf.setAllowsInvalid(true);
		nf.setMinimum(0);
		txtS = new JFormattedTextField(nf);
		
		pos=1;
		lblTbh.setText("Tambah");
		gbl.setBackground(Color.DARK_GRAY);
		gl.setBackground(Color.WHITE);
		
		BtnTambah.setBackground(Color.DARK_GRAY);;
		BtnKurang.setBackground(Color.BLACK);
		BtnHapus.setBackground(Color.BLACK);
		
		Tambah.setForeground(Color.GREEN);
		Kurang.setForeground(Color.GREEN);
		Hapus.setForeground(Color.GREEN);
		IDP.setForeground(Color.GREEN);
		NamaP.setForeground(Color.GREEN);
		NamaS.setForeground(Color.GREEN);
		StokP.setForeground(Color.GREEN);
		
		BtnTambah.add(Tambah);
		BtnKurang.add(Kurang);
		BtnHapus.add(Hapus);
		Tbh.add(lblTbh);
		Clr.add(new JLabel("Clear"));
		txtID.setEditable(false);
		txtID.setBackground(Color.LIGHT_GRAY);
		
		BtnHapus.setEnabled(false);
		
		dataModel.addColumn("ID Produk");
		dataModel.addColumn("ID Supplier");
		dataModel.addColumn("Nama Produk");
		dataModel.addColumn("Stok");
	    table.getColumnModel().getColumn(2).setPreferredWidth(400);
	    
	    rStok();
	    
	    JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setPreferredSize(new Dimension(480, 330));
	    
		gbc.insets = new Insets(5, 5, 5, 5);
		
		gl.add(BtnTambah);
		gl.add(BtnKurang);
		gl.add(BtnHapus);
		
		gbc.gridx = 0; gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbl.add(IDP, gbc);
		gbc.gridx = 1;
		gbl.add(txtID, gbc);
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
		gbl.add(CboNamaP, gbc);
		
		gbc.gridx=0; gbc.gridy = 2;
		gbc.gridwidth=1;
		gbl.add(StokP, gbc);
		gbc.gridx=1;
		gbc.gridwidth=2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbl.add(txtS, gbc);
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
		JLabel lblJudul = new JLabel("<HTML><H2>MAINTENANCE SUPPLIER</H2></HTML>");
		
		
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
		
		setSize(550, 650);
		setLocation((Layar.width - getWidth()) / 2, (Layar.height - getHeight()) / 2);
		
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
		//-----------------------------------------------------------------------------
		BtnTambah.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BtnTambah.setBackground(Color.DARK_GRAY);
				BtnKurang.setBackground(Color.BLACK);
				BtnHapus.setBackground(Color.BLACK);
				lblTbh.setText("Tambah");
				txtS.setEnabled(true);
				pos = 1;
			}
		});
		
		BtnKurang.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BtnTambah.setBackground(Color.BLACK);
				BtnKurang.setBackground(Color.DARK_GRAY);
				BtnHapus.setBackground(Color.BLACK);
				lblTbh.setText("Kurang");
				txtS.setEnabled(true);
				pos = 2;
			}
		});
		
		//--------------------------------------------------------------------------
		Clr.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtID.setText("");
				txtS.setText(null);
			}
		});
		
		Tbh.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(txtS.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"Masukkan Jumlah","Error",JOptionPane.ERROR_MESSAGE);
				}else{
					if(pos==1){
						int a = Integer.parseInt(txtS.getText());
						sp.tambah(txtID.getText(), a);
						JOptionPane.showMessageDialog(null,"Berhasil");
					}else{
						try {
							Statement stmt = con.getConnection().createStatement();
							ResultSet r = stmt.executeQuery("select Stok from StokProduk where IDP='"+txtID.getText()+"'");
							while(r.next()){
								if(Integer.parseInt(txtS.getText())>r.getInt(1)){
									JOptionPane.showMessageDialog(null,"Jumlah melebihi stok","Error",JOptionPane.ERROR_MESSAGE);
								}else{
									sp.kurang(txtID.getText(), Integer.parseInt(txtS.getText()));
									JOptionPane.showMessageDialog(null,"Berhasil");
								}
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					rTabel();
				}
			}
		});
		
		CboNamaP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(CboIDP.getItemCount()>0){
					CboIDP.setSelectedIndex(CboNamaP.getSelectedIndex());
					txtID.setText(CboIDP.getSelectedItem().toString());
				}
			}
		});
		
		CboS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtID.setText("");
				CboIDP.removeAllItems();
				CboNamaP.removeAllItems();
				CboIDS.setSelectedIndex(CboS.getSelectedIndex());
				try {
					Statement stmt = con.getConnection().createStatement();
					ResultSet r = stmt.executeQuery("select * from Produk where IDS='"+CboIDS.getSelectedItem().toString()+"'");
					while(r.next()){
						CboIDP.addItem(r.getString(1));
						CboNamaP.addItem(r.getString(3));
					}
					CboNamaP.setSelectedIndex(0);
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void rStok() {
		dataModel.setRowCount(0);
		try {
			Statement stmt = con.getConnection().createStatement();
			ResultSet r = stmt.executeQuery("select Produk.IDP,IDS,NamaP,Stok from Produk join StokProduk on Produk.IDP=StokProduk.IDP order by Produk.IDP");
			while(r.next()){
				v.addElement(r.getString(1));
				v.addElement(r.getString(2));
				v.addElement(r.getString(3));
				v.addElement(Integer.toString(r.getInt(4)));
				dataModel.addRow(new Object[]{v.elementAt(0),v.elementAt(1),v.elementAt(2),v.elementAt(3)});
				v.removeAllElements();
			}
			r = stmt.executeQuery("select * from Supplier");
			while(r.next()){
				CboIDS.addItem(r.getString(1));
				CboS.addItem(r.getString(2));
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void rTabel(){
		dataModel.setRowCount(0);
		try {
			Statement stmt = con.getConnection().createStatement();
			ResultSet r = stmt.executeQuery("select Produk.IDP,IDS,NamaP,Stok from Produk join StokProduk on Produk.IDP=StokProduk.IDP order by Produk.IDP");
			while(r.next()){
				v.addElement(r.getString(1));
				v.addElement(r.getString(2));
				v.addElement(r.getString(3));
				v.addElement(Integer.toString(r.getInt(4)));
				dataModel.addRow(new Object[]{v.elementAt(0),v.elementAt(1),v.elementAt(2),v.elementAt(3)});
				v.removeAllElements();
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Component getBL(){
		return bl;
	}
}
