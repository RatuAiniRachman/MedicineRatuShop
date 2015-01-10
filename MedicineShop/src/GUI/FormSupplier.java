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

import database.*;

@SuppressWarnings("serial")
public class FormSupplier extends JFrame{
	private JPanel ATAS = new JPanel();
	private JPanel BAWAH = new JPanel();
	private JPanel TENGAH = new JPanel();
	private JButton BtnUtama = new JButton("Menu Utama");
	
	
	private JButton BtnTambah = new JButton();
	private JButton BtnUbah = new JButton();
	private JButton BtnHapus = new JButton();
	private JButton Tbh = new JButton();
	private JButton Clr = new JButton();
	private JLabel lblTbh = new JLabel();
	private JLabel Tambah = new JLabel("<HTML><H3>TAMBAH</H3></HTML>");
	private JLabel Ubah = new JLabel("<HTML><H3>UBAH</H3></HTML>");
	private JLabel Hapus = new JLabel("<HTML><H3>HAPUS</H3></HTML>");
	private JTextField txtNamaS = new JTextField(20);
	private JTextField txtIDS = new JTextField(6);
	private JComboBox<String> CboIDS = new JComboBox<String>();
	private JLabel IDS = new JLabel("ID Supplier");
	private JLabel NamaS = new JLabel("Nama Supplier");
	private Dimension Layar = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel bl = new JPanel(new BorderLayout());
	private JPanel gbl = new JPanel(new GridBagLayout());
	private JPanel PanelID = new JPanel();
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
    private Supplier s = new Supplier();
    
    public FormSupplier(){
		super("Maintenance Produk");
		
		pos=1;
		lblTbh.setText("Tambah");
		gbl.setBackground(Color.DARK_GRAY);
		gl.setBackground(Color.WHITE);
		
		BtnTambah.setBackground(Color.DARK_GRAY);;
		BtnUbah.setBackground(Color.BLACK);
		BtnHapus.setBackground(Color.BLACK);
		
		Tambah.setForeground(Color.GREEN);
		Ubah.setForeground(Color.GREEN);
		Hapus.setForeground(Color.GREEN);
		NamaS.setForeground(Color.GREEN);
		IDS.setForeground(Color.GREEN);
		
		BtnTambah.add(Tambah);
		BtnUbah.add(Ubah);
		BtnHapus.add(Hapus);
		Tbh.add(lblTbh);
		Clr.add(new JLabel("Clear"));
		
		CboIDS.setPreferredSize(new Dimension(100,24));
		PanelID.add(txtIDS);
		
		dataModel.addColumn("ID Supplier");
		dataModel.addColumn("Nama Supplier");
	    table.getColumnModel().getColumn(1).setPreferredWidth(400);
	    JScrollPane scrollpane = new JScrollPane(table);
	    scrollpane.setPreferredSize(new Dimension(480, 330));
	    rSupp();
	    
		gbc.insets = new Insets(5, 5, 5, 5);
		
		gl.add(BtnTambah);
		gl.add(BtnUbah);
		gl.add(BtnHapus);
		
		gbc.gridx = 0; gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbl.add(NamaS, gbc);
		gbc.gridx=1;
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbl.add(txtNamaS, gbc);
		
		gbc.gridx = 0; gbc.gridy=1;
		gbc.gridwidth = 1;
		gbl.add(IDS, gbc);
		gbc.gridx = 1;
		gbl.add(PanelID, gbc);
		
		
		gbc.gridx = 0; gbc.gridy=2;
		gbc.gridwidth = 1;
		gbl.add(Tbh, gbc);
		gbc.gridx = 1;
		gbl.add(Clr, gbc);
		
		gbc.gridx=0; gbc.gridy = 3;
		gbc.gridwidth = 4;
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
		BtnTambah.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BtnTambah.setBackground(Color.DARK_GRAY);
				BtnUbah.setBackground(Color.BLACK);
				BtnHapus.setBackground(Color.BLACK);
				lblTbh.setText("Tambah");
				PanelID.removeAll();
				PanelID.add(txtIDS);
				PanelID.validate();
				PanelID.repaint();
				pos = 1;
			}
		});
		
		BtnUbah.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BtnTambah.setBackground(Color.BLACK);
				BtnUbah.setBackground(Color.DARK_GRAY);
				BtnHapus.setBackground(Color.BLACK);
				lblTbh.setText("Ubah");
				txtNamaS.setEnabled(true);
				PanelID.removeAll();
				PanelID.add(CboIDS);
				PanelID.validate();
				PanelID.repaint();
				pos = 2;
			}
		});
		
		BtnHapus.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BtnTambah.setBackground(Color.BLACK);
				BtnUbah.setBackground(Color.BLACK);
				BtnHapus.setBackground(Color.DARK_GRAY);
				lblTbh.setText("Hapus");
				txtNamaS.setEnabled(false);
				PanelID.removeAll();
				PanelID.add(CboIDS);
				PanelID.validate();
				PanelID.repaint();
				pos = 3;
			}
		});
		//--------------------------------------------------------------------------
		Clr.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtNamaS.setText("");
				CboIDS.setSelectedItem(null);
			}
		});
		
		Tbh.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(pos==1){
					if(txtIDS.getDocument().getLength()>3 || txtIDS.getDocument().getLength()<3){
						JOptionPane.showMessageDialog(null,"ID Supplier harus 3 karakter","Error",JOptionPane.ERROR_MESSAGE);
					}else{
						try {
							int a=0;
							Statement stmt = con.getConnection().createStatement();
							ResultSet r = stmt.executeQuery("select count(IDS) from Supplier where NamaS='"+txtNamaS.getText()+"' or IDS='"+txtIDS.getText()+"'");
							while(r.next()){
								a=r.getInt(1);
							}
							if(a==0){
								s.addSup(txtIDS.getText(), txtNamaS.getText());
								rSupp();
							}else{
								JOptionPane.showMessageDialog(null,"ID/Nama Supplier sudah ada","Error",JOptionPane.ERROR_MESSAGE);
							}
							stmt.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}else if(pos==2){
					if(txtNamaS.getText().isEmpty()){
						JOptionPane.showMessageDialog(null,"isi field","Error",JOptionPane.ERROR_MESSAGE);
					}else{
						s.gantiSup(CboIDS.getSelectedItem().toString(), txtNamaS.getText());
						rSupp();
					}
				}else{
					s.HapusSup(CboIDS.getSelectedItem().toString());
					rSupp();
				}
			}
		});
		
	}
    
    private void rSupp() {
    	dataModel.setRowCount(0);
    	CboIDS.removeAll();
		try {
			Statement stmt = con.getConnection().createStatement();
			ResultSet r = stmt.executeQuery("select * from Supplier");
			while(r.next()){
				v.addElement(r.getString(1));
				v.addElement(r.getString(2));
				dataModel.addRow(new Object[]{v.elementAt(0),v.elementAt(1)});
				CboIDS.addItem(r.getString(1));
				v.removeAllElements();
			}
			CboIDS.setSelectedIndex(0);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Component getBL(){
    	return bl;
    }
}
