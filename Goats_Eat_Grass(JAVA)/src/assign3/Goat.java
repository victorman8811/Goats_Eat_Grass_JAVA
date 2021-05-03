package assign3;



public class Goat extends Creature{

	public int die_judge(){

		if(age>69 || point<=0){

			return 1;

		}

		else
			return 0;

	}
}
