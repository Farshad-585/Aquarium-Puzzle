# Aquarium-Puzzle
---

### CITS1001 - Software Engineering with Java Project
#### The University of Western Australia

Aquarium is a modern puzzle game available at www.puzzle-aquarium.com. The player is required to determine the water levels of a set of aquariums to collectively satisfy a number of constraints. A typical starting position is shown in on the left, along with its solution on the right.

<img src="https://user-images.githubusercontent.com/61343458/124686898-7d4e1880-df06-11eb-859d-d076be5072b3.png" width="250" height="250">   <img src="https://user-images.githubusercontent.com/61343458/124686864-6c050c00-df06-11eb-8962-ba1fcd4e77de.png" width="250" height="250">

The game takes place on a square grid of spaces. In the figures, thin black lines separate spaces, thick red lines represent the boundaries between aquariums (so this puzzle has six aquariums), and blue spaces represent water sitting in an aquarium. We are looking at the aquariums from the side, so gravity acts down the screen. The solution to an Aquarium puzzle has the right amount of water in each row and column, and any water in an aquarium must sit at the bottom.

### The game of Aquarium
During Aquarium play, each space in the grid is in one of three states: water, air, or empty, shown in the figures as filled blue, pink circle, and blank respectively. Initially, all spaces are empty. The player has only two actions available: left-click on a space on the grid, or right-click on a space.
  - Left-click: if the space is air or empty, it becomes water; if it is water, it becomes empty.
  - Right-click: if the space is water or empty, it becomes air; if it is air, it becomes empty.

The player's goal is to generate a layout that satisfies three constraints.
  1. The number of water spaces in each row must match the number on the left of that row.
  2. The number of water spaces in each column must match the number at the top of that column.
  3. Any water in an aquarium must sit at the bottom, because of gravity. Specifically, within each aquarium, in each row the spaces must be either all water or all not-water; and all rows containing water must be below all rows containing not-water.

At any time during play, the player can ask the system to check if the current layout constitutes a solution. The system will respond either with three ticks, or with one of three indications of why the current layout is not a solution. (In all of the following, indexing of rows and columns starts from the top-left of the grid.)
  - Column k is wrong - This means that the column identified has the wrong number of water spaces. Columns are numbered leftwards from 0.
  - Row k is wrong - This means that the row identified has the wrong number of water spaces. Rows are numbered downwards from 0.
  - The aquarium at r,c is wrong - This means that the aquarium which includes space r,c does not obey the third constraint above.

### Running the Program
The puzzle can be run from the runApp.java. In this project, an Aquarium puzzle is described in a text file. There are several examples in the "Examples" folder within the project file. You can provide the file path to the constructor in the "runApp.java" file to play the game.

##### NOTE: THIS PROJECT WAS PART OF THE BEGINEER JAVA PROGRAMMING UNIT(CITS1001) AT THE UNIVERSITY OF WESTERN AUSTRALIA. SOME OF THE CODE AND LOGIC WAS PROVIDED BY PROFESSOR LYNDON WHILE. 




