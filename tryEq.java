class tryEq {

public static void main(String[]args){
	AddScore gas = new AddScore("Ascending", "Velasco", 55, 55);
	AddScore gaw = new AddScore("Descending", "Burgos", 100,100);
	AddScore gss = new AddScore("Even or Odd", "Zyron", 45, 45);
	gss.plottingOfScores();
	AddScore gass = new AddScore("Occurrence", "Jv", 45, 55);
	gas.plottingOfScores();
	gaw.plottingOfScores();
	gass.plottingOfScores();
	}
}