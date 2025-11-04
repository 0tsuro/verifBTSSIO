package fr.btssio.verificateur;

/**
 * Classe Modèle représentant un mot de passe et ses critères de validation
 * Pattern MVC : MODELE
 */
public class MotDePasse {
    
    // Attributs privés
    private String valeur;
    private boolean aMinuscules;
    private boolean aMajuscules;
    private boolean aChiffres;
    private boolean aCaracteresSpeciaux;
    private boolean longueurValide;
    private int force; // 0 à 100
    
    /**
     * Constructeur par défaut
     */
    public MotDePasse() {
        this.valeur = "";
        reinitialiser();
    }
    
    /**
     * Constructeur avec valeur initiale
     * @param valeur Le mot de passe à vérifier
     */
    public MotDePasse(String valeur) {
        this.valeur = valeur;
        verifier();
    }
    
    /**
     * Réinitialise tous les critères
     */
    private void reinitialiser() {
        this.aMinuscules = false;
        this.aMajuscules = false;
        this.aChiffres = false;
        this.aCaracteresSpeciaux = false;
        this.longueurValide = false;
        this.force = 0;
    }
    
    /**
     * Définit la valeur du mot de passe et lance la vérification
     * @param valeur Le nouveau mot de passe
     */
    public void setValeur(String valeur) {
        this.valeur = valeur;
        verifier();
    }
    
    /**
     * Vérifie tous les critères du mot de passe
     */
    public void verifier() {
        reinitialiser();
        
        if (valeur == null || valeur.isEmpty()) {
            return;
        }
        
        // Vérification de la longueur
        if (valeur.length() >= 8) {
            longueurValide = true;
        }
        
        // Vérification des caractères
        for (char c : valeur.toCharArray()) {
            if (Character.isLowerCase(c)) {
                aMinuscules = true;
            } else if (Character.isUpperCase(c)) {
                aMajuscules = true;
            } else if (Character.isDigit(c)) {
                aChiffres = true;
            } else if (estCaractereSpecial(c)) {
                aCaracteresSpeciaux = true;
            }
        }
        
        // Calcul de la force
        calculerForce();
    }
    
    /**
     * Vérifie si un caractère est un caractère spécial
     * @param c Le caractère à vérifier
     * @return true si c'est un caractère spécial
     */
    private boolean estCaractereSpecial(char c) {
        String caracteresSpeciaux = "!@#$%^&*()_+-=[]{}|;:',.<>?/~`\"\\";
        return caracteresSpeciaux.indexOf(c) != -1;
    }
    
    /**
     * Calcule la force du mot de passe (0-100)
     */
    private void calculerForce() {
        force = 0;
        if (longueurValide) force += 20;
        if (aMinuscules) force += 20;
        if (aMajuscules) force += 20;
        if (aChiffres) force += 20;
        if (aCaracteresSpeciaux) force += 20;
    }
    
    /**
     * Retourne le niveau de force en texte
     * @return "Faible", "Moyenne" ou "Forte"
     */
    public String getNiveauForce() {
        if (force <= 40) {
            return "Faible";
        } else if (force <= 80) {
            return "Moyenne";
        } else {
            return "Forte";
        }
    }
    
    // Getters
    public String getValeur() {
        return valeur;
    }
    
    public boolean aMinuscules() {
        return aMinuscules;
    }
    
    public boolean aMajuscules() {
        return aMajuscules;
    }
    
    public boolean aChiffres() {
        return aChiffres;
    }
    
    public boolean aCaracteresSpeciaux() {
        return aCaracteresSpeciaux;
    }
    
    public boolean aLongueurValide() {
        return longueurValide;
    }
    
    public int getForce() {
        return force;
    }
    
    /**
     * Vérifie si le mot de passe respecte tous les critères
     * @return true si tous les critères sont validés
     */
    public boolean estValide() {
        return longueurValide && aMinuscules && aMajuscules && 
               aChiffres && aCaracteresSpeciaux;
    }
    
    @Override
    public String toString() {
        return "MotDePasse [force=" + force + "%, niveau=" + getNiveauForce() + "]";
    }
}