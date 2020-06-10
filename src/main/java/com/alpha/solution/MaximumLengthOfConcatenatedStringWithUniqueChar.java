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
import java.util.Arrays;
import java.util.List;

public class MaximumLengthOfConcatenatedStringWithUniqueChar {
	static class Solution {
		public int maxLength(List<String> arr) {
			if (null == arr || arr.isEmpty())
				return 0;
			return maxLen(arr, new boolean[26], 0, "");
		}

		/*-
		 * 1239. Maximum Length of a Concatenated String with Unique Characters
		 * Time complexity : O(2^n)
		 * Space complexity : O(n)
		 */
		private int maxLen(List<String> arr, boolean[] c, int curr, String result) {

			// Done with the list of inputs
			if (curr == arr.size())
				return result.length();

			// Current String as an array of chars
			char[] s = arr.get(curr).toCharArray();

			// Sort to find duplicate chars in current string
			Arrays.sort(s);

			// More than 26 chars will definitely have duplicate chars. Skip including this
			// string
			if (s.length > 26)
				return maxLen(arr, c, curr + 1, result);

			// Check for duplicate chars. If present then skip including this string
			for (int i = 1; i < s.length; i++) {
				if (s[i] == s[i - 1]) {
					return maxLen(arr, c, curr + 1, result);
				}
			}

			// If chars are already present in result string then skip including this string
			for (int i = 0; i < s.length; i++) {
				if (c[s[i] - 'a']) {
					return maxLen(arr, c, curr + 1, result);
				}
			}

			// Get the maxLen if this string is not included in result
			int without = maxLen(arr, c, curr + 1, result);

			// Mark all the chars in the current string as present
			for (int i = 0; i < s.length; i++) {
				c[s[i] - 'a'] = true;
			}

			// Get the maxLen if this string is included in the result
			int with = maxLen(arr, c, curr + 1, result + new String(s));

			// Reset the array
			for (int i = 0; i < s.length; i++) {
				c[s[i] - 'a'] = false;
			}

			// Return max of two cases string included vs string not included
			return Math.max(with, without);
		}
	}

	public static void main(String... args) {
		Solution s = new Solution();
		List<String> arr = new ArrayList<>();
		arr.add("un");
		arr.add("iq");
		arr.add("ue");
		System.out.println(s.maxLength(arr));
	}
}
