package fr.btssio.verificateur;

import fr.btssio.verificateur.MotDePasse;
import fr.btssio.verificateur.VerificateurVue;
import fr.btssio.verificateur.VerificateurControleur;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Classe principale de l'application
 * Pattern MVC : Point d'entrée
 * Instancie le Modèle, la Vue et le Contrôleur
 */
public class Main {
    
    public static void main(String[] args) {
        
        // Configuration du Look and Feel (apparence système)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Lancement de l'application dans le thread Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Création du Modèle
                MotDePasse modele = new MotDePasse();
                
                // Création de la Vue
                VerificateurVue vue = new VerificateurVue();
                
                // Création du Contrôleur (qui lie Modèle et Vue)
                VerificateurControleur controleur = new VerificateurControleur(modele, vue);
                
                // Affichage de l'application
                controleur.afficher();
            }
        });
    }
}