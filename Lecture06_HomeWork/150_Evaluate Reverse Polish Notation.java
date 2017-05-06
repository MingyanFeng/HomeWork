/*
150. Evaluate Reverse Polish Notation

Evaluate the value of an arithmetic expression in Reverse Polish Notation. 
Valid operators are +, -, *, /. Each operand may be an integer or another expression. 

Some examples:
    ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
    ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
*/

public class EvaluateREversePolishNotation {
    public int evalRPN(String[] tokens) {
        /* 
        Use a stack to store numbers.
        When meeting operators, extract two operands from the stack, calculate the result, and push the result back to the stack
        */
        Deque<Integer> stack = new ArrayDeque<>();
        for (String s : tokens) {
            if (isInt(s)) {
                int tempInt = Integer.parseInt(s);
                stack.push(tempInt);
            } else {
                int tempInt1 = stack.pop();
                int tempInt2 = stack.pop();
                int tempInt = 0;
                switch(s.charAt(0)) {
                    case '+':
                        tempInt = tempInt1 + tempInt2;
                        break;
                    case '-':
                        tempInt = tempInt2 - tempInt1;
                        break;
                    case '*':
                        tempInt = tempInt1 * tempInt2;
                        break;
                    case '/':
                        tempInt = tempInt2 / tempInt1;
                        break;
                }
                stack.push(tempInt);
            }
        }
        return stack.pop();
    }
    /*
    Time complexity: O(n);
    Space complexity: O(n);
    */
    
    private boolean isInt(String s) { // judge whether a string is an integer
        if (s.length() == 1 && s.charAt(0) - '0' < 0) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        
    }
}