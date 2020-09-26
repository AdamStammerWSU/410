package edu.wsu.se;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Player{
	/*
	 * Contains
	 * (A)Player Constructor
	 * (B)Dummy Constructor
	 * (C)Add to Hand
	 * (D)Display Hand
	 * (E)Misc. Setters and Getters
	 */
	Set<Integer> hand = new TreeSet<Integer>();
	int number;
	int wins;
	String ip_address;
////////////////////////////////////////////////(A)Constructor
	public Player(int number, String ip_address)
	{
		//Basic building blocks of player
		this.number = number;
		this.ip_address = ip_address;
		wins = 0;
		//Makes sure the new hand has all 3 randomized different numbers 
		int counter = 0;
		Random rand = new Random();
		while(counter < 3){
			Set <Integer> newNumber = new TreeSet<Integer>();
			int newest = rand.nextInt(20)+1;
			newNumber.add(newest);
			if(hand.containsAll(newNumber) == false) {
				hand.add(newest);
				counter++;
			}
		}
	}
////////////////////////////////////////////////(B)Dummy Constructor
	//Player used to test game
	public Player(int number, int dummyNumber, boolean x)
	{
		this.number = number;
		hand.add(1);
		hand.add(2);
		hand.add(dummyNumber);
	}
////////////////////////////////////////////////(C)Add to Hand
	//Add to players hand
	public void addNumber(int number) 
	{
		hand.add(number);
	}
////////////////////////////////////////////////(D)Display Hand
	//Display hand
	public String displayHand()
	{
		String value = "[ ";
		for (int i : hand) {
		    value += (" "+ i);
		}
		value += " ]";
		return value;
	}
	///////////////////////////////////////////////(E)Setters and Getters
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public Set<Integer> getHand() {
		return hand;
	}
	public void setHand(Set<Integer> hand) {
		this.hand = hand;
	}	
}
