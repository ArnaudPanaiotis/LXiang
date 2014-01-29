/* @document Case.java :
 * This class contains a Case of the Grille
 * 
 * @author arnaud.panaiotis@gmx.fr
 * 
 * @about : 
 * See main class lxiang.Lxiang for more informations
 */
package system;
/**
 * @author Arno
 * @date 28/01/2014
 */


public class Case {

    
    //Unique id used to remove some useless call
    private double id;
    private Piece piece;
    
    /**
     * Constructor
     * @param id ths unique id in function of the case
     */
    public Case (int id){
        this.id = id;
    }
    
    public double getId(){return id;}
    
    public Piece getPiece(){return this.piece;}
    
    /**
     * Put the piece at this case
     * @param piece 
     */
    public void affectPiece(Piece piece){this.piece = piece;}
    
    /**
     * Mark this case as not having piece on it
     * USE : before recursive call
     */
    public void unable(){this.piece.unAffect();}
    
    /**
     * Mark this case as having the piece back on it
     * USE : after recursive call
     */
    public void able(){this.piece.affect();}
    
    
    /**
     * Say if the case is currently abled
     * @return 
     */
    public boolean isAble(){ return this.piece.isAffected();}
    
    
    
}
