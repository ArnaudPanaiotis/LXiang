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
 * @date 10/05/2014
 */


public class Case {

    
    //Unique id used to remove some useless call
    private final Long id;
    private final int indice;
    private Piece piece;
    
    /**
     * Constructor
     * @param id the unique id in function of the case
     * @param indice is the indice of the case (in the array)
     */
    Case (long id, int indice){
        this.id = id;
        this.indice = indice;
    }
    
    /**
     * @return the id on this case
     */
    public Long getId(){return id;}
    
    public Piece getPiece(){return this.piece;}
    
    /**
     * Put the piece at this case
     * @param piece 
     */
    public void affectPiece(Piece piece){
        this.piece = piece;
        this.piece.affect();
    }
    
    /**
     * Mark this case as not having piece on it
     * USE : before recursive call
     */
    public void makesNotHere(){this.piece.unAffect();}
    
    /**
     * Mark this case as having the piece back on it
     * USE : after recursive call
     */
    public void makesHereBack(){this.piece.affect();}
    
    
    /**
     * Say if the case is currently here
     * @return 
     */
    public boolean isHere(){ return this.piece.isAffected();}
    
    
    /**
     * @return the indice of the case at the left of this case
     * @throws error.WrongAccessException
     */
    public int getLeftCaseIndice() throws WrongAccessException{
        if (this.indice % 7 == 0)
            throw new WrongAccessException();
        return this.indice -1;
    }
    
    /**
     * @return the indice of the case at the right of this case
     * @throws error.WrongAccessException
     */
    public int getRightCaseIndice() throws WrongAccessException{
        if (this.indice % 7 == 6)
            throw new WrongAccessException();
        return this.indice +1;
    }
    
    /**
     * @return the indice of the case at the top of this case
     * @throws error.WrongAccessException
     */
    public int getTopCaseIndice() throws WrongAccessException{
        if (this.indice <7)
            throw new WrongAccessException();
        return this.indice -7;
    }
    
    /**
     * @return the indice of the case at the bottom of this case
     * @throws error.WrongAccessException
     */
    public int getBotCaseIndice() throws WrongAccessException{
        if (this.indice >=35)
            throw new WrongAccessException();
        return this.indice +7;
    }
    
    /**
     * @return the indice of this case (in the grille)
     */
    public int getIndice(){return this.indice;}
    
}
