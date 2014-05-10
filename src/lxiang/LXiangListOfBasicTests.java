/* @document LXiang.java, the main class
 * 
 * @author arnaud.panaiotis@gmx.fr
 * 
 * @about : 
 * This is a free software made by a Kado² user for other Kado² user
 * It is an help to found the best combinason for the flash game Xiang Xiang
 * 
 * @info :
 * This class is a main class to test the system part with a grille fulled 
 * "by the hand"
 */
package lxiang;

/**
 * @author Arno
 * @date 08/02/2014
 */

import error.MissingInitializationException;
import java.util.ArrayList;
import system.*;

public class LXiangListOfBasicTests {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Grille g = new Grille();


        
                //FULL THE GRILLE
        // Line 1
        g.affectPieceToCase(new Piece(  Enums.Color.YELLOW,
                                        Enums.Forme.ROND,
                                        Enums.Inside.POINTS), 0);
        g.affectPieceToCase(new Piece(  Enums.Color.BLUE,
                                        Enums.Forme.ROND,
                                        Enums.Inside.WAVE), 1);
        g.affectPieceToCase(new Piece(  Enums.Color.BLUE,
                                        Enums.Forme.SQUARE,
                                        Enums.Inside.CROSS), 2);
        g.affectPieceToCase(new Piece(  Enums.Color.YELLOW,
                                        Enums.Forme.TRIANGLE,
                                        Enums.Inside.SQUARE), 3);
        g.affectPieceToCase(new Piece(  Enums.Color.GREEN,
                                        Enums.Forme.SQUARE,
                                        Enums.Inside.TRIANGLE), 4);
        g.affectPieceToCase(new Piece(  Enums.Color.YELLOW,
                                        Enums.Forme.TRIANGLE,
                                        Enums.Inside.SQUARE), 5);
        
        // Line 2
        g.affectPieceToCase(new Piece(  Enums.Color.BLUE,
                                        Enums.Forme.SQUARE,
                                        Enums.Inside.POINTS), 6);
        g.affectPieceToCase(new Piece(  Enums.Color.GREEN,
                                        Enums.Forme.SQUARE,
                                        Enums.Inside.WAVE), 7);
        g.affectPieceToCase(new Piece(  Enums.Color.GREEN,
                                        Enums.Forme.SQUARE,
                                        Enums.Inside.SQUARE), 8);
        g.affectPieceToCase(new Piece(  Enums.Color.YELLOW,
                                        Enums.Forme.ROND,
                                        Enums.Inside.TRIANGLE), 9);
        g.affectPieceToCase(new Piece(  Enums.Color.RED,
                                        Enums.Forme.TRIANGLE,
                                        Enums.Inside.CROSS), 10);
        g.affectPieceToCase(new Piece(  Enums.Color.BLUE,
                                        Enums.Forme.ROND,
                                        Enums.Inside.POINTS), 11);
        
        // Line 3
        g.affectPieceToCase(new Piece(  Enums.Color.GREEN,
                                        Enums.Forme.ROND,
                                        Enums.Inside.WAVE), 12);
        g.affectPieceToCase(new Piece(  Enums.Color.BLUE,
                                        Enums.Forme.SQUARE,
                                        Enums.Inside.TRIANGLE), 13);
        g.affectPieceToCase(new Piece(  Enums.Color.BLUE,
                                        Enums.Forme.TRIANGLE,
                                        Enums.Inside.TRIANGLE), 14);
        g.affectPieceToCase(new Piece(  Enums.Color.YELLOW,
                                        Enums.Forme.TRIANGLE,
                                        Enums.Inside.SQUARE), 15);
        g.affectPieceToCase(new Piece(  Enums.Color.BLUE,
                                        Enums.Forme.ROND,
                                        Enums.Inside.SQUARE), 16);
        g.affectPieceToCase(new Piece(  Enums.Color.RED,
                                        Enums.Forme.SQUARE,
                                        Enums.Inside.SQUARE), 17);
        
        // Line 4
        g.affectPieceToCase(new Piece(  Enums.Color.RED,
                                        Enums.Forme.ROND,
                                        Enums.Inside.WAVE), 18);
        g.affectPieceToCase(new Piece(  Enums.Color.RED,
                                        Enums.Forme.SQUARE,
                                        Enums.Inside.SQUARE), 19);
        g.affectPieceToCase(new Piece(  Enums.Color.YELLOW,
                                        Enums.Forme.SQUARE,
                                        Enums.Inside.CROSS), 20);
        g.affectPieceToCase(new Piece(  Enums.Color.GREEN,
                                        Enums.Forme.SQUARE,
                                        Enums.Inside.SQUARE), 21);
        g.affectPieceToCase(new Piece(  Enums.Color.YELLOW,
                                        Enums.Forme.SQUARE,
                                        Enums.Inside.WAVE), 22);
        g.affectPieceToCase(new Piece(  Enums.Color.BLUE,
                                        Enums.Forme.TRIANGLE,
                                        Enums.Inside.CROSS), 23);
        
        // Line 5
        g.affectPieceToCase(new Piece(  Enums.Color.GREEN,
                                        Enums.Forme.TRIANGLE,
                                        Enums.Inside.CROSS), 24);
        g.affectPieceToCase(new Piece(  Enums.Color.BLUE,
                                        Enums.Forme.ROND,
                                        Enums.Inside.POINTS), 25);
        g.affectPieceToCase(new Piece(  Enums.Color.RED,
                                        Enums.Forme.TRIANGLE,
                                        Enums.Inside.TRIANGLE), 26);
        g.affectPieceToCase(new Piece(  Enums.Color.GREEN,
                                        Enums.Forme.SQUARE,
                                        Enums.Inside.POINTS), 27);
        g.affectPieceToCase(new Piece(  Enums.Color.BLUE,
                                        Enums.Forme.ROND,
                                        Enums.Inside.POINTS), 28);
        g.affectPieceToCase(new Piece(  Enums.Color.BLUE,
                                        Enums.Forme.TRIANGLE,
                                        Enums.Inside.TRIANGLE), 29);
        
        // Line 6
        g.affectPieceToCase(new Piece(  Enums.Color.YELLOW,
                                        Enums.Forme.ROND,
                                        Enums.Inside.TRIANGLE), 30);
        g.affectPieceToCase(new Piece(  Enums.Color.GREEN,
                                        Enums.Forme.SQUARE,
                                        Enums.Inside.WAVE), 31);
        g.affectPieceToCase(new Piece(  Enums.Color.RED,
                                        Enums.Forme.SQUARE,
                                        Enums.Inside.TRIANGLE), 32);
        g.affectPieceToCase(new Piece(  Enums.Color.GREEN,
                                        Enums.Forme.TRIANGLE,
                                        Enums.Inside.WAVE), 33);
        g.affectPieceToCase(new Piece(  Enums.Color.YELLOW,
                                        Enums.Forme.TRIANGLE,
                                        Enums.Inside.SQUARE), 34);
        g.affectPieceToCase(new Piece(  Enums.Color.YELLOW,
                                        Enums.Forme.TRIANGLE,
                                        Enums.Inside.WAVE), 35);
        
        // Line 7
        g.affectPieceToCase(new Piece(  Enums.Color.GREEN,
                                        Enums.Forme.TRIANGLE,
                                        Enums.Inside.TRIANGLE), 36);
        g.affectPieceToCase(new Piece(  Enums.Color.YELLOW,
                                        Enums.Forme.SQUARE,
                                        Enums.Inside.WAVE), 37);
        g.affectPieceToCase(new Piece(  Enums.Color.YELLOW,
                                        Enums.Forme.ROND,
                                        Enums.Inside.SQUARE), 38);
        g.affectPieceToCase(new Piece(  Enums.Color.GREEN,
                                        Enums.Forme.SQUARE,
                                        Enums.Inside.WAVE), 39);
        g.affectPieceToCase(new Piece(  Enums.Color.YELLOW,
                                        Enums.Forme.SQUARE,
                                        Enums.Inside.CROSS), 40);
        g.affectPieceToCase(new Piece(  Enums.Color.BLUE,
                                        Enums.Forme.TRIANGLE,
                                        Enums.Inside.TRIANGLE), 41);
        
        
        
        System.out.println("Valeur du hash "+g.calculateHash());
        
        
        
    }
}
