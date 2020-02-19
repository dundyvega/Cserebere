package windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import objects.Beo;
import objects.ChangedListener;
import objects.FileOperator;
import objects.Igeny;
import objects.IgenyTipus;
import objects.ListaPelda;
import objects.ModifiedUser;
import objects.NapiIgenyek;
import objects.NaponDolgozik;
import objects.PossibleAssigment;
import objects.User;
import objects.dolgozik;

public class AdminWindow2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int honap;
	private JPanel contentPane;
	private JMenuItem mntmMappaLtrehozsa;
	private JPanel nap1;
	private JLabel lNap1;
	private JPanel nap2;
	private JPanel nap3;
	private JLabel lNap3;
	private JButton btnNapVissza;
	private JButton btnNapNext;
	private JButton btnSzabadN;
	private JButton btnHVK;
	private JPanel szeretne1;
	private JLabel lbSzeretne1;
	private JPanel szeretne2;
	private JLabel lbSzeretne2;
	private JPanel szeretne3;
	private JLabel lbSzeretne3;
	private JButton btnSzeretneVisszsa;
	private JButton btnSzeretneNext;
	private JPanel vele1;
	private JLabel lbVele2;
	private JLabel lbVele3;
	private JButton btnVeleVissza;
	private JButton btnVeleNext;
	private JButton btnJavaslat;
	private JButton btnElfogads;
	private JLabel lNap2;
	private JLabel lbVele1;
	
	//private int l1.getListaIndex();
	//private int szeretneIndex;
	private ArrayList<String> napok;
	private int napokH;
	private int kezdo;
	private String expertM;
	private String expertCs;
	private String t1t2M;
	private String t1t2Cs;
	private String fix;
	private String spec;
	private String HVHT;
	private String HTHV;
	private ArrayList<Igeny> leadottIgenyek;
	private ArrayList<NapiIgenyek> haviIgenyek;
	private ArrayList<NapiIgenyek> haviModositasok;
	private int modositasIndex;
	private ArrayList<User> beosztasok;
	private ArrayList<NaponDolgozik> szabadNapjaVan;
	private ArrayList<NaponDolgozik> delelottDolgozikVan;
	private ArrayList<NaponDolgozik> delutanDOlgozikVan;
	private String multHonap;
	private ArrayList<NaponDolgozik> korabbiHonap;
	private ArrayList<User> velukCserelhet;
	private int velukCserelhetIndex;
	private JPanel vele2;
	private JPanel vele3;
	private ArrayList<NapiIgenyek> haviMaradjon;
	
	private ListaPelda<Igeny> l2 = null;
	private ListaPelda<String> l1;
	private ListaPelda<ModifiedUser> l3;
	private ArrayList<User> korabbiHonapEmberei;
	private ArrayList<ModifiedUser> userers;
	private ArrayList<ModifiedUser> theRealUserers;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminWindow2 frame = new AdminWindow2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the 
	 */
	public AdminWindow2() {
		
		 try {
	 			BufferedImage image = ImageIO.read(getClass().getResource("/img/image.png"));
	 			this.setIconImage(image);
	 		} catch (IOException e1) {
	 			// TODO Auto-generated catch block
	 			e1.printStackTrace();
	 		}
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 777, 488);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFeladatok = new JMenu("Feladatok");
		menuBar.add(mnFeladatok);
		
		JMenu mnFjlSzerkeszts = new JMenu("Fájl Szerkesztés");
		mnFeladatok.add(mnFjlSzerkeszts);
		
		JMenu csiribiriExp = new JMenu("Csiribiri export szerk.");
		
		JMenuItem mtmExcelT1T2 = new JMenuItem("T1T2.xlsx fájl létrehozása");
		JMenuItem mtmExcelExpert = new JMenuItem("Expert.xlsx létrehozása");
		JMenuItem mntmExcelFelbonts = new JMenuItem("Excel Kettébontása");
		
		
		/*listenerek*/
		
		//felbontas
		mntmExcelFelbonts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
		        FileNameExtensionFilter filter = new FileNameExtensionFilter(
		                "xlsx", "XLSX");
		        chooser.setFileFilter(filter);
		        int returnVal = chooser.showOpenDialog(null);
		        if(returnVal == JFileChooser.APPROVE_OPTION) {
		        	
		        	String fileName = chooser.getSelectedFile().getName();
		          
		        	if (!fileName.equals("") && fileName != null) {
		        		
		        		FileOperator.fileRess(chooser.getSelectedFile().getPath());
		        	}
		        }
				
			}
		});
		
		
		JMenu excelFileSzerk = new JMenu("Csiribiri export");
		excelFileSzerk.add(mntmExcelFelbonts);
		excelFileSzerk.add(mtmExcelT1T2);
		excelFileSzerk.add(mtmExcelExpert);
		
		mnFjlSzerkeszts.add(excelFileSzerk);
		
		JMenuItem mntmConfigBellts = new JMenuItem("Config Beállítás");
		mnFjlSzerkeszts.add(mntmConfigBellts);
		
		JMenuItem mntmresFjlokGenerlsa = new JMenuItem("mindkettőnek");
		mntmresFjlokGenerlsa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FileOperator.generateEmptyTxt(2);
				
			}
		});
		
		JMenuItem mtmuresT1T2 = new JMenuItem("T1T2-nek");
		mtmuresT1T2.addActionListener(e->FileOperator.generateEmptyTxt(1));
		
		
		JMenuItem mtmuresExpert = new JMenuItem("Expert-nek");
		mtmuresExpert.addActionListener(e->FileOperator.generateEmptyTxt(0));
		
		JMenu urs = new JMenu("Üres fájlok generálása");
		
		urs.add(mtmuresT1T2);
		urs.add(mtmuresExpert);
		urs.add(mntmresFjlokGenerlsa);
		
		mnFjlSzerkeszts.add(urs);
		
		JMenuItem mntmFjlokBetltse = new JMenuItem("Mindkét csoportnak");
		JMenuItem mntmExpertFl = new JMenuItem("Beo igény experteknek");
		JMenuItem mntmT1T2Fl = new JMenuItem("Beo igény t1t2");
		
		mntmT1T2Fl.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				try {
					FileOperator.igenyExcelElkeszites(0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		
		
		mntmExpertFl.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				try {
					FileOperator.igenyExcelElkeszites(1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		
		
		mntmFjlokBetltse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				try {
					FileOperator.igenyExcelElkeszites(2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		
		JMenu igeny = new JMenu("Igények excelbe");
		igeny.add(mntmT1T2Fl);
		igeny.add(mntmExpertFl);
		igeny.add(mntmFjlokBetltse);
		
		mnFjlSzerkeszts.add(igeny);
		
		mntmMappaLtrehozsa = new JMenuItem("Mappa Létrehozása");
		mntmMappaLtrehozsa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				FileOperator.createFolder();
			}
		});
		mnFjlSzerkeszts.add(mntmMappaLtrehozsa);
		
		JMenu mnIgnyek = new JMenu("Igények");
		menuBar.add(mnIgnyek);
		
		JMenuItem mntmExpertIgnyek = new JMenuItem("Expert Igények");
		mnIgnyek.add(mntmExpertIgnyek);
		
		mntmExpertIgnyek.addActionListener(e->igenyRendereles2(1));
		
		JMenuItem mntmTtIgnyek = new JMenuItem("T1/T2 Igények");
		mntmTtIgnyek.addActionListener(e->igenyRendereles2(0));
		
		mnIgnyek.add(mntmTtIgnyek);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(0, 6, 10, 30));
		setContentPane(contentPane);
		
		contentPane.setVisible(false);
		
		
		contentPane.setLayout(new GridLayout(4,1));
		
		
		
		/*
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNapok = new JLabel("   Napok");
		panel.add(lblNapok, BorderLayout.CENTER);
		
		nap1 = new JPanel();
		nap1.setBackground(Color.GREEN);
		getContentPane().add(nap1);
		nap1.setLayout(new BorderLayout(0, 0));
		nap1.setBorder(BorderFactory.createLineBorder(Color.black));
		
		lNap1 = new JLabel("11.01");
		nap1.add(lNap1, BorderLayout.CENTER);
		
		nap2 = new JPanel();
		nap2.setBackground(Color.ORANGE);
		getContentPane().add(nap2);
		nap2.setLayout(new BorderLayout(0, 0));
		
		lNap2 = new JLabel("11.02");
		lNap2.setBackground(Color.CYAN);
		nap2.add(lNap2, BorderLayout.CENTER);
		
		nap3 = new JPanel();
		nap3.setBackground(Color.ORANGE);
		getContentPane().add(nap3);
		nap3.setLayout(new BorderLayout(0, 0));
		
		lNap3 = new JLabel("11.03");
		nap3.add(lNap3, BorderLayout.CENTER);
		
		JPanel panel_4 = new JPanel();
		getContentPane().add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		btnNapVissza = new JButton("<<");
		panel_4.add(btnNapVissza, BorderLayout.EAST);
		btnNapVissza.addActionListener(e->napEltolas(--l1.getListaIndex()));
		btnNapVissza.setEnabled(false);
		
		
		JPanel panel_5 = new JPanel();
		getContentPane().add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		btnNapNext = new JButton(">>");
		panel_5.add(btnNapNext, BorderLayout.WEST);
		btnNapNext.addActionListener(e->napEltolas(++l1.getListaIndex()));
		
		JPanel panel_6 = new JPanel();
		getContentPane().add(panel_6);
		
		JPanel panel_7 = new JPanel();
		getContentPane().add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		btnSzabadN = new JButton("Szabadnapot szeretne");
		btnSzabadN.setBackground(Color.GREEN);
		panel_7.add(btnSzabadN, BorderLayout.CENTER);
		
		JPanel panel_8 = new JPanel();
		getContentPane().add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		btnHVK = new JButton("Du/De cserét szeretne");
		btnHVK.setBackground(Color.ORANGE);
		panel_8.add(btnHVK, BorderLayout.CENTER);
		
		JPanel panel_9 = new JPanel();
		getContentPane().add(panel_9);
		
		JPanel panel_10 = new JPanel();
		getContentPane().add(panel_10);
		
		JPanel panel_11 = new JPanel();
		getContentPane().add(panel_11);
		
		JPanel panel_12 = new JPanel();
		getContentPane().add(panel_12);
		panel_12.setLayout(new BorderLayout(0, 0));
		
		JLabel lblSzeretn = new JLabel("Ő szeretné:");
		panel_12.add(lblSzeretn, BorderLayout.CENTER);
		
		szeretne1 = new JPanel();
		szeretne1.setBackground(Color.GREEN);
		getContentPane().add(szeretne1);
		szeretne1.setLayout(new BorderLayout(0, 0));
		
		lbSzeretne1 = new JLabel("Név1");
		szeretne1.add(lbSzeretne1, BorderLayout.CENTER);
		
		szeretne2 = new JPanel();
		szeretne2.setBackground(Color.ORANGE);
		getContentPane().add(szeretne2);
		szeretne2.setLayout(new BorderLayout(0, 0));
		
		lbSzeretne2 = new JLabel("Név2");
		lbSzeretne2.setBackground(Color.ORANGE);
		szeretne2.add(lbSzeretne2, BorderLayout.CENTER);
		
		szeretne3 = new JPanel();
		szeretne3.setBackground(Color.ORANGE);
		getContentPane().add(szeretne3);
		szeretne3.setLayout(new BorderLayout(0, 0));
		
		lbSzeretne3 = new JLabel("Név3");
		szeretne3.add(lbSzeretne3, BorderLayout.CENTER);
		
		JPanel panel_16 = new JPanel();
		getContentPane().add(panel_16);
		panel_16.setLayout(new BorderLayout(0, 0));
		
		btnSzeretneVisszsa = new JButton("<<");
		panel_16.add(btnSzeretneVisszsa, BorderLayout.EAST);
		btnSzeretneVisszsa.addActionListener(e->valamitSzeretne(--szeretneIndex));
		
		JPanel panel_17 = new JPanel();
		getContentPane().add(panel_17);
		panel_17.setLayout(new BorderLayout(0, 0));
		
		btnSzeretneNext = new JButton(">>");
		panel_17.add(btnSzeretneNext, BorderLayout.WEST);
		
		btnSzeretneNext.addActionListener(e->valamitSzeretne(++szeretneIndex));
		
		JPanel panel_18 = new JPanel();
		getContentPane().add(panel_18);
		panel_18.setLayout(new BorderLayout(0, 0));
		
		JLabel lbVele = new JLabel("Vele");
		panel_18.add(lbVele, BorderLayout.CENTER);
		
		vele1 = new JPanel();
		vele1.setBackground(Color.GREEN);
		getContentPane().add(vele1);
		vele1.setLayout(new BorderLayout(0, 0));
		
		lbVele1 = new JLabel("Név1");
		vele1.add(lbVele1, BorderLayout.CENTER);
		
		vele2 = new JPanel();
		vele2.setBackground(Color.ORANGE);
		getContentPane().add(vele2);
		vele2.setLayout(new BorderLayout(0, 0));
		
		lbVele2 = new JLabel("Név2");
		vele2.add(lbVele2, BorderLayout.CENTER);
		
		vele3 = new JPanel();
		vele3.setBackground(Color.ORANGE);
		getContentPane().add(vele3);
		vele3.setLayout(new BorderLayout(0, 0));
		
		lbVele3 = new JLabel("Név3");
		vele3.add(lbVele3, BorderLayout.CENTER);
		
		JPanel panel_22 = new JPanel();
		getContentPane().add(panel_22);
		panel_22.setLayout(new BorderLayout(0, 0));
		
		btnVeleVissza = new JButton("<<");
		panel_22.add(btnVeleVissza, BorderLayout.EAST);
		
		JPanel panel_23 = new JPanel();
		getContentPane().add(panel_23);
		panel_23.setLayout(new BorderLayout(0, 0));
		
		btnVeleNext = new JButton(">>");
		panel_23.add(btnVeleNext, BorderLayout.WEST);
		
		JPanel panel_24 = new JPanel();
		getContentPane().add(panel_24);
		panel_24.setLayout(new BorderLayout(0, 0));
		
		btnJavaslat = new JButton("Javaslat");
		panel_24.add(btnJavaslat, BorderLayout.CENTER);
		
		JPanel panel_25 = new JPanel();
		getContentPane().add(panel_25);
		panel_25.setLayout(new BorderLayout(0, 0));
		
		btnElfogads = new JButton("Elfogadás");
		panel_25.add(btnElfogads, BorderLayout.CENTER);
		
		*/
		
	}
	
	private void valamitSzeretne(int i) {
		
		if (l1.getListaIndex() < this.modositasIndex) {
			
			//System.out.println("érték: " + i);
			
			szeretne(i);
		} else {
			
			modositaniSz(i);
		}
		
	}

	private void modositaniSz(int i) {
		// TODO Auto-generated method stub
		
	// TODO Auto-generated method stub
		
		
		if (i == 0) {
			
			btnSzeretneVisszsa.setEnabled(false);
			lbSzeretne1.setText(haviModositasok.get(l1.getListaIndex() - modositasIndex).getNapiIgenyek(0).getName() + "");
			lbSzeretne2.setText(haviModositasok.get(l1.getListaIndex() - modositasIndex).getNapiIgenyek(1).getName() + "");
			lbSzeretne3.setText(haviModositasok.get(l1.getListaIndex() - modositasIndex).getNapiIgenyek(2).getName() + "");
			
			szeretne1.setBackground(Color.GREEN);
			szeretne2.setBackground(Color.ORANGE);
			szeretne3.setBackground(Color.ORANGE);
			
			szeretne1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			szeretne2.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
			szeretne3.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
			
		} else 
			
			if (i == haviModositasok.get(l1.getListaIndex() - modositasIndex).getLengthOfNap() - 1) {
			
			btnSzeretneNext.setEnabled(false);
			int length = haviModositasok.get(l1.getListaIndex() - modositasIndex).getLengthOfNap() - 1;
			
			lbSzeretne1.setText(haviModositasok.get(l1.getListaIndex() - modositasIndex).getNapiIgenyek(length - 2).getName()  + "");
			lbSzeretne2.setText(haviModositasok.get(l1.getListaIndex() - modositasIndex).getNapiIgenyek(length - 1).getName() + "");
			lbSzeretne3.setText(haviModositasok.get(l1.getListaIndex() - modositasIndex).getNapiIgenyek(length).getName() + "");
			
			
			szeretne1.setBackground(Color.ORANGE);
			szeretne2.setBackground(Color.ORANGE);
			szeretne3.setBackground(Color.GREEN);
			
			szeretne3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			szeretne1.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
			szeretne2.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
			
		}
		
		else {
			
			btnSzeretneNext.setEnabled(true);
			btnSzeretneVisszsa.setEnabled(true);
			lbSzeretne1.setText(haviModositasok.get(l1.getListaIndex() - modositasIndex).getNapiIgenyek(i - 1).getName() + "");
			lbSzeretne2.setText(haviModositasok.get(l1.getListaIndex() - modositasIndex).getNapiIgenyek(i).getName() + "");
			lbSzeretne3.setText(haviModositasok.get(l1.getListaIndex() - modositasIndex).getNapiIgenyek(i + 1).getName() + "");
			
			szeretne1.setBackground(Color.ORANGE);
			szeretne2.setBackground(Color.GREEN);
			szeretne3.setBackground(Color.ORANGE);
			
			szeretne2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			szeretne1.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
			szeretne3.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		}
		
		
		
		
		
	}

	private Object szeretne(int i) {
		// TODO Auto-generated method stub
		
		
		if (i == 0) {
			
			btnSzeretneVisszsa.setEnabled(false);
			lbSzeretne1.setText(haviIgenyek.get(l1.getListaIndex()).getNapiIgenyek(0).getName() + "");
			lbSzeretne2.setText(haviIgenyek.get(l1.getListaIndex()).getNapiIgenyek(2).getName() + "");
			lbSzeretne3.setText(haviIgenyek.get(l1.getListaIndex()).getNapiIgenyek(3).getName() + "");
			
			szeretne1.setBackground(Color.GREEN);
			szeretne2.setBackground(Color.ORANGE);
			szeretne3.setBackground(Color.ORANGE);
			
			szeretne1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			szeretne2.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
			szeretne3.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
			
		} else if (i == haviIgenyek.get(l1.getListaIndex()).getLengthOfNap() - 1) {
			
			btnSzeretneNext.setEnabled(false);
			int length = haviIgenyek.get(l1.getListaIndex()).getLengthOfNap() - 1;
			
			lbSzeretne1.setText(haviIgenyek.get(l1.getListaIndex()).getNapiIgenyek(length - 2).getName()  + "");
			lbSzeretne2.setText(haviIgenyek.get(l1.getListaIndex()).getNapiIgenyek(length - 1).getName() + "");
			lbSzeretne3.setText(haviIgenyek.get(l1.getListaIndex()).getNapiIgenyek(length).getName() + "");
			
			
			szeretne1.setBackground(Color.ORANGE);
			szeretne2.setBackground(Color.ORANGE);
			szeretne3.setBackground(Color.GREEN);
			
			szeretne3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			szeretne1.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
			szeretne2.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
			
		}
		
		else {
			
			btnSzeretneNext.setEnabled(true);
			btnSzeretneVisszsa.setEnabled(true);
			lbSzeretne1.setText(haviIgenyek.get(l1.getListaIndex()).getNapiIgenyek(i - 1).getName() + "");
			lbSzeretne2.setText(haviIgenyek.get(l1.getListaIndex()).getNapiIgenyek(i).getName() + "");
			lbSzeretne3.setText(haviIgenyek.get(l1.getListaIndex()).getNapiIgenyek(i + 1).getName() + "");
			
			szeretne1.setBackground(Color.ORANGE);
			szeretne2.setBackground(Color.GREEN);
			szeretne3.setBackground(Color.ORANGE);
			
			szeretne2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			szeretne1.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
			szeretne3.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		}
		
		return null;
	}
	
	
	private Object igenyRendereles2(int mode) {
		
		try {
		
		beosztasok = FileOperator.getInfoFromFile(mode);
		
		leadottIgenyek = FileOperator.getIgenyek(mode);
		configBetoltes();
		
		szabadNapjaVan = new ArrayList<NaponDolgozik>();
		delelottDolgozikVan = new ArrayList<NaponDolgozik>();
		delutanDOlgozikVan = new ArrayList<NaponDolgozik>();
		
		// megvan User szer beosztás, és név alapján megvannak az igények, az igényekhez beállítjuk a Usert?
		
		
		for (int i = 0; i < leadottIgenyek.size(); ++i) {
			
			boolean talalt = false;
			
			for (int j = 1; j < beosztasok.size() && !talalt; ++j) {
				
				if (leadottIgenyek.get(i).getName().equals(beosztasok.get(j).getName())) {
					
					talalt = true;
					
					leadottIgenyek.get(i).setUser(beosztasok.get(j));
				}
				
			}
		}
		
		
		haviIgenyek = new ArrayList<NapiIgenyek>();
		haviModositasok = new ArrayList<NapiIgenyek>();
		haviMaradjon = new ArrayList<NapiIgenyek>();
		
		
		if (leadottIgenyek.size() > 0) {
		
		for (int i = 1; i <= napokH; ++i) {
			
			NapiIgenyek napiIgenyek = new NapiIgenyek();
			NapiIgenyek napiModositasok = new NapiIgenyek();
			NapiIgenyek napiMaradjon = new NapiIgenyek();
			
			
			for (int j = 0; j < leadottIgenyek.size(); ++j) {
				
				if (i == leadottIgenyek.get(j).getNap() && leadottIgenyek.get(j).getTipus() != IgenyTipus.Maradjon) {
					
					if (leadottIgenyek.get(j).getTipus() == IgenyTipus.SzabadHetkoznap || 
							leadottIgenyek.get(j).getTipus() == IgenyTipus.SzabadHetvege) {
					napiIgenyek.addNapiIgenyek(leadottIgenyek.get(j));
					} else {
						napiModositasok.addNapiIgenyek(leadottIgenyek.get(j));
					}
					
				} else if (leadottIgenyek.get(j).getTipus() == IgenyTipus.Maradjon && i == leadottIgenyek.get(j).getNap()){
					napiMaradjon.addNapiIgenyek(leadottIgenyek.get(j));
				}
			}
			
			//csak akkor vizsgáljuk a napokra leadott igényeket, ha voltak rá igények
			if (napiIgenyek.getLengthOfNap() > 0) {
				haviIgenyek.add(napiIgenyek);
			}
			
			if (napiModositasok.getLengthOfNap() > 0) {
				haviModositasok.add(napiModositasok);
			}
			
			if (napiMaradjon.getLengthOfNap() > 0) {
				haviMaradjon.add(napiMaradjon);
			}
		}
		}
		
		
		
		
		szabadNapjaVan = new ArrayList<NaponDolgozik>();
		delelottDolgozikVan = new ArrayList<NaponDolgozik>();
		delutanDOlgozikVan = new ArrayList<NaponDolgozik>();
		
		//System.out.println(beosztasok.get(1).get(30).getLeiras());
		
		for (int i = 0; i < napokH; ++i) {
			
			NaponDolgozik npSz = new NaponDolgozik(i + 1);
			NaponDolgozik npDe = new NaponDolgozik(i + 1);
			NaponDolgozik npDu = new NaponDolgozik(i + 1);
			
			for (int j = 1; j < beosztasok.size(); ++j) {
				
				if (beosztasok.get(j).get(i).getUserke() == dolgozik.szabadH || 
						beosztasok.get(j).get(i).getUserke() == dolgozik.szabadV) {
					
					npSz.addUser(beosztasok.get(j));
					npSz.setErtek(1);
					
				} else if (beosztasok.get(j).get(i).getUserke() == dolgozik.dolgHde || 
						beosztasok.get(j).get(i).getUserke() == dolgozik.dolgVde) {
					
					npDe.addUser(beosztasok.get(j));
					npDe.setErtek(1);
					
				} else if (beosztasok.get(j).get(i).getUserke() == dolgozik.dolgHdu ||
						beosztasok.get(j).get(i).getUserke() == dolgozik.dolgVdu) {
					npDu.addUser(beosztasok.get(j));
					npDu.setErtek(1);
				}
				
			}
			
			if (npSz.getLength() > 0) {
				szabadNapjaVan.add(npSz);
			}
			
			if (npDu.getLength() > 0) {
				delutanDOlgozikVan.add(npDu);
			}
			
			if (npDe.getLength() > 0) {
				
				delelottDolgozikVan.add(npDe);
			}
			
		}
		
		korabbiHonapEmberei = FileOperator.getPrewMonthsUser();
		
		toroldARosszakat(delelottDolgozikVan, 0);
		toroldARosszakat(delutanDOlgozikVan, 1);
		toroldARosszakat(szabadNapjaVan, 2);
		
		
		
		
		//l1.getListaIndex() = napokH;
		
		napok = new ArrayList<String>();
		
		
		
		
		for (int i = 0; i < haviIgenyek.size(); ++i) {
			
			napok.add(honap + "."+ haviIgenyek.get(i).getNapiIgenyek(0).getNap() + "");
		}
		
		modositasIndex = haviIgenyek.size();
		
		for (int i = 0; i < haviModositasok.size(); ++i) {
			napok.add(honap + "."+ haviModositasok.get(i).getNapiIgenyek(0).getNap() + "");
		}
		
		//l1.getListaIndex() = 0;
		
		l1 = new ListaPelda<String>(napok, "Napok: ");
		l2 = new ListaPelda<Igeny>(haviIgenyek.get(0).getArrayList(), "Kezelők");
		
		contentPane.add(l1);
		
		
		JPanel ketGomb = new JPanel();
		ketGomb.setLayout(new GridLayout(1, 0, 20, 20));
		
		JPanel kv1 = new JPanel();
		JPanel kv2 = new JPanel();
		JPanel kv3 = new JPanel();
		btnSzabadN = new JButton("Szabadnap csere");
		btnHVK = new JButton("DU/De csere");
		ketGomb.add(kv1);
		ketGomb.add(this.btnSzabadN);
		ketGomb.add(this.btnHVK);
		ketGomb.add(kv2);
		ketGomb.add(kv2);
		
		contentPane.add(ketGomb);
		
		contentPane.add(l2);
		
		
		
		
		
		
		l1.addChangedListener(new ChangedListener() {
			
			

			@Override
			public void somethingHappend() {
				// TODO Auto-generated method stub
				System.out.println("dfgfd");
				
				if (l1.getListaIndex() < modositasIndex) {
					
					if (haviIgenyek.get(l1.getListaIndex()).getLengthOfNap() > 0) {

							l2.setLista(haviIgenyek.get(l1.getListaIndex()).getArrayList());
							
							btnSzabadN.setBackground(Color.GREEN);
							btnHVK.setBackground(Color.ORANGE);
						
					}
				} else 
					if (haviModositasok.get(l1.getListaIndex() - haviIgenyek.size()).getLengthOfNap() > 0) {
						//haviModositasok.get(l1.getListaIndex() - haviIgenyek.size()
						
							 l2.setLista((haviModositasok.get(l1.getListaIndex() - haviIgenyek.size()).getArrayList()));;
							 
							 btnSzabadN.setBackground(Color.ORANGE);
								btnHVK.setBackground(Color.GREEN);
					}
				
			}
			
		});
		
		/*
		 * 
		 * 
		 * 
		 * 
		 * if (l1.getListaIndex() < modositasIndex) {
		if (haviIgenyek.get(l1.getListaIndex()).getLengthOfNap() > 0) {
			
			
			//a szabadnapokat vesszük nap váltás után
			btnSzabadN.setBackground(Color.green);
			this.btnHVK.setBackground(Color.orange);
			
			lbSzeretne1.setText(haviIgenyek.get(l1.getListaIndex()).getNapiIgenyek(0).getName());
			
			btnSzeretneNext.setEnabled(false);
			btnSzeretneVisszsa.setEnabled(false);
			lbSzeretne2.setText("");
			lbSzeretne3.setText("");
			
			szeretne1.setBackground(Color.green);
			szeretne2.setBackground(Color.orange);
			szeretne3.setBackground(Color.orange);
			szeretneIndex = 0;
			
			szeretne1.setBorder(BorderFactory.createLineBorder(Color.black));
			szeretne2.setBorder(BorderFactory.createLineBorder(Color.orange));
			szeretne3.setBorder(BorderFactory.createLineBorder(Color.orange));
			
			if (haviIgenyek.get(l1.getListaIndex()).getLengthOfNap() > 1) {
				btnSzeretneNext.setEnabled(true);
				
				lbSzeretne2.setText(haviIgenyek.get(l1.getListaIndex()).getNapiIgenyek(1).getName());
			}	
			
			if (haviIgenyek.get(l1.getListaIndex()).getLengthOfNap() > 2) {
				
					lbSzeretne3.setText(haviIgenyek.get(l1.getListaIndex()).getNapiIgenyek(2).getName());
				}
			
			
		}
		} else {
			
			
			
			if (haviModositasok.get(l1.getListaIndex() - haviIgenyek.size()).getLengthOfNap() > 0) {
				
				
				//a szabadnapokat vesszük nap váltás után
				btnSzabadN.setBackground(Color.orange);
				this.btnHVK.setBackground(Color.green);
				
				lbSzeretne1.setText(haviModositasok.get(l1.getListaIndex() - haviIgenyek.size()).getNapiIgenyek(0).getName());
				
				btnSzeretneNext.setEnabled(false);
				btnSzeretneVisszsa.setEnabled(false);
				lbSzeretne2.setText("");
				lbSzeretne3.setText("");
				
				szeretne1.setBackground(Color.green);
				szeretne2.setBackground(Color.orange);
				szeretne3.setBackground(Color.orange);
				szeretneIndex = 0;
				
				szeretne1.setBorder(BorderFactory.createLineBorder(Color.black));
				szeretne2.setBorder(BorderFactory.createLineBorder(Color.orange));
				szeretne3.setBorder(BorderFactory.createLineBorder(Color.orange));
				
				if (haviModositasok.get(l1.getListaIndex() - haviIgenyek.size()).getLengthOfNap() > 1) {
					btnSzeretneNext.setEnabled(true);
					
					lbSzeretne2.setText(haviModositasok.get(l1.getListaIndex() - modositasIndex).getNapiIgenyek(1).getName());
				}	
				
				if (haviModositasok.get(l1.getListaIndex() - haviIgenyek.size()).getLengthOfNap() > 2) {
					
						lbSzeretne3.setText(haviModositasok.get(l1.getListaIndex() - modositasIndex).getNapiIgenyek(2).getName());
					}
				
				
			}
			
			
			
			
			
		}
		 * 
		 * 
		 * 
		 * lNap1.setText(napok.get(0) + "");
		lNap2.setText(napok.get(1) + "");
		lNap3.setText(napok.get(2) + "");
		
		lbSzeretne1.setText(haviIgenyek.get(0).getNapiIgenyek(0).getName());
		
		if (haviIgenyek.get(0).getLengthOfNap() > 1) {
			lbSzeretne2.setText(haviIgenyek.get(0).getNapiIgenyek(1).getName());
			//System.out.println("gsfgs");
			
			if (haviIgenyek.get(0).getLengthOfNap() > 2)
			lbSzeretne3.setText(haviIgenyek.get(0).getNapiIgenyek(2).getName());
		}
		
		 */
		
		
		//cserélhetőség feltöltése
		
		
		listaElkeszitese();
		
		l3 = new ListaPelda<ModifiedUser>(theRealUserers, "Velük cserélhet");
		
		contentPane.add(l3);
		
		l2.addChangedListener(new ChangedListener() {

			@Override
			public void somethingHappend() {
				// TODO Auto-generated method stub
				listaElkeszitese();
				l3.setLista(theRealUserers);
				
			}
			
		});
		
		
		
		this.contentPane.setVisible(true);
		
		napEltolas(l1.getListaIndex());

		
		
		} catch (Exception ex) {}
		
		
		return null;
	}
	
/*
	private Object igenyRendereles(int mode) {
		// TODO Auto-generated method stub
		
		
		
		try {
			beosztasok = FileOperator.getInfoFromFile(mode);
			
			configBetoltes();
			
			szabadNapjaVan = new ArrayList<NaponDolgozik>();
			delelottDolgozikVan = new ArrayList<NaponDolgozik>();
			delutanDOlgozikVan = new ArrayList<NaponDolgozik>();
			
			//System.out.println(beosztasok.get(1).get(30).getLeiras());
			
			for (int i = 0; i < napokH; ++i) {
				
				NaponDolgozik npSz = new NaponDolgozik(i + 1);
				NaponDolgozik npDe = new NaponDolgozik(i + 1);
				NaponDolgozik npDu = new NaponDolgozik(i + 1);
				
				for (int j = 1; j < beosztasok.size(); ++j) {
					
					if (beosztasok.get(j).get(i).getUserke() == dolgozik.szabadH || 
							beosztasok.get(j).get(i).getUserke() == dolgozik.szabadV) {
						
						npSz.addUser(beosztasok.get(j));
						npSz.setErtek(1);
						
					} else if (beosztasok.get(j).get(i).getUserke() == dolgozik.dolgHde || 
							beosztasok.get(j).get(i).getUserke() == dolgozik.dolgVde) {
						
						npDe.addUser(beosztasok.get(j));
						npDe.setErtek(1);
						
					} else if (beosztasok.get(j).get(i).getUserke() == dolgozik.dolgHdu ||
							beosztasok.get(j).get(i).getUserke() == dolgozik.dolgVdu) {
						npDu.addUser(beosztasok.get(j));
						npDu.setErtek(1);
					}
					
				}
				
				if (npSz.getLength() > 0) {
					szabadNapjaVan.add(npSz);
				}
				
				if (npDu.getLength() > 0) {
					delutanDOlgozikVan.add(npDu);
				}
				
				if (npDe.getLength() > 0) {
					
					delelottDolgozikVan.add(npDe);
				}
				
			}
			
			korabbiHonap = FileOperator.teljesPrevMonth();
			
			toroldARosszakat(delelottDolgozikVan, 0);
			toroldARosszakat(delutanDOlgozikVan, 1);
			toroldARosszakat(szabadNapjaVan, 2);
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e1);
		}
		
		contentPane.setVisible(true);
		
		configBetoltes();
		
		// napok rész
		
		l1.getListaIndex() = napokH;
		
		napok = new ArrayList<String>();
		
		
		
		try {
			leadottIgenyek = FileOperator.getIgenyek(mode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//kulonvalasszuk az igenyeket
		
		
		
		
		// Igények
		
		haviIgenyek = new ArrayList<NapiIgenyek>();
		haviModositasok = new ArrayList<NapiIgenyek>();
		haviMaradjon = new ArrayList<NapiIgenyek>();
		
		
		if (leadottIgenyek.size() > 0) {
		
		for (int i = 1; i <= napokH; ++i) {
			
			NapiIgenyek napiIgenyek = new NapiIgenyek();
			NapiIgenyek napiModositasok = new NapiIgenyek();
			NapiIgenyek napiMaradjon = new NapiIgenyek();
			
			
			for (int j = 0; j < leadottIgenyek.size(); ++j) {
				
				if (i == leadottIgenyek.get(j).getNap() && leadottIgenyek.get(j).getTipus() != IgenyTipus.Maradjon) {
					
					if (leadottIgenyek.get(j).getTipus() == IgenyTipus.SzabadHetkoznap || 
							leadottIgenyek.get(j).getTipus() == IgenyTipus.SzabadHetvege) {
					napiIgenyek.addNapiIgenyek(leadottIgenyek.get(j));
					} else {
						napiModositasok.addNapiIgenyek(leadottIgenyek.get(j));
					}
					
				} else if (leadottIgenyek.get(j).getTipus() == IgenyTipus.Maradjon && i == leadottIgenyek.get(j).getNap()){
					napiMaradjon.addNapiIgenyek(leadottIgenyek.get(j));
				}
			}
			
			//csak akkor vizsgáljuk a napokra leadott igényeket, ha voltak rá igények
			if (napiIgenyek.getLengthOfNap() > 0) {
				haviIgenyek.add(napiIgenyek);
			}
			
			if (napiModositasok.getLengthOfNap() > 0) {
				haviModositasok.add(napiModositasok);
			}
			
			if (napiMaradjon.getLengthOfNap() > 0) {
				haviMaradjon.add(napiMaradjon);
			}
			
			
		}
		
		
		
		
		//System.out.println(this.delelottDolgozikVan.get(29).getName(0));
		

		
		
		//System.out.println(l1.getListaIndex());
		
		for (int i = 0; i < haviIgenyek.size(); ++i) {
			
			napok.add(honap + "."+ haviIgenyek.get(i).getNapiIgenyek(0).getNap() + "");
		}
		
		modositasIndex = haviIgenyek.size();
		
		for (int i = 0; i < haviModositasok.size(); ++i) {
			napok.add(honap + "."+ haviModositasok.get(i).getNapiIgenyek(0).getNap() + "");
		}
		
		l1.getListaIndex() = 0;
		
		lNap1.setText(napok.get(0) + "");
		lNap2.setText(napok.get(1) + "");
		lNap3.setText(napok.get(2) + "");
		
		lbSzeretne1.setText(haviIgenyek.get(0).getNapiIgenyek(0).getName());
		
		if (haviIgenyek.get(0).getLengthOfNap() > 1) {
			lbSzeretne2.setText(haviIgenyek.get(0).getNapiIgenyek(1).getName());
			//System.out.println("gsfgs");
			
			if (haviIgenyek.get(0).getLengthOfNap() > 2)
			lbSzeretne3.setText(haviIgenyek.get(0).getNapiIgenyek(2).getName());
		
		}
		
		
		}
		
		
		// végig megyünk a napokon, azon belül pedig a felhasználók, du, de, szabad cserenapjain,
		//ha van csere nap, akkor módosítuk a user értékét
		
		//szabadNapjaVan = new ArrayList<NaponDolgozik>();
		//delelottDolgozikVan = new ArrayList<NaponDolgozik>();
		//delutanDOlgozikVan = new ArrayList<NaponDolgozik>();
		
		
		
		System.out.println(delelottDolgozikVan.get(30).getName(0)); // idáig jó
		
		
		
		
		for (int i = 0; i < haviIgenyek.size(); ++i) { // végig megyünk a hó napjain
			
			for (int j = 0; j < haviIgenyek.get(i).getLengthOfNap(); ++j) { // ennyien adtak fel igényt a napra
				
				
				for (int k = 0; k < haviIgenyek.get(i).getNapiIgenyek(j).lengthOfAdnaErte(); ++k) {//mit adna érte j
						this.szabadNapjaVan.get(haviIgenyek.get(i).getNapiIgenyek(j).getAdnaErte(k) - 1).setErtekNevAlapjan(haviIgenyek.get(i).getNapiIgenyek(j).getName(), 2);
					
				}
				
				
			}
			
			
		}
		
		
		
		for (int i = 0; i < haviModositasok.size(); ++i) { // végig megyünk a hó napjain, akik módositast kértek
			
			for (int j = 0; j < haviModositasok.get(i).getLengthOfNap(); ++j) { // ennyien adtak fel igényt a napra
				
				
				for (int k = 0; k < haviModositasok.get(i).getNapiIgenyek(j).lengthOfAdnaErte(); ++k) { //végigmegyünk
					
					if (haviModositasok.get(i).getNapiIgenyek(j).getTipus() == IgenyTipus.Delelott) {
						
						this.delelottDolgozikVan.get(haviModositasok.get(i).getNapiIgenyek(j).getAdnaErte(k) - 1).setErtekNevAlapjan(haviModositasok.get(i).getNapiIgenyek(j).getName(), 2);
						
					} else {
						
						this.delutanDOlgozikVan.get(haviModositasok.get(i).getNapiIgenyek(j).getAdnaErte(k) - 1).setErtekNevAlapjan(haviModositasok.get(i).getNapiIgenyek(j).getName(), 2);
						
						
					}
					
				}
				
				
			}
			
			
		}
		
		
		// az első csere lehetőségek betöltése
		
		

			
		// töröljük a maradjon napokat 
		
		// először a szabad napokon megyünk végig
		
		
		toroldMaradjonErtekek(szabadNapjaVan);
		toroldMaradjonErtekek(delutanDOlgozikVan);
		toroldMaradjonErtekek(delelottDolgozikVan);
		
		
		

			
		
		
		
		
		
		listaElkeszitese();
		
		
		
		
		
		return null;
	}
	
	*/
	
	private void toroldMaradjonErtekek(ArrayList<NaponDolgozik> lista) {
		// TODO Auto-generated method stub
		
		System.out.println("Maradjonokat töröljük!");
		
		for (int i = 0; i < haviMaradjon.size(); ++i) { // végig megyünk a maradjon napokon
			
			for (int j = 0; j < haviMaradjon.get(i).getLengthOfNap(); ++j) {
				
				
				lista.get(haviMaradjon.get(i).getNapiIgenyek(j).getNap() - 1).remove(haviMaradjon.get(i).getNapiIgenyek(j).getName());
				
			}
			
		}
		
	}



	/**
	 * a dátum, és haviIgények lista kiválasztott elemei alapján elészíti a csere listát
	 * 
	 * FOntos paraméterek: 
	 * 
	 * l1.getListaIndex(): ez a nap lett kiválasztva
	 * szeretne index: kinek nézzük az igényeit
	 * haviIgenyek
	 *    
	 */
	private void listaElkeszitese() {
		
		int napIndexed = Integer.parseInt(napok.get(l1.getListaIndex()).split("\\.")[1]) - 1;
		
		userers = new ArrayList<ModifiedUser>();
		
		if (l1.getListaIndex() < haviIgenyek.size()) { // ha szabadNapról van szó
			
			Igeny ig = haviIgenyek.get(l1.getListaIndex()).getArrayList().get(l2.getListaIndex());
			
			
			for (int i = 0; i < ig.lengthOfAdnaErte(); ++i) { // végig megyünk a napokon amit adna
				//System.out.println("Adná érte: " + ig.getAdnaErte(i));
				
				ArrayList<User> idrtr = szabadNapjaVan.get(napIndexed).segment(delutanDOlgozikVan.get(ig.getAdnaErte(i) -1));
				ArrayList<User> idrtr2 = szabadNapjaVan.get(napIndexed).segment(delelottDolgozikVan.get(ig.getAdnaErte(i) - 1));
				
				
				for (int j = 0; j < idrtr.size(); ++j) {
					
					dolgozik dolg = ig.getUser().getList().get(napIndexed).getUserke();
					
					
					userers.add(new ModifiedUser(idrtr.get(j), 1, ig.getAdnaErte(i), dolg, ig.getNap()));
					
				}
				
				for (int j = 0; j < idrtr2.size(); ++j) {
					
					dolgozik dolg = ig.getUser().getList().get(napIndexed).getUserke();
					userers.add(new ModifiedUser(idrtr2.get(j), 1, ig.getAdnaErte(i), dolg, ig.getNap()));
					
				}
				
				
				
			}
			
		/*for (int i = 0; i < userers.size(); ++i) {
				
				System.out.println(userers.get(i));
				
			}
			
			*/
		System.out.println("második");
			removeKaracsonyFak(userers, 0); //szabadság karácsonyfákat
			removeHatNapozik(userers);
			
			


			
			
			
		} else { // külön délután, külön délelőtt, de nem ma
			
			/*
			 * if (l1.getListaIndex() < haviIgenyek.size()) { // ha szabadNapról van szó
			
			Igeny ig = haviIgenyek.get(l1.getListaIndex()).getArrayList().get(l2.getListaIndex());
			 */
			
			/*
			 * hányadik nap
			 * 
			 * 
			 */
			System.out.println("vajon miért?!");
			
			Igeny ig = haviModositasok.get(l1.getListaIndex() - haviIgenyek.size()).getArrayList().get(l2.getListaIndex());
			System.out.println("gfdg");
			
			for (int i = 0; i < ig.lengthOfAdnaErte(); ++i) {
				
				ArrayList<User> idrtr = null;
				 if (ig.getTipus() == IgenyTipus.Delelott) {
					 
					  idrtr = delelottDolgozikVan.get(napIndexed).segment(delutanDOlgozikVan.get(ig.getAdnaErte(i) -1));
						
				 } else {
					 
					  idrtr = delutanDOlgozikVan.get(napIndexed).segment(delelottDolgozikVan.get(ig.getAdnaErte(i) -1));
						
				 }
				 
					for (int j = 0; j < idrtr.size(); ++j) {
						
						dolgozik dolg = ig.getUser().getList().get(napIndexed).getUserke();
						
						
						userers.add(new ModifiedUser(idrtr.get(j), 1, ig.getAdnaErte(i), dolg, ig.getNap()));
						
					}
				 
				 
			}
			
			
			
			
			removeKaracsonyFak(userers, 0); // megnézi, hogy az igényelt nap miatt ne legyen karacsonyfa
			removeKaracsonyFak(userers, 3); // megnézi, hogy a cserélendő nap miatt nem lesz-e karacsonyfa
			

			
			
		}
		
		theRealUserers = new ArrayList<ModifiedUser>();
		
		for (int i = 0; i < userers.size(); ++i) {
			
			if (!userers.get(i).isLehetetlen()) {
				theRealUserers.add(userers.get(i));
				
			}
		}
		
	}
	
	//kitörli azokat a lehetséges cseréket, amelyek karácsonyfát generálnának
	
	
	private void removeHatNapozik(ArrayList<ModifiedUser> userers2) {
		// TODO Auto-generated method stub
		
		//addig megy jobbra balra az index elemtől, amig talál 1-1 szabadnapot
		
	}

	private void removeKaracsonyFak(ArrayList<ModifiedUser> userers, int mode) {
		/*
		 * mode = 0 szabadság
		 * mode = 1 delutan
		 * mode = 2 delelőtt
		 * 
		 * 
		 * a csere személy nem dolgozhat így:
		 *  délelőtt szabadnap délelőtt   ->  délelőtt délelőtt délelőtt   != délelőtt délután délelőtt
		 *  délelőtt szabadnap szabadnap  ->  délelőtt szabadnap délelőtt, délelőtt délután szabad
		 *  szabadnap szabadnap délelőtt  ->  szabadnap délelőtt délelőtt != szabadnap, délután délelőtt
		 *  szabadnap szabadnap szabadnap ->  ! szabad délelőtt szabad   ! szabad délután szabad
		 * délután szabadnap délután  ->    délután délután délután != délután délelőtt délután
		 * délután szabadnap szabadnap ->   délután délután szabad != délután délelott szabad
		 * szabad szabad délután       -> szabad délelőtt délután , szabad délután délután  
		 * 
		 * 
		 * szóval nem lehet:
		 * délelőtt délután délelőtt
		 * szabad délután délelőtt
		 * szabad délelőtt szabad
		 * szabad délután szabad
		 * délután délelőtt délelőtt
		 * délután délelőtt szabad
		 * 
		 * 
		 * kor
		 * 
		 */
		
		
		//korabbiHonap.get(korabbiHonap.size() - 1).getUser(user)?
		
		if (mode == 0) {
			
			
				for (ModifiedUser us: userers) {
					
					//System.out.println("dolgozik" + us.getDolg());
					
					User user = us.getUser();
					
					dolgozik b1 = null;
					dolgozik b3 = null;
					
					if (us.getEztAnapot() - 2 >= 0  && us.getEztAnapot()  <  user.getList().size()) {
					    b1 = user.get(us.getEztAnapot() - 2).getUserke();
					    b3 = user.get(us.getEztAnapot()).getUserke();
					    
					}  else if (us.getEztAnapot() - 2 < 0) {
						// itt a korábbi hónap utolsó napját kell nézni
						
						b3 = user.get(us.getEztAnapot()).getUserke();
						
						int korabbiIndex = getUserFromPrewMontsUserList(user);
						
						if (korabbiIndex != -1) {
							
							int indexR = this.korabbiHonapEmberei.get(korabbiIndex).getList().size();
							b1 = korabbiHonapEmberei.get(korabbiIndex).get(indexR - 1).getUserke();
							
							if (b1 == dolgozik.spec) {
								b1 = dolgozik.dolgVdu;
							}
							
							//System.out.println(korabbiHonapEmberei.get(korabbiIndex).getName() + " " + korabbiHonapEmberei.get(korabbiIndex).get(indexR - 1).getLeiras());
							
						} else {
							b1 = us.getDolg();
						}
						
					} else {
						b1 = user.get(us.getEztAnapot() - 2).getUserke();
						b3 = us.getDolg();
					}
					    //System.out.println(b1.getLeiras() + " " + b3.getLeiras() + " " + user.getName());
					    
					    int delelottB1 = b1 == dolgozik.dolgHde || b1 == dolgozik.dolgVde?1:10;
					    int delutanB1 = b1 == dolgozik.dolgHdu || b1 == dolgozik.dolgVdu?-1:10;
					    
					    int B1NapErtek = delelottB1 + delutanB1;  //szabad = 20; délután = 9, delelott 11
					    
					    
					    int delelottB3 = b3 == dolgozik.dolgHde || b3 == dolgozik.dolgVde?1:10;
					    int delutanB3 = b3 == dolgozik.dolgHdu || b3 == dolgozik.dolgVdu?-1:10;
					    
					    int B3NapErtek = delelottB3 + delutanB3;  //szabad = 20; délután = 9, delelott 11
					    
					    
					    int delelottB2 = us.getDolg() == dolgozik.dolgHde || us.getDolg() == dolgozik.dolgVde?1:10;
					    int delutanB2 = us.getDolg() == dolgozik.dolgHdu || us.getDolg() == dolgozik.dolgVdu?-1:10;
					
					    //System.out.println(delelottB2 + " " + delutanB2 + " fsdfsdfsdfsdf");
					
					    int B2NapErtek = delelottB2 + delutanB2;  //szabad = 20; délután = 9, delelott 11
					    
					    
					    //System.out.println(B1NapErtek + " "+ B2NapErtek + " " + B3NapErtek);
					/*
					 * délelőtt 11, délután 9 szabad 20
					 * tiltottak: 
						 * délelőtt délután délelőtt*
						 * szabad délután délelőtt*
						 * szabad délelőtt szabad*
						 * szabad délután szabad*
						 * délután délelőtt délelőtt
						 * délután délelőtt szabad
						 */
					    
					    
					    if ((B1NapErtek == 11 && B2NapErtek == 9 && B3NapErtek == 11) || 
					       (B1NapErtek == 20 && B2NapErtek == 9 && B3NapErtek == 11) ||
					       (B1NapErtek == 20 && B2NapErtek == 11 && B3NapErtek == 20) || 
					       (B1NapErtek == 20 && B2NapErtek == 9 && B3NapErtek == 20) ||
					       (B1NapErtek == 9 && B2NapErtek == 11 && B3NapErtek == 11) ||
					       (B1NapErtek == 9 && B2NapErtek == 11 && B3NapErtek == 20)) {
					    	
					    	us.setLehetetlenites(true);
					    	
					    //	System.out.println("fsdfd");
					    	
					    }
					 					
				}
		} else {
			/*
			 * 
			 * 
			 * Itt két dolgot kell nézni:
			 * 
			 * 1. A cserélendő nap két oldalán ép
			 */
			
			// végig megyünk a teljes listán
			
			
			
			for (ModifiedUser us: userers) {
				
				//System.out.println("dolgozik" + us.getDolg());
				
				User user = us.getUser();
				
				dolgozik b1 = null;
				dolgozik b3 = null;
				
				if (us.getNap() - 2 >= 0  && us.getNap()  <  user.getList().size()) {
				    b1 = user.get(us.getNap() - 2).getUserke();
				    b3 = user.get(us.getNap()).getUserke();
				    
				}  else if (us.getNap() - 2 < 0) {
					// itt a korábbi hónap utolsó napját kell nézni
					
					b3 = user.get(us.getNap()).getUserke();
					
					int korabbiIndex = getUserFromPrewMontsUserList(user);
					
					if (korabbiIndex != -1) {
						
						int indexR = this.korabbiHonapEmberei.get(korabbiIndex).getList().size();
						b1 = korabbiHonapEmberei.get(korabbiIndex).get(indexR - 1).getUserke();
						
						if (b1 == dolgozik.spec) {
							b1 = dolgozik.dolgVdu;
						}
						
						//System.out.println(korabbiHonapEmberei.get(korabbiIndex).getName() + " " + korabbiHonapEmberei.get(korabbiIndex).get(indexR - 1).getLeiras());
						
					} else {
						b1 = us.getDolg();
					}
					
				} else {
					b1 = user.get(us.getNap() - 2).getUserke();
					b3 = us.getDolg();
				}
				    //System.out.println(b1.getLeiras() + " " + b3.getLeiras() + " " + user.getName());
				    
				    int delelottB1 = b1 == dolgozik.dolgHde || b1 == dolgozik.dolgVde?1:10;
				    int delutanB1 = b1 == dolgozik.dolgHdu || b1 == dolgozik.dolgVdu?-1:10;
				    
				    int B1NapErtek = delelottB1 + delutanB1;  //szabad = 20; délután = 9, delelott 11
				    
				    
				    int delelottB3 = b3 == dolgozik.dolgHde || b3 == dolgozik.dolgVde?1:10;
				    int delutanB3 = b3 == dolgozik.dolgHdu || b3 == dolgozik.dolgVdu?-1:10;
				    
				    int B3NapErtek = delelottB3 + delutanB3;  //szabad = 20; délután = 9, delelott 11
				    
				    
				    int delelottB2 = us.getDolg() == dolgozik.dolgHde || us.getDolg() == dolgozik.dolgVde?1:10;
				    int delutanB2 = us.getDolg() == dolgozik.dolgHdu || us.getDolg() == dolgozik.dolgVdu?-1:10;
				
				    //System.out.println(delelottB2 + " " + delutanB2 + " fsdfsdfsdfsdf");
				
				    int B2NapErtek = delelottB2 + delutanB2;  //szabad = 20; délután = 9, delelott 11
				    
				    
				    //System.out.println(B1NapErtek + " "+ B2NapErtek + " " + B3NapErtek);
				/*
				 * délelőtt 11, délután 9 szabad 20
				 * tiltottak: 
					 * délelőtt délután délelőtt*
					 * szabad délután délelőtt*
					 * szabad délelőtt szabad*
					 * szabad délután szabad*
					 * délután délelőtt délelőtt
					 * délután délelőtt szabad
					 */
				    
				    
				    if ((B1NapErtek == 11 && B2NapErtek == 9 && B3NapErtek == 11) || 
				       (B1NapErtek == 20 && B2NapErtek == 9 && B3NapErtek == 11) ||
				       (B1NapErtek == 20 && B2NapErtek == 11 && B3NapErtek == 20) || 
				       (B1NapErtek == 20 && B2NapErtek == 9 && B3NapErtek == 20) ||
				       (B1NapErtek == 9 && B2NapErtek == 11 && B3NapErtek == 11) ||
				       (B1NapErtek == 9 && B2NapErtek == 11 && B3NapErtek == 20)) {
				    	
				    	us.setLehetetlenites(true);
				    	
				    //	System.out.println("fsdfd");
				    	
				    }
				 					
			}
			

			
			
		}
		
		
	}

	private int getUserFromPrewMontsUserList(User user) {
		// TODO Auto-generated method stub
		boolean talalt = false;
		int ret = -1;
		
		for (int i = 0; i < this.korabbiHonapEmberei.size() && !talalt; ++i) {
			
			if (korabbiHonapEmberei.get(i).getName().equals(user.getName())) {
				
				talalt = true;
				ret = i;
			}
		}
		
		return ret;
	}

	private boolean karacsonyfa(User us, Beo b1, Beo b2, Beo b3) {
		
		return true;
	}
	
    /**-
     * 
     * @param lista
     * @param i
     * 
     * A lista és a korábbi lista alapján kitörli azokat a napokat, amelyeket
     * nem cserélhet különböző okok miatt, pl. karácsonyfa
     * 
     * 
     * 
     */
	private void toroldARosszakat(ArrayList<NaponDolgozik> lista, int i) {
		// TODO Auto-generated method stub
		
	}
	


	private void napEltolas(int i) {
		
		
		if (i >= modositasIndex) {
			
			this.btnHVK.setBackground(Color.green);
			this.btnSzabadN.setBackground(Color.orange);
		} else {
			
			this.btnHVK.setBackground(Color.orange);
			this.btnSzabadN.setBackground(Color.green);
		}
		
		if (i == 0) {
			
			btnNapVissza.setEnabled(false);
			lNap1.setText(napok.get(0) + "");
			lNap2.setText(napok.get(1) + "");
			lNap3.setText(napok.get(2) + "");
			
			nap1.setBackground(Color.GREEN);
			nap2.setBackground(Color.ORANGE);
			nap3.setBackground(Color.ORANGE);
			
			nap1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			nap2.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
			nap3.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
			
		} else if (i == napok.size() - 1) {
			
			btnNapNext.setEnabled(false);
			int length = napok.size() - 1;
			
			lNap1.setText(napok.get(length - 2) + "");
			lNap2.setText(napok.get(length - 1) + "");
			lNap3.setText(napok.get(length) + "");
			
			
			nap1.setBackground(Color.ORANGE);
			nap2.setBackground(Color.ORANGE);
			nap3.setBackground(Color.GREEN);
			
			nap3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			nap1.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
			nap2.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
			
		}
		
		else {
			
			btnNapNext.setEnabled(true);
			btnNapVissza.setEnabled(true);
			lNap1.setText(napok.get(i - 1) + "");
			lNap2.setText(napok.get(i) + "");
			lNap3.setText(napok.get(i + 1) + "");
			
			nap1.setBackground(Color.ORANGE);
			nap2.setBackground(Color.GREEN);
			nap3.setBackground(Color.ORANGE);
			
			nap2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			nap1.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
			nap3.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		}
		
		if (l1.getListaIndex() < modositasIndex) {
		if (haviIgenyek.get(l1.getListaIndex()).getLengthOfNap() > 0) {
			
			
			//a szabadnapokat vesszük nap váltás után
			btnSzabadN.setBackground(Color.green);
			this.btnHVK.setBackground(Color.orange);
			
			lbSzeretne1.setText(haviIgenyek.get(l1.getListaIndex()).getNapiIgenyek(0).getName());
			
			btnSzeretneNext.setEnabled(false);
			btnSzeretneVisszsa.setEnabled(false);
			lbSzeretne2.setText("");
			lbSzeretne3.setText("");
			
			szeretne1.setBackground(Color.green);
			szeretne2.setBackground(Color.orange);
			szeretne3.setBackground(Color.orange);
			//szeretneIndex = 0;
			
			szeretne1.setBorder(BorderFactory.createLineBorder(Color.black));
			szeretne2.setBorder(BorderFactory.createLineBorder(Color.orange));
			szeretne3.setBorder(BorderFactory.createLineBorder(Color.orange));
			
			if (haviIgenyek.get(l1.getListaIndex()).getLengthOfNap() > 1) {
				btnSzeretneNext.setEnabled(true);
				
				lbSzeretne2.setText(haviIgenyek.get(l1.getListaIndex()).getNapiIgenyek(1).getName());
			}	
			
			if (haviIgenyek.get(l1.getListaIndex()).getLengthOfNap() > 2) {
				
					lbSzeretne3.setText(haviIgenyek.get(l1.getListaIndex()).getNapiIgenyek(2).getName());
				}
			
			
		}
		} else {
			
			
			
			if (haviModositasok.get(l1.getListaIndex() - haviIgenyek.size()).getLengthOfNap() > 0) {
				
				
				//a szabadnapokat vesszük nap váltás után
				btnSzabadN.setBackground(Color.orange);
				this.btnHVK.setBackground(Color.green);
				
				lbSzeretne1.setText(haviModositasok.get(l1.getListaIndex() - haviIgenyek.size()).getNapiIgenyek(0).getName());
				
				btnSzeretneNext.setEnabled(false);
				btnSzeretneVisszsa.setEnabled(false);
				lbSzeretne2.setText("");
				lbSzeretne3.setText("");
				
				szeretne1.setBackground(Color.green);
				szeretne2.setBackground(Color.orange);
				szeretne3.setBackground(Color.orange);
				//szeretneIndex = 0;
				
				szeretne1.setBorder(BorderFactory.createLineBorder(Color.black));
				szeretne2.setBorder(BorderFactory.createLineBorder(Color.orange));
				szeretne3.setBorder(BorderFactory.createLineBorder(Color.orange));
				
				if (haviModositasok.get(l1.getListaIndex() - haviIgenyek.size()).getLengthOfNap() > 1) {
					btnSzeretneNext.setEnabled(true);
					
					lbSzeretne2.setText(haviModositasok.get(l1.getListaIndex() - modositasIndex).getNapiIgenyek(1).getName());
				}	
				
				if (haviModositasok.get(l1.getListaIndex() - haviIgenyek.size()).getLengthOfNap() > 2) {
					
						lbSzeretne3.setText(haviModositasok.get(l1.getListaIndex() - modositasIndex).getNapiIgenyek(2).getName());
					}
				
				
			}
			
			
			
			
			
		}
	}
	
	
	
	public void configBetoltes() {
		// TODO Auto-generated method stub
		
		//configBetoltes();
		try {
		
			BufferedReader br = new BufferedReader(new FileReader("series.conf"));
			
			honap = Integer.parseInt(br.readLine().split("=")[1]);
			
			napokH = Integer.parseInt(br.readLine().split("=")[1]);
			
			kezdo = Integer.parseInt(br.readLine().split("=")[1]);
			
			expertM = br.readLine().split("=")[1];
			
			expertCs = br.readLine().split("=")[1];
	
			t1t2M = br.readLine().split("=")[1];
			
			 t1t2Cs = br.readLine().split("=")[1];
			
			 fix = br.readLine().split("=")[1];
			
			 spec = br.readLine().split("=")[1];
			
			 HVHT = br.readLine().split("=")[1];
			 HTHV = br.readLine().split("=")[1];
			 
			 multHonap = br.readLine().split("=")[1];
			 
			 br.close();
		} catch (Exception ex) {}
		
			
		}
	
	

}
