
// 리코쳇 로봇
// https://school.programmers.co.kr/learn/courses/30/lessons/169199

import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

public class Programmers169199 {
	public void Solution() {
		String[] board = {"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."};
		new Solution().solution(board);
	}
}

class Solution {
    public int solution(String[] board) {
        Explorer e = new Explorer(board);
        if (!e.canArrivalToGoal())
            return -1;
        
        boolean[][] visited = new boolean[board.length][board[0].length()];
        for (int i = 0; i < visited.length; i++)
            Arrays.fill(visited[i], false);
        Queue<Pos> posQ = new LinkedList<Pos>();

        int arrivalTime = 0;
        Pos initPos = e.findR();
        visited[initPos.x][initPos.y] = true;
        posQ.add(initPos);
        
        Pos currentPos, movePos;
        int qLen;
        while (!posQ.isEmpty()) {
            arrivalTime++;
            qLen = posQ.size();
            for (int i = 0; i < qLen; i++) {
                currentPos = posQ.remove();
                for (int dir = 0; dir <= 3; dir++) {
                    movePos = e.move(dir, currentPos);
                    if (!movePos.isSame(currentPos)
                       && !visited[movePos.x][movePos.y]) {
                        if (e.getTile(movePos) == 'G')
                            return arrivalTime;
                        visited[movePos.x][movePos.y] = true;
                        posQ.add(movePos);
                    }
                }
            }
        }
        return -1;
    }
}

class Pos {
    int x;
    int y;
    
    public Pos(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    boolean isSame(Pos pos) {
        return (x == pos.x && y == pos.y);
    }
}

class Explorer {
    String[] board;
    public Explorer(String[] board) {
        this.board = board;
    }
    
    // x == row Idx, y == colomn Idx
    char getTile(Pos pos) {
        return board[pos.x].charAt(pos.y);
    }
    
    Pos findR() {
    	for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length(); j++)
                if (board[i].charAt(j) == 'R')
                	return new Pos(i,j);
    	return null;
    }
    
    // direction : 0, 1, 2, 3 - ENWS
    Pos move(int direction, Pos position) {
        int xAdd = (direction-2)%2;
        int yAdd = (1-direction)%2;
        Pos pos = new Pos(position.x, position.y);
        
        do {
            pos.x += xAdd;
            pos.y += yAdd;
        } while (!isBorder(pos));
            
        pos.x -= xAdd;
        pos.y -= yAdd;
        return pos;
    }
    
    boolean isBorder(Pos position) {
        return isBorder(position.x, position.y);
    }
    
    boolean isBorder(int x, int y) {
        if (x < 0 || board.length <= x)
            return true;
        else if (y < 0 || board[x].length() <= y)
            return true;
        else if (board[x].charAt(y) == 'D')
            return true;
        else
            return false;
    }
    
    boolean canArrivalToGoal() {
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length(); j++)
                if (board[i].charAt(j) == 'G'){
                    if (!isBorder(i-1,j)
                       && !isBorder(i+1,j)
                       && !isBorder(i,j-1)
                       && !isBorder(i,j+1))
                        return false;
                    return true;
                }
        return false;
    }
}
