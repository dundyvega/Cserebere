package windows;
import java.awt.EventQueue;

import javax.swing.JFrame;
//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;

import objects.FileOperator;
import objects.User;
import objects.dolgozik;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class UserWindow2 {

	private JFrame frame;
	
//ezek mind azután lesznek feltöltve adatokkal, miután a kezelő kiválasztotta magát a listából
    
    //(amelyiket használom rakok mellé egy X-t)
    
    //minden gomb
    private ArrayList<JButton> gombok = new ArrayList<JButton>();         /*minden gomb X*/
    
    private ArrayList<JButton> hasznalt = new ArrayList<JButton>();      /*használt gombok X*/
    
    //hétvégéket jelölő gombok
    private ArrayList<JButton> hetvege = new ArrayList<JButton>();        /*hétvégék X*/
    
    //szabad hétvége
    private ArrayList<JButton> szabadHetvege = new ArrayList<JButton>();   /*X*/
    
    //hétköznapokat jelölő gombok
    private ArrayList<JButton> hetkoznap = new ArrayList<JButton>();         /*X*/
    
    //szabad hétköznap
    private ArrayList<JButton> szabadHetkoznap = new ArrayList<JButton>();
    
    
    //speciális napokat jelölő gombok
    private ArrayList<JButton> specialis = new ArrayList<JButton>();           
    
    //du beosztásos gombok
    private ArrayList<JButton> delutanok = new ArrayList<JButton>();          /*X*/
    
    //de beosztásos gombok
    private ArrayList<JButton> delelottok = new ArrayList<JButton>(); 
    
    private JButton megjegyzes;
    private JTextArea textArea;
    /*X*/
    
   //ebben a hónapban vagyunk
    private int honapSzama;
    
    //tipus
    
    private JComboBox<String> custTipe;
    private JComboBox <User> jComboBox1;
    
    private JButton csereButton, visszaButton;
    
    
    private boolean cserelniLehet = false;
    
    
    //paraméterek
    
    private int hetvegekSzabadok = 0;
    private int hetkoznapokSzabadok = 0;
    private int delutanokSzabadok = 0;
    private int delelottokSzabadok = 0;
    
    private JLabel label_11;
    
    
    
    // End of variables declaration       
    
    
    
    //exppert mappa, amiben az expertek txt fájlai vannak
	private String expertMapa;
	//expert beosztás
	private String expertCsiri;
	//t1t2 mappa, amiben a t1t2-esek txt fájlai vannak
	private String t1t2Mapa;
	//t1t2 beosztás
	private String t1t2Csiri;
	//ennyi napot lehet lefixálni
	private int fixNapok;
	private int fixNapok2;
	//ezekkel nem lehet cserélni
	private String[] speckok;
	
	//felhasználók
	private ArrayList<User> users;
	
	//alapszín
	private Color alapszin;
	
	private JPanel panel_3, panel_4, panel_5, panel_6, panel_7, panel_16, panel_18, panel_20, panel_22;

	private int visszaertek;
	

	
	//betolti az user paramétereit
	
	
	public void userBetoltes() {
		
		
		
    	String szoveg;
    	String datumos;
    	
    	try {
		User us = FileOperator.getUserInfo(expertCsiri, speckok);
		
		jComboBox1.addItem(us);
		//custTipe.setVisible(false);
		jComboBox1.setEnabled(true);
		
		for (int i = 0; i < hasznalt.size(); ++i) {
			
			hasznalt.get(i).setEnabled(true);
			
			szoveg = us.getList().get(i).getLeiras();
			//System.out.println(szoveg);
			
			if (szoveg.contains("HD Hiba EXP")) {
				szoveg = szoveg.substring(0, szoveg.length() - 11);
				
			} 
			
			if (szoveg.contains("HD Hibakezeles")) {
				szoveg = szoveg.substring(0, szoveg.length() - 14);
			}
			
			if (us.getList().get(i).getUserke() == dolgozik.spec) {
				
				//szoveg = "nem cserélhető";
				szoveg = szoveg.substring(12, szoveg.length());
				hasznalt.get(i).setEnabled(false);
			}
			
			if (us.getList().get(i).getUserke() == dolgozik.dolgE) {
				
				szoveg = "Éjszaka";
				hasznalt.get(i).setEnabled(false);
			}
			
			if (i > 1 && us.getList().get(i-1).getUserke() == dolgozik.dolgE) {
				hasznalt.get(i).setEnabled(false);
			}
			
			//System.out.println(szoveg);
			
			
			szoveg = hasznalt.get(i).getText() + "\n" + szoveg;
			
			hasznalt.get(i).setText("<html>" + szoveg.replaceAll("\\n", "<br>") + "</html>");
			
			
			
			if (us.get(i).getUserke() == dolgozik.dolgVde) { //délelőtt dolgozik
				delelottok.add(hasznalt.get(i));
			}
			
			if (us.get(i).getUserke() == dolgozik.dolgVdu) {
				delutanok.add(hasznalt.get(i));
			}
			
			if (us.get(i).getUserke() == dolgozik.szabadV) {
				
				
				
				if (hetvege.contains(hasznalt.get(i))) {
					
					szabadHetvege.add(hasznalt.get(i));
					
					
				} else {
					szabadHetkoznap.add(hasznalt.get(i));
					
				}
				
				//hasznalt.get(i).setEnabled(false);	 // nem kell, mivel a szabadnapok is fixálódhatnak
			}	
		}
    	} catch (Exception ex) {}
	}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserWindow2 window = new UserWindow2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		String username = System.getProperty("user.name");
		
	}

	/**
	 * Create the application.
	 */
	public UserWindow2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 952, 542);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 7));
		
		JLabel label = new JLabel();
		label.setText("hétfő");
		panel.add(label);
		
		JLabel label_1 = new JLabel();
		label_1.setText("kedd");
		panel.add(label_1);
		
		JLabel label_2 = new JLabel();
		label_2.setText("szerda");
		panel.add(label_2);
		
		JLabel label_3 = new JLabel();
		label_3.setText("csütörtök");
		panel.add(label_3);
		
		JLabel label_4 = new JLabel();
		label_4.setText("péntek");
		panel.add(label_4);
		
		JLabel label_5 = new JLabel();
		label_5.setText("szombat");
		panel.add(label_5);
		
		JLabel label_6 = new JLabel();
		label_6.setText("vasárnap");
		panel.add(label_6);
		
		JButton button_0 = new JButton();
		panel.add(button_0);
		
		JButton button_1 = new JButton();
		panel.add(button_1);
		
		JButton button_2 = new JButton();
		panel.add(button_2);
		
		JButton button_3 = new JButton();
		panel.add(button_3);
		
		JButton button_4 = new JButton();
		panel.add(button_4);
		
		JButton button_5 = new JButton();
		panel.add(button_5);
		
		JButton button_6 = new JButton();
		panel.add(button_6);
		
		JButton button_7 = new JButton();
		panel.add(button_7);
		
		JButton button_8 = new JButton();
		panel.add(button_8);
		
		JButton button_9 = new JButton();
		panel.add(button_9);
		
		JButton button_10 = new JButton();
		panel.add(button_10);
		
		JButton button_11 = new JButton();
		panel.add(button_11);
		
		JButton button_12 = new JButton();
		panel.add(button_12);
		
		JButton button_13 = new JButton();
		panel.add(button_13);
		
		JButton button_14 = new JButton();
		panel.add(button_14);
		
		JButton button_15 = new JButton();
		panel.add(button_15);
		
		JButton button_16 = new JButton();
		panel.add(button_16);
		
		JButton button_17 = new JButton();
		panel.add(button_17);
		
		JButton button_18 = new JButton();
		panel.add(button_18);
		
		JButton button_19 = new JButton();
		panel.add(button_19);
		
		JButton button_20 = new JButton();
		panel.add(button_20);
		
		JButton button_21 = new JButton();
		panel.add(button_21);
		
		JButton button_22 = new JButton();
		panel.add(button_22);
		
		JButton button_23 = new JButton();
		panel.add(button_23);
		
		JButton button_24 = new JButton();
		panel.add(button_24);
		
		JButton button_25 = new JButton();
		panel.add(button_25);
		
		JButton button_26 = new JButton();
		panel.add(button_26);
		
		JButton button_27 = new JButton();
		panel.add(button_27);
		
		JButton button_28 = new JButton();
		panel.add(button_28);
		
		JButton button_29 = new JButton();
		panel.add(button_29);
		
		JButton button_30 = new JButton();
		panel.add(button_30);
		
		JButton button_31 = new JButton();
		panel.add(button_31);
		
		JButton button_32 = new JButton();
		panel.add(button_32);
		
		JButton button_33 = new JButton();
		panel.add(button_33);
		
		JButton button_34 = new JButton();
		panel.add(button_34);
		
		JButton button_35 = new JButton();
		panel.add(button_35);
		
		JButton button_36 = new JButton();
		panel.add(button_36);
		
		/* gombok hozzáadása a listákhoz */
		
		alapszin = button_0.getBackground();
    	
    	gombok.add(button_0);
    	gombok.add(button_1);
    	gombok.add(button_2);
    	gombok.add(button_3);
    	gombok.add(button_4);
    	gombok.add(button_5);
    	gombok.add(button_6);
    	gombok.add(button_7);
    	gombok.add(button_8);
    	gombok.add(button_9);
    	gombok.add(button_10);
    	gombok.add(button_11);
    	gombok.add(button_12);
    	gombok.add(button_13);
    	gombok.add(button_14);
    	gombok.add(button_15);
    	gombok.add(button_16);
    	gombok.add(button_17);
    	gombok.add(button_18);
    	gombok.add(button_19);
    	gombok.add(button_20);
    	gombok.add(button_21);
    	gombok.add(button_22);
    	gombok.add(button_23);
    	gombok.add(button_24);
    	gombok.add(button_25);
    	gombok.add(button_26);
    	gombok.add(button_27);
    	gombok.add(button_28);
    	gombok.add(button_29);
    	gombok.add(button_30);
    	gombok.add(button_31);
    	gombok.add(button_32);
    	gombok.add(button_33);
    	gombok.add(button_34);
    	gombok.add(button_35);
    	gombok.add(button_36);

    	
    	for (int i = 0; i < gombok.size(); ++i) {
    		
    		gombok.get(i).setEnabled(false);
    		
    		/*
    		 * figyelő hozzárendelése a gombokhoz
    		 * 
    		 * 
    		 * 
    		 */
    		
    		gombok.get(i).addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					szinezo(e);
				}
    			
    		});
    	}
    	
    	
    	 configBetoltes();
		
		
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_1.add(panel_8);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		GridLayout gl_panel_2 = new GridLayout(0, 2);
		gl_panel_2.setVgap(1);
		gl_panel_2.setHgap(1);
		panel_2.setLayout(gl_panel_2);
		
		JLabel label_7 = new JLabel();
		label_7.setText("Szabad hétvége");
		panel_2.add(label_7);
		
		panel_3 = new JPanel();
		panel_3.setBackground(new Color(102, 255, 102));
		panel_2.add(panel_3);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGap(0, 132, Short.MAX_VALUE)
				.addGap(0, 132, Short.MAX_VALUE)
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGap(0, 27, Short.MAX_VALUE)
				.addGap(0, 27, Short.MAX_VALUE)
		);
		panel_3.setLayout(gl_panel_3);
		
		JLabel label_8 = new JLabel();
		label_8.setText("szabad hétköznap");
		panel_2.add(label_8);
		
		panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 153, 102));
		panel_2.add(panel_4);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGap(0, 132, Short.MAX_VALUE)
				.addGap(0, 132, Short.MAX_VALUE)
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGap(0, 27, Short.MAX_VALUE)
				.addGap(0, 27, Short.MAX_VALUE)
		);
		panel_4.setLayout(gl_panel_4);
		
		JLabel label_9 = new JLabel();
		label_9.setText("délután");
		panel_2.add(label_9);
		
		panel_5 = new JPanel();
		panel_5.setBackground(new Color(51, 204, 255));
		panel_2.add(panel_5);
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGap(0, 132, Short.MAX_VALUE)
				.addGap(0, 132, Short.MAX_VALUE)
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGap(0, 27, Short.MAX_VALUE)
				.addGap(0, 27, Short.MAX_VALUE)
		);
		panel_5.setLayout(gl_panel_5);
		
		JLabel label_10 = new JLabel();
		label_10.setText("délelőtt");
		panel_2.add(label_10);
		
		panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 255, 51));
		panel_2.add(panel_6);
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGap(0, 132, Short.MAX_VALUE)
				.addGap(0, 132, Short.MAX_VALUE)
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGap(0, 27, Short.MAX_VALUE)
				.addGap(0, 27, Short.MAX_VALUE)
		);
		panel_6.setLayout(gl_panel_6);
		
	 label_11 = new JLabel();
		label_11.setText("maradjon");
		panel_2.add(label_11);
		
		panel_7 = new JPanel();
		panel_7.setBackground(new Color(255, 204, 204));
		panel_2.add(panel_7);
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGap(0, 132, Short.MAX_VALUE)
				.addGap(0, 132, Short.MAX_VALUE)
		);
		gl_panel_7.setVerticalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGap(0, 27, Short.MAX_VALUE)
				.addGap(0, 27, Short.MAX_VALUE)
		);
		panel_7.setLayout(gl_panel_7);
		
		JPanel panel_14 = new JPanel();
		panel_1.add(panel_14);
		panel_14.setLayout(new GridLayout(0, 2, 0, 0));
		
		panel_16 = new JPanel();
		panel_16.setBackground(SystemColor.desktop);
		panel_14.add(panel_16);
		
		JPanel panel_17 = new JPanel();
		panel_14.add(panel_17);
		
		panel_18 = new JPanel();
		panel_18.setBackground(new Color(102, 51, 0));
		panel_14.add(panel_18);
		
		JPanel panel_19 = new JPanel();
		panel_14.add(panel_19);
		
		panel_20 = new JPanel();
		panel_20.setBackground(new Color(0, 0, 255));
		panel_14.add(panel_20);
		
		JPanel panel_21 = new JPanel();
		panel_14.add(panel_21);
		
		panel_22 = new JPanel();
		panel_22.setBackground(Color.MAGENTA);
		panel_14.add(panel_22);
		
		JPanel panel_23 = new JPanel();
		panel_14.add(panel_23);
		
		JPanel panel_24 = new JPanel();
		panel_14.add(panel_24);
		
		JPanel panel_9 = new JPanel();
		panel_1.add(panel_9);
		panel_9.setLayout(new GridLayout(0, 1, 0, 0));
		
		csereButton = new JButton("Csere ajánló");
		panel_9.add(csereButton);
		
		JPanel panel_12 = new JPanel();
		panel_9.add(panel_12);
		
		visszaButton = new JButton("Vissza");
		visszaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (visszaertek == 1) {
					visszaertek = 0;
					panel_16.setVisible(false);
					panel_18.setVisible(false);
					panel_20.setVisible(false);
					panel_22.setVisible(false);
					
					panel_7.setVisible(true);
					
					for (int i = 0; i < hasznalt.size(); ++i) {
						
						if (hasznalt.get(i).getBackground().equals(panel_16.getBackground()) ||
								hasznalt.get(i).getBackground().equals(panel_18.getBackground()) ||
								hasznalt.get(i).getBackground().equals(panel_20.getBackground()) || 
								hasznalt.get(i).getBackground().equals(panel_22.getBackground())) {
							hasznalt.get(i).setBackground(alapszin);
						}
						
						
						if (hasznalt.get(i).getBackground().equals(panel_3.getBackground()) ||
								hasznalt.get(i).getBackground().equals(panel_4.getBackground()) ||
								hasznalt.get(i).getBackground().equals(panel_5.getBackground()) ||
								hasznalt.get(i).getBackground().equals(panel_6.getBackground()) ||
								hasznalt.get(i).getBackground().equals(panel_7.getBackground())) {
									
									hasznalt.get(i).setEnabled(true);
								}
					}
					
					visszaButton.setVisible(false);
					
					cserelniLehet = false;
				
			} else if (visszaertek == 2) {
				System.out.println("megegyzésből visszalépés");
				visszaertek = 1;
				csereButton.setVisible(true);
				textArea.setVisible(false);
				megjegyzes.setVisible(false);
				visszaertek = 1;
			}
			}
		});
		panel_9.add(visszaButton);
		
		JPanel panel_13 = new JPanel();
		panel_9.add(panel_13);
		
		JPanel panel_15 = new JPanel();
		panel_1.add(panel_15);
		panel_15.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_15.add(scrollPane, BorderLayout.CENTER);
		
		 textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		megjegyzes = new JButton("Mentés");
		megjegyzes.setVisible(false);
		
		megjegyzes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//ide jön
			}
			
		});
		

		scrollPane.setColumnHeaderView(megjegyzes);
		
		textArea.setVisible(false);
		
		JPanel panel_10 = new JPanel();
		frame.getContentPane().add(panel_10, BorderLayout.NORTH);
		panel_10.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_11 = new JPanel();
		panel_10.add(panel_11);
		
		custTipe = new JComboBox<String>();
		panel_10.add(custTipe);
		custTipe.addItem("Választás");
		custTipe.addItem("Expert");
		custTipe.addItem("T1/T2");
		
		
		custTipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerTipeActionPerformed(evt);
            }
        });
		
		
		jComboBox1 = new JComboBox<User>();
		//jComboBox1.setEnabled(false);
		panel_10.add(jComboBox1);
		
		
		jComboBox1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jComboBox1ActionPerformed(e);
			}
        	
        });
		
		//gombok rendezése
		
		//nem használt panelek láthatatlanok
		
		panel_16.setVisible(false);
		panel_18.setVisible(false);
		panel_20.setVisible(false);
		panel_22.setVisible(false);
		
		//gombok láthatatlanok
		
		//csereButton.setVisible(false);
		visszaButton.setVisible(false);
		
		csereButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			
				csereGombMegnyomva();
				
			}
			
		});
		
		
		//userBetoltes();
		
	}
	
	
    protected void csereGombMegnyomva() {
    	if (!cserelniLehet) {
    	
    	String felugroAblak = "";
    	
    	if (hetvegekSzabadok < 0) {
    		felugroAblak += "Nem maradt elég szabad hétvége.\n";
    	}
    	
    	if (hetkoznapokSzabadok < 0) {
    		felugroAblak += "Nem maradt elég szabad hétköznap.\n";
    	}
    	
    	if (delutanokSzabadok < 0) {
    		felugroAblak += "Nem maradt elég délután.\n";
    	}
    	
    	if (delelottokSzabadok < 0) {
    		felugroAblak += "Nem maradt elég délelőtt.\n";
    	}
    	
    	if (fixNapok < 0) {
    		felugroAblak += "Maximum " + fixNapok2 + " fix nap jelölhető!\n";
    	}
    	
    	if (!felugroAblak.contentEquals(""))  {
    		//ha az egyik feltétel sérül
    		JOptionPane.showMessageDialog(null, felugroAblak);
    		
    	} else {
    		
    		//figyelmeztetés, mivel vissza menni nem lehet
    		int dialogResult = JOptionPane.showConfirmDialog(null, "Biztos vagy ebben?");
    		
    		if (dialogResult == JOptionPane.YES_OPTION) {
    			System.out.println("taka");
    			cserelniLehet = true;
    			
    			for (int i = 0; i < hasznalt.size(); ++i) {
    				if (!hasznalt.get(i).getBackground().equals(alapszin)) {
    					hasznalt.get(i).setEnabled(false);
    				}
    				
    			}
    			
    			visszaButton.setVisible(true);
    			visszaertek = 1;
    			csereButton.setText("Következő");
    			
    			//cserék láthatóvá/láthatatlanná tétele
    			
    			/*panel_3.setVisible(false);
    			panel_4.setVisible(false);
    			panel_5.setVisible(false);
    			panel_6.setVisible(false);
    			panel_7.setVisible(false);
    			*/
    			//panel_7.setVisible(false);
    			//label_11.setVisible(false);
    			
    			panel_16.setVisible(true);
    			panel_18.setVisible(true);
    			panel_20.setVisible(true);
    			panel_22.setVisible(true);
    			
    			
    		}
    		
    		
    	}
    	} else {
    		
    		
    			
    			
    			ArrayList<Integer> panel3 = new ArrayList<Integer>();
    			ArrayList<Integer> panel4 = new ArrayList<Integer>();
    			ArrayList<Integer> panel5 = new ArrayList<Integer>();
    			ArrayList<Integer> panel6 = new ArrayList<Integer>();
    			ArrayList<Integer> panel7 = new ArrayList<Integer>();
    			
    			ArrayList<Integer> panel16 = new ArrayList<Integer>();
    			ArrayList<Integer> panel18 = new ArrayList<Integer>();
    			ArrayList<Integer> panel20 = new ArrayList<Integer>();
    			ArrayList<Integer> panel22 = new ArrayList<Integer>();
    			
    			for (int i = 0; i < hasznalt.size(); ++i) {
    				
    				if (hasznalt.get(i).getBackground().equals(panel_3.getBackground())) {
    					panel3.add(i);
    				}
    				
    				if (hasznalt.get(i).getBackground().equals(panel_4.getBackground())) {
    					panel4.add(i);
    				}
    				
    				if (hasznalt.get(i).getBackground().equals(panel_5.getBackground())) {
    					panel5.add(i);
    				}
    				
    				if (hasznalt.get(i).getBackground().equals(panel_6.getBackground())) {
    					panel6.add(i);
    				}
    				
    				if (hasznalt.get(i).getBackground().equals(panel_7.getBackground())) {
    					panel7.add(i);
    				}
    				
    				if (hasznalt.get(i).getBackground().equals(panel_16.getBackground())) {
    					panel16.add(i);
    				}
    				
    				if (hasznalt.get(i).getBackground().equals(panel_18.getBackground())) {
    					panel18.add(i);
    				}
    				
    				if (hasznalt.get(i).getBackground().equals(panel_20.getBackground())) {
    					panel20.add(i);
    				}
    				
    				if (hasznalt.get(i).getBackground().equals(panel_22.getBackground())) {
    					panel22.add(i);
    				}

    			}
    			
    			if (panel3.size() <= panel16.size() &&
    					panel4.size() <= panel18.size() &&
    					panel5.size() <= panel20.size() &&
    					panel6.size() <= panel22.size()) {
    				
    				System.out.println("teljesül");
    				
    				int dialogResult = JOptionPane.showConfirmDialog(null, "Biztos vagy ebben?");
    				
    				
    	    		
    	    		if (dialogResult == JOptionPane.YES_OPTION) {
    	    			
    	    			megjegyzes.setVisible(true);
    	    			textArea.setVisible(true);
    	    			csereButton.setVisible(false);
    	    			
    	    			visszaertek = 2;
    	    			
    	    			
    	    			
    	    		}
    				
    				
    			} else {
    				
    				String szoveg = "";
    				if (panel4.size() > panel18.size()) {
    					szoveg += "Nem adtál meg elég szabad hétköznapot.\n";
    				}
    				
    				if (panel3.size() > panel16.size()) {
    					szoveg += "Nem adtál meg elég szabad hétköznapot.\n";
    				}
    				
    				if (panel5.size() > panel20.size()) {
    					szoveg += "Nem adtál meg elég délutánt\n";
    				}
    				
    				if (panel6.size() > panel22.size()) {
    					szoveg += "Nem adtál meg elég délelőttöt.";
    				}
    				
    				JOptionPane.showMessageDialog(null, szoveg);
    				
    				
    			}
    			
    			
    			
    	}
	}


	private void jComboBox1ActionPerformed(ActionEvent evt) {
   	 
    	String szoveg;
    	String datumos;
    	
    	
    	
    	
    	if (jComboBox1.getSelectedIndex() != 0) {
    		
    		jComboBox1.setEnabled(false);
    		
    		for (int i = 0; i < hasznalt.size(); ++i) {
        		
        		hasznalt.get(i).setText(honapSzama + "." + (i + 1));
        		hasznalt.get(i).setEnabled(true);
        		hasznalt.get(i).setBackground(alapszin);
        	}
    		
    		
    		
    		//ha nem az alap szar van kiválasztva
    		
    		//System.out.println(jComboBox1.getSelectedItem());
    		
    		User us = (User)jComboBox1.getSelectedItem();
    		
    		for (int i = 0; i < hasznalt.size(); ++i) {
    			
    			szoveg = us.getList().get(i).getLeiras();
    			//System.out.println(szoveg);
    			
    			if (szoveg.contains("HD Hiba EXP")) {
    				szoveg = szoveg.substring(0, szoveg.length() - 11);
    				
    			} 
    			
    			if (szoveg.contains("HD Hibakezeles")) {
    				szoveg = szoveg.substring(0, szoveg.length() - 14);
    			}
    			
    			if (us.getList().get(i).getUserke() == dolgozik.spec) {
    				
    				//szoveg = "nem cserélhető";
    				szoveg = szoveg.substring(12, szoveg.length());
    				hasznalt.get(i).setEnabled(false);
    			}
    			
    			if (us.getList().get(i).getUserke() == dolgozik.dolgE) {
    				
    				szoveg = "Éjszaka";
    				hasznalt.get(i).setEnabled(false);
    			}
    			
    			if (i > 1 && us.getList().get(i-1).getUserke() == dolgozik.dolgE) {
    				hasznalt.get(i).setEnabled(false);
    			}
    			
    			//System.out.println(szoveg);
    			
    			
    			szoveg = hasznalt.get(i).getText() + "\n" + szoveg;
    			
    			hasznalt.get(i).setText("<html>" + szoveg.replaceAll("\\n", "<br>") + "</html>");
    			
    			
    			
    			if (us.get(i).getUserke() == dolgozik.dolgVde) { //délelőtt dolgozik
    				delelottok.add(hasznalt.get(i));
    			}
    			
    			if (us.get(i).getUserke() == dolgozik.dolgVdu) {
    				delutanok.add(hasznalt.get(i));
    			}
    			
    			if (us.get(i).getUserke() == dolgozik.szabadV) {
    				
    				
    				
    				if (hetvege.contains(hasznalt.get(i)) && hasznalt.get(i).isEnabled()) {
    					
    					szabadHetvege.add(hasznalt.get(i));
    					
    					
    				} else if (hasznalt.get(i).isEnabled()){
    					szabadHetkoznap.add(hasznalt.get(i));
    					
    				}
    				
    				//paraméterek beállítása
    		    	
    		    	
    		    	hetvegekSzabadok = szabadHetvege.size();
    		    	hetkoznapokSzabadok = szabadHetkoznap.size();
    		    	delutanokSzabadok = delutanok.size();
    		    	delelottokSzabadok = delelottok.size();
    				
    				//hasznalt.get(i).setEnabled(false);	 // nem kell, mivel a szabadnapok is fixálódhatnak
    			}
    			
    		}
    		
    	}
    	
    	
    
    	
  
    		
    }

	protected void customerTipeActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
		   // kezelő tipusának kiválasztása
    	if (custTipe.getSelectedIndex() != 0) {
    		
    		custTipe.setEnabled(false);
    		
    		if (custTipe.getSelectedIndex() == 1) { //ha expert
    			
    			listaBetolt(expertCsiri);
    			
    		} else {
    			
    			
    			listaBetolt(t1t2Csiri);
    			
    		}
    		
    		
    		jComboBox1.removeAllItems();
    		
    		for (int i = 0; i < users.size(); ++i) {
    			jComboBox1.addItem(users.get(i));
    			
    		}
    		
    		
    		
    		/*névlista láthatóvá tevése*/
    		
    		jComboBox1.setVisible(true);
    		
    		
    		
    	} else {
    		
    		/*névlista láthatatlanná tevése*/
    		
    		jComboBox1.setVisible(false);
    		
    
    		
    	}
		
	}
	
	
    //betölti az expertek adatbázisát
    private void listaBetolt(String fajl) {
		// TODO Auto-generated method stub
    	
    	try {
    		
    		
    		/*ha csak egy felhasználót kell betöltenie, akkor ez */
    		/*
    		
			users= FileOperator.getInfoFromFile(fajl, speckok);
			
			
			
			*/
    		
    		users = FileOperator.getInfoFromFile(fajl, speckok);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

    
	protected void szinezo(ActionEvent e) {
		
		
		if (visszaertek != 2)
		
		if (!cserelniLehet) {
				// TODO Auto-generated method stub
				
		
		    	//System.out.println(e.getActionCommand().split("<br>")[0].split(">")[1].split("\\.")[1]);
		    	
		    	int lenyomottGomb = Integer.parseInt(e.getActionCommand().split("<br>")[0].split(">")[1].split("\\.")[1]) - 1;
		    	System.out.println(lenyomottGomb);
		    	
		    	//hasznalt.get(lenyomottGomb).setBackground(jPanel1.getBackground());   //szabad hétvégét szeretne panel_3
		    	 // hasznalt.get(lenyomottGomb).setBackground(jPanel3.getBackground());  //szabad hétköznap  	   panel_4
		    	
		    	//hasznalt.get(lenyomottGomb).setBackground(jPanel4.getBackground());   //délután szeretne dolgozni panel_5
		    	
		    	//hasznalt.get(lenyomottGomb).setBackground(jPanel5.getBackground());  //délelőtt szeretne dolgozni  panel_6,
		    	
		    	//hasznalt.get(lenyomottGomb).setBackground(jPanel8.getBackground());    // maradjon            panel_7
		    	
		    	
		    	/*
		    	
		    	hétvégét kattintok, a következő színek váltakoznak:
		    	
		    	    szabad hétvége, du/de (ha délelőtt akkor a du szín, ha du akkor a de szín), maradjon, eredeti
		    	    
		    	hétköznapot kattintok    
		    		szabad hétköznap, du/de (ha délelőtt akkor a du szín, ha du akkor a de szín, maradjon, eredeti)
		    		
		    	
		    		*/
		    	
		    	//innen folytatom holnap
		    	
		    	
		    	if (szabadHetvege.contains(hasznalt.get(lenyomottGomb)) || szabadHetkoznap.contains(hasznalt.get(lenyomottGomb)) ) {
		    		
		    		if (hasznalt.get(lenyomottGomb).getBackground().equals(alapszin)) {
		    			hasznalt.get(lenyomottGomb).setBackground(panel_7.getBackground());
		    			
		    			fixNapok -= 1;
		    			if (szabadHetvege.contains(hasznalt.get(lenyomottGomb))) {
		    				hetvegekSzabadok -= 1;
		    			} else {
		    				hetkoznapokSzabadok -= 1;
		    			}
		    			
		    			
		    		} else {
		    			hasznalt.get(lenyomottGomb).setBackground(alapszin);
		    			
		    			fixNapok += 1;
		    			
		    			if (szabadHetvege.contains(hasznalt.get(lenyomottGomb))) {
		    				hetvegekSzabadok += 1;
		    			} else {
		    				hetkoznapokSzabadok += 1;
		    			}
		    		}
		    		
		    		
		    	} else 
		    	
		    	//ha hétvége
		    	if (hetvege.contains(hasznalt.get(lenyomottGomb))) {
			    	if (hasznalt.get(lenyomottGomb).getBackground().equals(alapszin)) {
			    		
			    		hasznalt.get(lenyomottGomb).setBackground(panel_3.getBackground());
			    		hetvegekSzabadok -= 1;
			    		
			    	} else if (hasznalt.get(lenyomottGomb).getBackground().equals(panel_3.getBackground())) {
			    		
			    		if (delelottok.contains(hasznalt.get(lenyomottGomb))) { //ha nem szabadnap, hanem du szeretne dolgozni
			    			hasznalt.get(lenyomottGomb).setBackground(panel_5.getBackground());	    		
			    			hetvegekSzabadok += 1;
			    			delutanokSzabadok -= 1;
			    		}
			    		
			    	
			    		if (delutanok.contains(hasznalt.get(lenyomottGomb))) {
			    			
			    		hasznalt.get(lenyomottGomb).setBackground(panel_6.getBackground());
			    		hetvegekSzabadok += 1;
			    		delelottokSzabadok -= 1;
			    		}
			    	} else if (hasznalt.get(lenyomottGomb).getBackground().equals(panel_5.getBackground()) ||
			    			hasznalt.get(lenyomottGomb).getBackground().equals(panel_6.getBackground())) {
			    		     
			    		   if (hasznalt.get(lenyomottGomb).getBackground().equals(panel_5.getBackground())) {
			    			   delelottokSzabadok += 1;
			    		   } else delutanokSzabadok +=1;
			    			 
			    		hasznalt.get(lenyomottGomb).setBackground(panel_7.getBackground());
			    		
			    		
			    		
			    		fixNapok -= 1;
			    		if (delutanok.contains(hasznalt.get(lenyomottGomb)))  {
			    			delutanokSzabadok -= 1;
			    			
			    			//délutáni na
			    		} else delelottokSzabadok -= 1;
			    		
			    		System.out.println("csökkentve");
			    
			    		
			    	} else {
			    		
			    	
			    		
			    		hasznalt.get(lenyomottGomb).setBackground(alapszin);
			    		fixNapok += 1;
			    		if (delutanok.contains(hasznalt.get(lenyomottGomb)))  {
			    			delutanokSzabadok += 1;
			    			
			    			//délutáni na
			    		} else delelottokSzabadok += 1;
			    		
			    		
			    		System.out.println("visszanovelve");
			    		
			    		
			    	}
			    	
			    	
		    	} else {
		    		
		    		
		    		if (hasznalt.get(lenyomottGomb).getBackground().equals(alapszin)) {
			    		hetkoznapokSzabadok -= 1;
			    		hasznalt.get(lenyomottGomb).setBackground(panel_4.getBackground());
			    		
			    	} else if (hasznalt.get(lenyomottGomb).getBackground().equals(panel_4.getBackground())) {
			    		
			    		if (delelottok.contains(hasznalt.get(lenyomottGomb))) { //ha nem szabadnap, hanem du szeretne dolgozni
			    			hasznalt.get(lenyomottGomb).setBackground(panel_5.getBackground());	    		
			    			
			    			hetkoznapokSzabadok += 1;
			    			delutanokSzabadok -= 1;
			    		
			    		}
			    		
			    	
			    		if (delutanok.contains(hasznalt.get(lenyomottGomb))) {
			    			
			    		hasznalt.get(lenyomottGomb).setBackground(panel_6.getBackground());
			    		hetkoznapokSzabadok += 1;
			    		delelottokSzabadok -= 1;
			    		}
			    	} else if (hasznalt.get(lenyomottGomb).getBackground().equals(panel_5.getBackground()) ||
			    			hasznalt.get(lenyomottGomb).getBackground().equals(panel_6.getBackground())) {
			    		
			    		if (hasznalt.get(lenyomottGomb).getBackground().equals(panel_5.getBackground())) {
			    			delutanokSzabadok += 1;
			    		} else delelottokSzabadok += 1;
			    		
			    		fixNapok -= 1;
			    		if (delutanok.contains(hasznalt.get(lenyomottGomb)))  {
			    			delutanokSzabadok -= 1;
			    			
			    			//délutáni na
			    		} else delelottokSzabadok -= 1;
			    		
			    		hasznalt.get(lenyomottGomb).setBackground(panel_7.getBackground());
			    	} else {
			    		fixNapok += 1;
			    		hasznalt.get(lenyomottGomb).setBackground(alapszin);
			    		
			    		if (delutanok.contains(hasznalt.get(lenyomottGomb)))  {
			    			delutanokSzabadok += 1;
			    			
			    			//délutáni na
			    		} else delelottokSzabadok += 1;
			    	}
		    		
		    	}
    	
    	
		} else {
		
			int lenyomottGomb = Integer.parseInt(e.getActionCommand().split("<br>")[0].split(">")[1].split("\\.")[1]) - 1;
	    	System.out.println(lenyomottGomb);
	    	
	    	if (szabadHetvege.contains(hasznalt.get(lenyomottGomb)) && hasznalt.get(lenyomottGomb).getBackground().equals(alapszin)) {
	    	
	    		System.out.println("szabad hetvege");
	    		
	    		hasznalt.get(lenyomottGomb).setBackground(panel_16.getBackground());
	    	} else if (szabadHetvege.contains(hasznalt.get(lenyomottGomb))) {
	    		hasznalt.get(lenyomottGomb).setBackground(alapszin);
	    	}
	    	
	    	
	    	if (szabadHetkoznap.contains(hasznalt.get(lenyomottGomb)) && hasznalt.get(lenyomottGomb).getBackground().equals(alapszin)) {
		    	
	    		System.out.println("szabad hetvege");
	    		
	    		hasznalt.get(lenyomottGomb).setBackground(panel_18.getBackground());
	    	} else if (szabadHetkoznap.contains(hasznalt.get(lenyomottGomb))) {
	    		hasznalt.get(lenyomottGomb).setBackground(alapszin);
	    	}
	    	
	    	
	    	
	    	if (delutanok.contains(hasznalt.get(lenyomottGomb)) && hasznalt.get(lenyomottGomb).getBackground().equals(alapszin)) {
		    	
	    		System.out.println("szabad hetvege");
	    		
	    		hasznalt.get(lenyomottGomb).setBackground(panel_20.getBackground());
	    	} else if (delutanok.contains(hasznalt.get(lenyomottGomb))) {
	    		hasznalt.get(lenyomottGomb).setBackground(alapszin);
	    	}
	    	
	    	
	    	
	    	if (delelottok.contains(hasznalt.get(lenyomottGomb)) && hasznalt.get(lenyomottGomb).getBackground().equals(alapszin)) {
		    	
	    		System.out.println("szabad hetvege");
	    		
	    		hasznalt.get(lenyomottGomb).setBackground(panel_22.getBackground());
	    	} else if (delelottok.contains(hasznalt.get(lenyomottGomb))) {
	    		hasznalt.get(lenyomottGomb).setBackground(alapszin);
	    	}
	    	
	    	
		
		}
		
		
		
		/*System.out.println("Statisztika:  hétvégék: " + hetvegekSzabadok + " hetkoznapok" + hetkoznapokSzabadok + "" +
		 " du " + delutanokSzabadok + " de: " + delelottokSzabadok + " fix: " + fixNapok );*/
    	
	} 
		
		


	public void setTitle(String string) {
		// TODO Auto-generated method stub
		frame.setTitle(string);
		
	}

	public void setDefaultCloseOperation(int disposeOnClose) {
		// TODO Auto-generated method stub
		
		frame.setDefaultCloseOperation(disposeOnClose);
	}

	public void setSize(int i, int j) {
		// TODO Auto-generated method stub
		frame.setSize(i, j);
		
	}

	public void setLocationRelativeTo(Object object) {
		// TODO Auto-generated method stub
		frame.setLocationRelativeTo((Component) object);
		
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);
		
	}
	
	
	

/**
 * Az alábbi adatokat kell betöltenie:
 * 
 * hónap: hányadik hónap
 * kezdo: a hét hányadik napjával kezdődik a hónap
 * napok: ennyi nap van van a hónapban
 * expert mappa:  itt találhatóak a txt fájlok
 * expert beo: itt található az expertek beosztása
 * t1t2 mappa: itt találhatóak a txt fájlok
 * t1t2beo: itt található az t1t2 beosztása
 * fix: itt van megadva az a szám, amennyi napot "fix-nek" lehet jelölni
 * 
 * 
 */


public boolean configBetoltes() {
	{
		// TODO Auto-generated method stub
		
		File config = new File ("series.conf");
		
		try {
		
			BufferedReader br = new BufferedReader(new FileReader("series.conf"));
			
			String honap = br.readLine();
			
			String napok = br.readLine();
			
			String kezdo = br.readLine();
			
			String expertM = br.readLine();
			
			String expertCs = br.readLine();
			
			String t1t2M = br.readLine();
			
			String t1t2Cs = br.readLine();
			
			String fix = br.readLine();
			
			String spec = br.readLine();
			
			String HVHT = br.readLine();
			String HTHV = br.readLine();
			
			ArrayList<Integer> hetvegeHetkoznap = new ArrayList<Integer>();
			ArrayList<Integer> hetkoznapHetvege = new ArrayList<Integer>();
			
			String valami = HVHT.split("=")[1];
			String[] valamiString = valami.split(",");
			
			
			for (int i = 0; i <  valamiString.length; i++ ) {
				hetvegeHetkoznap.add(Integer.parseInt(valamiString[i]));
				
			}
			
			 valami = HTHV.split("=")[1];
			valamiString = valami.split(",");
			
			for (int i = 0; i <  valamiString.length; i++ ) {
				hetkoznapHetvege.add(Integer.parseInt(valamiString[i]));
				
			}
		
			
			
			//globális fájlok, mappák, változók beállítása
			 expertMapa = expertM.split("=")[1];
			 expertCsiri = expertCs.split("=")[1];
			 t1t2Mapa = t1t2M.split("=")[1];
			 t1t2Csiri = t1t2Cs.split("=")[1];
			 fixNapok = Integer.parseInt(fix.split("=")[1]);
			 fixNapok2 = fixNapok;
			 speckok = spec.split("=")[1].split(",");
			
			System.out.println(speckok[2]);
			
			/*a kezdő nap lekérése*/
			
			String[] array = kezdo.split("=");
			int kezdoNap = Integer.parseInt(array[1]) - 1;
			
			
			/*napok számának a lekérése*/
			
			array = napok.split("=");
			int napokSzama = Integer.parseInt(array[1]);
			
			
			/*honap beállítása*/
			array = honap.split("=");
			honapSzama = Integer.parseInt(array[1]);
			
			System.out.println(kezdoNap + " -tól + " +napokSzama);
			
			int datum = 1;
			

			
			for (int i = kezdoNap; i <= napokSzama + kezdoNap -1; ++i)  {
			
				//gombok.get(i).setEnabled(true);
				
				//gombok hozzáadása a megfelelő kategóriába
				
				
				//String twoLines = honapSzama + "." + (datum++) + "\nszabadság";
				//System.out.println(i + "");
				
			
				//gombok.get(i).setText("<html>" + twoLines.Allreplace("\\n", "<br>") + "</html>");
				
				gombok.get(i).setText(honapSzama + "." + (datum++));
				
				hasznalt.add(gombok.get(i));
			
				if (i == 5 || i == 6 ||
						i == 12 || i == 13 ||
						i == 19 || i  == 20 ||
						i == 26 || i == 27 ||
						i == 33 || i == 34)  {
					
					hetvege.add(gombok.get(i));
				
				} else {
					
					hetkoznap.add(gombok.get(i));
					
				}
		
			}
		
			
			if (hetvegeHetkoznap.get(0) != 0) {
				System.out.println("valami " + hetvegeHetkoznap.size()); 
				for (int i = 0; i < hetvegeHetkoznap.size(); ++i) {
					hetvege.remove(gombok.get(hetvegeHetkoznap.get(i) + kezdoNap - 1));
					hetkoznap.add(gombok.get(hetvegeHetkoznap.get(i) + kezdoNap - 1));
				}
				
			}
			
			
			if (hetkoznapHetvege.get(0) != 0) {
				
				for (int i = 0; i < hetkoznapHetvege.size(); ++i) {
					hetkoznap.remove(gombok.get(hetkoznapHetvege.get(i) + kezdoNap - 1));
					hetvege.add(gombok.get(hetkoznapHetvege.get(i) + kezdoNap - 1));
				}
				
			}
			
			
			//String twoLines = "11.21\nszabadság";
			//jButton1.setText("<html>" + twoLines.replaceAll("\\n", "<br>") + "</html>");
			
			

			
			
			/*
			 * itt kerülnek feltöltésre a globális változok értékekkel, amelyek kellenek a gombok generálásához.
			 * 
			 * 
			 */
			
		
			
			/** itt történnek meg a beolvasások, a hétvége és a hétköznap gombok feltöltése*/
			
			br.close();
			
			return true;
		
		} catch (Exception ex) {
			
			return false;
		}
		
	}
	
	
	
	
}
}
