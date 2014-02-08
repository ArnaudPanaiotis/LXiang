/* @document Case.java :
 * This class contains a Case of the Grille
 * 
 * @author arnaud.panaiotis@gmx.fr
 * 
 * @about : 
 * See main class lxiang.Lxiang for more informations
 */
package system;

import error.WrongAccessException;

/**
 * @author Arno
 * @date 28/01/2014
 */


public class Case {

    
    //Unique id used to remove some useless call
    private double id;
    private int indice;
    private Piece piece;
    
    /**
     * Constructor
     * @param id ths unique id in function of the case
     */
    public Case (int id, int indice){
        this.id = id;
        this.indice = indice;
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
    
    
    /**
     * @return the indice of the case at the left of this case
     */
    public int getLeftCaseIndice() throws WrongAccessException{
        if (this.indice % 7 == 0)
            throw new WrongAccessException();
        return this.indice -1;
    }
    
    /**
     * @return the indice of the case at the right of this case
     */
    public int getRightCaseIndice() throws WrongAccessException{
        if (this.indice % 7 == 6)
            throw new WrongAccessException();
        return this.indice +1;
    }
    
    /**
     * @return the indice of the case at the top of this case
     */
    public int getTopCaseIndice() throws WrongAccessException{
        if (this.indice <7)
            throw new WrongAccessException();
        return this.indice -7;
    }
    
    /**
     * @return the indice of the case at the bottom of this case
     */
    public int getBotCaseIndice() throws WrongAccessException{
        if (this.indice >35)
            throw new WrongAccessException();
        return this.indice +7;
    }
    
    /**
     * @return the indice of this case (in the grille)
     */
    public int getIndice(){return this.indice;}
    
}
