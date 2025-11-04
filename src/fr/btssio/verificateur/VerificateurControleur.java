package fr.btssio.verificateur;

import fr.btssio.verificateur.MotDePasse;
import fr.btssio.verificateur.VerificateurVue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe Contrôleur de l'application
 * Pattern MVC : CONTROLEUR
 * Gère les interactions entre la Vue et le Modèle
 */
public class VerificateurControleur {
    
    // Références vers le Modèle et la Vue
    private MotDePasse modele;
    private VerificateurVue vue;
    
    /**
     * Constructeur du contrôleur
     * @param modele Le modèle (données)
     * @param vue La vue (interface graphique)
     */
    public VerificateurControleur(MotDePasse modele, VerificateurVue vue) {
        this.modele = modele;
        this.vue = vue;
        
        // Ajout des écouteurs d'événements
        ajouterEcouteurs();
    }
    
    /**
     * Ajoute les écouteurs d'événements sur les composants de la vue
     */
    private void ajouterEcouteurs() {
        
        // Écouteur pour le bouton "Vérifier"
        vue.getBoutonVerifier().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifierMotDePasse();
            }
        });
        
        // Écouteur pour le bouton "Afficher/Masquer"
        vue.getBoutonAfficher().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vue.basculerAffichageMotDePasse();
            }
        });
        
        // Écouteur pour la touche Entrée dans le champ
        vue.getChampMotDePasse().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifierMotDePasse();
            }
        });
    }
    
    /**
     * Méthode principale : vérifie le mot de passe et met à jour la vue
     */
    private void verifierMotDePasse() {
        // Récupération du mot de passe depuis la vue
        String motDePasse = vue.getMotDePasse();
        
        // Validation : vérifier que le champ n'est pas vide
        if (motDePasse.isEmpty()) {
            vue.afficherErreur("Veuillez saisir un mot de passe !");
            return;
        }
        
        // Mise à jour du modèle avec le nouveau mot de passe
        modele.setValeur(motDePasse);
        
        // Mise à jour de la vue avec les résultats du modèle
        vue.mettreAJourAffichage(
            modele.aLongueurValide(),
            modele.aMinuscules(),
            modele.aMajuscules(),
            modele.aChiffres(),
            modele.aCaracteresSpeciaux(),
            modele.getForce(),
            modele.getNiveauForce()
        );
    }
    
    /**
     * Affiche la vue
     */
    public void afficher() {
        vue.setVisible(true);
    }
}