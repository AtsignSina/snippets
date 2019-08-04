
import java.util.stream.IntStream;

public final class IranianNationalIdentityCode {
  public static final int INVALID = 0;
  public static final int LEGAL = 1;
  public static final int PERSON = 2;

  /**
   * This method check iranian personal identity code
   *
   * @param code input code
   * @return true if input follows algorithm, Otherwise return false
   */
  public static boolean checkPersonalIdentityCode(String code) {
    if (code == null
        || code.isEmpty()
        || (code.length() != 10 || !code.matches("^\\d{10}$") || code.equals("0000000000"))) {
      return false;
    }

    int sum =
        IntStream.range(0, 9).map(i -> Character.getNumericValue(code.charAt(i)) * (10 - i)).sum()
            % 11;
    int check = Character.getNumericValue(code.charAt(9));

    return (sum < 2 && check == sum) || (sum >= 2 && check + sum == 11);
  }

  /**
   * This method check iranian legal identity code
   *
   * @param code input code
   * @return true if input follows algorithm, Otherwise return false
   */
  public static boolean checkLegalIdentityCode(String code) {
    if (code == null
        || code.isEmpty()
        || (code.length() != 11 || !code.matches("^\\d{11}$") || code.equals("00000000000"))) {
      return false;
    }
    final int[] ratio = {29, 27, 23, 19, 17, 29, 27, 23, 19, 17};
    int decimal = Character.getNumericValue(code.charAt(9));
    int remaining =
        IntStream.range(0, 10)
                .map(i -> (Character.getNumericValue(code.charAt(i)) + decimal + 2) * (ratio[i]))
                .sum()
            % 11;
    return remaining == Character.getNumericValue(code.charAt(10));
  }

  /**
   * This method check iranian identity code, either personal or legal.
   *
   * @param code input code
   * @return 1 if code follows personal algorithm or 2 if code follows legal algorithm. Otherwise
   *     retrun 0. returned value can compare with this class static final integer fields
   */
  public static int checkIranianNationalIdentityCode(String code) {
    if (checkPersonalIdentityCode(code)) {
      return PERSON;
    } else if (checkLegalIdentityCode(code)) {
      return LEGAL;
    } else {
      return INVALID;
    }
  }
}
