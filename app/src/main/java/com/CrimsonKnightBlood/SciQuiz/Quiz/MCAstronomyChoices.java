package com.CrimsonKnightBlood.SciQuiz.Quiz;

public class MCAstronomyChoices
{
	
	public String Choices[][] = {
		{"Earth", "Neptune", "Jupiter", "Venus"},
		{"111 days", "88 days", "50 days", "25 days"},
		{"11 years", "3 years", "26 years", "49 years"},
		{"Irregular", "Barred-Spiral", "Elliptical", "Spiral"},
		{"Aristarchus", "Hipparcus", "Pythagorus", "Copernicus"},
		{"toward the sun", "toward the earth", "away from the sun", "behind the comet in its orbit"},
		{"radio wavelengths", "microwave", "infrared", "ultraviolet"},
		{"Spiral Galaxies", "Irregular Galaxies", "Elliptical Galaxies", "large clouds of gas and dust"},
		{"Astronomy", "Tomography", "Cosmology", "Cryology"},
		{"2%", "1%", "50%", ".001%"},
		{"White Dwarf", "Neuron Star", "Main Sequence Star", "Supernova"},
		{"Mercury", "Pluto", "Venus", "Mars"},
		{"Saturn", "Uranus", "Jupiter", "Neptune"},
		{"Mercury", "Jupiter", "Saturn", "Pluto"},
		{"Titan", "Triton", "Europa", "Io"},
		{"Mercury", "Venus", "Earth", "Mars"},
		{"Saturn", "Neptune", "Uranus", "Jupiter"},
		{"28 days", "29.5 days", "30 days", "30.3 days"},
		{"Sagittarius", "Big Deeper", "Cassiopeia", "Orion"},
		{"Neutron Star", "Black Hole", "White Dwarf", "Red Giant"}
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
