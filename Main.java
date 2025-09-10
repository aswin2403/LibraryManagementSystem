import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n📚 Library Management System");
            System.out.println("1. Add Book");
            System.out.println("2. List Books");
            System.out.println("3. Add Member");
            System.out.println("4. List Members");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    InsertBook.main(null);
                    break;
                case 2:
                    ListBooks.main(null);
                    break;
                case 3:
                    AddMember.main(null);
                    break;
                case 4:
                    ListMembers.main(null);
                    break;
                case 5:
                    IssueBook.main(null);
                    break;
                case 6:
                    ReturnBook.main(null);
                    break;
                case 7:
                    System.out.println("👋 Exiting... Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("❌ Invalid choice, try again!");
            }
        }
    }
}

