import java.util.Scanner;

public class Menu implements ResultOperationHistory {
    Scanner sc = new Scanner(System.in);
    Multiplication multiplication = new Multiplication();
    Division division = new Division();
    Subtraction subtraction = new Subtraction();
    Addition addition = new Addition();
    private double one;
    private double two;
    private String symbolOperation;
    private double res;
    private double[] history = new double[3];
    private int operationCounter = 0;

    public Menu() {

    }

    public void answer() {
        try {
            menuOperation();
        } catch (unvalidMenuException e) {
            sc.next();
            answer();
        }
    }

    private void menuOperation() throws unvalidMenuException {
        System.out.println("Калькулятор работает");
        System.out.println("Для подсчета мат операции введите - 1");
        System.out.println("Для вывода истории опреций введите - 2");
        System.out.println("Для выхода введите - 0");
        razdelitel();
        while (!sc.hasNext("0")) {
            if (sc.hasNext("1")) {
                result();
            } else if (sc.hasNext("2")) {
                outputHistory();
                sc.next();
                menuOperation();
            } else {
                throw new unvalidMenuException();
            }
        }
    }

    private void result() throws unvalidMenuException {
        firstNumber();
        secondNumber();
        System.out.println("Введите мат операцию");
        res = whichMethod();
        razdelitel();
        System.out.println("Результат " + res);
        razdelitel();
        addResulHistory();
        operationCounter++;
        menuOperation();

    }

    private void firstNumber() {
        System.out.println("Введите 1ый символ");
        sc.next();
        one = numbers();
    }

    private void secondNumber() {
        System.out.println("Введите 2ой символ");
        two = numbers();
    }

    private double numbers() {
        double number = 0;
        while (!sc.hasNextDouble()) {
            System.out.println("Вы ввели неверное число");
            sc.next();
        }
        return number = sc.nextDouble();
    }

    private void symbol() throws outOfMathOperation {
        char oper;
        boolean ans = false;
        while (!ans) {
            switch (oper = sc.next().charAt(0)) {
                case '+':
                    this.symbolOperation = "Addition";
                    ans = true;
                    break;
                case '-':
                    this.symbolOperation = "Subtraction";
                    ans = true;
                    break;
                case '/':
                    this.symbolOperation = "Division";
                    ans = true;
                    break;
                case '*':
                    this.symbolOperation = "Multiplication";
                    ans = true;
                    break;
                default:
                    throw new outOfMathOperation();
            }
        }
    }

    private double whichMethod() {
        try {
            symbol();
        } catch (outOfMathOperation e) {
            System.out.println("Введите валидный символ ( + , - , * , / )");
            whichMethod();
        }
        String method = this.symbolOperation;
        if (method.equals("Multiplication")) {
            return multiplication.action(one, two);
        } else if (method.equals("Division")) {
            return division.action(one, two);
        } else if (method.equals("Addition")) {
            return addition.action(one, two);
        } else return subtraction.action(one, two);
    }

    public void outputHistory() {
        razdelitel();
        System.out.println("История операций");
        razdelitel();
        for (int a = 0; a < history.length; a++) {
            System.out.println(history[a]);
        }
        razdelitel();
    }

    public void addResulHistory() {
        if (operationCounter >= history.length) {
            operationCounter = 0;
            history[operationCounter] = res;
        } else {
            history[operationCounter] = res;
        }
    }

    public void razdelitel() {
        System.out.println("---------------------------");
    }


}
