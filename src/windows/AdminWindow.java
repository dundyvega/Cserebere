package windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import objects.FileOperator;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class AdminWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuItem mntmMappaLtrehozsa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminWindow frame = new AdminWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JMenuItem mntmTtIgnyek = new JMenuItem("T1/T2 Igények");
		mnIgnyek.add(mntmTtIgnyek);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
