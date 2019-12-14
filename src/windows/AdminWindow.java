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
import java.awt.event.ActionEvent;

public class AdminWindow extends JFrame {

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
		
		JMenuItem mntmExcelFelbonts = new JMenuItem("Excel Felbontás");
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
		mnFjlSzerkeszts.add(mntmExcelFelbonts);
		
		JMenuItem mntmConfigBellts = new JMenuItem("Config Beállítás");
		mnFjlSzerkeszts.add(mntmConfigBellts);
		
		JMenuItem mntmresFjlokGenerlsa = new JMenuItem("Üres fájlok Generálása");
		mntmresFjlokGenerlsa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FileOperator.generateEmptyTxt();
				
			}
		});
		mnFjlSzerkeszts.add(mntmresFjlokGenerlsa);
		
		JMenuItem mntmFjlokBetltse = new JMenuItem("Adatok Excelbe");
		mnFjlSzerkeszts.add(mntmFjlokBetltse);
		
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
