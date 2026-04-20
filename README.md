<h1>🕹 Game of Life</h1>

<p style="text-align: center">
  "We don’t program life. We let it happen."
</p>

<hr>

<h2>📖 About the Project</h2>
<p style="text-align: justify;">
  The "Game of Life" is a cellular automaton invented by British mathematician John Horton Conway in 1970.
  It is an abstract mathematical model that follows simple rules and defines the evolution of a universe of cells.
  The universe of the Game of Life is a two-dimensional grid composed of cells that can be either alive or dead.
  Each cell interacts with its eight immediate neighbors (horizontally, vertically, and diagonally).
</p>

<img width="100%" src="https://th.bing.com/th/id/R.d419bedb9212f18949f281ba98c291cd?rik=k6AQj59iBNBrKg&pid=ImgRaw&r=0" alt="Game of Life Simulation">

<p style="text-align: justify;">
  The cells follow these rules:
</p>
<ol>
  <li>Any live cell with fewer than two live neighbors dies of loneliness in the next generation.</li>
  <li>Any live cell with two or three live neighbors remains alive in the next generation.</li>
  <li>Any live cell with more than three live neighbors dies of overpopulation in the next generation.</li>
  <li>Any dead cell with exactly three live neighbors becomes a live cell in the next generation.</li>
</ol>

<hr>

<h2>🖼️ Screenshots</h2>
<p>Here are some screenshots of the application interface:</p>

<h3>CLI</h3>
<ul>
  <img width="100%" src="https://imgur.com/eBpnqsJ.png" alt="CLI Main Menu View">
  <img width="100%" src="https://imgur.com/RaocHs7.png" alt="CLI Game Explanation View">
  <img width="100%" src="https://imgur.com/T4X9k5R.png" alt="CLI Game Config View">
  <img width="100%" src="https://imgur.com/DizGd2W.png" alt="CLI Game Grid View">
  <img width="100%" src="https://imgur.com/JRZczND.png" alt="CLI Game Grid View">
  <img width="100%" src="https://imgur.com/K6WA1Qm.png" alt="CLI Game Grid View">
</ul>

<h3>GUI</h3>
<p style="text-align: justify;">
  The graphical interface was introduced in version 1.1.0 and was inspired by the web version available at 
  <a href="https://playgameoflife.com/" target="_blank">playgameoflife.com</a>.
</p>
<ul>
  <img width="100%" src="https://imgur.com/G6uvv5T.png" alt="GUI Main Menu View">
  <img width="100%" src="https://imgur.com/8tbsZko.png" alt="GUI Game Explanation View">
  <img width="100%" src="https://imgur.com/jrH6BJL.png" alt="GUI Game Grid View">
  <img width="100%" src="https://imgur.com/lQ7zY54.png" alt="GUI Game Grid View">
  <img width="100%" src="https://imgur.com/NG8UgHk.png" alt="GUI Game Grid View">
</ul>

<hr>

<h2>🚀 How to Run</h2>
<ol>
  <li>Clone or download this repository.</li>
</ol>

<h3>Using an IDE (e.g., IntelliJ, Eclipse)</h3>
<ol>
  <li>Open the project in your Java IDE.</li>
  <li>Run the <code>dev.guedes.gameoflife.GameOfLifeApplication</code> class.</li>
</ol>

<h3>Using the Executable JAR</h3>
<h4>GUI - Default</h4>
<ol>
  <li>Navigate to the <code>/dist</code> folder.</li>
  <li>Run the GUI version via terminal:<br>
    <code>java -jar .\dist\game-of-life-1.1.0.jar</code>
  </li>
</ol>

<h4>CLI</h4>
<ol>
  <li>Navigate to the <code>/dist</code> folder.</li>
  <li>Run the CLI mode using the flag:<br>
    <code>java -jar .\dist\game-of-life-1.1.0.jar --cli</code>
  </li>
</ol>

<h3>Using the Windows .bat File (CLI)</h3>
<ol>
  <li>Navigate to the <code>/dist</code> folder.</li>
  <li>Double-click the file <code>game-of-life-1.1.0.bat</code> or run it via terminal:<br>
    <code>game-of-life-1.1.0.bat</code>
  </li>
</ol>
