package com.CrimsonKnightBlood.SciQuiz.Quiz;

public class MCEarthScienceChoices
{
	
	public String Choices[][] = {
		{"Igneous", "Sedimentary", "Metamorphic", "Fossilized"},
		{"Caldera", "Volcanic Neck", "Nuee Ardente", "Laccolith"},
		{"Limestone", "Quartzite", "Shale", "Marble"},
		{"Hydroelectric", "Wind", "Geothermal", "Ocean thermal energy conversion"},
		{"21%", "28%", "50%", "100%"},
		{"Radiation", "X-rays", "Gamma Rays", "Ultraviolet Rays"},
		{"Calcite", "Muscovite", "Quartz", "Diamond"},
		{"Marble", "Ironstone", "Travertine", "Bedrock"},
		{"Granite", "Coal", "Slate", "Shale"},
		{"Sandstone", "Quartz", "Granite", "Marble"},
		{"Dark Limestone", "Basalt", "Gypsum", "Obsidian"},
		{"Extrusive Rocks", "Granite", "Intrusive Rocks", "Mineral"},
		{"Asthenosphere", "Lithosphere", "Crust", "Mantle"},
		{"California", "Texas", "Oregon", "Wisconsin"},
		{"Drumlin", "Esker", "Moraine", "Kame"},
		{"The Rockies", "Switzerland", "Greenland", "The Andes Mountains"},
		{"Drifts", "Veins", "Cirques", "Dunes"},
		{"Low cloud layers", "Rain clouds", "Thick and fleecy", "Made of ice crystals"},
		{"Tidal Wave", "Storm Surge", "Hurricane", "Cyclonistat"},
		{"1", "8", "4", "10"},
		{"5", "10", "3", "6"},
		{"Bauxite", "Hematite", "Galena", "Malachite"},
		{"Jurassic", "Silurian", "Devonian", "Permian"},
		{"Devonian", "Permian", "Jurassic", "Silurian"},
		{"Cambrian", "Carboniferous", "Silurian", "Ordovician"},
		{"Triasic", "Devonian", "Permian", "Tertiary"},
		{"Quaternary", "Cretaceous", "Triasic", "Carboniferous"},
		{"Cenozoic", "Precambrian", "Mesozoic", "Cambrian"},
		{"Reverse Fault", "Tear Fault", "Overthrust Fault", "Normal Fault"},
		{"Tear Fault", "Normal Fault", "Thrust Fault", "Strike-slip Fault"},
		{"Normal Fault", "Thrust Fault", "Tear Fault", "Overthrust Fault"},
		{"Trough of the Pacific", "Doldrums", "Calms of Cancer", "Calms of Capricorn"},
		{"Pothole", "Sinkhole", "Kettle Hole", "Cave"},
		{"Gorge", "Water Gap", "Divide", "Backswamp"},
		{"Niagara Falls", "Yosemite Falls", "Victoria Falls", "Yellowstone Falls"},
		{"Photic Zone", "Limnetic Zone", "Profundal Zone", "Littoral Zone"},
		{"Limonite", "Magnetite", "Pyrite", "Hematite"},
		{"Swamps", "Jungles", "Forest", "Grasslands"},
		{"Fog", "Tornado", "Smog", "Sleet"},
		{"Transpiration", "Condensation", "Evaporation", "Rain"},
		{"A ring of gas", "A passing star", "A comet", "A number of moons"},
		{"Methane", "Sulfuric Acid", "Hydrocloric Acid", "Water"},
		{"Olivine", "Feldspar", "Fluorite", "Muscovite"},
		{"Afrasia", "Europia", "Pangea", "Gondwanaland"},
		{"Jurassic", "Mississippian", "Devonian", "Silurian"},
		{"Andes", "Sierra Nevadas", "Cascades", "Himalayas"},
		{"Calcite", "Quartz", "Muscovite", "Horneblende"},
		{"Weatherology", "Oceanography", "Meteorology", "Atmospherology"},
		{"Chlorine", "Fluorine", "Bromine", "Oxygen"},
		{"Tidal Wave", "Bore", "Neap Tidal Wave", "Tsunamis"}
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
