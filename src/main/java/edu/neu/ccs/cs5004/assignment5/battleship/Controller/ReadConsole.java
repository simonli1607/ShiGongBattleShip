package edu.neu.ccs.cs5004.assignment5.battleship.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import edu.neu.ccs.cs5004.assignment5.battleship.Enums.Direction;

/**
 * Represents a read console to get the input data.
 */
public class ReadConsole {
  private static String input;


  public static void main(String[] args) {

    ReadConsole.readFromConsole(args);

  }
  
    public static void readFromConsole(String[] args) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {

			input = br.readLine();

			if ("q".equals(input)) {
				System.out.println("Exit!");
				System.exit(0);
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

  public static void wrongInput() {
    System.out.println(RealGame.ANSI_RED + "Incorrect input! Please enter again!"
        + RealGame.ANSI_RESET);
  }

  public String getInput() {
    return input;
  }

  public int[] inputLocation() {

    int[] location = new int[2];

    boolean factor = true;

    while (factor == true) {
    	ReadConsole.readFromConsole(null);

      String input = getInput();
      char[] str = input.toCharArray();

      switch (input.length()) {
        case 2:
          if (str[0] - 'A' >= 0 && str[0] <= 'J' && str[1] >= '1' && str[1] <= '9') {
            location[0] = (int) str[0] - 'A';
            location[1] = (int) str[1] - '1';
            return location;
          }

        case 3:
          if (str[0] - 'A' >= 0 && str[0] <= 'J' && str[1] >= '1' && str[2] <= '0') {
            location[0] = (int) str[0] - 'A';
            location[1] = 9; //we get 10 but the index is 9
            return location;
          }

        default:
          ReadConsole.wrongInput();

      }
    }
    return location;

  }

//get a valid int (from start to end)
  public int inputNum(int start, int end){
    while (true) {
      ReadConsole.readFromConsole(null);
      if (getInput().length() == 1 && getInput().charAt(0) >= start + 48
          && getInput().charAt(0) <= end + 48) {
        return Integer.parseInt(getInput());
      } else {
        ReadConsole.wrongInput();
      }
    }
  }


}
