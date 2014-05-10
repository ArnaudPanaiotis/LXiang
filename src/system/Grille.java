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
 * @date 09/02/2014
 */
public class Grille {
   
    //hashtable : a table of hash used to remove some redoundant recursiv calls
    private static Hashtable<Long, Return> hashtable;
    // X (resp Y) is the number of column (resp line)
    private final int X = 6;
    private final int Y = 7;
    //The matrix of the Grille
    //Use getCase(int x, int y) method
    private Case grille [] ;
    //A boolean present to check if the grille were fulled before start
    private boolean asBeenFulled;
    
    private Return completeSolution = null;

    
    
    
    private class Return {
        public int score = 0;
        public ArrayList<Couple> list;
        
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
        
        public void display(long hash){
            System.out.println("Score : "+score+" with hash : "+(long) hash);
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
        grille = new Case[X*Y];
        long pow = 1L;
        for (int i = 0; i<X*Y; i++){
            grille[i] = new Case(pow, i);
            pow *= 2L;
        }
        asBeenFulled = false;
    }
    
    
    
    /**
     * @return the hash of the current grille
     */
    private long calculateHash(){
        long tmp = 0L;
        long pow = 1L;
        for (int i = 0; i<42/*X*Y*/; i++){
            if(! grille[i].isAble())
                tmp = (long)pow + (long)tmp;
            pow *= 2;
        } 
        return tmp;
    }
    
    /**
     * Give the score of the configuration without cases A and B
     * @param A the indice of the &st case
     * @param B the indice of the 2nd case
     * @return the score with the under grille, and the list of action to do
     */
    private Return calculateScoreWith2Case (int A, int B){
        Return ret, retBis;
        long hashWithoutAB, hashWithAB;
        
        hashWithAB = (long) this.calculateHash();
        //Unaffect those 2 cases to have a hash like their aren't here
        grille[A].unable();
        grille[B].unable();
        hashWithoutAB = (long) this.calculateHash();
        if (hashtable.containsKey(hashWithoutAB)){
            //case this configuration were even calculated
            ret = hashtable.get((long) hashWithoutAB);
            /*System.out.println("Add from hash "+A+" "+B);
            ret.display((long) hashWithAB);
            System.out.println();*/
            
        } else {
            //case this configuration need to be calculated
            ret = this.calculateRecursively();    
            /*System.out.println("Add from recursiv call "+A+" "+B);
            ret.display((long) hashWithAB);
            System.out.println();*/
            hashtable.put((long) hashWithoutAB, ret);
        }
            
        //clone the solution for returning
        retBis = ret.clone();
        retBis.score += grille[A].getPiece().calculateScore(
                grille[B].getPiece());
        retBis.addNewCouple(A, B);

        //Reaffect the 2 cases
        grille[A].able();
        grille[B].able();
        
        return retBis;
    }
  
    /**
     * Function to calculate the two case to select for a grille given
     * @return the Return solution with the hugher score for this grille 
     */
    private Return calculateRecursively(){
        Return tempMaxSolution = new Return();
        tempMaxSolution.list = new ArrayList();
        
        Return compareTmp = new Return();
        compareTmp.score = 0;
        
        Case    caseFirst,          //the first case of the two
                caseBot,            //the case direclty under the first
                caseBotThenRight,   //the case on right of a free case at bot
                caseTop,            //the case at top (not used for recursiv calls)
                caseTopThenRight,   //the case accessible by top then right
                caseRight,          //the case direclty at right of the first
                caseRightThenTop,   //the case on top of a free case at right
                caseRightThenBot;   //the case on bot of a free case at right
                //NB : others access to second case are given by changing the first
        //for each 1st case clicked
        for (int first = 0; first<X*Y; first++){
            //check if this case is free
            if (grille[first].isAble()){
                caseFirst = grille[first];
            } else {
                continue; //unneeded case consideration
            }
            
            
            
            
            /*
             * START OF LOOP FOR BOT ACCESS
             */
            //loop to get all cases accessible by bot
            try {
                caseBot = grille[caseFirst.getBotCaseIndice()];
            } catch (WrongAccessException w){caseBot = null;}
            while (caseBot != null && ! caseBot.isAble()) {
                
                //loop to get accessible by bot then right
                try {
                    caseBotThenRight = grille[caseBot.getRightCaseIndice()];
                } catch (WrongAccessException w){caseBotThenRight = null;}
                while (caseBotThenRight != null && ! caseBotThenRight.isAble()) {
                    //search for a able right case
                    try{
                        caseBotThenRight = grille[caseBotThenRight.getRightCaseIndice()];
                    } catch (WrongAccessException w){caseBotThenRight = null;}
                }
                //check if there is a case at bot then right is able to recusriv call
                if (caseBotThenRight != null){
                    compareTmp = this.calculateScoreWith2Case(  
                                        caseFirst.getIndice(),
                                        caseBotThenRight.getIndice());
                    if (compareTmp.score > tempMaxSolution.score){
                        tempMaxSolution = compareTmp;
                    }
                }
                           
                //change the bot case
                try{
                    caseBot = grille[caseBot.getBotCaseIndice()];
                } catch (WrongAccessException w){caseBot = null;}
            }
            //check if there is a case at bot able to recusriv call
            if (caseBot != null){
                compareTmp = this.calculateScoreWith2Case(  
                                    caseFirst.getIndice(),
                                    caseBot.getIndice());
                if (compareTmp.score > tempMaxSolution.score){
                    tempMaxSolution = compareTmp;
                }
            }
            
            
            
            /*
             * START OF LOOP FOR TOP ACCESS
             */
            //loop to get all cases accessible by top
            try {
                caseTop = grille[caseFirst.getTopCaseIndice()];
            } catch (WrongAccessException w){caseTop = null;}
            while (caseTop != null && ! caseTop.isAble()) {
                
                //loop to get accessible by top then right
                try {
                    caseTopThenRight = grille[caseTop.getRightCaseIndice()];
                } catch (WrongAccessException w){caseTopThenRight = null;}
                while (caseTopThenRight != null && ! caseTopThenRight.isAble()) {
                    //search for a able right case
                    try{
                        caseTopThenRight = grille[caseTopThenRight.getRightCaseIndice()];
                    } catch (WrongAccessException w){caseTopThenRight = null;}
                }
                //check if there is a case at top then right is able to recusriv call
                if (caseTopThenRight != null){
                    compareTmp = this.calculateScoreWith2Case(  
                                        caseFirst.getIndice(),
                                        caseTopThenRight.getIndice());
                    if (compareTmp.score > tempMaxSolution.score){
                        tempMaxSolution = compareTmp;
                    }
                }
                
                           
                //change the top case
                try{
                    caseTop = grille[caseTop.getTopCaseIndice()];
                } catch (WrongAccessException w){caseTop = null;}
            }
            
            
            
            /*
             * START OF LOOP FOR RIGHT ACCESS
             */
            //loop to get all cases accessible by top
            try {
                caseRight = grille[caseFirst.getRightCaseIndice()];
            } catch (WrongAccessException w){caseRight = null;}
            while (caseRight != null && ! caseRight.isAble()) {
                
                //loop to get accessible by right then top
                try {
                    caseRightThenTop = grille[caseRight.getTopCaseIndice()];
                } catch (WrongAccessException w){caseRightThenTop = null;}
                while (caseRightThenTop != null && ! caseRightThenTop.isAble()) {
                    //search for a able right case
                    try{
                        caseRightThenTop = grille[caseRightThenTop.getTopCaseIndice()];
                    } catch (WrongAccessException w){caseRightThenTop = null;}
                }
                //check if there is a case at right then top is able to recusriv call
                if (caseRightThenTop != null){
                    compareTmp = this.calculateScoreWith2Case(  
                                        caseFirst.getIndice(),
                                        caseRightThenTop.getIndice());
                    if (compareTmp.score > tempMaxSolution.score){
                        tempMaxSolution = compareTmp;                       
                    }
                }
                
                               
                //loop to get accessible by right then bot
                try {
                    caseRightThenBot = grille[caseRight.getBotCaseIndice()];
                } catch (WrongAccessException w){caseRightThenBot = null;}
                while (caseRightThenBot != null && ! caseRightThenBot.isAble()) {
                    //search for a able right case
                    try{
                        caseRightThenBot = grille[caseRightThenBot.getBotCaseIndice()];
                    } catch (WrongAccessException w){caseRightThenBot = null;}
                }
                //check if there is a case at right then top is able to recusriv call
                if (caseRightThenBot != null){
                    compareTmp = this.calculateScoreWith2Case(  
                                        caseFirst.getIndice(),
                                        caseRightThenBot.getIndice());
                    if (compareTmp.score > tempMaxSolution.score){
                        tempMaxSolution = compareTmp;
                    }
                }
                
                                           
                //change the right case
                try{
                    caseRight = grille[caseRight.getRightCaseIndice()];
                } catch (WrongAccessException w){caseRight = null;}
            }
            
            //check if there is a case at bot able to recusriv call
            if (caseRight != null){
                compareTmp = this.calculateScoreWith2Case(  
                                    caseFirst.getIndice(),
                                    caseRight.getIndice());
                if (compareTmp.score > tempMaxSolution.score){
                    tempMaxSolution = compareTmp;
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
            if (! grille[i].isAble()) {
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
