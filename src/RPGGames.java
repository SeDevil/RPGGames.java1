import java.util.Random;
import java.util.Scanner;
public class RPGGames {
    public static void main(String[] args) {
        class Character {
            String name;
            int strength;
            int agility;
            int health;

            public Character(String name, int strength, int agility, int health) {
                this.name = name;
                this.strength = strength;
                this.agility = agility;
                this.health = health;
            }

            public boolean isAlive() {
                return health > 0;
            }

            public void attack(Character opponent) {
                Random rand = new Random();
                int hitChance = rand.nextInt(100);
                if (hitChance < opponent.agility) {
                    System.out.println(opponent.name + " уклоняется от атаки!");
                } else {
                    opponent.health -= this.strength;
                    System.out.println(this.name + " атакует " + opponent.name + " и наносит " + this.strength + " урона! У " + opponent.name + " осталось " + opponent.health + " здоровья.");
                }
            }

            public void heal(int amount) {
                health += amount;
                System.out.println(this.name + " восстанавливает " + amount + " здоровья! Теперь у него " + health + " здоровья.");
            }
        }
// Создаем рыцаря
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя вашего рыцаря: ");
        String knightName = scanner.nextLine();
        Character knight = new Character(knightName, 10, 70, 30);
        Character orc = new Character("Орк", 8, 50, 25);

        System.out.println("Битва начинается! Ваш рыцарь: " + knight.name);

// Выбор действия
        boolean inBattle = false;
        while (!inBattle) {
            System.out.println("1. Пойти к торговцу (увеличить силу)");
            System.out.println("2. Пойти в лес сражаться с орком");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("Вы подошли к торговцу и купили меч. Сила увеличена на 5.");
                knight.strength += 5; // Увеличиваем силу
            } else if (choice == 2) {
                System.out.println("Вы встретили орка в лесу!");
                inBattle = true; // Начинаем битву
            } else {
                System.out.println("Неверный выбор! Попробуйте снова.");
            }
        }

// Бой с орком
        while (knight.isAlive() && orc.isAlive()) {
            System.out.println("Выберите действие:");
            System.out.println("1. Атаковать");
            System.out.println("2. Защититься");
            System.out.print("Ваш выбор: ");
            int action = scanner.nextInt();

            if (action == 1) {
                knight.attack(orc);
            } else if (action == 2) {
                System.out.println(knight.name + " защищается!");
            }

            if (orc.isAlive()) {
                Random rand = new Random();
                if (rand.nextBoolean()) {
                    orc.attack(knight);
                } else {
                    orc.heal(10);
                }
            }
        }

// Итоги боя
        if (knight.isAlive()) {
            System.out.println(knight.name + " победил орка!");
            System.out.println("Вы получили 100 очков опыта и 50 золота.");
        } else {
            System.out.println(knight.name + " пал в бою!");
        }

        scanner.close();
    }
}