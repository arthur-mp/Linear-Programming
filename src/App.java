import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        float[] objective = new float[2];
        ArrayList<Float> values = new ArrayList<Float>();

        template();

        System.out.println("Função de máximo: 1");
        System.out.println("Função de minimo: -1");
        System.out.print("Função é de: ");
        int func = input.nextInt();

        System.out.println("--------------------");
        System.out.println("Função Objetiva:");
        System.out.print("1°  Coeficiente: ");
        objective[0] = input.nextFloat();
        System.out.print("2°  Coeficiente: ");
        objective[1] = input.nextFloat();

        System.out.println("--------------------");
        System.out.print("Quantidade de restrições: ");
        int q = input.nextInt();
        String[][] restrictions = receiveRestrictions(q);
        ArrayList<String> possibleValues = functionBruteForce(restrictions, func);

        if (possibleValues.get(0) == "Solução Infinita") {
            System.out.println("Solução Infinita");
        } else {
            if (possibleValues.get(0) == "Não existe solução!") {
                System.out.println("Não existe solução!");
            } else {
                for (int i = 0; i < possibleValues.size(); i++) {
                    String[] coordinates = possibleValues.get(i).split(",");
                    float x = Float.valueOf(coordinates[0]);
                    float y = Float.valueOf(coordinates[1]);

                    if (x != 0 || y != 0) {
                        float result = calculateValues(x, y, objective);
                        values.add(result);
                    }
                }

                Collections.sort(values);

                soluction(func, values);
            }

        }

        input.close();
    }

    public static void template() {
        System.out.println("--------------------");
        System.out.println("Programação Linear");
        System.out.println("--------------------");
        System.out.println("Padrão:");
        System.out.println("Váriaveis: 2");
        System.out.println("Restrições: min. 1");
        System.out.println("            max. 3");
        System.out.println("--------------------");
    }

    public static float calculateValues(float x1, float x2, float[] objective) {
        float x = objective[0];
        float y = objective[1];

        float result = ((x1 * x) + (x2 * y));
        return result;
    }

    public static String[][] receiveRestrictions(int y) {
        Scanner input = new Scanner(System.in);
        String[][] restrictrions = new String[y][4];

        System.out.println("--------------------");
        System.out.println("Restrições:");
        System.out.println("Exemplo: ");
        System.out.println("5x + 2y <= 20");
        System.out.println("--------------------");

        for (int i = 0; i < y; i++) {
            System.out.println("--------------------");
            System.out.println(i + 1 + "° Inequação: ");
            System.out.print("1° Coeficiente: ");
            restrictrions[i][0] = input.next();
            System.out.print("2° Coeficiente: ");
            restrictrions[i][1] = input.next();
            System.out.print("Operador: ");
            restrictrions[i][2] = input.next();
            System.out.print("Valor restritivo: ");
            restrictrions[i][3] = input.next();
        }

        input.close();
        return restrictrions;

    }

    public static ArrayList<String> functionBruteForce(String[][] restrictions, int func) {
        ArrayList<String> values = new ArrayList<String>();

        float x, y, z, w;
        int valid = 0;
        int restriction = 0;

        for (int i = 0; i < restrictions.length; i++) {
            if (restrictions[i][2].equals("<=")) {
                if (Integer.parseInt(restrictions[i][3]) >= restriction) {
                    restriction = Integer.parseInt(restrictions[i][3]);
                }
            }
        }

        if (func == 1 && restriction == 0) {
            values.clear();
            values.add("Solução Infinita");
            return values;
        } else {
            for (int i = 0; i < restriction; i++) {
                for (int j = 0; j < restriction; j++) {

                    for (int k = 0; k < restrictions.length; k++) {
                        x = Float.parseFloat(restrictions[k][0]);
                        y = Float.parseFloat(restrictions[k][1]);
                        z = Float.parseFloat(restrictions[k][3]);

                        if (restrictions[k][2].equals("<=")) {
                            w = i * x + j * y;
                            if (w <= z) {
                                valid++;
                            } else {
                                valid = 0;
                            }
                            continue;
                        }
                        if (restrictions[k][2].equals(">=")) {
                            w = i * x + j * y;
                            if (w >= z) {
                                valid++;
                            } else {
                                valid = 0;
                            }
                            continue;
                        }
                    }

                    if (valid >= restrictions.length) {
                        valid = 0;
                        String value = Integer.toString(i) + "," + Integer.toString(j);
                        values.add(value);
                    }

                }
            }

            if (values.size() != 0) {
                return values;
            } else {
                values.add("Não existe solução!");
                return values;
            }

        }

    }

    public static void soluction(int func, ArrayList<Float> values) {
        System.out.println("--------------------");
        if (func == 1) {
            if (values.size() == 0) {
                System.out.println("Não possui solução");
            } else {
                if (values.get(values.size() - 1) == values.get(values.size() - 2)) {
                    System.out.println("Possui mais de uma solução");
                } else {
                    System.out.println("Solução:" + values.get(values.size() - 1));
                }
            }
        } else {
            if (values.size() == 0) {
                System.out.println("Não possui solução");
            } else {
                if (values.get(0) == values.get(1)) {
                    System.out.println("Possui mais de uma solução");
                } else {
                    System.out.println("Solução:" + values.get(0));
                }
            }
        }
        System.out.println("--------------------");
    }

}
