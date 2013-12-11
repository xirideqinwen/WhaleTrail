WhaleTrail
==========

A game in the sea riding the whale and keeping it alive

I. FRONT-END DEVELOPMENT

A.	StartMenuActivity.java offers several choices to the user. 

These options allow the user to decide whether to:

i.	Continue

ii.	 Begin a new game

iii.	 Request information

iv.	 Exit the game window. 

B.	PickNameActivity.java allows the user to enter a family name.

C.	MainActivity.java displays.

The display section of MainActivity shows the following information:

i.	Family Name displays the name variable as entered by the user of the application.

ii.	Location displays the current position of the player in the game.

iii.	Day displays the current day according to the time mindset of the application.

iv.	Money displays the amount of money of which the player is currently in possession.

v.	Food displays the current amount of food of which the player is currently in possession.

vi.	Pace displays the current pace of the game.

vii.	Rations displays the current available rations for the player.

D.	MainActivity.java Buttons

Three buttons included in the MainActivity section of this application are those of Buy, Settings, and Continue. 

i. The Buy button allows the user to purchase supplies. 

ii.	The Settings button causes a pop-up menu to change the rations or the current pace.

iii. The Continue button allows for the generation of random events.

II. BACK-END DEVELOPMENT


A. GameThread.java contains the public and private members detailed below.

Its public members include:
i. GameThread(SurfaceHolder, MainGamePanel) initializes a game thread given the 2 classes SurfaceHolder and MainGamePanel. 
ii. void setRunning(bool) is a function setter that causes a person in the game to either run or stop running. If the bool input value is equal to 1, the person will be triggered to begin running. If the bool input value is equal to 0, the person in the game will be triggered to stop running. This function, however, does not return a value.

iii. void run() is a function causing the person in the game to run. This function does not return a value.

Its private members include:
i. bool running is a variable that may be either true or false. If the game is currently being played, the value of this variable will be equal to 1. If not, it will be equal to 0. 

ii.	SurfaceHolder surfaceHolder is a variable for the display surface holder. This variable is initialized within the class SurfaceHolder. 

iii. MainGamePanel gamePanel is a variable for the overall display panel. This variable is initialized within the class MainGamePanel. 

B.	Person.java contains the following public and private members.

Its public members include:
i. string getName() is a function that returns the name of a person as a string variable. 
ii. int getHealth() is a function returning the health of a person as an integer value. 
iii. void changeHealth(int) is a function utilized to change the health of a game person. This function does not return a value.

iv. string toString() is a function that converts data into a string variable and then return the string.

Its private members include:
i. string name is a string variable containing the name of the player.
ii. int health is a variable storing an integer value that represents the overall health of the player. 
C. Family.java is a class containing both public and private members.

Its public members include:
i. The Family constructor initializes a family for each run of the game.

ii. string nextDay() is a function returning a string that contains the name of the next day according to the time settings of the game.

iii. PaceTypes getPace() operates as a function getter and returns the type of pace currently in use by the game and player from the class PaceTypes.

iv. RationTypes getRations() is a function getter that returns the types of rations from the class RationTypes.

v. int getPost() serves as the function getter that returns the integer value of the variable Post.

vi. bool atTown() is a function returns either true or false, depending upon whether the player is located at the town. 

vii. void setPosition(int) is a function that inputs an integer value and uses this value as the new position of the player. This function does not return a value.

viii. void incPos() operates as a function that increments the position of the player in the game. This function does not return a value.

ix. void incPos(int) is a function that inputs an integer value and increments the position of the player in the game by the specified integer input. This function does not return a value.

x. void setPace(PaceTypes) is a function allowing the user to set the game pace. This function does not return a value.

xi. void setRations(RationsTypes) works as a function that allows the user to set rations for the game. This function does not return a value.

xii. void eatFood() is a function triggering the player to consume his or her food. This function does not return a value.

xiii. void updateHealth() operates as a function to update the health status of the player. This function does not return a value.

xiv. void incitem(item, int) is a function that takes in as input both an item member and an integer value.

xv.	int getAmt(Item) is a function getter that returns a variable of integer value. This variable represents the currently held amount of the item specified by the input. 

xvi. string getName() is a function getter that returns the name of the player.

xvii. void setName(string) is a function utilized to set the name of a player in the game to the string variable entered as the input to this function.

xviii. int isDead() is a function returning an integer value to determine whether or not the player is dead.

xix. string causeOfDeath() works as a function returning a string variable displaying the player’s cause of death.


Its private members include:
i. int position is an integer value of the current position of the player

ii. RationTypes myRation is a variable for the ration of the specific player. This variable is initialized in the class RationTypes. 

iii. PaceTypes myPace is a variable for the player’s pace, declared in the class PaceTypes.

iv. string name is a variable of string value and contains the name of the player.

v. int foodAmt is a variable of integer value that represents the amount of food currently obtained.

vi. int whaleAmt is a variable of integer value for the number of whales collected by the player.

vii. int clotheAmt is a variable of integer value that contains the amount of clothing of the player.

viii. int money is a variable of integer value representing how much money is held currently.

ix. int health is a variable of integer value and represents the health factor of the player.

x. int incByAtLeast(int, int) operates as a function that increments a value by at least the amount of the integer entered as input to this function.

D. Item.java contains each of the following variables: string name and int cost.
i. string name is a string variable containing the name of the item in question, as previously explained in section D.ii. 

ii. int cost is a variable of integer value to represent the cost of the specified item.

E. Event.java generates a random find event, one from the base class below, entitled AbstractFind.

F. AbstractFind.java is the parent class for the following derived classes:
i. FindFood.java is a derived class of AbstractFind and adds an amount of 25 to the variable food for the family in the game.

ii. FindGold.java is a derived class of AbstractFind and rewards the player by increasing the currently held amount of money by 200.

iii. FindNothing.java is a derived class of AbstractFind and causes no impact upon the game itself other than an output message reading, “Nothing happened today.” 

iv. FindPirates.java is a derived class of AbstractFind and causes the player to encounter a band of pirates. As a result, the variable clotheAmt is decreased by 10 and the variable money is decreased by 50.

v. FindRock.java is a derived class of AbstractFind. When this event is generated, the amount stored in the variable food is decreased by 10.

vi. FindSickness.java is a derived class of AbstractFind and can have 1 of 2 different effects. If the clotheAmt variable is less than 20, the new value for variable health will be decreased by 3 from its current value. Should the clotheAmt variable be less than 5, the new value for variable health will be decreased by 7 from its current value. 
