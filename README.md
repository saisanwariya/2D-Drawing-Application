# Java 2D Drawing Application

## Overview

This Java 2D Drawing Application allows users to create and manipulate shapes on a canvas. It includes various features such as undo, clear, shape selection, filling options, gradient painting, color selection, stroke width, and more. Users can draw lines, ovals, and rectangles with customizable attributes.

## Program Functionality

The application provides the following functionality:

- **Undo**: Click the "Undo" button to remove the last shape drawn.
- **Clear**: Click the "Clear" button to clear all shapes from the canvas.
- **Shape Selection**: Use the combo box to select the shape you want to draw: line, oval, or rectangle.
- **Filling Option**: Use the checkbox to specify whether the shape should be filled or unfilled.
- **Gradient Painting**: Use the checkbox to enable/disable gradient painting.
- **Color Selection**: Two JButtons allow you to choose the first and second colors for the gradient if gradient painting is enabled.
- **Stroke Width**: Enter the desired stroke width in the text field.
- **Stroke Dash Length**: Enter the desired stroke dash length in the text field.
- **Dashed or Solid Line**: Use the checkbox to specify whether to draw a dashed or solid line.
- **Draw Canvas**: The JPanel is the drawing canvas where you can create and view shapes.
- **Status Bar**: A JLabel at the bottom of the frame displays the current mouse location on the canvas.

If gradient painting is selected, the shape will be filled with a gradient of the two chosen colors. If not, the shape will be filled with the first color.

## Software Setup

1. **Java Development Kit (JDK)**: Ensure that you have Java JDK installed on your system. You can download it from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html).

2. **Development Environment**: You can use any Java development environment of your choice. Popular options include Eclipse, IntelliJ IDEA, or Visual Studio Code.

3. **Project Files**: Clone or download this GitHub repository to obtain the project files.

## Running the Program

Follow these steps to run the Java 2D Drawing Application:

1. Open your preferred Java development environment.

2. Import the project into your IDE. If you are using Eclipse or IntelliJ IDEA, you can use the "Import" or "Open Project" feature, respectively.

3. Build the project to ensure that all dependencies are resolved and the application compiles successfully.

4. Locate the main class (the class with the `main` method) and run it. This should start the application.

5. Use the application's graphical user interface to interact with the drawing canvas and create shapes with various options.

## Notes

- When dragging the mouse to create a new shape, the shape will be drawn as you move the mouse.

- A template project has been provided, which includes a `MyShapes` hierarchy for drawing lines, rectangles, and ovals. You must use this `MyShapes` hierarchy.

- In the `paintComponent(Graphics g)` method of the `DrawPanel`, shapes are drawn by looping through an `ArrayList` of `MyShapes` and calling the `draw(Graphics2D g2d)` method for each shape.

- You do not need to create event handlers for all components in the top two lines of the frame. Event handlers are only required for the buttons. You can retrieve values from other components when the user interacts with the canvas, which provides all the necessary information to create a new `MyShapes` object.


# Academic Integrity Statement

Please note that all work included in this project is the original work of the author, and any external sources or references have been properly cited and credited. It is strictly prohibited to copy, reproduce, or use any part of this work without permission from the author.

If you choose to use any part of this work as a reference or resource, you are responsible for ensuring that you do not plagiarize or violate any academic integrity policies or guidelines. The author of this work cannot be held liable for any legal or academic consequences resulting from the misuse or misappropriation of this work.

Any unauthorized copying or use of this work may result in serious consequences, including but not limited to academic penalties, legal action, and damage to personal and professional reputation. Therefore, please use this work only as a reference and always ensure that you properly cite and attribute any sources or references used.
