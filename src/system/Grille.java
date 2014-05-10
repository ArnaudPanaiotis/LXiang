/* @document Grille.java :
 * This class contains the grid on which Case will be matched 
 * 
 * @author arnaud.panaiotis@gmx.fr
 * 
 * @about : 
 * See main class lxiang.Lxiang for more informations
 */
package system;

import java.util.*;

import error.*;

/**
 * @author Arno
 * @date 10/05/2014
 */
public class Grille {
   
    //hashtable : a table of hash used to remove some redoundant recursiv calls
    private static Hashtable<Long, Return> hashtable;
    // X (resp Y) is the number of column (resp line)
    private final int X = 6;
    private final int Y = 7;
    //The matrix of the Grille
    //Use getCase(int x, int y) method
    private final Case grille [] ;
    //A boolean present to check if the grille were fulled before start
    private boolean asBeenFulled;
    
    private Return completeSolution = null;

    
    
    
    private class Return {
        public int score;
        public ArrayList<Couple> list;
        
        
        public Return(){
            score = 0;
            list = new ArrayList<>();
        }
        
        private class Couple {
            public int first;
            public int second;
            
            public Couple(int first, int second){
                this.first = first;
                this.second = second;
            }
        }
        
        public void addNewCouple(int first, int second){
            list.add(0, new Couple(first, second));
        }       
        
        public int getFirstFromIndice (int indice){
            return list.get(indice).first;
        }
        
        public int getSecondFromIndice (int indice){
            return list.get(indice).second;
        }
        
        @Override
        public Return clone(){
            Return clone = new Return();
            clone.score = this.score;
            clone.list = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                clone.list.add(i, list.get(i));
            }
            return clone;
        }
        
        public void display(Long hash){
            System.out.println("Score : "+score+" with hash : "+ hash.toString());
            System.out.print("List :");
            for (int i = 0; i < list.size(); i++){
                System.out.print(list.get(i).first+" "+list.get(i).second+" / ");
            }
            System.out.println();
            
        }

    }
    
    
    /**
     * Constructor
     * Initialize all the requested data for Grille
     */    
    public Grille(){
        hashtable = new Hashtable<Long, Return>();
        hashtable.put(0L, new Return());
        grille = new Case[X*Y];
        Long pow = 1L;
        for (int i = 0; i<X*Y; i++){
            grille[i] = new Case(pow, i);
            pow *=  2L;
        }
        asBeenFulled = false;
    }
    
    
    
    /**
     * Note: value for empty gille is 0L
     * @return the hash of the current grille
     */
    public Long calculateHash(){
        Long sum = 0L;
        Long pow = 1L;
        for (int i = 0; i < (X*Y); i++){
            if(grille[(X*Y)-(i+1)].isHere())
                sum = new Long(pow.longValue() + sum.longValue()); 
            pow *= 2L;
        } 
        return sum;
    }
    
    /**
     * Give the score of the configuration without cases A and B
     * @param A the indice of the 1st case
     * @param B the indice of the 2nd case
     * @return the score with the under grille, and the list of action to do
     */
    
    
    private Return calculateScoreWith2Case (int A, int B){
        Return ret, //variable for the return of this function
                retRecurse; // returned variable for recursive call
        Long hashWithoutAB; // hash of returned grille, used for recursive call
        
        //Unaffect those 2 cases to have a hash like their aren't here
        grille[A].makesNotHere();
        grille[B].makesNotHere();
        hashWithoutAB = this.calculateHash();
        if (hashtable.containsKey(hashWithoutAB)){
            //case this configuration were even calculated
            //the best result for recursive sub-grille is know
            retRecurse = hashtable.get(hashWithoutAB);
            //System.out.println("Reuse with index "+A+" "+B);
            //ret.display((long) hashWithAB);
            //System.out.println();
        } else {
            //case this configuration need to be calculated
            retRecurse = this.calculateRecursively();    
            //System.out.println("New recursiv call with "+A+" "+B+ " and hash : "+hashWithoutAB);
            //ret.display((long) hashWithAB);
            //System.out.println();
            hashtable.put(hashWithoutAB, retRecurse);
        }
            
        //clone the solution for returning of this function
        ret = retRecurse.clone();
        //add to score the score of this 2 cases
        ret.score += grille[A].getPiece().calculateScore(
                grille[B].getPiece());
        //add this couple to th list
        ret.addNewCouple(A, B);

        //Reaffect the 2 cases
        grille[A].makesHereBack();
        grille[B].makesHereBack();
        
        return ret;
    }

  
    /**
     * Function to calculate the two case to select for a grille given
     * @return the Return solution with the hugher score for this grille 
     */
    private Return calculateRecursively(){
        Return tempMaxSolution = new Return();
        Return returnRecursive;
        
        Case    caseFirst,          //the first case of the two
                caseSecondBot,            //the case direclty under the first
                caseSecondBotThenRight,   //the case on right of a free case at bot
                caseSecondTop,            //the case at top (not used for recursiv calls)
                caseSecondTopThenRight,   //the case accessible by top then right
                caseSecondRight,          //the case direclty at right of the first
                caseSecondRightThenTop,   //the case on top of a free case at right
                caseSecondRightThenBot;   //the case on bot of a free case at right
                //NB : others access from second case are given by changing the first
        
        //for each 1st case clicked
        for (int first = 0; first<X*Y; first++){
            //check if this case is free
            if (grille[first].isHere()){
                caseFirst = grille[first];
                //System.out.println("New value for first "+caseFirst.getIndice());
            } else {
                continue; //unneeded case consideration
            }
         
            //
            // START OF LOOP FOR BOT ACCESS
            //
            //loop to get all cases accessible by bot
            try {
                caseSecondBot = grille[caseFirst.getBotCaseIndice()];
            } catch (WrongAccessException w){caseSecondBot = null;}
            while (caseSecondBot != null && ! caseSecondBot.isHere()) {                
                //loop to get accessible by bot then right
                //
                // START OF LOOP FOR BOT THEN RIGHT ACCESS
                //
                try {
                    caseSecondBotThenRight = grille[caseSecondBot.getRightCaseIndice()];
                } catch (WrongAccessException w){caseSecondBotThenRight = null;}
                while (caseSecondBotThenRight != null && ! caseSecondBotThenRight.isHere()) {
                    //search for a able right case
                    try{
                        caseSecondBotThenRight = grille[caseSecondBotThenRight.getRightCaseIndice()];
                    } catch (WrongAccessException w){caseSecondBotThenRight = null;}
                }
                //check if there is a case at bot then right is able to recusriv call
                //
                // FIND A CASE BY BOT THEN RIGHT
                //
                if (caseSecondBotThenRight != null){
                    returnRecursive = this.calculateScoreWith2Case(  
                                        caseFirst.getIndice(),
                                        caseSecondBotThenRight.getIndice());
                    if (returnRecursive.score > tempMaxSolution.score){
                        tempMaxSolution = returnRecursive;
                    }
                }
                           
                //
                // NEW BOT CASE
                //
                try{
                    caseSecondBot = grille[caseSecondBot.getBotCaseIndice()];
                } catch (WrongAccessException w){caseSecondBot = null;}
            }
            //check if there is a case at bot able to recusriv call
            if (caseSecondBot != null && caseSecondBot.isHere()){
                //
                // SECOND CASE FOUND DIRECTLY IN BOT
                //
                returnRecursive = this.calculateScoreWith2Case(  
                                    caseFirst.getIndice(),
                                    caseSecondBot.getIndice());
                if (returnRecursive.score > tempMaxSolution.score){
                    tempMaxSolution = returnRecursive;
                }
            }
         
            
            
            //
            // START OF LOOP FOR TOP ACCESS
            //
            //loop to get all cases accessible by top
            try {
                caseSecondTop = grille[caseFirst.getTopCaseIndice()];
            } catch (WrongAccessException w){caseSecondTop = null;}
            while (caseSecondTop != null &&  caseSecondTop.isHere()) {
                //
                // START OF LOOP FOR TOP THEN RIGHT ACCESS
                //              
                //loop to get accessible by top then right
                try {
                    caseSecondTopThenRight = grille[caseSecondTop.getRightCaseIndice()];
                } catch (WrongAccessException w){caseSecondTopThenRight = null;}
                while (caseSecondTopThenRight != null && ! caseSecondTopThenRight.isHere()) {
                    //search for a able right case
                    try{
                        caseSecondTopThenRight = grille[caseSecondTopThenRight.getRightCaseIndice()];
                    } catch (WrongAccessException w){caseSecondTopThenRight = null;}
                }
                //check if there is a case at top then right is able to recusriv call
                if (caseSecondTopThenRight != null && caseSecondTopThenRight.isHere()){
                    returnRecursive = this.calculateScoreWith2Case(  
                                        caseFirst.getIndice(),
                                        caseSecondTopThenRight.getIndice());
                    if (returnRecursive.score > tempMaxSolution.score){
                        tempMaxSolution = returnRecursive;
                    }
                }
                
                           
                //change the top case
                try{
                    caseSecondTop = grille[caseSecondTop.getTopCaseIndice()];
                } catch (WrongAccessException w){caseSecondTop = null;}
            }
            
            
            
            //
            // START OF LOOP FOR RIGHT ACCESS
            //
            //loop to get all cases accessible by top
            try {
                caseSecondRight = grille[caseFirst.getRightCaseIndice()];
            } catch (WrongAccessException w){caseSecondRight = null;}
            while (caseSecondRight != null && ! caseSecondRight.isHere()) {
                //
                // START OF LOOP FOR RIGTH THEN TOP ACCESS
                //                   
                //loop to get accessible by right then top
                try {
                    caseSecondRightThenTop = grille[caseSecondRight.getTopCaseIndice()];
                } catch (WrongAccessException w){caseSecondRightThenTop = null;}
                while (caseSecondRightThenTop != null && ! caseSecondRightThenTop.isHere()) {
                    //search for a able right case
                    try{
                        caseSecondRightThenTop = grille[caseSecondRightThenTop.getTopCaseIndice()];
                    } catch (WrongAccessException w){caseSecondRightThenTop = null;}
                }
                //check if there is a case at right then top is able to recusriv call
                if (caseSecondRightThenTop != null && caseSecondRightThenTop.isHere()){
                    returnRecursive = this.calculateScoreWith2Case(  
                                        caseFirst.getIndice(),
                                        caseSecondRightThenTop.getIndice());
                    if (returnRecursive.score > tempMaxSolution.score){
                        tempMaxSolution = returnRecursive;                       
                    }
                }
                
                //
                // START OF LOOP FOR RIGTH THEN BOTTOM ACCESS
                //                               
                //loop to get accessible by right then bot
                try {
                    caseSecondRightThenBot = grille[caseSecondRight.getBotCaseIndice()];
                } catch (WrongAccessException w){caseSecondRightThenBot = null;}
                while (caseSecondRightThenBot != null && ! caseSecondRightThenBot.isHere()) {
                    //search for a able right case
                    try{
                        caseSecondRightThenBot = grille[caseSecondRightThenBot.getBotCaseIndice()];
                    } catch (WrongAccessException w){caseSecondRightThenBot = null;}
                }
                //check if there is a case at right then top is able to recusriv call
                if (caseSecondRightThenBot != null){
                    returnRecursive = this.calculateScoreWith2Case(  
                                        caseFirst.getIndice(),
                                        caseSecondRightThenBot.getIndice());
                    if (returnRecursive.score > tempMaxSolution.score){
                        tempMaxSolution = returnRecursive;
                    }
                }
                
                                           
                //change the right case
                try{
                    caseSecondRight = grille[caseSecondRight.getRightCaseIndice()];
                } catch (WrongAccessException w){caseSecondRight = null;}
            }
            
            //check if there is a case at bot able to recusriv call
            if (caseSecondRight != null){
                returnRecursive = this.calculateScoreWith2Case(  
                                    caseFirst.getIndice(),
                                    caseSecondRight.getIndice());
                if (returnRecursive.score > tempMaxSolution.score){
                    tempMaxSolution = returnRecursive;
                }
            }
           
            
            
        }
        return tempMaxSolution;
    }
  
    
    /**
     * Affect a new Piece to a case by the case indice
     * @param p
     * @param indice 
     */
    public void affectPieceToCase (Piece p, int indice){
        grille[indice].affectPiece(p);
    }
    
    
    /**
     * Check if the grille have been completed, modify asBeenFulled if true
     */
    private void checkFull(){
        for (int i = 0; i<X*Y; i++){
            if (! grille[i].isHere()) {
                return;
            }
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
        
        if (completeSolution == null){
            completeSolution = this.calculateRecursively();
        }
        
        return completeSolution.score;
    }
    
    
    public int [] getListToPlay() throws MissingInitializationException{
        
        int array [] = new int [44];
        
        this.checkFull();
        if (! asBeenFulled)
            throw new MissingInitializationException();
        
        if (completeSolution == null){
            completeSolution = this.calculateRecursively();
        }
        
        System.out.println("DEBUG : nombre de couple solution : "+completeSolution.list.size());
        
        for (int i = 0; i < completeSolution.list.size(); i++) {
            array[2*i] = completeSolution.getFirstFromIndice(i);
            array[2*i+1] = completeSolution.getSecondFromIndice(i);
        }
        
        return array;
    }
    
     
}
