package shapeManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import shapes.*;


public class ShapeFileReader
{
	String fileName;
	
	public static Shape[] readShapesFromFile(String fileName) throws IllegalArgumentException
	{
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName)))
		{
			String line = reader.readLine();
			if (line == null) {
				throw new IllegalArgumentException("File is empty");
			}
		
            int numShapes = Integer.parseInt(line);
            Shape[] shapes = new Shape[numShapes];
            
            for (int i = 0; i < numShapes; i++) 
            {
            	line = reader.readLine();
				if (line == null)
				{
					throw new IllegalArgumentException("File is missing shape data");
				}
				
				String[] parts = line.trim().split("\\s+");
				if (parts.length < 3) 
				{
					throw new IllegalArgumentException("Invalid line in file: " + line);
				}
				
				String shapeType = parts[0];
				double height = Double.parseDouble(parts[1]);
				double param = Double.parseDouble(parts[2]);
				
				switch (shapeType.toLowerCase()) 
				{
				case "cylinder":
					shapes[i] = new Cylinder(height, param);
					break;
				case "cone":
					shapes[i] = new Cone(height, param);
					break;
				case "pyramid":
					shapes[i] = new Pyramid(height, param);
					break;
				case "squareprism":
					shapes[i] = new SquarePrism(height, param);
					break;
				case "triangularprism":
					shapes[i] = new TriangularPrism(height, param);
					break;
				case "pentagonalprism":
					shapes[i] = new PentagonalPrism(height, param);
					break;
				case "octagonalprism":
					shapes[i] = new OctagonalPrism(height, param);
					break;
				default:
					throw new IllegalArgumentException("Invalid shape type: " + shapeType);
				}
            }
            return shapes;
		}
		catch (IOException e)
		{
			throw new IllegalArgumentException("Error reading file: " + e.getMessage());
		}

	}
}
