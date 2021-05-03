package assign3;

import java.util.Random;

/**
 * The World class is used to represent the world for simlation of artificial life
 * with goats and grasses. The world of fixed size (20x35) is expected to be 
 * initialized with the number of passes and the seed for the random number generator 
 * used for the simulation. The class will provide a mainLoop method with an argument 
 * about the interval for displaying the world to the standard output. 
 * The world consists of a 2D grid, in each of which, a creature (a grass or a goat) 
 * may reside. If no creature resides, the slot will be null.   
 * @author li
 *
 */

public class World {

	// TODO: put your data fields here

	int seed_num;
	Random rand;	

	public World(int numpass,int display,int seed){

		Creature [][] arr=new Creature[20][35];

		for(int i=0;i<20;i++){

			for(int j=0;j<35;j++){

				arr[i][j]=null;

			}
		}

		this.seed_num=seed;
		rand=new Random(this.seed_num);

		for(int i=0;i<5;i++){

			int count=0;
			int x=0;
			int y=0;

			while(count==0){

				x=rand.nextInt(20);
				y=rand.nextInt(35);

				if(arr[x][y]==null){

					count=1;

				}

			}



			arr[x][y]=new Goat();

			arr[x][y].age=0;
			arr[x][y].point=20;
			arr[x][y].judge=1;
			arr[x][y].move=0;

		}

		for(int i=0;i<10;i++){

			int count=0;
			int x=0;
			int y=0;

			while(count==0){

				x=rand.nextInt(20);
				y=rand.nextInt(35);

				if(arr[x][y]==null){

					count=1;

				}
			}


			arr[x][y]=new Grass();

			arr[x][y].age=0;
			arr[x][y].judge=0;
			arr[x][y].move=0;


		}

		show(arr);

		int count_display=0;

		for(int i=0;i<numpass;i++){

			old(arr);
			act_select(arr);
			goat_move(arr,i);
			goat_child(arr,i);
			grass_child(arr,i);
			if(count_display==display){

				show(arr);
				count_display=0;

			}
			(count_display)++;

		}	

	}



	public void show(Creature [][]arr){

		int count=0;

		System.out.print(" ");

		for(int i=0;i<35;i++){

			if(count==10){
				count=0;
			}

			System.out.print(" ");
			System.out.print(count);
			count++;
		}

		count=0;
		System.out.print("\n");

		int count_grass=0;
		int count_goat=0;
		for(int i=0;i<20;i++){

			if(count==10){
				count=0;
			}
			System.out.print(count);
			count++;

			for(int j=0;j<35;j++){

				if(arr[i][j]==null){

					System.out.print("  ");

				}

				else if((arr[i][j]).judge==1){

					count_goat++;
					System.out.print(" X");


				}

				else if((arr[i][j]).judge==0){

					count_grass++;
					System.out.print(" I");


				}

			}
			System.out.print("\n");

		}

		System.out.print("------------------------------------------------------------------------\n");


	}


	public void act_select(Creature [][]arr){

		for(int i=0;i<20;i++){

			for(int j=0;j<35;j++){

				if(arr[i][j]==null){

					continue;

				}

				else if(arr[i][j].judge==1){


					if(arr[i][j].age<50 || arr[i][j].age>55){

						arr[i][j].act=0;

					}

					else{
						arr[i][j].act=2;
					}


				}

				else if(arr[i][j].judge==0){

					if(arr[i][j].age>2 && arr[i][j].age<6){

						arr[i][j].act=4;


					}
					else{
						arr[i][j].move=(arr[i][j].move)+1;

					}

				}
			}
		}
	}



	public void old(Creature [][]arr){

		for(int i=0;i<20;i++){

			for(int j=0;j<35;j++){

				if(arr[i][j]!=null){

					int temp=0;
					(arr[i][j].age)++;

					if(arr[i][j].judge==1){

						arr[i][j].point=(arr[i][j].point)-1;

					}


					temp=arr[i][j].die_judge();
					if(temp==1){

						arr[i][j]=null;

					}

				}

			}
		}
	}

	public void grass_child(Creature [][]arr,int move_count){

		for(int i=0;i<20;i++){

			for(int j=0;j<35;j++){

				if(arr[i][j]!=null && (arr[i][j].judge)==0 && (arr[i][j].age)>2 && (arr[i][j].age)<6 && (arr[i][j].move)==move_count && (arr[i][j].act)==4){

					(arr[i][j].move)++;

					int direct=0;
					int count=0;

					while(count==0){


						direct=rand.nextInt(4);//0 is up,1 is down,2 is right,3 is left

						if(direct==0 && i-1>-1){

							count=1;

						}

						if(direct==1 && i+1<20){

							count=1;

						}

						if(direct==2 && j+1<35){

							count=1;

						}

						if(direct==3 && j-1>-1){

							count=1;

						}

					}


					if(direct==0){

						if(arr[i-1][j]==null){

							arr[i-1][j]=new Grass();
							arr[i-1][j].age=0;
							arr[i-1][j].move=move_count+1;
							arr[i-1][j].judge=0;


						}

					}

					if(direct==1){

						if(arr[i+1][j]==null){

							arr[i+1][j]=new Grass();
							arr[i+1][j].age=0;
							arr[i+1][j].move=move_count+1;
							arr[i+1][j].judge=0;

						}

					}

					if(direct==2){

						if(arr[i][j+1]==null){

							arr[i][j+1]=new Grass();
							arr[i][j+1].age=0;
							arr[i][j+1].move=move_count+1;
							arr[i][j+1].judge=0;


						}

					}

					if(direct==3){

						if(arr[i][j-1]==null){

							arr[i][j-1]=new Grass();
							arr[i][j-1].age=0;
							arr[i][j-1].move=move_count+1;
							arr[i][j-1].judge=0;

						}

					}
				}
			}
		}
	}




	public void goat_child(Creature [][]arr,int move_count){

		for(int i=0;i<20;i++){

			for(int j=0;j<35;j++){

				if(arr[i][j]!=null && (arr[i][j].judge)==1 && (arr[i][j].age)>49 && (arr[i][j].age)<56 && (arr[i][j].move)==move_count && (arr[i][j].act)==2){

					(arr[i][j].move)++;

					int direct=0;
					int count=0;


					while(count==0){


						direct=rand.nextInt(4);//0 is up,1 is down,2 is right,3 is left

						if(direct==0 && i-1>0){

							count=1;

						}

						if(direct==1 && i+1<20){

							count=1;

						}

						if(direct==2 && j+1<35){

							count=1;

						}

						if(direct==3 && j-1>0){

							count=1;

						}

					}

					if(direct==0){

						if(arr[i-1][j]!=null && arr[i-1][j].judge==0){

							arr[i-1][j]=null;

							arr[i-1][j]=new Goat();
							arr[i-1][j].age=0;
							arr[i-1][j].point=20;
							arr[i-1][j].move=move_count+1;
							arr[i-1][j].judge=1;

						}

						if(arr[i-1][j]==null){

							arr[i-1][j]=new Goat();
							arr[i-1][j].age=0;
							arr[i-1][j].point=20;
							arr[i-1][j].move=move_count+1;
							arr[i-1][j].judge=1;


						}

					}

					if(direct==1){

						if(arr[i+1][j]!=null && arr[i+1][j].judge==0){

							arr[i+1][j]=null;

							arr[i+1][j]=new Goat();
							arr[i+1][j].age=0;
							arr[i+1][j].point=20;
							arr[i+1][j].move=move_count+1;
							arr[i+1][j].judge=1;
						}

						if(arr[i+1][j]==null){

							arr[i+1][j]=new Goat();
							arr[i+1][j].age=0;
							arr[i+1][j].point=20;
							arr[i+1][j].move=move_count+1;
							arr[i+1][j].judge=1;

						}

					}

					if(direct==2){

						if(arr[i][j+1]!=null && arr[i][j+1].judge==0){

							arr[i][j+1]=null;

							arr[i][j+1]=new Goat();
							arr[i][j+1].age=0;
							arr[i][j+1].point=20;
							arr[i][j+1].move=move_count+1;
							arr[i][j+1].judge=1;
						}

						if(arr[i][j+1]==null){

							arr[i][j+1]=new Goat();
							arr[i][j+1].age=0;
							arr[i][j+1].point=20;
							arr[i][j+1].move=move_count+1;
							arr[i][j+1].judge=1;


						}

					}

					if(direct==3){

						if(arr[i][j-1]!=null && arr[i][j-1].judge==0){

							arr[i][j-1]=null;

							arr[i][j-1]=new Goat();
							arr[i][j-1].age=0;
							arr[i][j-1].point=20;
							arr[i][j-1].move=move_count+1;
							arr[i][j-1].judge=1;

						}

						if(arr[i][j-1]==null){

							arr[i][j-1]=new Goat();
							arr[i][j-1].age=0;
							arr[i][j-1].point=20;
							arr[i][j-1].move=move_count+1;
							arr[i][j-1].judge=1;

						}

					}
				}
			}
		}
	}




	public void goat_move(Creature [][]arr,int move_count){

		for(int i=0;i<20;i++){

			for(int j=0;j<35;j++){

				if((arr[i][j]!=null) && (arr[i][j].judge==1) && ((arr[i][j].age<50) || (arr[i][j].age>55 )&& (arr[i][j].age<70)) && (arr[i][j].move==move_count) && (arr[i][j].act==0)){

					int count=0;

					int direct=0;

					(arr[i][j].move)++;


					while(count==0){


						direct=rand.nextInt(4);//0 is up,1 is down,2 is right,3 is left

						if(direct==0 && i-1>0){

							count=1;

						}

						if(direct==1 && i+1<20){

							count=1;

						}

						if(direct==2 && j+1<35){

							count=1;

						}

						if(direct==3 && j-1>0){

							count=1;

						}

					}


					if(direct==0){

						if(arr[i-1][j]==null){

							arr[i-1][j]=arr[i][j];
							arr[i][j]=null;

						}

						if(arr[i-1][j]!=null && arr[i-1][j].judge==0){

							arr[i-1][j]=null;
							arr[i][j].point=(arr[i][j].point)+5;
							arr[i-1][j]=arr[i][j];
							arr[i][j]=null;

						}

					}

					if(direct==1){

						if(arr[i+1][j]==null){

							arr[i+1][j]=arr[i][j];
							arr[i][j]=null;

						}

						if(arr[i+1][j]!=null && arr[i+1][j].judge==0){

							arr[i+1][j]=null;
							arr[i][j].point=(arr[i][j].point)+5;
							arr[i+1][j]=arr[i][j];
							arr[i][j]=null;

						}
					}

					if(direct==2){

						if(arr[i][j+1]==null){

							arr[i][j+1]=arr[i][j];
							arr[i][j]=null;

						}

						if(arr[i][j+1]!=null && arr[i][j+1].judge==0){

							arr[i][j+1]=null;
							arr[i][j].point=(arr[i][j].point)+5;
							arr[i][j+1]=arr[i][j];
							arr[i][j]=null;

						}
					}

					if(direct==3){

						if(arr[i][j-1]==null){

							arr[i][j-1]=arr[i][j];
							arr[i][j]=null;

						}

						if(arr[i][j-1]!=null && arr[i][j-1].judge==0){

							arr[i][j-1]=null;
							arr[i][j].point=(arr[i][j].point)+5;
							arr[i][j-1]=arr[i][j];
							arr[i][j]=null;

						}
					}




				}

			}

		}
	}


	/**
	 * The constructor of the World containing two parameters:
	 * @param numPasses number of passes used for the whole simulation
	 * @param seed the seed to initialize the random number generator for the simulation
	 */
	/*public World(int numPasses, int seed) {
	// TODO: put your code here
	}*/

	/** the main loop use for the simulation and showing the result to the screen 
	 * periodically.
	 * @param displayInterval the number of passes for each display of the world
	 */

	public void mainLoop(int displayInterval) {

		// TODO: put your code here

	}
}
