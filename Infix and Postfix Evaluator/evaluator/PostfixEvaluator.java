package evaluator;

import parser.ArithParser;
import stack.LinkedStack;

public class PostfixEvaluator extends Evaluator {

  private LinkedStack<Integer> stack = new LinkedStack<Integer>();

  /** return stack object (for testing purpose). */
  public LinkedStack<Integer> getStack() { 
    return stack; 
  }

  /** This method performs one step of evaluation of a postfix expression.
   *  The input is a token. Follow the postfix evaluation algorithm
   *  to implement this method. If the expression is invalid, throw an
   *  exception with the corresponding exception message.
   */
  public void evaluate_step(String token) throws Exception {
    if (isOperand(token)) {
            stack.push(Integer.parseInt(token));
    } else {      
      String operator = token;
      Integer A;
      Integer B;
      Integer result;
      
      if (stack.isEmpty()) {
        throw new Exception("too few operands");
      }
      
      if (stack.count == 1 && !token.equals("!")) {
        throw new Exception("too few operands");
      }

      switch (operator) {
      
      case "+":
        try {
          A = stack.pop();
          B = stack.pop();
        } catch(Exception e) {
          throw new Exception("too few operands");
        }
        result = B + A;
        stack.push(result);
        break;
        
      case "-":
        try {
          A = stack.pop();
          B = stack.pop();
        } catch(Exception e) {
          throw new Exception("too few operands");
        }
        result = B - A;
        stack.push(result);
        break;
        
      case "*":
        try {
          A = stack.pop();
          B = stack.pop();
        } catch(Exception e) {
          throw new Exception("too few operands");
        }
        result = B * A;
        stack.push(result);
        break;
        
      case "/":
        try {
          A = stack.pop();
          B = stack.pop();
        } catch(Exception e) {
          throw new Exception("too few operands");
        }

        if (A == 0) {
          throw new Exception("division by zero");
        }
        
        result = B / A;
        stack.push(result);
        break;
        
      case "!":
        try {
          A = stack.pop();
        } catch (Exception e) {
          throw new Exception("too few operands");
        }
        result = A * -1;
        stack.push(result);
        break;
        
       default:
         throw new Exception("invalid operator");
      }
    }
  }
  
  /** This method evaluates a postfix expression (defined by expr)
   *  and returns the evaluation result. It throws an Exception
   *  if the postfix expression is invalid.
   */
  public Integer evaluate(String expr) throws Exception {
    for (String token : ArithParser.parse(expr)) {
      evaluate_step(token);
    }
    // The stack should have exactly one operand after evaluation is done
    if (stack.size() > 1) {
      throw new Exception("too many operands");
    } else if (stack.size() < 1) {
      throw new Exception("too few operands");
    }
    return stack.pop(); 
  }

  public static void main(String[] args) throws Exception {
    System.out.println(new PostfixEvaluator().evaluate("50 25 ! / 3 +"));
  }
}