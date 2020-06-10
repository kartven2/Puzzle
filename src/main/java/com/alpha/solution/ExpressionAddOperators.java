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
 * 282. Expression Add Operators
 * Time Complexity: O(4^n)
 * Space Complexity: O(n)
 * 
 */
public class ExpressionAddOperators {
	static class Solution {

		// list of possible operators
		private static final String[] operators = { "", "+", "-", "*" };

		private boolean isNum(char c) {
			// check if the character is a digit between 0-9
			int val = c - '0';
			return val >= 0 && val <= 9;
		}

		private String eval(String exp, int target) {
			// result of evaluvating the infix expression
			long result = 0;

			// variable to store value in case of * operator
			long prev = 0l;

			// variable to store parsed value
			long curr = 0l;

			// placeholder until first operator is encountered
			char op = Character.MIN_VALUE;

			for (int i = 0; i < exp.length();) {

				// if the char is a digit then get the operand
				if (isNum(exp.charAt(i))) {

					// get the operand as a string
					StringBuilder operand = new StringBuilder();

					// loop until you get the operand
					while (i < exp.length() && isNum(exp.charAt(i))) {
						operand.append(exp.charAt(i));
						i++;
					}

					// parse operand string to long value
					String opstr = operand.toString();
					curr = Long.valueOf(opstr);

					// if operand is invalid such as "063" then ignore evaluvating the expression
					if (opstr.charAt(0) == '0' && opstr.length() > 1)
						return null;

					// switch on operator
					// in all cases keep track of prev value which will come in handy if there is *
					// op
					switch (op) {

					// add current value to result
					case '+':
						result += curr;
						prev = curr;
						break;

					// subtract current value from result
					case '-':
						result -= curr;
						prev = -curr;
						break;

					// For multiplication we have to undo the previous value from the result
					// since we are keeping track of prev value we can just
					// 1.subtract prev from result and 2. add prev*curr to the result
					// And save prev*curr as the prev value.
					case '*':
						result = result - prev + prev * curr;
						prev = prev * curr;
						break;

					// For an empty char basically for first operand just initialize result with
					// curr
					case Character.MIN_VALUE:
						result = curr;
						prev = curr;
						break;
					}

				} else {
					// if the char is an operator then just set the op
					op = exp.charAt(i++);
				}
			}

			// return the expression if it evaluvates to target or else null
			return (result == (long) target) ? exp : null;
		}

		public List<String> addOperators(String num, int target) {
			// result list
			List<String> result = new ArrayList<>();

			// input validiation
			if (null == num || num.length() == 0)
				return result;

			// Recursive expression construction and collection of results
			collect(result, num, target, 0, "");

			// Return result
			return result;
		}

		private void collect(List<String> result, String num, int target, int i, String expr) {
			// if num.length has been reached
			if (i == num.length()) {

				// evaluvate the expression and add to the result if it's not null
				if (null != eval(expr, target)) {
					result.add(expr);
				}
				return;
			}

			// don't add operator to an empty expression. make sure the expression starts
			// with an operand
			if (expr.equals("")) {
				collect(result, num, target, i + 1, expr + num.charAt(i));
				return;
			}

			// add all possible set of operators and digit combination: "", "+", "-", "*"
			for (String c : operators) {
				collect(result, num, target, i + 1, expr + c + num.charAt(i));
			}
		}
	}

	public static void main(String... args) {
		Solution s = new Solution();
		for (String res : s.addOperators("232", 8))
			System.out.println(res);
	}

}
