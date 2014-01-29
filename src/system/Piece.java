/* @document Case.java :
 * This class contains a Piece on a case
 * 
 * @author arnaud.panaiotis@gmx.fr
 * 
 * @about : 
 * See main class lxiang.Lxiang for more informations
 */
package system;
/**
 * @author Arno
 * @date 29/01/2014
 */
public class Piece {
    
    private Enums.Color color;
    private Enums.Forme forme;
    private Enums.Inside inside;
    
    //A boolean used to active/unactive values while recursives calls
    private boolean isAffected;
    
    //Constructor
    public Piece(Enums.Color color, 
                 Enums.Forme forme, 
                 Enums.Inside inside){
        this.color = color;
        this.forme = forme;
        this.inside = inside;
        this.isAffected = true;
    }
    
    public Enums.Color getColor(){return this.color;}
    
    public Enums.Forme getForme(){return this.forme;}
    
    public Enums.Inside getInside(){return this.inside;}
    
    public boolean isAffected(){return this.isAffected;}
    
    public void unAffect(){this.isAffected = false;}
    
    public void affect(){this.isAffected = true;}
    
    /**
     * Calculate the score between 2 pieces
     * @param piece the piece to compare this with
     * @return the score given by this 2 pieces
     */
    public int calculateScore(Piece piece){
        int ret = 0;
        if (this.color  == piece.color)  ret++;
        if (this.forme  == piece.forme)  ret++;
        if (this.inside == piece.inside) ret++;
        switch (ret){
            case 0 : 
                ret = 1;
                break;
            case 1 :
                ret = 50;
                break;
            case 2 :
                ret = 300;
                break;
            case 3 :
                ret = 1000;
                break;
        }
        return ret; 
    }
   
}
