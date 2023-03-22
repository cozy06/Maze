import com.github.cozy06.MazePrint
import java.util.ArrayList

class MazeGen {
    private fun isGoodPath(maze: Array<IntArray>, point: IntArray): Boolean {
        if (point[0] < 0 || point[0] >= maze.size || point[1] < 0 || point[1] >= maze[0].size) {
            return false
        }
        val dirs = arrayOf(intArrayOf(0, -1), intArrayOf(1, 0), intArrayOf(0, 1), intArrayOf(-1, 0))
        var countPath = 0
        for (dir in dirs) {
            val newPoint = intArrayOf(point[0] + dir[0], point[1] + dir[1])
            if (newPoint[0] < 0 || newPoint[0] >= maze.size || newPoint[1] < 0 || newPoint[1] >= maze[0].size) {
                continue
            }
            if (maze[newPoint[0]][newPoint[1]] == 0) {
                countPath++
            }
        }
        return countPath <= 1
    }

    fun generateMaze(size: Int, print: Boolean = false, maze: Array<IntArray> = Array(size) { IntArray(size) { 1 } }, row: Int = 0, col: Int = 0): Array<IntArray> {
        val dirs = arrayOf(intArrayOf(0, -1), intArrayOf(1, 0), intArrayOf(0, 1), intArrayOf(-1, 0))
        val directions: MutableList<IntArray?> = ArrayList()
        for (dir in dirs) {
            directions.add(dir)
        }
        directions.shuffle()
        for (dir in directions) {
            val newRow = row + dir!![0]
            val newCol = col + dir[1]
            val newPoint = intArrayOf(newRow, newCol)
            if (isGoodPath(maze, newPoint)) {
                maze[newRow][newCol] = 0
                generateMaze(size, maze= maze, row= newRow, col= newCol)
            }
        }

        maze[0][size-1] = 2
        maze[size-1][0] = 3


        val GeneratedMaze = maze.map { it.reversedArray() }.toTypedArray()


        if(print) {
            MazePrint().printMaze(GeneratedMaze, size)
        }

        return GeneratedMaze
    }
}