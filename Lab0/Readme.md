# Lab 0: Introduction to Java Programming in Eclipse

## Background

In this assignment, you'll delve into Java programming using the Eclipse IDE. The focus is on implementing a system to manage appliance data for Modern Appliances, using principles similar to those in OOP languages like Python and C#.

## Instructions

1. This lab can be completed individually or in a group. If working in a group, submit only one assignment with all group members' names included in the comments.
2. Refer to the **Marking Criteria** section for assessment details.
3. Submit your completed zipped Eclipse project to Brightspace by the due date.

## Exercise

Modern Appliances needs a system to manage appliance data efficiently. The data file, appliances.txt, contains various appliance types formatted differently:

### Appliance Types and Data Formatting

- **Refrigerators**: ItemNumber;Brand;Quantity;Wattage;Color;Price;NumberOfDoors;Height;Width
- **Vacuums**: ItemNumber;Brand;Quantity;Wattage;Color;Price;Grade;BatteryVoltage
- **Microwaves**: ItemNumber;Brand;Quantity;Wattage;Color;Price;Capacity;RoomType
- **Dishwashers**: ItemNumber;Brand;Quantity;Wattage;Color;Price;Feature;SoundRating

### Program Guidelines

- Implement a class hierarchy with an `Appliance` superclass and subclasses for each appliance type.
- Parse `appliances.txt` into a list containing all appliance types.
- Include methods to:
  - Allow a customer to purchase an appliance by item number.
  - Search and display appliances by brand.
  - Display a specified number of random appliances.
  - Persist changes back to `appliances.txt` upon exiting.

### Expected Output

Include a user-friendly menu-driven interface with options to perform the above tasks.

## Marking Criteria

- **Working Code**: Correct Java version, no errors.
- **Menu Options**: All menu options function correctly.
- **Save File**: Persistence of changes to `appliances.txt` works correctly.
- **OOP Modelling**: Appropriate use of abstraction and `toString` method overriding.
- **Naming And Style**: Follows Java naming conventions with consistent indentation.
- **Packaging**: Classes are appropriately packaged, resource files correctly placed.
- **Access Control**: Correct specification of access modifiers.
- **Best Practices**: Correct handling of loops and exceptions.
- **Use (and trust) the IDE!**: Resolves all Eclipse warnings.
- **Documentation**: Includes header comments and inline comments for clarity.

Ensure your implementation meets these criteria for successful completion of the assignment.
