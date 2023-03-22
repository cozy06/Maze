class MazeSol {

    private fun printMaze(maze: Array<IntArray>, size: Int) {
        for (i: Int in 1..size+2) {
            print("■ ")
        }
        println()
        val message = "Hello, world!"
        for (row in maze) {
            print("■ ")
            for (col in row) {
                when (col) {
                    0 -> print("□ ")
                    1 -> print("■ ")
                    2 -> print("⊞ ")
                    3 -> print("⊠ ")
                    -1 -> print("⊡ ")
                }
            }
            print("■")
            println()
        }
        for (i: Int in 1..size+2) {
            print("■ ")
        }
        println()
    }

    fun dfs(maze: Array<IntArray>, x: Int, y: Int, visited: MutableSet<Pair<Int, Int>>, path: MutableList<Pair<Int, Int>>): Boolean {
        // check if current position is the end point
        if (maze[x][y] == 3) {
            path.add(Pair(x, y))
            return true
        }

        // mark current position as visited
        visited.add(Pair(x, y))

        // check adjacent positions
        val dx = intArrayOf(0, 0, -1, 1)
        val dy = intArrayOf(-1, 1, 0, 0)
        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            // check if adjacent position is valid
            if (nx >= 0 && nx < maze.size && ny >= 0 && ny < maze[0].size && maze[nx][ny] != 1 && !visited.contains(Pair(nx, ny))) {
                // recursively search from adjacent position
                if (dfs(maze, nx, ny, visited, path)) {
                    path.add(Pair(x, y))
                    return true
                }
            }
        }

        return false
    }

    fun solveMaze(maze: Array<IntArray>, print: Boolean= false): List<Pair<Int, Int>>? {
        val start = findStart(maze)
        val visited = mutableSetOf<Pair<Int, Int>>()
        val path = mutableListOf<Pair<Int, Int>>()

        dfs(maze, start.first, start.second, visited, path)


        val PATH = if (path.isNotEmpty()) path.reversed() else null

        var solvedMaze = maze

        if(print) {
            if (PATH != null) {
                println("Path found:")
                for (p in PATH) {
                    println("(${p.first}, ${p.second})")
                    maze[p.first][p.second] = -1
                    solvedMaze += maze
                    printMaze(maze, solvedMaze[0].size)
                }
            } else {
                println("No path found")
            }
        }



        return PATH
    }

    fun findStart(maze: Array<IntArray>): Pair<Int, Int> {
        for (i in maze.indices) {
            for (j in maze[0].indices) {
                if (maze[i][j] == 2) {
                    return Pair(i, j)
                }
            }
        }
        throw IllegalArgumentException("Start not found")
    }

}