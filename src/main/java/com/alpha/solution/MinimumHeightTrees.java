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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*-
 * 310. Minimum Height Trees
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class MinimumHeightTrees {
	static class Solution {
		public List<Integer> findMinHeightTrees(int n, int[][] edges) {

			List<Integer> result = new ArrayList<>();

			//if size of tree is < 3 then return all the 
			//vertex as all vertex are a part of Minimum Height Trees
			if (n < 3) {
				for (int i = 0; i < n; i++) {
					result.add(i);
				}
				return result;
			}

			//Set to maintain adjacency list for graph
			Set<Integer>[] adj = new HashSet[n];
			
			//indegree counter for all vertex in the graph
			int[] indegree = new int[n];

			//Populate adjacency list for the graph and indegree vertex count
			for (int i = 0; i < edges.length; i++) {
				for (int j = 0; j < 2; j++) {
					if (adj[edges[i][j]] == null) {
						adj[edges[i][j]] = new HashSet<>();
					}
					adj[edges[i][j]].add(edges[i][(j + 1) % 2]);
					indegree[edges[i][j]]++;
				}
			}

			//Queue for running BFS
			Queue<Integer> q = new LinkedList<>();
			for (int i = 0; i < n; i++) {
				//Start with leaf nodes
				if (indegree[i] == 1) {
					q.add(i);
				}
			}

			//boolean to maintain visited nodes
			boolean[] marked = new boolean[n];
			
			//Trim the tree until we have < 2 nodes
			while (n > 2) {
				
				//get the size of the queue
				int sz = q.size();
				
				//For each leaf node poll from the queue
				//Remove the leaf node from the tree
				//Add new leaf nodes to the queue if any
				for (int i = 0; i < sz; i++) {
					
					//For each leaf node poll from the queue
					int v = q.poll();
					
					for (int w : adj[v]) {
						
						//For adjacent node remove the edge
						adj[w].remove(v);
						adj[v].remove(w);
						
						//Reduce the indegree count
						indegree[w]--;
						indegree[v]--;
						
						//Reduce the vertex count
						n--;
						
						//if the new adjacent node is being visited first time
						// And if it's a leaf node then add the node to the queue
						if (!marked[w] && indegree[w] == 1) {
							marked[w] = true;
							q.add(w);
						}
					}
				}
			}

			//1 or 2 nodes may remain in the queue
			//if these nodes are the root then we will end up with a MHT
			while (!q.isEmpty()) {
				//Return the remaining nodes as result
				result.add(q.poll());
			}

			return result;
		}

	}

	public static void main(String... args) {
		Solution s = new Solution();
		int[][] edges = { { 1, 0 }, { 1, 2 }, { 1, 3 } };
		s.findMinHeightTrees(4, edges);
	}
}
