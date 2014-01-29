/* @document Grille.java :
 * This class contains the grid on which Case will be matched 
 * 
 * @author arnaud.panaiotis@gmx.fr
 * 
 * @about : 
 * See main class lxiang.Lxiang for more informations
 */
package system.data;

import java.util.Hashtable;

/**
 * @author Arno
 * @date 28/01.2014
 */
public class Grille {
    
    private class WrongAccessException extends Exception{
        
    }
    
    //hashtable : a table of hash used to remove some redoundant recursiv calls
    private static Hashtable<Double, Integer> hashtable;
    // X (resp Y) is the number of column (resp line)
    private final int X = 6;
    private final int Y = 7;
    //The matrix of the Grille
    //Use getCase(int x, int y) method
    private Case grille [] ;
    
    /**
     * Constructor
     * Initialize all the requested data for Grille
     */    
    public Grille(){
        hashtable = new Hashtable<Double, Integer>();
        grille = new Case[X*Y];
        for (int i = 0; i<X*Y; i++){
            grille[i] = new Case(2^i);
        }
            
    }
    
    /**
     * Return the case at the left of the case given in parameter
     * @param indice : the indice of the current case in the array
     */
    private Case getLeftCase(int indice) throws WrongAccessException{
        if (indice % 7 == 0)
            throw new WrongAccessException();
        return grille[indice -1];
    }
    
    /**
     * Return the case at the right of the case given in parameter
     * @param indice : the indice of the current case in the array
     */
    private Case getRightCase(int indice) throws WrongAccessException{
        if (indice % 7 == 6)
            throw new WrongAccessException();
        return grille[indice +1];
    }
    
    /**
     * Return the case at the top of the case given in parameter
     * @param indice : the indice of the current case in the array
     */
    private Case getTopCase(int indice) throws WrongAccessException{
        if (indice <7)
            throw new WrongAccessException();
        return grille[indice -7];
    }
    
    /**
     * Return the case at the bottom of the case given in parameter
     * @param indice : the indice of the current case in the array
     */
    private Case getBotCase(int indice) throws WrongAccessException{
        if (indice >35)
            throw new WrongAccessException();
        return grille[indice +7];
    }
    
    
    
    
}
