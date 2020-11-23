import java.util.*;

/**
 * Program class for an SRPN calculator. Current it outputs "0" for every "=" sign.
 */

public class SRPN {

    private Stack <Long> myStack = new Stack(); // Stack created as type Long to store numbers.
    private long result =0; //variable used to store the result in any calculation.
    private long lastValue =0; // variable used to make sure we calculating per order of entry in stack.
    private char operator =' '; // operator char variable to control the operations in calcstack method.
    private int stacksize = 23; //Stack size for SRPN as per test 4 excercise seems 23 elements max in the stack.

    // create instance of Random class
    Random rand = new Random();

    //method Process command
    public void processCommand(String s) {

        String toAddToStack = ""; //this string is going to have the selected values we want to add to our stack.

        //for cycle to understand each character inserted per line and make decisions to what to do with it.
        for (int i = 0; i < s.length(); i++) {
                if ((s.charAt(i) >= '0' && s.charAt(i) <= '9')) toAddToStack = toAddToStack + ((s.charAt(i))); //if its a numeric 0 up to 9 add to variable toAddToStack.
                else {
                    switch (s.charAt(i)) {
                        case ('='):
                            if(myStack.size()==0){
                                System.out.println("Stack empty.");
                            }
                            else if(myStack.size()>1) {
                                calcStack(operator);
                                System.out.println(myStack.peek());
                            }
                            else {
                                //calcStack(operator);
                                System.out.println(myStack.peek());
                            }
                            toAddToStack ="";
                            break;
                        case (' '):
                            //if (toAddToStack != "") addToMyStack((Integer.parseInt(String.valueOf(toAddToStack))));
                            toAddToStack ="";
                            break;
                        case ('r'):
                            if (toAddToStack != "") addToMyStack((Long.parseLong(toAddToStack)));
                            addToMyStack(rand.nextInt(Integer.MAX_VALUE));
                            toAddToStack ="";
                            break;
                        case ('d'):
                            if (toAddToStack != "") addToMyStack((Long.parseLong(toAddToStack)));
                            displayStack();
                            toAddToStack ="";
                            break;
                        case ('+'):
                            operator = '+';
                            if (toAddToStack != "") addToMyStack((Long.parseLong(toAddToStack)));
                            if (myStack.size() > 1) calcStack(operator);
                                else System.out.println("Stack underflow");
                            toAddToStack = "";
                            break;
                        case ('-'):
                            //to deal with negative numbers
                            if(i==0 && s.length()>1)  toAddToStack = String.valueOf(s.charAt(i));
                            else {
                                operator = '-';
                                if (toAddToStack != "") addToMyStack((Long.parseLong(toAddToStack)));
                                else if (myStack.size() > 1) calcStack(operator);
                                else System.out.println("Stack underflow");
                                toAddToStack = "";
                            }
                            break;
                        case ('*'):
                            operator = '*';
                            if (toAddToStack != "") addToMyStack((Long.parseLong(toAddToStack)));
                            else if (myStack.size() > 1) calcStack(operator);
                            else System.out.println("Stack underflow");
                            toAddToStack = "";
                            break;
                        case ('/'):
                            operator = '/';
                            if (toAddToStack != "") addToMyStack((Long.parseLong(toAddToStack)));
                            else if (myStack.size() > 1) calcStack(operator);
                            else System.out.println("Stack underflow");
                            toAddToStack = "";
                            break;
                        case ('%'):
                            operator = '%';
                            if (toAddToStack != "") addToMyStack((Long.parseLong(toAddToStack)));
                            else if (myStack.size() > 1) calcStack(operator);
                            else System.out.println("Stack underflow");
                            toAddToStack = "";
                            break;
                        case ('^'):
                            operator = '^';
                            if (toAddToStack != "") addToMyStack((Long.parseLong(toAddToStack)));
                            else if (myStack.size() > 1) calcStack(operator);
                            else System.out.println("Stack underflow");
                            toAddToStack = "";
                            break;
                        default:
                            System.out.println("unrecognized operator or operand: " + '"' + s.charAt(i) + '"');
                            break;
                    }
                }
       }
       if (toAddToStack!="") addToMyStack((Long.parseLong(toAddToStack))); // adds to stack any remain value entered when finishes cycle that goes through entire string of characters.
    }

    //function that runs all objects inside the stack and displays all of them per line.
    public void displayStack() {
        for (Object item : myStack) {
                    System.out.println(item);
        }
    }

    //function used before any push to the stack , converts any long number received that is bigger than the MAX_INTEGER , returning the Max_value of the Integer.
    public long manageSaturation(long a) {
        int b;
        if(a>=Integer.MAX_VALUE) a=Integer.MAX_VALUE;
        return a;
    }

    //function used to add values to stack receives a long and pushes to stack after managing saturation and if stack is not full.
    public void addToMyStack(long c) {
        if(myStack.size()<stacksize){
            myStack.push(manageSaturation(c));
        }
        else System.out.println("Stack Overflow");
    }

    //function to calculate operations , receives the operator and with that performs operations from values of the stack.
    public void calcStack(char operator) {
        int resultpow = 0;
        if(lastValue == 0 ) lastValue= myStack.pop();
        if(!myStack.isEmpty()) {
            if(operator == '+') result = myStack.pop() + lastValue;
            if(operator == '-') result = myStack.pop() - lastValue;
            if(operator == '*') result = myStack.pop() * lastValue;
            if(operator == '/') {
                if(lastValue==0) System.out.println("Divide by 0.");
                else result = myStack.pop() / lastValue;
            }
            if(operator == '%') result = myStack.pop() % lastValue;
            if(operator == '^') {
                resultpow = (int) Math.pow(myStack.pop(), lastValue);
                result = resultpow;
            }
            lastValue=0;
        }
        addToMyStack(result);
    }

}
