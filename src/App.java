import javax.xml.bind.DataBindingException;
import java.util.Scanner;

public class App {
    private static final String ERROR_TOKEN_PARSE_PATTERN = "Error! Unexpected token: %s";
    private static final String MESSAGE_TRY_AGAIN = "Try again >>>";

    private Scanner sc;

    public App() {
        sc = new Scanner(System.in);
    }

    public void run() {

        System.out.println("#####\tВычесление площади двух прямоугольников\t#####");
        System.out.print("Initial state: ");
        State s = Manager.getState();
        System.out.println(s);

        boolean cont = true;
        String input;

        while (cont) {
            System.out.print("Введите число от 1 до 6, что бы выбрать команду");
            input = sc.next();

            if (!checkOperator(input)) {
                System.out.println("Incorrect operator! Try again");
                continue;
            }

            try {
                if ("1".equals(input)) {
                    System.out.print("Изменение ширины первого прямоугольника. Введите новую ширину ");
                    double alpha = readDouble();
                    s.getRect1().setWidth(alpha);
                }

                if ("2".equals(input)) {
                    System.out.print("Изменение высоты первого прямоугольника. Ведите новую высоту ");
                    double alpha = readDouble();
                    s.getRect1().setHeight(alpha);
                }

                if ("3".equals(input)) {
                    System.out.print("Изменение местоположения первого прямоугольника. На сколько хотите переместить? Введите x и y ");
                    double alpha = readDouble();
                    double betta = readDouble();
                    s.getRect1().move(alpha, betta);
                }

                if ("4".equals(input)) {
                    System.out.print("Изменение ширины второго прямоугольника. Введите новую ширину ");
                    double alpha = readDouble();
                    s.getRect2().setWidth(alpha);
                }

                if ("5".equals(input)) {
                    System.out.print("Изменение высоты второго прямоугольника. Ведите новую высоту ");
                    double alpha = readDouble();
                    s.getRect2().setHeight(alpha);
                }

                if ("6".equals(input)) {
                    System.out.print("Изменение местоположения второго прямоугольника. Введите x и y ");
                    double alpha = readDouble();
                    double betta = readDouble();
                    s.getRect2().move(alpha, betta);
                }
                System.out.print("Current state: ");
                System.out.println(s);
                Manager.setState(s);
            } catch (DataBindingException e) {
                System.out.println("XML binding error! XML state file may be corrupted.");
                return;
            } catch (ArithmeticException e) {
                System.out.println(String.format("Arithmetic Error: %s", e.getMessage()));
                continue;
            }

            cont = readContinue();
        }
    }

    private boolean checkOperator(String str){
        //проверить что введенный символ 1 2 3 4 5 или 6
        return "1".equals(str) || "2".equals(str) || "3".equals(str) || "4".equals(str) || "5".equals(str) || "6".equals(str);
    }

    private double readDouble(){
        while (true) {
            String token = sc.next();
            try {
                return Double.parseDouble(token);
            } catch (NumberFormatException e) {
                System.out.println(String.format(ERROR_TOKEN_PARSE_PATTERN, token));
                System.out.print(MESSAGE_TRY_AGAIN);
            }
        }
    }

    private boolean readContinue() {
        System.out.print("Continue? (y/n) >>>");
        while (true) {
            String token = sc.next();
            if ("y".equalsIgnoreCase(token)) {
                return true;
            }
            if ("n".equalsIgnoreCase(token)) {
                return false;
            }
            System.out.println(String.format(ERROR_TOKEN_PARSE_PATTERN, token));
            System.out.print(MESSAGE_TRY_AGAIN);
        }
    }

    public static void main(String[] args) {
        new App().run();
    }
}

