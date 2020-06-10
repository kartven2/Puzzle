/*-
Copyright [2020] Karthik.V

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.alpha.solution;

import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

/*-
 * 503. Next Greater Element II
 * Time Complexity : O(2n)
 * Space Complexity : O(n)
 */
public class NextGreaterElementII {
	public int[] nextGreaterElements(int[] nums) {
		// input validation
		if (null == nums || nums.length == 0)
			return nums;

		// result array
		int[] result = new int[nums.length];

		// stack to keep track of greatest/current element index
		Stack<Integer> stack = new Stack<>();

		// traverse from right to left twice to account for circular array
		// first traversal will only consider next greatest elements to the right of the
		// current element
		// second traversal will consider next greatest elements from the left of the
		// current element
		for (int i = (2 * nums.length) - 1; i >= 0; i--) {

			// use modulo to get the valid index
			int idx = i % nums.length;

			// get curr value
			int curr = nums[idx];

			// remove elements from stack whose values are <= curr value
			// this ensures that stack.peek() always contains the next greatest element
			while (!stack.isEmpty() && nums[stack.peek()] <= curr) {
				stack.pop();
			}

			// if stack is empty
			if (stack.isEmpty()) {

				// set value to -1;
				result[idx] = -1;

				// push the current index to the stack
				stack.push(idx);

				continue;
			}

			// set the top element from the stack as the next greatest element
			result[idx] = nums[stack.peek()];

			// push the current index to the stack
			stack.push(idx);
		}

		// return result
		return result;
	}

	public static void main(String... args) {
		NextGreaterElementII ng = new NextGreaterElementII();
		int[] nums = { 1, 2, 1 };
		int[] result = ng.nextGreaterElements(nums);
		String output = Arrays.stream(result).mapToObj(String::valueOf).collect(Collectors.joining(", "));
		System.out.println(output);
	}

}
