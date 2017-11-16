/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmanserver;

/**
 *
 * @author Sarah
 */
public class HangmanServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TCPServer server = new TCPServer () ;
        server.getConnections();
    }
    
}
