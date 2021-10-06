package buscador;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;

public class PanelMuestra extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldBusqueda;
	private JTextField textFieldCoin;
	private JTextPane textPaneBusqueda;

	/**
	 * crea el panel de salida para la busqueda
	 */
	public PanelMuestra() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		textPaneBusqueda = new JTextPane();
		scrollPane.setViewportView(textPaneBusqueda);
		
		JPanel panel = new JPanel();
		scrollPane.setColumnHeaderView(panel);
		
		JLabel lblNewLabel = new JLabel("Pablara: ");
		panel.add(lblNewLabel);
		
		textFieldBusqueda = new JTextField();
		textFieldBusqueda.setPreferredSize(new Dimension(50, 20));
		textFieldBusqueda.setEditable(false);
		panel.add(textFieldBusqueda);
		textFieldBusqueda.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Coincidencias");
		panel.add(lblNewLabel_1);
		
		textFieldCoin = new JTextField();
		textFieldCoin.setEditable(false);
		panel.add(textFieldCoin);
		textFieldCoin.setColumns(10);
	}
	
	public JTextPane getTextpaneBusqueda() {
		return textPaneBusqueda; 
	}
	
	public void setBusqueda(String busqueda) {
		this.textFieldBusqueda.setText(busqueda); 
	}

	public void setCantidad(String busqueda) {
		this.textFieldCoin.setText(busqueda); 
	}
	
	
	public void refresh() {
		this.update(getGraphics());
	}

}
