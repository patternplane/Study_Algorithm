
// 요격 시스템
// https://school.programmers.co.kr/learn/courses/30/lessons/181188

import java.util.PriorityQueue;
import java.util.Comparator;

public class Programmers181188 {
	public int solution(int[][] targets) {
        PriorityQueue<targetPos> endPointHeap
            = new PriorityQueue<targetPos>(new targetPosComparator());
        
        for (int[] targetData : targets) 
            endPointHeap.add(new targetPos(targetData[0], targetData[1]));
        
        int answer = 0;
        int lastEndPos = -1;
        targetPos missileData;
        while (!endPointHeap.isEmpty()) {
            missileData = endPointHeap.poll();
            if (lastEndPos <= missileData.startPos) {
                answer++;
                lastEndPos = missileData.endPos;
            }
        }
        
        return answer;
    }
}

class targetPosComparator implements Comparator<targetPos> {
    public int compare(targetPos a, targetPos b) {
        return a.endPos - b.endPos;
    }
}

class targetPos {
    int startPos;
    int endPos;
    
    public targetPos(int startPos, int endPos) {
        this.startPos = startPos;
        this.endPos = endPos;
    }
}
