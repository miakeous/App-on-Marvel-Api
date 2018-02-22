package interfaceGraphique;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Appli extends JFrame implements ActionListener {
	JTabbedPane fullonglet  = new JTabbedPane();
	
	OngletAlice og2 = new OngletAlice();
	//OngletDave og1 = new OngletDave();
	JButton doc = new JButton("Doc Utilisateur");
	//MyTextPane docs = new MyTextPane();
	public Appli(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//On la laisse visible
		this.setVisible(true);
		//JPanel panDave = og1.envoieDave();
		JTabbedPane ongletalice =  og2.envoieAlice();
		
		//fullonglet.add("Dave", panDave);
		
		fullonglet.add("Alice",ongletalice);
		doc.addActionListener(this);
		//StringBuilder text = new StringBuilder();
		//text.append("Cliquer ici pour ouvrir le lien : <a href='Documentation utilisateurs V1.pdf'>Visualiser la doc utilisateur</a>");
		//docs.setText(text.toString());
		//On ajoute finalement nos onglet a la fenetre
		this.getContentPane().add(fullonglet);
		this.getContentPane().add(doc, BorderLayout.SOUTH);
				//On donne un titre a la fenetre
		this.setTitle("Projet Info ");
				//On definit une taille suffisante pour tout afficher.
		this.setSize(1000, 1000);
		
	}
public void actionPerformed(ActionEvent e){
			
			if (e.getSource().equals(doc) ){
				System.out.println("salut");
				//new ReadFile();

			
				
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
	public static void main(String arg[]){
		new Appli();
	}
}
