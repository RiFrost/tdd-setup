package tdd.setup;

// behaviour inspired by https://www.online-calculator.com/
public class Calculator {

    private String screen = "0";

    private double latestValue;

    private String latestOperation = "";

    private boolean firstInput = true;

    public String readScreen() {
        return screen;
    }
    public void pressDigitKey(int digit) {
        if(digit > 9 || digit < 0) throw new IllegalArgumentException();

        if(latestOperation.isEmpty() && !firstInput) {
            screen = screen + digit;
        } else {
            screen = Integer.toString(digit);
        }

        firstInput = false;
    }

    public void pressClearKey() {
        screen = "0";
        latestOperation = "";
        latestValue = 0.0;
        firstInput = true;
    }

    public void pressOperationKey(String operation)  {
        if(!latestOperation.isEmpty()) {
            latestValue = switch(latestOperation) {
                case "+" -> latestValue + Double.parseDouble(screen);
                case "-" -> latestValue - Double.parseDouble(screen);
                case "x" -> latestValue * Double.parseDouble(screen);
                case "/" -> latestValue / Double.parseDouble(screen);
                default -> throw new IllegalArgumentException();
            };

            screen = Double.toString(latestValue);
            if(screen.endsWith(".0")) screen = screen.substring(0,screen.length()-2);
        } else {
            latestValue = Double.parseDouble(screen);
        }

        latestOperation = operation;
    }

    public void pressDotKey() {
        screen = screen.contains(".") ? screen : screen + ".";
    }

    public void pressNegative() {
        screen = screen.startsWith("-") ? screen.substring(1) : "-" + screen;
    }

    public void pressEquals() {
        var result = switch(latestOperation) {
            case "+" -> latestValue + Double.parseDouble(screen);
            case "-" -> latestValue - Double.parseDouble(screen);
            case "x" -> latestValue * Double.parseDouble(screen);
            case "/" -> latestValue / Double.parseDouble(screen);
            default -> throw new IllegalArgumentException();
        };
        screen = Double.toString(result);
        if(screen.endsWith(".0")) screen = screen.substring(0,screen.length()-2);
    }
}
