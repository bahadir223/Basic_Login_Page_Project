package LoginProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService implements Zorunlu {

    List<String> emails = new ArrayList<>();
    List<String> passwords = new ArrayList<>();
    List<String> usernames = new ArrayList<>();
    List<User> users = new ArrayList<>();

    @Override
    public void signUp() {

        Scanner input = new Scanner(System.in);

        // 1) name- surname
        System.out.println("Name - Surname :  ");
        String name = input.nextLine();

        // 2) Username
        String userName;
        boolean existUserName;

        do {

            System.out.println("Enter username : ");
            userName = input.next();
            existUserName = usernames.contains(userName);

            if (existUserName) {
                System.out.println("Bu username daha önce kullanıldı, lütfen farklı bir username girin.");
            }

        } while (existUserName);

        usernames.add(userName);

        /*
           3)Email
           must contain @
           must be unique
         */

        String email;
        boolean existEmail;
        boolean isValid;

        do {
            System.out.println("Lütfen email adresinizi giriniz : ");
            email = input.next();
            existEmail = emails.contains(email);
            isValid = isValidEmail(email);

            if (existEmail) {
                System.out.println("Bu email daha önce kullanıldı, lütfen farklı bir email girin.");
            }
        } while (existEmail || !isValid);
        emails.add(email);

        /*
         4 ) password
         must contain at least 6 character
         */

        String password;
        boolean isValidPass;

        do {

            System.out.println("Lütfen parolanızı giriniz.");
            System.out.println("UYARI: Parolanız en az 6 haneli olmalıdır!");
            password = input.next();
            isValidPass = isValidPass(password);

        } while (!isValidPass);

        passwords.add(password);

        User user = new User(name, userName, email, password);
        users.add(user);

        System.out.println("Tebrikler! Kayıt işlemi başarıyla gerçekleştirildi.Giriş yapabilirsiniz.");


    }

    @Override
    public void logIn() {

        Scanner input = new Scanner(System.in);

        boolean isMail;
        String userEmail;

        do {
            System.out.println("Lütfen email adresinizi giriniz.");
            userEmail = input.next();
            isMail = emails.contains(userEmail);
        } while (!isMail);

        boolean isValid;
        do {
            System.out.println("Lütfen parolanızı giriniz.");
            String userPass = input.next();
            int idxEmail = emails.indexOf(userEmail);

            isValid = passwords.get(idxEmail).equals(userPass);

            if (!isValid) {
                System.out.println("Hatalı parola lütfen tekrar deneyin");
            }

        } while (!isValid);

        System.out.println("Hoşgeldiniz...");

    }

    public boolean isValidEmail(String email) {
        boolean isValid = email.contains("@");
        if (isValid == false) {
            System.out.println("Bu email geçersiz!! lütfen farklı bir email girin.");
        }
        return isValid;
    }

    public boolean isValidPass(String pass) {
        boolean isValid;
        if (pass.length() >= 6) {
            isValid = true;
        } else {
            isValid = false;
            System.out.println("Hatalı parola");
        }
        return isValid;
    }

}
