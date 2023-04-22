package com.CrimsonKnightBlood.SciQuiz.Quiz;

public class MCChemistryChoices
{

	public String Choices[][] = {
		{"One", "Two", "Three", "Four"},
		{"Argon", "Krypton", "Xenon", "Radon"},
		{"At", "Br", "Cl", "K"},
		{"Xylene", "Styrene", "Hexane", "Naphthalene"},
		{"Propylene", "Benzene", "N-Octane", "N-Tridecane"},
		{"Waxes", "Steroids", "Fats and Oils", "Starches"},
		{"Pressure", "Force", "Volume", "Viscosity"},
		{"Force", "Volume", "Viscosity", "Pressure"},
		{"Alkaline Earths", "Alkali Metals", "Halogens", "Noble Gases"},
		{"Ionic", "Polar Covalent", "Nonpolar Covalent", "Covalent Network"},
		{"Polar Covalent", "Covalent Network", "Ionic", "Nonpolar Covalent"},
		{"Soap", "Petroleum", "Rubber", "Plastic"},
		{"Boyle", "Dalton", "Lavoiser", "Proust"},
		{"Alkenes", "Alkanes", "Alkyne", "Mixanes"},
		{"3", "4", "5", "6"},
		{"Butene", "Butane", "Acetylene", "Propane"},
		{"Acetone", "Water", "Chloroform", "Gasoline"},
		{"Magnesium", "Gallium", "Sodium", "Cesium"},
		{"Oxygen", "Carbon", "Nitrogen", "Sulfur"},
		{"Uranium", "Sodium", "Gold", "Copper"}
	};

	public String getChoice1(int mc) {
		String choice = Choices[mc][0];
		return choice;
	}

	public String getChoice2(int mc) {
		String choice = Choices[mc][1];
		return choice;
	} 

	public String getChoice3(int mc) {
		String choice = Choices[mc][2];
		return choice;
	} 
	
	public String getChoice4(int mc) {
		String choice = Choices[mc][3];
		return choice;
	}

}
