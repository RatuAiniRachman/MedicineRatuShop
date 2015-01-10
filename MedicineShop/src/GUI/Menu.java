package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class Menu extends JFrame {
	
	private JPanel ATAS = new JPanel();
	private JPanel KIRI = new JPanel();
	private JPanel KANAN = new JPanel();
	private JPanel BAWAH = new JPanel();
	private JButton BtnProduk = new JButton();
	private JButton BtnSup = new JButton();
	private JButton BtnStok = new JButton();
	private JButton BtnUser = new JButton();
	private JButton BtnLaporan = new JButton();
	private JButton BtnKeluar = new JButton();
	private JLabel Prd = new JLabel("<HTML><H3>MAINTENANCE PRODUCTS</H3></HTML>");
	private JLabel Sup = new JLabel("<HTML><H3>MAINTENANCE SUPPLIERS</H3></HTML>");
	private JLabel Stok = new JLabel("<HTML><H3>MAINTENANCE STOCKS</H3></HTML>");
	private JLabel User = new JLabel("<HTML><H3>MAINTENANCE USERS</H3></HTML>");
	private JLabel Laporan = new JLabel("<HTML><H3>MAINTENANCE LAPORAN</H3></HTML>");
	private JLabel Keluar = new JLabel("<HTML><H3>KELUAR</H3></HTML>");
	private Dimension Layar = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel Panel = new JPanel(new GridLayout(6,1,10,20));
	private JPanel Panel1 = new JPanel(new GridLayout(6,1,10,20));
	
	FormUser menu_user;
	
	static MainUtama lg = new MainUtama();
	
	public Menu(String user){
		super(user);
		menu_user = new FormUser(user);
		JLabel lblJudul = new JLabel("<HTML><H2>Maintenance (PRODUK)</H2></HTML>");
		JLabel lblFooter = new JLabel("<HTML><H4>Copyright by Qurratul Aini Rachman</H4></HTML>");
		lblJudul.setForeground(Color.GREEN);
		lblFooter.setForeground(Color.GREEN);
		Prd.setForeground(Color.GREEN);
		Sup.setForeground(Color.GREEN);
		Stok.setForeground(Color.GREEN);
		User.setForeground(Color.GREEN);
		Laporan.setForeground(Color.GREEN);
		Keluar.setForeground(Color.GREEN);
		
		ATAS.setBackground(Color.BLACK);
		BAWAH.setBackground(Color.BLACK);
		KIRI.setBackground(Color.DARK_GRAY);
		BtnProduk.setBackground(Color.BLACK);
		BtnProduk.add(Prd);
		BtnSup.setBackground(Color.BLACK);
		BtnSup.add(Sup);
		BtnStok.setBackground(Color.BLACK);
		BtnStok.add(Stok);
		BtnUser.setBackground(Color.BLACK);
		BtnUser.add(User);
		BtnLaporan.setBackground(Color.BLACK);
		BtnLaporan.add(Laporan);
		BtnKeluar.setBackground(Color.BLACK);
		BtnKeluar.add(Keluar);
		
		ATAS.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		BAWAH.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		KIRI.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		
		Panel.setBackground(Color.DARK_GRAY);
		Panel.add(BtnProduk);
		Panel.add(BtnSup);
		Panel.add(BtnStok);
		Panel1.add(BtnUser);
		Panel1.add(BtnLaporan);
		Panel1.add(BtnKeluar);
		KIRI.add(Panel);
		KANAN.add(Panel1);
		ATAS.add(lblJudul);
		BAWAH.add(lblFooter);
		
		
		Container k = getContentPane();
		k.setLayout(new BorderLayout());
		k.add(ATAS, BorderLayout.NORTH);
		k.add(KIRI, BorderLayout.WEST);
		k.add(KANAN, BorderLayout.EAST);
		k.add(BAWAH, BorderLayout.SOUTH);
		
		
		setSize(600,500);
		setLocation(((Layar.width - getWidth()) / 2),(Layar.height - getHeight()) / 2);
		//setResizable(false);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//-----------------------------------------------------------------------------------
		BtnKeluar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lg.setVisible(true);
				dispose();
				
			}
		});
		//-----------------------------------------------------------------------------------
		BtnProduk.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FormProduk menu_produk = new FormProduk();
				menu_produk.setVisible(true);
				dispose();
			}
		});
		//----------------------------------------------------------------------------------
		BtnSup.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FormSupplier menu_supplier = new FormSupplier();
				menu_supplier.setVisible(true);
				dispose();
			}
		});
		//----------------------------------------------------------------------------------
		BtnStok.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FormStok menu_stok = new FormStok();
				menu_stok.setVisible(true);
				dispose();
			}
		});
		//----------------------------------------------------------------------------------
		BtnUser.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FormUser menu_user = new FormUser("");
				menu_user.setVisible(true);
				dispose();
			}
		});
		//----------------------------------------------------------------------------------
		BtnLaporan.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FormLaporan lap = new FormLaporan();
				lap.setVisible(true);
				dispose();
			}
		});
		//----------------------------------------------------------------------------------
	}
	
}
