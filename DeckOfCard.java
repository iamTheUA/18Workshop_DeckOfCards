package com.bridgelabz.workshop;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

class Player {
	String name;
	Card[] cards, sd;
	public int size = 6;

	Player() {
		cards = new Card[size];
		sd = new Card[size];
	}

	void sort() {
		for (int i = 0; i < size; i++) {
			int t = cards[i].value;
			Card temp = cards[i];
			for (int j = i; j < size - 1; j++) {
				if(t<cards[j].value) {
					cards[i]=cards[j];
					cards[j]=temp;
				}
			}
		}
	}

	public void show() {

		System.out.println();
		System.out.println(this.name+": ");
		for (int i = 0; i < size; i++) {
			System.out.println(cards[i].name);
		}
	}

	
}

class Card implements Comparable<Card>  {
	Integer value;
	String name;

	Card(int v, String n) {
		this.value = v;
		this.name = n;
	}
 @Override
	public int compareTo( Card e) {
	 	return this.value.compareTo(e.value);
	}
}

public class DeckOfCard {

	static String[] rank = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King","Ace" };
	static String[] suit = { " of Clubs ", " of Diamonds ", " of Hearts ", " of Spades " };
    static String[][] deck = new String[4][16]; 
	static String[][] newDeck;
	static Stack<Card> shuffledDeck = new Stack<>();
	static Player[] players;
	static int p; 

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for(int i = 0; i<4; i ++) {
			for(int j=0; j<13; j++) {
				deck[i][j]=rank[j]+suit[i];
			}
		}
		newDeck=deck;
		System.out.println("Enter the Number of Players: ");
		p = sc.nextInt();
		if (p > 1 && p < 5) {
			sufflingDeck();
			Player[] player = new Player[p];
			for (int i = 0; i < p; i++) {
				player[i]=new Player();
				for (int j = 0; j < 6; j++) {
					player[i].cards[j] = shuffledDeck.pop();
				}
				player[i].name= "Player"+(i+1);
				Arrays.sort(player[i].cards);
	
				player[i].show();
			}
			
		} else
			System.out.println("Total Player should be 2, 3 or 4 !");
		sc.close();

	}

	public static void sufflingDeck() {
		int size=0;
		while (size < p*6) {
			shuffledDeck.add(selectCard());

			size++;
		}
	}

	public static Card selectCard() {
		Random c = new Random();
		int suit = c.nextInt(4);
		int rank = c.nextInt(13);
		if (newDeck[suit][rank] == null) {
			return selectCard();
		}
		Card t = new Card(rank, newDeck[suit][rank]);
		newDeck[suit][rank] = null;
		return t;

	}

}
