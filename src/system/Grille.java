/* @document Grille.java :
 * This class contains the grid on which Case will be matched 
 * 
 * @author arnaud.panaiotis@gmx.fr
 * 
 * @about : 
 * See main class lxiang.Lxiang for more informations
 */
package system;

import java.util.Hashtable;

import error.*;

/**
 * @author Arno
 * @date 28/01/2014
 */
public class Grille {
   
    //hashtable : a table of hash used to remove some redoundant recursiv calls
    private static Hashtable<Double, Integer> hashtable;
    // X (resp Y) is the number of column (resp line)
    private final int X = 6;
    private final int Y = 7;
    //The matrix of the Grille
    //Use getCase(int x, int y) method
    private Case grille [] ;
    //A boolean present to check if the grille were fulled before start
    private boolean asBeenFulled;
    
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
        asBeenFulled = false;
    }
    
    /**
     * Return the indice of the case at the left of the case given in parameter
     * @param indice : the indice of the current case in the array
     */
    private int getLeftCase(int indice) throws WrongAccessException{
        if (indice % 7 == 0)
            throw new WrongAccessException();
        return indice -1;
    }
    
    /**
     * Return the indice of the case at the right of the case given in parameter
     * @param indice : the indice of the current case in the array
     */
    private int getRightCase(int indice) throws WrongAccessException{
        if (indice % 7 == 6)
            throw new WrongAccessException();
        return indice +1;
    }
    
    /**
     * Return the indice of the case at the top of the case given in parameter
     * @param indice : the indice of the current case in the array
     */
    private int getTopCase(int indice) throws WrongAccessException{
        if (indice <7)
            throw new WrongAccessException();
        return indice -7;
    }
    
    /**
     * Return the indice of the case at the bottom of the case given in 
     * parameter
     * @param indice : the indice of the current case in the array
     */
    private int getBotCase(int indice) throws WrongAccessException{
        if (indice >35)
            throw new WrongAccessException();
        return indice +7;
    }
    
    /**
     * @return the hash of the current grille
     */
    private int calculateHash(){
        int tmp = 0;
        for (int i = 0; i<X*Y; i++){
            if(grille[i].isAble())
                tmp += 2^i;
        } 
        return tmp;
    }
    
    /**
     * Give the score of the configuration without cases A and B
     * @param A the indice of the &st case
     * @param B the indice of the 2nd case
     * @return the score with the under grille
     */
    private int calculateScoreWith2Case (int A, int B){
        int ret;
        double hash;
        
        //Unaffect those 2 cases to have a hash like their aren't here
        grille[A].unable();
        grille[B].unable();
        hash = this.calculateHash();
        if (hashtable.containsKey(hash)){
            //case this configuration were even calculated
            ret = hashtable.get(hash);
        } else {
            //case this configuration need to be calculated
            ret = this.calculateRecursively();
            hashtable.put(hash, ret);
        }
        
        //Reaffect the 2 cases
        grille[A].able();
        grille[B].able();
        return ret;
    }
  
    private int calculateRecursively(){
        int tempMaxScore = 0;
        for (int first = 0; first<X*Y; first++){
            //for each 1st case clicked
            
        }
        return 0;
    }
    
    /**
     * Check if the grille have been completed, modify asBeenFulled if true
     */
    private void checkFull(){
        for (int i = 0; i<X*Y; i++){
            if (! grille[i].isAble())
                return;
        }
        asBeenFulled = true;
    }
    
    /**
     * @return the score able with this grille
     * @throws MissingInitializationException 
     */
    public int getScore() throws MissingInitializationException{
        this.checkFull();
        if (! asBeenFulled)
            throw new MissingInitializationException();
        return this.calculateRecursively();
    }
}
