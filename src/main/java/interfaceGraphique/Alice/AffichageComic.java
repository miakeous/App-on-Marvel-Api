package interfaceGraphique.Alice;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import useCase.MainClass;
import useCase.alice.PersonnageMarvel;
import useCase.alice.infoComics;

public class AffichageComic {
	
	JButton bouton1 = new JButton("Rechercher");
	
	String recherche = "";
	String annee = "";
	String chapitre = "";
	
	JPanel pan3 = new JPanel();
	JScrollPane scrollPane = new JScrollPane() ;
	
	MyTextPane resultatH = new MyTextPane();
	
	JTextField id = new JTextField(20);
	JTextField anne = new JTextField(10);
	JTextField chap = new JTextField(10);
	
	JPanel pan2 = new JPanel();
	
	//On va maintenant creer notre fenetre
		public AffichageComic(){
			AbstractAction action = new AbstractAction()
			{
			    public void actionPerformed(ActionEvent e)
			    {
			    	recherche = id.getText();
					annee = (anne.getText());
					//System.out.println(annee.equals(""));
					chapitre = chap.getText();
					/*try {
						infoComics nouvel = new infoComics();
						//resultatH.setText(nouvel.GetComics(recherche, annee, chapitre, ""));
					} catch (IOException e1) {
						resultatH.setText("Erreur identifiant non trouve !");
						e1.printStackTrace();
					}*/
			    }
			};
			
			Ecouteur listen = new Ecouteur();
			resultatH.setOpaque(true);
			resultatH.setContentType("text/html");
			bouton1.addActionListener(listen);
			JPanel pan = new JPanel();
			pan.add(id);
			pan.add(anne);
			pan.add(chap);
			id.addActionListener(action);
			anne.addActionListener(action);
			chap.addActionListener(action);
			resultatH.setEditable(false);
			JPanel pan1 = new JPanel();
			pan1.add(bouton1);
			
			id.setText("Exemple : Spider-Man ");
			
	        
			
			pan.setAlignmentY(Component.TOP_ALIGNMENT);
			pan1.setAlignmentY(Component.CENTER_ALIGNMENT);
			pan2.setAlignmentY(Component.BOTTOM_ALIGNMENT);
			pan3.add(pan);
			pan3.add(pan1);
			
			pan3.setAlignmentY(Component.CENTER_ALIGNMENT);
			scrollPane = new JScrollPane(resultatH);
			
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setViewportView(resultatH);
			scrollPane.setPreferredSize(new Dimension(700,550));
			pan3.add(scrollPane);
			
			id.addFocusListener(new FocusAdapter() {
			    @Override
			    public void focusGained(FocusEvent e) {
			    	id.setText("");
			    }
			});
			
			
		}

	
	public class  Ecouteur implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			if (e.getSource().equals(bouton1) ){
				recherche = id.getText();
				annee = (anne.getText());
				//System.out.println(annee.equals(""));
				chapitre = chap.getText();
				/*try {
					infoComics nouvel = new infoComics();
					//resultatH.setText(nouvel.GetComics(recherche, annee, chapitre, ""));
				} catch (IOException e1) {
					resultatH.setText("Erreur identifiant non trouve !");
					e1.printStackTrace();
				}*/
			}
	
			

		}
	}

	public class MyTextPane extends JTextPane {
	    public MyTextPane(){
	        super();
	    }
	 
	    public boolean getScrollableTracksViewportWidth(){
	        return false;
	    }
	}
	
	public  JPanel envoie(){

		return pan3;
	}
	public static void main(String[] args) {
		JFrame fen = new JFrame();
		JTabbedPane fullonglet  = new JTabbedPane();
		AffichageComic affi = new AffichageComic();
		fullonglet.addTab("MABITE", affi.envoie());
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fen.setVisible(true);
		fen.getContentPane().add(fullonglet);
		//On definit une taille suffisante pour tout afficher.
		fen.setSize(1000, 1000);
	}

}
