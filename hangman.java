import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

class City
{
	private String storeCity[];
	private int randomCity; 
	
	public City()
	{
		
	}
	public City(String[] storeCity)
	{		
			this.storeCity = storeCity;		
	}
	
	public String getCity()
	{
		randomCity = (int) (Math.random() * storeCity.length);
		return storeCity[randomCity];
	}
}

class Game extends City
{
	private char enteredLetters[];
	public Game()
	{

	}

	public Game(String selectedCity)
	{
		enteredLetters = new char[selectedCity.length()];
		for(int i = 0; i < selectedCity.length(); i++)
		{
			enteredLetters[i] = '_';
		}
	}
	
	public void setSelectedCity(String selectedCity)
	{
		enteredLetters = new char[selectedCity.length()];
		for(int i = 0; i < selectedCity.length(); i++)
		{
			enteredLetters[i] = '_';
		}	
	}
	
	public void display()
	{
		for(int i = 0; i < enteredLetters.length; i++) 
		{
            System.out.print(enteredLetters[i]+"\t");
		}
		System.out.println();
	}
	
	public void play(String selectedCity)
	{
		
		int chancesLeft = 6;
		int flag = 0;
		boolean cityFetched = false;
		Scanner sc = new Scanner(System.in);
		
		do
		{	
			flag = 0;
			System.out.println("Enter letter: ");
			char letterEntered = sc.nextLine().charAt(0);
			letterEntered = Character.toUpperCase(letterEntered);
			
			for(int i = 0; i < selectedCity.length(); i++) 
			{
	            char letter = selectedCity.charAt(i);
	            if(letter == letterEntered)
	            {
	            	enteredLetters[i] = letterEntered;
	            	flag = 1;
	            }
			}
			if(flag == 0 && chancesLeft!=0)
			{
	        	chancesLeft--;
	        	System.out.println("Letter does not match !");
	        	System.out.println(chancesLeft+" chances remaining");
	        }	
			if(chancesLeft == 0 )
			{
				System.out.println("You Lose!");
				System.out.println();
				break;	
			}
				
			display();
			if(String.valueOf(enteredLetters).equalsIgnoreCase(selectedCity))
			{
				cityFetched=true;
				System.out.println("Congratulations !! You guessed it right.");
				System.out.println();
			}
			
		}while(!cityFetched);
	}
}


public class HangIO
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("\t* H A N G M A N  G A M E *");
		System.out.println();
		System.out.println("Guess the Indian City: ");
		try 
		{			
			BufferedReader in = new BufferedReader(new FileReader("city.txt"));
			String str;
			List<String> list = new ArrayList<String>();
			while((str = in.readLine()) != null)
			{
				list.add(str);
			}
			String[] storeCity = list.toArray(new String[0]);
										
			City c = new City(storeCity);
			
			String selectedCity = c.getCity();						
			Game g = new Game(selectedCity);
			g.display();
			g.play(selectedCity);
			
			char ans;
			do
			{
				System.out.println("Try another? (Y/N): ");
				ans = sc.nextLine().charAt(0);
				ans = Character.toUpperCase(ans);
				switch(ans)
				{
					case 'Y':				
						selectedCity = c.getCity();						
						g.setSelectedCity(selectedCity);
						g.display();
						g.play(selectedCity);
						break;
						
					case 'N':
						System.out.println("You Quit");
						break;	
						
					default:
						System.out.println("Enter valid option");
				}
			}while(ans !='N');		
			
			
			
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
		
	}

	
}
