package edu.wsu.se;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Player {

	Set<Integer> hand = new TreeSet<Integer>();
	int number;
	int wins;
	
	double ip_address;
	
//////////////////////////////////////////////// Constructor
	public void Player(int number, double ip_address)
	{
		this.number = number;
		this.ip_address = ip_address;
		wins = 0;
		/*
		Random rand = new Random();
			for(int i = 0; i < 3; i++)
			{
				hand.add(rand.nextInt(20)+1);
			}	
			*/
		
	}
	public void addNumber(int number) 
	{
		hand.add(number);
	}
	/////////////////////////////////////////////// Setters and Getters
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getIp_address() {
		return ip_address;
	}

	public void setIp_address(double ip_address) {
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
	public String displayHand()
	{
		
		String value = "[ ";
		for (int i : hand) 
		{
		    value += (" "+ i);
		}
		value += " ]";
		return value;
	}
	
	
	
}
