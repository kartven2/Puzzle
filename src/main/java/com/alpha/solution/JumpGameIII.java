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

import java.util.ArrayList;
import java.util.List;

/*-
 * 1306. Jump Game III
 * Time Complexity: O(n+2n)
 * Space Complexity: O(2n)
 * 
 */
public class JumpGameIII {
	static class Solution {
		public boolean canReach(int[] arr, int s) {

			// Validate input
			if (null == arr || arr.length == 0)
				return false;

			// adjacency list for all the nodes
			int[][] adj = new int[arr.length][2];

			// List of destination indexes where value is 0
			List<Integer> dest = new ArrayList<>();

			// Create the graph
			for (int i = 0; i < arr.length; i++) {
				adj[i][0] = i - arr[i];
				adj[i][1] = i + arr[i];
				// Get all destination vertex
				if (arr[i] == 0) {
					dest.add(i);
				}
			}

			// Visited vertex to identify and avoid cycle
			boolean[] visited = new boolean[arr.length];

			// For each destination check if there is a path from source to dest
			for (int d : dest) {

				// If destination is reachable from any source then return true
				if (isReachable(s, d, adj, visited)) {
					return true;
				}
			}

			// If none of the destination is reachable from source return false
			return false;
		}

		private boolean isReachable(int s, int d, int[][] adj, boolean[] visited) {

			// if current node is destination return true
			if (s == d)
				return true;

			// if it's a cycle then there is no path to destination
			if (visited[s])
				return false;

			// mark node as visited
			visited[s] = true;

			// Check if destination is reachable from any of the adj nodes
			for (int w : adj[s]) {
				if (w >= 0 && w < adj.length) {
					if (isReachable(w, d, adj, visited)) {
						return true;
					}
				}
			}

			// unset the flag while unwinding
			visited[s] = false;

			// return false if there is no path to destination
			return false;
		}
	}

	public static void main(String... args) {
		Solution s = new Solution();
		int[] arr = { 4, 2, 3, 0, 3, 1, 2 };
		int start = 5;
		System.out.println(s.canReach(arr, start));
	}
}
