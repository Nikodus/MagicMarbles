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

	@Override
	public MMFieldState getFieldState(int row, int col, int[][] Field) {


		if(Field[row][col] == 0)return MMFieldState.RED;
		if(Field[row][col] == 1)return MMFieldState.GREEN;
		if(Field[row][col] == 2)return MMFieldState.BLUE;

		return MMFieldState.EMPTY;
	}

	public void setFieldState(int row, int col, int[][] Field,MMFieldState state) {
		if(state == MMFieldState.RED) Field[row][col] = 0;
		if(state == MMFieldState.GREEN) Field[row][col] = 1;
		if(state == MMFieldState.BLUE) Field[row][col] = 2;
		if(state == MMFieldState.EMPTY) Field[row][col] = 3;


	}



	@Override
	public void select(int row, int col) throws MMException {
		if(getFieldState(row, col, Gamefield) == MMFieldState.EMPTY) throw new MMException();
		MMFieldClear clear = new MMFieldClear(MMFieldState.EMPTY,row,col,Gamefield);
		clear.moveEmpty();
		win = clear.checkWin();
		if(clear.getClearedfields() != 1)
		{
			score = score + (clear.getClearedfields() * clear.getClearedfields());
		}
		else{throw new MMException();}

		int negscore = clear.isMovePossible();

		if(negscore==-1);
		else{
			score = score - (10*negscore);
			win = true;
		}

	}


	private class MMFieldClear {

		private MMFieldState colorstate;
		private int clearedfields;

		public MMFieldClear(MMFieldState colorstate, int row, int col, int[][] Field) throws MMException {
			if(getFieldState(row, col, Field) != MMFieldState.EMPTY)
			{
				this.colorstate = colorstate;
				clearedfields = 1;
				clearField(row, col, Field);

			}

		}

		public void clearField(int row, int col, int[][] Field){


			try {
				if ((getFieldState(row, col,Field) == getFieldState(row - 1, col, Field) || colorstate == getFieldState(row - 1, col,Field)) && getFieldState(row - 1, col,Field) != MMFieldState.EMPTY) {
					setFieldState(row, col,Field, MMFieldState.EMPTY);
					clearField(row - 1, col,Field);
					if(getFieldState(row-1, col,Field) != MMFieldState.EMPTY)this.colorstate = getFieldState(row-1, col,Field);
					setFieldState(row-1, col,Field ,MMFieldState.EMPTY);
					clearedfields++;
				}
			}
			catch (IndexOutOfBoundsException e){};
			try {
				if((getFieldState(row,col,Field) == getFieldState(row,col-1,Field) || colorstate == getFieldState(row, col-1,Field)) && getFieldState(row,col-1,Field) != MMFieldState.EMPTY)
				{
					setFieldState(row,col,Field,MMFieldState.EMPTY);
					clearField(row,col-1,Field);
					if(getFieldState(row, col-1,Field) != MMFieldState.EMPTY)this.colorstate = getFieldState(row, col-1,Field);
					setFieldState(row,col-1,Field,MMFieldState.EMPTY);
					clearedfields++;
				}
			}catch (IndexOutOfBoundsException e){};
			try {
				if((getFieldState(row, col,Field) == getFieldState(row+1,col,Field) || colorstate == getFieldState(row + 1, col,Field)) && getFieldState(row+1,col,Field) != MMFieldState.EMPTY)
				{
					setFieldState(row,col,Field,MMFieldState.EMPTY);
					clearField(row+1,col,Field);
					if(getFieldState(row+1, col,Field) != MMFieldState.EMPTY)this.colorstate = getFieldState(row+1, col,Field);
					setFieldState(row+1,col,Field,MMFieldState.EMPTY);
					clearedfields++;
				}
			}catch (IndexOutOfBoundsException e){};
			try {
				if((getFieldState(row,col,Field) == getFieldState(row,col+1,Field) || colorstate == getFieldState(row, col+1,Field))  && getFieldState(row,col+1,Field) != MMFieldState.EMPTY)
				{
					setFieldState(row,col,Field,MMFieldState.EMPTY);
					clearField(row,col+1,Field);
					if(getFieldState(row, col+1,Field) != MMFieldState.EMPTY)this.colorstate = getFieldState(row, col+1,Field);
					setFieldState(row,col+1,Field,MMFieldState.EMPTY);
					clearedfields++;
				}
			}catch (IndexOutOfBoundsException e){};


		}

		//	3x4 Field
		//
		//			Access: Array[y][x]
		//
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
							setFieldState(i,x,Gamefield,getFieldState(i-1,x,Gamefield));
							setFieldState(i-1,x,Gamefield,MMFieldState.EMPTY);
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

		public boolean checkWin(){
			{
				boolean win = true;
				for (int x = 0; x < Gamefield.length; x++) {
					for (int y = 0; y < Gamefield[0].length; y++) {
						if(getFieldState(x,y,Gamefield) != MMFieldState.EMPTY)
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

		public int isMovePossible()
		{
			int count = 0;
			int[][] Dummyfield = new int[Gamefield.length][Gamefield[0].length];
			for (int i = 0; i < Dummyfield.length; i++) {
				Dummyfield[i] = Arrays.copyOf(Gamefield[i],Gamefield[i].length);
			}
			for (int x = 0; x < Dummyfield.length; x++) {
				for (int y = 0; y < Dummyfield[0].length; y++) {
					try{
						if(getFieldState(x,y,Dummyfield) == MMFieldState.EMPTY)continue;
						MMFieldClear Dummyclear = new MMFieldClear(MMFieldState.EMPTY,x,y,Dummyfield);
						if(Dummyclear.clearedfields>1)return -1;
						else count++;
					}catch (MMException e){

					}
				}
			}
			return count;

		}
	}
}
