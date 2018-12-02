package exam.review.leetcode.hashset;

/**
 * https://leetcode.com/problems/keys-and-rooms/description/
 * 
 */
public class KeysAndRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Stack<List<Integer>> buffer = new Stack<>();
        HashSet<Integer> seen = new HashSet<>();
        buffer.push(rooms.get(0));
        seen.add(0);
        while(!buffer.isEmpty()) {
            List<Integer> roomKeys = buffer.pop();
            for(Integer key: roomKeys) {
                if(!seen.contains(key)) {
                    seen.add(key);
                    buffer.push(rooms.get(key));
                }
            }
        }
        return rooms.size() == seen.size();        
    }

    /**
     * DFS solution
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        dfs(rooms,0,visited);
        for(int i = 0; i < visited.length; i++){
            if(!visited[i])
                return false;
        }
        
        return true;
    }
    
    public void dfs(List<List<Integer>> rooms, int room, boolean[] visited){
        if(visited[room])
            return;
        else
            visited[room] = true;
        
        List<Integer> current = rooms.get(room);
        
        for(int i = 0;i < current.size(); i++){
            dfs(rooms,current.get(i),visited);
        }
    }
}