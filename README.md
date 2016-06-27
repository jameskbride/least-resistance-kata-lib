# Path of Least Resistance #
## The Challenge ##
Energy flows through the path of least resistance. For this challenge, you are
provided a grid of integers where each integer represents the amount of resistance
encountered at a given point on the grid. Energy enters the grid from the left (at any
point) and passes through the grid to the right, moving only one column per round. 

## Programming Challenge ##
Movement is always to an adjacent row, meaning energy can flow horizontally or
diagonally. For the sake of this challenge, we assume the first and last row are also
adjacent. Effectively, the grid “wraps”. 

The total resistance of a path is the sum of the integers in each of the visited cells.
The solution needs to handle grids of various sizes with a minimum of 1 row and 5
columns up to 10 rows and 100 columns. If in the next move, the total resistance will
exceed 50, the energy cannot continue.

The purpose of this challenge is to find the path of least resistance (that is, the path
with the lowest total resistance). The paths of least resistance through two slightly
different 5 x 6 grids are shown below. The grid values differ only in the bottom row.
The path for the grid on the right takes advantage of the adjacency between the first
and last rows. 

<pre>
3 4 1 2 8 6
6 1 8 2 7 4
5 9 3 9 9 5
8 4 1 3 2 6
3 7 2 8 6 4
</pre>
Path by row: 1 2 3 4 4 5

<pre>
3 4 1 2 8 6
6 1 8 2 7 4
5 9 3 9 9 5
8 4 1 3 2 6
3 7 2 1 2 3
</pre>
Path by row: 1 2 1 5 4 5

## Input ##
## Programming Challenge ##
The input consists of a sequence of row specifications. Each row is represented by a
series of delimited integers on a single line. Note: integers are not restricted to being
positive.

## Output ##
Three lines should be output for each matrix specification. The first line is either “Yes”
or “No” to indicate the water made it all the way through the grid. The second line is
the total resistance. The third line shows the path taken as a sequence of n delimited
integers, each representing the rows traversed in turn. If there is more than one path
of least resistance, only one path need be shown in the solution.

## Examples ##
<pre>
3 4 1 2 8 6 
6 1 8 2 7 4 
5 9 3 9 9 5 
8 4 1 3 2 6 
3 7 2 8 6 4
</pre>
Output: 

Yes

16

1 2 3 4 4 5

<pre>
3 4 1 2 8 6 
6 1 8 2 7 4 
5 9 3 9 9 5 
8 4 1 3 2 6 
3 7 2 12 3
</pre>
Output:

Yes

11

1 2 1 5 4 5

<pre>
19 10 19 10 19 
21 23 20 19 12 
20 12 20 11 10
</pre>
Output:

No

48

1 1 1
