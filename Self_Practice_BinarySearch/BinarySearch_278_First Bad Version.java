/*
You are a product manager and currently leading a team to develop a new product. 

Unfortunately, the latest version of your product fails the quality check. 

Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which will return whether version is bad. 

Implement a function to find the first bad version. You should minimize the number of calls to the API.
*/

/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
// Version 1: 自己写的，后来发现多写了一个多余的变量，所以改进了一下。
    public int firstBadVersion(int n) {
        /*
        Use binary search to find the first bad version among 1 to n;
        */
        
        int start = 1;
        int end = n;
        //int first = 0;
        
        while (start + 1 < end) {
            int mid = (start + end) >>> 1;
            if (isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid;
            }
            //first = mid;
        }
        
        if (isBadVersion(start)) {
            return start;
        }
        //if (isBadVersion(end)) {
        return end;
        //}
        
        //return first;
    }
    /*
    Time Complexity: O(logn), n == input n;
    Space Complexity: O(1), start + mid == O(1) extra space;
    */
}