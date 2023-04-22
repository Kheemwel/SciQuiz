package com.CrimsonKnightBlood.SciQuiz.Quiz;

public class MCPhysicsChoices
{

	public String Choices[][] = {
		{"Always Negative", "Always Positive", "Always Zero", "Either postive or negative"},
		{"Angular Displacement", "Angular Momentum", "Angular Acceleration", "Angular Velocity"},
		{"Ohm's Law", "Faraday's Law", "Ampere's Law", "Gauss's Law"},
		{"Einstein", "Bohr", "Compton", "Heisenberg"},
		{"Cavendish", "Copernicus", "Kepler", "Newton"},
		{"Sound Waves", "Water Waves", "Sunlight", "An 'Ether'"},
		{"Fahrenheit", "Galileo", "Celsius", "Centigrade"},
		{"Charge", "Matter", "Energy", "Momentum"},
		{"Cavitation", "Absorption", "Adsorption", "Dissolving"},
		{"Separation", "Cleavage", "Fussion", "Fission"},
		{"1", "3", "5", "7"},
		{"Velocity", "Potential Energy", "Momentum", "Kinetic Energy"},
		{"Slug", "Dyne", "Pound", "Gram"},
		{"Work", "Power", "Energy", "Impulse"},
		{"Radio", "Sound", "Electromagnetic", "Light"},
		{"Area divided by force", "Area times force", "Force divided by area", "Area minus force"},
		{"3", "6", "2", "4"},
		{"Time", "Length", "Mass", "Weight"},
		{"Strangelove", "Oppenheimer", "Einstein", "Faraday"},
		{"The alpha particle", "The Electron", "The Neutron", "The Cloud Chamber"}
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
