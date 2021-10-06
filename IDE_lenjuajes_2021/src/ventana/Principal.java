package ventana;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import archivo.Editor;
import automata.Guia;
import buscador.Buscar;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.GridLayout;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.event.CaretEvent;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Toolkit;

public class Principal extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ABRIR = "ABRIR";
	public static final String CREAR = "CREAR";
	public static final String GUARDAR = "GUARDAR";
	public static final String ANALIZAR ="ANALIZAR";
	public static final String BUSCAR ="BUSCAR";

	/**
	 * Create the frame.
	 */
	private JPanel contentPane;
	JMenuBar menuBar = new JMenuBar();
	JMenu inicio = new JMenu("Inicio");
	JMenuItem itmAbrir = new JMenuItem("Abrir");
	JMenuItem itmGuardar = new JMenuItem("Guardar");
	JPanel panel_principal = new JPanel();
	JPanel panel_secundario = new JPanel();
	JPanel panel_botones = new JPanel();
	private Editor editor;
	private final JMenuItem itmCrear = new JMenuItem("Crear");
	private final JLabel labelFila = new JLabel("fila: ");
	private final JLabel labelColumna = new JLabel("columna: ");
	private final JTextArea textAreaEditar = new JTextArea();
	private final JTextArea textAreaNumero = new JTextArea();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JPanel panel = new JPanel();
	private final JScrollPane scrollPane_1 = new JScrollPane();
	private final JPanel panel_1 = new JPanel();
	private final JPanel panel_2 = new JPanel();
	private final JTable tableInformacion = new JTable();
	private final JTextArea textArea = new JTextArea();
	private final JButton btnAnalizar = new JButton("Analizar");
	private final JButton btnBuscar = new JButton("Buscar");

	public Principal() {
		setTitle("Ger 12 IDE 2021");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Ger12\\git\\IDE_LENGUAJES _2021\\IDE_lenjuajes_2021\\src\\ventana\\ger12.jpg"));
		contentPane = new JPanel();
		editor = new Editor();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1001, 783);

		setJMenuBar(menuBar);

		menuBar.add(inicio);

		inicio.add(itmCrear);
		itmCrear.setActionCommand(CREAR);
		itmCrear.addActionListener(this);

		inicio.add(itmAbrir);
		itmAbrir.setActionCommand(ABRIR);
		itmAbrir.addActionListener(this);

		inicio.add(itmGuardar);
		itmGuardar.setActionCommand(GUARDAR);
		itmGuardar.addActionListener(this);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		panel_principal.setBackground(Color.RED);
		contentPane.add(panel_principal);
		panel_principal.setLayout(new BorderLayout(0, 0));
		
		panel_principal.add(scrollPane);
		
		scrollPane.setViewportView(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		textAreaNumero.setEditable(false);
		panel_2.add(textAreaNumero, BorderLayout.WEST);
		textAreaNumero.setBorder(new LineBorder(new Color(0, 0, 0)));
		textAreaNumero.setPreferredSize(new Dimension(30, 22));
		panel_2.add(textAreaEditar, BorderLayout.CENTER);
		
		textAreaEditar.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				int linea =1;
				int columna = 1;
				int total = 1;
				
				try {
					int pos = textAreaEditar.getCaretPosition();
					linea = textAreaEditar.getLineOfOffset(pos);
					columna = pos - textAreaEditar.getLineStartOffset(linea);
					
					linea +=1;
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
				total = textAreaEditar.getLineCount();
				String cambio = "1";
				for (int i = 0; i < total; i++) {
					if(i>=1)cambio =cambio +"\n"+(i+1) ;
				}
				textAreaNumero.setText(cambio);
				
				filaComuna(linea, columna);
			}
		});

		panel_secundario.setBackground(Color.GREEN);
		contentPane.add(panel_secundario);
		panel_secundario.setLayout(new BorderLayout(0, 0));
		panel_botones.setPreferredSize(new Dimension(10, 30));

		panel_botones.setSize(new Dimension(5, 100));
		panel_secundario.add(panel_botones, BorderLayout.NORTH);
		panel_botones.setLayout(new BoxLayout(panel_botones, BoxLayout.X_AXIS));
		labelFila.setMaximumSize(new Dimension(50, 14));
		labelFila.setMinimumSize(new Dimension(30, 30));
		labelFila.setPreferredSize(new Dimension(50, 25));
		panel_botones.add(labelFila);
		labelColumna.setMaximumSize(new Dimension(70, 14));
		
		panel_botones.add(labelColumna);
		
		panel_botones.add(btnAnalizar);
		btnAnalizar.setActionCommand(ANALIZAR);
		btnAnalizar.addActionListener(this);
		
		panel_botones.add(btnBuscar);
		btnBuscar.setActionCommand(BUSCAR);
		btnBuscar.addActionListener(this);
		
		panel_secundario.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel.add(scrollPane_1, BorderLayout.CENTER);
		
		scrollPane_1.setViewportView(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		tableInformacion.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"token", "lexema", "Fila", "Columna"
			}
		));
		
		panel_1.add(tableInformacion);
		textArea.setBorder(new LineBorder(Color.BLACK, 2));
		
		panel_1.add(textArea);
	}

	public String getContenido() {
		String contenido;
		contenido = textAreaEditar.getText();
		return contenido;
	}

	public void refrescarcontenido(String contenido) {
		textAreaEditar.setText(contenido);
	}


	/**
	 * comando para abrir un archivo
	 */
	public void abrirArchivo() {
		JFileChooser fc = new JFileChooser();
		if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File f = fc.getSelectedFile();
			String contenido;
			try {
				contenido = editor.abrir(f.getAbsolutePath());
				this.refrescarcontenido(contenido);

			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, e.getMessage(), "Editor", JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	/**
	 * crear un nuevo archivo 
	 */
	public void crearArchivo() {
		editor.crear();
		this.refrescarcontenido("");
	}

	/**
	 * guardar archivo 
	 */
	public void guardarArchivo() {
		
		String contenido ="";
		String rutaArchivo = "";
		if (editor.esArchivoNuevo()) {
			JFileChooser fc = new JFileChooser();
			if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				rutaArchivo = fc.getSelectedFile().getAbsolutePath();
				
			}
		}
		contenido = this.getContenido();
		try {
			editor.guardar(contenido, rutaArchivo);
			JOptionPane.showMessageDialog(this, "Archivo Guardado con exito", "Editor", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), "Editor", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * acciones de botosnes
	 */
	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getActionCommand().equals(ABRIR)) {
			this.abrirArchivo();

		} else if (evento.getActionCommand().equals(CREAR)) {
			this.crearArchivo();

		} else if (evento.getActionCommand().equals(GUARDAR)) {
			this.guardarArchivo();
		
		}else if(evento.getActionCommand().equals(ANALIZAR)) {
			Guia g = new Guia();
			g.paso1(textAreaEditar, textArea, tableInformacion);
		}else if(evento.getActionCommand().equals(BUSCAR)) {
			String cadena = JOptionPane.showInputDialog("introdusca la palabra");
			Buscar bs = new Buscar(cadena,textAreaEditar );
			bs.creaarEstados();
			bs.encontrar();
		}
	}
	
	/**
	 * llena los indicadores de fila y comuna
	 * @param fila
	 * @param columna
	 */
	public void filaComuna(int fila,int columna) {
		labelFila.setText("fila: "+fila);
		labelColumna.setText("columna; "+columna);
	}
	
}
