/*
57. Insert Interval

Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
You may assume that the intervals were initially sorted according to their start times.

Example 1:
    Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9]. 
Example 2:
    Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16]. 
    This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10]. 
*/

public class InsertInterval {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new LinkedList<>();
        
        int i = 0;
        // add all the intervals before the new interval
        while (i < intervals.size() && newInterval.start > intervals.get(i).end) {
            result.add(intervals.get(i++));
        }
        
        while (i < intervals.size() && newInterval.end >= intervals.get(i).start) {
            // when the start of the new interval is found, find the end of the new interval
            newInterval = new Interval(Math.min(newInterval.start, intervals.get(i).start), 
                                        Math.max(newInterval.end, intervals.get(i).end));
            i++;
        }
        result.add(newInterval);
        
        // add all the rest intervals
        while (i < intervals.size()) {
            result.add(intervals.get(i++));
        }
        
        return result;
    }
    /*
    Time complexity: O(n)
    Space complexity: O(1)
    */

    public static void main(String[] args) {
        
    }
}