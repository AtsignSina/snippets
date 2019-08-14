public class PersianNumberToNumber {
    public static int convert(String value) {
    StringBuilder newValue = new StringBuilder();
    int i = 0;
    if (value == null || value.isEmpty()) {
      return 0;
    }
    boolean positive = true;
    if (value.charAt(0) == '+' || value.charAt(0) == '-') {
      positive = value.charAt(0) == '+';
      i++;
    }
    for (; i < value.length(); i++) {
      char ch = value.charAt(i);
      if (ch >= 1776 && ch <= 1785) { // For Persian digits.
        newValue.append((char) ((int) ch - 1728));
      } else if (ch >= 1632 && ch <= 1641) { // For Arabic & Unix digits.
        newValue.append((char) ((int) ch - 1584));
      } else if (ch >= 48 && ch <= 57) { // For Normal Digit
        newValue.append(ch);
      } else {
        throw new IllegalArgumentException("THERE IS NO NUMBER");
      }
    }
    return Integer.parseInt(newValue.toString()) * (positive ? 1 : -1);
  }
}
