/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack_02;

import java.util.Scanner;

/**
 *
 * @author gubotdev
 */
public class Dealer {
    
    private Hand dealerHand = new Hand();
    
    private Player[] myPlayers;
    private Deck myDeck = new Deck();
    
    Scanner scan = new Scanner(System.in);
    
    public Dealer (int numOfPlayers){
        
    }
    
    public Dealer(){
        System.out.println("How many want to play?");
        int num = scan.nextInt();
        initPlayers(num);
    }
    
    public void playGame(){
        this.dealOutOpeningHand();
        this.playOutPlayerHands();
        this.playOutDealerHand();
        this.declareWinners();
    }
    
    private void initPlayers(int numOfPlayers){
        myPlayers = new Player[numOfPlayers];
        for(int i = 0; i < myPlayers.length; i++){
            System.out.println("Player " + (i+1) + " enter name: ");
            String name = scan.next();
            if(name.equals("")){
                myPlayers[i] = new Player(name);
            }else{
                myPlayers[i] = new Player(name);
            }
            myPlayers[i] = new Player(name);
        }
    }
    
    public void dealOutOpeningHand(){
        for(int i = 0; i < 2; i++){
            for(Player currPlayer : myPlayers){
                currPlayer.getMyHand().addCard(myDeck.dealCard());
            }
            dealerHand.addCard(myDeck.dealCard());
        }
    }
    
    public void playOutPlayerHands(){
        for(Player currPlayer : myPlayers){
            System.out.println("\n" + currPlayer.getName() + "'s Hand");
            currPlayer.getMyHand().printHand();
            while(currPlayer.getMyHand().getNumOfCards() < 5 && 
                    currPlayer.getMyHand().getScore() < 21){
                 System.out.println(currPlayer.getName() + " Wana hit? (y/n)" );
                 char opt = scan.next().toLowerCase().charAt(0);
                 System.out.println("\n");
                 if(opt == 'y'){
                     currPlayer.getMyHand().addCard(myDeck.dealCard());
                 }else{
                     break;
                 }
                 currPlayer.getMyHand().printHand();
            }
        }
    }
    
    public void playOutDealerHand(){
        while(dealerHand.getScore() < 16 && dealerHand.getNumOfCards() < 5){
            dealerHand.addCard(myDeck.dealCard());
        }
        System.out.println("Dealer's Hand");
        dealerHand.printHand();
    }
    
    public void declareWinners(){
        
        for(int i = 0; i < myPlayers.length; i++){
            Player currPlayer = myPlayers[i];
            System.out.println(currPlayer.getName() + "'s Hand");
            currPlayer.getMyHand().printHand();
            if(dealerHand.getScore() > 21 || 
                    currPlayer.getMyHand().getScore() > 21){
                if(currPlayer.getMyHand().getScore() > 21){
                    System.out.println(currPlayer.getName() + 
                            " you're Busted Buster!");
                }else{
                    System.out.println(currPlayer.getName() +
                            " the dealer Busted, you win!");
                }
            }else if(dealerHand.getScore()==21 || 
                    dealerHand.getNumOfCards() > 4){
                System.out.println(currPlayer.getName() +
                        " the Dealer got lucky");
            }else if(currPlayer.getMyHand().getNumOfCards() > 4){
                System.out.println(currPlayer.getName() + 
                        " there goes the last luck you had, you win!");
            }else if(currPlayer.getMyHand().getScore() > dealerHand.getScore()){
                System.out.println(currPlayer.getName() + "The battle was won");
            }else{
                System.out.println(currPlayer.getName() + "YA DONE GOOFED!");
            }
        }
        
    }
}
