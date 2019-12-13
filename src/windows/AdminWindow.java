package windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class AdminWindow extends JFrame {

	private JPanel contentPane;

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
		mnFjlSzerkeszts.add(mntmExcelFelbonts);
		
		JMenuItem mntmConfigBellts = new JMenuItem("Config Beállítás");
		mnFjlSzerkeszts.add(mntmConfigBellts);
		
		JMenuItem mntmresFjlokGenerlsa = new JMenuItem("Üres fájlok Generálása");
		mnFjlSzerkeszts.add(mntmresFjlokGenerlsa);
		
		JMenuItem mntmFjlokBetltse = new JMenuItem("Adatok Excelbe");
		mnFjlSzerkeszts.add(mntmFjlokBetltse);
		
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
