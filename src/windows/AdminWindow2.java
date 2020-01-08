package windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import objects.FileOperator;
import objects.Igeny;
import objects.IgenyTipus;
import objects.NapiIgenyek;

public class AdminWindow2 extends JFrame {

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
	
	private int napokIndex;
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
		
		mntmExpertIgnyek.addActionListener(e->igenyRendereles(1));
		
		JMenuItem mntmTtIgnyek = new JMenuItem("T1/T2 Igények");
		mntmTtIgnyek.addActionListener(e->igenyRendereles(2));
		
		mnIgnyek.add(mntmTtIgnyek);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(0, 6, 10, 30));
		setContentPane(contentPane);
		
		contentPane.setVisible(false);
		
		
		
		
		
		
		
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
		btnNapVissza.addActionListener(e->napEltolas(--napokIndex));
		btnNapVissza.setEnabled(false);
		
		
		JPanel panel_5 = new JPanel();
		getContentPane().add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		btnNapNext = new JButton(">>");
		panel_5.add(btnNapNext, BorderLayout.WEST);
		btnNapNext.addActionListener(e->napEltolas(++napokIndex));
		
		JPanel panel_6 = new JPanel();
		getContentPane().add(panel_6);
		
		JPanel panel_7 = new JPanel();
		getContentPane().add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		btnSzabadN = new JButton("Szabadnap");
		panel_7.add(btnSzabadN, BorderLayout.CENTER);
		
		JPanel panel_8 = new JPanel();
		getContentPane().add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		btnHVK = new JButton("HT");
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
		
		JPanel panel_17 = new JPanel();
		getContentPane().add(panel_17);
		panel_17.setLayout(new BorderLayout(0, 0));
		
		btnSzeretneNext = new JButton(">>");
		panel_17.add(btnSzeretneNext, BorderLayout.WEST);
		
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
		
		JPanel vele2 = new JPanel();
		vele2.setBackground(Color.ORANGE);
		getContentPane().add(vele2);
		vele2.setLayout(new BorderLayout(0, 0));
		
		lbVele2 = new JLabel("Név2");
		vele2.add(lbVele2, BorderLayout.CENTER);
		
		JPanel vele3 = new JPanel();
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
		
		
		
	}

	private Object igenyRendereles(int mode) {
		// TODO Auto-generated method stub
		
		contentPane.setVisible(true);
		
		configBetoltes();
		
		// napok rész
		
		napokIndex = napokH;
		
		napok = new ArrayList<String>();
		
		System.out.println(napokIndex);
		
		for (int i = 0; i < napokIndex; ++i) {
			
			napok.add(honap+ "." +  (i + 1));
		}
		
		napokIndex = 0;
		
		lNap1.setText(napok.get(0) + "");
		lNap2.setText(napok.get(1) + "");
		lNap3.setText(napok.get(2) + "");
		
		
		try {
			leadottIgenyek = FileOperator.getIgenyek(mode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//kulonvalasszuk az igenyeket
		
		
		
		
		// Igények
		
		haviIgenyek = new ArrayList<NapiIgenyek>();
		
		if (leadottIgenyek.size() > 0) {
		
		for (int i = 1; i <= napokH; ++i) {
			
			NapiIgenyek napiIgenyek = new NapiIgenyek();
			
			
			for (int j = 0; j < leadottIgenyek.size(); ++j) {
				
				if (i == leadottIgenyek.get(j).getNap() && leadottIgenyek.get(j).getTipus() != IgenyTipus.Maradjon) {
					
					napiIgenyek.addNapiIgenyek(leadottIgenyek.get(j));
					
				}
			}
			
			//csak akkor vizsgáljuk a napokra leadott igényeket, ha voltak rá igények
			if (napiIgenyek.getLengthOfNap() > 0) {
				haviIgenyek.add(napiIgenyek);
			}
			
			
		}
		}
		
		
		
		return null;
	}

	private void napEltolas(int i) {
		
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
	}
	
	
	
	public void configBetoltes() {
		// TODO Auto-generated method stub
		
		File config = new File ("series.conf");
		
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
			 
			 br.close();
		} catch (Exception ex) {}
		
			
		}
	
	

}
