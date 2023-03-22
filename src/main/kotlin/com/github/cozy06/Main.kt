fun main() {
    val Maze: Array<IntArray> = MazeGen().generateMaze(25, print= true)

    val path = MazeSol().solveMaze(Maze, print= true)

    print(path)
}