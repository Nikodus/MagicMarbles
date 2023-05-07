package Version1.magicmarbles.model;

import Version1.inout.In;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Implementation of the magic marbles game
  */
public class MMGameImpl implements MMGame {

	/**
	 * Constructor 
	 * @param width the width of the game board
	 * @param height the height of the game board
	 */
	private final int width;
	private final int height;
	private int score;
	private int[][] Gamefield;
	private boolean win = false;



	public MMGameImpl(int width, int height) {

		this.width = width;
		this.height = height;
		this.Gamefield = new int[height][width];
		Random rand = new Random();
		for (int y = 0; y < Gamefield[0].length; y++) {
			for (int x = 0; x < Gamefield.length; x++) {
				this.Gamefield[x][y] = rand.nextInt(3);
			}
		}
		int randint = rand.nextInt(3);

	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public MMState getGameState() {

		if(win)return MMState.END;
		return MMState.RUNNING;
	}

	@Override
	public int getGamePoints() {
		// TODO Auto-generated method stub
		return score;
	}

	@Override
	public MMFieldState getFieldState(int row, int col) {


		if(Gamefield[row][col] == 0)return MMFieldState.RED;
		if(Gamefield[row][col] == 1)return MMFieldState.GREEN;
		if(Gamefield[row][col] == 2)return MMFieldState.BLUE;

		return MMFieldState.EMPTY;
	}

	public void setFieldState(int row, int col, MMFieldState state) {
		if(state == MMFieldState.RED) Gamefield[row][col] = 0;
		if(state == MMFieldState.GREEN) Gamefield[row][col] = 1;
		if(state == MMFieldState.BLUE) Gamefield[row][col] = 2;
		if(state == MMFieldState.EMPTY) Gamefield[row][col] = 3;


	}



	@Override
	public void select(int row, int col) throws MMException {
		if(getFieldState(row, col) == MMFieldState.EMPTY) throw new MMException();
		MMFieldClear clear = new MMFieldClear(MMFieldState.EMPTY,row,col);
		clear.moveEmpty();
		win = clear.checkWin();
		score = score + (clear.getClearedfields() * clear.getClearedfields());
	}


	private class MMFieldClear {

		private MMFieldState colorstate;
		private int clearedfields;

		public MMFieldClear(MMFieldState colorstate, int row, int col) throws MMException {
			if(getFieldState(row, col) != MMFieldState.EMPTY)
			{
				this.colorstate = colorstate;
				clearedfields = 1;
				clearField(row, col);

			}


		}

		public void clearField(int row, int col){


			try {
				if ((getFieldState(row, col) == getFieldState(row - 1, col) || colorstate == getFieldState(row - 1, col)) && getFieldState(row - 1, col) != MMFieldState.EMPTY) {
					setFieldState(row, col, MMFieldState.EMPTY);
					clearField(row - 1, col);
					if(getFieldState(row-1, col) != MMFieldState.EMPTY)this.colorstate = getFieldState(row-1, col);
					setFieldState(row-1, col, MMFieldState.EMPTY);
					clearedfields++;
				}
			}
			catch (IndexOutOfBoundsException e){};
			try {
				if((getFieldState(row,col) == getFieldState(row,col-1) || colorstate == getFieldState(row, col-1)) && getFieldState(row,col-1) != MMFieldState.EMPTY)
				{
					setFieldState(row,col,MMFieldState.EMPTY);
					clearField(row,col-1);
					if(getFieldState(row, col-1) != MMFieldState.EMPTY)this.colorstate = getFieldState(row, col-1);
					setFieldState(row,col-1,MMFieldState.EMPTY);
					clearedfields++;
				}
			}catch (IndexOutOfBoundsException e){};
			try {
				if((getFieldState(row, col) == getFieldState(row+1,col) || colorstate == getFieldState(row + 1, col)) && getFieldState(row+1,col) != MMFieldState.EMPTY)
				{
					setFieldState(row,col,MMFieldState.EMPTY);
					clearField(row+1,col);
					if(getFieldState(row+1, col) != MMFieldState.EMPTY)this.colorstate = getFieldState(row+1, col);
					setFieldState(row+1,col,MMFieldState.EMPTY);
					clearedfields++;
				}
			}catch (IndexOutOfBoundsException e){};
			try {
				if((getFieldState(row,col) == getFieldState(row,col+1) || colorstate == getFieldState(row, col+1))  && getFieldState(row,col+1) != MMFieldState.EMPTY)
				{
					setFieldState(row,col,MMFieldState.EMPTY);
					clearField(row,col+1);
					if(getFieldState(row, col+1) != MMFieldState.EMPTY)this.colorstate = getFieldState(row, col+1);
					setFieldState(row,col+1,MMFieldState.EMPTY);
					clearedfields++;
				}
			}catch (IndexOutOfBoundsException e){};


		}

		//	3x4 Field
		//				 x-Axis  <-> (column) (Width)
		//				   0  1  2  3
		//	^			0  r  g  b  r
		//	|	y-Axis	1  b  r  r  g
		//	V	(row)   2  g  g  b  b
		//		(Height)
		//

		public void moveEmpty(){
			for (int x = 0; x < Gamefield[0].length; x++) {
				int count = getHeight();
				for (int y = 0; y < Gamefield.length; y++) {
					if (Gamefield[y][x] == 3) count--;
				}
				for (; count>0;count--) {
					for(int i = 1; i < Gamefield.length; i++)
					{
						if(Gamefield[i][x] == 3)
						{
							setFieldState(i,x,getFieldState(i-1,x));
							setFieldState(i-1,x,MMFieldState.EMPTY);
						}
					}
				}
			}

			ArrayList<ArrayList<Integer>> rightList = new ArrayList<ArrayList<Integer>>();


			for (int x = 0; x < Gamefield[0].length; x++) {
				ArrayList<Integer> temprightList = new ArrayList<Integer>();
				for (int y = 0; y < Gamefield.length; y++) {
					temprightList.add(Gamefield[y][x]);
				}
				rightList.add(temprightList);
			}

			for (int i = 0; i < rightList.size(); i++) {
				if(rightList.get(i).get(rightList.get(i).size()-1) == 3)
				{
					ArrayList<Integer> DummyList = new ArrayList<Integer>(rightList.get(i));
					rightList.remove(i);
					rightList.add(0,DummyList);
				}
			}

			for (int x = 0; x < rightList.size(); x++) {
				for (int y = 0; y < rightList.get(0).size(); y++) {
					Gamefield[y][x] = rightList.get(x).get(y);
				}
			}
		}

		public boolean checkWin() throws MMException {
			{
				boolean win = true;
				for (int x = 0; x < Gamefield.length; x++) {
					for (int y = 0; y < Gamefield[0].length; y++) {
						if(getFieldState(y,x) != MMFieldState.EMPTY)
						{
							win = false;
						}
					}
				}
				return win;
			}
		}

		public int getClearedfields() {
			return clearedfields;
		}
	}
}
