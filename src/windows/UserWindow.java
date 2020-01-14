/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package windows;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;

import objects.FileOperator;
import objects.User;
import objects.dolgozik;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

/**
 *
 * @author dundyvega
 */
public class UserWindow extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Creates new form UserWindow
     */
    public UserWindow() {
        initComponents();
        
        /*feltöltjük a gombokat*/
        
        gombokFeltoltve();
        
        /*betöltjük a konfig fájlt*/
        configBetoltes();
        
        
    }
    
    /*az összes gombot hozzáadjuk a listához*/
    private void gombokFeltoltve() {
		// TODO Auto-generated method stub
    	
    	//gomb eredeti színének letárolása
    	
    	alapszin = jButton1.getBackground();
    	
    	
    	gombok.add(jButton1);
    	gombok.add(jButton2);
    	gombok.add(jButton3);
    	gombok.add(jButton4);
    	gombok.add(jButton5);
    	gombok.add(jButton6);
    	gombok.add(jButton7);
    	gombok.add(jButton8);
    	gombok.add(jButton9);
    	gombok.add(jButton10);
    	gombok.add(jButton11);
    	gombok.add(jButton12);
    	gombok.add(jButton13);
    	gombok.add(jButton14);
    	gombok.add(jButton15);
    	gombok.add(jButton16);
    	gombok.add(jButton17);
    	gombok.add(jButton18);
    	gombok.add(jButton19);
    	gombok.add(jButton20);
    	gombok.add(jButton21);
    	gombok.add(jButton22);
    	gombok.add(jButton23);
    	gombok.add(jButton24);
    	gombok.add(jButton25);
    	gombok.add(jButton26);
    	gombok.add(jButton27);
    	gombok.add(jButton28);
    	gombok.add(jButton29);
    	gombok.add(jButton30);
    	gombok.add(jButton31);
    	gombok.add(jButton32);
    	gombok.add(jButton33);
    	gombok.add(jButton34);
    	gombok.add(jButton35);
    	gombok.add(jButton37);
    	gombok.add(jButton38);
    	
    	for (int i = 0; i < gombok.size(); ++i) {
    		
    		gombok.get(i).setEnabled(false);
    		
    		/*
    		 * figyelő hozzárendelése a gombokhoz
    		 */
    		
    		gombok.get(i).addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					szinezo(e);
				}
    			
    		});
    	}
    	
    	jButton36.setEnabled(false);
    	
    	
    	
		
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
				
				
				//globális fájlok, mappák, változók beállítása
				 expertMapa = expertM.split("=")[1];
				 expertCsiri = expertCs.split("=")[1];
				 t1t2Mapa = t1t2M.split("=")[1];
				 t1t2Csiri = t1t2Cs.split("=")[1];
				 fixNapok = Integer.parseInt(fix.split("=")[1]);
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
				

				
				for (int i = kezdoNap; i <= napokSzama + kezdoNap - 1; ++i)  {
				
					//gombok.get(i).setEnabled(true);
					
					//gombok hozzáadása a megfelelő kategóriába
					
					
					//String twoLines = honapSzama + "." + (datum++) + "\nszabadság";
					//System.out.println(i + "");
					
				
					//gombok.get(i).setText("<html>" + twoLines.Allreplace("\\n", "<br>") + "</html>");
					
					gombok.get(i).setText(honapSzama + "." + (datum++));
					
					hasznalt.add(gombok.get(i));
					
					if (i == 5 || i == 6 ||
							i == 12 || i == 13 ||
							i == 19 || i == 20 ||
							i == 26 || i == 27 ||
							i == 33 || i == 34)  {
						
						hetvege.add(gombok.get(i));
					
					} else {
						
						hetkoznap.add(gombok.get(i));
						
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

    
    
    
    
    
    

	/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
	    private void initComponents() {  
    	
    	this.setResizable(false);
    	
    	

        jComboBox1 = new javax.swing.JComboBox<User>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton36 = new javax.swing.JButton();
        jButton36_2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        customerTipe = new javax.swing.JComboBox<>();
        jButton37 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        //jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        jButton3.setText("jButton3");

        jButton4.setText("jButton4");

        jButton5.setText("jButton5");

        jButton6.setText("jButton6");

        jButton7.setText("jButton7");

        jButton8.setText("jButton8");

        jButton9.setText("jButton9");

        jButton10.setText("jButton9");

        jButton11.setText("jButton9");

        jButton12.setText("jButton9");

        jButton13.setText("jButton13");

        jButton14.setText("jButton14");

        jButton15.setText("jButton15");

        jButton16.setText("jButton16");

        jButton17.setText("jButton17");

        jButton18.setText("jButton18");

        jButton19.setText("jButton19");

        jButton20.setText("jButton20");

        jButton21.setText("jButton21");

        jButton22.setText("jButton22");

        jButton23.setText("jButton23");

        jButton24.setText("jButton24");

        jButton25.setText("jButton25");

        jButton26.setText("jButton26");

        jButton27.setText("jButton27");

        jButton28.setText("jButton28");

        jButton29.setText("jButton29");

        jButton30.setText("jButton30");

        jButton31.setText("jButton31");

        jButton32.setText("jButton32");

        jButton33.setText("jButton33");

        jButton34.setText("jButton34");

        jButton35.setText("jButton35");

        jLabel1.setText("Szabad hétvége");

        jLabel2.setText("szabad hétköznap");

        jLabel3.setText("délután szeretnék");

        jLabel4.setText("délelőtt szeretnék");

        jLabel5.setText("maradjon");

        jPanel1.setBackground(new java.awt.Color(102, 255, 102));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(51, 204, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 51));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 27, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 27, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 153, 102));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );

        jButton36.setText("Csere ajánló");
        jButton36_2.setText("Előző");

        jLabel6.setText("hétfő");

        jLabel7.setText("kedd");

        jLabel8.setText("szerda");

        jLabel9.setText("csütörtök");

        jLabel10.setText("péntek");

        jLabel11.setText("szombat");

        jLabel12.setText("vasárnap");

        customerTipe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<< választás >>", "Expert", "T1/T2"}));
        jComboBox1.setVisible(false);
        
        
        jComboBox1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jComboBox1ActionPerformed(e);
			}
        	
        });
        
        customerTipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerTipeActionPerformed(evt);
            }
        });

        jButton37.setText("jButton37");

        jButton38.setText("jButton38");
        
        JButton btnKorbbi = new JButton("Korábbi");
        btnKorbbi.setVisible(false);
        
        JButton btnKvetkez = new JButton("Következő");
        btnKvetkez.setVisible(false);
        
        JLabel lblKrlekVlasszKi = new JLabel("Kérlek válassz ki x darab szabadnapot");
        lblKrlekVlasszKi.setVisible(false);
        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(0, 147, Short.MAX_VALUE)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(jButton29, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
        						.addComponent(jButton22, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(jButton15, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(jButton8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(jButton37, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(btnKorbbi, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
        							.addGap(18)
        							.addComponent(btnKvetkez, 0, 0, Short.MAX_VALUE))
        						.addComponent(jButton38, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(jButton30, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
        						.addComponent(jButton23, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(jButton16, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(jButton9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        									.addComponent(jButton31, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        									.addComponent(jButton24, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        									.addComponent(jButton17, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        									.addComponent(jButton10, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        									.addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
        								.addGroup(layout.createSequentialGroup()
        									.addGap(28)
        									.addComponent(jLabel8)))
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addGroup(layout.createSequentialGroup()
        									.addPreferredGap(ComponentPlacement.UNRELATED)
        									.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        										.addComponent(jButton32, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        										.addComponent(jButton25, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        										.addComponent(jButton18, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        										.addComponent(jButton11, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        										.addComponent(jButton4, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)))
        								.addGroup(layout.createSequentialGroup()
        									.addGap(37)
        									.addComponent(jLabel9)))
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addGroup(layout.createSequentialGroup()
        									.addPreferredGap(ComponentPlacement.UNRELATED)
        									.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        										.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        											.addComponent(jButton33, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
        											.addComponent(jButton26, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        											.addComponent(jButton19, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        											.addComponent(jButton12, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        										.addComponent(jButton5, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)))
        								.addGroup(layout.createSequentialGroup()
        									.addGap(48)
        									.addComponent(jLabel10)))
        							.addGap(18)
        							.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
        								.addGroup(layout.createSequentialGroup()
        									.addComponent(jButton34, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
        									.addPreferredGap(ComponentPlacement.UNRELATED)
        									.addComponent(jButton35, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
        								.addGroup(layout.createSequentialGroup()
        									.addGroup(layout.createParallelGroup(Alignment.LEADING)
        										.addComponent(jButton27, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        										.addComponent(jButton20, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        										.addComponent(jButton13, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        									.addPreferredGap(ComponentPlacement.UNRELATED)
        									.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        										.addComponent(jButton14, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
        										.addComponent(jButton21, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        										.addComponent(jButton28, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        								.addGroup(layout.createSequentialGroup()
        									.addGap(17)
        									.addComponent(jLabel11)
        									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        									.addComponent(jLabel12)
        									.addGap(48))))
        						.addComponent(jButton36, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)))
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        						.addGroup(layout.createSequentialGroup()
        							.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        								.addComponent(jLabel5)
        								.addComponent(jLabel4))
        							.addPreferredGap(ComponentPlacement.RELATED, 939, Short.MAX_VALUE))
        						.addGroup(layout.createSequentialGroup()
        							.addGap(107)
        							.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        								.addGroup(layout.createSequentialGroup()
        									.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
        									.addPreferredGap(ComponentPlacement.RELATED))
        								.addGroup(layout.createSequentialGroup()
        									.addComponent(jLabel6)
        									.addGap(86)))
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addGroup(layout.createSequentialGroup()
        									.addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
        									.addGap(480)
        									.addComponent(jButton6, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
        								.addGroup(layout.createSequentialGroup()
        									.addGap(36)
        									.addComponent(jLabel7))
        								.addComponent(lblKrlekVlasszKi)
        								.addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 542, GroupLayout.PREFERRED_SIZE)
        								.addComponent(customerTipe, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
        							.addGap(12)))
        					.addComponent(jButton7, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
        						.addComponent(jLabel3, Alignment.LEADING)
        						.addComponent(jLabel2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(jLabel1))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addGroup(layout.createSequentialGroup()
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        							.addGap(203)
        							.addComponent(jPanel6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addGap(112)
        							.addComponent(jPanel7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        					.addGap(0, 454, Short.MAX_VALUE)))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(27)
        			.addComponent(customerTipe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(31)
        			.addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(33)
        			.addComponent(lblKrlekVlasszKi)
        			.addGap(36)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel7)
        				.addComponent(jLabel8)
        				.addComponent(jLabel9)
        				.addComponent(jLabel10)
        				.addComponent(jLabel11)
        				.addComponent(jLabel12)
        				.addComponent(jLabel6))
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
        				.addComponent(jButton3, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        				.addComponent(jButton7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(jButton6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(jButton5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(jButton4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(jButton2, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
        					.addComponent(jButton1, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
        				.addComponent(jButton14, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
        				.addComponent(jButton13, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(jButton12, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(jButton11, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(jButton10, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(jButton9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(jButton8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(jButton17, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        				.addComponent(jButton18, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(jButton21, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(jButton20, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(jButton19, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(jButton16, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(jButton15, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(jButton23, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        				.addComponent(jButton24, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(jButton25, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(jButton26, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(jButton28, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(jButton27, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(jButton22, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(jButton33, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
        					.addComponent(jButton35, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
        					.addComponent(jButton34, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
        				.addComponent(jButton32, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jButton31, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jButton30, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jButton29, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jButton38, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        				.addComponent(jButton37, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
        			.addGap(28)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnKvetkez)
        				.addComponent(btnKorbbi))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jPanel7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(jButton36)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(jPanel6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        					.addGap(50))
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jPanel1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jLabel1, Alignment.TRAILING))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jPanel3, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jLabel2, Alignment.TRAILING))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(jLabel3)
        							.addGap(18)
        							.addComponent(jLabel4)
        							.addGap(18)
        							.addComponent(jLabel5)
        							.addGap(11))
        						.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>       // </editor-fold>    
    
    
    
    private void jComboBox1ActionPerformed(ActionEvent evt) {
    	 
    	String szoveg;
    	String datumos;
    	
    	
    	
    	
    	if (jComboBox1.getSelectedIndex() != 0) {
    		
    		
    		for (int i = 0; i < hasznalt.size(); ++i) {
        		
        		hasznalt.get(i).setText(honapSzama + "." + (i + 1));
        		hasznalt.get(i).setEnabled(true);
        		hasznalt.get(i).setBackground(alapszin);
        	}
    		
    		
    		jButton36.setEnabled(true);
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
    				
    				
    				
    				if (hetvege.contains(hasznalt.get(i))) {
    					
    					szabadHetvege.add(hasznalt.get(i));
    					
    					
    				} else {
    					szabadHetkoznap.add(hasznalt.get(i));
    					
    				}
    				
    				//hasznalt.get(i).setEnabled(false);	 // nem kell, mivel a szabadnapok is fixálódhatnak
    			}
    			
    		}
    		
    	}
    	
  
    		
    }
    
    private void customerTipeActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // kezelő tipusának kiválasztása
    	if (customerTipe.getSelectedIndex() != 0) {
    		
    		customerTipe.setEnabled(false);
    		
    		if (customerTipe.getSelectedIndex() == 1) { //ha expert
    			
    			listaBetolt(expertCsiri);
    			
    		} else {
    			
    			
    			listaBetolt(t1t2Csiri);
    			
    		}
    		
    		
    		jComboBox1.removeAllItems();
    		
    		for (int i = 0; i < users.size(); ++i) {
    			jComboBox1.addItem(users.get(i));
    			
    		}
    		
    		for (int i = 0; i < hasznalt.size(); ++i)
    			hasznalt.get(i).setEnabled(true);
    		
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
			users= FileOperator.getInfoFromFile(fajl, speckok);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    
    
    
    public void szinezo (ActionEvent e) {
    	
    	//System.out.println(e.getActionCommand().split("<br>")[0].split(">")[1].split("\\.")[1]);
    	
    	int lenyomottGomb = Integer.parseInt(e.getActionCommand().split("<br>")[0].split(">")[1].split("\\.")[1]) - 1;
    	System.out.println(lenyomottGomb);
    	
    	//hasznalt.get(lenyomottGomb).setBackground(jPanel1.getBackground());   //szabad hétvégét szeretne
    	 // hasznalt.get(lenyomottGomb).setBackground(jPanel3.getBackground());  //szabad hétköznap  	
    	
    	//hasznalt.get(lenyomottGomb).setBackground(jPanel4.getBackground());   //délután szeretne dolgozni
    	
    	//hasznalt.get(lenyomottGomb).setBackground(jPanel5.getBackground());  //délelőtt szeretne dolgozni
    	
    	//hasznalt.get(lenyomottGomb).setBackground(jPanel8.getBackground());    // maradjon
    	
    	
    	/*
    	
    	hétvégét kattintok, a következő színek váltakoznak:
    	
    	    szabad hétvége, du/de (ha délelőtt akkor a du szín, ha du akkor a de szín), maradjon, eredeti
    	    
    	hétköznapot kattintok    
    		szabad hétköznap, du/de (ha délelőtt akkor a du szín, ha du akkor a de szín, maradjon, eredeti)
    		
    	
    		*/
    	
    	//innen folytatom holnap
    	
    	
    	if (szabadHetvege.contains(hasznalt.get(lenyomottGomb)) || szabadHetkoznap.contains(hasznalt.get(lenyomottGomb)) ) {
    		
    		if (hasznalt.get(lenyomottGomb).getBackground().equals(alapszin)) {
    			hasznalt.get(lenyomottGomb).setBackground(jPanel8.getBackground());
    		} else {
    			hasznalt.get(lenyomottGomb).setBackground(alapszin);
    		}
    		
    		
    	} else 
    	
    	//ha hétvége
    	if (hetvege.contains(hasznalt.get(lenyomottGomb))) {
	    	if (hasznalt.get(lenyomottGomb).getBackground().equals(alapszin)) {
	    		
	    		hasznalt.get(lenyomottGomb).setBackground(jPanel1.getBackground());
	    		
	    	} else if (hasznalt.get(lenyomottGomb).getBackground().equals(jPanel1.getBackground())) {
	    		
	    		if (delelottok.contains(hasznalt.get(lenyomottGomb))) { //ha nem szabadnap, hanem du szeretne dolgozni
	    			hasznalt.get(lenyomottGomb).setBackground(jPanel4.getBackground());	    		}
	    		
	    	
	    		if (delutanok.contains(hasznalt.get(lenyomottGomb))) {
	    			
	    		hasznalt.get(lenyomottGomb).setBackground(jPanel5.getBackground());
	    		}
	    	} else if (hasznalt.get(lenyomottGomb).getBackground().equals(jPanel4.getBackground()) ||
	    			hasznalt.get(lenyomottGomb).getBackground().equals(jPanel5.getBackground())) {
	    		hasznalt.get(lenyomottGomb).setBackground(jPanel8.getBackground());
	    	} else {
	    		hasznalt.get(lenyomottGomb).setBackground(alapszin);
	    	}
	    	
	    	
    	} else {
    		
    		
    		if (hasznalt.get(lenyomottGomb).getBackground().equals(alapszin)) {
	    		
	    		hasznalt.get(lenyomottGomb).setBackground(jPanel3.getBackground());
	    		
	    	} else if (hasznalt.get(lenyomottGomb).getBackground().equals(jPanel3.getBackground())) {
	    		
	    		if (delelottok.contains(hasznalt.get(lenyomottGomb))) { //ha nem szabadnap, hanem du szeretne dolgozni
	    			hasznalt.get(lenyomottGomb).setBackground(jPanel4.getBackground());	    		}
	    		
	    	
	    		if (delutanok.contains(hasznalt.get(lenyomottGomb))) {
	    			
	    		hasznalt.get(lenyomottGomb).setBackground(jPanel5.getBackground());
	    		}
	    	} else if (hasznalt.get(lenyomottGomb).getBackground().equals(jPanel4.getBackground()) ||
	    			hasznalt.get(lenyomottGomb).getBackground().equals(jPanel5.getBackground())) {
	    		hasznalt.get(lenyomottGomb).setBackground(jPanel8.getBackground());
	    	} else {
	    		hasznalt.get(lenyomottGomb).setBackground(alapszin);
	    	}
    		
    	}
    	
    	
    }

	/**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton36_2;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<User> jComboBox1;
    private javax.swing.JComboBox<String>customerTipe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    
    
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
    private ArrayList<JButton> delelottok = new ArrayList<JButton>();          /*X*/
    
   //ebben a hónapban vagyunk
    private int honapSzama;
    
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
	//ezekkel nem lehet cserélni
	private String[] speckok;
	
	//felhasználók
	private ArrayList<User> users;
	
	//alapszín
	private Color alapszin;
}