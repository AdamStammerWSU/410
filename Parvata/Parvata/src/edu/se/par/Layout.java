package edu.se.par;

public class Layout {

	int inputPageCount;
	int outputPageCount;
	String layout;
	String layoutArray [][];
	
	public Layout() {
		
	}
	
	public int getInputPageCount()
	{
		return inputPageCount;	
	}
	
	public int getOutputPageCount()
	{
		return outputPageCount;
	}
	
	public String[][] getLayout()
	{
		return layoutArray;
	}
	
	public String getLayoutString()
	{
		return layout;
	}
	
	public void saveLayout()
	{
		
	}
}