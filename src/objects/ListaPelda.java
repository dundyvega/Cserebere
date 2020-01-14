package objects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ListaPelda <T> extends JPanel {
	
	private ArrayList<ChangedListener> listeners;
	private ArrayList<T> lista;
	private int listaIndex;
	private int osszehasonlitoIndex = 0;
	
	private JPanel contentPane;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JButton button;
	private JButton button_1;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	
	public void setLista(ArrayList<T> t) {
		listaIndex = 0;
		lista = t;
		megjelenites();
		
		mozgasd(0);
	}
	




	/**
	 * Create the frame.
	 */
	

	public ListaPelda(ArrayList<T> t, String elnevezes) {
		
		listeners = new ArrayList<ChangedListener>();
		lista = t;
		setListaIndex(0);
		

		contentPane = this;
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 20, 20));
		
		JLabel nevezesL = new JLabel(elnevezes);
		contentPane.add(nevezesL);
		
		
		panel1 = new JPanel();
		contentPane.add(panel1);
		panel1.setLayout(new BorderLayout(0, 0));
		
		label1 = new JLabel();
		panel1.add(label1);

		
		panel2 = new JPanel();
		contentPane.add(panel2);
		panel2.setLayout(new BorderLayout(0, 0));
		
		label2 = new JLabel();
		panel2.add(label2);
	
		
		panel3 = new JPanel();
		contentPane.add(panel3);
		panel3.setLayout(new BorderLayout(0, 0));
		
		label3 = new JLabel();
		panel3.add(label3);
		
		panel4 = new JPanel();
		contentPane.add(panel4);
		panel4.setLayout(new BorderLayout(0, 0));
		
		label4 = new JLabel();
		panel4.add(label4);
		
		button = new JButton("<<");
		contentPane.add(button);
		
		button.addActionListener(e->mozgasd(--listaIndex));
		
		button_1 = new JButton(">>");
		contentPane.add(button_1);
		button_1.addActionListener(e->mozgasd(++listaIndex));
		
		button.setEnabled(false);
		button_1.setEnabled(false);
		
		if (lista.size() > 0) {
			button_1.setEnabled(false);
		}
		
		megjelenites();
		listaIndex = 0;
		mozgasd(0);
	}

	public Object mozgasd(int i) {
		// TODO Auto-generated method stub
		
		//System.out.println("A listenerek száma: " + listeners.size());
		for (int j = 0; j < listeners.size(); ++j) {
			listeners.get(j).somethingHappend();
			//System.out.println("kiabál!");
		}
		
		
		if (i == lista.size() - 1) {
			
			
			button_1.setEnabled(false);
			
			fesdKiATobbit(getLastPanel());
			if (lista.size() > 0) {
				button.setEnabled(true);
			}
			
			
			
		} else if (i == 0) {
			
			button.setEnabled(false);
			
			fesdKiATobbit(panel1);
			
			if (lista.size() > 0) {
				
				button_1.setEnabled(true);
			}
				
			
		} else {
			
			button.setEnabled(true);
			button_1.setEnabled(true);
			
			
			if (osszehasonlitoIndex < listaIndex) { //jobra lépünk
				
				if (panel1.getBackground().equals(Color.green)) {
					fesdKiATobbit(panel2);
				} else {
					fesdKiATobbit(panel3);
				} 
				} else {
					
					if (panel4.getBackground().equals(Color.green)) {
						fesdKiATobbit(panel3);
					} else {
						fesdKiATobbit(panel2);
					}
					
				}
	
			
			
		}
		
		
		panelekreIrdListaAktErtekeit();
		osszehasonlitoIndex = listaIndex;
		
		return null;
	}

	private void panelekreIrdListaAktErtekeit() {
		// TODO Auto-generated method stub
		
		// ha 1 zöld, akkor 0,1,2,3,4
		//ha utolsó: akkor utolso, utolsó - 1, utolsó -2, utolsó - 3
		// ha csökkenő, és második: második = aktualis, elso = aktualis - 1, harmadik aktualis + 1, negyedik akt + 2
		// ha növekvő: harmadik = aktualis, negyedik = aktualis + 1, elso = aktualis - 2, masodik = aktualis -1
		
		if (lista.size() >= 4)
		
		if (panel1.getBackground().equals(Color.GREEN)) {
			
			label1.setText(lista.get(0).toString());
			
			if (panel2.getBackground().equals(Color.orange)) {
				
				label2.setText(lista.get(1).toString());
			}
			
			if (panel3.getBackground().equals(Color.orange)) {
				label3.setText(lista.get(2).toString());
			}
			
			if (panel4.getBackground().equals(Color.orange)) {
				label4.setText(lista.get(3).toString());
			}
			
			//+ többi
		} else if (getLastPanel().getBackground().equals(Color.GREEN) && panel4.equals(getLastPanel())) {
			
			label4.setText(lista.get(lista.size() - 1).toString());
			label3.setText(lista.get(lista.size() - 2).toString());
			label2.setText(lista.get(lista.size() - 3).toString());
			label1.setText(lista.get(lista.size() - 4).toString());
				
			
		} else if (panel2.getBackground().equals(Color.green)) {
			
			label2.setText(lista.get(listaIndex).toString());
			label1.setText(lista.get(listaIndex - 1).toString());
			
			if (lista.size() >= 2) {
				
				label3.setText(lista.get(listaIndex + 1).toString());
				
				if (lista.size() >= 3) {
					label4.setText(lista.get(listaIndex + 2).toString());
				}
			}
			
		} else if (panel3.getBackground().equals(Color.green)) {
			
			label3.setText(lista.get(listaIndex).toString());
			label2.setText(lista.get(listaIndex - 1).toString());
			label1.setText(lista.get(listaIndex - 2).toString());
			
			if (lista.size() >= 3) {
				label4.setText(lista.get(listaIndex + 1).toString());
			}
		}
		
		
	}




	public int getListaIndex() {
		return listaIndex;
	}
	
	private void fesdKiATobbit(JPanel panel) {
		
		if (panel.equals(panel1)) {
			
			panel1.setBackground(Color.green);
			panel1.setBorder(BorderFactory.createLineBorder(Color.black));

			panel2.setBackground(lista.size() > 1? Color.orange: Color.DARK_GRAY);
			panel2.setBorder(BorderFactory.createLineBorder(lista.size() > 2? Color.orange: Color.DARK_GRAY));
			
			panel3.setBackground(lista.size() > 1? Color.orange: Color.DARK_GRAY);
			panel3.setBorder(BorderFactory.createLineBorder(lista.size() > 2? Color.orange: Color.DARK_GRAY));
			
			panel4.setBackground(lista.size() > 1? Color.orange: Color.DARK_GRAY);
			panel4.setBorder(BorderFactory.createLineBorder(lista.size() > 2? Color.orange: Color.DARK_GRAY));
			
			
		} else if (panel.equals(panel2)) {
			
			panel1.setBackground(Color.orange);
			panel1.setBorder(BorderFactory.createLineBorder(Color.orange));
			
			panel2.setBackground(Color.green);
			panel2.setBorder(BorderFactory.createLineBorder(Color.black));
			
			panel3.setBackground(lista.size() > 2? Color.orange: Color.DARK_GRAY);
			panel3.setBorder(BorderFactory.createLineBorder(lista.size() > 3? Color.orange: Color.DARK_GRAY));
			
			panel4.setBackground(lista.size() > 2? Color.orange: Color.DARK_GRAY);
			panel4.setBorder(BorderFactory.createLineBorder(lista.size() > 3? Color.orange: Color.DARK_GRAY));
			
			
			
		} else if (panel.equals(panel3)) {
			
			panel1.setBackground(Color.orange);
			panel1.setBorder(BorderFactory.createLineBorder(Color.orange));
			
			panel2.setBackground(Color.orange);
			panel2.setBorder(BorderFactory.createLineBorder(Color.orange));
			
			panel3.setBackground(Color.green);
			panel3.setBorder(BorderFactory.createLineBorder(Color.black));
			
			panel4.setBackground(lista.size() > 3? Color.orange: Color.DARK_GRAY);
			panel4.setBorder(BorderFactory.createLineBorder(lista.size() > 3? Color.orange: Color.DARK_GRAY));
			
			
		} else {
			
			
			panel1.setBackground(Color.orange);
			panel1.setBorder(BorderFactory.createLineBorder(Color.orange));
			
			panel2.setBackground(Color.orange);
			panel2.setBorder(BorderFactory.createLineBorder(Color.orange));
			
			panel3.setBackground(Color.orange);
			panel3.setBorder(BorderFactory.createLineBorder(Color.orange));
			
			panel4.setBackground(Color.green);
			panel4.setBorder(BorderFactory.createLineBorder(Color.black));
		}
	}
	
	private JPanel getLastPanel() {
		
		if (lista.size() == 0) {
			return null;
		} else
		if (lista.size() == 1) {
			return panel1;
		} else
		
		if (lista.size() == 2) {
			return panel2;
		} else
		
		if (lista.size() == 3) {
			return panel3;
		} else {
			return panel4;
		}
	}

	public void setListaIndex(int listaIndex) {
		this.listaIndex = listaIndex;
	}
	
	
	public void lathatatlan(JPanel panel) {
		panel.setVisible(false);
	}
	
	public void megjelenites() {
		
		label1.setText("faszom");
		
		if (lista.size() == 0) {
			
		 lathatatlan(panel1);
		 lathatatlan(panel2);
		 lathatatlan(panel3);
		 lathatatlan(panel4);
		
	} else if (lista.size() > 0) {
		
		panel1.setVisible(true);
		panel1.setBackground(Color.GREEN);
		panel1.setBorder(BorderFactory.createLineBorder(Color.black));
		label1.setText(lista.get(0).toString());
		 lathatatlan(panel2);
		 lathatatlan(panel3);
		 lathatatlan(panel4);
		 
		 if (lista.size() >= 2) {
			 panel2.setVisible(true);
			 panel2.setBackground(Color.orange); 
			 panel2.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
			 label2.setText(lista.get(1).toString());
			 button_1.setEnabled(true);
			 
			 if (lista.size() >=3) {
				 
				 panel3.setVisible(true);
				 panel3.setBackground(Color.orange); 
				 panel3.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
				 label3.setText(lista.get(2).toString());
				 
				 
				 if (lista.size() >=4) {
					 
					 panel4.setVisible(true);
					 panel4.setBackground(Color.orange); 
					 panel4.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
					 label4.setText(lista.get(3).toString());
				 
			 }
		 }
	}
	}
	}





	public void addChangedListener(ChangedListener changedListener) {
		// TODO Auto-generated method stub
		listeners.add(changedListener);
		System.out.println("Hozzáadva");
		
	}

}
