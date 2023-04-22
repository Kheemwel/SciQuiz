package com.CrimsonKnightBlood.SciQuiz.Quiz;

public class MCBiologyChoices
{

	public String Choices[][] = {
		{"George Cuvier", "Auguste Compte", "Marquis de Condorcet", "Jacque Tonnies"},
		{"Spermatocytes", "Autosomes", "Ribosomes", "Lysosomes"},
		{"Lysosome", "Endoplasmic Reticulum", "Cytoplasm", "Mitochondrion"},
		{"Citric Acid", "Hydrochloric Acid", "Lactic Acid", "Pyruvic Acid"},
		{"Deamination", "Dehydration", "Hydration", "Oxidoamination"},
		{"a Turner's individual", "an abnormal female", "a Kleinfelter's individual", "a clinically normal female"},
		{"denature cholorophyll", "produce carbohydrates", "split water", "move water molecules"},
		{"Annelids", "Dinoflagellates", "Coelenterates", "Diatoms"},
		{"Lobster", "Tick", "Spider", "Scorpion"},
		{"Urea", "Glycogen", "Glucose", "Fibrinogen"},
		{"Humus", "Phototropic Nutrition", "Hydroponics", "Mycorrhiza"},
		{"Transpiration", "Translocation", "Active Transport", "Osmosis"},
		{"Smallpox", "Poliomyelitis", "Cholera", "Diphtheria"},
		{"Fat", "Enzymes", "Carbohydrate", "Other Genes"},
		{"Alcohols", "Proteins", "Steroids", "Nucleic Acids"},
		{"Head", "Tail", "Insertion", "Origin"},
		{"Collagen", "Actin", "Myosin", "Fibrin"},
		{"Apocrine", "Endocrine", "Exocrine", "Holocrine"},
		{"Pain", "Swelling", "Fever", "Redness"},
		{"Starfish", "Sea Anemone", "Whelk", "Millipede"},
		{"Estrogen", "Prolactin", "Leutenizing Hormone", "Androgen"},
		{"Vaporization", "Evapotranspiration", "Evaporation", "Transpiration"},
		{"Reptilia", "Foraminifera", "Porifera", "Cnidaria"},
		{"Metamorphosis", "moist skin", "the abscence of scale", "live in salt water"},
		{"Ankle", "Elbow", "Wrist", "Knee"},
		{"Cyclic", "Annual", "Circadian", "Perennial"},
		{"Pistil", "Filament", "Anther", "Style"},
		{"Diphtheria", "Bubonic Plague", "Step Throat", "Herpes"},
		{"Ascomycetes", "Plyocetes", "Phycomycetes", "Basidomycetes"},
		{"3", "6", "9", "12"},
		{"Erythrocytes", "Red Cells", "Platelets", "White Cells"},
		{"Periosteum", "Medullary Canal", "Cancellous Bone", "Epiphysis"},
		{"Frontal", "Temporal", "Occipital", "Parietal"},
		{"Heart", "Tooth Decay", "Liver", "Kidney"},
		{"Extophase", "Telophase", "Anaphase", "Prophase"},
		{"Orthoptera", "Diptera", "Hymenoptera", "Coleoptera"},
		{"Four", "Three", "Two", "One"},
		{"Vyline and Lysine", "Myosin and Actin", "Peptone and Edstin", "Glutelin and Leucine"},
		{"Association", "Motor", "Stimulatory", "Sensory"},
		{"Liver", "Pituitary Gland", "Skin", "Pineal Gland"},
		{"Oviduct", "Ovary", "Uterus", "Vagina"},
		{"Vas Deference", "Ureter", "Epididymus", "Urethra"},
		{"3", "4", "1", "2"},
		{"no dead cells are in the skin", "Subcutaneous Tissue", "Dermis", "Epidermis"},
		{"Nervous System", "Endocrine System", "Digestive System", "Muscular System"},
		{"Skull", "Tarsal", "Vertebral Column", "Sternun"},
		{"agents which cause the production of bacteria", "Bacteria", "Viruses", "Bacteria Precursors"},
		{"Transformation", "Conjugation", "Transduction", "Translation"},
		{"Semilunar Valve", "Bicuspid Valve", "Tricuspid Valve", "Mitral Valve"},
		{"Protistans", "Fungi", "Virions", "Monerans"},
		
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
