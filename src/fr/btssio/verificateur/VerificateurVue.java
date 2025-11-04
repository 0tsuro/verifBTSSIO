package fr.btssio.verificateur;

import javax.swing.*;
import java.awt.*;

/**
 * Classe Vue de l'application
 * Pattern MVC : VUE
 * Gère l'affichage de l'interface graphique
 */
public class VerificateurVue extends JFrame {
    
    // Composants de l'interface
    private JPasswordField champMotDePasse;
    private JButton boutonVerifier;
    private JButton boutonAfficher;
    private JLabel labelTitre;
    private JLabel labelSaisie;
    private JLabel[] labelsResultats;
    private JProgressBar barreForce;
    private JLabel labelForce;
    
    /**
     * Constructeur de la vue
     */
    public VerificateurVue() {
        // Configuration de la fenêtre
        setTitle("Vérificateur de Mot de Passe - MVC");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        
        // Création de l'interface
        creerInterface();
    }
    
    /**
     * Crée tous les composants de l'interface graphique
     */
    private void creerInterface() {
        // Panel du haut avec le titre
        JPanel panelHaut = new JPanel();
        panelHaut.setBackground(new Color(52, 152, 219));
        labelTitre = new JLabel("Vérificateur de Force de Mot de Passe");
        labelTitre.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitre.setForeground(Color.WHITE);
        panelHaut.add(labelTitre);
        
        // Panel central
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Zone de saisie
        JPanel panelSaisie = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelSaisie = new JLabel("Mot de passe : ");
        labelSaisie.setFont(new Font("Arial", Font.PLAIN, 14));
        champMotDePasse = new JPasswordField(20);
        champMotDePasse.setFont(new Font("Arial", Font.PLAIN, 14));
        panelSaisie.add(labelSaisie);
        panelSaisie.add(champMotDePasse);
        
        // Boutons
        JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        boutonVerifier = new JButton("Vérifier");
        boutonVerifier.setBackground(new Color(46, 204, 113));
        boutonVerifier.setForeground(Color.WHITE);
        boutonVerifier.setFocusPainted(false);
        
        boutonAfficher = new JButton("Afficher/Masquer");
        boutonAfficher.setFocusPainted(false);
        
        panelBoutons.add(boutonVerifier);
        panelBoutons.add(boutonAfficher);
        
        // Panel des résultats
        JPanel panelResultats = new JPanel();
        panelResultats.setLayout(new BoxLayout(panelResultats, BoxLayout.Y_AXIS));
        panelResultats.setBorder(BorderFactory.createTitledBorder("Critères de sécurité"));
        
        String[] criteres = {
            "Longueur minimale (8 caractères)",
            "Contient des minuscules (a-z)",
            "Contient des majuscules (A-Z)",
            "Contient des chiffres (0-9)",
            "Contient des caractères spéciaux (!@#$%...)"
        };
        
        labelsResultats = new JLabel[5];
        for (int i = 0; i < 5; i++) {
            JPanel panelCritere = new JPanel(new FlowLayout(FlowLayout.LEFT));
            labelsResultats[i] = new JLabel("○ " + criteres[i]);
            labelsResultats[i].setFont(new Font("Arial", Font.PLAIN, 13));
            panelCritere.add(labelsResultats[i]);
            panelResultats.add(panelCritere);
        }
        
        // Barre de force
        JPanel panelForce = new JPanel();
        panelForce.setLayout(new BoxLayout(panelForce, BoxLayout.Y_AXIS));
        panelForce.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        labelForce = new JLabel("Force du mot de passe : Non vérifié");
        labelForce.setFont(new Font("Arial", Font.BOLD, 14));
        labelForce.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        barreForce = new JProgressBar(0, 100);
        barreForce.setStringPainted(true);
        barreForce.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panelForce.add(labelForce);
        panelForce.add(Box.createRigidArea(new Dimension(0, 5)));
        panelForce.add(barreForce);
        
        // Ajout des composants au panel central
        panelCentral.add(panelSaisie);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelCentral.add(panelBoutons);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 20)));
        panelCentral.add(panelResultats);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 20)));
        panelCentral.add(panelForce);
        
        // Ajout à la fenêtre
        add(panelHaut, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
    }
    
    /**
     * Met à jour l'affichage selon les résultats de vérification
     * @param longueurValide true si la longueur est valide
     * @param aMinuscules true si contient des minuscules
     * @param aMajuscules true si contient des majuscules
     * @param aChiffres true si contient des chiffres
     * @param aCaracteresSpeciaux true si contient des caractères spéciaux
     * @param force Force du mot de passe (0-100)
     * @param niveauForce Niveau en texte ("Faible", "Moyenne", "Forte")
     */
    public void mettreAJourAffichage(boolean longueurValide, boolean aMinuscules, 
                                     boolean aMajuscules, boolean aChiffres, 
                                     boolean aCaracteresSpeciaux, int force, 
                                     String niveauForce) {
        Color vert = new Color(46, 204, 113);
        Color rouge = new Color(231, 76, 60);
        
        // Mise à jour des labels
        labelsResultats[0].setText((longueurValide ? "✓" : "✗") + " Longueur minimale (8 caractères)");
        labelsResultats[0].setForeground(longueurValide ? vert : rouge);
        
        labelsResultats[1].setText((aMinuscules ? "✓" : "✗") + " Contient des minuscules (a-z)");
        labelsResultats[1].setForeground(aMinuscules ? vert : rouge);
        
        labelsResultats[2].setText((aMajuscules ? "✓" : "✗") + " Contient des majuscules (A-Z)");
        labelsResultats[2].setForeground(aMajuscules ? vert : rouge);
        
        labelsResultats[3].setText((aChiffres ? "✓" : "✗") + " Contient des chiffres (0-9)");
        labelsResultats[3].setForeground(aChiffres ? vert : rouge);
        
        labelsResultats[4].setText((aCaracteresSpeciaux ? "✓" : "✗") + " Contient des caractères spéciaux (!@#$%...)");
        labelsResultats[4].setForeground(aCaracteresSpeciaux ? vert : rouge);
        
        // Mise à jour de la barre de force
        barreForce.setValue(force);
        
        // Mise à jour du label et de la couleur
        labelForce.setText("Force du mot de passe : " + niveauForce);
        
        if (force <= 40) {
            labelForce.setForeground(rouge);
            barreForce.setForeground(rouge);
        } else if (force <= 80) {
            Color orange = new Color(243, 156, 18);
            labelForce.setForeground(orange);
            barreForce.setForeground(orange);
        } else {
            labelForce.setForeground(vert);
            barreForce.setForeground(vert);
        }
    }
    
    /**
     * Affiche un message d'erreur
     * @param message Le message à afficher
     */
    public void afficherErreur(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * Récupère le mot de passe saisi
     * @return Le mot de passe
     */
    public String getMotDePasse() {
        return new String(champMotDePasse.getPassword());
    }
    
    /**
     * Bascule l'affichage/masquage du mot de passe
     */
    public void basculerAffichageMotDePasse() {
        if (champMotDePasse.getEchoChar() == (char) 0) {
            champMotDePasse.setEchoChar('•');
        } else {
            champMotDePasse.setEchoChar((char) 0);
        }
    }
    
    // Getters pour les composants (pour le contrôleur)
    public JButton getBoutonVerifier() {
        return boutonVerifier;
    }
    
    public JButton getBoutonAfficher() {
        return boutonAfficher;
    }
    
    public JPasswordField getChampMotDePasse() {
        return champMotDePasse;
    }
}