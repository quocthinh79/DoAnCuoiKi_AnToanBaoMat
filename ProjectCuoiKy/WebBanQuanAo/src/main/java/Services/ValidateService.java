package Services;

public class ValidateService {
    public static boolean isPasswordValid(String passWord) {
        // kiểm tra ít nhất 4 kí tự có 1 chữ cái và 1 chữ số
        String regexCheckPassWord = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,}$";
        if (!passWord.matches(regexCheckPassWord)) {
            return false;
        }

        return true;
    }

    // Kiểm tra giá trị nhập vào có rỗng hay không
    public static boolean isInputNull(String input) {
        return input.equals("");
    }

    // Kiểm tra số điện thoại nhập có hợp lệ hay không
    public static boolean isPhoneNumberValid(String phoneNumber) {
        /*
        \d{10} matches 1234567890
        (?:\d{3}-){2}\d{4} matches 123-456-7890
        \(\d{3}\)\d{3}-?\d{4} matches (123)456-7890 or (123)4567890
        */
        String regexPhoneNumberCheck = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
        return phoneNumber.matches(regexPhoneNumberCheck);
    }

    // Kiểm tra password lặp lại đúng hay sai
    public static boolean samePassword(String password, String passwordRP) {
        return password.equals(passwordRP);
    }

    // Kiểm tra email nhập có hợp lệ hay không
    public static boolean isEmail(String email) {
        String regexEmail = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$";
        return email.matches(regexEmail);
    }

    // Kiểm tra tài khoản hợp lệ (chỉ bao gồm chữ thường và số)
    public static boolean isValidUser(String username) {
        String regexUsername = "^[a-z0-9]([._-](?![._-])|[a-z0-9]){3,18}[a-z0-9]$";
        return username.matches(regexUsername);
    }
}
