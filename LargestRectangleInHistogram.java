import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;


/**
 * LeetCode 84. Largest Rectangle in Histogram
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 */
public class LargestRectangleInHistogram {


    /**
     * Given an array of integers heights representing the histogram's bar height 
     * where the width of each bar is 1, 
     * return the area of the largest rectangle in the histogram.
     * 
     * Runtime: O(n^2) - Space: O(n)
     * 
     * Runtime: 21 ms, faster than 80.11% of Java online submissions.
     * Memory Usage: 81.6 MB, less than 33.70% of Java online submissions.
     * 
     * 96 / 96 test cases passed.
     * Status: Accepted
     * Runtime: 21 ms
     * Memory Usage: 81.6 MB
     */
    static public int largestRectangleArea(int[] heights) {
        
        // **** sanity check(s) ****
        int n = heights.length;
        if (n == 0) return 0;

        // **** initialization ****
        int maxArea     = 0;

        int[] left      = new int[n];
        int[] right     = new int[n];
        left[0]         = -1;
        right[n - 1]    = n;

        // **** populate right array (right to left) ****
        for (int i = n - 2; i >= 0; i--) {

            // **** next index ****
            int next = i + 1;

            // **** look for heights[i] >= heights - O(n) ****
            while (next < n && heights[next] >= heights[i])
                next = right[next];

            // **** populate this right entry ****
            right[i] = next;

            // ???? ????
            System.out.print("<<< heights[" + i + "]: " + heights[i] + " >= heights[" + next + "]: ");
            if (next >= n)
                System.out.println("out of bounds");
            else System.out.println(heights[next]);
        }

        // **** populate left array (left to right) - O(n) ****
        for (int i = 1; i < n; i++) {

            // **** previous index ****
            int prev = i - 1;

            // **** look for height <= heights[i] - O(n) ****
            while (prev >= 0 && heights[prev] >= heights[i])
                prev = left[prev];

            // **** populate this left entry ****
            left[i] = prev;

            // ???? ????
            System.out.print("<<< heights[" + prev + "]: ");
            if (prev < 0)
                System.out.print("out of bounds");
            else System.out.print(heights[prev]);
            System.out.println(" <= heights[" + i + "]: " + heights[i]);
        }

        // ???? ????
        System.out.println("<<<   right: " + Arrays.toString(right));
        System.out.println("<<<    left: " + Arrays.toString(left));

        // **** loop computing and updating maxArea - O(n) ****
        for (int i = 0; i < n; i++) {

            // **** width of rectangle ****
            int width = right[i] - left[i] - 1;

            // ???? ????
            System.out.println("<<<   width: " + width + " height[" + i + "]: " + heights[i]);

            // **** compute area ****
            int area = heights[i] * width;

            // ???? ????
            System.out.println("<<<    area: " + area);

            // **** update maxArea (if needed) ****
            maxArea = Math.max(maxArea, area);

            // ???? ????
            System.out.println("<<< maxArea: " + maxArea);
        }

        // **** return maxArea ****
        return maxArea;
    }


    /**
     * Given an array of integers heights representing the histogram's bar height 
     * where the width of each bar is 1, 
     * return the area of the largest rectangle in the histogram.
     * 
     * Execution: O(n) - Space: O(n)
     * 
     * Runtime: 79 ms, faster than 14.67% of Java online submissions.
     * Memory Usage: 88.9 MB, less than 21.78% of Java online submissions.
     * 
     * 96 / 96 test cases passed.
     * Status: Accepted
     * Runtime: 79 ms
     * Memory Usage: 88.9 MB
     */
    static public int largestRectangleArea0(int[] heights) {
        
        // **** sanity check(s) ****
        int n = heights.length;
        if (n == 0) return 0;

        // **** initialization ****
        int maxArea         = 0;
        Stack<Integer> ms   = new Stack<>();

        // **** traverse input array - O(n) ****
        for (int i = 0; i <= n; i++) {

            // **** set current height ****
            int height = i == n ? 0 : heights[i];

            // ???? ????
            System.out.println("<<<       i: " + i + " height: " + height);

            // **** loop computing and updating maxArea - O(1) ****
            while (!ms.empty() && height < heights[ms.peek()]) {

                // **** index from stack ****
                int j = ms.pop();

                // ???? ????
                System.out.println("<<<       j: " + j);
                
                // **** compute width of rectangle *****
                int width = ms.isEmpty() ? i : i - ms.peek() - 1;

                // ???? ????
                System.out.println("<<<   width: " + width);

                // **** compute rectangle area ****
                int area = heights[j] * width;

                // ???? ????
                System.out.println("<<<    area: " + area);

                // **** update maxArea as needed ****
                maxArea = Math.max(maxArea, area);

                // ???? ????
                System.out.println("<<< maxArea: " + maxArea);
            }

            // **** push current index ****
            ms.push(i);

            // ???? ????
            System.out.println("<<<      ms: " + ms.toString());
        }

        // **** return maxArea ****
        return maxArea;
    }


    /**
     * Test scaffold.
     * !!! NOT PART OF SOLUTION !!!
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read int[] heights ****
        int[] heights = Arrays.stream(br.readLine().trim().split(","))
                            .mapToInt(Integer::parseInt)
                            .toArray();

        // **** close buffered reader ****
        br.close();
        
        // ???? ????
        System.out.println("main <<< heights: " + Arrays.toString(heights));

        // **** call function of interest and display result ****
        System.out.println("main <<< largestRectangleArea0: " + largestRectangleArea0(heights));

        // **** call function of interest and display result ****
        System.out.println("main <<<  largestRectangleArea: " + largestRectangleArea(heights));
    }
}