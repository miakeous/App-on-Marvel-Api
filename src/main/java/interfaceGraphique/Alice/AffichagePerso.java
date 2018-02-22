package interfaceGraphique.Alice;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

import org.json.JSONException;
import useCase.alice.InformationPerso;
import useCase.alice.PersonnageMarvel;

public class AffichagePerso {
	JButton bouton1 = new JButton("Rechercher un Personnage");
	//JButton bouton2 = new JButton("Rechercher information Comic");
	JPanel panel = new JPanel();
	JPanel pan3 = new JPanel();
	JScrollPane scrollPane = new JScrollPane() ;
	JLabel resu = new JLabel();
	JLabel resuf = new JLabel();

	MyTextPane resultatH = new MyTextPane();

	String recherche = "";
	JPanel pan2 = new JPanel();
	JTextField id = new JTextField(20);
	
	public AffichagePerso() {
		AbstractAction action = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        System.out.println("some action");
		        recherche = id.getText();
				
				//PersonnageMarvel nouvel = new PersonnageMarvel(recherche);
				//resultatH.setText(nouvel.getInfo());
		    }
		};
		id.addActionListener(action);
		Ecouteur listen = new Ecouteur();
		resultatH.setOpaque(true);
		resultatH.setContentType("text/html");
		bouton1.addActionListener(listen);
		//bouton2.addActionListener(listen);
		resultatH.addHyperlinkListener(new HyperlinkListener() {
	        public void hyperlinkUpdate(HyperlinkEvent e) {
	        	if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
	                if (Desktop.isDesktopSupported()) {
	                    try {
	                        Desktop.getDesktop().browse(e.getURL().toURI());
	                    } catch (IOException e1) {
	                        // TODO Auto-generated catch block
	                        e1.printStackTrace();
	                    } catch (URISyntaxException e1) {
	                        // TODO Auto-generated catch block
	                        e1.printStackTrace();
	                    }
	                }
	                }
	        	}});
		
		
		JPanel pan = new JPanel();
		pan.add(id);
		resultatH.setEditable(false);
		HTMLEditorKit kit = new HTMLEditorKit();
		 StyleSheet styleSheet = kit.getStyleSheet();
		 styleSheet.addRule("h1 {margin-left : 300pt ; margin-top : -40pt;}\n");
		 styleSheet.addRule("p {margin-left : 30 ;}\n");
		resultatH.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
		Document doc = kit.createDefaultDocument();
		resultatH.setDocument(doc);
		JPanel pan1 = new JPanel();
		pan1.add(bouton1);
		//pan1.add(bouton2);
		id.setText("Exemple : Spider-Man ");
		pan.setAlignmentY(Component.TOP_ALIGNMENT);
		pan1.setAlignmentY(Component.CENTER_ALIGNMENT);
		pan2.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		pan3.add(pan);
		pan3.add(pan1 );
		
		pan3.setAlignmentY(Component.CENTER_ALIGNMENT);
		scrollPane = new JScrollPane(resultatH);
		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportView(resultatH);
		scrollPane.setPreferredSize(new Dimension(700,550));
		pan3.add(scrollPane);
		panel = pan3;
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
				
				//PersonnageMarvel nouvel = new PersonnageMarvel();
				//resultatH.setText(nouvel.getInfo(recherche));
			}
			
		}
	}

	public class MyTextPane extends JEditorPane {
	    public MyTextPane(){
	        super();
	    }
	 
}

	public Component envoie() {
		// TODO Auto-generated method stub
		return pan3;
	}}
