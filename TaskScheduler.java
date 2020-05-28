// Time Complexity: O(n)
// Space Complexity: O(1)
 class Solution {
     public int leastInterval(char[] tasks, int n) {
         int[] char_map = new int[26];

         for(int c : tasks) {
             char_map[c - 'A']++;
         }

         Arrays.sort(char_map); // Sprting 26 integers. Does not affect time complexity
         int val = char_map[25] - 1;
         int idle_slots = val * n;

         for(int i = 24; i >= 0; i--) {
             idle_slots -= Math.min(char_map[i], val);
         }

         return idle_slots + tasks.length;

     }
 }

// Using Priority Queue
// Time Complexity: O(n)
// Space Complexity: O(n)
class Solution {
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        for(char c : tasks) {
            map.put(c , map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });



        pq.addAll(map.values());
        int cycles = 0;

        while(!pq.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i <= n; i++) {
                if(!pq.isEmpty()) {
                    list.add(pq.poll());
                }
            }

            for(int temp: list) {
                if(temp > 1) {
                    pq.add(temp - 1);
                }
            }

            cycles += pq.isEmpty()? list.size() : n + 1;
        }
        return cycles;
    }
}