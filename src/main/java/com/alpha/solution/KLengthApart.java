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

/*-
 * 1437. Check If All 1's Are at Least Length K Places Away
 * Space Complexity: O(n)
 * Time Complexity: O(1)
 */
public class KLengthApart {
	public boolean kLengthApart(int[] nums, int k) {

		// input validation
		if (null == nums || nums.length == 0 || k == 0)
			return true;

		int n = nums.length;
		int start = 0;

		// Find the first 1 in the array
		while (start < n && nums[start] == 0) {
			start++;
		}

		// set skip variable to k to pass check for the first 1
		int skip = k;

		for (int i = start; i < n; i++) {

			// if the value is 1
			if (nums[i] == 1) {

				// check if skip is atleast equal to k
				if (skip >= k) {

					// reset skip count
					skip = 0;

				} else {

					// if skip is < k return false
					return false;
				}
			} else {

				// increment skip count if value is 0
				skip++;
			}
		}

		// return true if we reach end of array
		return true;
	}

	public static void main(String... args) {
		KLengthApart ka = new KLengthApart();
		int[] nums = { 1, 0, 0, 1, 0, 1 };
		System.out.println(ka.kLengthApart(nums, 1));
	}

}
