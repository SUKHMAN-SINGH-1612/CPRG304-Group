package problemdomain;

public class Dishwasher extends Appliance
{
	
	// Variables or attributes
	
	private String feature;
	private String soundRating;

	// Getters and Setters
	
	public String getFeature()
	{
		return feature;
	}
	public String getSoundRating()
	{
		return soundRating;
	}
	public void setFeature(String feature)
	{
		this.feature = feature;
	}
	public void setSoundRating(String soundRating)
	{
		this.soundRating = soundRating;
	}
	
	// Constructor
	
	public Dishwasher(long itemNumber, String brand, double quantity, double wattage, String color, double price,
			String feature, String soundRating)
	{
		super(itemNumber, brand, quantity, wattage, color, price);
		this.feature = feature;
		this.soundRating = soundRating;
	}
	
	// toString method
	@Override
	public String toString()
	{
        String soundRateText;
        if (soundRating.equals("Qt"))
            soundRateText = "Quietest";
        else if (soundRating.equals("Qr"))
            soundRateText = "Quieter";
        else if (soundRating.equals("Qu"))
            soundRateText = "Quiet";
        else if (soundRating.equals("M"))
            soundRateText = "Moderate";
        else
            soundRateText = "Unknown";
        
        return super.toString() + "\nFeatures: " + feature + "\nSoundRating: " + soundRateText + "\n";
	}
}
