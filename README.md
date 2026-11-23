<div align="center">
  <h1>🕹️ Game of Life</h1>

  <h3>A Java Implementation of Conway's Cellular Automaton</h3>

  <p>
    <em>"We don't program life. We let it happen."</em>
  </p>

  <p>
    <img src="https://img.shields.io/badge/Language-Java-orange?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" />
    <img src="https://img.shields.io/badge/Build-Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven" />
    <img src="https://img.shields.io/badge/Interface-CLI%20%7C%20GUI-blue?style=for-the-badge" alt="CLI and GUI" />
    <img src="https://img.shields.io/badge/License-MIT-brightgreen?style=for-the-badge" alt="MIT License" />
  </p>
</div>

<hr />

<div align="justify">
  <h2>📖 About the Project</h2>

  <p>
    <strong>Game of Life</strong> is a Java implementation of Conway's Game of Life, a cellular automaton invented by British mathematician John Horton Conway in 1970.
  </p>

  <p>
    It is an abstract mathematical model that simulates the evolution of a universe of cells according to a small set of rules. The universe consists of a two-dimensional grid in which each cell can be either alive or dead.
  </p>

  <p>
    Each cell interacts with its eight immediate neighbors (horizontally, vertically, and diagonally). Based on these interactions, the state of every cell is updated from one generation to the next, producing patterns that can evolve, stabilize, or disappear over time.
  </p>

  <p>
    The cells follow four fundamental rules:
  </p>

  <ol>
    <li><strong>Underpopulation:</strong> Any live cell with fewer than two live neighbors dies in the next generation.</li>
    <li><strong>Survival:</strong> Any live cell with two or three live neighbors remains alive in the next generation.</li>
    <li><strong>Overpopulation:</strong> Any live cell with more than three live neighbors dies in the next generation.</li>
    <li><strong>Reproduction:</strong> Any dead cell with exactly three live neighbors becomes alive in the next generation.</li>
  </ol>

  <p>
    <img src="https://th.bing.com/th/id/R.d419bedb9212f18949f281ba98c291cd?rik=k6AQj59iBNBrKg&pid=ImgRaw&r=0" alt="Conway's Game of Life simulation" width="100%" />
  </p>

  <blockquote>
    <em>Simple rules can lead to surprisingly complex behavior.</em>
  </blockquote>
</div>

<hr />

<div align="justify">
  <h2>✨ Features</h2>

  <ul>
    <li><strong>Cellular Automaton Simulation:</strong> Simulates Conway's Game of Life using its four fundamental rules.</li>
    <li><strong>Command-Line Interface (CLI):</strong> Provides a terminal-based experience for interacting with the simulation.</li>
    <li><strong>Graphical User Interface (GUI):</strong> Offers a visual experience for configuring and observing cell generations.</li>
    <li><strong>Interactive Grid:</strong> Allows users to visualize the evolution of living and dead cells.</li>
    <li><strong>Execution Mode Selection:</strong> Supports both graphical and terminal execution through a command-line argument.</li>
  </ul>
</div>

<hr />

<div align="justify">
  <h2>📸 Screenshots</h2>

  <p>
    Explore the two interfaces available in Game of Life.
  </p>

  <h3>Command-Line Interface (CLI)</h3>

  <p>
    The terminal interface provides a text-based experience for navigating the application and observing the simulation.
  </p>

  <div align="center">
    <h4>Main Menu</h4>
    <p>
      <img src="https://imgur.com/krZyVNq.png" alt="Game of Life CLI main menu" width="90%" />
    </p>

<h4>Game Explanation</h4>
    <p>
      <img src="https://imgur.com/TyNOkR5.png" alt="Game of Life CLI explanation screen" width="90%" />
    </p>

<h4>Game Configuration</h4>
    <p>
      <img src="https://imgur.com/mlSJpVA.png" alt="Game of Life CLI configuration screen" width="90%" />
    </p>

<h4>Simulation</h4>
    <p>
      <img src="https://imgur.com/fvXeY7x.png" alt="Game of Life CLI simulation" width="90%" />
    </p>
    <p>
      <img src="https://imgur.com/7vTOHPW.png" alt="Game of Life CLI simulation with evolving cells" width="90%" />
    </p>
    <p>
      <img src="https://imgur.com/WHwuwbU.png" alt="Game of Life CLI simulation result" width="90%" />
    </p>
    <p>
      <img src="https://imgur.com/Qwsj0uL.png" alt="Game of Life CLI simulation with evolving cells" width="90%" />
    </p>
    <p>
      <img src="https://imgur.com/kQouwf9.png" alt="Game of Life CLI simulation result" width="90%" />
    </p>
  </div>

  <h3>Graphical User Interface (GUI)</h3>

  <p>
    Introduced in version 1.1.0, the graphical interface was inspired by the web version available at <a href="https://playgameoflife.com/" target="_blank" rel="noopener noreferrer">playgameoflife.com</a>.
  </p>

  <div align="center">
    <h4>Main Menu</h4>
    <p>
      <img src="https://imgur.com/9Tfq5jD.png" alt="Game of Life GUI main menu" width="90%" />
    </p>

<h4>Game Explanation</h4>
    <p>
      <img src="https://imgur.com/G32B80F.png" alt="Game of Life GUI explanation screen" width="90%" />
    </p>

<h4>Simulation</h4>
    <p>
      <img src="https://imgur.com/KN9Ed0j.png" alt="Game of Life GUI simulation" width="90%" />
    </p>
    <p>
      <img src="https://imgur.com/ae00JOi.png" alt="Game of Life GUI simulation with evolving cells" width="90%" />
    </p>
    <p>
      <img src="https://imgur.com/d2NL5z1.png" alt="Game of Life GUI simulation result" width="90%" />
    </p>
    <p>
      <img src="https://imgur.com/cLkFhzY.png" alt="Game of Life GUI simulation result" width="90%" />
    </p>
    <p>
      <img src="https://imgur.com/9iCda7x.png" alt="Game of Life GUI simulation result" width="90%" />
    </p>
  </div>
</div>

<hr />

<div align="justify">
  <h2>🛠️ Technologies Used</h2>

  <table>
    <thead>
      <tr>
        <th align="left">Technology</th>
        <th align="left">Purpose</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td><a href="https://www.java.com/">Java</a></td>
        <td>Application development and implementation.</td>
      </tr>
      <tr>
        <td><a href="https://maven.apache.org/">Apache Maven</a></td>
        <td>Project build and dependency management.</td>
      </tr>
      <tr>
        <td><a href="https://github.com/google/guice">Google Guice</a></td>
        <td>Dependency injection and application component management.</td>
      </tr>
      <tr>
        <td><a href="https://junit.org/junit5/">JUnit 5</a></td>
        <td>Automated unit testing.</td>
      </tr>
      <tr>
        <td><a href="https://git-scm.com/">Git</a></td>
        <td>Source code versioning.</td>
      </tr>
    </tbody>
  </table>
</div>

<hr />

<div align="justify">
  <h2>📂 Project Structure</h2>

  <p>
    The project follows a modular Java application structure, separating its core components into dedicated packages for configuration, enums, exceptions, mappers, models, utilities, validators, and views.
  </p>

<pre>
game-of-life/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── dev/guedes/gameoflife/
│   │   │       ├── config/
│   │   │       ├── enums/
│   │   │       ├── exceptions/
│   │   │       ├── mappers/
│   │   │       │   └── impl/
│   │   │       ├── models/
│   │   │       ├── utils/
│   │   │       │   ├── cli/
│   │   │       │   └── swing/
│   │   │       ├── validators/
│   │   │       ├── views/
│   │   │       │   ├── cli/
│   │   │       │   │   └── component/
│   │   │       │   └── gui/
│   │   │       │       ├── components/
│   │   │       │       │   ├── buttons/
│   │   │       │       │   ├── footer/
│   │   │       │       │   ├── frame/
│   │   │       │       │   ├── grid/
│   │   │       │       │   └── header/
│   │   │       │       └── styles/
│   │   │       ├── Application.java
│   │   │       ├── ApplicationInjector.java
│   │   │       ├── ApplicationModule.java
│   │   │       └── GameOfLifeApplication.java
│   │   └── resources/
│   │       └── assets/
│   │           └── icon.png
│   └── test/
│       └── java/
│           ├── dev/guedes/gameoflife/
│           │   ├── mappers/
│           │   ├── models/
│           │   ├── utils/
│           │   │   ├── cli/
│           │   │   └── swing/
│           │   ├── validators/
│           │   └── GameOfLifeApplicationTests.java
│           └── extensions/
│               └── GuiceJUnit5Extension.java
├── .mvn/
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
└── LICENSE
</pre>

  <p>
    The <code>main</code> source set contains the application implementation, while the <code>test</code> source set contains automated tests for the main components, including mappers, models, utilities, and validators.
  </p>

  <p>
    The <code>views</code> package separates the command-line interface (CLI) from the graphical user interface (GUI), allowing each presentation layer to maintain its own components and view management logic.
  </p>

  <p>
    Maven Wrapper scripts allow the project to be built without requiring a separate Maven installation.
  </p>
</div>

<hr />

<div align="justify">
  <h2>🚀 Getting Started</h2>

  <p>
    You can download a ready-to-run version of Game of Life from GitHub Releases or explore and build the source code locally.
  </p>

  <h3>Running a Released Version</h3>

  <ol>
    <li>Visit the project's <a href="https://github.com/JoaoGuedesLuna/game-of-life/releases">Releases</a> page on GitHub.</li>
    <li>Choose the version you want to download.</li>
    <li>Download the executable JAR file from the release assets.</li>
    <li>Make sure Java is installed on your machine.</li>
    <li>Open a terminal in the directory containing the downloaded file.</li>
  </ol>

  <h4>Graphical User Interface (Default)</h4>

  <p>
    Run the application without additional arguments to start the graphical interface:
  </p>

<pre>
java -jar game-of-life-1.1.0.jar
</pre>

  <h4>Command-Line Interface (CLI)</h4>

  <p>
    Pass the <code>--cli</code> argument to start the application in terminal mode:
  </p>

<pre>
java -jar game-of-life-1.1.0.jar --cli
</pre>

  <h3>Running from Source Code</h3>

  <h4>Prerequisites</h4>

  <ul>
    <li>Java Development Kit (JDK) compatible with the project configuration.</li>
    <li>Git (to clone the repository).</li>
  </ul>

  <h4>Steps</h4>

  <ol>
    <li>Clone the repository:</li>
  </ol>

<pre>
git clone https://github.com/JoaoGuedesLuna/game-of-life.git
</pre>

  <ol start="2">
    <li>Navigate to the project directory:</li>
  </ol>

<pre>
cd game-of-life
</pre>

  <ol start="3">
    <li>Run the application using Maven Wrapper.</li>
  </ol>

  <p><strong>Linux / macOS:</strong></p>

<pre>
./mvnw compile exec:java
</pre>

  <p><strong>Windows:</strong></p>

<pre>
mvnw.cmd compile exec:java
</pre>

  <p>
    To start the CLI instead of the default graphical interface, pass the <code>--cli</code> argument through the Maven execution configuration.
  </p>

  <p>
    Alternatively, you can open the project in a Java-compatible IDE, such as IntelliJ IDEA or Eclipse, and run the <code>GameOfLifeApplication</code> class.
  </p>
</div>

<hr />

<div align="justify">
  <h2>📄 License</h2>

  <p>
    This project is licensed under the <strong>MIT License</strong>.
  </p>

  <p>
    You are free to use, copy, modify, distribute, and share this project in accordance with the terms of the license.
  </p>

  <p>
    See the <a href="LICENSE">LICENSE</a> file for details.
  </p>
</div>
