import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] count = new int[k];

   
        TreeSet<Integer> free = new TreeSet<>();

        for (int i = 0; i < k; i++) {
            free.add(i);
        }

       
        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        for (int i = 0; i < arrival.length; i++) {

            int time = arrival[i];

         
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                int[] completed = busy.poll();
                free.add(completed[1]);
            }

          
            if (free.isEmpty()) {
                continue;
            }

          
            int preferred = i % k;

            Integer server = free.ceiling(preferred);

            
            if (server == null) {
                server = free.first();
            }

           
            count[server]++;

            int finishTime = arrival[i] + load[i];

            busy.offer(new int[]{finishTime, server});

            free.remove(server);
        }

  
        int max = 0;

        for (int c : count) {
            max = Math.max(max, c);
        }

    
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                result.add(i);
            }
        }

        return result;
    }
}