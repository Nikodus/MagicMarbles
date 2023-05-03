package Version1.magicmarbles.model;

import java.util.Random;

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



	public MMGameImpl(int width, int height) {

		this.width = width;
		this.height = height;
		this.Gamefield = new int[width][height];
		Random rand = new Random();
		for (int y = 0; y < Gamefield[1].length; y++) {
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
		// TODO Auto-generated method stub
		return MMState.RUNNING;
	}

	@Override
	public int getGamePoints() {
		// TODO Auto-generated method stub
		return score;
	}

	@Override
	public MMFieldState getFieldState(int row, int col) {


		if(Gamefield[col][row] == 0)return MMFieldState.RED;
		if(Gamefield[col][row] == 1)return MMFieldState.GREEN;
		if(Gamefield[col][row] == 2)return MMFieldState.BLUE;

		return MMFieldState.EMPTY;
	}

	public void setFieldState(int row, int col, MMFieldState state) {
		if(state == MMFieldState.RED) Gamefield[col][row] = 0;
		if(state == MMFieldState.GREEN) Gamefield[col][row] = 1;
		if(state == MMFieldState.BLUE) Gamefield[col][row] = 2;
		if(state == MMFieldState.EMPTY) Gamefield[col][row] = 3;


	}



	@Override
	public void select(int row, int col) throws MMException {
		MMFieldClear clear = new MMFieldClear(MMFieldState.EMPTY,row,col);
		clear.moveEmpty(row, col);
	}


	private class MMFieldClear {

		private MMFieldState colorstate;

		public MMFieldClear(MMFieldState colorstate, int row, int col) throws MMException {
			if(getFieldState(row, col) != MMFieldState.EMPTY)
			{
				this.colorstate = colorstate;
				clearField(row, col);

			}


		}

		public void clearField(int row, int col) throws MMException {

			try {
				if ((getFieldState(row, col) == getFieldState(row - 1, col) || colorstate == getFieldState(row - 1, col)) && getFieldState(row - 1, col) != MMFieldState.EMPTY) {
					setFieldState(row, col, MMFieldState.EMPTY);
					clearField(row - 1, col);
					if(getFieldState(row-1, col) != MMFieldState.EMPTY)this.colorstate = getFieldState(row-1, col);
					setFieldState(row-1, col, MMFieldState.EMPTY);
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
				}
			}catch (IndexOutOfBoundsException e){};
			try {
				if((getFieldState(row, col) == getFieldState(row+1,col) || colorstate == getFieldState(row + 1, col)) && getFieldState(row+1,col) != MMFieldState.EMPTY)
				{
					setFieldState(row,col,MMFieldState.EMPTY);
					clearField(row+1,col);
					if(getFieldState(row+1, col) != MMFieldState.EMPTY)this.colorstate = getFieldState(row+1, col);
					setFieldState(row+1,col,MMFieldState.EMPTY);
				}
			}catch (IndexOutOfBoundsException e){};
			try {
				if((getFieldState(row,col) == getFieldState(row,col+1) || colorstate == getFieldState(row, col+1))  && getFieldState(row,col+1) != MMFieldState.EMPTY)
				{
					setFieldState(row,col,MMFieldState.EMPTY);
					clearField(row,col+1);
					if(getFieldState(row, col+1) != MMFieldState.EMPTY)this.colorstate = getFieldState(row, col+1);
					setFieldState(row,col+1,MMFieldState.EMPTY);
				}
			}catch (IndexOutOfBoundsException e){};


		}
		public void moveEmpty(int row, int col) throws MMException {
			for (int x = Gamefield.length-1; x >0 ; x--) {
				for (int y = Gamefield[1].length-1, count = 0; y > 0 ; y--) {
					if(Gamefield[x][y] == 3)count++;
					for (int i = 0; i < count; i++) {
						for (int z = 0; Gamefield[1].length-1 > z ; z++) {
							int save = Gamefield[x][z];
							Gamefield[x][z] = Gamefield[x][z+1];
							Gamefield[x][z+1] = save;
						}
					}

				}
			}

		}
	}
}
