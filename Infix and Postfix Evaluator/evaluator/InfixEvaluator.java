package evaluator;

import parser.ArithParser;
import stack.LinkedStack;

public class InfixEvaluator extends Evaluator {

  private LinkedStack<String> operators = new LinkedStack<String>();
  private LinkedStack<Integer> operands  = new LinkedStack<Integer>();

  /** return stack object (for testing purpose). */
  public LinkedStack<String> getOperatorStack() { 
    return operators; 
  }
  
  public LinkedStack<Integer> getOperandStack() { 
    return operands;
  }


  /** This method performs one step of evaluation of a infix expression.
   *  The input is a token. Follow the infix evaluation algorithm
   *  to implement this method. If the expression is invalid, throw an
   *  exception with the corresponding exception message.
   */
  public void evaluate_step(String token) throws Exception {
    if (isOperand(token)) {
      // TODO: What do we do if the token is an operand?
      operands.push(Integer.parseInt(token));
    } else {
      /* TODO: What do we do if the token is an operator?
               If the expression is invalid, make sure you throw
               an exception with the correct message.

               You can call precedence(token) to get the precedence
               value of an operator. It's already defined in 
               the Evaluator class.
       */ 
      String oper = token;
      
      if(oper.equals("(")) {
        operators.push(token);
      }
      
      else if (operators.count == 0 || precedence(token) > precedence(operators.top())) {
        operators.push(token);
      }
      
      else if (oper.equals(")")) {
        while(!operators.top().equals("(")) {
          process_operator();
          if(operators.count == 0) {
            throw new Exception("missing (");
          }
        }
        operators.pop(); //check to make sure this is in right spot
      }
      
      else {
        while(true) {
          process_operator();
          if(operators.count == 0 || precedence(token) > precedence(operators.top())) {
            operators.push(token);
            break;
          }
        }
      }
      
      
      
      
    }
  }
  
  public void process_operator() throws Exception{
    String operator = operators.pop();
    Integer A;
    Integer B;
    Integer result;
    
    if(operands.count == 0) {
      throw new Exception("too few operands");
    }
    
    if(operands.count == 1 && !operator.equals("!")) {
      throw new Exception("too few operands");
    }
    
    switch(operator) {
    
    case "+":
      try {
        A = operands.pop();
        B = operands.pop();
      } catch (Exception e){
        throw new Exception("too few operands");
      }
      result = B + A;
      break;
      
    case "-":
      try {
        A = operands.pop();
        B = operands.pop();
      } catch (Exception e) {
        throw new Exception("too few operands");
      }
      result = B - A;
      break;
      
    case "*":
      try {
        A = operands.pop();
        B = operands.pop();
      } catch(Exception e) {
        throw new Exception("too few operands");
      }
      result = B * A;
      break;
      
    case "/":
      try {
        A = operands.pop();
        B = operands.pop();
      } catch(Exception e) {
        throw new Exception("too few operands");
      }
      if(A == 0) { 
        throw new Exception("division by zero");
        }
      result = B / A;
      break;
      
    case "!":
      try {
        A = operands.pop();
      } catch (Exception e) {
        throw new Exception("too few operands");
      }
      result = A * -1;
      break;
      
    default: 
      throw new Exception("invalid operator");
    }
    operands.push(result);
    
  }
  
  
  /** This method evaluates an infix expression (defined by expr)
   *  and returns the evaluation result. It throws an Exception object
   *  if the infix expression is invalid.
   */
  public Integer evaluate(String expr) throws Exception {

    for (String token : ArithParser.parse(expr)) {
      evaluate_step(token);
    }

    /* TODO: what do we do after all tokens have been processed? */
    while(operators.count != 0) {
      process_operator();
    }

    // The operand stack should have exactly one operand after the evaluation is done
    if (operands.size() > 1) {
      throw new Exception("too many operands");
    } else if (operands.size() < 1) {
      throw new Exception("too few operands");
    }

    return operands.pop();
  }

  public static void main(String[] args) throws Exception {
    System.out.println(new InfixEvaluator().evaluate("5+(5+2*(5+9))/!8"));
  }
}
