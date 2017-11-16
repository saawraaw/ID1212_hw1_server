/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmanserver;

import java.net.Socket;

/**
 *
 * @author Sarah
 */
public class Controller implements Runnable { 
    private final Game game;
    private final ServerThread server ;
    private boolean stopPlaying = false ; 

    
    public Controller (Socket s) {
        game = new Game ();
        server = new ServerThread (s);
        
    }
    @Override
    public void run() {
        server.sendDataToClient("Starting a New Game...\n");
        while (!stopPlaying) {
            if(!game.getWaitForNewGame())
                server.sendDataToClient(game.getState());
            String in = server.readClientData () ;
            in = in.toLowerCase() ;
            interpreter (in);
        }
        server.disconnect();
    }
    
    private void interpreter (String in) {
        if (in.equals("quit game")) {
            server.sendDataToClient("Quiting Game...\n");
                stopPlaying = true ;
        } else if (game.getGameFinished()) {
            if (in.equals("play")) {
                server.sendDataToClient("Starting a New Game...\n");
                game.newGame();
            } else {
                server.sendDataToClient("Invalid Command\n");
                server.sendDataToClient("Enter \"Play\" To Play Again. "
                        + "Otherwise, Enter \"Quit Game\" To Exit.\n" );
            }
        } else {
            game.play(in);
        }
            
        
    }
    

}
