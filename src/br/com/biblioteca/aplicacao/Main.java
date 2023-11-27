package br.com.biblioteca.aplicacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import br.com.biblioteca.dao.LivroDAO;
import br.com.biblioteca.model.Livro;

public class Main {
	
	static JFrame frame;
	static LivroDAO livroDAO;
	static List<Livro> livros;
	
	public static void adicionarLivro() {		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(0,0,600,400);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Cadastro de Livro");
		label.setBounds(230,30,200,30);
		panel.add(label);
		
		JLabel labelt = new JLabel("Titulo");
		labelt.setBounds(80,100,100,30);
		panel.add(labelt);
		
		JTextField titulo = new JTextField();
		titulo.setBounds(130,100,400,30);
		panel.add(titulo);
		
		JLabel labela = new JLabel("Autor");
		labela.setBounds(80,150,100,30);
		panel.add(labela);
		
		JTextField autor = new JTextField();
		autor.setBounds(130,150,400,30);
		panel.add(autor);
		
		JLabel labele = new JLabel("Editora");
		labele.setBounds(80,200,100,30);
		panel.add(labele);
		
		JTextField editora = new JTextField();
		editora.setBounds(130,200,400,30);
		panel.add(editora);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				telaInicial();
			}
		});
		btnVoltar.setBounds(130, 250, 90, 50);
		panel.add(btnVoltar);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Livro livro = new Livro();
				livro.setTitulo(titulo.getText().toString());
				livro.setAutor(autor.getText().toString());
				livro.setEditora(editora.getText().toString());
				
				livroDAO.save(livro);
				JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
				telaInicial();
			}
		});
		btnAdicionar.setBounds(440, 250, 90, 50);
		panel.add(btnAdicionar);
		
		frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
	}
	
	public static void telaInicial() {
		livros = livroDAO.listar();
		
		frame.getContentPane().removeAll();
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(0,0,600,400);
		panel.setLayout(null);
		
		String[] columnNames = {"Id", "Titulo", "Autor", "Editora"};
		
		String[][] data = new String[livros.size()][4];

		int rowIndex = 0;
		for (Livro livro : livros) {
		    data[rowIndex][0] = String.valueOf(livro.getId());
		    data[rowIndex][1] = livro.getTitulo();
		    data[rowIndex][2] = livro.getAutor();
		    data[rowIndex][3] = livro.getEditora();
		    rowIndex++;
		}
		
		JTable table = new JTable(data, columnNames);
		table.setEnabled(false);
		
		JScrollPane tableContainer = new JScrollPane(table);
		tableContainer.setBounds(0, 0, 400, 400);
		panel.add(tableContainer);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				adicionarLivro();
				
			}
		});
		btnNovo.setBounds(450, 150, 90, 50);
		panel.add(btnNovo);
		
		frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
	}

	public static void main(String[] args) {
		livroDAO = new LivroDAO();
		
		frame = new JFrame();
		frame.setTitle("Biblioteca Bonita");
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
		frame.setBounds(300,100,600,400);
		frame.setResizable(false);
		
		telaInicial();
		
		frame.setVisible(true);
		
	}
}